package com.example.ptjm.model;

public class RegisterModel {
    private String registrationDate;
    private String  userId;
    private String username;
    private int age;
    private String address;
    private String phone_number;
    private String password;
    private int userType;
    private String gender;
    private String specializedField;

    public RegisterModel(String userId, String username, int age, String address, String phone_number, String password, int userType, String gender, String specializedField, String registrationDate) {
        this.userId = userId;
        this.username = username;
        this.age = age;
        this.address = address;
        this.phone_number = phone_number;
        this.password = password;
        this.userType = userType;
        this.gender = gender;
        this.specializedField = specializedField;
        this.registrationDate = registrationDate;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
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
    public String getGender(){
        return gender;
    }
    public String getSpecializedField(){
        return specializedField;
    }


}
