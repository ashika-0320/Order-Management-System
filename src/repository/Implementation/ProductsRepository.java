package repository.Implementation;

import model.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductsRepository {
    static public List<Products> products= new ArrayList<Products>();

    public void createProduct(Products product){
        products.add(product);
        System.out.println("Product created succesfully");
    }

    public void removeProduct(int productId){
        boolean found = false;
        for (Products product: products){
            if(product.getProductId()==productId){
                products.remove(product);
                System.out.println("Product removed succesfully 🗑️ ");
                found= true;
                break;
            }

        }
        if(!found){
            System.out.println("Product not found");
        }
    }

    public Products findProductbyId(int productId){
        for (Products product: products){
            if(product.getProductId()==productId){
                return product;
            }
    }
        return null;
};

    public Products findProductsbyName(String name){
        for (Products product : products){
            if(product.getItemName().equals(name)){
                return product;
            }
        }
        return null;
    }

    public void updateProduct(int id){
        System.out.println("Enter new id");
        Products product= findProductbyId(id);
        Scanner sc= new Scanner(System.in);
        System.out.println("enter new id");
        int newid= sc.nextInt();
        System.out.println("enter product name");
        String productName= sc.next();
        System.out.println("Enter price");
        double price = sc.nextDouble();
        product.setProductId(newid);
        product.setItemName(productName);
        product.setPrice(price);
        System.out.println("Order saved successfully");
    }

    public List<Products> getAllProducts(){
        return products;
    }
}
