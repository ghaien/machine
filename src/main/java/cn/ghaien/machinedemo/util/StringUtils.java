package cn.ghaien.machinedemo.util;

import java.util.Arrays;

/**
 * Created by rt on 2018/9/16.
 */
public class StringUtils {

    public static String bytesToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String str = Integer.toHexString(b);
            if (str.length() == 1) {
                sb.append("0" + str);
            } else if (str.length() == 2) {
                sb.append(str);
            } else if (str.length() > 2) {
                sb.append(str.substring(str.length() - 2));
            }
        }
        return sb.toString();
    }

    public static byte[] stringToBytes(String s) {
        byte[] bytes = new byte[s.length() / 2];
        for (int i = 0, j = 0; i < s.length(); i += 2, j++) {
            bytes[j] = (byte) ((int) Integer.valueOf(s.substring(i, i + 2), 16));
        }
        return bytes;
    }

    public static void main(String[] args) {
        byte[] bytes = {-1, 123, 1};
        System.out.println(bytesToString(bytes));

        bytes = stringToBytes("011000100001000c");
        System.out.println(Arrays.toString(bytes));

        System.out.println((byte) ((int) Integer.valueOf("10", 16)));
    }
}
