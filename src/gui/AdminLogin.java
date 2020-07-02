package gui;

import Jdbc.users.UsersDao;

/**
 * Created by yanjw on 2018-06-12.
 */
public class AdminLogin {

    public static boolean Adminauthenticate(String username, String password) {
        // hardcoded username and password
        System.out.println(username + password);
        UsersDao ud = new UsersDao();
        if (ud.adminLogin(username, password)) {
            return true;
        }
        return false;
    }
}
