package Jdbc.review;

/**
 * Created by zelin on 2018/6/16.
 */
public class Review {
    private String username;
    private String res_name;
    private String res_phoneNumber;
    private int Id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    private int rating;
}
