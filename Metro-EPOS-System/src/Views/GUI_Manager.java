package Views;
import Controllers.Branch;
import Controllers.Cashier;
import Views.Cashier.SalesData;
import Views.Cashier.addOns;
import Views.Cashier.showBill;
import Views.Frame.frame;

import javax.swing.*;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class GUI_Manager
{
    JPanel oldPanel;
    private SalesData sales;
    private addOns adds;

    public GUI_Manager()
    {
        adds=new addOns();
        frame f = new frame();

        sales = new SalesData("Asfandyar","1");
        sales.refreshPanel(null,0,f.getFrame());

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
                    if(!UIHandler.isProductExist(Integer.parseInt(pID))){
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
            adds.show(sales.getTotal(),f.getFrame());
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
                    File file;
                    JOptionPane.showMessageDialog(f.getFrame(),"Return Amount (Rs): " + returnAmount);
                    try {
                        file = UIHandler.showBillImage(sales.getPrintableList(),Double.parseDouble(adds.getReceivedAmonunt()),Double.parseDouble(adds.getAddionalCharges()),sales.getDiscountValue(),sales.getBranchID(),true);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    showBill bill = new showBill(file.getAbsolutePath(),f.getFrame());
                    adds.remove();
                    sales.refreshPanel(null,0,f.getFrame());
                }
            }
        });

        f.addPanel(sales.getPanel());
        oldPanel = sales.getPanel();
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
    public static void main(String[] args) {
        Branch branch = new Branch("Main Branch", 1, "123 Main St, Lahore","123-456-7890", 50, true);
        UIHandler.createCashier("Ahmad Shamail", "password123", "ahmad@example.com",
                "EMP123", "BR001", 50000, "01/01/2020",
                "N/A", true, branch, true);
        GUI_Manager g = new GUI_Manager();
    }
}