package Jdbc.users;

import Jdbc.jdbcUtil.JdbcUtil;
import Jdbc.reserve.Reserve;
import gui.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zelin on 2018/6/10.
 */
public class UsersDao {

    public boolean addUser(Users user) {
        Connection conn = JdbcUtil.getConnection();
        String sql = "insert into users (username,password,USER_PHONENUMBER)values(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUser_phoneNumber());

            ps.execute();
        }
         catch (SQLException e){

             // e.printStackTrace();
            System.out.println("false");
            return false;
        }
        return true;
    }

    public void delUser(String username){
        Connection conn = JdbcUtil.getConnection();
        String sql = "delete from users where username = '"+ username+"'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);


            ps.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("false");

        }
    }

    public String updateUser(Users user) {

        Connection conn = JdbcUtil.getConnection();
        String sql = "update users set password=? where username=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getPassword());
            ps.setString(2, user.getUsername());

            ps.execute();
        }
        catch (SQLException e){
            System.out.println("update user fail");
        }
        return user.getPassword();
    }

    public List<Users> getPhoneNumber(String username){
        Connection conn = JdbcUtil.getConnection();
        try {
            Statement state = conn.createStatement();
            ResultSet resultSet = state.executeQuery("select user_phoneNumber from users where username = '"+username+"'");
            List<Users> usersList = new ArrayList<Users>();
            while (resultSet.next()) {
                Users user = new Users();
                user.setUser_phoneNumber(resultSet.getString("user_phoneNumber"));
                usersList.add(user);
            }
            return usersList;
        }catch (SQLException e){
            System.out.println("getReserves fail");
        }
        return null;
    }

    public boolean adminLogin(String username, String password){
        try {
            Connection conn = JdbcUtil.getConnection();

            String sql = "SELECT COUNT( * ) AS COUNT FROM USERS WHERE USERNAME = '"+username+"' AND PASSWORD = '"+password+"'";
            PreparedStatement ps = conn.prepareStatement(sql);
            //ps.setString(1, username);
            //ps.setString(2, password);
            // ps.execute();
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("count"));
                return resultSet.getInt("count") == 1;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkPassword(String username, String password) {

        try {
            Connection conn = JdbcUtil.getConnection();
            String sql = "SELECT COUNT( * ) AS COUNT FROM USERS WHERE USERNAME = '"+username+"' AND PASSWORD = '"+password+"'";
            PreparedStatement ps = conn.prepareStatement(sql);
            //ps.setString(1, username);
            //ps.setString(2, password);
            // ps.execute();
            ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getInt("count"));
            return resultSet.getInt("count") == 1;
        }
    }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Users> getUser(){
        Connection conn = JdbcUtil.getConnection();
        try {
            Statement state = conn.createStatement();
            ResultSet resultSet = state.executeQuery("select * from users");
            List<Users> usersList = new ArrayList<Users>();
            while (resultSet.next()) {
                Users users = new Users();
                users.setUsername(resultSet.getString("username"));
                users.setPassword(resultSet.getString("password"));
                users.setUser_phoneNumber(resultSet.getString("user_phoneNumber"));


                usersList.add(users);
            }
            return usersList;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("getReserves fail");
        }
        return null;
    }
}
