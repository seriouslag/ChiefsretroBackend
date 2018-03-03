package com.chiefsretro.entities;

public class DbProductOptionImage {

    private Integer productOptionImageId;

    private String productOptionImageLocation;

    private boolean hasThumb;

    private Integer productOptionImageOrder;

    public Integer getProductOptionImageId() {return this.productOptionImageId;}
    public void setProductOptionImageId(int productOptionImageId) {
        this.productOptionImageId = productOptionImageId;
    }

    public String getProductOptionImageLocation() {return this.productOptionImageLocation;}
    public void setProductOptionImageLocation(String productOptionImageLocation) {
        this.productOptionImageLocation = productOptionImageLocation;
    }

    public boolean getHasThumb() {return this.hasThumb;}
    public void setHasThumb(boolean hasThumb) {
        this.hasThumb = hasThumb;
    }

    public Integer getProductOptionImageOrder() {return this.productOptionImageOrder;}
    public void setProductOptionImageOrder(Integer productOptionImageOrder) {this.productOptionImageOrder = productOptionImageOrder;}
}
