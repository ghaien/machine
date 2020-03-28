package cn.ghaien.machinedemo.util.exceptions;

/**
 * @author guo.haien
 * @Date 2018/7/3
 */
public class ConnectException extends RuntimeException {

    public ConnectException() {
    }

    public ConnectException(String message) {
        super(message);
    }
}
