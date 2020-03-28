package cn.ghaien.machinedemo.dao;

import cn.ghaien.machinedemo.entity.pojo.UserMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author guo.haien
 * @Date 2018/7/5
 */
@Repository
public interface UserMachineRepository extends JpaRepository<UserMachine, Long> {

    List<UserMachine> findByUserInfoId(Long userId);

    UserMachine findByUserInfoIdAndMachineInfoId(Long userId, Long machineId);
}
