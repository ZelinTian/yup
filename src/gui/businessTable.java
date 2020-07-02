package gui;

import Jdbc.action.Action;
import Jdbc.restaurant.Restaurant;
import Jdbc.restaurant.RestaurantDao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class businessTable {
    private static Action action = new Action();
    private static RestaurantDao rd = new RestaurantDao();

    public static void main(){
        // TODO add table

        // create JFrame and JTable
        JFrame frame = new JFrame();
        JTable table = new JTable();

        // create a table model and set a Column Identifiers to this model

        //model.setColumnIdentifiers(columns);

        Vector<String> columns = new Vector<>(Arrays.asList("Restaurant Name", "address", "Phone Number","open time", "avg cost"));
        Vector<Vector<Object>> rows = new Vector<>();

        List<Restaurant> restaurantList = action.getRestaurants();
        for (Restaurant r : restaurantList){
            Vector<Object> v = new Vector<>();
            v.add(r.getRes_name());
            v.add(r.getAddress());
            v.add(r.getRes_phoneNumber());
            v.add(r.getOpen_time());
            v.add(r.getAverage_cost());
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
        JTextField text4 = new JTextField();
        JTextField text5 = new JTextField();

        // create JButtons
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");
        JButton btnUpdateText = new JButton("can not update restaurant name!");

        text1.setBounds(20, 620, 100, 25);
        text2.setBounds(20, 650, 100, 25);
        text3.setBounds(20, 680, 100, 25);
        text4.setBounds(20, 710, 100, 25);
        text5.setBounds(20, 740, 100, 25);


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
        frame.add(text4);
        frame.add(text5);

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
                row[3] = text4.getText();
                row[4] = text5.getText();

                // add row to the model
                model.addRow(row);
                Restaurant r = new Restaurant();
                r.setRes_name(text1.getText());
                r.setAddress(text2.getText());
                r.setRes_phoneNumber(text3.getText());
                r.setOpen_time(Integer.parseInt(text4.getText()));
                r.setAverage_cost(Integer.parseInt(text5.getText()));
                rd.addRestaurant(r);
            }
        });

        // button remove row
// button remove row
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable

                    String resName = table.getModel().getValueAt(i, 0).toString();
                    String resAdd = table.getModel().getValueAt(i, 1).toString();
                    String resNum = table.getModel().getValueAt(i, 2).toString();
                    int resTime = (Integer) table.getModel().getValueAt(i, 3);
                    int resCost = (Integer)table.getModel().getValueAt(i, 4);

                    model.removeRow(i);


                    Restaurant r = new Restaurant();
                    r.setRes_name(resName);
                    r.setAddress(resAdd);
                    r.setRes_phoneNumber(resNum);
                    r.setOpen_time(resTime);
                    r.setAverage_cost(resCost);
                    rd.delRestaurant(r);
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
                text4.setText(model.getValueAt(i, 3).toString());
                text5.setText(model.getValueAt(i, 4).toString());

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
                    String resName = table.getModel().getValueAt(i,0).toString();
                    String resNumber = table.getModel().getValueAt(i,2).toString();
                    model.setValueAt(text2.getText(), i, 1);
                    model.setValueAt(text3.getText(), i, 2);
                    model.setValueAt(text4.getText(), i, 3);
                    model.setValueAt(text5.getText(), i, 4);

                    Restaurant r = new Restaurant();

                    int resTime = Integer.parseInt(text4.getText());
                    int resCost = Integer.parseInt(text5.getText());

                    r.setRes_name(resName);
                    r.setAddress(text2.getText());
                    r.setRes_phoneNumber(resNumber);
                    r.setOpen_time(resTime);
                    r.setAverage_cost(resCost);
                    rd.updateRestaurants(r);
                }
                else{
                    System.out.println("Update Error");
                }
            }
        });
        frame.setSize(1400,1000);
        frame.setLocationRelativeTo(null);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}