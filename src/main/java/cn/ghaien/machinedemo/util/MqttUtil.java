package cn.ghaien.machinedemo.util;

/**
 * MQTT相关工具类
 *
 * @author guo.haien
 * @date 2019/5/22 23:31
 */
public class MqttUtil {

    private static String getTopic(String appId, String deviceId, String type) {
        return "application/" + appId + "/device/" + deviceId + "/" + type;
    }

    public static String getRxTopic(String appId, String deviceId) {
        return getTopic(appId, deviceId, "rx");
    }

    public static String getTxTopic(String appId, String deviceId) {
        return getTopic(appId, deviceId, "tx");
    }

    private static String getClientId(String appId, String deviceId, String type) {
        return appId + "_" + deviceId + "_" + type;
    }

    public static String getRxClientId(String appId, String deviceId) {
        return getClientId(appId, deviceId, "rx");
    }

    public static String getTxClientId(String appId, String deviceId) {
        return getClientId(appId, deviceId, "tx");
    }
}
