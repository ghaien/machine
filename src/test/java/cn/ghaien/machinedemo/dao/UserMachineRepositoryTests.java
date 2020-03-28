package cn.ghaien.machinedemo.dao;

import cn.ghaien.machinedemo.entity.pojo.UserMachine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author guo.haien
 * @Date 2018/7/9
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMachineRepositoryTests {

    @Autowired
    private UserMachineRepository userMachineRepository;

    @Test
    public void save() {
        UserMachine userMachine = new UserMachine();
        userMachine.setUserInfoId(1L);
        userMachine.setMachineInfoId(1L);
        userMachineRepository.save(userMachine);
    }

    @Test
    public void delete() {
        UserMachine userMachine = new UserMachine();
        userMachine.setUserInfoId(1L);
        userMachine.setMachineInfoId(1L);
        userMachine = userMachineRepository.findByUserInfoIdAndMachineInfoId(
                userMachine.getUserInfoId(), userMachine.getMachineInfoId());
        userMachineRepository.delete(userMachine.getId());
    }
}
