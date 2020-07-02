package gui; /**
 * Created by yanjw on 2018-06-12.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class threeBtn extends JFrame{
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JPanel controlPanel;
    private LoginDialog ld;

    public threeBtn(){
    }

    public void start(){
        threeBtn threeBtnwindow = new threeBtn();
        threeBtnwindow.showButton();

    }

    private static ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = threeBtn.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    private void showButton(){

        mainFrame = new JFrame("User Home Page");
        mainFrame.setSize(800,400);
        mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("", JLabel.CENTER);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);

        headerLabel.setText("Hi " + "" + "!");

        //resources folder should be inside SWING folder.
        // TODO
        //ImageIcon icon = createImageIcon("images/iconPig.jpeg","Java");

        JButton orderButton = new JButton("ORDER");
        JButton reserveButton = new JButton("RESERVE");
        JButton rateButton = new JButton("RATE");
        JButton MYorderButton = new JButton("MY ORDER");
        JButton MYreserveButton = new JButton("MY RESERVE");
        rateButton.setHorizontalTextPosition(SwingConstants.LEFT);

        orderButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e1) {order o = new order();}
                });
        reserveButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e2) {
                        reserve r = new reserve();
                    }
                });
        rateButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e3) {
                        rate r = new rate();
                    }
                });

        MYorderButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e1) {
                        myOrder o = new myOrder();
                    }
                });
        MYreserveButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e2) {
                        myReserve r = new myReserve();
                    }
                });

        controlPanel.add(orderButton);
        controlPanel.add(reserveButton);
        controlPanel.add(rateButton);
        controlPanel.add(MYorderButton);
        controlPanel.add(MYreserveButton);

        mainFrame.setVisible(true);
    }

}


