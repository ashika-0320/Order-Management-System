package repository.Interface;

import model.Products;

import java.util.List;

public interface ProductDAOInterface {
    public void createProduct(Products product);
    public void appendProduct(int id, long qty);
    public void removeProduct(int productId);
    public int findProductsbyName(String name);
    public Products findProductbyId(int id);
    public void updateProduct(int id);
    public List<Products> getAllProducts();

}
