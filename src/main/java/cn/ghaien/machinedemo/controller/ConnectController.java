package cn.ghaien.machinedemo.controller;

import cn.ghaien.machinedemo.usrCloud.ClientCallbackAdapter;
import cn.ghaien.machinedemo.usrCloud.UsrCloudClient;
import cn.ghaien.machinedemo.util.commons.Response;
import cn.ghaien.machinedemo.util.commons.ResponseCode;
import cn.ghaien.machinedemo.util.commons.ReturnCode;
import cn.ghaien.machinedemo.util.commons.UserConnect;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 连接透传云
 *
 * @author guo.haien
 * @Date 2018/7/3
 */
@RestController
@RequestMapping(value = {"connect"})
public class ConnectController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsrCloudClient usrCloudClient;

    /**
     * 连接设备
     *
     * @return 返回值
     */
    @RequestMapping(value = {"/{userName}"}, method = RequestMethod.GET)
    public Response<Object> connect(@PathVariable String userName) {
        try {
            return usrCloudClient.connect(userName);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Response<>(ResponseCode.FAILURE);
        }
    }

    /**
     * 断开连接
     *
     * @return 返回值
     */
    @RequestMapping(value = {"disconnect/{userName}"}, method = RequestMethod.GET)
    public Response<Object> disconnect(@PathVariable String userName) {
        try {
            usrCloudClient.disConnect(userName);
        } catch (MqttException e) {
            logger.error(e.getMessage());
            return new Response<>(ResponseCode.FAILURE);
        }
        return new Response<>();
    }
}
