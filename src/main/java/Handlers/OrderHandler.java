package Handlers;

import General.OrderDaoImpl;
import Interfaces.OrderDao;
import Objects.Order;

import java.util.ArrayList;

/**
 * Created by Tymek on 06.03.2017.
 */
public class OrderHandler {
    private static OrderHandler ourInstance = new OrderHandler();

    public static OrderHandler getInstance() {
        return ourInstance;
    }

    private OrderDao orderDao;
    private ArrayList<Order> orders;

    private OrderHandler() {
        this.orderDao = new OrderDaoImpl();
    }

    public ArrayList<Order> getUserOrders(String user_login){
        this.orders = this.orderDao.getUserOrders(user_login);
        return orders;
    }

    public int createOrder(Order order){
        return this.orderDao.createOrder(order);
    }

    public Boolean createProductOnOrder(int orderId, int productId, String price){
        return orderDao.createProductOnOrder(orderId, productId, price);
    }

    public Boolean updateOrderValue(int number, String value){
        return orderDao.updateValue(value,number);
    }
}
