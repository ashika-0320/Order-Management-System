package repository.Implementation;

import model.Products;
import repository.DBconnection;
import repository.Interface.ProductDAOInterface;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDAOImpl implements ProductDAOInterface {
    DBconnection db = new DBconnection();
    @Override
    public void createProduct(Products product) {
        String sql = "Insert into products (itemName, price) values(?,?)";
        try{
            Connection con = db.getConnection();
            PreparedStatement st= con.prepareStatement(sql);
            int ProductId= product.getProductId();
            String itemName= product.getItemName();
            double price = product.getPrice();
            st.setString(1,itemName);
            st.setDouble(2,price);
            st.executeUpdate();
            System.out.println("Products Added Sucessfully");
        } catch (SQLException e) {
            System.out.println("Failed to add data to database");
        }
    }

    @Override
    public void removeProduct(int productId) {
        DBconnection db = new DBconnection();
        try{
            String sql ="Delete from products where productId = "+ productId;
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            int rowsAffected =st.executeUpdate(sql);

            if(rowsAffected<0){
                System.out.println("Product not found!!");
            }
            else {
                System.out.println("Product deleted succesfully");
            }

        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Product order is already confirmed! Cannot remove the product");
        }
        catch (SQLException e){
            System.out.println("Not able to remove the data");
        }
    }

    @Override
    public Products findProductbyId(int id) {
        DBconnection db = new DBconnection();
        try{
            String sql ="Select *From products Where itemName = "+ id;
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                int foundProductId= rs.getInt(1);
                String itemName= rs.getString(2);
                double price = rs.getDouble(3);
                Products products = new Products(itemName, price);
                products.setProductId(foundProductId);
                return products;
            }

        }
        catch (SQLException e){
            System.out.println("Cannot find product! There is an error while fetching product");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Products findProductsbyName(String name) {
        DBconnection db = new DBconnection();
        try{
            String sql ="Select *From products Where itemName = "+ "'"+ name + "'";
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                int foundProductId= rs.getInt(1);
                String itemName= rs.getString(2);
                double price = rs.getDouble(3);
                Products products = new Products(itemName, price);
                products.setProductId(foundProductId);
                return products;
            }

        }
        catch (SQLException e){
            System.out.println("Cannot find product! There is an error while fetching product");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateProduct(int id){
        Products product= findProductbyId(id);
        Scanner sc= new Scanner(System.in);
        System.out.println("enter new product name");
        String productName= sc.next();
        System.out.println("Enter price");
        double price = sc.nextDouble();
        DBconnection db = new DBconnection();
        String sql ="UPDATE products Set itemName= ?, price = ? Where productId = ?";
        try{
            Connection con = db.getConnection();
            PreparedStatement st= con.prepareStatement(sql);
            st.setString(1, productName);
            st.setDouble(2, price);
            st.setInt(3,id);


            int rows = st.executeUpdate();
            if(rows<0){
                System.out.println("Failed to update");
            }
            else {
                System.out.println("Product updated successfully");
            }
        }
        catch (SQLException e){
            System.out.println("Cannot Update data. Some exception has occured");
            e.printStackTrace();
        }
        System.out.println("Order saved successfully");
    }

    @Override
    public List<Products> getAllProducts() {
        DBconnection db = new DBconnection();
        List<Products> allproducts = new ArrayList<Products>();
        try{
            String sql ="Select *From products";
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int foundProductId= rs.getInt(1);
                String itemName= rs.getString(2);
                double price = rs.getDouble(3);
                Products products = new Products(itemName, price);
                products.setProductId(foundProductId);
                allproducts.add(products);
            }
            return allproducts;

        }
        catch (SQLException e){
            System.out.println("Cannot find products! There is an error while fetching product");
            e.printStackTrace();
        }

        System.out.println("There are no products in Stock!");
        return List.of();
    }
}
