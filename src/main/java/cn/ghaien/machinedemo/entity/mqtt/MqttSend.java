package cn.ghaien.machinedemo.entity.mqtt;

/**
 * Mqtt推送消息类
 *
 * @author guo.haien
 * @date 2019/5/23 22:11
 */
public class MqttSend {

    private Boolean confirmed;

    private String data;

    private String devEUI;

    private Integer fPort;

    private String reference;

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDevEUI() {
        return devEUI;
    }

    public void setDevEUI(String devEUI) {
        this.devEUI = devEUI;
    }

    public Integer getfPort() {
        return fPort;
    }

    public void setfPort(Integer fPort) {
        this.fPort = fPort;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
