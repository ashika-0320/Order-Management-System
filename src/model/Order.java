package model;

import java.time.LocalDateTime;

public class Order {
    private int id ;
    private int totalItems;
    private int productId;
    private String ProductName;
    private LocalDateTime orderDate;
    private double totalPrice;
    private double price;

    public Order( Products product,int totalItems) {
        this.totalItems = totalItems;
        this.orderDate = LocalDateTime.now();
        this.productId= product.getProductId();
        this.ProductName = product.getItemName();
        this.totalPrice = product.getPrice()*totalItems;
        this.price = product.getPrice();
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
        recalculateTotalPrice();
    }
    public void recalculateTotalPrice(){
        totalPrice=totalItems*price;
    }

    public int getProductId() {
       return productId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
