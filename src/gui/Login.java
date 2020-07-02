package gui;

import Jdbc.action.Action;
import Jdbc.jdbcUtil.JdbcUtil;
import Jdbc.users.Users;
import Jdbc.users.UsersDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by yanjw on 2018-06-12.
 */
public class Login {
    private static UsersDao user = new UsersDao();
    private static String name;

    public static boolean authenticate(String username, String password) {
        // pass down username & password:  a.checkPassword(username, password);
        // return T or F

        //System.out.println(username +"  " +password);
        if (user.checkPassword(username, password)) {
            //System.out.println(3);
        //if (username.equals("1") && password.equals("1")) {
            name = username;
            return true;
        }
        return false;
    }
    public String getUserName()
    { return name; }
}
