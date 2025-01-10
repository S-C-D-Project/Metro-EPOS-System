package Controllers;

import java.util.ArrayList;

public class Vendor {
    private int vendorId;
    private String name;
    private String contactInfo;
    private ArrayList<Product> productList;

    public Vendor(int vendorId, String name, String contactInfo, ArrayList<Product> productList) {
        this.vendorId = vendorId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.productList = productList != null ? productList : new ArrayList<Product>();
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

}
