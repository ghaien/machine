package cn.ghaien.machinedemo.service;

import cn.ghaien.machinedemo.entity.input.MachineInfoQuery;
import cn.ghaien.machinedemo.entity.pojo.MachineInfo;
import cn.ghaien.machinedemo.util.commons.PageBean;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 设备信息 service
 *
 * @author guo.haien
 * @date 2018/7/9
 */
public interface MachineInfoService {
    /**
     * 分页查询设备信息
     *
     * @return 设备信息集合
     */
    PageBean<MachineInfo> queryList(MachineInfoQuery query);

    /**
     * 通过设备的id来获取设备信息
     *
     * @param id 设备id
     * @return 设备信息
     */
    MachineInfo getOne(Long id);

    /**
     * 通过用户id来查询设备列表信息
     *
     * @param userId 用户id
     * @return 设备列表信息
     */
    List<MachineInfo> queryListByUserId(Long userId);

    /**
     * 插入设备信息
     *
     * @param machineInfo 设备信息
     */
    void add(MachineInfo machineInfo);

    /**
     * 修改设备信息
     *
     * @param machineInfo 设备信息
     */
    void update(MachineInfo machineInfo);

    /**
     * 删除指定id的设备信息（逻辑删除）
     *
     * @param id 设备id
     */
    void delete(Long id);

    /**
     * 删除指定编号的设备信息（逻辑删除）
     *
     * @param machineNo 设备编号
     */
    void delete(String machineNo);
}
