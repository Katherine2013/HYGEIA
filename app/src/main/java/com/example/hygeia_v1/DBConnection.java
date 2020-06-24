package com.example.hygeia_v1;

import android.util.Log;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;


public class DBConnection {
    private static final String TAG = "hygeia1111";

    // 如在本机电脑上运行虚拟机，ip设为10.0.2.2，而非127.0.0.1
    private static String driver = "com.mysql.jdbc.Driver";
    private static String ip = "10.0.2.2";
    private static int port = 3306;
    private static String dbName = "maindb";
    private static String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbName;
    private static String user = "root";
    private static String password = "8888102";


    public static Connection getConn(){
        Connection conn = null;
        Log.v(TAG, "加载CONN驱动成功？？？？？");
        try {
            Class.forName(driver); // 加载MYSQL驱动
            Log.v(TAG, "加载JDBC驱动成功");
            conn = (Connection) DriverManager.getConnection(url, user, password);//获取连接
            Log.d(TAG, "数据库连接成功？");
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "加载JDBC驱动失败");
            e.printStackTrace();
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
//            e.printStackTrace();
        }
        return conn;
    }

    public static void closeAll(Connection conn, PreparedStatement ps){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
