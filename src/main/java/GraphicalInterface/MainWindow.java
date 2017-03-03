package GraphicalInterface;

import Database.PostgresDB;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;


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


    public MainWindow() {
        super();
        this.createWindow("Sklep Muzyczny", this.panel1, 900, 450);
        this.loggedPanel.setVisible(false);

        this.setTableModel(this.productsTable, tableColumnNames);
//        this.setRows();
        ArrayList<Categories> lista_kategorii = new ArrayList<Categories>(Arrays.asList(Categories.values()));
        this.categoriesList.setListData(lista_kategorii.toArray());
        this.loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginPanel.setVisible(false);
                loggedPanel.setVisible(true);
            }
        });
    }


    public ResultSet getPlyty() {

        String SQL = "SELECT tytul,gatunek, cena FROM plyty";

    try (Connection conn = PostgresDB.getInstance().connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL)) {
        return rs;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        return null;
    }
}

    private void displayPlyty(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString("Tytul") + "\t"
                    + rs.getString("gatunek") + "\t"
                    + rs.getString("cena"));

        }
    }





    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
    }
    
}
