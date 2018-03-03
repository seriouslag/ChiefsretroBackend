package com.chiefsretro.entities;

import java.util.List;

public class DbProduct {
    private Integer productId;

    private String productName;

    private String productDescription;

    private List<DbProductOption> productOptions;

    public List<DbProductOption> getProductOptions() {
        return productOptions;
    }

    public void setProductOptions(List<DbProductOption> productOptions) {
        this.productOptions = productOptions;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) { this.productName = productName; }

    public double getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {this.productId = productId; }

    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) { this.productDescription = productDescription; }

}
