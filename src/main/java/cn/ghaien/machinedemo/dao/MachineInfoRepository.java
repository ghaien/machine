package cn.ghaien.machinedemo.dao;

import cn.ghaien.machinedemo.entity.pojo.MachineInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author guo.haien
 * @Date 2018/7/5
 */
@Repository
public interface MachineInfoRepository extends JpaRepository<MachineInfo, Long>, JpaSpecificationExecutor<MachineInfo> {

    List<MachineInfo> findByIdIn(Long[] ids);

    List<MachineInfo> findByIsDelete(String isDelete);

    MachineInfo findByMachineNoAndIsDelete(String machine, String isDelete);

    MachineInfo findByMachineNo(String machine);
}
