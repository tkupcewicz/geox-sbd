package GraphicalInterface;

import Handlers.OrderHandler;
import Handlers.ProductsHandler;
import Handlers.UserHandler;
import Objects.Order;
import Objects.Product;
import Objects.User;
import sun.applet.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Tymek on 06.03.2017.
 */
public class UserWindow extends TableWindow {
    private JPanel outerPanel;
    private JButton closeButton;
    private JTable ordersTable;
    private JButton editButton;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField addressField;
    private JTextField firstNameField;
    private JTextField secondNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField postalCodeField;
    private JTable cartTable;
    private JButton orderButton;
    private JButton cancelButton1;
    private JTextField loginField;

    private static UserWindow instance = new UserWindow();




    static UserWindow getInstance() {
        if (instance == null){
            instance = new UserWindow();
        }
        instance.getjFrame().toFront();
        return instance;
    }

    UserWindow() {
        instance = this;
        loadUser();
        this.setTableModel(this.cartTable, MainWindow.getTableColumnNames());
        this.setTableModel2(this.ordersTable, MainWindow.getOrderColumnNames());
        this.updateCartTable();
        this.createWindow("My account", this.outerPanel, 600, 400);

        this.setOrderRows(OrderHandler.getInstance().getUserOrders(MainWindow.getInstance().getActiveUser().getLogin())
                .stream()
                .map(Order::toObject)
                .collect(Collectors.toCollection(ArrayList::new)));

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instance.closeWindow();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getInstance().enableEditMode();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getInstance().disableEditMode();
                loadUser();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getInstance().disableEditMode();


                String val = validateFields();
                if(val.length() == 0){
                    getInstance().disableEditMode();
                    Boolean ret = UserHandler.getInstance().updateUser(emailField.getText(), firstNameField.getText(),
                            secondNameField.getText(), addressField.getText(), postalCodeField.getText(),
                            phoneField.getText(), loginField.getText());
                    if(ret){
                        JOptionPane.showMessageDialog(outerPanel, "Successfully updated user info.",
                                "Success", JOptionPane.INFORMATION_MESSAGE);

                        User updatedUser = new User(MainWindow.getInstance().getActiveUser().getLogin(),
                                MainWindow.getInstance().getActiveUser().getPassword(),
                                firstNameField.getText(),
                                secondNameField.getText(),
                                emailField.getText(),
                                addressField.getText(),
                                postalCodeField.getText(),
                                phoneField.getText(),
                                MainWindow.getInstance().getActiveUser().getManager());
                        MainWindow.getInstance().setActiveUser(updatedUser);
                    }
                    else{
                        JOptionPane.showMessageDialog(outerPanel, "Couldn't update user info.",
                                "Warning", JOptionPane.ERROR_MESSAGE);
                    }
                    getInstance().loadUser();
                }
                else {
                    JOptionPane.showMessageDialog(outerPanel, val,
                            "Warning", JOptionPane.WARNING_MESSAGE);
                    getInstance().enableEditMode();
                }
            }
        });
        cancelButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.getInstance().clearCartList();
                cartTable.repaint();
                JOptionPane.showMessageDialog(outerPanel, "Canceled cart successfully.",
                        "Information", JOptionPane.INFORMATION_MESSAGE);
                instance.closeWindow();
            }
        });
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean isCreated;
                ArrayList<Product> notAdded = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                java.util.Date date = new java.util.Date();
                Order order = new Order(-1, dateFormat.format(date), MainWindow.getInstance().getCartSum(),
                        MainWindow.getInstance().getActiveUser().getLogin());
                int orderId = OrderHandler.getInstance().createOrder(order);
                if(orderId == -1){
                    JOptionPane.showMessageDialog(outerPanel, "Something went wrong, couldn't create order.",
                            "Warning", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    for(int i = 0; i < MainWindow.getInstance().getCartList().size(); i++){
                        Product product = MainWindow.getInstance().getCartList().get(i);
                        if(ProductsHandler.getInstance().reduceProductAmount(product.getId())){
                            OrderHandler.getInstance().createProductOnOrder(orderId, product.getId(), product.getPrice());
                        }
                        else{
                            notAdded.add(product);
                        }
                    }
                    if(notAdded.size() > 0){
                        String canceledProducts = "";
                        for(int k = 0; k < notAdded.size(); k++){
                            MainWindow.getInstance().getCartList().remove(notAdded.get(k));
                            canceledProducts += notAdded.get(k).toString() + "\n";
                        }
                        JOptionPane.showMessageDialog(outerPanel, "Some product were removed from" +
                                        " order because they're out of stock: \n" + canceledProducts,
                                "Warning", JOptionPane.ERROR_MESSAGE);

                    }
                    if(MainWindow.getInstance().getCartList().size() > 0){
                        JOptionPane.showMessageDialog(outerPanel, "Successfully created order. No: " + orderId);
                    }
                    else{
                        JOptionPane.showMessageDialog(outerPanel, "Cannot create order with empty cart.");
                    }
                    OrderHandler.getInstance().updateOrderValue(orderId, MainWindow.getInstance().getCartSum());
                    MainWindow.getInstance().clearCartList();
                }
            }
        });
    }

    public static void main(String[] args) {
        getInstance();
    }

    public void enableEditMode(){
        this.addressField.setEnabled(true);
        this.firstNameField.setEnabled(true);
        this.secondNameField.setEnabled(true);
        this.emailField.setEnabled(true);
        this.phoneField.setEnabled(true);
        this.postalCodeField.setEnabled(true);
        this.saveButton.setVisible(true);
        this.cancelButton.setVisible(true);
        this.editButton.setVisible(false);
    }

    public void disableEditMode() {
        this.addressField.setEnabled(false);
        this.firstNameField.setEnabled(false);
        this.secondNameField.setEnabled(false);
        this.emailField.setEnabled(false);
        this.phoneField.setEnabled(false);
        this.postalCodeField.setEnabled(false);
        this.saveButton.setVisible(false);
        this.cancelButton.setVisible(false);
        this.editButton.setVisible(true);
    }

    public void closeWindow(){
        instance.getjFrame().setVisible(false);
        instance.getjFrame().dispose();
        instance = null;
    }

    public void loadUser(){
        this.loginField.setText(MainWindow.getInstance().getActiveUser().getLogin());
        this.addressField.setText(MainWindow.getInstance().getActiveUser().getAddress());
        this.firstNameField.setText(MainWindow.getInstance().getActiveUser().getFirstName());
        this.secondNameField.setText(MainWindow.getInstance().getActiveUser().getSecondName());
        this.emailField.setText(MainWindow.getInstance().getActiveUser().getEmail());
        this.phoneField.setText(MainWindow.getInstance().getActiveUser().getPhoneNumber());
        this.postalCodeField.setText(MainWindow.getInstance().getActiveUser().getPostalCode());

    }

    private String validateFields(){
        String ret = "";
        if(firstNameField.getText().length() <= 0) ret += "First name field can't be empty.\n";
        if(secondNameField.getText().length() <= 0) ret += "Second name field can't be empty.\n";
        if(emailField.getText().length() <= 0) ret += "Email field can't be empty.\n";
        if(addressField.getText().length() <= 0) ret += "Address field can't be empty.\n";
        if(postalCodeField.getText().length() != 5) ret += "Postal code should be 5 characters long..\n";
        return ret;
    }

    public void updateCartTable() {
        if (!(MainWindow.getInstance().getCartList() == null)) {
            this.setProductRows(MainWindow.getInstance().getCartList()
                    .stream()
                    .map(Product::toObject)
                    .collect(Collectors.toCollection(ArrayList::new)));
        }
        else{
            // TODO: Fix clearing Cart Table after cancelling order.
            this.clearProductRows();
        }
    }

}
