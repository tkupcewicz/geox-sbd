package GraphicalInterface;

import General.ProductsHandler;
import General.ReviewHandler;
import Objects.Product;
import Objects.Review;
import sun.applet.Main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.LocalDate;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tymek on 04.03.2017.
 */
public class ProductWindow extends Window{

    private static ProductWindow instance = new ProductWindow();
    private Product activeProduct = null;

    private JPanel imagePanel;
    private JPanel outerPanel;
    private JButton orderButton;
    private JButton cancelButton;
    private JLabel titleLabel;
    private JLabel authorLabel;
    private JLabel genreLabel;
    private JLabel dateLabel;
    private JLabel quantityLabel;
    private JLabel priceLabel;
    private JLabel imgLabel;
    private JEditorPane reviewsPane;
    private JTextArea reviewArea;
    private JButton submitButton;
    private JPanel submitReviewPanel;
    private JPanel orderPanel;
    private JTextField orderField;

    static ProductWindow getInstance() {
        if (instance == null){
            instance = new ProductWindow();
        }
        instance.getjFrame().toFront();
        return instance;
    }
//        TODO: rysowanie obrazkow
//        produkt view powinien miec przypisany dany produkt, cos jak aktual user dla mainwindow
//        jesli product window wywoluje manager to kazde pole zamiast labela to field juz wypelniony i przycisk update
//        dodawanie do zamowienia, pewnie trzeba bedzie cos wymyslic zeby zamowienie mialo status zlozone/niezlozone itd
//        Ustawic na sztywno rozmiar okna


    ProductWindow(){
        instance = this;
        this.createWindow("Product view", this.outerPanel, 330, 600);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instance.getjFrame().setVisible(false);
                instance.getjFrame().dispose();
                instance = null;
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                Review rev = new Review(dateFormat.format(date), reviewArea.getText(), activeProduct.getId(),
                        MainWindow.getInstance().getActiveUser().getLogin());
                ReviewHandler.getInstance().createReview(rev);
                instance.updateProductWindow(activeProduct.getId());
            }

        });
    }


    public static void main(String[] args) {
        ProductWindow productWindow = new ProductWindow();
    }

    public void updateProductWindow(int id){
        if(MainWindow.getInstance().getActiveUser() == null){
            submitReviewPanel.setVisible(false);
            orderPanel.setVisible(false);
        }
        if(activeProduct == null || activeProduct.getId() != id){
            activeProduct = ProductsHandler.getInstance().getProduct(id);
        }
        this.titleLabel.setText(activeProduct.getName());
        this.authorLabel.setText(activeProduct.getPublisher());
        this.genreLabel.setText(String.valueOf(activeProduct.getGenre()));
        this.dateLabel.setText(String.valueOf(activeProduct.getDate()));
        this.quantityLabel.setText(String.valueOf(activeProduct.getQuantity()));
        this.priceLabel.setText(activeProduct.getPrice());
        this.reviewsPane.setText(ReviewHandler.getInstance().productReviewsToString(activeProduct.getId()));


        URL url = null;
        try {
            url = new URL("https://placehold.it/300x300");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        java.awt.Image image = java.awt.Toolkit.getDefaultToolkit().createImage(url);
        this.imgLabel.setIcon(new ImageIcon(image));





    }
}
