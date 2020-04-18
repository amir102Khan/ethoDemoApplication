package com.amir.ethodemoapplication.model;

public class UserModel {
    private String name;
    private String email;
    private String uid;
    private String phone;
    private String bike;

    public UserModel(){

    }

    public UserModel(String name,String email,String uid,String phone,String bike){
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.phone = phone;
        this.bike = bike;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBike() {
        return bike;
    }

    public void setBike(String bike) {
        this.bike = bike;
    }
}
