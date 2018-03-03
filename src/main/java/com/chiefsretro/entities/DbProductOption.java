package com.chiefsretro.entities;

import java.util.List;

public class DbProductOption {

    private Integer productOptionsId;

    private String productColor;

    private double productPrice;

    private Integer productQuantity;

    private List<DbProductOptionImage> productOptionImages;


    public List<DbProductOptionImage> getProductOptionImages() {
        return productOptionImages;
    }

    public void setProductOptionImages(List<DbProductOptionImage> productOptionImages) {
        this.productOptionImages = productOptionImages;
    }

    public Integer getProductOptionsId() {
        return productOptionsId;
    }

    public void setProductOptionsId(Integer productOptionsId) {
        this.productOptionsId = productOptionsId;
    }

    public String getProductColor() {
        return productColor;
    }
    public void setProductColor(String productColor) { this.productColor = productColor; }


    public double getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(double productPrice) { this.productPrice = productPrice; }

    public int getProductQuantity() { return productQuantity; }
    public void setProductQuantity(Integer productQuantity) { this.productQuantity = productQuantity; }

    public Integer getProductOptionId() {
        return productOptionsId;
    }
    public void setProductOptionId(Integer productOptionId) {
        this.productOptionsId = productOptionId;
    }

}
