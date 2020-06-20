package com.example.MyApp.DTO;

public class UserDTO {
    private int user_id;
    private String full_name;
    private int age;
    private char sex;
    private String address;
    private boolean status;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserDTO(int user_id, String full_name, int age, char sex, String address, boolean status) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.status = status;
    }
}
