package com.example.backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.db.DatabaseRepo;
import com.example.backend.db.DatabaseRepoInterface;
import com.example.backend.models.Order;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/database")
@CrossOrigin(origins = "http://localhost:4200")
public class DatabaseController {
    DatabaseRepoInterface databaseRepoInterface = new DatabaseRepo();
    
    @PostMapping("/insert")
    public Integer insertNewRowInDatabase(){
        return databaseRepoInterface.insert();
    }

    @PostMapping("saveUserDetails")
    public void saveUser(@RequestBody Order o) {
        databaseRepoInterface.save(o);
    }
    

}