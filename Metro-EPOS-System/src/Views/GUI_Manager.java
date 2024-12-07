package Views;
import Views.Cashier.SalesData;
import Views.Cashier.addOns;
import Views.Frame.GifPlayer;
import Views.Frame.frame;
import Views.LogIn.AdminLogIn;
import Views.LogIn.CashierLogIn;
import Views.LogIn.DataOperatorLogIn;
import Views.LogIn.ManagerLogIn;
import Views.Manager.BranchInfo;
import Views.Manager.EmployeeInfo;
import Views.Operator.ExpandedInfo;
import Views.Operator.VendorInfo;
import org.apache.commons.logging.Log;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class GUI_Manager
{
    frame f;
    JPanel oldPanel;
    private SalesData sales;
    private addOns adds;
    private VendorInfo vendor;
    private ExpandedInfo operatorExpandedInfo;
    private AdminLogIn adminLogIn;
    private ManagerLogIn managerLogIn;
    private CashierLogIn cashierLogIn;
    private DataOperatorLogIn dataOperatorLogIn;
    private EmployeeInfo employeeInfo;
    private BranchInfo branchInfo;

    public GUI_Manager() {
        f = new frame();
        vendor = new VendorInfo();
        operatorExpandedInfo = new ExpandedInfo();
        adminLogIn = new AdminLogIn();
        managerLogIn = new ManagerLogIn();
        cashierLogIn = new CashierLogIn();
        dataOperatorLogIn = new DataOperatorLogIn();
        adds = new addOns(f.getFrame());
        sales = new SalesData();
        employeeInfo = new EmployeeInfo();
        branchInfo = new BranchInfo();
    }

    public void LogIn() {
        if(oldPanel!=null){
            f.replacePanel(oldPanel,adminLogIn.getPanel());
        }
        else{
            f.addPanel(adminLogIn.getPanel());
            f.show();
        }
        oldPanel = adminLogIn.getPanel();

        //----------------------------------ADMIN LOGIN PANEL LOGIC------------------------------//
        adminLogIn.getLogInButton().addActionListener(e->{
            String id = adminLogIn.getID();
            String pass = adminLogIn.getPass();
            if(!UIHandler.isNumbers(id) || id.trim().isEmpty()){
                JOptionPane.showMessageDialog(f.getFrame(),"Invalid ID","Error",JOptionPane.ERROR_MESSAGE);
            }
            else {

                String repsone = null;
                try {
                    repsone = UIHandler.isValidAdmin(id, pass);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (repsone.equals("not")) {
                    JOptionPane.showMessageDialog(f.getFrame(), "Account Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    adminLogIn.resetFields();
                    // admin panels
                }
            }
        });
        adminLogIn.getManagerButton().addActionListener(this::ActionPerformer);
        adminLogIn.getCashierButton().addActionListener(this::ActionPerformer);
        adminLogIn.getDataOperatorButton().addActionListener(this::ActionPerformer);

        //----------------------------------MANAGER LOGIN PANEL LOGIC------------------------------//
        managerLogIn.getLogInButton().addActionListener(e->{
            String id = managerLogIn.getID();
            String pass = managerLogIn.getPass();

            if(!UIHandler.isNumbers(id) || id.trim().isEmpty()){
                JOptionPane.showMessageDialog(f.getFrame(),"Invalid ID","Error",JOptionPane.ERROR_MESSAGE);
            }
            else {
                String repsone = null;
                try {
                    repsone = UIHandler.isValidManager(id, pass);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (repsone.equals("not")) {
                    JOptionPane.showMessageDialog(f.getFrame(), "Account Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String[] data = repsone.split(",");
                    managerLogIn.resetFields();
                    ManagerPanels(data[0], data[1]);
                }
            }
        });
        managerLogIn.getAdminButton().addActionListener(this::ActionPerformer);
        managerLogIn.getCashierButton().addActionListener(this::ActionPerformer);
        managerLogIn.getDataOperatorButton().addActionListener(this::ActionPerformer);

        //----------------------------------CASHIER LOGIN PANEL LOGIC------------------------------//
        cashierLogIn.getLogInButton().addActionListener(e->{
            String id = cashierLogIn.getID();
            String pass = cashierLogIn.getPass();

            if(!UIHandler.isNumbers(id) || id.trim().isEmpty()){
                JOptionPane.showMessageDialog(f.getFrame(),"Invalid ID","Error",JOptionPane.ERROR_MESSAGE);
            }
            else {

                String repsone = null;
                try {
                    repsone = UIHandler.isValidCashier(id, pass);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (repsone.equals("not")) {
                    JOptionPane.showMessageDialog(f.getFrame(), "Account Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String[] data = repsone.split(",");
                    cashierLogIn.resetFields();
                    CashierPanels(data[0], data[1]);
                }
            }
        });
        cashierLogIn.getAdminButton().addActionListener(this::ActionPerformer);
        cashierLogIn.getManagerButton().addActionListener(this::ActionPerformer);
        cashierLogIn.getDataOperatorButton().addActionListener(this::ActionPerformer);

        //----------------------------------Data Operator LOGIN PANEL LOGIC------------------------------//
        dataOperatorLogIn.getLogInButton().addActionListener(e->{
            String id = dataOperatorLogIn.getID();
            String pass = dataOperatorLogIn.getPass();

            if(!UIHandler.isNumbers(id) || id.trim().isEmpty()){
                JOptionPane.showMessageDialog(f.getFrame(),"Invalid ID","Error",JOptionPane.ERROR_MESSAGE);
            }
            else {
                String repsone = null;
                try {
                    repsone = UIHandler.isValidDataOperator(id, pass);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (repsone.equals("not")) {
                    JOptionPane.showMessageDialog(f.getFrame(), "Account Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String[] data = repsone.split(",");
                    dataOperatorLogIn.resetFields();
                    DataOpeatorPanels(data[0], data[1]);
                }
            }
        });
        dataOperatorLogIn.getAdminButton().addActionListener(this::ActionPerformer);
        dataOperatorLogIn.getManagerButton().addActionListener(this::ActionPerformer);
        dataOperatorLogIn.getCashierButton().addActionListener(this::ActionPerformer);
    }

    public void AdminPanels(String name){

    }

    public void ManagerPanels(String name, String branchID){
        employeeInfo.setNameBranch(name,branchID);
        employeeInfo.refreshPanel(UIHandler.getEmployeeInfo(Integer.parseInt(branchID)),f);
        f.replacePanel(oldPanel,employeeInfo.getPanel());
        oldPanel=employeeInfo.getPanel();

        //-----------------------BRANCH INFO PANEL LOGIC------------------------//
        branchInfo.setNameBranch(name,branchID);
        branchInfo.getLogoutButton().addActionListener(e->{
            branchInfo.resetFields();
            LogIn();
        });
        branchInfo.getEmployeeInfoButton().addActionListener(e->{
            employeeInfo.refreshPanel(UIHandler.getEmployeeInfo(Integer.parseInt(branchID)),f);
            f.replacePanel(oldPanel,employeeInfo.getPanel());
            oldPanel=employeeInfo.getPanel();
        });
        branchInfo.getEnterButton().addActionListener(e->{
            String start = branchInfo.getStartRange();
            String end = branchInfo.getEndRange();

            if(start.equals("dd/MM/yyyy") || end.equals("dd/MM/yyyy") || start.trim().isEmpty() || end.trim().isEmpty() || !UIHandler.isValidDate(start) || !UIHandler.isValidDate(end) || !UIHandler.isStartDateBeforeOrEqual(start, end)){
                JOptionPane.showMessageDialog(f.getFrame(),"Invalid Range","Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(UIHandler.DisplayChartRanged(start,end,"line")==null){
                JOptionPane.showMessageDialog(f.getFrame(),"Invalid Range","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                String type;
                if(branchInfo.getSelectedTime().equals("yearly")){
                    type = "line";
                }
                else{
                    type = "bar";
                }
                branchInfo.refreshPanel(UIHandler.getStocksDataofBranch(Integer.parseInt(branchID)),f,UIHandler.getBranchSales(Integer.parseInt(branchID),branchInfo.getSelectedTime()),UIHandler.getBranchRemaingingStock(Integer.parseInt(branchID),branchInfo.getSelectedTime()),UIHandler.getBranchProfit(Integer.parseInt(branchID),branchInfo.getSelectedTime()),UIHandler.DisplayChartRanged(start,end,"line"));
            }
        });

        //-------------------EMPLOYEE INFO PANEL LOGIC-----------------------------//
        employeeInfo.getLogoutButton().addActionListener(e->{
            employeeInfo.resetFields();
            LogIn();
        });
        employeeInfo.getAddButton().addActionListener(e->{
            String empName = employeeInfo.getEmpName();
            String newEmpSalary = employeeInfo.getEmpSalary();
            String newEmpPhoneNo = employeeInfo.getEmpNumber();
            String role = employeeInfo.getEmployeeRole();

            if(empName.trim().isEmpty() || empName.equals("  Type Name") || newEmpSalary.trim().isEmpty() || newEmpSalary.equals("  Enter Salary") || newEmpPhoneNo.trim().isEmpty() || newEmpPhoneNo.equals("  Type Phone No.")){
                JOptionPane.showMessageDialog(f.getFrame(),"Invalid Employee Info","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                String str = empName+","+newEmpSalary+","+newEmpPhoneNo+","+role;
                ArrayList<String> newList = UIHandler.addEmployeeInfo(Integer.parseInt(branchID),str);
                employeeInfo.refreshPanel(newList,f);
            }
        });
        employeeInfo.getSearchButton().addActionListener(e->{
            String search = employeeInfo.getSearched();
            if(search.trim().equals("Search") || search.trim().isEmpty()){
                employeeInfo.refreshPanel(UIHandler.getEmployeeInfo(Integer.parseInt(branchID)),f);
            }
            else
            {
                ArrayList<String> newList = new ArrayList<>();
                ArrayList<String> oldList = employeeInfo.getList();
                for(int i=0; i<oldList.size(); i++){
                    String[] data = oldList.get(i).split(",");
                    if(data[0].equals(search) || data[1].equals(search) || data[2].equals(search) || data[3].equals(search) || data[4].equals(search) || data[5].equals(search) || data[6].equals(search) || data[7].equals(search)){
                        newList.add(oldList.get(i));
                    }
                }
                employeeInfo.refreshPanel(newList,f);
            }
        });
        employeeInfo.getBranchInfoButton().addActionListener(e->{
            branchInfo.refreshPanel(UIHandler.getStocksDataofBranch(Integer.parseInt(branchID)),f,UIHandler.getBranchSales(Integer.parseInt(branchID),"today"),UIHandler.getBranchRemaingingStock(Integer.parseInt(branchID),"today"),UIHandler.getBranchProfit(Integer.parseInt(branchID),"today"),UIHandler.DisplayChart("daily","bar"));
            f.replacePanel(oldPanel,branchInfo.getPanel());
            oldPanel=branchInfo.getPanel();
        });

    }

    public void CashierPanels(String name, String branchID)
    {
        sales.setNamesBranch(name,branchID);
        sales.refreshPanel(null,0,f.getFrame());
        f.replacePanel(oldPanel,sales.getPanel());
        oldPanel = sales.getPanel();

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

                                String[] data=product.split(",");
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
            sales.resetFields();
            LogIn();
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
    }

    public void DataOpeatorPanels(String name , String branchID)
    {
        //--------------------------------DATA OPERATOR EXPANDED INFO PANEL LOGIC------------------//
        operatorExpandedInfo.getLogoutButton().addActionListener(e->{
            operatorExpandedInfo.resetFields();
            oldPanel=operatorExpandedInfo.getPanel();
            LogIn();
        });
        operatorExpandedInfo.getBackButton().addActionListener(e->{
            operatorExpandedInfo.resetFields();
            ArrayList<String> vendorsList = UIHandler.getVendorsList(Integer.parseInt(branchID));
            vendor.refreshPanel(vendorsList,f,operatorExpandedInfo);
            f.replacePanel(operatorExpandedInfo.getPanel(),vendor.getPanel());
            oldPanel = vendor.getPanel();
        });
        operatorExpandedInfo.getAddButton().addActionListener(e->{
            ArrayList<String> list = operatorExpandedInfo.getList();
            int id = operatorExpandedInfo.getVendorID();
            operatorExpandedInfo.refreshPanel(list,f.getFrame(),id,true);
        });

        //--------------------------------VENDOR INFO PANEL LOGIC------------------//
        vendor.setNameBranch(name,branchID);
        ArrayList<String> vendorsList = UIHandler.getVendorsList(Integer.parseInt(branchID));
        vendor.refreshPanel(vendorsList,f,operatorExpandedInfo);
        f.replacePanel(oldPanel,vendor.getPanel());
        oldPanel = vendor.getPanel();

        vendor.getLogoutButton().addActionListener(e->{
            vendor.resetFields();
            LogIn();
        });
        vendor.getAddButton().addActionListener(e->{
            String vName = vendor.getVendorName();
            String vAddress = vendor.getVendorAddress();
            String city = vendor.getVendorCity();
            int bID = vendor.getBranchID();

            if(vName.trim().isEmpty() || vName.equals("  Type Name") || city.trim().isEmpty() || city.equals("  Enter City Name") || vAddress.trim().isEmpty() || vAddress.equals("  Type Address")){
                JOptionPane.showMessageDialog(f.getFrame(),"Invalid Information Entered","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                ArrayList<String> newList = UIHandler.addVendor(bID,vName,vAddress,city);
                vendor.refreshPanel(newList,f,operatorExpandedInfo);
            }
        });
        vendor.getSearchButton().addActionListener(e->{
            String search = vendor.getSearched();
            if(search.trim().equals("Search") || search.trim().isEmpty()){
                vendor.refreshPanel(UIHandler.getVendorsList(Integer.parseInt(branchID)),f,operatorExpandedInfo);
            }
            else
            {
                ArrayList<String> newList = new ArrayList<>();
                ArrayList<String> oldList = vendor.getList();
                for(int i=0; i<oldList.size(); i++){
                    String[] data = oldList.get(i).split(",");
                    if(data[0].equals(search) || data[1].equals(search) || data[2].equals(search) || data[3].equals(search) || data[4].equals(search) || data[5].equals(search)){
                        newList.add(oldList.get(i));
                    }
                }
                vendor.refreshPanel(newList,f,operatorExpandedInfo);
            }
        });
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

    private void ActionPerformer(ActionEvent e) {

        if(e.getSource()==managerLogIn.getAdminButton() || e.getSource()==cashierLogIn.getAdminButton() || e.getSource()==dataOperatorLogIn.getAdminButton()){
            f.replacePanel(oldPanel,adminLogIn.getPanel());
            oldPanel = adminLogIn.getPanel();
            managerLogIn.resetFields();
            cashierLogIn.resetFields();
            dataOperatorLogIn.resetFields();
        }
        else if(e.getSource()==adminLogIn.getManagerButton() || e.getSource()==cashierLogIn.getManagerButton() || e.getSource()==dataOperatorLogIn.getManagerButton()){
            f.replacePanel(oldPanel,managerLogIn.getPanel());
            oldPanel = managerLogIn.getPanel();
            adminLogIn.resetFields();
            cashierLogIn.resetFields();
            dataOperatorLogIn.resetFields();
        }
        else if(e.getSource()==adminLogIn.getCashierButton() || e.getSource()==managerLogIn.getCashierButton() || e.getSource()==dataOperatorLogIn.getCashierButton()){
            f.replacePanel(oldPanel,cashierLogIn.getPanel());
            oldPanel=cashierLogIn.getPanel();
            adminLogIn.resetFields();
            managerLogIn.resetFields();
            cashierLogIn.resetFields();
        }
        else if(e.getSource()==adminLogIn.getDataOperatorButton() || e.getSource()==managerLogIn.getDataOperatorButton() || e.getSource()==cashierLogIn.getDataOperatorButton()){
            f.replacePanel(oldPanel,dataOperatorLogIn.getPanel());
            oldPanel = dataOperatorLogIn.getPanel();
            adminLogIn.resetFields();
            managerLogIn.resetFields();
            cashierLogIn.resetFields();
        }
    }

    public static void main(String[] args)
    {
        Thread splashThread = new Thread(() -> {
            GifPlayer gifPlayer = new GifPlayer();
        });
        splashThread.start();

        Thread GUI = new Thread(() -> {
            GUI_Manager g = new GUI_Manager();
            try {
                splashThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            g.LogIn();
        });
        GUI.start();
    }


}