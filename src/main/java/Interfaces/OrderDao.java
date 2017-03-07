package Interfaces;

import Objects.Order;

import java.util.ArrayList;

/**
 * Created by Tymek on 06.03.2017.
 */
public interface OrderDao {
    public ArrayList<Order> getUserOrders(String userLogin);
    public int createOrder(Order order);
    public Boolean createProductOnOrder(int orderId, int productId, String price);
    public Boolean updateValue(String value, int number);

}
