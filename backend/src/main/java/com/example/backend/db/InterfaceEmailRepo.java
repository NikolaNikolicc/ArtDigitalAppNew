package com.example.backend.db;

public interface InterfaceEmailRepo {
    
    public void sendSimpleMessage(String to, String subject, String text);
    
}
