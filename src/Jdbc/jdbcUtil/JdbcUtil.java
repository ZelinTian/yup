package Jdbc.jdbcUtil;

import java.sql.*;

/**
 * Created by zelin on 2018/6/10.
 */
public class JdbcUtil {
    public static  final String URL = "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug";
    public static  final String USER = "ora_m1f1b";
    public static  final String PASSWORD = "a14629159";
    private static Connection conn = null;
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("hi");
    }
    public  static  Connection getConnection(){
        return conn;
    }


    public static void main(String[] args) throws SQLException {
        Statement state = conn.createStatement();
        ResultSet resultSet = state.executeQuery("select *  from users");

        while (resultSet.next()){
            System.out.println(resultSet.getString("username")+","+resultSet.getString("password"));
        }
    }
}


