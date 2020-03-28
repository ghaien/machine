package cn.ghaien.machinedemo.usrCloud;

import cn.ghaien.machinedemo.entity.pojo.ArmInfo;
import cn.ghaien.machinedemo.service.ArmInfoService;
import cn.ghaien.machinedemo.util.commons.ReturnCode;
import cn.usr.UsrCloudMqttCallbackAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 1.继承 UsrCloudMqttCallbackAdapter
 *
 * @author guo.haien
 * @Date 2018/7/2
 */
public class ClientCallbackAdapter extends UsrCloudMqttCallbackAdapter {

    private Integer connectStatus;

    private Integer subscribeStatus;

    private int sendStatus = ReturnCode.SEND_NORETURN;

    private byte[] returnData = {};

    private ArmInfoService armInfoService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ClientCallbackAdapter() {
    }

    public ClientCallbackAdapter(ArmInfoService armInfoService) {
        this.armInfoService = armInfoService;
    }

    /**
     * 2.重写 onConnectAck 方法
     */
    @Override
    public void onConnectAck(int returnCode, String description) {
        connectStatus = returnCode;
        logger.info("连接状态 - " + connectStatus);
        super.onConnectAck(returnCode, description);
    }

    /**
     * 重写 onSubscribeAck 函数
     */
    @Override
    public void onSubscribeAck(int messageId, String clientId, String topics, int returnCode) {
        subscribeStatus = returnCode;
        logger.info(messageId + " " + clientId + " " + topics + " " + returnCode);
        super.onSubscribeAck(messageId, clientId, topics, returnCode);

    }

    /**
     * 重写 onDisSubscribeAck 函数
     */
    @Override
    public void onDisSubscribeAck(int messageId, String clientId, String topics, int returnCode) {
        subscribeStatus = returnCode;
        logger.info(messageId + " " + clientId + " " + topics + " " + returnCode);
        super.onDisSubscribeAck(messageId, clientId, topics, returnCode);

    }

    @Override
    public void onPublishDataAck(int messageId, String topic, boolean isSuccess) {
        logger.info(messageId + " " + topic + " " + isSuccess);
        sendStatus = isSuccess ? 0 : 1;
        super.onPublishDataAck(messageId, topic, isSuccess);
    }

    @Override
    public void onPublishDataResult(int messageId, String topic) {
        logger.info(messageId + " " + topic);
        super.onPublishDataResult(messageId, topic);
    }

    @Override
    public void onReceiveEvent(int messageId, String topic, byte[] data) {
        logger.info(messageId + " " + topic + " " + data);
        if (data[1] == (byte) 3) {
            ArmInfo armInfo = new ArmInfo();
            armInfo.setMachineNo(getMachineNo(topic));
            armInfo.setZoneNo(Integer.valueOf(data[0]));
            armInfo.setArmType(Integer.valueOf(data[4]));
            armInfoService.save(armInfo);
        } else {
            returnData = data;
        }
        super.onReceiveEvent(messageId, topic, data);
    }

    private String getMachineNo(String topic) {
        return topic.substring(topic.lastIndexOf("/") + 1, topic.length() - 1);
    }

    public Integer getConnectStatus() {
        return connectStatus;
    }

    public void setConnectStatus(Integer connectStatus) {
        this.connectStatus = connectStatus;
    }

    public Integer getSubscribeStatus() {
        return subscribeStatus;
    }

    public void setSubscribeStatus(Integer subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
    }

    public int getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(int sendStatus) {
        this.sendStatus = sendStatus;
    }

    public byte[] getReturnData() {
        return returnData;
    }

    public void setReturnData(byte[] returnData) {
        this.returnData = returnData;
    }
}
