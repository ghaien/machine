package cn.ghaien.machinedemo.remote;

import cn.ghaien.machinedemo.util.commons.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用设备相关服务
 *
 * @author guo.haien
 * @date 2018/10/13 22:30
 */
@FeignClient(name = "spring-cloud-eureka-machine-communicate")
public interface MachineRemote {

    /**
     * 向设备发送操作指令
     *
     * @param devNo 设备编号
     * @param data 操作指令
     * @return 响应消息
     */
    @RequestMapping(value = {"/machine/operate"}, method = RequestMethod.GET)
    Response<String> operate(@RequestParam(value = "devNo") String devNo,@RequestParam(value = "data") String data);
}
