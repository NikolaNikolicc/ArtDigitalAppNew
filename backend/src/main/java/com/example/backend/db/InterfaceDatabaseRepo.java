package com.example.backend.db;

import com.example.backend.models.Order;

public interface InterfaceDatabaseRepo {

    Integer insert();

    Integer save(Order o);

    Order getOrderDetails(Integer orderID);
    
}
