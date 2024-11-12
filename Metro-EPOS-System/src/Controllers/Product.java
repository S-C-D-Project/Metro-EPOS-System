package Controllers;

import Models.InternetConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Product {
    private int branchId;
    private String productName;
    private String contactInfo;
    private String category;
    private String productSize;
    private double originalPrice;
    private int salePrice;
    private double pricePerUnit;
    private int stockQuantity;

    public Product(String productName, String contactInfo, String category, double originalPrice, int salePrice, double pricePerUnit, int stockQuantity,int branchid,String productSize) {

        this.branchId = branchid;
        this.productName = productName;
        this.contactInfo = contactInfo;
        this.category = category;
        this.productSize = productSize;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.pricePerUnit = pricePerUnit;
        this.stockQuantity = stockQuantity;
    }

    public int getBranchid() {
        return branchId;
    }

    public void setBranchid(int branchid) {
        this.branchId = branchid;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    // Getters and Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
