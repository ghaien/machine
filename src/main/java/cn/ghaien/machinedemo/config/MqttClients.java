package cn.ghaien.machinedemo.config;

import cn.ghaien.machinedemo.entity.mqtt.MqttSend;
import cn.ghaien.machinedemo.util.MqttUtil;
import com.alibaba.fastjson.JSON;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mq客户端
 *
 * @author guo.haien
 * @date 2019/5/22 23:37
 */
@Component
public class MqttClients {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String SERVER_URI = "tcp://39.97.186.43:1883";

    private static final String USER_NAME = "test";

    private static final char[] PASS_WORD = "test".toCharArray();

    private static ConcurrentHashMap<String, BlockingQueue<String>> subMessageMap = new ConcurrentHashMap<>();

    /**
     * 订阅消息
     *
     * @param appId
     * @param deviceId
     */
    public void subscribe(String appId, String deviceId) {
        String clientId = MqttUtil.getRxClientId(appId, deviceId);
        String topic = MqttUtil.getRxTopic(appId, deviceId);
        int qos = 1;
        MqttClient client = null;
        try {
            // clientId即连接MQTT的客户端ID，MemoryPersistence设置clientId的保存形式，默认为以内存保存
            client = new MqttClient(SERVER_URI, clientId, new MemoryPersistence());
            // MQTT的连接设置
            MqttConnectOptions options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置连接的用户名
            options.setUserName(USER_NAME);
            // 设置连接的密码
            options.setPassword(PASS_WORD);
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            // 设置回调函数
            client.setCallback(new MqttCallback() {
                public void connectionLost(Throwable cause) {
                    logger.info("connection lost topic: " + topic);
                }
                public void messageArrived(String topic, MqttMessage message) throws InterruptedException {
                    logger.info("topic: " + topic);
                    logger.info("Qos: " + message.getQos());
                    logger.info("message content: " + new String(message.getPayload()));
                    BlockingQueue<String> messageQueue = subMessageMap.get(clientId);
                    messageQueue.put(new String(message.getPayload()));
                }
                public void deliveryComplete(IMqttDeliveryToken token) {
                    logger.info("delivery complete: " + token.isComplete());
                }
            });
            client.connect(options);
            //订阅消息
            client.subscribe(topic, qos);
            subMessageMap.put(clientId, new ArrayBlockingQueue<>(100));
        } catch (Exception e) {
            logger.error(e.getMessage());
            try {
                if (client != null) {
                    client.disconnect();
                    subMessageMap.remove(clientId);
                }
            } catch (MqttException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void publish(String appId, String deviceId, MqttSend mqttSend) {
        publish(appId, deviceId, JSON.toJSONString(mqttSend));
    }

    /**
     * 推送消息
     *
     * @param appId
     * @param deviceId
     * @param content
     */
    public void publish(String appId, String deviceId, String content) {
//        String content = "{\"confirmed\":true,\"data\":\"MDEyNDU=\",\"devEUI\":\"6688888888888888\",\"fPort\":3,\"reference\":\"test\"}";
        String topic = MqttUtil.getTxTopic(appId, deviceId);
        String clientId = MqttUtil.getTxClientId(appId, deviceId);
        MqttClient client = null;
        int qos = 1;
        try {
            // 创建客户端
            client = new MqttClient(SERVER_URI, clientId, new MemoryPersistence());
            // 创建链接参数
            MqttConnectOptions connOpts = new MqttConnectOptions();
            // 在重新启动和重新连接时记住状态
            connOpts.setCleanSession(false);
            // 设置连接的用户名
            connOpts.setUserName(USER_NAME);
            connOpts.setPassword(PASS_WORD);
            // 建立连接
            client.connect(connOpts);
            // 创建消息
            MqttMessage message = new MqttMessage(content.getBytes());
            // 设置消息的服务质量
            message.setQos(qos);
            // 发布消息
            client.publish(topic, message);
            logger.info("send message: " +  message);
            // 断开连接
            client.disconnect();
            // 关闭客户端
            client.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (client != null) {
                try {
                    client.disconnect();
                } catch (MqttException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
