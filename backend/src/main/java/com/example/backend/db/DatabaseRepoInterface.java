package com.example.backend.db;

import com.example.backend.models.Order;

public interface DatabaseRepoInterface {

    Integer insert();

    Object save(Order o);
    
}
