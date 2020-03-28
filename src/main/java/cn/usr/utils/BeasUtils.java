package cn.usr.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Properties;


public class BeasUtils {
    public BeasUtils() {
    }

    public static Properties getPropertie(Class<?> obj, String propName) {
        Properties prop = new Properties();
        try {
            prop.load(new InputStreamReader(obj.getClassLoader().getResourceAsStream(propName), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            return prop;
        }
        return prop;
    }


    public static final String getMD5(String pwd) {
        char[] md5String = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] btInput = pwd.getBytes();


            MessageDigest mdInst = MessageDigest.getInstance("MD5");


            mdInst.update(btInput);


            byte[] md = mdInst.digest();


            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[(k++)] = md5String[(byte0 >>> 4 & 0xF)];
                str[(k++)] = md5String[(byte0 & 0xF)];
            }


            return new String(str);
        } catch (Exception e) {
        }
        return null;
    }
}
