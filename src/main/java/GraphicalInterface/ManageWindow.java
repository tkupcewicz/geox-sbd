package GraphicalInterface;

import Handlers.ProductsHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tymek on 07.03.2017.
 */
public class ManageWindow extends  Window{

    private static ManageWindow instance = new ManageWindow();

    private JPanel panel1;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField genreField;
    private JTextField productDateField;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextField imgField;
    private JButton addProductButton;
    private JButton cancelButton;
    private JTextField aristNameField;
    private JTextField artistDateField;
    private JTextArea descriptionField;
    private JButton addArtistButton;
    private JButton cancelButton1;
    private JButton closeButton;

    static ManageWindow getInstance() {
        if (instance == null){
            instance = new ManageWindow();
        }
        instance.getjFrame().toFront();
        return instance;
    }

    public ManageWindow() {
        instance = this;
        this.createWindow("Manage", this.panel1, 600, 400);
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean ret = ProductsHandler.getInstance().createProduct(
                        titleField.getText(),
                        genreField.getText().toUpperCase(),
                        productDateField.getText(),
                        authorField.getText(),
                        imgField.getText(),
                        Integer.parseInt(quantityField.getText()),
                        priceField.getText());
                if(ret){
                    JOptionPane.showMessageDialog(panel1, "Successfully created product.");
                    clearProductField();
                }
                else {
                    JOptionPane.showMessageDialog(panel1,
                            "Couldn't create product.",
                            "Warning", JOptionPane.WARNING_MESSAGE);

                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearProductField();
            }
        });
        addArtistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean ret = ProductsHandler.getInstance().createArtist(
                        aristNameField.getText(),
                        artistDateField.getText(),
                        descriptionField.getText());
                if(ret){
                    JOptionPane.showMessageDialog(panel1, "Successfully created artist.");
                    clearArtistField();
                }
                else {
                    JOptionPane.showMessageDialog(panel1,
                            "Couldn't create artist.",
                            "Warning", JOptionPane.WARNING_MESSAGE);

                }
            }
        });
        cancelButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearArtistField();
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instance.getjFrame().setVisible(false);
                instance.getjFrame().dispose();
                instance = null;
            }
        });
    }

    public static void main(String[] args) {
        getInstance();
    }

    public void clearProductField(){
        titleField.setText("");
        genreField.setText("");
        productDateField.setText("");
        authorField.setText("");
        imgField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }

    public void clearArtistField(){
        aristNameField.setText("");
        artistDateField.setText("");
        descriptionField.setText("");
    }

}
