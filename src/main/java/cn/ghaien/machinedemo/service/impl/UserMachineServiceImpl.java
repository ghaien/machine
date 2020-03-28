package cn.ghaien.machinedemo.service.impl;

import cn.ghaien.machinedemo.dao.UserMachineRepository;
import cn.ghaien.machinedemo.entity.pojo.UserMachine;
import cn.ghaien.machinedemo.service.UserMachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guo.haien
 * @Date 2018/7/9
 */
@Service
public class UserMachineServiceImpl implements UserMachineService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMachineRepository userMachineRepository;

    @Override
    public void add(UserMachine userMachine) {
        userMachineRepository.save(userMachine);
    }

    @Override
    public void delete(UserMachine userMachine) {
        userMachine = userMachineRepository.findByUserInfoIdAndMachineInfoId(
                userMachine.getUserInfoId(), userMachine.getMachineInfoId());
        userMachineRepository.delete(userMachine);
    }
}
