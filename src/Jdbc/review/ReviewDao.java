package Jdbc.review;

import Jdbc.jdbcUtil.JdbcUtil;
import Jdbc.reserve.Reserve;
import Jdbc.restaurant.Restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zelin on 2018/6/16.
 */
public class ReviewDao {
    public Review addReview(Review review){
        Connection conn = JdbcUtil.getConnection();
        String sql = "insert into review (username, res_name,res_phoneNumber, Id, rating)values(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, review.getUsername());
            ps.setString(2, review.getRes_name());
            ps.setString(3, review.getRes_phoneNumber());
            ps.setInt(4, review.getId());
            ps.setInt(5, review.getRating());

            ps.execute();
            return review;

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("addReview fail");
        }
        return null;
    }

    public List<Restaurant> division (){
        try {
            Connection conn = JdbcUtil.getConnection();
            Statement state = conn.createStatement();
            String sql = "SELECT r.res_name   FROM restaurant r  WHERE NOT EXISTS " +
            "((SELECT u.username  FROM users u) MINUS (SELECT v.username FROM review v WHERE v.res_name = r.res_name))" ;
            System.out.println(sql);
            ResultSet resultSet = state.executeQuery(sql);
            List<Restaurant> restaurantList = new ArrayList<Restaurant>();
            while (resultSet.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setRes_name(resultSet.getString("res_name"));

                restaurantList.add(restaurant);
            }
            return restaurantList;

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("division fail");
        }
        return null;
    }

    public int getReview (String res_name){
        try {
            Connection conn = JdbcUtil.getConnection();
            Statement state = conn.createStatement();
            ResultSet resultSet = state.executeQuery("select AVG(rating) as rating from review v, restaurant r where" +
                    " r.res_name = '"+res_name+"' and r.res_phoneNumber = v.res_phoneNumber");
            while(resultSet.next())
                return resultSet.getInt("rating");
            return resultSet.getInt("rating");

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("getReview fail");
        }
        return 0;
    }

//    public void updateReview (String res_name, double newRate){
//        try {
//            Connection conn = JdbcUtil.getConnection();
//            Statement s = conn.createStatement();
//            ResultSet resultSet = s.executeQuery("select SUM(rating) as sum, COUNT( * ) as count from review v, restaurant r where" +
//                    " r.res_name = '"+res_name+"' and r.res_phoneNumber = v.res_phoneNumber");
//            double old_sum = resultSet.getDouble("sum");
//            double count = resultSet.getInt("count");
//
//            double new_sum = old_sum + newRate;
//            double new_avg = new_sum / (count+1);
//
//            String sql = "update review set rating=? wherer .res_name = '"+res_name+"' and r.res_phoneNumber = v.res_phoneNumber";
//            try {
//                PreparedStatement ps = conn.prepareStatement(sql);
//                ps.setString(1,user.getPassword());
//                ps.setString(2, user.getUsername());
//
//                ps.execute();
//            }
//            catch (SQLException e){
//                System.out.println("update user fail");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}
