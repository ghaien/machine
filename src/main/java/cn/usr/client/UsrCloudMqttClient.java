package cn.usr.client;

import java.io.UnsupportedEncodingException;

import org.eclipse.paho.client.mqttv3.MqttException;

public abstract interface UsrCloudMqttClient {
    public abstract void setUsrCloudMqttCallback(UsrCloudMqttCallback paramUsrCloudMqttCallback);

    public abstract void Connect(String paramString1, String paramString2)
            throws MqttException;

    public abstract void ConnectByToken(String paramString1, String paramString2)
            throws MqttException;

    public abstract boolean DisConnectUnCheck()
            throws MqttException;

    public abstract void SubscribeForDevId(String paramString)
            throws MqttException;

    public abstract void SubscribeParsedByDevId(String paramString)
            throws MqttException;

    public abstract void SubscribeForUsername()
            throws MqttException;

    public abstract void SubscribeParsedForUsername()
            throws MqttException;

    public abstract void SubscribeParsedForUsername(String paramString)
            throws MqttException;

    public abstract void SubscribeAllDevParsedForUsername()
            throws MqttException;

    public abstract void SubscribeAllDevParsedForUsername(String paramString)
            throws MqttException;

    public abstract void DisSubscribeforDevId(String paramString)
            throws MqttException;

    public abstract void DisSubscribeParsedforDevId(String paramString)
            throws MqttException;

    public abstract void DisSubscribeAllDevParsedForUsername()
            throws MqttException;

    public abstract void DisSubscribeAllDevParsedForUsername(String paramString)
            throws MqttException;

    public abstract void DisSubscribeforuName()
            throws MqttException;

    public abstract void DisSubscribeforuName(String paramString)
            throws MqttException;

    public abstract void DisSubscribeParsedForUsername()
            throws MqttException;

    public abstract void DisSubscribeParsedForUsername(String paramString)
            throws MqttException;

    public abstract void publishForDevId(String paramString, byte[] paramArrayOfByte)
            throws MqttException;

    public abstract void publishForuName(byte[] paramArrayOfByte)
            throws MqttException;

    public abstract void publishForuName(String paramString, byte[] paramArrayOfByte)
            throws MqttException;

    public abstract void publishParsedSetDataPoint(String paramString1, String paramString2, String paramString3, String paramString4)
            throws MqttException;

    public abstract void publishParsedQueryDataPoint(String paramString1, String paramString2, String paramString3)
            throws MqttException;

    public abstract String USRToolBytesToUtf8str(byte[] paramArrayOfByte)
            throws UnsupportedEncodingException;

    public abstract byte[] USRToolUtf8strToBytes(String paramString)
            throws UnsupportedEncodingException;
}
