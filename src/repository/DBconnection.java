package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    Connection conn=null;

    String url= "jdbc:mysql://localhost:3306/order_management_system";
    String username="root";
    String password ="12345";

    public Connection getConnection(){
        Connection conn= null;
        try{
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            System.out.println("Failed to connect to database");
        }
        return conn;
    }
}
