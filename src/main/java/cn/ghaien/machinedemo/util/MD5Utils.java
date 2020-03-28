package cn.ghaien.machinedemo.util;

import org.apache.tomcat.util.buf.HexUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author guo.haien
 * @Date 2018/7/6
 */
public class MD5Utils {

    private static final String SALT = "sayuan";

    public static String getHex(String s) {
        s = SALT + s;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] md5Bytes = md.digest(s.getBytes());
        //转化为16进制字符串输出
        return HexUtils.toHexString(md5Bytes);
    }
}
