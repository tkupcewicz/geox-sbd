package Interfaces;

import Objects.Product;
import Objects.Review;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Tymek on 05.03.2017.
 */
public interface ReviewDao {
    public ArrayList<Review> getProductReviews(int productId) throws SQLException;
    public Review getReview(int id);
    public Boolean createReview(Review review);
}
