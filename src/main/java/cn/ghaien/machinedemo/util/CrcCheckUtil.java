package cn.ghaien.machinedemo.util;

import cn.ghaien.machinedemo.entity.input.OperateInput;
import cn.ghaien.machinedemo.usrCloud.ClientAdapter;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author guo.haien
 * @Date 2018/7/2
 */
public class CrcCheckUtil {

    private static int[] ccittTable = {
            0x0000, 0xC0C1, 0xC181, 0x0140, 0xC301, 0x03C0, 0x0280,
            0xC241, 0xC601, 0x06C0, 0x0780, 0xC741, 0x0500, 0xC5C1, 0xC481,
            0x0440, 0xCC01, 0x0CC0, 0x0D80, 0xCD41, 0x0F00, 0xCFC1, 0xCE81,
            0x0E40, 0x0A00, 0xCAC1, 0xCB81, 0x0B40, 0xC901, 0x09C0, 0x0880,
            0xC841, 0xD801, 0x18C0, 0x1980, 0xD941, 0x1B00, 0xDBC1, 0xDA81,
            0x1A40, 0x1E00, 0xDEC1, 0xDF81, 0x1F40, 0xDD01, 0x1DC0, 0x1C80,
            0xDC41, 0x1400, 0xD4C1, 0xD581, 0x1540, 0xD701, 0x17C0, 0x1680,
            0xD641, 0xD201, 0x12C0, 0x1380, 0xD341, 0x1100, 0xD1C1, 0xD081,
            0x1040, 0xF001, 0x30C0, 0x3180, 0xF141, 0x3300, 0xF3C1, 0xF281,
            0x3240, 0x3600, 0xF6C1, 0xF781, 0x3740, 0xF501, 0x35C0, 0x3480,
            0xF441, 0x3C00, 0xFCC1, 0xFD81, 0x3D40, 0xFF01, 0x3FC0, 0x3E80,
            0xFE41, 0xFA01, 0x3AC0, 0x3B80, 0xFB41, 0x3900, 0xF9C1, 0xF881,
            0x3840, 0x2800, 0xE8C1, 0xE981, 0x2940, 0xEB01, 0x2BC0, 0x2A80,
            0xEA41, 0xEE01, 0x2EC0, 0x2F80, 0xEF41, 0x2D00, 0xEDC1, 0xEC81,
            0x2C40, 0xE401, 0x24C0, 0x2580, 0xE541, 0x2700, 0xE7C1, 0xE681,
            0x2640, 0x2200, 0xE2C1, 0xE381, 0x2340, 0xE101, 0x21C0, 0x2080,
            0xE041, 0xA001, 0x60C0, 0x6180, 0xA141, 0x6300, 0xA3C1, 0xA281,
            0x6240, 0x6600, 0xA6C1, 0xA781, 0x6740, 0xA501, 0x65C0, 0x6480,
            0xA441, 0x6C00, 0xACC1, 0xAD81, 0x6D40, 0xAF01, 0x6FC0, 0x6E80,
            0xAE41, 0xAA01, 0x6AC0, 0x6B80, 0xAB41, 0x6900, 0xA9C1, 0xA881,
            0x6840, 0x7800, 0xB8C1, 0xB981, 0x7940, 0xBB01, 0x7BC0, 0x7A80,
            0xBA41, 0xBE01, 0x7EC0, 0x7F80, 0xBF41, 0x7D00, 0xBDC1, 0xBC81,
            0x7C40, 0xB401, 0x74C0, 0x7580, 0xB541, 0x7700, 0xB7C1, 0xB681,
            0x7640, 0x7200, 0xB2C1, 0xB381, 0x7340, 0xB101, 0x71C0, 0x7080,
            0xB041, 0x5000, 0x90C1, 0x9181, 0x5140, 0x9301, 0x53C0, 0x5280,
            0x9241, 0x9601, 0x56C0, 0x5780, 0x9741, 0x5500, 0x95C1, 0x9481,
            0x5440, 0x9C01, 0x5CC0, 0x5D80, 0x9D41, 0x5F00, 0x9FC1, 0x9E81,
            0x5E40, 0x5A00, 0x9AC1, 0x9B81, 0x5B40, 0x9901, 0x59C0, 0x5880,
            0x9841, 0x8801, 0x48C0, 0x4980, 0x8941, 0x4B00, 0x8BC1, 0x8A81,
            0x4A40, 0x4E00, 0x8EC1, 0x8F81, 0x4F40, 0x8D01, 0x4DC0, 0x4C80,
            0x8C41, 0x4400, 0x84C1, 0x8581, 0x4540, 0x8701, 0x47C0, 0x4680,
            0x8641, 0x8201, 0x42C0, 0x4380, 0x8341, 0x4100, 0x81C1, 0x8081,
            0x4040
    };

    private static int Crc16(byte[] q, int len) {
        int crc = 0xffff;
        for (int i = 0; i < len; i++) {
            crc = ccittTable[(crc ^ q[i]) & 0xff] ^ (crc >> 8);
        }
        return crc;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        OperateInput operateInput = new OperateInput();
        operateInput.setZoneNum(0x01);
        operateInput.setOperateType(3);
//        byte[] bytes = getData(operateInput);
//        String hexString = "0123456789abcdef";
//        // 根据默认编码获取字节数组
//        StringBuilder sb = new StringBuilder(bytes.length * 2);
//        // 将字节数组中每个字节拆解成2位16进制整数
//        for (int i = 0; i < bytes.length; i++) {
//            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
//            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
//        }
//        System.out.println(sb.toString());

        byte[] bytes = getReturnData(operateInput.getZoneNum(), operateInput.getOperateType());
        String hexString = "0123456789abcdef";
        // 根据默认编码获取字节数组
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        System.out.println(sb.toString());

    }

