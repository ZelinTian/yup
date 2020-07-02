package Jdbc.reserve;

/**
 * Created by zelin on 2018/6/13.
 */
public class Reserve {
    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String getRes_phoneNumber() {
        return res_phoneNumber;
    }

    public void setRes_phoneNumber(String res_phoneNumber) {
        this.res_phoneNumber = res_phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_phoneNumber() {
        return user_phoneNumber;
    }

    public void setUser_phoneNumber(String user_phoneNumber) {
        this.user_phoneNumber = user_phoneNumber;
    }

    private String res_name;
    private String res_phoneNumber;
    private String username;
    private String user_phoneNumber;

    public int getReserve_time() {
        return reserve_time;
    }

    public void setReserve_time(int reserve_time) {
        this.reserve_time = reserve_time;
    }

    private int reserve_time;


}
