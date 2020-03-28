package cn.ghaien.machinedemo.service.impl;

import cn.ghaien.machinedemo.dao.UserInfoRepository;
import cn.ghaien.machinedemo.entity.pojo.UserInfo;
import cn.ghaien.machinedemo.service.UserInfoService;
import cn.ghaien.machinedemo.util.MD5Utils;
import cn.ghaien.machinedemo.util.commons.Response;
import cn.ghaien.machinedemo.util.commons.ResponseCode;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author guo.haien
 * @Date 2018/7/6
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public List<UserInfo> queryList() {
        return userInfoRepository.findByIsDelete("1");
    }

    @Override
    public UserInfo get(Long id) {
        return userInfoRepository.findOne(id);
    }

    @Override
    public void addUser(UserInfo userInfo) {
        userInfo.setIsDelete("1");
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setPassword(MD5Utils.getHex(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
    }

    @Override
    public void delete(Long id) {
        UserInfo userInfo = userInfoRepository.findOne(id);
        userInfo.setIsDelete("0");
        userInfo.setUpdateTime(LocalDateTime.now());
        userInfoRepository.save(userInfo);
    }

    @Override
    public Response<Object> login(String account, String password) {
        UserInfo userInfo = userInfoRepository.getByAccountAndIsDelete(account, "1");
        if (userInfo == null) {
            return new Response<>(ResponseCode.ACCOUNT_PASSWORD_ERROR);
        }
        if (!userInfo.getPassword().equals(MD5Utils.getHex(password))) {
            return new Response<>(ResponseCode.ACCOUNT_PASSWORD_ERROR);
        }
        return new Response<>();
    }
}
