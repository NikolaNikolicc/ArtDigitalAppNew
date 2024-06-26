package com.example.backend.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.backend.models.Order;

public class RepoDatabase implements InterfaceDatabaseRepo{

    @Override
    public Integer insert() {
        final String sql = "INSERT INTO ordertable (name, surname, mail, phone, comment, postal, city, address, paperBacking, promoCode, payment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            for (int i = 1; i <= 11; i++) {  // Set all placeholders to null
                stm.setObject(i, null);
            }
            int affectedRows = stm.executeUpdate();
            if(affectedRows > 0){
                try(ResultSet generatedKeys = stm.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer save(Order o) {
        final String sql = "UPDATE `artdigital`.`ordertable` " + 
        "SET name = ?, " + 
        "    surname = ?, " + 
        "    mail = ?, " + 
        "    phone = ?, " + 
        "    comment = ?, " + 
        "    postal = ?, " + 
        "    city = ?, " + 
        "    address = ?, " + 
        "    paperBacking = ?, " + 
        "    promoCode = ?, " + 
        "    payment = ?" + 
        "WHERE orderID = ?"; 
        try (Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);) {
            stm.setString(1, o.getName());
            stm.setString(2, o.getSurname());
            stm.setString(3, o.getMail());
            stm.setString(4, o.getPhone());
            stm.setString(5, o.getComment());
            stm.setString(6, o.getPostal());
            stm.setString(7, o.getCity());
            stm.setString(8, o.getAddress());
            stm.setString(9, o.getPaperBacking());
            stm.setString(10, o.getPromoCode());
            stm.setString(11, o.getPayment());
            stm.setInt(12, o.getOrderID());
            stm.executeUpdate();
            return o.getOrderID();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Order getOrderDetails(Integer orderID) {
        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn.prepareStatement("SELECT * FROM ordertable WHERE orderID = ?");) {
            stm.setInt(1, orderID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Order order = new Order(
                    orderID, 
                    rs.getString("name"), 
                    rs.getString("surname"), 
                    rs.getString("mail"), 
                    rs.getString("phone"), 
                    rs.getString("comment"), 
                    rs.getTimestamp("date"), 
                    rs.getString("postal"), 
                    rs.getString("city"), 
                    rs.getString("address"), 
                    rs.getString("paperBacking"), 
                    rs.getString("promoCode"), 
                    rs.getString("payment")
                );
                return order;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new Order(-1, "Greška", "Greška", "Greška", "Greška", "Greška", null, "Greška", "Greška", "Greška", "Greška", "Greška", "Greška");
    }
    
}
