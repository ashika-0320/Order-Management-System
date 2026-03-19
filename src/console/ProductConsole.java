package console;

import model.Products;
import repository.Implementation.ProductDAOImpl;
import repository.Implementation.ProductsRepository;
import service.LoadFileData;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProductConsole {
    Scanner sc = new Scanner(System.in);
    ProductsRepository repo= new ProductsRepository();
    ProductDAOImpl prepo = new ProductDAOImpl();

    public void start(){
        loop:
    while(true) {
            System.out.println("====Product Operations====");
            System.out.println("1.Create Product");
            System.out.println("2.Remove Product");
            System.out.println("3.Update Product");
            System.out.println("4.View All Products");
        System.out.println("5.Load data from file");
            System.out.println("6.Exit");
        int choice = sc.nextInt();

        sc.nextLine();

            switch (choice) {
                case 1: {
                    try {
                        System.out.println("Enter Product Name");
                        String productName = sc.nextLine();
                        int found = prepo.findProductsbyName(productName);
                        if(found!=0){
                            System.out.println("Enter Quantity");
                            long qty= sc.nextLong();
                            prepo.appendProduct(found,qty);
                        }
                        else{
                            System.out.println("Enter Price of Product");
                            double price = sc.nextDouble();
                            System.out.println("Enter Quantity");
                            long qty= sc.nextLong();
                            Products product = new Products(productName, price,qty);
                            prepo.createProduct(product);
                        }


                        break;
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid input!! Enter valid number");
                        sc.nextLine();
                        break loop;
                    }
                    catch (Exception e){
                        System.out.println("An error has occurred!!" + e.getMessage());
                        break loop;
                    }
                }

                case 2: {
                    try {
                        List<Products> products= prepo.getAllProducts();
                        for (Products product : products) {
                            System.out.println(product);
                        }
                        System.out.println("Enter id of the product you want to remove");
                        int id = sc.nextInt();
                        prepo.removeProduct(id);
                        break;
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid input!! Enter valid number");
                        break loop;
                    }
                }

                case 3: {
                    try {
                        List<Products> products= prepo.getAllProducts();
                        for (Products product : products) {
                            System.out.println(product);
                        }
                        System.out.println("Enter id of the product to update");
                        int id = sc.nextInt();
                        prepo.updateProduct(id);
                        break;
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid input!! Enter valid number");
                        sc.nextLine();
                        break loop;
                    }
                }

                case 4: {
                    try{
                        System.out.println("=====All Products=====");
                        List<Products> products = prepo.getAllProducts();
                        for (Products product : products) {
                            System.out.println(product);
                        }
                        break;
                    }
                    catch (Exception e){
                        System.out.println("An error occured while listing orders" + e.getMessage());
                        break loop;
                    }
                }

                case 5:{
                    System.out.println("File data executed");
                    LoadFileData fileData = new LoadFileData();
                    fileData.openFile();
                    List<Products> productList= fileData.readFile();
                    prepo.loadFileDataToDB(productList);
                    fileData.closeFile();
                    break;
                }

                case 6:{
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
