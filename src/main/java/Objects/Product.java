package Objects;

import GraphicalInterface.Categories;

import java.util.Date;

/**
 * Created by Tymek on 03.03.2017.
 */
public class Product {
    private int id;
    private String name;
    private Categories genre;
    private Date date;
    private String price;
    private String publisher;
    private String image_url;
    private int quantity;

    public Product(int id, String name, Categories genre, Date date,
                   String price, String publisher, String image_url, int quantity) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.date = date;
        this.price = price;
        this.publisher = publisher;
        this.image_url = image_url;
        this.quantity = quantity;
    }

    public Object[] toObject(){
        return new Object[]{this.id, this.name, this.genre, this.date,
                this.price, this.publisher, this.image_url, this.quantity};
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Categories getGenre() {
        return this.genre;
    }

    public Date getDate() {
        return this.date;
    }

    public String getPrice() {
        return this.price;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public int getQuantity() {
        return this.quantity;
    }
}



