package cn.ghaien.machinedemo.service.impl;

import cn.ghaien.machinedemo.dao.MachineInfoRepository;
import cn.ghaien.machinedemo.dao.UserMachineRepository;
import cn.ghaien.machinedemo.entity.input.MachineInfoQuery;
import cn.ghaien.machinedemo.entity.pojo.MachineInfo;
import cn.ghaien.machinedemo.entity.pojo.UserMachine;
import cn.ghaien.machinedemo.service.MachineInfoService;
import cn.ghaien.machinedemo.util.commons.PageBean;
import cn.ghaien.machinedemo.util.consts.DeleteStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 设备信息 service实现
 *
 * @author guo.haien
 * @ate 2018/7/9
 */
@Service
public class MachineInfoServiceImpl implements MachineInfoService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MachineInfoRepository machineInfoRepository;

    @Autowired
    private UserMachineRepository userMachineRepository;

    @Override
    public PageBean<MachineInfo> queryList(MachineInfoQuery query) {
        // 分页
        Pageable pageable = new PageRequest(query.getPageNo(), query.getPageSize());
        // 查询条件设置
        Specification<MachineInfo> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> ps = new ArrayList<>();
            ps.add(criteriaBuilder.equal(root.get("isDelete").as(String.class), DeleteStatus.NORMAL));
            if (!StringUtils.isEmpty(query.getMachineUseNo())) {
                ps.add(criteriaBuilder.equal(root.get("machineUseNo").as(String.class), query.getMachineUseNo()));
            }
            Predicate[] pa = new Predicate[ps.size()];
            criteriaQuery.where(criteriaBuilder.and(ps.toArray(pa)));
            return criteriaQuery.getRestriction();
        };
        Page<MachineInfo> pageInfo = machineInfoRepository.findAll(specification, pageable);
        return new PageBean<>(pageInfo.getContent(), pageInfo.getTotalElements());
    }

    @Override
    public MachineInfo getOne(Long id) {
        return machineInfoRepository.getOne(id);
    }

    @Override
    public List<MachineInfo> queryListByUserId(Long userId) {
        // 先通过用户id查询设备和用户的中间表
        List<UserMachine> userMachineList = userMachineRepository.findByUserInfoId(userId);
        logger.info("ID为{" + userId + "}拥有的设备数：" + userMachineList.size());
        // 取出中间表中的设备id来获取设备信息
        Long[] machineIds = new Long[userMachineList.size()];
        for (int i = 0; i < userMachineList.size(); i++) {
            machineIds[i] = userMachineList.get(i).getMachineInfoId();
        }
        return machineInfoRepository.findByIdIn(machineIds);
    }

    @Override
    public void add(MachineInfo machineInfo) {
        MachineInfo oldMachine = machineInfoRepository.findByMachineNo(machineInfo.getMachineNo());
        if (oldMachine != null) {
            oldMachine.setIsDelete(DeleteStatus.NORMAL);
            oldMachine.setUpdateTime(LocalDateTime.now());
            machineInfo = oldMachine;
        } else {
            machineInfo.setIsDelete(DeleteStatus.NORMAL);
            machineInfo.setCreateTime(LocalDateTime.now());
        }
        machineInfoRepository.save(machineInfo);
    }

    @Override
    public void update(MachineInfo machineInfo) {
        machineInfo.setUpdateTime(LocalDateTime.now());
        machineInfoRepository.save(machineInfo);
    }

    @Override
    public void delete(Long id) {
        MachineInfo machineInfo = machineInfoRepository.getOne(id);
        if (machineInfo == null) {
            logger.info("id为{}的设备不存在", id);
            return;
        }
        // 逻辑删除
        machineInfo.setIsDelete(DeleteStatus.DELETED);
        machineInfo.setUpdateTime(LocalDateTime.now());
        machineInfoRepository.save(machineInfo);
    }

    @Override
    public void delete(String machineNo) {
        MachineInfo machineInfo = machineInfoRepository.findByMachineNoAndIsDelete(machineNo, DeleteStatus.NORMAL);
        if (machineInfo == null) {
            logger.info("编号为{}的设备不存在", machineNo);
            return;
        }
        // 逻辑删除
        machineInfo.setIsDelete(DeleteStatus.DELETED);
        machineInfo.setUpdateTime(LocalDateTime.now());
        machineInfoRepository.save(machineInfo);
    }
}
