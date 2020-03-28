package cn.ghaien.machinedemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guo.haien
 * @Date 2018/7/10
 */
public class Log {

    private static Level LOGGER_LEVEL = Level.WARN;

    public static void info(String message) {
        if (checkLevel(Level.INFO)) {
            getLog().info(message);
        }
    }

    private static Logger getLog() {
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        Class cla = null;
        try {
            cla = Class.forName(stes[3].getClassName());
            return LoggerFactory.getLogger(cla);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean checkLevel(Level level) {
        return level.toInt() < LOGGER_LEVEL.toInt();
    }

    public static void main(String[] args) {
        Integer i = 2018;

        System.out.println(Integer.toUnsignedString(2018/256, 16));
        System.out.println(Integer.toUnsignedString(2018%256, 16));
        System.out.println(Arrays.toString("".split(" ")));
    }
}
