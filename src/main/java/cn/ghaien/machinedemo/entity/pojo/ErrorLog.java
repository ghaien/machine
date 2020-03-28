package cn.ghaien.machinedemo.entity.pojo;

import cn.ghaien.machinedemo.util.commons.ResponseCode;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author ghaien
 * @date 2020-03-28 16:03
 */
@Entity
@Table(name = "tbl_error_log")
public class ErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;

    private String code;

    private String message;

    @Lob
    @Column(columnDefinition = "text")
    private String data;

    @Lob
    @Column(columnDefinition = "text")
    private String detailMessage;

    private LocalDateTime createTime;

    public ErrorLog() {
    }

    public ErrorLog(ResponseCode responseCode, Exception e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        this.url = request.getRequestURL().toString();
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = e.getMessage();
        StringBuilder detailMessage = new StringBuilder(e.toString());
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            detailMessage.append("\n\tat ").append(stackTraceElement);
        }
        this.detailMessage = detailMessage.toString();
        this.createTime = LocalDateTime.now();
    }

    public ErrorLog(ResponseCode responseCode, String data, Exception e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        this.url = request.getRequestURL().toString();
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
        StringBuilder detailMessage = new StringBuilder(e.toString());
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            detailMessage.append("\n\tat ").append(stackTraceElement);
        }
        this.detailMessage = detailMessage.toString();
        this.createTime = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
