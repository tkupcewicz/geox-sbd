package GraphicalInterface;

import javax.swing.*;

/**
 * Created by Tymek on 04.03.2017.
 */
public class ProductWindow extends Window{

    private static ProductWindow instance;

    private JPanel imagePanel;
    private JLabel priceLabel;
    private JLabel titleLabel;
    private JLabel genreLabel;
    private JLabel authorLabel;
    private JLabel dateLabel;
    private JLabel quantityLabel;
    private JTextField textField1;
    private JButton orderButton;
    private JButton cancelButton;
    private JPanel outerPanel;

    static ProductWindow getInstance() {
        if (instance == null){
            instance = new ProductWindow();
        }
        return instance;
    }

    ProductWindow(){
        instance = this;
        this.createWindow("Product view", this.outerPanel, 500, 550);
    }

    public static void main(String[] args) {
        ProductWindow productWindow = new ProductWindow();
    }
}
