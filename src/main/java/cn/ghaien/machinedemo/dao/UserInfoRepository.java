package cn.ghaien.machinedemo.dao;

import cn.ghaien.machinedemo.entity.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author guo.haien
 * @Date 2018/7/5
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    List<UserInfo> findByIsDelete(String isDelete);

    UserInfo getByAccountAndIsDelete(String account, String isDelete);
}
