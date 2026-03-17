package repository.Interface;

import model.Order;

import java.util.List;


public interface OrderDAOInterface {
        public void addOrder(Order order);
        public Order findById(int id);
        public List<Order> getAllOrder();
}

