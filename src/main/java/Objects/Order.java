package Objects;

import java.util.Date;

/**
 * Created by Tymek on 06.03.2017.
 */
public class Order {
    private int number;
    private String date;
    private String value;
    private String user;

    public Order(int number, String date, String value, String user){
        this.number = number;
        this.date = date;
        this.value = value;
        this.user = user;
    }

    public Object[] toObject(){
        return new Object[]{this.number, this.date, this.value, this.user};
    }

    public int getNumber() {
        return this.number;
    }

    public String getDate() {
        return this.date;
    }

    public String getValue() {
        return this.value;
    }

    public String getUser() {
        return this.user;
    }
}
