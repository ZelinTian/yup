package Jdbc.carrier;

import Jdbc.jdbcUtil.JdbcUtil;
import Jdbc.restaurant.Restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zelin on 2018/6/11.
 */
public class CarrierDao {


    public List<Carrier> getCarrier(){
        Connection conn = JdbcUtil.getConnection();
        try{
            Statement state = conn.createStatement();
            ResultSet resultSet = state.executeQuery("select * from carrier");
            List<Carrier> carrierList = new ArrayList<Carrier>();
            while (resultSet.next()) {
                Carrier carrier = new Carrier();
                carrier.setCarrier_name(resultSet.getString("carrier_name"));
                carrier.setCarrier_phoneNumber(resultSet.getString("carrier_phoneNumber"));
                carrierList.add(carrier);
            }
            return carrierList;
        }catch (SQLException e){
            System.out.println("getCarrier fail");
        }
        return null;
    }

    public Carrier addCarrier(Carrier c) {
        Connection conn = JdbcUtil.getConnection();
        String sql = "insert into carrier (carrier_name,carrier_phoneNumber)values(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getCarrier_name());
            ps.setString(2, c.getCarrier_phoneNumber());

            ps.execute();
            return c;
        }catch (SQLException e) {
            System.out.println("addCarrier fail");
        }
        return null;
    }

    public void delCarrier(Carrier c){
        Connection conn  = JdbcUtil.getConnection();
        String sql = "delete from carrier where carrier_name = '" + c.getCarrier_name() + "'";

        try {
            Statement ps = conn.prepareStatement(sql);
            //ps.setString(1,c.getCarrier_name());
            System.out.println(c.getCarrier_name()+"@");
            System.out.println(sql);
            ps.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("delCarrier fail");
        }
    }

    public String updateCarrier(Carrier c) {
        Connection conn  = JdbcUtil.getConnection();
        String sql = "update carrier set carrier_phoneNumber = '" + c.getCarrier_phoneNumber() + "' where carrier_name = '" + c.getCarrier_name() + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            // ps.setString(1,c.getCarrier_phoneNumber());
            //ps.setString(2,c.getCarrier_name());

            ps.execute();
            return c.getCarrier_phoneNumber();
        } catch (SQLException e){
            System.out.println("updateCarrier fail");
        }
        return null;
    }

}
