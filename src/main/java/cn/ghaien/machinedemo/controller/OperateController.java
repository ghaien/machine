package cn.ghaien.machinedemo.controller;

import cn.ghaien.machinedemo.entity.input.OperateInput;
import cn.ghaien.machinedemo.service.MqttService;
import cn.ghaien.machinedemo.usrCloud.UsrCloudClient;
import cn.ghaien.machinedemo.util.CrcCheckUtil;
import cn.ghaien.machinedemo.util.commons.Response;
import cn.ghaien.machinedemo.util.commons.ResponseCode;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guo.haien
 * @Date 2018/7/4
 */
@RestController
@RequestMapping(value = {"operate"})
public class OperateController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MqttService mqttService;

    @RequestMapping(method = RequestMethod.GET)
    public Response<String> operate(OperateInput operateInput) {
        try {
            mqttService.publishForDevId(operateInput);
            return new Response<>();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new Response<>(ResponseCode.FAILURE);
        }
    }
}
