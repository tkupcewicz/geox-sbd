package General;

import Interfaces.ReviewDao;
import Objects.Review;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Tymek on 05.03.2017.
 */
public class ReviewHandler {
    private static ReviewHandler ourInstance = new ReviewHandler();
    private ReviewDao reviewDao;

    public static ReviewHandler getInstance() {
        return ourInstance;
    }

    private ReviewHandler() {
        this.reviewDao = new ReviewDaoImpl();

    }

    public String productReviewsToString(int productId){
        String result = "";
        ArrayList<Review> listOfReviews = null;
        try {
            listOfReviews =  reviewDao.getProductReviews(productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(Review rev : listOfReviews){
             result += String.format("Author: %s\nDate: %s\nReview:%s\n\n",
                    rev.getUserLogin(), rev.getDate(), rev.getContent());
        }
        return result;
    }

    public Boolean createReview(Review review){
        return reviewDao.createReview(review);
    }
}
