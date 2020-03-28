package cn.ghaien.machinedemo.service;

import cn.ghaien.machinedemo.entity.input.ArmInfoQuery;
import cn.ghaien.machinedemo.entity.pojo.ArmInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author guo.haien
 * @Date 2018/7/12
 */

public interface ArmInfoService {

    void save(ArmInfo armInfo);

    /**
     * 新增告警信息
     *
     * @param machineNo 设备编号
     * @param data 设备传输的数据
     */
    void addByData(String machineNo, String data);

    List<ArmInfo> queryList(ArmInfoQuery armInfoQuery);
}
