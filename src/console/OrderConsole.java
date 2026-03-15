package console;

import model.Order;
import model.Products;
import repository.OrderRepository;
import repository.ProductsRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OrderConsole {
    private final Scanner sc = new Scanner(System.in);
    private final OrderRepository repo = new OrderRepository();
    private final ProductsRepository prepo = new ProductsRepository();

    public void start(){

        while(true) {
            try{
                System.out.println("========== Make an order ==========");
                System.out.println("1.Create Order");
                System.out.println("2.Find Item By Id");
                System.out.println("3.View All Orders in Cart");
                System.out.println("4. View All Products");
                System.out.println("5. Exit");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1: {
                        try {
                            System.out.println("Enter item name");
                            String itemName = sc.next();
                            System.out.println("Enter product id");
                            int productId = sc.nextInt();
                            System.out.println("Enter total items");
                            int totalItems = sc.nextInt();
                            Order order = new Order(itemName, productId, totalItems);
                            repo.addOrder(order);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid entry!! Enter a number");
                            sc.nextLine();
                        }
                    }

                    case 2: {
                        try {
                            System.out.println("Enter the Id to find order");
                            int id = sc.nextInt();
                            Order order = repo.findById(id);
                            if (order != null) {
                                System.out.println(order);
                            } else {
                                System.out.println("Item not found!!!😢");
                            }
                            break;
                        }
                        catch (InputMismatchException e){
                            System.out.println("Invalid id!! Enter a number");
                            sc.nextLine();
                        }
                        catch (Exception e){
                            System.out.println("An error occured while removing product" + e.getMessage());
                        }
                    }

                    case 3: {
                        try {
                            System.out.println("=====Total Orders=====");
                            List<Order> orders = repo.getAllOrder();
                            for (Order order : orders) {
                                System.out.println(order);
                            }
                            break;
                        }
                        catch (Exception e){
                            System.out.println("An error occured while listing orders" + e.getMessage());
                        }
                    }

                    case 4:{
                        try {
                            System.out.println("=====All Products=====");
                            List<Products> products = prepo.getAllProducts();
                            for (Products product : products) {
                                System.out.println(product);
                            }
                            break;
                        }
                        catch (Exception e){
                            System.out.println("An error occured while listing orders" + e.getMessage());
                        }
                    }

                    case 5:{
                        System.out.println("Exiting....");
                        return;
                    }
                }
            }
            catch (Exception e){
                System.out.println("Something went wrong"+ e.getMessage()+"Due to"+e.getCause());

            }
        }


    };
}
