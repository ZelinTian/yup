package gui;

import Jdbc.action.Action;
import Jdbc.carrier.Carrier;
import Jdbc.deliver.Deliver;
import Jdbc.deliver.DeliverDao;
import Jdbc.restaurant.Restaurant;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by yanjw on 2018-06-17.
 */
public class BusinessResult {
    private static Action action = new Action();
    private static DeliverDao dao = new DeliverDao();
    private JTable table;

    public BusinessResult(List<Restaurant> restaurants){
        JFrame frame = new JFrame("Order Restaurant");
        frame.setSize(new Dimension(950, 400));
        Vector<String> columns = new Vector<>(Arrays.asList("Name", "address", "Phone Number","open time", "avg cost"));
        Vector<Vector<Object>> rows = new Vector<>();
        Login l = new Login();
        String username = l.getUserName();
        // System.out.println("username" + username.toString());


        for (Restaurant r : restaurants){
            Vector<Object> v = new Vector<>();
            v.add(r.getRes_name());
            v.add(r.getAddress());
            System.out.println("add"+r.getAddress());
            v.add(r.getRes_phoneNumber());
            v.add(r.getOpen_time());
            v.add(r.getAverage_cost());
            rows.add(v);
        }

        DefaultTableModel tableModel = new DefaultTableModel(rows, columns) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 2 ? Integer.class : String.class;
            }
        };
        table = new JTable(tableModel);

        JScrollPane pane = new JScrollPane(table);
        frame.add(pane, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        table.setCellSelectionEnabled(true);
        ListSelectionModel select2= table.getSelectionModel();
        select2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select2.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String Data = null;
                String resNumber = null;
                int[] row = table.getSelectedRows();
                for (int i = 0; i < row.length; i++) {
                    Data = (String) table.getValueAt(row[i], 0);
                    resNumber = (String) table.getValueAt(row[i], 2);
                }
                System.out.println("You have selected restaurant: " + Data);
                //System.out.println(username);
                // add deliver
                List<Carrier> carriers = action.getCarrier();
                Carrier c = carriers.get(0);
                System.out.println("carrier" + c.getCarrier_name());
                String Cname = null;
                if (c!=null){
                    Cname = c.getCarrier_name();
                }
                Deliver d = new Deliver();
                d.setRes_name(Data);
                d.setRes_phoneNumber(resNumber);
                d.setCarrier_name(Cname);
                d.setUsername(username);
                //if (dao.addDeliver(d)){
                 //   action.delCarrier(c);
                dao.addDeliver(d);
                    JOptionPane.showMessageDialog(null, "You have selected restaurant: " + Data.toString());
                //}else {
                 //   JOptionPane.showMessageDialog(null, "Order fail, you have order this restaurant");
               // }
            }
        });
    }

}
