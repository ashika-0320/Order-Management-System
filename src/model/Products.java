package model;

public class Products {
    int productId;
    String itemName;
    double price;
    long availableQty;

    public Products( String itemName, double price, long availableQty) {
        this.itemName = itemName;
        this.price = price;
        this.availableQty=availableQty;
    }

    public long getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(long availableQty) {
        this.availableQty = availableQty;
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
        return "Product Id: " + productId + ", Item name: " +itemName + ", Price: " +price + ", Available Qty: " +availableQty;
    }
}
