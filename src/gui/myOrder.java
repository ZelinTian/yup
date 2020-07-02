package gui;

import Jdbc.deliver.Deliver;
import Jdbc.deliver.DeliverDao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by yanjw on 2018-06-15.
 */
public class myOrder {
    private static DeliverDao dao = new DeliverDao();

    public myOrder(){
        JFrame frame = new JFrame("My order");
        Container contentPane = frame.getContentPane();

        Login l = new Login();
        String username = l.getUserName();
        List<Deliver> d = dao.getDeliver(username);
        String result = "<html>Your Order at ";
        for (Deliver dd: d ){
            String resName = dd.getRes_name().trim();
            String resNumber = dd.getRes_phoneNumber().trim();
            String carName = dd.getCarrier_name().trim();
            result += resName+"    "+resNumber+"   , delivered by  "+carName + "<br/>";
        }
        JLabel closeButton = new JLabel(result+"</html>", JLabel.CENTER);
        contentPane.add(closeButton);

        // set the size of the frame 300 x 200
        frame.setBounds(50, 50, 300, 200);
        frame.setVisible(true);
    }
}
