package cn.ghaien.machinedemo.service;

import cn.ghaien.machinedemo.entity.pojo.UserInfo;
import cn.ghaien.machinedemo.util.commons.Response;

import java.util.List;

/**
 * @author guo.haien
 * @Date 2018/7/6
 */
public interface UserInfoService {

    List<UserInfo> queryList();

    UserInfo get(Long id);

    void addUser(UserInfo userInfo);

    void delete(Long id);

    Response<Object> login(String account, String password);
}
