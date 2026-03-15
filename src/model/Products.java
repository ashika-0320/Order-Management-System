package model;

public class Products {
    int productId;
    String itemName;
    double price;

    public Products(int productId, String itemName, double price) {
        this.productId = productId;
        this.itemName = itemName;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString(){
        return "Product Id:" + productId + "Item name: " +itemName + "Price: " +price;
    }
}
