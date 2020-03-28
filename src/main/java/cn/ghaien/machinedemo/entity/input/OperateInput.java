package cn.ghaien.machinedemo.entity.input;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author guo.haien
 * @Date 2018/7/9
 */
public class OperateInput {

    private String userName;

    private String devId;

    private Integer zoneNum;

    private Integer operateType;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operateTime;

    private Integer secondNum;

    private Integer sensitivity;

    private Double pressureValue;

    private Integer armType;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime startArm;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime endArm;

    private Integer devStatus;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public Integer getZoneNum() {
        return zoneNum;
    }

    public void setZoneNum(Integer zoneNum) {
        this.zoneNum = zoneNum;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public LocalDateTime getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(LocalDateTime operateTime) {
        this.operateTime = operateTime;
    }

    public Integer getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(Integer secondNum) {
        this.secondNum = secondNum;
    }

    public Integer getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(Integer sensitivity) {
        this.sensitivity = sensitivity;
    }

    public Double getPressureValue() {
        return pressureValue;
    }

    public void setPressureValue(Double pressureValue) {
        this.pressureValue = pressureValue;
    }

    public Integer getArmType() {
        return armType;
    }

    public void setArmType(Integer armType) {
        this.armType = armType;
    }

    public LocalTime getStartArm() {
        return startArm;
    }

    public void setStartArm(LocalTime startArm) {
        this.startArm = startArm;
    }

    public LocalTime getEndArm() {
        return endArm;
    }

    public void setEndArm(LocalTime endArm) {
        this.endArm = endArm;
    }

    public Integer getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(Integer devStatus) {
        this.devStatus = devStatus;
    }
}
