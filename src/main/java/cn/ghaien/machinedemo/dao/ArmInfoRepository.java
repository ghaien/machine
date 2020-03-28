package cn.ghaien.machinedemo.dao;

import cn.ghaien.machinedemo.entity.pojo.ArmInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author guo.haien
 * @Date 2018/7/12
 */
@Repository
public interface ArmInfoRepository extends JpaRepository<ArmInfo, Long>, JpaSpecificationExecutor<ArmInfo> {
}
