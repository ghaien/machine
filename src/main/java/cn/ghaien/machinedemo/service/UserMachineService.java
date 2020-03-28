package cn.ghaien.machinedemo.service;

import cn.ghaien.machinedemo.entity.pojo.UserMachine;

/**
 * @author guo.haien
 * @Date 2018/7/9
 */
public interface UserMachineService {

    void add(UserMachine userMachine);

    void delete(UserMachine userMachine);
}
