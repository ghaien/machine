package cn.ghaien.machinedemo.util.commons;

import cn.ghaien.machinedemo.usrCloud.ClientAdapter;
import cn.ghaien.machinedemo.usrCloud.ClientCallbackAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guo.haien
 * @Date 2018/7/3
 */
public class UserConnect {

    private String userName;

    private ClientAdapter clientAdapter;

    private ClientCallbackAdapter clientCallbackAdapter;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ClientAdapter getClientAdapter() {
        return clientAdapter;
    }

    public void setClientAdapter(ClientAdapter clientAdapter) {
        this.clientAdapter = clientAdapter;
    }

    public ClientCallbackAdapter getClientCallbackAdapter() {
        return clientCallbackAdapter;
    }

    public void setClientCallbackAdapter(ClientCallbackAdapter clientCallbackAdapter) {
        this.clientCallbackAdapter = clientCallbackAdapter;
    }
}
