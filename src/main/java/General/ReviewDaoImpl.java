package General;

import Database.PostgresDB;
import Database.Query;
import GraphicalInterface.Categories;
import Interfaces.ReviewDao;
import Objects.Product;
import Objects.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Tymek on 05.03.2017.
 */
public class ReviewDaoImpl implements ReviewDao {
    @Override
    public ArrayList<Review> getProductReviews(int productId) throws SQLException {
        String query = Query.getProductReviews(productId);
        ResultSet rs = PostgresDB.getInstance().select(query).getSet();
        ArrayList<Review> reviews = new ArrayList<>();
        while(rs.next()){
            reviews.add(new Review(
                    rs.getDate("date").toString(),
                    rs.getString("content"),
                    rs.getInt("product_id"),
                    rs.getString("user")
            ));
        }
        return reviews;
    }

    @Override
    public Review getReview(int id) {
        return null;
    }

    @Override
    public Boolean createReview(Review review) {
        String query = Query.addReviews(review);
        try {
            PostgresDB.getInstance().insert(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
