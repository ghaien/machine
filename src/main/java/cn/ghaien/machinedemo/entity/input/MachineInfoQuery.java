package cn.ghaien.machinedemo.entity.input;

/**
 * 设备信息查询条件
 *
 * @author guo.haien
 * @date 2018/10/8 22:55
 */
public class MachineInfoQuery extends PageQuery {

    private String machineUseNo;

    public String getMachineUseNo() {
        return machineUseNo;
    }

    public void setMachineUseNo(String machineUseNo) {
        this.machineUseNo = machineUseNo;
    }
}
