package com.homell.Homell;

public class Expire {
    private String editTextName;
    private String realbrand;
    private String date;
    private String productdesc;
    private String productId;

    public Expire() {
    }

    public Expire(String editTextName, String realbrand, String date, String productmonth, String productdesc, String productId) {
        this.editTextName = editTextName;
        this.realbrand = realbrand;
        this.date = date;

        this.productdesc = productdesc;
        this.productId = productId;
    }

    public String getEditTextName() {
        return editTextName;
    }

    public void setEditTextName(String name) {
        this.editTextName = name;
    }

    public String getRealbrand() {
        return realbrand;
    }

    public void setRealbrand(String brand) {
        this.realbrand = brand;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String desc) {
        this.productdesc = desc;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String xid) {
        this.productId = xid;
    }
}
