package GraphicalInterface;

import Handlers.OrderHandler;
import Handlers.ProductsHandler;
import Handlers.UserHandler;
import Objects.Order;
import Objects.Product;
import Objects.User;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.objects.NativeMath.round;


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
    private static String[] orderColumnNames = {"Number", "Date", "Value"};

    private User activeUser;
    private ArrayList<Product> cartList;

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
                    MainWindow.this.cartList = new ArrayList<Product>();

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
                clearCartList();
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
        myAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserWindow.getInstance();
            }
        });
        manageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageWindow.getInstance();
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

    public void setActiveUser(User user){
        this.activeUser = user;
    }

    public void clearCartList(){
        this.cartList.clear();
    }

    public void addProductToCart(int id){
        cartList.add(ProductsHandler.getInstance().getProduct(id));
    }

    public ArrayList<Product> getCartList(){
        return cartList;
    }

    public static String[] getTableColumnNames() {
        return MainWindow.tableColumnNames;
    }

    public static String[] getOrderColumnNames() {
        return MainWindow.orderColumnNames;
    }

    public String getCartSum(){
        double sum = 0.0f;
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
//        formatter.format
        for(int i = 0; i < cartList.size(); i++){
            sum += Double.parseDouble(cartList.get(i).getPrice().replaceAll(",",".").substring(3));
        }
        String ret = String.valueOf((double) Math.round(sum * 100) / 100);
        return ret.replaceAll("\\.",",");
    }

    // TODO:
//    2. dodac okno manage store, dodawanie artystow, plyt, zmiana statusu zamowien
//    3. dodac obsluge ilosci produktow
//    4. Obejsc czyszczenie tabeli koszyka i update zamowien poprzez zamykanie okna po wykonaniu transakcji z koszykiem
}
