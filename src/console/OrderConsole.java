package console;

import model.Order;
import model.Products;
import repository.Implementation.OrderDAO;
import repository.Implementation.OrderRepository;
import repository.Implementation.ProductDAOImpl;
import repository.Implementation.ProductsRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OrderConsole {
    private final Scanner sc = new Scanner(System.in);
    private final OrderRepository repo = new OrderRepository();
    private final OrderDAO drepo = new OrderDAO();
    private final ProductDAOImpl prepo = new ProductDAOImpl();

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
                System.out.println("Choice from order console"+choice);
                switch (choice) {
                    case 1: {
                        try {
                            System.out.println("Enter item name");
                            String itemName = sc.next();
                            Products product = prepo.findProductsbyName(itemName);
                            if(product == null){
                                System.out.println("Product out of stock!!!😢");
                                break;
                            }
                            System.out.println("Enter total ");
                            int totalItems = sc.nextInt();
                            Order order = new Order(product, totalItems);
                            drepo.addOrder(order);
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
                            Order order = drepo.findById(id);
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
                            List<Order> orders = drepo.getAllOrder();
                            if(orders==null){
                                System.out.println("There is no items in the cart🛒.Click 1 to make a order");
                                break;
                            }
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
                            List<Products> products = prepo.getAllProducts();
                            if(products== null){
                                System.out.println("There are no products added! Reach out in 10 minutes!");
                                break;
                            }
                            System.out.println(products);
                            break;
                        }
                        catch (Exception e){
                            System.out.println("An error occured while listing orders" + e.getMessage());
                        }
                    }

                    case 5: {
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
