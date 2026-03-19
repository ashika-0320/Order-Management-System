package repository.Implementation;
import model.Order;
import model.Products;
import repository.DBconnection;
import repository.Interface.OrderDAOInterface;
import service.Reciept;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class OrderDAO implements OrderDAOInterface {
    private final DBconnection db = new DBconnection();

    public void addOrder(Order order){
        String sql="Insert into orders (totalItems, productId, productName, orderDate, totalPrice) values (?,?,?,?,?)";
        try{
            Connection con= db.getConnection();
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1,order.getTotalItems());
            ps.setInt(2,order.getProductId());
            ps.setString(3,order.getProductName());
            ps.setTimestamp(4,Timestamp.valueOf(order.getOrderDate()));
            ps.setDouble(5, order.getTotalPrice());

            ps.executeUpdate();
            System.out.println("Order saved successfully");
        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.println("The order id already exists.Try again with another!");
        }
        catch (SQLException e){
            System.out.println("Failed to save order");
            e.printStackTrace();
        }

    }

    public Order findById(int id){
        String sql = "Select *from orders where id="+id;
        try{
            Connection con=db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery(sql);

            if(rs.next()){
                Products product =new Products(rs.getString("productName"),rs.getDouble("totalPrice")/rs.getDouble("totalItems"), rs.getLong("availableQty"));
                int totalItems=rs.getInt("totalItems");
                Order order = new Order(product,totalItems);
                order.setId(rs.getInt(id));
                return order;
            }

        }
        catch (SQLException e){
            System.out.println("Failed to retrieve order");
        }
        return null;
    }

    public void removeOrder(int id){
        String sql = "Delete From orders where id=?";
        try{
            Connection con=db.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            int rows = st.executeUpdate();

            if(rows<0){
                System.out.println("Failed to delete.");
            }
            else{
                System.out.println("Item deleted succesfully!");
            }

        }
        catch (SQLException e){
            System.out.println("Failed to retrieve order");
        }
    }

    public List<Order> getAllOrder(){
        List<Order> orders=new ArrayList<Order>();
        String sql ="Select o.id, o.totalItems, o.productId, o.productName, o.orderDate, o.totalPrice, p.availableQty from orders o join products p on o.productId = p.productId";

        try{
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                int id= rs.getInt("id");
                int totalItems= rs.getInt("totalItems");
                int productId = rs.getInt("productId");
                String productName= rs.getString("productName");
                Timestamp ts= rs.getTimestamp("orderDate");
                LocalDateTime orderDate = ts.toLocalDateTime();
                double price = rs.getDouble("totalPrice");
                long qty = rs.getLong("availableQty");
                Products product = new Products(productName,price/totalItems, qty);
                product.setProductId(productId);
                Order order= new Order(product, totalItems);
                order.setId(id);
                orders.add(order);
            }
        }
        catch (SQLException e){
            System.out.println("Failed to get all orders!");
            e.printStackTrace();
        }
        return orders;
    }

    public void clearCart (){
        String sql = "Delete from orders";
        try{
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            int rows=st.executeUpdate(sql);
            if(rows != 0){
                System.out.println("Cart cleared");
            }
        }
        catch(SQLException e){
            System.out.println("Failed to delete");
            e.printStackTrace();
        }
    }




}
