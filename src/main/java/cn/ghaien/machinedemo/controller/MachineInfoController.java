package cn.ghaien.machinedemo.controller;

import cn.ghaien.machinedemo.entity.input.MachineInfoQuery;
import cn.ghaien.machinedemo.entity.pojo.MachineInfo;
import cn.ghaien.machinedemo.service.MachineInfoService;
import cn.ghaien.machinedemo.util.commons.PageBean;
import cn.ghaien.machinedemo.util.commons.Response;
import cn.ghaien.machinedemo.util.commons.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 设备信息 controller
 *
 * @author guo.haien
 * @Date 2018/7/9
 */
@RestController
@RequestMapping(value = {"/machineInfo"})
public class MachineInfoController {

    @Autowired
    private MachineInfoService machineInfoService;

    /**
     * 设备列表查询
     *
     * @return 设备列表信息
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public Response<PageBean<MachineInfo>> list(MachineInfoQuery query) {
        return new Response<>(ResponseCode.SUCCESS, machineInfoService.queryList(query));
    }

    /**
     * 获取某个设备信息
     *
     * @param id 设备id
     * @return 单个设备信息
     */
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public Response<MachineInfo> list(@PathVariable Long id) {
        return new Response<>(ResponseCode.SUCCESS, machineInfoService.getOne(id));
    }

    /**
     * 获取某个用户拥有的设备列表信息
     *
     * @param userId 用户id
     * @return 设备列表信息
     */
    @RequestMapping(value = {"/list/{userId}"}, method = RequestMethod.GET)
    public Response<List<MachineInfo>> listByUserId(@PathVariable Long userId) {
        return new Response<>(ResponseCode.SUCCESS, machineInfoService.queryListByUserId(userId));
    }

    /**
     * 新增设备信息
     *
     * @param machineInfo 设备信息
     * @return 是否新增成功消息
     */
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public Response<Object> add(MachineInfo machineInfo) {
        machineInfoService.add(machineInfo);
        return new Response<>();
    }

    /**
     * 修改设备信息
     *
     * @param machineInfo 设备信息
     * @return 是否新增成功消息
     */
    @RequestMapping(value = {"/update"}, method = RequestMethod.POST)
    public Response<Object> update(MachineInfo machineInfo) {
        machineInfoService.update(machineInfo);
        return new Response<>();
    }

    /**
     * 删除单个设备信息
     *
     * @param id 设备id
     * @return 是否删除成功消息
     */
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    public Response<Object> delete(@PathVariable Long id) {
        machineInfoService.delete(id);
        return new Response<>();
    }

    /**
     * 当设备连接服务时，新增设备信息
     *
     * @param machineNo 设备编号
     * @return 连接后是否新增成功
     */
    @RequestMapping(value = {"/{machineNo}/connect"}, method = RequestMethod.GET)
    public Response<Object> connect(@PathVariable String machineNo) {
        MachineInfo machineInfo = new MachineInfo();
        machineInfo.setMachineNo(machineNo);
        machineInfoService.add(machineInfo);
        return new Response<>();
    }

    /**
     * 当设备与服务器断开连接时，删除指定编号的设备信息
     *
     * @param machineNo 设备编号
     * @return 断开连接时是否成功删除
     */
    @RequestMapping(value = {"/{machineNo}/disConnect"}, method = RequestMethod.GET)
    public Response<Object> disConnect(@PathVariable String machineNo) {
        machineInfoService.delete(machineNo);
        return new Response<>();
    }
}
