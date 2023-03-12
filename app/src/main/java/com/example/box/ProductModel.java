package com.example.box;

public class ProductModel {

    private String productId;
    private String productName;
    private String productPrice;
    private String productDescription;

    public ProductModel(String productId, String productName, String productPrice, String productDescription) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }
}
