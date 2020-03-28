package cn.ghaien.machinedemo.controller;

import cn.ghaien.machinedemo.entity.input.ArmInfoQuery;
import cn.ghaien.machinedemo.entity.pojo.ArmInfo;
import cn.ghaien.machinedemo.service.ArmInfoService;
import cn.ghaien.machinedemo.util.commons.Response;
import cn.ghaien.machinedemo.util.commons.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author guo.haien
 * @Date 2018/7/12
 */
@RestController
@RequestMapping(value = {"/armInfo"})
public class ArmInfoController {

    @Autowired
    private ArmInfoService armInfoService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public Response<List<ArmInfo>> list(ArmInfoQuery armInfoQuery) {
        return new Response<>(ResponseCode.SUCCESS, armInfoService.queryList(armInfoQuery));
    }

    @RequestMapping(value = {"/add/{machineNo}/{data}"}, method = RequestMethod.GET)
    public Response<LocalDateTime> addByData(@PathVariable String machineNo, @PathVariable(value = "data") String data) {
        armInfoService.addByData(machineNo, data);
        return new Response<>(ResponseCode.SUCCESS, null);
    }

    @RequestMapping(value = {"/nowTime"}, method = RequestMethod.GET)
    public Response<LocalDateTime> nowTime() {
        return new Response<>(ResponseCode.SUCCESS, LocalDateTime.now());
    }
}
