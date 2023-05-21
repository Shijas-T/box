package com.example.box;

public class ProductModel {

    private String productName;
    private String productCategory;
    private String productPrice;
    private String productDescription;
    private String productImageUrl;

    public ProductModel() {
        // empty constructor
        // required for Firebase.
    }

    public ProductModel(String productName, String productCategory, String productPrice, String productDescription, String productImageUrl) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productImageUrl = productImageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }
}
