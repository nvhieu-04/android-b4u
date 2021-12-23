package com.example.b4u.model;

import java.util.HashMap;

public class PurchasedProduct {
    String nameProduct;
    String priceProduct;
    String quantityProduct;
    String nameUser;
    String phoneUser;
    String addressUser;
    String ID;

    public PurchasedProduct()
    {

    }
    public PurchasedProduct(String nameProduct, String priceProduct, String quantityProduct, String nameUser, String phoneUser, String addressUser, String ID) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.quantityProduct = quantityProduct;
        this.nameUser = nameUser;
        this.phoneUser = phoneUser;
        this.addressUser = addressUser;
        this.ID = ID;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(String quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(String addressUser) {
        this.addressUser = addressUser;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public HashMap<String,Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("Address",addressUser);
        result.put("Name",nameUser);
        result.put("NameProduct",nameProduct);
        result.put("Phone",phoneUser);
        result.put("TotalPrice",priceProduct);
        result.put("QuantityProduct",quantityProduct);
        result.put("PriceProduct",ID);
        return result;
    }
}
