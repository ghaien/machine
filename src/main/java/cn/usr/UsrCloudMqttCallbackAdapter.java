package cn.usr;

import cn.usr.client.UsrCloudMqttCallback;

public class UsrCloudMqttCallbackAdapter
        implements UsrCloudMqttCallback {
    public UsrCloudMqttCallbackAdapter() {
    }

    public void onConnectAck(int returnCode, String description) {
    }

    public void onSubscribeAck(int messageId, String clientId, String topics, int returnCode) {
    }

    public void onDisSubscribeAck(int messageId, String clientId, String topics, int returnCode) {
    }

    public void onReceiveEvent(int messageId, String topic, byte[] data) {
    }

    public void onReceiveParsedEvent(int messageId, String topic, String jsonData) {
    }

    public void onPublishDataAck(int messageId, String topic, boolean isSuccess) {
    }

    public void onPublishDataResult(int messageId, String topic) {
    }
}
