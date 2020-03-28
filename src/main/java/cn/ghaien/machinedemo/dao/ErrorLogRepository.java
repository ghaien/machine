package cn.ghaien.machinedemo.dao;

import cn.ghaien.machinedemo.entity.pojo.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ghaien
 * @date 2020-03-28 16:12
 */
public interface ErrorLogRepository extends JpaRepository<ErrorLog, Integer> {
}
