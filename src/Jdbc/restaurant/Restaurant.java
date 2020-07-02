package Jdbc.restaurant;

/**
 * Created by zelin on 2018/6/10.
 */
public class Restaurant {
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


    public int getAverage_cost() {
        return average_cost;
    }

    public void setAverage_cost(int average_cost) {
        this.average_cost = average_cost;
    }


    private String res_name;
    private String res_phoneNumber;
    private int average_cost;

    public double getAvg_rate() {
        return avg_rate;
    }

    public void setAvg_rate(double avg_rate) {
        this.avg_rate = avg_rate;
    }

    private double avg_rate;

    public int getOpen_time() {
        return open_time;
    }

    public void setOpen_time(int open_time) {
        this.open_time = open_time;
    }

    private int open_time;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;


}
