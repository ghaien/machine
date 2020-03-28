package cn.usr.impl;

import cn.usr.client.UsrCloudMqttCallback;
import cn.usr.client.UsrCloudMqttClient;
import cn.usr.entity.MqttPropertise;
import cn.usr.utils.BeasUtils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class UsrCloudMqttClientImpl
        implements MqttCallbackExtended, UsrCloudMqttClient {
    private UsrCloudMqttCallback usrCloudMqttCallback;
    private volatile String userName;
    private volatile MqttAsyncClient mqttAsyncClient;

    public UsrCloudMqttClientImpl() {
    }

    public void setUsrCloudMqttCallback(UsrCloudMqttCallback usrCloudMqttCallback) {
        this.usrCloudMqttCallback = usrCloudMqttCallback;
    }

    public void Connect(String userName, String passWord) throws MqttException {
        Connect1(userName, BeasUtils.getMD5(passWord));
    }

    private void Connect1(String userName, String passWord) throws MqttException {
        String clientId = (MqttPropertise.CLIENTID_PREFIX + userName).trim();
        this.userName = userName;
        mqttAsyncClient = new MqttAsyncClient(MqttPropertise.SERVER_ADDRESS, clientId, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(600);
        options.setAutomaticReconnect(true);
        mqttAsyncClient.setCallback(this);
        mqttAsyncClient.connect(options, null, new IMqttActionListener() {
            public void onSuccess(IMqttToken token) {
                if (usrCloudMqttCallback != null) {
                    usrCloudMqttCallback.onConnectAck(0, "连接成功");
                }
            }

            public void onFailure(IMqttToken token, Throwable throwable) {
                if (usrCloudMqttCallback != null) {
                    usrCloudMqttCallback.onConnectAck(1, throwable.toString());
                }
            }
        });
    }

    public void ConnectByToken(String userName, String token) throws MqttException {
        Connect1(userName, token);
    }

    public boolean DisConnectUnCheck() throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            return false;
        }
        mqttAsyncClient.disconnect();
        return true;
    }


    public void SubscribeForDevId(String devId)
            throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_SUBSCRIBE_DEV_RAW.replaceAll("<Id>", devId);
        Subscribe(topic);
    }

    public void SubscribeForUsername() throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_SUBSCRIBE_USER_RAW.replaceAll("<Account>", userName);
        Subscribe(topic);
    }

    public void SubscribeParsedByDevId(String devId) throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_SUBSCRIBE_DEV_PARSED.replaceAll("<Id>", devId);
        Subscribe(topic);
    }

    public void SubscribeParsedForUsername() throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_SUBSCRIBE_USER_PARSED.replaceAll("<Account>", userName);
        Subscribe(topic);
    }

    public void SubscribeParsedForUsername(String userName) throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_SUBSCRIBE_USER_PARSED.replaceAll("<Account>", userName);
        Subscribe(topic);
    }

    public void SubscribeAllDevParsedForUsername() throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }

        String topic = MqttPropertise.TOPIC_SUBSCRIBE_USER_ALLDEV_PARSED.replaceAll("<Account>", userName);
        Subscribe(topic);
    }

    public void SubscribeAllDevParsedForUsername(String userName) throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }

        String topic = MqttPropertise.TOPIC_SUBSCRIBE_USER_ALLDEV_PARSED.replaceAll("<Account>", userName);
        Subscribe(topic);
    }


    private void Subscribe(String topic)
            throws MqttException {
        int[] Qos = {0};
        String[] topics = {topic.trim()};
        mqttAsyncClient.subscribe(topics, Qos).setActionCallback(new IMqttActionListener() {
            public void onSuccess(IMqttToken iMqttToken) {
                if (usrCloudMqttCallback != null) {
                    usrCloudMqttCallback.onSubscribeAck(iMqttToken.getMessageId(), iMqttToken.getClient().getClientId(), Arrays.toString(iMqttToken.getTopics()), 0);
                }
            }

            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                if (usrCloudMqttCallback != null) {
                    usrCloudMqttCallback.onSubscribeAck(iMqttToken.getMessageId(), iMqttToken.getClient().getClientId(), Arrays.toString(iMqttToken.getTopics()), 1);
                }
            }
        });
    }


    public void DisSubscribeforDevId(String devId)
            throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_SUBSCRIBE_DEV_RAW.replaceAll("<Id>", devId);
        UnSubscribe(topic);
    }

    public void DisSubscribeforuName() throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_SUBSCRIBE_USER_RAW.replaceAll("<Account>", userName);
        UnSubscribe(topic);
    }

    public void DisSubscribeforuName(String userName) throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_SUBSCRIBE_USER_RAW.replaceAll("<Account>", userName);
        UnSubscribe(topic);
    }

    public void DisSubscribeParsedforDevId(String devId) throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_SUBSCRIBE_DEV_PARSED.replaceAll("<Id>", devId);
        UnSubscribe(topic);
    }

    public void DisSubscribeAllDevParsedForUsername() throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_SUBSCRIBE_USER_ALLDEV_PARSED.replaceAll("<Account>", userName);
        UnSubscribe(topic);
    }

    public void DisSubscribeAllDevParsedForUsername(String userName) throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_SUBSCRIBE_USER_ALLDEV_PARSED.replaceAll("<Account>", userName);
        UnSubscribe(topic);
    }

    public void DisSubscribeParsedForUsername() throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_SUBSCRIBE_USER_PARSED.replaceAll("<Account>", userName);
        UnSubscribe(topic);
    }

    public void DisSubscribeParsedForUsername(String userName) throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_SUBSCRIBE_USER_PARSED.replaceAll("<Account>", userName);
        UnSubscribe(topic);
    }


    private void UnSubscribe(String topic)
            throws MqttException {
        mqttAsyncClient.unsubscribe(topic).setActionCallback(new IMqttActionListener() {
            public void onSuccess(IMqttToken iMqttToken) {
                if (usrCloudMqttCallback != null) {
                    usrCloudMqttCallback.onDisSubscribeAck(iMqttToken.getMessageId(), iMqttToken.getClient().getClientId(), Arrays.toString(iMqttToken.getTopics()), 0);
                }
            }

            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                if (usrCloudMqttCallback != null) {
                    usrCloudMqttCallback.onDisSubscribeAck(iMqttToken.getMessageId(), iMqttToken.getClient().getClientId(), Arrays.toString(iMqttToken.getTopics()), 1);
                }
            }
        });
    }


    public void publishForDevId(String devId, byte[] data)
            throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_PUBLISH_DEV_RAW.replaceAll("<Id>", devId);
        PublishData(topic, data);
    }

    public void publishForuName(byte[] data) throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_PUBLISH_USER_RAW.replaceAll("<Account>", userName);
        PublishData(topic, data);
    }

    public void publishForuName(String userName, byte[] data) throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_PUBLISH_USER_RAW.replaceAll("<Account>", userName);
        PublishData(topic, data);
    }

    public void publishParsedSetDataPoint(String devId, String slaveIndex, String pointId, String value) throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_PUBLISH_DEV_PARSED.replaceAll("<Id>", devId);
        String data = MqttPropertise.JSON_SETDATAPOINT.replaceAll("%SLAVEINDEX%", slaveIndex).replaceAll("%POINTID%", pointId).replaceAll("%POINTVALUE%", value);
        PublishData(topic, data.getBytes());
    }

    public void publishParsedQueryDataPoint(String devId, String slaveIndex, String pointId) throws MqttException {
        if ((mqttAsyncClient == null) && (!mqttAsyncClient.isConnected())) {
            throw new MqttException(32104);
        }
        String topic = MqttPropertise.TOPIC_PUBLISH_DEV_PARSED.replaceAll("<Id>", devId);
        String data = MqttPropertise.JSON_QUERYDATAPOINT.replaceAll("%SLAVEINDEX%", slaveIndex).replaceAll("%POINTID%", pointId);
        PublishData(topic, data.getBytes());
    }

    public String USRToolBytesToUtf8str(byte[] data) throws UnsupportedEncodingException {
        return new String(data, "utf-8");
    }

    public byte[] USRToolUtf8strToBytes(String data) throws UnsupportedEncodingException {
        return data.getBytes("utf-8");
    }


    private void PublishData(String topic, byte[] data)
            throws MqttException {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(1);
        mqttMessage.setRetained(true);
        mqttMessage.setPayload(data);
        IMqttDeliveryToken publish = mqttAsyncClient.publish(topic, mqttMessage);
        if (usrCloudMqttCallback != null) {
            usrCloudMqttCallback.onPublishDataResult(publish.getMessageId(), Arrays.toString(publish.getTopics()));
        }
        publish.setActionCallback(new IMqttActionListener() {
            public void onSuccess(IMqttToken iMqttToken) {
                if (usrCloudMqttCallback != null) {
                    usrCloudMqttCallback.onPublishDataAck(iMqttToken.getMessageId(), Arrays.toString(iMqttToken.getTopics()), true);
                }
            }

            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                if (usrCloudMqttCallback != null) {
                    usrCloudMqttCallback.onPublishDataAck(iMqttToken.getMessageId(), Arrays.toString(iMqttToken.getTopics()), true);
                }
            }
        });
    }


    public void connectionLost(Throwable cause) {
        if (usrCloudMqttCallback != null) {
            usrCloudMqttCallback.onConnectAck(3, cause.toString());
        }
    }


    public void deliveryComplete(IMqttDeliveryToken token) {
    }


    public void messageArrived(String topic, MqttMessage message)
            throws Exception {
        if (usrCloudMqttCallback != null) {
            if (topic.contains("JsonTx")) {
                usrCloudMqttCallback.onReceiveParsedEvent(message.getId(), topic, message.toString());
            } else {
                usrCloudMqttCallback.onReceiveEvent(message.getId(), topic, message.getPayload());
            }
        }
    }


    public void connectComplete(boolean b, String s) {
        if (usrCloudMqttCallback != null) {
            usrCloudMqttCallback.onConnectAck(2, "与服务器完成连接");
        }
    }
}
