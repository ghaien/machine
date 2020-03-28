package cn.ghaien.machinedemo.service.impl;

import cn.ghaien.machinedemo.dao.ArmInfoRepository;
import cn.ghaien.machinedemo.entity.input.ArmInfoQuery;
import cn.ghaien.machinedemo.entity.pojo.ArmInfo;
import cn.ghaien.machinedemo.service.ArmInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guo.haien
 * @Date 2018/7/12
 */
@Service
public class ArmInfoServiceImpl implements ArmInfoService {

    @Autowired
    private ArmInfoRepository armInfoRepository;

    @Override
    public void save(ArmInfo armInfo) {
        armInfo.setCreateTime(LocalDateTime.now());
        armInfoRepository.save(armInfo);
    }

    @Override
    public void addByData(String machineNo, String data) {
        ArmInfo armInfo = new ArmInfo();
        armInfo.setMachineNo(machineNo);
        armInfo.setZoneNo(Integer.valueOf(data.substring(0, 2), 16));
        armInfo.setArmType(Integer.valueOf(data.substring(8, 10)));
        armInfo.setCreateTime(LocalDateTime.now());
        if (armInfo.getArmType().equals(0)) {
            return;
        }
        armInfoRepository.save(armInfo);
    }

    @Override
    public List<ArmInfo> queryList(ArmInfoQuery armInfoQuery) {
        Pageable page = new PageRequest(armInfoQuery.getPage(), armInfoQuery.getPageSize(), new Sort(Sort.Direction.DESC, "createTime"));
        Page<ArmInfo> armInfoPage = armInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (armInfoQuery.getCreateTime() != null) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"), armInfoQuery.getCreateTime()));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        }, page);
        return armInfoPage.getContent();
    }


}
