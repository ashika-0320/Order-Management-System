package repository;

import model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
List<Order> orders= new ArrayList<Order>();

public void addOrder (Order newOrder){
    orders.add(newOrder);
    System.out.println("Order saved successfully");
}

public Order findById(int id){
    for (Order order : orders){
        if(order.getId()==id) {
            return order;
        }
    }
    return null;
}

public List<Order> getAllOrder(){
    return orders;
}
}
