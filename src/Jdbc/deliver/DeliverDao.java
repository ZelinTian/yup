package Jdbc.deliver;

import Jdbc.jdbcUtil.JdbcUtil;
import Jdbc.reserve.Reserve;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zelin on 2018/6/13.
 */
public class DeliverDao {
    public Deliver addDeliver (Deliver deliver){
        Connection conn = JdbcUtil.getConnection();
//        String a = deliver.getUsername();
//        String b = deliver.getRes_name();
//        String c = deliver.getRes_phoneNumber();
//        String d = deliver.getCarrier_name();
        String sql = "insert into deliver_order (carrier_name,res_name,res_phoneNumber,username) values ('"+deliver.getCarrier_name()+"','"+deliver.getRes_name()+"','"+deliver.getRes_phoneNumber()+"','"+deliver.getUsername()+"')";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            System.out.println(sql);
//            ps.setString(1, deliver.getCarrier_name());
//            ps.setString(2, deliver.getRes_name());
//            ps.setString(3,deliver.getRes_phoneNumber());
//            ps.setString(4, deliver.getUsername());

            ps.execute();
            return deliver;
            //return true;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("addDeliver fail");
        }
        return null;
        //return false;
    }
    public List<Deliver> getDeliver (String username){
        Connection conn = JdbcUtil.getConnection();
        try {
            Statement state = conn.createStatement();
            ResultSet resultSet = state.executeQuery("SELECT DISTINCT c.carrier_name, c.carrier_phoneNumber, d.res_name, d.res_phoneNumber\n" +
                    "   FROM carrier c, deliver_order d\n" +
                    "   WHERE d.carrier_name = c.carrier_name AND d.username = '"+username+"'");
            List<Deliver> deliverList = new ArrayList<Deliver>();
            while (resultSet.next()) {
                Deliver deliver = new Deliver();
                deliver.setUsername(username);
                deliver.setRes_name(resultSet.getString("res_name"));
                deliver.setCarrier_name(resultSet.getString("carrier_name"));
                deliver.setRes_phoneNumber(resultSet.getString("res_phoneNumber"));

                deliverList.add(deliver);
            }
            return deliverList;
        }catch (SQLException e){
            System.out.println("getReserves fail");
        }
        return null;
    }

    public void delReserve(Deliver deliver){
        Connection conn  = JdbcUtil.getConnection();
        String sql = "delete from reserve where username =? and carrier_name = ? and res_phoneNumber = ? and res_name = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,deliver.getUsername());
            ps.setString(2,deliver.getCarrier_name());
            ps.setString(3,deliver.getRes_phoneNumber());
            ps.setString(4,deliver.getRes_name());

            ps.execute();
        }catch (SQLException e){
            System.out.println("delDeliver fail");
        }
    }
}
