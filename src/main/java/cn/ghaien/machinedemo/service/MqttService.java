package cn.ghaien.machinedemo.service;

import cn.ghaien.machinedemo.entity.input.OperateInput;

/**
 * @author guo.haien
 * @date 2019/5/31 22:44
 */
public interface MqttService {

    void publishForDevId(OperateInput operateInput);
}
