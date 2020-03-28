package cn.ghaien.machinedemo.util.commons;

/**
 * @author guo.haien
 * @Date 2018/7/3
 */
public enum ResponseCode {
    SUCCESS("0", "请求成功"),
    FAILURE("1000", "请求失败"),
    ACCOUNT_PASSWORD_ERROR("1101", "账号或密码错误"),
    CONNECT_TIMEOUT("2001", "连接超时"),
    CONNECT_FAIL("2002", "连接失败"),
    SUBSCRIBE_TIMEOUT("3001", "订阅超时"),
    SUBSCRIBE_FAIL("3002", "订阅失败"),
    DIS_SUBSCRIBE_TIMEOUT("3003", "订阅超时"),
    DIS_SUBSCRIBE_FAIL("3004", "取消订阅失败"),
    RESPONSE_ERROR("4001", "响应消息错误"),
    SEND_FAIL("4002", "发送命令失败"),
    RESPONSE_TIMEOUT("4003", "响应超时");

    private String code;

    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
