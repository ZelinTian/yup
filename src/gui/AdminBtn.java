package gui; /**
 * Created by yanjw on 2018-06-12.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class AdminBtn{
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JPanel controlPanel;
    private LoginDialog ld;

    public AdminBtn(){
    }

    public static void start(){
        AdminBtn threeBtnwindow = new AdminBtn();
        threeBtnwindow.showButton();

    }
    /*
    private static ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = threeBtn.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }*/
    private void showButton(){

        mainFrame = new JFrame("Admin Home Page");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                //System.exit(0);
            }
        });
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

        JButton busiTButton = new JButton("Business");
        JButton userTButton = new JButton("User");
        JButton carrTButton = new JButton("Carrier");
        carrTButton.setHorizontalTextPosition(SwingConstants.LEFT);


        busiTButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e1) {
                        businessTable b = new businessTable();
                        b.main();
                        //b.setVisible(true);
                    }
                });
        userTButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e2) {
                        userTable u = new userTable();
                        u.main();
                        //u.setVisible(true);
                    }
                });
        carrTButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e3) {
                        carrierTable c = new carrierTable();
                        c.main();
                        //c.setVisible(true);
                    }
                });

        controlPanel.add(busiTButton);
        controlPanel.add(userTButton);
        controlPanel.add(carrTButton);

        mainFrame.setVisible(true);
    }

}


