package com.shanhezai.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * 往mysql插入一千万条数据.
 *
 * @author TANG
 * @date 2019/12/08
 */
public class BulkInsert {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        final String url = "jdbc:mysql://127.0.0.1:3306/bigdb";
        final String name = "com.mysql.jdbc.Driver";
        final String user = "root";
        final String password = "root";
        Connection conn = null;
        // 指定连接类型
        Class.forName(name);
        // 获取连接
        conn = DriverManager.getConnection(url, user, password);
        if (conn != null) {
            System.out.println("获取连接成功");
            insert(conn);
        } else {
            System.out.println("获取连接失败");
        }
    }

    /**
     * .往mysql插数据 @param conn
     */
    public static void insert(Connection conn) {
        // 开始时间
        Long begin = System.currentTimeMillis();
        SnowflakeUtils snowflakeUtilsId = new SnowflakeUtils(1, 1);
        Random random = new Random();
        // 随机生成0~100的数 (B - A )+A
        int age = random.nextInt(100);
        int sex = random.nextInt(1);
        // sql前缀
        String prefix = "INSERT INTO person (id,username,age,sex) VALUES ";
        try {
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            // 准备执行语句
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement("");
            // 外层循环，总提交事务次数,插十万条数据提交一次事务
            for (int i = 1; i <= 100; i++) {
                suffix = new StringBuffer();
                // 第j次提交步长
                for (int j = 1; j <= 100000; j++) {
                    // 构建SQL后缀
                    suffix.append("('" + snowflakeUtilsId + "','猪八戒'" + ",'" + age + "'" + ",'" + sex + "'" + "),");
                }
                // 构建完整SQL
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行SQL
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
                // 清空上一次添加的数据
                suffix = new StringBuffer();
            }
            // 头等连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = System.currentTimeMillis();
        // 耗时
        System.out.println("1000万条数据插入花费时间 : " + (end - begin) / 1000 + " s");
        System.out.println("插入完成");
    }
}
