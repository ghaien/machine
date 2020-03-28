package cn.usr.demo;

import org.eclipse.paho.client.mqttv3.MqttException;


public class App {
    public App() {
    }

    public static void main(String[] args) {
        UsrCloudClientDemo client = new UsrCloudClientDemo();
        UsrCloudCallbackDemo callback = new UsrCloudCallbackDemo();
        try {
            client.setUsrCloudMqttCallback(callback);
            client.Connect("sayuan", "admin");
            Thread.sleep(1000);
            client.SubscribeParsedByDevId("123456");
            client.publishForDevId("123456", new byte[] {0x01});
            Thread.sleep(3000L);


        } catch (MqttException e) {


            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
