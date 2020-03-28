package cn.ghaien.machinedemo.entity.pojo;

import javax.persistence.*;

/**
 * @author guo.haien
 * @Date 2018/7/5
 */
@Entity
@Table(name = "TBL_USER_MACHINE")
public class UserMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userInfoId;

    private Long machineInfoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public Long getMachineInfoId() {
        return machineInfoId;
    }

    public void setMachineInfoId(Long machineInfoId) {
        this.machineInfoId = machineInfoId;
    }
}
