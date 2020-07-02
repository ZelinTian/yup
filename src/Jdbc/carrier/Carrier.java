package Jdbc.carrier;

/**
 * Created by zelin on 2018/6/11.
 */
public class Carrier {

    public String getCarrier_name() {
        return carrier_name;
    }

    public void setCarrier_name(String carrier_name) {
        this.carrier_name = carrier_name;
    }

    public String getCarrier_phoneNumber() {
        return carrier_phoneNumber;
    }

    public void setCarrier_phoneNumber(String carrier_phoneNumber) {
        this.carrier_phoneNumber = carrier_phoneNumber;
    }

    private String carrier_name;
    private String carrier_phoneNumber;

}
