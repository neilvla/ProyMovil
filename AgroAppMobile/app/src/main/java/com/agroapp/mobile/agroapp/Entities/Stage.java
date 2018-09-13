package com.agroapp.mobile.agroapp.Entities;

public class Stage extends Base {
    private String name;
    private String description;
    private String image;

    public Stage() {
    }

    public Stage(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return ((image != null && image != "") ? image : "");
    }

    public void setImage(String image) {
        this.image = image;
    }
}
