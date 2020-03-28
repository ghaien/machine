package cn.ghaien.machinedemo.service.impl;

import cn.ghaien.machinedemo.entity.pojo.ArmInfo;
import cn.ghaien.machinedemo.service.ArmInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by rt on 2018/7/14.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ArmInfoServiceImplTest {

    @Autowired
    private ArmInfoService armInfoService;

    @Test
    public void save() throws Exception {
        ArmInfo armInfo = new ArmInfo();
        armInfo.setArmType(80);
        armInfo.setMachineNo("356566078066602");
        armInfo.setZoneNo(2);
        armInfoService.save(armInfo);
    }

}