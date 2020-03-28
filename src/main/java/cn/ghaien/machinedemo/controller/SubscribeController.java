package cn.ghaien.machinedemo.controller;

import cn.ghaien.machinedemo.usrCloud.ClientCallbackAdapter;
import cn.ghaien.machinedemo.usrCloud.UsrCloudClient;
import cn.ghaien.machinedemo.util.commons.Response;
import cn.ghaien.machinedemo.util.commons.ResponseCode;
import cn.ghaien.machinedemo.util.commons.ReturnCode;
import cn.ghaien.machinedemo.util.commons.UserConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订阅
 *
 * @author guo.haien
 * @Date 2018/7/4
 */
@RestController
@RequestMapping(value = {"subscribe"})
public class SubscribeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsrCloudClient usrCloudClient;

    /**
     * 指定用户订阅指定设备
     *
     * @param userName 用户名称
     * @param devId    设备编号
     * @return 返回值
     */
    @RequestMapping(value = {"/{userName}/{devId}"}, method = RequestMethod.GET)
    public Response<Object> subscribe(@PathVariable String userName, @PathVariable String devId) {
        try {
            usrCloudClient.connect(userName);
            return usrCloudClient.SubscribeForDevId(userName, devId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Response<>(ResponseCode.FAILURE);
        }
    }

    /**
     * 指定用户取消订阅指定设备
     *
     * @param userName 用户名称
     * @param devId    设备编号
     * @return 返回值
     */
    @RequestMapping(value = {"/cancel/{userName}/{devId}"}, method = RequestMethod.GET)
    public Response<Object> disSubscribe(@PathVariable String userName, @PathVariable String devId) {
        try {
            return usrCloudClient.DisSubscribeforDevId(userName, devId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Response<>(ResponseCode.FAILURE);
        }
    }
}
