package console;

import model.Order;
import repository.OrderRepository;

import java.util.List;
import java.util.Scanner;

public class OrderConsole {
    private Scanner sc = new Scanner(System.in);
    private OrderRepository repo = new OrderRepository();

    public void start(){

        while(true) {
            System.out.println("========== Make an order ==========");
            System.out.println("1.Create Order");
            System.out.println("2.Find Item By Id");
            System.out.println("3.View All Orders in Cart");

            int choice = sc.nextInt();

            switch(choice){
                case 1: {
                    System.out.println("Enter item name");
                    String itemName = sc.next();
                    System.out.println("Enter product id");
                    int productId = sc.nextInt();
                    System.out.println("Enter total items");
                    int totalItems = sc.nextInt();
                    Order order = new Order(itemName,productId,totalItems);
                    repo.addOrder(order);
                    break;
                }

                case 2:{
                    System.out.println("Enter the Id to find order");
                    int id= sc.nextInt();
                    Order order=repo.findById(id);
                    if(order!=null){
                        System.out.println(order);
                    }
                    else {
                        System.out.println("Item not found!!!😢");
                    }
                    break;
                }

                case 3: {
                    System.out.println("=====Total Orders=====");
                    List<Order> orders=repo.getAllOrder();
                    for(Order order: orders){
                        System.out.println(order);
                    }
                    break;
                }
            }
        }


    };
}
