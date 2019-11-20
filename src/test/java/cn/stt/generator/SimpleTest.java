package cn.stt.generator;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @ClassName SimpleTest
 * @Description TODO
 * @Author shitt7
 * @Date 2019/11/20 15:40
 * @Version 1.0
 */
public class SimpleTest {

    @Test
    public void test1() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = " jdbc:mysql://127.0.0.1:3306/cloud_auth?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
        Connection connection = DriverManager.getConnection(url, "root", "root");
        System.out.println(connection);
    }
}
