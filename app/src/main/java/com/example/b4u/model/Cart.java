package com.example.b4u.model;

public class Cart {
    String NameProduct;
    String PriceProduct;
    String QuantityProduct;
    String TotalPrice;

    public  Cart()
    {

    }
    public Cart(String nameProduct, String priceProduct, String quantityProduct, String totalPrice) {
        NameProduct = nameProduct;
        PriceProduct = priceProduct;
        QuantityProduct = quantityProduct;
        TotalPrice = totalPrice;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public String getPriceProduct() {
        return PriceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        PriceProduct = priceProduct;
    }

    public String getQuantityProduct() {
        return QuantityProduct;
    }

    public void setQuantityProduct(String quantityProduct) {
        QuantityProduct = quantityProduct;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }
}
