package console;

import model.Order;
import model.Products;
import repository.Implementation.OrderDAO;
import repository.Implementation.OrderRepository;
import repository.Implementation.ProductDAOImpl;
import repository.Implementation.ProductsRepository;
import service.*;
import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OrderConsole {
    private final Scanner sc = new Scanner(System.in);
    private final OrderRepository repo = new OrderRepository();
    private final OrderDAO drepo = new OrderDAO();
    private final ProductDAOImpl prepo = new ProductDAOImpl();
    private Checkout checkout = new Checkout();
    File file = new File("reciept.txt");


    public void start(){

        while(true) {
            try{
                System.out.println("=================================================");
                System.out.println("       WELCOME TO ASHIKA'S ONLINE MART🛒       ");
                System.out.println("===================================================");
                System.out.println("1.Add item to cart🛒");
                System.out.println("2.Remove an item");
                System.out.println("3.View Cart History");
                System.out.println("4.View All Products");
                System.out.println("5.Checkout");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                switch (choice) {
                    case 1: {
                        try {
                            System.out.println("==========AVAILABLE PRODUCTS=========");
                            List<Products> products=prepo.getAllProducts();
                            for (Products product : products) {
                                System.out.println(product);
                            }
                            System.out.println("Enter item name");
                            String itemName = sc.next();
                            Products product = prepo.findProductsbyName(itemName);
                            if(product == null){
                                System.out.println("Product out of stock😢. Press 4 to view available products.");
                                break;
                            }
                            System.out.println("No. of items:  ");
                            int totalItems = sc.nextInt();
                            Order order = new Order(product, totalItems);
                            drepo.addOrder(order);
                            System.out.println("SUCESS! Item added to cart");
                            System.out.println("=========CART Updated=========");
                            List<Order> orders = drepo.getAllOrder();
                            if(orders==null){
                                System.out.println("There is no items in the cart🛒.Click 1 to make a order");
                                break;
                            }
                            for (Order order1 : orders) {
                                System.out.println(order1 + "\n");
                            }
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid entry!! Enter a number");
                            sc.nextLine();
                        }
                    }

                    case 2: {
                        try {
                            List<Order> orders = drepo.getAllOrder();
                            if(orders==null){
                                System.out.println("There is no items in the cart🛒.Click 1 to make a order");
                                break;
                            }
                            for (Order order1 : orders) {
                                System.out.println(order1 + "\n");
                            }
                            System.out.println("Enter the Id to remove order");
                            int id = sc.nextInt();
                            drepo.removeOrder(id);
                            System.out.println("=========CART Updated=========");
                            orders=drepo.getAllOrder();
                            for (Order order1 : orders) {
                                System.out.println(order1);
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
                            System.out.println("=====CART HISTORY=====");
                            List<Order> orders = drepo.getAllOrder();
                            if(orders==null){
                                System.out.println("There is no items in the cart🛒.Click 1 to make a order");
                                break;
                            }
                            for (Order order : orders) {
                                System.out.println(order + "\n");

                            }
                            break;
                        }
                        catch (Exception e){
                            System.out.println("An error occured while listing orders" + e.getMessage());
                        }
                    }

                    case 4:{
                        try {
                            System.out.println("===========AVAILABLE PRODUCTS============" );
                            List<Products> products = prepo.getAllProducts();
                            if(products== null){
                                System.out.println("There are no products added! Reach out in 10 minutes!");
                                break;
                            }
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
                        List<Order> orders = new ArrayList<Order>();
                        orders= drepo.getAllOrder();
                        double TotalPrice = checkout.calcualteTotalPrice();
                        System.out.println("Total amount to be paid:"+ TotalPrice);
                        System.out.println("1.Pay via esewa");
                        System.out.println("2.Pay via Khalti");
                        int paymentChoice=sc.nextInt();
                        Reciept reciept=new Reciept();
                        switch (paymentChoice){
                            case 1: {
                                PaymentGateway payment = new Esewa(TotalPrice);
                                payment.pay();
                                break;
                            }
                            case 2:{
                                PaymentGateway payment = new Khalti(TotalPrice);
                                payment.pay();
                                break;
                            }
                            default:{
                                System.out.println("Invalid Entry");
                                break;
                            }
                        }
                        System.out.println("Generate Reciept? (y/n):");
                        char reciept_choice = sc.next().charAt(0);
                        if(reciept_choice=='y'|| reciept_choice == 'Y'){
                            reciept.recieptGen(orders, TotalPrice);
                            System.out.println("RECIEPT HAS BEEN GENERATED!");
                            System.out.println("\nTHANK YOU FOR VISITING! SEE YOU NEXT TIME😊");
                        }
                        else{
                            System.out.println("Transaction Succeeded!");
                        }
                        break;
                    }

                    case 6: {
                        System.out.println("Exiting....");
                        return;
                    }

                    default:{
                        System.out.println("Invalid Entry! Enter number from 1 to 6.");
                    }
                }
            }
            catch (Exception e){
                System.out.println("Something went wrong"+ e.getMessage()+"Due to"+e.getCause());

            }
        }


    };
}
