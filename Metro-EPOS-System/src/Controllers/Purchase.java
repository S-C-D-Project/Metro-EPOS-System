package Controllers;

import Models.DataBaseHandler;

import java.util.Date;

public class Purchase {
    private int purchaseID;
    private String purchaseDate;
    private int purchaseAmount;
    private Date date;
    private int vendorId;  // Added vendorId to link a purchase to a vendor

    // Constructor
    public Purchase(int purchaseID, String purchaseDate, int purchaseAmount, Date date, int vendorId) {
        this.purchaseID = purchaseID;
        this.purchaseDate = purchaseDate;
        this.purchaseAmount = purchaseAmount;
        this.date = date;
        this.vendorId = vendorId;
    }

    // Getter and Setter for purchaseID
    public int getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    // Getter and Setter for purchaseDate
    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    // Getter and Setter for purchaseAmount
    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    // Getter and Setter for date
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Getter and Setter for vendorId (new field)
    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }


}
