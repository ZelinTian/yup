package gui; /**
 * Created by yanjw on 2018-06-12.
 */

//import org.fluttercode.datafactory.impl.DataFactory;

import Jdbc.action.Action;
import Jdbc.restaurant.Restaurant;
import Jdbc.restaurant.RestaurantDao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


public class reserve{

    private JButton buttonApply = new JButton("Apply");
    private JButton buttonMin = new JButton("Get Min Cost");
    private JButton buttonMax = new JButton("Get Max Cost");
    private JButton buttonRate = new JButton("Rate:");
    private JButton buttonAvg = new JButton("AVG Cost:");
    private JButton buttonDiv = new JButton("Division");

    private JTable table;
    private static Action action = new Action();

    public reserve() {
        JFrame frame = createFrame();
        TableModel tableModel = createTableModel();
        table = new JTable(tableModel);


        String[] cTitles = new String[] { "0 - 2", "2 - 4", "4 - 6", "6 - 8",
                "8 - 10"};
        String[] bTitles = new String[] { "0 - 20",
                "20 - 40", "40 - 60", "60 - 80", "80 - 100", "100+"  };

        final JComboBox<String> cList = new JComboBox<String>(cTitles);
        final JComboBox<String> bList = new JComboBox<String>(bTitles);


        // customize some appearance:

        cList.setForeground(Color.BLACK);
        cList.setFont(new Font("Arial", Font.BOLD, 14));
        cList.setMaximumRowCount(100);

        bList.setForeground(Color.BLACK);
        bList.setFont(new Font("Arial", Font.BOLD, 14));
        bList.setMaximumRowCount(100);
        // make the combo box editable so we can add new item when needed
        cList.setEditable(false);
        bList.setEditable(false);

        JTextField serchField = getTextHelper.createSearchField(table);
        JPanel jp = new JPanel();
        jp.add(serchField);
        frame.add(jp, BorderLayout.NORTH);

        jp.add(buttonRate);
        jp.add(cList);
        jp.add(buttonAvg);
        jp.add(bList);
        jp.add(buttonApply);
        jp.add(buttonDiv);
        jp.add(buttonMin);
        jp.add(buttonMax);

        //pack();
        //setLocationRelativeTo(null);

        JScrollPane pane = new JScrollPane(table);
        frame.add(pane, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<String> combo = (JComboBox<String>) event.getSource();
                String selectedBook = (String) combo.getSelectedItem();

                DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) combo
                        .getModel();

                int selectedIndex = model.getIndexOf(selectedBook);
                if (selectedIndex < 0) {
                    model.addElement(selectedBook);
                }

            }
        });


        bList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<String> combo2 = (JComboBox<String>) event.getSource();
                String selectedBList = (String) combo2.getSelectedItem();

                DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) combo2
                        .getModel();

                int selectedIndex2 = model.getIndexOf(selectedBList);
                if (selectedIndex2 < 0) {
                    model.addElement(selectedBList);
                }

            }
        });
        buttonApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String selectedC = (String) cList.getSelectedItem();
                String selectedB = (String) bList.getSelectedItem();
                table.removeAll();
                JOptionPane.showMessageDialog(null,
                        "You selected: " + "Rate: " + selectedC  + ",  " + "Avg Cost: " + selectedB);

                List<Map<String, Object>> conds = new ArrayList<Map<String, Object>>();
                Map<String, Object> cond = new HashMap<String, Object>();
                Map<String, Object> cond1 = new HashMap<String, Object>();
                Map<String, Object> cond2 = new HashMap<String, Object>();
                Map<String, Object> cond3= new HashMap<String, Object>();
                Map<String, Object> cond4 = new HashMap<String, Object>();
                cond.put("name", "average_cost");
                cond.put("relation", ">=");
                cond.put("value",selectedB.split("-")[0]);
                conds.add(cond);
                cond1.put("name", "average_cost");
                cond1.put("relation", "<");
                cond1.put("value",selectedB.split("-")[1]);
                conds.add(cond1);

//                cond3.put("name", "rating");
//                cond3.put("relation", ">=");
//                cond3.put("value",selectedC.split("-")[0]);
//                conds.add(cond3);
//                cond4.put("name", "rating");
//                cond4.put("relation", "<");
//                cond4.put("value",selectedC.split("-")[1]);

                System.out.println(serchField.getText().length());
                if (!serchField.getText().equals("")){
                    cond2.put("name", "res_name");
                    cond2.put("relation", "=");
                    cond2.put("value", " '" + serchField.getText() + "'");
                    conds.add(cond2);
                } else {
                    System.out.println("empty");
                }
                //TODO
                List<Restaurant> restaurantResult = action.Andquery(conds);
                System.out.println(restaurantResult.size());
                reserveResult b = new reserveResult(restaurantResult);
                System.out.println(restaurantResult);
            }
        });

        buttonDiv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                table.removeAll();
//                JOptionPane.showMessageDialog(null,
//                        "You selected: " + "Rate: " + selectedC  + ",  " + "Avg Cost: " + selectedB);



//                cond3.put("name", "rating");
//                cond3.put("relation", ">=");
//                cond3.put("value",selectedC.split("-")[0]);
//                conds.add(cond3);
//                cond4.put("name", "rating");
//                cond4.put("relation", "<");
//                cond4.put("value",selectedC.split("-")[1]);


                //TODO
                List<Restaurant> restaurantResult = action.division();
                System.out.println(restaurantResult.size());
                reserveResult r = new reserveResult(restaurantResult);
                System.out.println(restaurantResult);
            }
        });

        buttonMin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                RestaurantDao rd = new RestaurantDao();
                JOptionPane.showMessageDialog(null, "The minimum cost is:" + rd.getMinCost());
            }
        });

        buttonMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                RestaurantDao rd = new RestaurantDao();
                JOptionPane.showMessageDialog(null, "The maximum cost is:" + rd.getMaxCost());
            }
        });

    }

    private static TableModel createTableModel() {
        Vector<String> columns = new Vector<>(Arrays.asList("Name", "address", "Phone Number","open time", "avg cost", "rating"));
        Vector<Vector<Object>> rows = new Vector<>();

        List<Restaurant> restaurantList = action.getRestaurants();
        for (Restaurant r : restaurantList){
            Vector<Object> v = new Vector<>();
            v.add(r.getRes_name());
            v.add(r.getAddress());
            v.add(r.getRes_phoneNumber());
            v.add(r.getOpen_time());
            v.add(r.getAverage_cost());
            v.add(r.getAvg_rate());
            rows.add(v);
        }

        DefaultTableModel dtm = new DefaultTableModel(rows, columns) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 2 ? Integer.class : String.class;
            }
        };
        return dtm;
    }

    private static JFrame createFrame() {
        JFrame frame = new JFrame("Reserve Restaurant");
        frame.setSize(new Dimension(1100, 400));
        return frame;
    }

}

