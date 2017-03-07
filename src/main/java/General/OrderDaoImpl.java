package General;

import Database.PostgresDB;
import Database.Query;
import GraphicalInterface.Categories;
import Interfaces.OrderDao;
import Objects.Order;
import Objects.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Tymek on 06.03.2017.
 */
public class OrderDaoImpl implements OrderDao {

    @Override
    public ArrayList<Order> getUserOrders(String userLogin) {
        String query = Query.getUserOrders(userLogin);
        ResultSet rs = PostgresDB.getInstance().select(query).getSet();
        ArrayList<Order> orders = new ArrayList<>();
        try {
            while(rs.next()){
                orders.add(new Order(
                        rs.getInt("number"),
                        rs.getDate("date").toString(),
                        rs.getString("value"),
                        rs.getString("user_login")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }


    @Override
    public int createOrder(Order order) {
        String query = Query.createOrder(order.getUser(), order.getDate(), order.getValue());
        ResultSet rs = PostgresDB.getInstance().select(query).getSet();
        ArrayList<Order> orders = new ArrayList<>();
        int ret = -1;
        try {
            while(rs.next()){
                ret = rs.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Boolean createProductOnOrder(int orderId, int productId, String price) {
        String query = Query.createProductOnOrder(orderId, productId, price);
        try {
            PostgresDB.getInstance().insert(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateValue(String value, int number) {
        String query = Query.updateOrderValue(number, value);
        try {
            PostgresDB.getInstance().update(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
