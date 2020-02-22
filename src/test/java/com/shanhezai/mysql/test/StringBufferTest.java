package com.shanhezai.mysql.test;

import com.shanhezai.mysql.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * @author TANG
 * @date 2019/12/08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StringBufferTest {
    @Test
    public void test1() {
        Random random = new Random();
        // 随机生成0~100的数 (B - A )+A
        int i = random.nextInt(100);
        // sql前缀
        String prefix = "INSERT INTO person (id,username,age,sex) VALUES ";
        // 保存sql后缀
        StringBuffer suffix = new StringBuffer();
        // 构建SQL后缀
        suffix.append("('" + UUIDUtils.getUuid() + "','猪八戒'" + ",'18'" + ",'1'" + "),");
        // prefix + suffix = INSERT INTO person (id,username,age,sex) VALUES
        // ('9a1d20ec978f4bfab704e2157861b7c3','猪八戒','18','1'),
        System.out.println("prefix + suffix = " + prefix + suffix);
    }

    @Test
    public void test2() {
        Random random = new Random();
        // 随机生成0~100的数 (B - A )+A
        int i = random.nextInt(100);
        System.out.println("i = " + i);
    }

    @Test
    public void test3() {
        Random random = new Random();
        // 随机生成0~100的数 (B - A )+A
        int i = random.nextInt(100);
        StringBuffer suffix = new StringBuffer();
        // 构建SQL后缀
        suffix.append("('" + UUIDUtils.getUuid() + "','猪八戒'" + ",'" + i + "'" + ",'1'" + "),");
        System.out.println("suffix = " + suffix);
    }
}
