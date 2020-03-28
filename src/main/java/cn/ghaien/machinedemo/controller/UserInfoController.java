package cn.ghaien.machinedemo.controller;

import cn.ghaien.machinedemo.entity.pojo.UserInfo;
import cn.ghaien.machinedemo.service.UserInfoService;
import cn.ghaien.machinedemo.util.commons.Response;
import cn.ghaien.machinedemo.util.commons.ResponseCode;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author guo.haien
 * @Date 2018/7/6
 */
@RestController
@RequestMapping(value = {"userInfo"})
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = {"list"}, method = RequestMethod.GET)
    public Response<List<UserInfo>> list() {
        return new Response<>(ResponseCode.SUCCESS, userInfoService.queryList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Response<UserInfo> get(@PathVariable Long id) {
        return new Response<>(ResponseCode.SUCCESS, userInfoService.get(id));
    }

    @RequestMapping(value = {"add"}, method = RequestMethod.POST)
    public Response<Object> add(UserInfo userInfo) {
        userInfoService.addUser(userInfo);
        return new Response<>();
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    public Response<Object> delete(@PathVariable Long id) {
        userInfoService.delete(id);
        return new Response<>();
    }
}
