package service;

import model.Products;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadFileData {
    private Scanner Reader;

    public void openFile (){
        try{
            File readFile= new File("items.txt");
            Reader = new Scanner(readFile);

        }
        catch(FileNotFoundException e){
            System.out.println("File not found!!");
        }
    }

    public List<Products> readFile(){
        List<Products> products= new ArrayList<Products>();
        while (Reader.hasNextLine()){
            String name = Reader.next();
            double price = Reader.nextDouble();
            Products product= new Products(name,price);
            products.add(product);
        }
        System.out.println(products);
        return products;
    }

    public void closeFile(){
        Reader.close();
    }
}
