package com.example.ptjm;

public class RegisterModel {
    private String  userId;
    private String username;
    private String age;
    private String address;
    private String phone_number;
    private String password;
    private int userType;

    public RegisterModel(String userId, String username, String age, String address, String phone_number, String password,int userType) {
        this.userId = userId;
        this.username = username;
        this.age = age;
        this.address = address;
        this.phone_number = phone_number;
        this.password = password;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getPassword() {
        return password;
    }
    public int getUserType(){
        return userType;
    }
}
