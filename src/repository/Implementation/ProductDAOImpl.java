package repository.Implementation;

import model.Products;
import repository.DBconnection;
import repository.Interface.ProductDAOInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDAOImpl implements ProductDAOInterface {
    DBconnection db = new DBconnection();

    @Override
    public void createProduct(Products product) {
        String sql = "Insert into products (itemName, price,availableQty) values(?,?,?)";
        try {
            Connection con = db.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            int ProductId = product.getProductId();
            String itemName = product.getItemName();
            double price = product.getPrice();
            long qty = product.getAvailableQty();
            st.setString(1, itemName);
            st.setDouble(2, price);
            st.setLong(3, qty);
            st.executeUpdate();
            System.out.println("Products Added Sucessfully");
        } catch (SQLException e) {
            System.out.println("Failed to add data to database");
            e.printStackTrace();
        }
    }

    @Override
    public void appendProduct(int id, long qty) {
        DBconnection db = new DBconnection();
        try {
            String sql = "UPDATE products Set availableQty= availableQty+" + qty + " where productId=" + id;
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            int rowsAffected = st.executeUpdate(sql);

            if (rowsAffected <= 0) {
                System.out.println("Product not found!!");
            } else {
                System.out.println("Product added succesfully!!!");
            }

        } catch (SQLException e) {
            System.out.println("Cannot add product");
            e.printStackTrace();
        }
    }

    @Override
    public void removeProduct(int productId) {
        DBconnection db = new DBconnection();
        try {
            String sql = "Delete from products where productId = " + productId;
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            int rowsAffected = st.executeUpdate(sql);

            if (rowsAffected <= 0) {
                System.out.println("Product not found!!");
            } else {
                System.out.println("Product deleted succesfully");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Product order is already confirmed! Cannot remove the product");
        } catch (SQLException e) {
            System.out.println("Not able to remove the data");
        }
    }

    @Override
    public Products findProductbyId(int id) {
        DBconnection db = new DBconnection();
        try {
            String sql = "Select *From products Where productId = " + id;
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int foundProductId = rs.getInt(1);
                String itemName = rs.getString(2);
                double price = rs.getDouble(3);
                long qty = rs.getLong(4);
                Products products = new Products(itemName, price, qty);
                products.setProductId(foundProductId);
                return products;
            }

        } catch (SQLException e) {
            System.out.println("Cannot find product! There is an error while fetching product");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int findProductsbyName(String name) {
        DBconnection db = new DBconnection();
        try {
            String sql = "Select *From products Where itemName = " + "'" + name + "'";
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int foundProductId = rs.getInt(1);
                String itemName = rs.getString(2);
                double price = rs.getDouble(3);
                long qty = rs.getLong(4);
                Products products = new Products(itemName, price, qty);
                products.setProductId(foundProductId);
                return foundProductId;
            }

        } catch (SQLException e) {
            System.out.println("Cannot find product! There is an error while fetching product");
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void updateProduct(int id) {
        Products product = findProductbyId(id);
        Scanner sc = new Scanner(System.in);
        System.out.println("enter new product name");
        String productName = sc.nextLine();
        System.out.println("Enter price");
        double price = sc.nextDouble();
        System.out.println("Enter qty");
        long qty = sc.nextLong();
        DBconnection db = new DBconnection();
        String sql = "UPDATE products Set itemName= ?, price = ?, availableQty=? Where productId = ?";
        try {
            Connection con = db.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, productName);
            st.setDouble(2, price);
            st.setLong(3, qty);
            st.setInt(4, id);


            int rows = st.executeUpdate();
            if (rows < 0) {
                System.out.println("Failed to update");
            } else {
                System.out.println("Product updated successfully");
            }
        } catch (SQLException e) {
            System.out.println("Cannot Update data. Some exception has occured");
            e.printStackTrace();
        }
        System.out.println("Order saved successfully");
    }

    @Override
    public List<Products> getAllProducts() {
        DBconnection db = new DBconnection();
        List<Products> allproducts = new ArrayList<Products>();
        try {
            String sql = "Select *From products";
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int foundProductId = rs.getInt(1);
                String itemName = rs.getString(2);
                double price = rs.getDouble(3);
                long qty = rs.getLong(4);
                Products products = new Products(itemName, price, qty);
                products.setProductId(foundProductId);
                allproducts.add(products);
            }
            return allproducts;

        } catch (SQLException e) {
            System.out.println("Cannot find products! There is an error while fetching product");
            e.printStackTrace();
        }

        System.out.println("There are no products in Stock!");
        return List.of();
    }

    public void loadFileDataToDB(List<Products> productsList) {
        for (Products products : productsList) {
            String sql = "Insert into products (itemName, price, availableQty) values(?,?,?)";
            try {
                Connection con = db.getConnection();
                PreparedStatement st = con.prepareStatement(sql);
                String itemName = products.getItemName();
                double price = products.getPrice();
                long qty = products.getAvailableQty();
                st.setString(1, itemName);
                st.setDouble(2, price);
                st.setLong(3, qty);
                st.executeUpdate();
                System.out.println("Products Added Sucessfully");
            } catch (SQLException e) {
                System.out.println("Failed to add data to database");
                e.printStackTrace();
            }
        }
    }

    public long checkAvailability(int id, long qty) {


        DBconnection db = new DBconnection();
        long result = 0;
        try {
            String sql = "Select availableQty from products where productId =?";
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                long availableQty = rs.getLong("availableQty");
                result = availableQty - qty;
            }
            if (result < 0) {
                return result;
            } else {
                String updateSql = "Update products Set availableQty = ? where productId = ?";
                PreparedStatement st = con.prepareStatement(updateSql);
                st.setLong(1, result);
                st.setInt(2, id);
                st.executeUpdate();
                return result;
            }

        } catch (SQLException e) {
            System.out.println("Error while checking availability");
            e.printStackTrace();
        }
        return 0;
    };
}