    public static byte[] getData(OperateInput operateInput) {
        int zoneNum = operateInput.getZoneNum();
        Integer operateType = operateInput.getOperateType();

        int length = 9 + (REGISTER_NUMBER[operateType] * 2);
        byte[] data = new byte[length];
        int index = 0;
        data[index++] = (byte) zoneNum;
        data[index++] = (byte) 0x10;
        data[index++] = (byte) 0x00;
        data[index++] = (byte) REGISTER_ADDRESS[operateType];
        data[index++] = (byte) 0x00;
        data[index++] = (byte) REGISTER_NUMBER[operateType];
        data[index++] = (byte) (REGISTER_NUMBER[operateType] * 2);
        /* 数据中不用设置值 */
        String[] datas = OPERATE_DATA[operateType].isEmpty() ? new String[] {} : OPERATE_DATA[operateType].split(" ");
        for (String d : datas) {
            data[index++] = Byte.valueOf(d, 16);
        }
        /* 设置秒数 */
        if (operateInput.getSecondNum() != null) {
            data[index++] = (byte) (operateInput.getSecondNum() / 256);
            data[index++] = (byte) (operateInput.getSecondNum() % 256);
        }
        /* 设置压值 */
        if (operateInput.getPressureValue() != null) {
            data[index++] = (byte) (operateInput.getPressureValue() * 10 / 256);
            data[index++] = (byte) (operateInput.getPressureValue() * 10 % 256);
        }
        /* 设置灵敏度 */
        if (operateInput.getSensitivity() != null) {
            data[index++] = (byte) 0;
            data[index++] = operateInput.getSensitivity().byteValue();
        }
        /* 设置系统时间 */
        if (operateInput.getOperateTime() != null) {
            LocalDateTime ot = operateInput.getOperateTime();
            data[index++] = (byte) (ot.getYear() / 256);
            data[index++] = (byte) (ot.getYear() % 256);
            data[index++] = (byte) ot.getMonthValue();
            data[index++] = (byte) ot.getDayOfMonth();
            data[index++] = (byte) ot.getHour();
            data[index++] = (byte) ot.getMinute();
            data[index++] = (byte) ot.getSecond();
            data[index++] = (byte) 0;
        }
        /* 设置布防类型 */
        if (operateInput.getArmType() != null) {
            data[index++] = operateInput.getArmType().byteValue();
        }
        /* 设置撤防时间 */
        if (operateInput.getStartArm() != null) {
            data[index++] = (byte) operateInput.getStartArm().getHour();
            data[index++] = (byte) operateInput.getStartArm().getMinute();
        }
        /* 设置布防时间 */
        if (operateInput.getEndArm() != null) {
            data[index++] = (byte) operateInput.getEndArm().getHour();
            data[index++] = (byte) operateInput.getEndArm().getMinute();
        }
        /* 设置通信断线检测或联动极使能状态*/
        if (operateInput.getDevStatus() != null) {
            data[index++] = operateInput.getDevStatus().byteValue();
        }

        int crc16 = Crc16(data, index);
        data[index++] = (byte) (crc16 & 0xff);
        data[index++] = (byte) (crc16 >> 8);
        return data;
    }

    private static byte[] getReturnData(int zoneNum, Integer operateType) {
        int length = 8;
        byte[] data = new byte[length];
        int index = 0;
        data[index++] = (byte) zoneNum;
        data[index++] = (byte) 0x10;
        data[index++] = (byte) 0x00;
        data[index++] = (byte) REGISTER_ADDRESS[operateType];
        data[index++] = (byte) 0x00;
        data[index++] = (byte) REGISTER_NUMBER[operateType];
        int crc16 = Crc16(data, index);
        data[index++] = (byte) (crc16 & 0xff);
        data[index++] = (byte) (crc16 >> 8);
        return data;
    }

    public static boolean checkReturnData(byte[] returnData, OperateInput operateInput) {
        byte[] trueData = getReturnData(operateInput.getZoneNum(), operateInput.getOperateType());
        System.out.println("应响应数据={}" + Arrays.toString(trueData));
        System.out.println("实际响应数据={}" + Arrays.toString(returnData));
        if (trueData.length != returnData.length) {
            return false;
        }
        for (int i = 0; i < trueData.length; i++) {
            if (returnData[i] != trueData[i]) {
                return false;
            }
        }
        return true;
    }

    private static final int[] REGISTER_ADDRESS = {
            0x02, 0x02, 0x02, 0x02, 0x02,
            0x04, 0x04, 0x04, 0x04, 0x04,
            0x10, 0x14, 0x1c, 0x20, 0x22, 0x24, 0x26, 0x28, 0x2a, 0x2c, 0x2e, 0x30,
            0x32, 0x32, 0x32, 0x32, 0x32, 0x32,
            0x34, 0x36
    };

    private static final int[] REGISTER_NUMBER = {
            0x01, 0x01, 0x01, 0x01, 0x01,
            0x01, 0x01, 0x01, 0x01, 0x01,
            0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x04,
            0x03, 0x03, 0x03, 0x03, 0x03, 0x03,
            0x01, 0x01
    };

    private static final String[] OPERATE_DATA = {
            "00 00", "00 01", "00 02", "00 03", "00 04",
            "00 00", "00 01", "00 02", "00 03", "00 04",
            "", "00 00", "", "", "", "", "", "", "", "", "", "",
            "01", "02", "03", "04", "05", "06",
            "00", "00"
    };
}
