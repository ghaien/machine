package cn.ghaien.machinedemo.util.commons;

/**
 * @author guo.haien
 * @Date 2018/7/3
 */
public interface ReturnCode {

    Integer SUCCESS = 0x00;
    Integer DISCONNECT = 0x01;
    Integer SERVER_CONNECT = 0x02;
    Integer SERVER_DISCONNECT = 0x03;
    Integer SUBSCRIBE_SUCCESS = 0x00;
    Integer SUBSCRIBE_FAIL = 0x01;
    Integer DISSUBSCRIBE = 0x02;
    Integer SEND_SUCCESS = 0x00;
    Integer SEND_FAIL = 0x01;
    Integer SEND_NORETURN = 0x02;
}
