package cn.ghaien.machinedemo.config;

import cn.ghaien.machinedemo.dao.ErrorLogRepository;
import cn.ghaien.machinedemo.entity.pojo.ErrorLog;
import cn.ghaien.machinedemo.util.commons.Response;
import cn.ghaien.machinedemo.util.commons.ResponseCode;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * controller异常处理
 *
 * @author ghaien
 * @date 2020-03-28 15:46
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @Autowired
    private ErrorLogRepository errorLogRepository;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response<String> handleException(Exception e) {
        errorLogRepository.save(new ErrorLog(ResponseCode.FAILURE, e));
        return new Response<>(ResponseCode.FAILURE, e.getMessage());
    }
}
