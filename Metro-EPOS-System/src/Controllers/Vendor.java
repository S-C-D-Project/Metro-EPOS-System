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

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(Product product) {
        if (product != null) {
            this.productList.add(product);
            System.out.println("Product " + product.getProductName() + " has been added.");
        } else {
            System.out.println("Error: Product cannot be null.");
        }
    }

    public void removeProduct(String productName) {
        boolean productFound = false;

        for (Product product : this.productList) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                this.productList.remove(product);
                productFound = true;
                System.out.println("Product " + productName + " has been removed.");
                break;
            }
        }

        if (!productFound) {
            System.out.println("Error: Product with name '" + productName + "' not found.");
        }
    }

}
