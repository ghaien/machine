package cn.ghaien.machinedemo.service;

import cn.ghaien.machinedemo.entity.input.OperateInput;

/**
 * mq业务层接口
 *
 * @author guo.haien
 * @date 2019/5/31 22:44
 */
public interface MqttService {

    /**
     * 将发送的数据进行加密后发送到mq
     *
     * @param operateInput
     */
    void publishForDevId(OperateInput operateInput);
}
