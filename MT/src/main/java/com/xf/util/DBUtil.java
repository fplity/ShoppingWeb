package com.xf.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties config = new Properties();
            try (InputStream input = DBUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
                if (input != null) {
                    config.load(input);
                }
            }
            String url = setting(config, "db.url", "DB_URL", "jdbc:mysql://localhost:3306/mt");
            String username = setting(config, "db.username", "DB_USERNAME", "root");
            String password = setting(config, "db.password", "DB_PASSWORD", null);
            if (password == null) {
                throw new IllegalStateException("Configure db.password in local db.properties or set DB_PASSWORD.");
            }
           return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static String setting(Properties config, String key, String environment, String fallback) {
        String value = System.getProperty(key);
        if (value == null) {
            value = System.getenv(environment);
        }
        return value == null ? config.getProperty(key, fallback) : value;
    }
    public void main() {
        System.out.println(DBUtil.getConnection());
    }
}
