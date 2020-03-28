package cn.ghaien.machinedemo.controller;

import cn.ghaien.machinedemo.service.UserInfoService;
import cn.ghaien.machinedemo.util.commons.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guo.haien
 * @Date 2018/7/9
 */
@RestController
@RequestMapping(value = {"login"})
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/{account}/{password}", method = RequestMethod.GET)
    public Response<Object> login(@PathVariable String account, @PathVariable String password) {
        return userInfoService.login(account, password);
    }

}
