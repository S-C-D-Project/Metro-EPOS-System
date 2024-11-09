package Controllers;

public class Product {
    private String productName;
    private String contactInfo;
    private String category;
    private double originalPrice;
    private int salePrice;
    private double pricePerUnit;
    private double pricePerCarton;
    private int stockQuantity;

    public Product(String productName, String contactInfo, String category, double originalPrice, int salePrice, double pricePerUnit, double pricePerCarton, int stockQuantity) {
        this.productName = productName;
        this.contactInfo = contactInfo;
        this.category = category;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.pricePerUnit = pricePerUnit;
        this.pricePerCarton = pricePerCarton;
        this.stockQuantity = stockQuantity;
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

    public double getPricePerCarton() {
        return pricePerCarton;
    }

    public void setPricePerCarton(double pricePerCarton) {
        this.pricePerCarton = pricePerCarton;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
