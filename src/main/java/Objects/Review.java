package Objects;

import java.util.Date;

/**
 * Created by Tymek on 05.03.2017.
 */
public class Review {
    private String date;
    private String content;
    private int productId;
    private String userLogin;

    public Review(String date, String content, int productId, String userLogin){
        this.date = date;
        this.content = content;
        this.productId = productId;
        this.userLogin = userLogin;
    }

    public String getDate() {
        return this.date;
    }

    public String getContent() {
        return this.content;
    }

    public int getProductId() {
        return this.productId;
    }

    public String getUserLogin() {
        return this.userLogin;
    }
}
