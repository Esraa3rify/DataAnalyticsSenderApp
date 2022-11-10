package com.example.proven.model;

public class Users{
    private String name, password,image,address;
    private static String phone;
    public Users(){

    }



    public Users(String name, String phone, String password) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.image=image;
        this.address=address;

    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return password;
    }

    public void setPass(String password) {
        this. password = password;
    }


    public static String getPhoneNum() {

        return phone;

    }

    public void setPhoneNum(String phone) {
        this.phone = phone;
    }


}
