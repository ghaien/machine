package cn.ghaien.machinedemo.dao;

import cn.ghaien.machinedemo.entity.pojo.UserInfo;
import org.apache.tomcat.util.buf.HexUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Encoder;
import sun.misc.HexDumpEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author guo.haien
 * @Date 2018/7/5
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserInfoRepositoryTests {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    public void testInsert() throws NoSuchAlgorithmException {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("ghaien");
        userInfo.setUserName("郭海恩");
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setIsDelete("1");
        String src = "ghaien";
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5Bytes = md.digest(src.getBytes());
        //转化为16进制字符串输出
        System.out.println(HexUtils.toHexString(md5Bytes));
        userInfo.setPassword(HexUtils.toHexString(md5Bytes));
        userInfoRepository.save(userInfo);
    }
}
