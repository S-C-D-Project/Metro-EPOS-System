package Controllers;

import Models.DataBaseHandler;
import java.util.ArrayList;

public class Bill {
    private ArrayList<Product> productList;
    private int cashAmount, returnAmount, totalbill, additionalCharges, salesTaxAmount;
    private double discount, salesTax;

    public Bill() {
        this.productList = new ArrayList<>();
        this.cashAmount = 0;
        this.returnAmount = 0;
        this.totalbill = 0;
        this.additionalCharges = 0;
        this.discount = 0;
        this.salesTax = 0;
    }

    public Bill(ArrayList<Product> productList, int cashAmount, int returnAmount, int totalbill, int additionalCharges, double discount) {
        this.productList = productList;
        this.cashAmount = cashAmount;
        this.returnAmount = returnAmount;
        this.totalbill = totalbill;
        this.additionalCharges = additionalCharges;
        this.discount = discount;
        this.salesTax = 0;
    }

    public int calculateBill(boolean isVendor) {
        double totalAmount = 0;
        for (Product product : productList) {
            totalAmount += isVendor ? product.getOriginalPrice() : product.getSalePrice();
        }
        salesTaxAmount = (int) ((totalAmount * salesTax) / 100);
        totalAmount += salesTaxAmount + additionalCharges - discount;
        this.totalbill = (int) totalAmount;
        return totalbill;
    }

    public Bill addProduct(int id, boolean isVendor) {
        if (productList.isEmpty()) {
            salesTax = DataBaseHandler.getSalesTax();
        }
        Product newProduct = DataBaseHandler.checkProduct(id);
        if (newProduct != null) {
            productList.add(newProduct);
            calculateBill(isVendor);
            return this;
        } else {
            return null;
        }
    }

    public Bill removeProduct(int id, boolean isVendor) {
        productList.removeIf(product -> product.getId() == id);
        calculateBill(isVendor);
        return this;
    }

    public Bill addAdditionalAmount(int additionalAmount, boolean isVendor) {
        this.additionalCharges = additionalAmount;
        calculateBill(isVendor);
        return this;
    }

    public Bill addDiscount(double discount, boolean isVendor) {
        this.discount = discount;
        calculateBill(isVendor);
        return this;
    }
}
