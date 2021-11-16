package com.example.b4u.model;

public class Category {
    Integer id;
    Integer imageurl;
    String  name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category(Integer id, Integer imageurl, String name) {
        this.id = id;
        this.imageurl = imageurl;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImageurl() {
        return imageurl;
    }

    public void setImageurl(Integer imageurl) {
        this.imageurl = imageurl;
    }
}
