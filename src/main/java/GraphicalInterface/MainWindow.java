package GraphicalInterface;

import Handlers.ProductsHandler;
import Handlers.UserHandler;
import Objects.Product;
import Objects.User;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * Created by Tymek on 16.01.2017.
 */

public class MainWindow extends TableWindow {
    JPanel panel1;
    private JTextField loginField;
    private JPanel loginPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JTable productsTable;
    private JList categoriesList;
    private JPasswordField passwordField1;
    private JButton myAccountButton;
    private JButton logoutButton;
    private JButton manageButton;
    private JPanel loggedPanel;
    private static String[] tableColumnNames = {"Id", "Title", "Genre", "Price"};

    private User activeUser;

    private static MainWindow instance = new MainWindow();

    static MainWindow getInstance() {
        if (instance == null){
            instance = new MainWindow();
        }
        return instance;
    }

    public MainWindow() {
        super();
        this.createWindow("Music Store", this.panel1, 900, 450);
        this.loggedPanel.setVisible(false);
        this.setTableModel(this.productsTable, tableColumnNames);
        this.activeUser = null;
        this.manageButton.setVisible(false);


        try {
            this.setProductRows(ProductsHandler.getInstance().getAllProducts()
                    .stream()
                    .map(Product::toObject)
                    .collect(Collectors.toCollection(ArrayList::new)));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Categories> lista_kategorii = new ArrayList<Categories>(Arrays.asList(Categories.values()));

        this.categoriesList.setListData(lista_kategorii.toArray());

        this.loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(UserHandler.getInstance().verifyUser(MainWindow.this.loginField.getText(),
                        String.valueOf(MainWindow.this.passwordField1.getPassword()))){
                    MainWindow.this.loginPanel.setVisible(false);
                    MainWindow.this.loggedPanel.setVisible(true);
                    MainWindow.this.activeUser = UserHandler.getInstance().getUser(loginField.getText());
                    if(activeUser.getManager()) MainWindow.this.manageButton.setVisible(true);
                    loginField.setText("");
                    passwordField1.setText("");
                }
                else{
                    JOptionPane.showMessageDialog(panel1, "Login or password is incorrect.",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                    passwordField1.setText("");
                }
            }
        });

        this.registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterWindow.getInstance();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.this.activeUser = null;
                MainWindow.this.loginPanel.setVisible(true);
                MainWindow.this.loggedPanel.setVisible(false);
                MainWindow.this.manageButton.setVisible(false);
            }
        });
        productsTable.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 2) {
//                    System.out.println(productsTable.getValueAt(productsTable.getSelectedRow(), 0).toString());
//                    new ProductWindow((Integer) productsTable.getValueAt(productsTable.getSelectedRow(), 0));
                    ProductWindow.getInstance();
                    ProductWindow.getInstance().updateProductWindow(
                            (Integer) productsTable.getValueAt(productsTable.getSelectedRow(), 0)
                    );
                }
            }
        });
        categoriesList.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 2) {
//                    System.out.println(categoriesList.getSelectedValue().toString());
                    updateTable((Categories) categoriesList.getSelectedValue());
                }
            }
        });
    }

    public User getActiveUser() {
        return this.activeUser;
    }

    public static void main(String[] args) {
        MainWindow.getInstance();
    }

    private void updateTable(Categories cat) {
        this.setRows(ProductsHandler.getInstance().filterProducts(cat)
                .stream()
                .map(Product::toObject)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

}
