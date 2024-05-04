package com.example.backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.db.RepoDatabase;
import com.example.backend.db.InterfaceDatabaseRepo;
import com.example.backend.models.Order;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/database")
@CrossOrigin(origins = "http://localhost:4200")
public class DatabaseController {
    InterfaceDatabaseRepo databaseRepoInterface = new RepoDatabase();
    
    @PostMapping("/insert")
    public Integer insertNewRowInDatabase(){
        return databaseRepoInterface.insert();
    }

    @PostMapping("saveUserDetails")
    public Integer saveUser(@RequestBody Order o) {
        return databaseRepoInterface.save(o);
    }
    

}