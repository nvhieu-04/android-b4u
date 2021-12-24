package com.example.b4u.model;

import java.util.HashMap;

public class PurchasedProduct {
    String NameProduct;
    String TotalPrice;
    String QuantityProduct;
    String Name;
    String Phone;
    String Address;
    String Time;
    String PriceProduct;

    public PurchasedProduct(String nameProduct, String totalPrice, String quantityProduct, String name, String phone, String address, String time, String priceProduct) {
        NameProduct = nameProduct;
        TotalPrice = totalPrice;
        QuantityProduct = quantityProduct;
        Name = name;
        Phone = phone;
        Address = address;
        Time = time;
        PriceProduct = priceProduct;
    }

    public PurchasedProduct()
    {

    }

    public String getPriceProduct() {
        return PriceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        PriceProduct = priceProduct;
    }

    public PurchasedProduct(String nameProduct, String totalPrice, String quantityProduct, String name, String phone, String address, String time) {
        NameProduct = nameProduct;
        TotalPrice = totalPrice;
        QuantityProduct = quantityProduct;
        Name = name;
        Phone = phone;
        Address = address;
        Time = time;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getQuantityProduct() {
        return QuantityProduct;
    }

    public void setQuantityProduct(String quantityProduct) {
        QuantityProduct = quantityProduct;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public HashMap<String,Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("Address",Address);
        result.put("Name",Name);
        result.put("NameProduct",Name);
        result.put("Phone",Phone);
        result.put("TotalPrice",TotalPrice);
        result.put("QuantityProduct",QuantityProduct);
        result.put("Time",Time);
        return result;
    }
}
