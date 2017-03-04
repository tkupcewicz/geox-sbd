package GraphicalInterface;

import General.UserHandler;
import Objects.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by Tymek on 04.03.2017.
 */
public class RegisterWindow extends Window {
    private static RegisterWindow instance;

    private JTextField loginField;
    private JTextField emailField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField addressField;
    private JTextField postalField;
    private JTextField phoneField;
    private JButton registerButton;
    private JPanel outerPanel;
    private JTextField firstField;
    private JTextField secondField;
    private JButton cancelButton;

    static RegisterWindow getInstance() {
        if (instance == null){
            instance = new RegisterWindow();
        }
        return instance;
    }

    public RegisterWindow(){
        instance = this;
        this.createWindow("Register", this.outerPanel, 300, 450);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String val = validateFields();
                if(val.length() == 0){
                    Boolean ret = UserHandler.getInstance().registerUser(new User(loginField.getText(),
                            String.valueOf(passwordField1.getPassword()),
                            firstField.getText(), secondField.getText(), emailField.getText(),
                            addressField.getText(), postalField.getText(), phoneField.getText(), false));
                    if(ret){
                        getInstance().getjFrame().setVisible(false);
                        getInstance().getjFrame().dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(outerPanel, "That login is already taken.",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                }
                else {
                    JOptionPane.showMessageDialog(outerPanel, val,
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }


            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getInstance().getjFrame().setVisible(false);
                getInstance().getjFrame().dispose();
            }
        });
    }

    public static void main(String[] args) {
        RegisterWindow registerWindow = new RegisterWindow();
    }

    private String validateFields(){
        String ret = "";
        if(loginField.getText().length() < 5) ret += "Login should be at least 5 characters long.\n";
        if(!Arrays.equals(passwordField1.getPassword(), passwordField2.getPassword())){
            ret += "Passwords doesn't match each other.\n";
        }
        if(passwordField1.getPassword().length < 8) ret += "Password should be at least 8 characters long.\n";
        if(firstField.getText().length() <= 0) ret += "First name field can't be empty.\n";
        if(secondField.getText().length() <= 0) ret += "Second name field can't be empty.\n";
        if(emailField.getText().length() <= 0) ret += "Email field can't be empty.\n";
        if(addressField.getText().length() <= 0) ret += "Address field can't be empty.\n";
        if(postalField.getText().length() != 5) ret += "Postal code should be 5 characters long..\n";

        return ret;
    }
}
