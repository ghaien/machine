package cn.usr;

import cn.usr.client.UsrCloudMqttCallback;
import cn.usr.client.UsrCloudMqttClient;
import cn.usr.impl.UsrCloudMqttClientImpl;

import java.io.UnsupportedEncodingException;

import org.eclipse.paho.client.mqttv3.MqttException;


public class UsrCloudMqttClientAdapter
        implements UsrCloudMqttClient {
    private UsrCloudMqttClient usrCloudMqttClient;

    public UsrCloudMqttClientAdapter() {
        usrCloudMqttClient = new UsrCloudMqttClientImpl();
    }

    public void Connect(String userName, String passWord) throws MqttException {
        usrCloudMqttClient.Connect(userName, passWord);
    }

    public void ConnectByToken(String userName, String token) throws MqttException {
        usrCloudMqttClient.ConnectByToken(userName, token);
    }

    public boolean DisConnectUnCheck() throws MqttException {
        return usrCloudMqttClient.DisConnectUnCheck();
    }

    public void SubscribeForDevId(String devId) throws MqttException {
        usrCloudMqttClient.SubscribeForDevId(devId);
    }

    public void SubscribeForUsername() throws MqttException {
        usrCloudMqttClient.SubscribeForUsername();
    }

    public void DisSubscribeforDevId(String devId) throws MqttException {
        usrCloudMqttClient.DisSubscribeforDevId(devId);
    }

    public void DisSubscribeforuName() throws MqttException {
        usrCloudMqttClient.DisSubscribeforuName();
    }

    public void DisSubscribeforuName(String userName) throws MqttException {
        usrCloudMqttClient.DisSubscribeforuName(userName);
    }

    public void setUsrCloudMqttCallback(UsrCloudMqttCallback CloudMqttCallback) {
        usrCloudMqttClient.setUsrCloudMqttCallback(CloudMqttCallback);
    }

    public void publishForDevId(String devId, byte[] data) throws MqttException {
        usrCloudMqttClient.publishForDevId(devId, data);
    }

    public void publishForuName(byte[] data) throws MqttException {
        usrCloudMqttClient.publishForuName(data);
    }

    public void publishForuName(String userName, byte[] data) throws MqttException {
        usrCloudMqttClient.publishForuName(userName, data);
    }

    public void SubscribeParsedByDevId(String devId) throws MqttException {
        usrCloudMqttClient.SubscribeParsedByDevId(devId);
    }

    public void SubscribeParsedForUsername() throws MqttException {
        usrCloudMqttClient.SubscribeParsedForUsername();
    }

    public void SubscribeParsedForUsername(String userName) throws MqttException {
        usrCloudMqttClient.SubscribeParsedForUsername(userName);
    }

    public void SubscribeAllDevParsedForUsername() throws MqttException {
        usrCloudMqttClient.SubscribeAllDevParsedForUsername();
    }

    public void SubscribeAllDevParsedForUsername(String userName) throws MqttException {
        usrCloudMqttClient.SubscribeParsedForUsername(userName);
    }

    public void DisSubscribeParsedforDevId(String devId) throws MqttException {
        usrCloudMqttClient.DisSubscribeParsedforDevId(devId);
    }

    public void DisSubscribeAllDevParsedForUsername() throws MqttException {
        usrCloudMqttClient.DisSubscribeAllDevParsedForUsername();
    }

    public void DisSubscribeAllDevParsedForUsername(String userName) throws MqttException {
        usrCloudMqttClient.DisSubscribeAllDevParsedForUsername(userName);
    }

    public void DisSubscribeParsedForUsername() throws MqttException {
        usrCloudMqttClient.DisSubscribeParsedForUsername();
    }

    public void DisSubscribeParsedForUsername(String userName) throws MqttException {
        usrCloudMqttClient.DisSubscribeParsedForUsername(userName);
    }

    public void publishParsedSetDataPoint(String devId, String slaveIndex, String pointId, String value) throws MqttException {
        usrCloudMqttClient.publishParsedSetDataPoint(devId, slaveIndex, pointId, value);
    }

    public void publishParsedQueryDataPoint(String devId, String slaveIndex, String pointId) throws MqttException {
        usrCloudMqttClient.publishParsedQueryDataPoint(devId, slaveIndex, pointId);
    }

    public String USRToolBytesToUtf8str(byte[] data) throws UnsupportedEncodingException {
        return usrCloudMqttClient.USRToolBytesToUtf8str(data);
    }

    public byte[] USRToolUtf8strToBytes(String data) throws UnsupportedEncodingException {
        return usrCloudMqttClient.USRToolUtf8strToBytes(data);
    }
}
