package Controllers;

import Models.InternetConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Product {
    private int branchId;
    private String productName;
    private String category;
    private String productSize;
    private double originalPrice;
    private int salePrice;
    private double pricePerUnit;
    private int stockQuantity;
    private double salesTax;

    public int getBranchId() {
        return branchId;
    }

    public Product(int branchId, String productName, String category, String productSize, double originalPrice, int salePrice, double pricePerUnit, int stockQuantity, double salesTax) {
        this.branchId = branchId;
        this.productName = productName;

        this.category = category;
        this.productSize = productSize;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.pricePerUnit = pricePerUnit;
        this.stockQuantity = stockQuantity;
        this.salesTax = salesTax;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
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
