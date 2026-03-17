package repository.Interface;

import model.Products;

import java.util.List;

public interface ProductDAOInterface {
    public void createProduct(Products product);
    public void removeProduct(int productId);
    public Products findProductsbyName(String name);
    public Products findProductbyId(int id);
    public void updateProduct(int id);
    public List<Products> getAllProducts();
}
