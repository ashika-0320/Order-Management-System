package service;

import repository.DBconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Checkout {
    private double totalAmount;

    public double calcualteTotalPrice(){
        DBconnection db = new DBconnection();
        String sql = "SELECT SUM(totalPrice) as Amount from orders";
        try{
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            return rs.getDouble("Amount");
        }
        catch (SQLException e){
            System.out.println("Error occured while calculating total price");
        }
        return 0;
    }
}
