package gui;

import Jdbc.action.Action;
import Jdbc.reserve.Reserve;
import Jdbc.reserve.ReserveDao;
import Jdbc.restaurant.Restaurant;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class reserveResult {
    private static Action action = new Action();
    private static ReserveDao dao = new ReserveDao();
    private JTable table;

    public reserveResult(List<Restaurant> restaurants){
        JFrame frame = new JFrame("Reserved Restaurant");
        frame.setSize(new Dimension(1000, 400));
        Vector<String> columns = new Vector<>(Arrays.asList("Reserve Name", "address", "Phone Number","open time", "avg cost"));
        Vector<Vector<Object>> rows = new Vector<>();

        JTextField serchField = getTextHelper.createSearchField(table);
        JPanel jp = new JPanel();
        jp.add(serchField);
        frame.add(jp, BorderLayout.NORTH);

        //restaurants = action.getRestaurants();
        Login l = new Login();
        String username = l.getUserName();

        for (Restaurant r : restaurants){
            Vector<Object> v = new Vector<>();
            v.add(r.getRes_name());
            v.add(r.getAddress());
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
                String resName = null;
                String resNumber = null;
                int resTime = Integer.parseInt(serchField.getText().trim());
                System.out.println("time"+resTime+ "???");

                int[] row = table.getSelectedRows();
                for (int i = 0; i < row.length; i++) {
                    resName = (String) table.getValueAt(row[i], 0);
                    resNumber = (String) table.getValueAt(row[i], 2);
                }

                Reserve reserve = new Reserve();
                reserve.setRes_name(resName.trim());
                reserve.setRes_phoneNumber(resNumber.trim());
                reserve.setReserve_time(resTime);
                reserve.setUsername(username);
                dao.addReserve(reserve);
                JOptionPane.showMessageDialog(null, "You have a reservation at: " + resName.trim()
                        + ", at time: " + serchField.getText().trim());
            }
        });
    }
}


