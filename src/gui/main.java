package gui; /**
 * Created by yanjw on 2018-06-12.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class main {
    public static void main(String[] args) {
        final JFrame frame = new JFrame("YEP");
        //final JFrame frame2 = new JFrame("USER REGISTRATION");
        final JButton btnLogin = new JButton("Click to User Login");
        final JButton btnRegister = new JButton("Click to User Register");
        final JButton btnAdmin = new JButton("Click to Admin Login");

        btnLogin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        LoginDialog loginDlg = new LoginDialog(frame);
                        loginDlg.setVisible(true);
                        // if logon successfully
                        if(loginDlg.isSucceeded()){
                            //btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
                            threeBtn tB = new threeBtn();
                            tB.start();
                            //tB.setVisible(true);
                        }
                    }
                });

        btnRegister.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e2) {
                        RegistrationForm registrationFrm = new RegistrationForm();
                        registrationFrm.setVisible(true);
                    }
                });

        btnAdmin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        AdminLoginDialog aloginDlg = new AdminLoginDialog(frame);
                        aloginDlg.setVisible(true);
                        // if logon successfully
                        if(aloginDlg.isSucceeded()){
                            //btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
                            AdminBtn aB = new AdminBtn();
                            aB.start();
                            //tB.setVisible(true);
                        }
                    }
                });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(btnAdmin);
        frame.getContentPane().add(btnLogin);
        frame.getContentPane().add(btnRegister);

        frame.setVisible(true);
    }
}