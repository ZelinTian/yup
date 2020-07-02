package gui;

import Jdbc.action.Action;
import Jdbc.users.Users;
import Jdbc.users.UsersDao;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class userTable {
    private static Action action = new Action();
    private static UsersDao ud = new UsersDao();

    public static void main(){

        // create JFrame and JTable
        JFrame frame = new JFrame();
        JTable table = new JTable();

        Vector<String> columns = new Vector<>(Arrays.asList("User Name", "Password", "User Phone_Number"));
        Vector<Vector<Object>> rows = new Vector<>();

        //TODO
        List<Users> userList = ud.getUser();
        for (Users u : userList){
            Vector<Object> v = new Vector<>();
            v.add(u.getUsername());
            v.add(u.getPassword());
            v.add(u.getUser_phoneNumber());
            rows.add(v);
        }

        DefaultTableModel model = new DefaultTableModel(rows, columns) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 2 ? Integer.class : String.class;
            }
        };


        // set the model to the table
        table.setModel(model);


        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,15);
        table.setFont(font);
        table.setRowHeight(20);

        // create JTextFields
        JTextField text1 = new JTextField();
        JTextField text2 = new JTextField();
        JTextField text3 = new JTextField();


        // create JButtons
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");
        JButton btnUpdateText = new JButton("can not update User Name!");

        text1.setBounds(20, 620, 100, 25);
        text2.setBounds(20, 650, 100, 25);
        text3.setBounds(20, 680, 100, 25);


        btnAdd.setBounds(150, 630, 100, 25);
        btnUpdate.setBounds(150, 660, 100, 25);
        btnUpdateText.setBounds(150, 660, 100, 25);
        btnDelete.setBounds(150, 690, 100, 25);


        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 1300, 550);

        frame.setLayout(null);

        frame.add(pane);

        // add JTextFields to the jframe
        frame.add(text1);
        frame.add(text2);
        frame.add(text3);

        // add JButtons to the jframe
        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnUpdate);

        // create an array of objects to set the row data
        Object[] row = new Object[10];

        // button add row
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                row[0] = text1.getText();
                row[1] = text2.getText();
                row[2] = text3.getText();

                // add row to the model
                model.addRow(row);

                Users user = new Users();
                user.setPassword(text2.getText());
                user.setUsername(text1.getText());
                user.setUser_phoneNumber(text3.getText());
                ud.addUser(user);
            }
        });

        // button remove row
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable
                    String username = table.getModel().getValueAt(i,0).toString();
                    model.removeRow(i);
                    System.out.println("delete"+username.toString().trim());
                    ud.delUser(username);
                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });

        // get selected row data From table to textfields
        table.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e){

                // i = the index of the selected row
                int i = table.getSelectedRow();


                text1.setText(model.getValueAt(i, 0).toString());
                text2.setText(model.getValueAt(i, 1).toString());
                text3.setText(model.getValueAt(i, 2).toString());

            }
        });

        // button update row
        btnUpdate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                if(i >= 0)
                {
                    //model.setValueAt(text1.getText(), i, 0);
                    String username = table.getModel().getValueAt(i,0).toString();
                    model.setValueAt(text2.getText(), i, 1);
                    model.setValueAt(text3.getText(), i, 2);

                    Users user = new Users();
                    user.setUsername(username);
                    user.setPassword(text2.getText());
                    user.setUser_phoneNumber(text3.getText());
                    //System.out.println("update:"+text2.getText().toString()+text3.getText().toString());
                    ud.updateUser(user);
                }
                else{
                    System.out.println("Update Error");
                }
            }
        });

        frame.setSize(1400 , 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}