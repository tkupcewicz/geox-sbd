package GraphicalInterface;

import Database.PostgresDB;
import General.ProductsHandler;
import General.UserHandler;
import Objects.Product;
import Objects.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private static String[] tableColumnNames = {"Tytuł", "Gatunek", "Cena"};

    private User activeUser;


    public MainWindow() {
        super();
        this.createWindow("Sklep Muzyczny", this.panel1, 900, 450);
        this.loggedPanel.setVisible(false);
        this.setTableModel(this.productsTable, tableColumnNames);
        this.activeUser = null;

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

                }
                else{
                    JOptionPane.showMessageDialog(panel1, "Login or password is incorrect.",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterWindow();
            }
        });
    }


    public void getPlyty() {

        String SQL = "SELECT title, genre, img FROM products";

    try (Connection conn = PostgresDB.getInstance().connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL)) {
        displayPlyty(rs);
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    private void displayPlyty(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString("title") + "\t"
                    + rs.getString("genre") + "\t"
                    + rs.getString("img"));
        }
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
    }

}
