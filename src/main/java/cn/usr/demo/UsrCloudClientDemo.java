package cn.usr.demo;

import cn.usr.UsrCloudMqttClientAdapter;
import cn.usr.client.UsrCloudMqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;


public class UsrCloudClientDemo
        extends UsrCloudMqttClientAdapter {
    public UsrCloudClientDemo() {
    }

    public void Connect(String userName, String passWord)
            throws MqttException {
        super.Connect(userName, passWord);
    }

    public void setUsrCloudMqttCallback(UsrCloudMqttCallback CloudMqttCallback) {
        super.setUsrCloudMqttCallback(CloudMqttCallback);
    }

    public void publishParsedSetDataPoint(String devId, String slaveIndex, String pointId, String value) throws MqttException {
        super.publishParsedSetDataPoint(devId, slaveIndex, pointId, value);
    }

    public void publishParsedQueryDataPoint(String devId, String slaveIndex, String pointId) throws MqttException {
        super.publishParsedQueryDataPoint(devId, slaveIndex, pointId);
    }

    public void SubscribeParsedByDevId(String devId) throws MqttException {
        super.SubscribeParsedByDevId(devId);
    }

    public void publishForDevId(String devId, byte[] data) throws MqttException {
        super.publishForDevId(devId, data);
    }
}
