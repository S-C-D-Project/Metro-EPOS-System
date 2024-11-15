package Views;
import Views.Cashier.SalesData;
import Views.Frame.frame;

import javax.swing.*;
import java.util.ArrayList;

public class GUI_Manager
{
    JPanel oldPanel;
    private SalesData sales;

    public GUI_Manager()
    {
        frame f = new frame();

        sales = new SalesData("Asfandyar","1234");
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
            else if(!UIHandler.isProductExist(Integer.parseInt(pID))){
                JOptionPane.showMessageDialog(f.getFrame(),"Product Not Found","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                if(list==null){
                    list=new ArrayList<>();
                }
                list.add(UIHandler.getProductName(Integer.parseInt(pID))+","+qty+","+UIHandler.getProductPrice(Integer.parseInt(pID), Integer.parseInt(qty)));
                sales.refreshPanel(list,discount,f.getFrame());
            }
        });
        sales.getLogoutButton().addActionListener(e->{
            JOptionPane.showMessageDialog(f.getFrame(),"Logout Pressed","Message",JOptionPane.INFORMATION_MESSAGE);
        });
        sales.getPrintButton().addActionListener(e->{
            sales.refreshPanel(null,0,f.getFrame());
        });
        sales.getFixButton().addActionListener(e->{
            String discountStr = sales.getDiscount();
            ArrayList<String> list = sales.getList();
            if(!isValidDiscount(discountStr) || discountStr.trim().isEmpty())
            {
                JOptionPane.showMessageDialog(f.getFrame(),"Invalid Discount","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                sales.refreshPanel(list, Double.parseDouble(discountStr),f.getFrame());
            }
        });

        f.addPanel(sales.getPanel());
        oldPanel = sales.getPanel();
        f.show();
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
        GUI_Manager g = new GUI_Manager();
    }
}