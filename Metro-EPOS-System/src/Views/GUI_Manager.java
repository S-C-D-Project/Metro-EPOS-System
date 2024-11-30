package Views;
import Controllers.Branch;
import Views.Cashier.SalesData;
import Views.Cashier.addOns;
import Views.Decorate.LogInTheme;
import Views.Frame.frame;
import Views.LogIn.AdminLogIn;
import Views.LogIn.ManagerLogIn;
import Views.Operator.ExpandedInfo;
import Views.Operator.VendorInfo;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GUI_Manager
{
    JPanel oldPanel;
    private SalesData sales;
    private addOns adds;
    private VendorInfo vendor;
    private ExpandedInfo operatorExpandedInfo;
    private AdminLogIn adminLogIn;
    private ManagerLogIn managerLogIn;

    public void LogIn(){

    }

    public GUI_Manager() throws IOException {
        frame f = new frame();
        adminLogIn = new AdminLogIn();
        managerLogIn = new ManagerLogIn();
        adds=new addOns(f.getFrame());
        sales = new SalesData("Asfandyar","1");
        vendor = new VendorInfo("Asfandyar","1");
        operatorExpandedInfo = new ExpandedInfo("Asfandyar","1");

        ArrayList<String> helo = new ArrayList<>();
        for(int i=0; i<5; i++){
            if(i==3){
                helo.add("123,Electronicals,Islamabad,190-C Muslim Town,2,Inactive");
            }
            else{
                helo.add("123,Electronicals,Islamabad,190-C Muslim Town,2,Active");
            }
        }
        operatorExpandedInfo.refreshPanel(helo,f.getFrame(),1,false);

        //-------------------CASHIER PANEL LOGIC----------------------------
        sales.getEnterButton().addActionListener(e->{
            String pID = sales.getProductID();
            String qty = sales.getQuantity();

            ArrayList<String> list = sales.getList();
            double discount = sales.getDiscountValue();

            if(pID.trim().isEmpty() || qty.trim().isEmpty() || !isNumbers(pID) || !isNumbers(qty) || qty.equals("0")){
                JOptionPane.showMessageDialog(f.getFrame(),"Invalid Values Entered","Error",JOptionPane.ERROR_MESSAGE);
            }
            else {
                try {
                    if(!UIHandler.isProductExist(Integer.parseInt(pID),Integer.parseInt(qty))){
                        JOptionPane.showMessageDialog(f.getFrame(),"Product Not Found","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        if(list==null)
                        {
                            list=new ArrayList<>();
                        }
                        if(checkProductDuplication(pID,list)) {


                            list.add(UIHandler.getProductName(Integer.parseInt(pID)) + "," + qty + "," + UIHandler.getProductPrice(Integer.parseInt(qty)) + "," + pID);
                        }else{
                            int i=0;
                            for(String product:list){

                                String data[]=product.split(",");
                                if(data[3].equals(pID)){
                                    data[1]=String.valueOf(Integer.parseInt(data[1])+Integer.parseInt(qty));
                                data[2]=String.valueOf(UIHandler.getProductPriceUsingName(Integer.parseInt(data[3]), sales.getBranchID(),Integer.parseInt(data[1])));

                                    list.set(i,data[0]+","+data[1]+","+data[2]+","+data[3]);
                                }
                                i++;

                            }
                        }
                        sales.refreshPanel(list,discount,f.getFrame());

                        }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        sales.getLogoutButton().addActionListener(e->{
            JOptionPane.showMessageDialog(f.getFrame(),"Logout Pressed","Message",JOptionPane.INFORMATION_MESSAGE);
        });
        sales.getPrintButton().addActionListener(e->{
            adds.show(sales.getTotal());
        });
        sales.getFixButton().addActionListener(e->{
            String discountStr = sales.getDiscount();
            ArrayList<String> list = sales.getList();
            if(!isValidDiscount(discountStr) || discountStr.trim().isEmpty() || Double.parseDouble(discountStr)<0 || Double.parseDouble(discountStr)>100)
            {
                JOptionPane.showMessageDialog(f.getFrame(),"Invalid Discount","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                sales.refreshPanel(list, Double.parseDouble(discountStr),f.getFrame());
            }
        });
        //--------------------------------ADDITIONAL PANEL LOGIC------------------//
        adds.getCancel_Button().addActionListener(e->{
            adds.remove();
        });
        adds.getOk_Button().addActionListener(e->{
            if(adds.getReceivedAmonunt().trim().isEmpty() || adds.getAddionalCharges().trim().isEmpty() || sales.getPrintableList().isEmpty() || !isValidDiscount(adds.getAddionalCharges()) || !isValidDiscount(adds.getReceivedAmonunt()) || Double.parseDouble(adds.getAddionalCharges())<0 || Double.parseDouble(adds.getReceivedAmonunt())<0){
                if(sales.getPrintableList().isEmpty()){
                    JOptionPane.showMessageDialog(f.getFrame(),"No Item Added to List","Error",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(f.getFrame(),"Invalid Entries","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                double total = Double.parseDouble(adds.getAddionalCharges()) + adds.getTotal();
                if(Double.parseDouble(adds.getReceivedAmonunt())<total){
                    JOptionPane.showMessageDialog(f.getFrame(),"Received Amount is Less than Total","Error",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    double returnAmount = Double.parseDouble(adds.getReceivedAmonunt()) - total;
                    double roundedValue = Math.round(returnAmount * 100.0)/100.0;
                    File file;
                    JOptionPane.showMessageDialog(f.getFrame(),"Return Amount (Rs): " + roundedValue);
                    try {
                        file = UIHandler.showBillImage(sales.getPrintableList(),Double.parseDouble(adds.getReceivedAmonunt()),Double.parseDouble(adds.getAddionalCharges()),sales.getDiscountValue(),sales.getBranchID(),false);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    adds.remove();
                    sales.resetFields();
                    sales.refreshPanel(null,0,f.getFrame());
                    UIHandler.deleteTempBill(file);
                }
            }
        });

        //--------------------------------DATA OPERATOR EXPANDED INFO PANEL LOGIC------------------//
        operatorExpandedInfo.getLogoutButton().addActionListener(e->{
            JOptionPane.showMessageDialog(f.getFrame(),"Logout Pressed","Message",JOptionPane.INFORMATION_MESSAGE);
        });
        operatorExpandedInfo.getBackButton().addActionListener(e->{
            f.replacePanel(oldPanel,vendor.getPanel());
            oldPanel = vendor.getPanel();
        });
        operatorExpandedInfo.getAddButton().addActionListener(e->{
            ArrayList<String> list = operatorExpandedInfo.getList();
            int id = operatorExpandedInfo.getVendorID();
            operatorExpandedInfo.refreshPanel(list,f.getFrame(),id,true);
        });

        f.addPanel(managerLogIn.getPanel());
        oldPanel = operatorExpandedInfo.getPanel();
        f.show();
    }

    public static boolean checkProductDuplication(String id, ArrayList<String> list) {
        for(String product:list){
            String data[]=product.split(",");
            if(data[3].equals(id)){
                return false;
            }

        }
        return true;
    }

    public static boolean isValidDiscount(String line) {
        for(int i=0; i<line.length(); i++){
            char c = line.charAt(i);
            if (!Character.isDigit(c) && c!='.') {
                return false;
            }
        }
        return true;
    }
    public static boolean isNumbers(String line) {
        for(int i=0; i<line.length(); i++){
            char c = line.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        Branch branch = new Branch("Main Branch", 1, "123 Main St, Lahore","123-456-7890", 50, true);
        UIHandler.createCashier("Ahmad Shamail", "password123", "ahmad@example.com",
                "EMP123", "BR001", 50000, "01/01/2020",
                "N/A", true, branch, true);
        GUI_Manager g = new GUI_Manager();
    }
}