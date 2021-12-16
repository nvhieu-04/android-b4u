package com.example.b4u.model;

public class CareProduct {
    String name;
    String description;
    String price;
    String price_before;
    String rate;
    Integer imageUrl;
    Integer bigimageurl;

    public CareProduct(String name, String description, String price, String price_before, String rate, Integer imageUrl, Integer bigimageurl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.price_before = price_before;
        this.rate = rate;
        this.imageUrl = imageUrl;
        this.bigimageurl = bigimageurl;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_before() {
        return price_before;
    }

    public void setPrice_before(String price_before) {
        this.price_before = price_before;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getBigimageurl() {
        return bigimageurl;
    }

    public void setBigimageurl(Integer bigimageurl) {
        this.bigimageurl = bigimageurl;
    }
}
