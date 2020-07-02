package gui;
/**
 * Created by yanjw on 2018-06-12.
 */
import Jdbc.action.Action;
import Jdbc.users.Users;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import java.awt.GridLayout;
@SuppressWarnings("serial")


public class RegistrationForm extends JFrame implements ActionListener{
    private JButton btnRegister;
    private static  Action action = new Action();
    JLabel title, idLabel, passLabel, contactLabel;
    JTextField idField, passwordField, contactField;
    JButton registerButton, exitButton;
    JPanel panel;
    List<User> list = new ArrayList<User>();
    Object[][] data;

    // Defining Constructor
    RegistrationForm() {
        setSize(250, 360);
        setLayout(null);
        //Defining Labels
        title = new JLabel("Registration Form");
        title.setBounds(60, 7, 200, 30);
        idLabel = new JLabel("Name");
        idLabel.setBounds(30, 50, 60, 30);
        passLabel = new JLabel("Password");
        passLabel.setBounds(30, 85, 60, 30);
        contactLabel = new JLabel("Contact");
        contactLabel.setBounds(30, 120, 60, 30);

// Defining ID field
        idField = new JTextField();
        idField.setBounds(95, 50, 100, 30);
        passwordField = new JTextField();
        passwordField.setBounds(95, 85, 100, 30);
        contactField = new JTextField();
        contactField.setBounds(95, 120, 100, 30);

        //Defining Exit Button
        exitButton = new JButton("Cancel");
        exitButton.setBounds(25, 230, 80, 30);
        exitButton.addActionListener(this);
        //Defining Register Button
        registerButton = new JButton("Register");
        registerButton.setBounds(110, 230, 100, 30);
        registerButton.addActionListener(this);
        // fixing all Label,TextField,Button
        add(title);
        add(idLabel);
        add(passLabel);
        add(contactLabel);
        add(idField);
        add(passwordField);
        add(contactField);
        add(exitButton);
        add(registerButton);
        //Defining Panel
        panel =new JPanel();
        panel.setLayout(new GridLayout());
        panel.setBounds(250,10, 400, 300);
        panel.setBorder(BorderFactory.createDashedBorder(Color.blue));


        // Enable Scrolling on table

        panel.setEnabled(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == exitButton) {
            dispose();
        };

        if (ae.getSource() == registerButton) {


            if (idField.getText().equals("") || passwordField.getText().equals("") ||
                    contactField.getText().equals("") ) JOptionPane.showMessageDialog(idField, "Fields will not be blank");

            else {
                //Storing records in List
                list.add(new User(idField.getText(), passwordField.getText(), contactField.getText()));
                Users u = new Users();
                u.setUsername(idField.getText());
                u.setPassword(passwordField.getText());
                u.setUser_phoneNumber(contactField.getText());
                action.addUser(u);
                // using for DialogBox
                JOptionPane.showMessageDialog(this, "Successfully Registered");
                idField.setText("");
                passwordField.setText("");
                //bg.clearSelection();
                contactField.setText("");

            }

        }
    }



    public static void main(String[] args) {
        new RegistrationForm();

    }
}