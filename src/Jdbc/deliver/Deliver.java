package Jdbc.deliver;

/**
 * Created by zelin on 2018/6/13.
 */
public class Deliver {
    public String getCarrier_name() {
        return carrier_name;
    }

    public void setCarrier_name(String carrier_name) {
        this.carrier_name = carrier_name;
    }

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

    private String carrier_name;
    private String res_name;
    private String res_phoneNumber;
    private String username;

}
