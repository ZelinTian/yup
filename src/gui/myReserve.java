package gui;

import Jdbc.reserve.Reserve;
import Jdbc.reserve.ReserveDao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by yanjw on 2018-06-15.
 */
public class myReserve {
    private static ReserveDao dao = new ReserveDao();


    public myReserve(){
        JFrame frame = new JFrame("My Reservation");
        Container contentPane = frame.getContentPane();

        Login l = new Login();
        String username = l.getUserName();
        List<Reserve> reserves = dao.getReserves(username);
        //if (reserves.size() == 0)
        String resName = "";
        String resNumber = "";
        String Label = "<html>";
        int reserveTime = 0;
        for (Reserve r : reserves){
            resName = r.getRes_name();
            resNumber = r.getRes_phoneNumber();
            reserveTime = r.getReserve_time();
            Label += "Your reservation at restaurant: " + resName + " Restaurant number is: "+resNumber
                    + ", Rreserved by user: " + username + " At time: " + reserveTime + "<br/>";

        }
        /*
        String resName = reserves.get(0).getRes_name();
        String resNumber = reserves.get(0).getRes_phoneNumber();
        int reserveTime = reserves.get(0).getReserve_time();

        JLabel closeButton = new JLabel("Your reservation at restaurant: " +
                resName + ", restaurant number is: " + resNumber + ", reserved by user: "+ username + ", at time:" + reserveTime, JLabel.CENTER);*/

        JLabel closeButton = new JLabel(Label + "</html>");
        contentPane.add(closeButton);

        // set the size of the frame 300 x 200
        frame.setBounds(50, 50, 300, 200);
        frame.setVisible(true);
    }
}


