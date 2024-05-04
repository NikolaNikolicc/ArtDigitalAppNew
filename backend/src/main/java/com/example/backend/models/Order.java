package com.example.backend.models;

import java.sql.Timestamp;

public class Order {
    Integer orderID;
    String name;
    String surname;
    String mail;
    String phone;
    String comment;
    Timestamp date;
    String postal;
    String city;
    String address;
    String paperBacking;
    String promoCode;
    String payment;

    
    
    @Override
    public String toString() {
        return "\t\t\tDETALJI PORUDŽBINE\n" + 
        "Redni broj porudžbine: " + this.getOrderID() + "\n" + 
        "Ime: " + this.getName() + "\n" + 
        "Prezime: " + this.getSurname() + "\n" + 
        "Mejl: " + this.getMail() + "\n" + 
        "Telefon: " + this.getPhone() + "\n" + 
        "Komentar: " + this.getComment() + "\n" + 
        "Datum kreiranja porudžbine: " + this.getDate() + "\n" + 
        "Poštanski kod: " + this.getPostal() + "\n" + 
        "Grad: " + this.getCity() + "\n" + 
        "Adresa: " + this.getAddress() + "\n" + 
        "Podloga: " + this.getPaperBacking() + "\n" + 
        "Promo kod: " + this.getPromoCode() + "\n";
    }

    public Order(Integer orderID, String name, String surname, String mail, String phone, String comment,
            Timestamp date, String postal, String city, String address, String paperBacking, String promoCode,
            String payment) {
        this.orderID = orderID;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phone = phone;
        this.comment = comment;
        this.date = date;
        this.postal = postal;
        this.city = city;
        this.address = address;
        this.paperBacking = paperBacking;
        this.promoCode = promoCode;
        this.payment = payment;
    }
    public String getPaperBacking() {
        return paperBacking;
    }
    public void setPaperBacking(String paperBacking) {
        this.paperBacking = paperBacking;
    }
    public String getPromoCode() {
        return promoCode;
    }
    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
    public String getPayment() {
        return payment;
    }
    public void setPayment(String payment) {
        this.payment = payment;
    }
    public Integer getOrderID() {
        return orderID;
    }
    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Timestamp getDate() {
        return date;
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }
    public String getPostal() {
        return postal;
    }
    public void setPostal(String postal) {
        this.postal = postal;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
