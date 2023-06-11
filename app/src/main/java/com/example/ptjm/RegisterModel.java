package com.example.ptjm;

public class RegisterModel {
    private String  userId;
    private String username;
    private String age;
    private String address;
    private int gender;
    private String phone_number;
    private String specialized_field;
    private String password;

    public RegisterModel(String userId, String username, String age, String address, int gender, String phone_number, String specialized_field, String password) {
        this.userId = userId;
        this.username = username;
        this.age = age;
        this.address = address;
        this.gender = gender;
        this.phone_number = phone_number;
        this.specialized_field = specialized_field;
        this.password = password;
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

    public String getAddress(){
        return address;
    }

    public int getGender() {
        return gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getSpecialized_field() {
        return specialized_field;
    }

    public String getPassword() {
        return password;
    }

}
