package model;

import java.time.LocalDateTime;

public class Order {
    static int counter= 1;
    private int id ;
    private int totalItems;
    private int productId;
    private String ProductName;
    private LocalDateTime orderDate;
    private int customerId;

    public Order( String productName,int productId,int totalItems) {
        this.id = counter++;
        this.totalItems = totalItems;
        this.productId = productId;
        this.orderDate = LocalDateTime.now();
        ProductName = productName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    @Override
    public String toString(){
        return "Order Id: " + id+ ", Total Items: " +totalItems+ ", Product Id: " +productId+ "Product Name: " + ProductName +"Order Date: "+ orderDate;

    }
}
