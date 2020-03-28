package cn.ghaien.machinedemo.usrCloud;

import cn.ghaien.machinedemo.entity.input.OperateInput;
import cn.ghaien.machinedemo.entity.pojo.MachineInfo;
import cn.ghaien.machinedemo.remote.MachineRemote;
import cn.ghaien.machinedemo.service.ArmInfoService;
import cn.ghaien.machinedemo.util.CrcCheckUtil;
import cn.ghaien.machinedemo.util.HttpUtils;
import cn.ghaien.machinedemo.util.StringUtils;
import cn.ghaien.machinedemo.util.commons.Response;
import cn.ghaien.machinedemo.util.commons.ResponseCode;
import cn.ghaien.machinedemo.util.commons.ReturnCode;
import cn.ghaien.machinedemo.util.commons.UserConnect;
import cn.ghaien.machinedemo.util.exceptions.ConnectException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 透传云连接客户端
 *
 * @author guo.haien
 * @Date 2018/7/2
 */
@Component
public class UsrCloudClient {

    @Value("${USR_CLOUD_NAME}")
    private String usrCloudName;

    @Value("${USR_CLOUD_PASSWORD}")
    private String usrCloudPassword;

    @Autowired
    private ArmInfoService armInfoService;

    @Autowired
    private MachineRemote machineRemote;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 存储连接 key：用户名称 value：连接信息
     */
    public Map<String, UserConnect> map = new HashMap<>(512);

    /**
     * 连接
     *
     * @param userName 用户名称
     * @throws MqttException 异常信息
     */
    public Response<Object> connect(String userName) throws MqttException, InterruptedException {
        ClientAdapter clientAdapter;
        ClientCallbackAdapter clientCallbackAdapter;

        /* 1.初始化客户端适配 */
        /* 2.初始化客户端回调适配 */
        UserConnect userConnect = map.get(userName);
        if (userConnect != null) {
            clientAdapter = userConnect.getClientAdapter();
            clientCallbackAdapter = userConnect.getClientCallbackAdapter();
            if (ReturnCode.SERVER_CONNECT.equals(clientCallbackAdapter.getConnectStatus())) {
                return new Response<>();
            }
        } else {
            userConnect = new UserConnect();
            clientAdapter = new ClientAdapter();
            clientCallbackAdapter = new ClientCallbackAdapter(armInfoService);
            userConnect.setClientAdapter(clientAdapter);
            userConnect.setClientCallbackAdapter(clientCallbackAdapter);
            map.put(userName, userConnect);
        }

        /* 3.客户端设置回调 */
        clientAdapter.setUsrCloudMqttCallback(clientCallbackAdapter);

        /* 4.进行连接 */
        clientAdapter.Connect(usrCloudName, usrCloudPassword);

        /* 5.判断是否连接成功 */
        if (isReturn(1, clientCallbackAdapter)) {
            if (ReturnCode.SERVER_CONNECT.equals(clientCallbackAdapter.getConnectStatus())) {
                return new Response<>();
            } else {
                return new Response<>(ResponseCode.CONNECT_FAIL);
            }
        } else {
            return new Response<>(ResponseCode.CONNECT_TIMEOUT);
        }
    }

    /**
     * 断开连接
     *
     * @param userName 用户名称
     * @throws MqttException 异常信息
     */
    public void disConnect(String userName) throws MqttException {
        UserConnect userConnect = map.get(userName);
        if (userConnect != null &&
                userConnect.getClientAdapter().DisConnectUnCheck()) {
            map.remove(userName);
        }
    }

