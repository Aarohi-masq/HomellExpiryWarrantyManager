package com.homell.Homell;

public class Products {
    String productName;
    String productbrand;
    String productdate;

    String productdesc;
    String Pid;
    public Products()
    {
    }
    public Products(String productName,String productbrand,String productdate, String Pid, String productdesc)
    {
        this.productName=productName;
        this.productbrand=productbrand;
        this.productdate=productdate;

        this.productdesc=productdesc;
        this.Pid=Pid;
    }

    public String getEditTextName() {
        return productName;
    }

    public String getDate() {
        return productdate;
    }



    public String getRealbrand() {
        return productbrand;
    }

    public String getProductId(){
        return Pid;
    }

    public String getProductdesc() {
        return productdesc;
    }
}

