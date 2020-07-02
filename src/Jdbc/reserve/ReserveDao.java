package Jdbc.reserve;

import Jdbc.jdbcUtil.JdbcUtil;
import Jdbc.restaurant.Restaurant;
import Jdbc.users.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zelin on 2018/6/13.
 */
public class ReserveDao {
    public List<Reserve> getReserves(String username){
        Connection conn = JdbcUtil.getConnection();
        try {
            Statement state = conn.createStatement();
            ResultSet resultSet = state.executeQuery("select * from reserve where username = '"+username+"'");
            List<Reserve> reserveList = new ArrayList<Reserve>();
            while (resultSet.next()) {
                Reserve reserve = new Reserve();
                reserve.setRes_phoneNumber(resultSet.getString("res_phoneNumber"));
                reserve.setRes_name(resultSet.getString("res_name"));
                reserve.setReserve_time(resultSet.getInt("reserve_time"));
                reserve.setUsername(resultSet.getString("username"));

                reserveList.add(reserve);
            }
            return reserveList;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("getReserves fail");
        }
        return null;
    }

    public Reserve addReserve(Reserve reserve){
        Connection conn = JdbcUtil.getConnection();
        String sql = "insert into reserve (username, res_name,res_phoneNumber, reserve_time)values(?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, reserve.getUsername());
            ps.setString(2, reserve.getRes_name());
            ps.setString(3, reserve.getRes_phoneNumber());
            ps.setInt(4, reserve.getReserve_time());

            ps.execute();
            return reserve;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("addReserve fail");
        }
        return null;
    }

    public int updateReserve(Reserve reserve){
        Connection conn  = JdbcUtil.getConnection();
        String sql = "update reserve set reserve_time = ? where username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,reserve.getReserve_time());
            ps.setString(2,reserve.getUsername());

            ps.execute();
            return reserve.getReserve_time();
        }catch (SQLException e){
            System.out.println("updateReserve fail");
        }
        return 0;
    }

    public void delReserve(Reserve reserve){
        Connection conn  = JdbcUtil.getConnection();
        String sql = "delete from reserve where username =? ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,reserve.getUsername());

            ps.execute();
        }catch (SQLException e){
            System.out.println("delReserve fail");
        }
    }
}
