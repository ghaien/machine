package cn.ghaien.machinedemo.entity.pojo;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author guo.haien
 * @Date 2018/7/5
 */
@Entity
@Table(name = "tbl_arm_info")
public class ArmInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String machineNo;

    private Integer zoneNo;

    private Integer armType;

    private LocalDateTime createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
    }

    public Integer getZoneNo() {
        return zoneNo;
    }

    public void setZoneNo(Integer zoneNo) {
        this.zoneNo = zoneNo;
    }

    public Integer getArmType() {
        return armType;
    }

    public void setArmType(Integer armType) {
        this.armType = armType;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
