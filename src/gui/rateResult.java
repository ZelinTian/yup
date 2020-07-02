package gui;

import Jdbc.action.Action;
import Jdbc.restaurant.Restaurant;
import Jdbc.review.Review;
import Jdbc.review.ReviewDao;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class rateResult {
    private static Action action = new Action();
    private static ReviewDao dao = new ReviewDao();
    private JTable table;


    public rateResult(List<Restaurant> restaurants) {
        JFrame frame = new JFrame("Restaurant's rate");
        frame.setSize(new Dimension(1000, 400));
        Vector<String> columns = new Vector<>(Arrays.asList("Name", "address", "Phone Number", "open time", "avg cost"));
        Vector<Vector<Object>> rows = new Vector<>();

        JTextField serchField = getTextHelper.createSearchField(table);
        serchField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!((c >= '0') && (c <= '9') || (c ==KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {

                    e.consume();

                }

            }

        });

        JPanel jp = new JPanel();
        jp.add(serchField);
        frame.add(jp, BorderLayout.NORTH);

        //restaurants = action.getRestaurants();
        Login l = new Login();
        String username = l.getUserName();

        for (Restaurant r : restaurants) {
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
        ListSelectionModel select2 = table.getSelectionModel();
        select2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select2.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String resName = null;
                String resNumber = null;
                int userId = (int) (Math.random()*1000000);
                int userRate = Integer.parseInt(serchField.getText());
                System.out.println("time"+userRate + "!!!");
                int[] row = table.getSelectedRows();
                for (int i = 0; i < row.length; i++) {
                    resName = (String) table.getValueAt(row[i], 0);
                    resNumber = (String) table.getValueAt(row[i], 2);
                    /*
                    userId = (int) table.getValueAt(row[i], 3);
                    userRate = (int) table.getValueAt(row[i], 4);*/
                }

                System.out.println("You have selected restaurant: " + resName);


                Review review = new Review();
                review.setRes_name(resName);
                review.setRes_phoneNumber(resNumber);
                review.setId(userId);
                review.setRating(userRate);
                review.setUsername(username);
                dao.addReview(review);
                JOptionPane.showMessageDialog(null, "You rated: " + resName.trim() + userRate);
            }
        });
    }
}






