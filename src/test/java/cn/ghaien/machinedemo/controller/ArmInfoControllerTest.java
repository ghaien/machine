package cn.ghaien.machinedemo.controller;

import cn.ghaien.machinedemo.entity.input.ArmInfoQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * @author guo.haien
 * @Date 2018/7/12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ArmInfoControllerTest {

    @Autowired
    private ArmInfoController armInfoController;

    @Test
    public void list() {
        ArmInfoQuery armInfoQuery = new ArmInfoQuery();
        armInfoQuery.setCreateTime(LocalDateTime.now());
        armInfoQuery.setPage(1);
        armInfoQuery.setPageSize(10);
        System.out.println(armInfoController.list(armInfoQuery).getData());
    }
}