    /**
     * 指定用户订阅指定设备
     *
     * @param userName 用户名称
     * @param devId    设备编号
     * @throws MqttException 异常信息
     */
    public Response<Object> SubscribeForDevId(String userName, String devId) throws MqttException, ConnectException, InterruptedException {
        UserConnect userConnect = map.get(userName);
        if (userConnect == null) {
            throw new ConnectException("未连接服务器");
        }
        ClientAdapter clientAdapter = userConnect.getClientAdapter();
        ClientCallbackAdapter clientCallbackAdapter = userConnect.getClientCallbackAdapter();
        // 将上次订阅的结果置空
        clientCallbackAdapter.setSubscribeStatus(null);
        clientAdapter.SubscribeForDevId(devId);

        /* 判断是否订阅成功 */
        if (isReturn(2, clientCallbackAdapter)) {
            if (ReturnCode.SUBSCRIBE_SUCCESS.equals(clientCallbackAdapter.getSubscribeStatus())) {
                return new Response<>();
            } else {
                return new Response<>(ResponseCode.SUBSCRIBE_FAIL);
            }
        } else {
            return new Response<>(ResponseCode.SUBSCRIBE_TIMEOUT);
        }
    }

    /**
     * 指定用户取消订阅指定设备
     *
     * @param userName 用户名称
     * @param devId    设备编号
     * @throws MqttException 异常信息
     */
    public Response<Object> DisSubscribeforDevId(String userName, String devId) throws MqttException, ConnectException, InterruptedException {
        UserConnect userConnect = map.get(userName);
        if (userConnect == null) {
            throw new ConnectException("未连接服务器");
        }
        ClientAdapter clientAdapter = userConnect.getClientAdapter();
        ClientCallbackAdapter clientCallbackAdapter = userConnect.getClientCallbackAdapter();
        // 将上次取消订阅的结果置空
        clientCallbackAdapter.setSubscribeStatus(null);
        clientAdapter.DisSubscribeforDevId(devId);

        /* 判断是否取消订阅成功 */
        if (isReturn(2, clientCallbackAdapter)) {
            if (ReturnCode.SUBSCRIBE_SUCCESS.equals(clientCallbackAdapter.getSubscribeStatus())) {
                return new Response<>();
            } else {
                return new Response<>(ResponseCode.DIS_SUBSCRIBE_FAIL);
            }
        } else {
            return new Response<>(ResponseCode.DIS_SUBSCRIBE_TIMEOUT);
        }
    }

    public Response<String> publishForDevId(OperateInput operateInput) throws Exception {
        String data = StringUtils.bytesToString(CrcCheckUtil.getData(operateInput));
        Response<String> response = machineRemote.operate(operateInput.getDevId(), data);
        // 若返回的code为成功的则判断设备的响应消息是否正确，否则直接返回请求的结果
        if (ResponseCode.SUCCESS.getCode().equals(response.getCode())) {
            String responseMsg = response.getData();
            /* 判断是否接收到正确的响应消息 */
            if (!org.springframework.util.StringUtils.isEmpty(responseMsg)) {
                log.info("收到响应消息={}", responseMsg);
                if (CrcCheckUtil.checkReturnData(StringUtils.stringToBytes(responseMsg), operateInput)) {
                    return new Response<>();
                } else {
                    return new Response<>(ResponseCode.RESPONSE_ERROR);
                }
            } else {
                return new Response<>(ResponseCode.RESPONSE_TIMEOUT);
            }
        } else {
            return response;
        }

    }

    /**
     * 判断是否接收到回调值
     *
     * @param type                  类型 1 连接 2 订阅 3 发送操作指令
     * @param clientCallbackAdapter 回调对象
     * @return 返回值
     */
    private boolean isReturn(int type, ClientCallbackAdapter clientCallbackAdapter) throws InterruptedException {
        boolean flag = false;
        int i = 0;
        while (!flag && i++ < 50) {
            Thread.sleep(100);
            switch (type) {
                // 连接
                case 1:
                    flag = clientCallbackAdapter.getConnectStatus() != null;
                    break;
                // 订阅
                case 2:
                    flag = clientCallbackAdapter.getSubscribeStatus() != null;
                    break;
                // 发送操作指令
                default:
                    flag = clientCallbackAdapter.getReturnData().length != 0;
            }
        }
        return flag;
    }
}
