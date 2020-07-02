package Jdbc.restaurant;

import Jdbc.jdbcUtil.JdbcUtil;
import Jdbc.review.ReviewDao;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zelin on 2018/6/10.
 */
public class RestaurantDao {
    public List<Restaurant> getRestaurants() {
        Connection conn = JdbcUtil.getConnection();
        ReviewDao rdao = new ReviewDao();
        try {
            Statement state = conn.createStatement();
//            ResultSet resultSet = state.executeQuery("select u.average_cost, u.open_time, u.res_name, u.res_phoneNumber, address, AVG(rating) as rating from restaurant u, review v " +
//                    "where u.res_name = v.res_name group by u.res_name");
            ResultSet resultSet = state.executeQuery("select * from restaurant");
            List<Restaurant> restaurantList = new ArrayList<Restaurant>();
            while (resultSet.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setAverage_cost(resultSet.getInt("average_cost"));
                restaurant.setOpen_time(resultSet.getInt("open_time"));
                restaurant.setRes_name(resultSet.getString("res_name"));
                restaurant.setRes_phoneNumber(resultSet.getString("res_phoneNumber"));
                restaurant.setAddress(resultSet.getString("address"));
                restaurant.setAvg_rate(rdao.getReview(restaurant.getRes_name()));
                restaurantList.add(restaurant);
            }
            return restaurantList;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("get restaurants fail");
        }
        return null;
    }

    public int getMinCost (){
        Connection conn = JdbcUtil.getConnection();
        ReviewDao rdao = new ReviewDao();
        try {
            Statement state = conn.createStatement();
//
            ResultSet resultSet = state.executeQuery("SELECT MIN(AVG(r.average_cost)) as mincost" +
                    " FROM restaurant r, review v" +
                    " WHERE r.res_name = v.res_name AND r.res_phoneNumber = v.res_phoneNumber" +
                    " GROUP BY v.rating");
            while (resultSet.next()) {
             return   resultSet.getInt("mincost");
            }

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("get restaurants fail");
        }
        return -1;
    }

    public int getMaxCost (){
        Connection conn = JdbcUtil.getConnection();
        ReviewDao rdao = new ReviewDao();
        try {
            Statement state = conn.createStatement();
//
            ResultSet resultSet = state.executeQuery("SELECT MAX(AVG(r.average_cost)) as maxcost" +
                    " FROM restaurant r, review v" +
                    " WHERE r.res_name = v.res_name AND r.res_phoneNumber = v.res_phoneNumber" +
                    " GROUP BY v.rating");
            while (resultSet.next()) {
                return   resultSet.getInt("maxcost");
            }

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("get restaurants fail");
        }
        return -1;
    }

    public List<Restaurant> Andquery (List<Map<String, Object>> cond){
        Connection conn = JdbcUtil.getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from restaurant where 1=1");

        if (cond != null && cond.size()>0){
            for (int i =0; i<cond.size(); i++){
                Map<String, Object> map= cond.get(i);
                sb.append(" and " + map.get("name") + map.get("relation") + map.get("value"));
            }
        }
        try {
            PreparedStatement ptmt = conn.prepareStatement(sb.toString());
            ResultSet  resultSet = ptmt.executeQuery();
            List<Restaurant> restaurantList = new ArrayList<Restaurant>();
            System.out.println(sb);
            while (resultSet.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setAverage_cost(resultSet.getInt("average_cost"));
                restaurant.setOpen_time(resultSet.getInt("open_time"));
                restaurant.setAddress(resultSet.getString("address"));
                restaurant.setRes_name(resultSet.getString("res_name"));
                restaurant.setRes_phoneNumber(resultSet.getString("res_phoneNumber"));

                restaurantList.add(restaurant);
            }
            System.out.println(sb);
            System.out.println(restaurantList.size());
            return restaurantList;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("filter fail");
        }
        return null;
    }

//    public List<Restaurant> Orquery (List<Map<String, Object>> cond) throws SQLException {
//        Connection conn = JdbcUtil.getConnection();
//        StringBuilder sb = new StringBuilder();
//        sb.append("select * from restaurants where 1=2");
//
//        if (cond != null && cond.size()>0){
//            for (int i =0; i<cond.size(); i++){
//                Map<String, Object> map= cond.get(i);
//                sb.append(" or " + map.get("name") + "=" + map.get("value"));
//            }
//        }
//        PreparedStatement ptmt = conn.prepareStatement(sb.toString());
//        ResultSet  resultSet = ptmt.executeQuery();
//        List<Restaurant> restaurantList = new ArrayList<Restaurant>();
//        System.out.println(sb);
//        while (resultSet.next()) {
//            Restaurant restaurant = new Restaurant();
//            restaurant.setAverage_cost(resultSet.getInt("average_cost"));
//            restaurant.setOpen_time(resultSet.getString("open_time"));
//            restaurant.setPostal_code(resultSet.getString("postal_code"));
//            restaurant.setRes_name(resultSet.getString("res_name"));
//            restaurant.setRes_phoneNumber(resultSet.getString("res_phoneNumber"));
//            restaurant.setStreet_name(resultSet.getString("street_name"));
//            restaurant.setStreetNumber(resultSet.getInt("streetNumber"));
//            restaurant.setUnitNumber(resultSet.getInt("unitNumber"));
//
//            restaurantList.add(restaurant);
//        }
//        return restaurantList;
//    }
//
    public Restaurant updateRestaurants(Restaurant r){
        Connection conn = JdbcUtil.getConnection();
        String sql = ("update restaurant set open_time = '"+ r.getOpen_time()+"',address = '"+ r.getAddress()+"' where res_name = '"+ r.getRes_name()+"' and res_phoneNumber = '"+ r.getRes_phoneNumber()+"'");
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            System.out.println(sql);
//            ps.setInt(1, r.getOpen_time());
//            ps.setString(2, r.getAddress());
//            ps.setString(3, r.getRes_name());
//            ps.setString(4, r.getRes_phoneNumber());

            ps.execute();
            return r;
        } catch (SQLException e){
            System.out.println("update restaurant fail");
        }
        return null;
    }

    public void delRestaurant(Restaurant r){
        Connection conn  = JdbcUtil.getConnection();
        String sql = "delete from restaurant where res_name = ? and res_phoneNumber = ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,r.getRes_name());
            ps.setString(2,r.getRes_phoneNumber());

            ps.execute();
        }catch (SQLException e){
            System.out.println("del restaurant fail");
        }
    }


    public Restaurant addRestaurant(Restaurant r){
        Connection conn = JdbcUtil.getConnection();
        String sql = "insert into restaurant (res_name,open_time,res_phoneNumber,average_cost" +
                ")values(?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, r.getRes_name());
            ps.setInt(2, r.getOpen_time());
            ps.setString(3, r.getRes_phoneNumber());
            ps.setInt(4, r.getAverage_cost());

            ps.execute();
            return r;
        } catch (SQLException e){
            System.out.println("add restaurant fail");
        }
        return null;
    }

}
