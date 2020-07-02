package gui;

import Jdbc.action.Action;
import Jdbc.carrier.Carrier;
import Jdbc.carrier.CarrierDao;
import Jdbc.restaurant.Restaurant;
import Jdbc.restaurant.RestaurantDao;

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

public class carrierTable {
    private static Action action = new Action();
    private static CarrierDao cd = new CarrierDao();

    public static void main(){

        // create JFrame and JTable
        JFrame frame = new JFrame();
        JTable table = new JTable();

        Vector<String> columns = new Vector<>(Arrays.asList("Carrier Name", "Carrier Phone_Number"));
        Vector<Vector<Object>> rows = new Vector<>();

        List<Carrier> carrierList = action.getCarrier();
        for (Carrier c : carrierList){
            Vector<Object> v = new Vector<>();
            v.add(c.getCarrier_name());
            v.add(c.getCarrier_phoneNumber());
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

        // create JButtons
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");
        JButton btnUpdateText = new JButton("can not update Carrier Name!");


        text1.setBounds(20, 620, 100, 25);
        text2.setBounds(20, 650, 100, 25);

        btnAdd.setBounds(150, 620, 100, 25);
        btnUpdate.setBounds(150, 665, 100, 25);
        btnDelete.setBounds(150, 710, 100, 25);

        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 1300, 550);

        frame.setLayout(null);

        frame.add(pane);

        // add JTextFields to the jframe
        frame.add(text1);
        frame.add(text2);

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

                // add row to the model
                model.addRow(row);

                Carrier carrier = new Carrier();
                carrier.setCarrier_name(text1.getText());
                carrier.setCarrier_phoneNumber(text2.getText());
                cd.addCarrier(carrier);
            }
        });

        // button remove row
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();
                System.out.println("index:" + i);
                if(i >= 0){
                    // remove a row from jtable
                    model.removeRow(i);

                    Carrier carrier = new Carrier();
                    carrier.setCarrier_name(text1.getText().trim());
                    carrier.setCarrier_phoneNumber(text2.getText());
                    cd.delCarrier(carrier);
                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });

        // get selected row data From table to textfields
        table.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                text1.setText(model.getValueAt(i, 0).toString());
                text2.setText(model.getValueAt(i, 1).toString());
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
                    model.setValueAt(text1.getText().trim(), i, 0);
                    model.setValueAt(text2.getText().trim(), i, 1);

                    Carrier carrier = new Carrier();
                    String carName = text1.getText().toString();
                    String carNumber = text2.getText().toString();
                    carrier.setCarrier_name(carName);
                    carrier.setCarrier_phoneNumber(carNumber);
                    System.out.println("update"+carName+carNumber);
                    cd.updateCarrier(carrier);
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