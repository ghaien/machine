package cn.usr.demo;

import cn.usr.UsrCloudMqttCallbackAdapter;

import java.io.PrintStream;


public class UsrCloudCallbackDemo
        extends UsrCloudMqttCallbackAdapter {
    public UsrCloudCallbackDemo() {
    }

    public void onConnectAck(int returnCode, String description) {
        System.out.println("【连接响应】连接返回码：" + returnCode + "返回描述：" + description);
    }


    public void onSubscribeAck(int messageId, String clientId, String topics, int returnCode) {
        System.out.println("【订阅响应】订阅消息MessageID：" + messageId + "客户端ID：" + clientId + "订阅主题" + topics + "订阅返回码" + returnCode);
    }

    public void onDisSubscribeAck(int messageId, String clientId, String topics, int returnCode) {
        super.onDisSubscribeAck(messageId, clientId, topics, returnCode);
    }


    public void onReceiveEvent(int messageId, String topic, byte[] data) {
        System.out.println("【接受响应】接受消息MessageID：" + messageId + "接受来自主题" + topic + "接受数据为:" + data);
    }

    public void onReceiveParsedEvent(int messageId, String topic, String jsonData) {
        System.out.println("【接受响应】接受消息MessageID：" + messageId + "接受来自主题" + topic + "接受数据为:" + jsonData);
    }

    public void onPublishDataAck(int messageId, String topic, boolean isSuccess) {
        System.out.println("【推送响应】推送消息MessageID：" + messageId + "推送主题" + topic + "推送结果:" + isSuccess);
    }

    public void onPublishDataResult(int messageId, String topic) {
        System.out.println("【推送响应】本次推送消息MessageID：" + messageId + "推送主题" + topic);
    }
}
