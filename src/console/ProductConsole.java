package console;

import model.Order;
import model.Products;
import repository.ProductsRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProductConsole {
    Scanner sc = new Scanner(System.in);
    ProductsRepository repo= new ProductsRepository();

    public void start(){
        loop:
    while(true) {
            System.out.println("====Product Operations====");
            System.out.println("1.Create Product");
            System.out.println("2.Remove Product");
            System.out.println("3.Update Product");
            System.out.println("4.View All Products");
            System.out.println("5.Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    try {
                        System.out.println("enter id");
                        int id = sc.nextInt();
                        System.out.println("enter product name");
                        String productName = sc.next();
                        System.out.println("Enter price");
                        double price = sc.nextDouble();
                        Products product = new Products(id, productName, price);
                        repo.createProduct(product);
                        break;
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid input!! Enter valid number");
                    }
                    catch (Exception e){
                        System.out.println("An error has occurred!!" + e.getMessage());
                    }
                }

                case 2: {
                    try {
                        System.out.println("Enter id of the product to remove");
                        int id = sc.nextInt();
                        repo.removeProduct(id);
                        break;
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid input!! Enter valid number");
                    }
                }

                case 3: {
                    try {
                        System.out.println("Enter id of the product to update");
                        int id = sc.nextInt();
                        repo.updateProduct(id);
                        break;
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid input!! Enter valid number");
                    }
                }

                case 4: {
                    try{
                        System.out.println("=====All Products=====");
                        List<Products> products = repo.getAllProducts();
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
                    break loop;
                }

                default: {
                    System.out.println("Invalid choice");
                    break;
                }
            }


    }
    }
}
