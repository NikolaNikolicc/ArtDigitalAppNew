package com.example.backend.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseRepo implements DatabaseRepoInterface{

    @Override
    public Integer insert() {
        final String sql = "INSERT INTO ordertable (name, surname, mail, phone, comment, postal, city, address, paperBacking, promoCode, payment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DB.source().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            for (int i = 1; i <= 8; i++) {  // Set all placeholders to null
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
    
}
