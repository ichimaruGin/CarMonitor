package org.zju.car_monitor.util;


import java.sql.Connection;
import java.sql.DriverManager;


/**
 * User: jiezhen
 * Date: 3/20/13
 * Time: 9:49 PM
 */
public class DbUtil {
    private static String driver = "com.mysql.jdbc.Driver",// 驱动
            url = PropertyManager.getMySQLURL(),// URL
            Name = PropertyManager.getMySQLUserName(),// 用户名
            Password = PropertyManager.getMySQLPassword();// 密码

    public static Connection getConnection() {
        Connection connection = null;
        try {
             connection = DriverManager.getConnection(url, Name, Password);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
