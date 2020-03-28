package cn.ghaien.machinedemo.controller;

import cn.ghaien.machinedemo.entity.pojo.UserMachine;
import cn.ghaien.machinedemo.service.UserMachineService;
import cn.ghaien.machinedemo.util.commons.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guo.haien
 * @Date 2018/7/9
 */
@RestController
@RequestMapping(value = {"userMachine"})
public class UserMachineController {

    @Autowired
    private UserMachineService userMachineService;

    @RequestMapping(method = RequestMethod.POST)
    public Response<Object> add(UserMachine userMachine) {
        userMachineService.add(userMachine);
        return new Response<>();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Response<Object> delete(UserMachine userMachine) {
        userMachineService.delete(userMachine);
        return new Response<>();
    }
}
