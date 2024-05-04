package com.example.backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.db.InterfaceDatabaseRepo;
import com.example.backend.db.InterfaceEmailRepo;
import com.example.backend.db.RepoDatabase;
import com.example.backend.db.RepoEmail;
import com.example.backend.models.Order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {
    InterfaceEmailRepo emailRepoInterface = new RepoEmail();
    InterfaceDatabaseRepo databaseRepoInterface = new RepoDatabase();
    
    @PostMapping("/send")
    public Integer sendMail(@RequestBody Integer orderID) {
        Order order = databaseRepoInterface.getOrderDetails(orderID);
        String to = "posta.nikolan@gmail.com";
        String subject = "posiljka uspela";
        String text = order.toString() + "Preuzmite pošiljku: http://localhost:8080/file/download/" + orderID + "\n";
        this.emailRepoInterface.sendSimpleMessage(to, subject, text);
        return 1;
    }
    
    
}
