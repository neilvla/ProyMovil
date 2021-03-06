package com.agroapp.mobile.agroapp.Entities;

import java.io.Serializable;

public class User extends Base implements Serializable {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return ((image != null && image != "") ? image : "");
    }

    public void setImage(String image) {
        this.image = image;
    }
}
