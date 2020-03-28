package cn.ghaien.machinedemo.service.impl;

import cn.ghaien.machinedemo.config.MqttClients;
import cn.ghaien.machinedemo.entity.input.OperateInput;
import cn.ghaien.machinedemo.entity.mqtt.MqttSend;
import cn.ghaien.machinedemo.service.MqttService;
import cn.ghaien.machinedemo.util.CrcCheckUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * mq业务层
 *
 * @author guo.haien
 * @date 2019/5/31 22:46
 */
@Service
public class MqttServiceImpl implements MqttService {

    @Autowired
    private MqttClients mqttClients;

    @Override
    public void publishForDevId(OperateInput operateInput) {
        byte[] dataBytes = CrcCheckUtil.getData(operateInput);
        String data = Base64.getEncoder().encodeToString(dataBytes);
        MqttSend mqttSend = new MqttSend();
        mqttSend.setConfirmed(true);
        mqttSend.setfPort(93);
        mqttSend.setReference("test");
        mqttSend.setDevEUI("66888888888888");
        mqttSend.setData(data);
        mqttClients.publish(operateInput.getZoneNum() + "", operateInput.getDevId(), JSON.toJSONString(mqttSend));
    }
}
