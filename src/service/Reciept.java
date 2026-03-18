package service;

import model.Order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Reciept {

    public void recieptGen(List<Order> orders, double amount){
        try{
            FileWriter recieptWriter= new FileWriter("reciept.txt");
            System.out.println(orders);
            if(orders == null){
                System.out.println("No order found");
                System.out.println(orders);
                return;
            }
            recieptWriter.write("\n================RECIEPT================\n");

            for(Order order:orders){

                recieptWriter.write("\nOrder Id: "+order.getId()+"\nProduct Id: "+order.getProductId()+"\nProduct Name: "+order.getProductName()+"\nPrice: "+order.getPrice()+"\nQuantity: "+order.getTotalItems()+"\nTotal: "+order.getTotalPrice()+"\nDate: "+order.getOrderDate());

                recieptWriter.write("\n=======================================================\n");
            }
            recieptWriter.write("=================GRAND TOTAL===============\n");
            recieptWriter.write (String.valueOf(amount));
            recieptWriter.write("\n==========STATUS==========\n");
            recieptWriter.write("PAID");
            recieptWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
