package com.homework.userAccount;

public class UserAccount {
    private String name;
    private String email;
    private int id;
    private String country;

    public UserAccount(String name, String email, String country, int id){
        this.name = name;
        this.id = id;
        this.country = country;
        this.email=email;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString(){
        return "Name=" + this.name +
                ", Email=" + this.email +
                ", Country=" + this.country;
    }
}
