package Jdbc.action;

import Jdbc.carrier.Carrier;
import Jdbc.carrier.CarrierDao;
import Jdbc.deliver.Deliver;
import Jdbc.deliver.DeliverDao;
import Jdbc.reserve.Reserve;
import Jdbc.reserve.ReserveDao;
import Jdbc.restaurant.Restaurant;
import Jdbc.restaurant.RestaurantDao;
import Jdbc.review.ReviewDao;
import Jdbc.users.Users;
import Jdbc.users.UsersDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

/**
 * Created by zelin on 2018/6/10.
 */
public class Action {
    private UsersDao ud = new UsersDao();
    private CarrierDao cd = new CarrierDao();
    private ReserveDao resd = new ReserveDao();
    private RestaurantDao rd = new RestaurantDao();
    private DeliverDao dd = new DeliverDao();
    private ReviewDao reviewDao = new ReviewDao();



    public boolean addUser(Users u) {
        return ud.addUser(u);
    }

    public String updateUser(Users u) {

        return ud.updateUser(u);
    }

    public  List<Restaurant> division (){
        return reviewDao.division();
    }

    public Carrier addCarrier(Carrier carrier) {
        return cd.addCarrier(carrier);
    }

    public List<Carrier> getCarrier() {
        return cd.getCarrier();
    }

    public void delCarrier(Carrier c) {
        cd.delCarrier(c);
    }

    public String updateCarrier(Carrier c) {
        return cd.updateCarrier(c);
    }

    public List<Reserve> getReserves(String username) {
        return resd.getReserves(username);
    }

    public Reserve addReserves(Reserve r) {
        return resd.addReserve(r);
    }

    public int updateReserves(Reserve r) {
        return resd.updateReserve(r);
    }

    public Restaurant updateRestaurant(Restaurant restaurant){
        return rd.updateRestaurants(restaurant);
    }

    public void delReserve(Reserve r) {
        resd.delReserve(r);
    }

    public List<Restaurant> getRestaurants() {
        return rd.getRestaurants();
    }

    public List<Restaurant> Andquery(List<Map<String, Object>> cond) {

        return rd.Andquery(cond);
    }



    /*
    public Deliver addDeliver (Deliver deliver){
        return dd.addDeliver(deliver);
    }*/

    public void delRestaurant(Restaurant r) {
        rd.delRestaurant(r);
    }

    public Restaurant addRestaurant(Restaurant r) {
        return rd.addRestaurant(r);
    }

    public static void main(String[] args) throws SQLException {
//        Users user = new Users();
//        UsersDao ud = new UsersDao();
//        user.setUsername("aa");
//        user.setPassword("tzl");
//        ud.updateUser(user);
//        String pas = updateUser(user);
//        System.out.println(pas);

//        user.setUser_phoneNumber("7783196160");
//        ud.addUser(user);
//        System.out.println(user.getPassword());
//        Boolean a = ud.checkPassword(user.getUsername(), user.getPassword());
//        if (a == true){
//            System.out.println("true");
//        }else {
//            System.out.println("false");
//        }
//        System.out.println("name:" + user.getUsername() + "password:" + user.getPassword() + "phone#:" + user.getUser_phoneNumber());

//        Restaurant restaurant = new Restaurant();
//        RestaurantDao rd = new RestaurantDao();
//        List<Map<String, Object>> conds = new ArrayList<Map<String, Object>>();
//        Map<String, Object> cond = new HashMap<String, Object>();
//        cond.put("name", "res_phoneNumber");
//        cond.put("value", "'7783196160'");
//        conds.add(cond);
//        Map<String, Object> cond1 = new HashMap<String, Object>();
//        cond1.put("name", "average_cost");
//        cond1.put("value", "'12'");
//        conds.add(cond1);
//        List<Restaurant> result = rd.Andquery(conds);
//        for (int i = 0; i < result.size(); i++) {
//            System.out.println(result.get(i).getAverage_cost());
//        }

        // updateRestaurant
//        restaurant.setRes_name("a");
//        restaurant.setPostal_code("v8e3r4");
//        restaurant.setUnitNumber(123);
//        restaurant.setOpen_time("1222");
//        restaurant.setStreetNumber(4532);
//        restaurant.setStreet_name("west mall");
//        restaurant.setRes_phoneNumber("7783196160");
//        rd.updateRestaurants(restaurant);
//        System.out.println("update success restaurant");
        // delRestaurant
//        restaurant.setRes_phoneNumber("7783196160");
//        restaurant.setRes_name("a");
//        rd.delRestaurant(restaurant);
//        System.out.println("delete restaurant success");


        // addRestaurant
//        restaurant.setRes_name("a");
//        restaurant.setPostal_code("v8e3r4");
//        restaurant.setUnitNumber(123);
//        restaurant.setOpen_time("1222");
//        restaurant.setStreetNumber(4532);
//        restaurant.setStreet_name("west mall");
//        restaurant.setRes_phoneNumber("7783196160");
//        restaurant.setAverage_cost(12);
//        rd.addRestaurant(restaurant);
//        System.out.println("add success restaurant");


//        List<Restaurant> restaurantList = .getRestaurants();
//        for (Restaurant r : restaurantList){
//            System.out.println("name:" + r.getRes_name());
//        }
//    }


//        Carrier carrier = new Carrier();
//        CarrierDao cd = new CarrierDao();
//        List<Carrier> result = cd.getCarrier();
//        // getCarrier
//        for (Carrier c : result){
//            System.out.println("name:" + c.getCarrier_name());
//        }
        // addCarrier
//        carrier.setCarrier_name("asdfg");
//        carrier.setCarrier_phoneNumber("1231231321");
//        cd.addCarrier(carrier);
//        System.out.println("succss add carrier");
        // delCarrier
//        carrier.setCarrier_name("asdfg");
//        cd.delCarrier(carrier);
//        System.out.println("success del");
        // updateCarrier
//        carrier.setCarrier_name("aba");
//        carrier.setCarrier_phoneNumber("222");
//        cd.updateCarrier(carrier);
//        System.out.println("update success");


        //addReserve
//        Reserve reserve = new Reserve();
//        ReserveDao resd = new ReserveDao();
//        reserve.setUsername("aab");
//        reserve.setReserve_time(1200);
//        reserve.setUser_phoneNumber("7783196160");
//        reserve.setRes_name("ubc");
//        reserve.setRes_phoneNumber("77839401827");
//        resd.addReserve(reserve);
//        System.out.println("add reserve success");

        //updateReserve
//        reserve.setUsername("aab");
//        reserve.setReserve_time(1300);
//        resd.updateReserve(reserve);
//        System.out.println("update reserve success");

        //getReserve
//        List<Reserve> reserveList = resd.getReserves();
//        for (Reserve r : reserveList){
//            System.out.println("time:" + r.getReserve_time());
//        }

        // delReserve
//        reserve.setUsername("aab");
//        resd.delReserve(reserve);
//        System.out.println("del reserve success");
    }
}






