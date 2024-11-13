package Controllers;

import Models.DataBaseHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class Bill {
    private ArrayList<Product> productList;
    private int cashAmount, returnAmount, totalbill, additionalCharges, salesTaxAmount;
    private double discount, salesTax;

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public int getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(int cashAmount) {
        this.cashAmount = cashAmount;
    }

    public int getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(int returnAmount) {
        this.returnAmount = returnAmount;
    }

    public int getTotalbill() {
        return totalbill;
    }

    public void setTotalbill(int totalbill) {
        this.totalbill = totalbill;
    }

    public int getAdditionalCharges() {
        return additionalCharges;
    }

    public void setAdditionalCharges(int additionalCharges) {
        this.additionalCharges = additionalCharges;
    }

    public int getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(int salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

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
        double salesTax = 0;
        for (Product product : productList) {
            totalAmount += isVendor ? ((product.getOriginalPrice() + product.getSalesTax()) * product.getStockQuantity()) :
                    (product.getSalePrice() * product.getStockQuantity());
            salesTax += product.getSalesTax();
        }

        salesTaxAmount = (int) salesTax;
        totalAmount += additionalCharges - discount;
        if(totalAmount<0){
            return this.totalbill;
        }
        this.totalbill = (int) totalAmount;
        return totalbill;
    }

    public Bill addProduct(int productId, int branchId, boolean isVendor, int quantity) throws SQLException {
        if (productList.isEmpty()) {
            salesTax = DataBaseHandler.getInstance().getSalesTax();
        }

        for (Product p : productList) {
            if (p.getProductId() == productId) {
                p.setStockQuantity( quantity);
                calculateBill(isVendor);
                return this;
            }
        }

        Product newProduct = DataBaseHandler.getProduct(productId, branchId);

        if (newProduct != null) {
            System.out.println(newProduct);
            newProduct.setStockQuantity(quantity);
            productList.add(newProduct);
            calculateBill(isVendor);
            return this;
        } else {
            return null;
        }
    }


    public Bill removeProduct(int id, boolean isVendor) {
        productList.removeIf(product -> product.getProductId() == id);
        calculateBill(isVendor);
        return this;
    }

    public Bill addAdditionalAmount(int additionalAmount, boolean isVendor) {
        this.additionalCharges = additionalAmount;
        calculateBill(isVendor);
        return this;
    }

    public Bill addDiscount(double discount, boolean isVendor) {
        if(discount>totalbill){
            return  this;
        }
        this.discount = discount;

        calculateBill(isVendor);
        return this;
    }

    public Bill saveBill(int cashAmount) {
        this.cashAmount = cashAmount;
        this.returnAmount=cashAmount-totalbill;
        if(this.returnAmount<0){
            return null;

        }
        DataBaseHandler.saveBill(this);
        return this;
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Bill currentBill = new Bill();
//        int choice;
//        boolean isVendor = false;  // Assuming this is set based on user role (e.g., false for customer, true for vendor)
//
//        do {
//            System.out.println("\nMenu:");
//            System.out.println("1. Add Product");
//            System.out.println("2. View Current Bill");
//            System.out.println("3. Remove Product");
//            System.out.println("4. Add Additional Charges");
//            System.out.println("5. Add Discount");
//            System.out.println("6. Save Bill");
//            System.out.println("7. Exit");
//            System.out.print("Enter your choice: ");
//            choice = scanner.nextInt();
//
//            switch (choice) {
//                case 1:
//                    System.out.print("Enter Product ID: ");
//                    int productId = scanner.nextInt();
//                    System.out.print("Enter Branch ID: ");
//                    int branchId = scanner.nextInt();
//
//                    System.out.print("Enter Quantity: ");
//                    int quantity = scanner.nextInt();
//                    try {
//                        if(currentBill.addProduct(productId, branchId, isVendor,quantity)!=null){
//                        System.out.println("Product added successfully!");}
//                        else{
//                            System.out.println("Product not added !");
//                        }
//                    } catch (SQLException e) {
//                        System.out.println("Error adding product: " + e.getMessage());
//                    }
//                    break;
//
//                case 2:
//                    currentBill.calculateBill(isVendor);
//                    System.out.println("\nCurrent Bill:");
//                    System.out.println("Total: $" + currentBill.getTotalbill());
//                    System.out.println("Sales Tax: $" + currentBill.getSalesTaxAmount());
//                    System.out.println("Discount: $" + currentBill.getDiscount());
//                    System.out.println("Additional Charges: $" + currentBill.getAdditionalCharges());
//                    System.out.println("Amount Due: $" + (currentBill.getTotalbill() - currentBill.getDiscount() + currentBill.getAdditionalCharges()));
//                    break;
//
//                case 3:
//                    System.out.print("Enter Product ID to remove: ");
//                    int removeProductId = scanner.nextInt();
//                    currentBill.removeProduct(removeProductId, isVendor);
//                    System.out.println("Product removed successfully!");
//                    break;
//
//                case 4:
//                    System.out.print("Enter Additional Charges: ");
//                    int additionalAmount = scanner.nextInt();
//                    currentBill.addAdditionalAmount(additionalAmount, isVendor);
//                    System.out.println("Additional charges added.");
//                    break;
//
//                case 5:
//                    System.out.print("Enter Discount Amount: ");
//                    double discountAmount = scanner.nextDouble();
//                    currentBill.addDiscount(discountAmount, isVendor);
//                    System.out.println("Discount added.");
//                    break;
//
//                case 6:
//                    System.out.print("Enter Cash Amount: ");
//                    int cashAmount = scanner.nextInt();
//                    currentBill.saveBill(cashAmount);
//                    System.out.println("Bill saved successfully!");
//                    break;
//
//                case 7:
//                    System.out.println("Exiting...");
//                    break;
//
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//        } while (choice != 7);
//
//        scanner.close();
//    }
    }

