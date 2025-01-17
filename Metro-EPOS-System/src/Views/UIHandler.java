package Views;

import Controllers.*;
import Models.DataBaseHandler;
import Models.VendorModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static Controllers.PDFGenerator.generateChartPDF;
import static Models.VendorModel.insertVendor;


public class UIHandler {
    private static Cashier cashier = new Cashier();
    private static BranchManager branchManager = new BranchManager();
    private static DataEntryOperator dataEntryOperator = new DataEntryOperator();
    private static SuperAdmin superAdmin;
private static ArrayList<String>employeeList=new ArrayList<>();
    private static ArrayList<String>branchList=new ArrayList<>();

    public static boolean isProductExist(int pID, int qty) throws SQLException {

        return cashier.isProductExist(pID, cashier.getBranch().getId(), qty);
    }

    public static boolean isNewAdmin(String id, String pass){
        return false;
    }
    public static boolean isNewBranchManager(String id, String pass){return branchManager.isFirstTime();
    }
    public static boolean isNewCashier(String id, String pass){
        return cashier.isFirstTime();
    }
    public static boolean isNewOperator(String id, String pass){
        return dataEntryOperator.isFirstTime();
    }

    public static String getProductPrice(int qty) {
        return String.valueOf(qty * cashier.getProductPrice());
    }

    public static double getProductPriceUsingName(int id, int branchid, int qty) throws SQLException {

        return cashier.getProductPriceByid(id, branchid) * qty;
    }

    public static String getProductName(int pID) {

        return cashier.getProductName();
    }

    public static File showBillImage(ArrayList<String> list, double cashAmount, double additionalAmount, double discount, int branchid, boolean isVendor) throws Exception {
        return cashier.saveBill(list, (int) cashAmount, (int) additionalAmount, discount, branchid, isVendor);
    }

    public static void deleteTempBill(File file) {
        cashier.deleteTempBill(file);

    }

    public static String isValidAdmin(String id, String pass) throws SQLException {
        if (superAdmin == null) {
            superAdmin = SuperAdmin.getInstance(id, pass, "superAdmin");

        } if (superAdmin != null) {
            return superAdmin.getName() + "," + superAdmin.getEmployeeNumber();
        } else {
            return "not";
        }
    }
public static void superAdminlogout(){
        superAdmin.logout();
        superAdmin = null;
}
    public static String isValidManager(String id, String pass) throws SQLException {

        if (branchManager != null) {
            branchManager = (BranchManager) branchManager.vallidateEmployee(id, pass, "manager");
           if(branchManager!=null){
            return branchManager.getName() + "," + branchManager.getBranch().getId();
        } else{ return "not";}
        }
        else {
            return "not";
        }
    }

    public static String isValidCashier(String id, String pass) throws SQLException {
        if (cashier != null) {
            cashier = (Cashier) cashier.vallidateEmployee(id, pass, "cashier");
            if (cashier != null) {

                return cashier.getName() + "," + cashier.getBranch().getId();
            } else {
                return "not";
            }
        } else {
            return "not";
        }
    }


    public static String isValidDataOperator(String id, String pass) throws SQLException {
        dataEntryOperator= (DataEntryOperator) dataEntryOperator.vallidateEmployee(id,pass,"operator");
        boolean result= DataBaseHandler.isValidDataOperator(id,pass);
        String name= DataBaseHandler.getEmployeeName(id);
        String branch= DataBaseHandler.getEmployeeBranch(id);
        dataEntryOperator=new DataEntryOperator();
        dataEntryOperator.setid(Integer.parseInt(branch));
        if(result){
            return name+","+branch;
        }
        else {
            return "not";
        }

    }

    public static ArrayList<String> getVendorsList(int branchID) {
        ArrayList<String> list = DataBaseHandler.getVendorsList(branchID);
        return list;
    }

    public static ArrayList<String> updateVendorInfo(int id, String str) {
        String[] values = str.split(",");
        DataBaseHandler.updateVendorInfo(id, values[0], values[1], values[2], values[4]);
        ArrayList<String> list = DataBaseHandler.getVendorsList(dataEntryOperator.getid());
        return list;
    }

    public static ArrayList<String> addVendor(int branchId, String vendorName, String vendorAddress, String vendorCity) {
        String status = "Inactive";
        insertVendor(vendorName, vendorCity, vendorAddress, status, branchId);
        return getVendorsList(branchId);

    }

    public static ArrayList<String> getVendorProducts(int Vid) {
        return DataBaseHandler.getVendorProducts(Vid);
    }

    public static ArrayList<String> addNewVendorProduct(int vID, String str) {
        String[] values = str.split(",");
        String sample_manufaturer = values[6];
        System.out.println(sample_manufaturer);
        int branch = dataEntryOperator.getid();
        System.out.println(branch);
        DataBaseHandler.addOrUpdateProductAndPurchase(branch, values[1], values[0], sample_manufaturer, Float.parseFloat(values[2]), Integer.parseInt(values[3]),
                Float.parseFloat(values[4]), vID, DataBaseHandler.getVendorName(vID), values[7], Integer.parseInt(values[5]));

        ArrayList<String> list = DataBaseHandler.getVendorProducts(vID);

        return list;
    }

    public static ArrayList<String> updateVendorProductInfo(int vID, String str, String productName) {

        String[] values = str.split(",");
        int productid = DataBaseHandler.getProductidbyName(productName.trim());
        boolean result = DataBaseHandler.updateProductInfo(vID, productid, values[1], values[0], values[2], Integer.parseInt(values[3]), values[4], Integer.parseInt(values[5]), values[6], values[7]);
        if (!result) {
            return null;
        } else {
            ArrayList<String> list = DataBaseHandler.getVendorProducts(vID);

            return list;
        }
    }

    public static ArrayList<String> deleteVendorProduct(int id, String catagory, String name, String originalPrice, String salesPrice, String pricePerUnit) {

        int prodid = DataBaseHandler.getProductidbyName(name);
        boolean result = DataBaseHandler.deleteProductByVendorId(id, prodid);
        if (!result) {
            return null;
        }
        ArrayList<String> list = DataBaseHandler.getVendorProducts(id);

        return list;
    }

    public static ArrayList<String> getEmployeeInfo(int branchID) {

        employeeList= branchManager.getEmployeesByBranch(branchID);
        return employeeList;
    }

    public static ArrayList<String> addEmployeeInfo(int branchID, String str) {

        String []employee=str.split(",");
        int id= branchManager.addEmployee(employee[0], "email", Integer.parseInt(employee[1]), branchID, employee[3]);
        String employeeInfo = id+","+employee[0]+","+id+"@gmail.com"+","+"123"+","+employee[1]+","+employee[2]+","+employee[3]+","+"Active";
        employeeList.add(employeeInfo);
        return employeeList;
    }
    public static ArrayList<String> updateEmployeeInfo(int empID, int branchID, String str) {
        String[] data = str.split(",");
        if (data.length != 7) {
            throw new IllegalArgumentException("Invalid input format for employee data.");
        }

        Employee employee = new BranchManager(
                data[0], // Name
                data[2], // Email
                data[1], // Password
                empID,   // Employee ID
                branchID, // Branch ID
                Integer.parseInt(data[3]), // Salary
                Boolean.parseBoolean(data[6]), // Is Active
                data[5], // Role
                data[4]  // Phone Number
        );

        branchManager.updateEmployee(employee);

        for (int i = 0; i < employeeList.size(); i++) {
            String[] existingData = employeeList.get(i).split(",");
            if (Integer.parseInt(existingData[0]) == empID) {
                employeeList.set(i,empID+","+ employee.toString());
                break;
            }
        }

        return employeeList;
    }


    public static ArrayList<String> getStocksDataofBranch(int branchID) {
        // i provide branchID and should get the (productsName,Stocks status). If stocks are 0
        // it should provide Out of Stock as status
        ArrayList<String> list = DataBaseHandler.getProductsByBranchId(branchID);
        return list;
    }

    public static ArrayList<String> addBranch(String bName, String city, String address) throws SQLException {
       int branchid=branchManager.addBranch(bName,address,city);
       String branch=branchid+","+bName+","+city+","+address+","+0+","+"active";
       branchList.add(branch);
        return branchList;
    }

    public static String getAllBranchIDs() throws SQLException {
        // return all ids there are for a branch
        String ids = branchManager.getAllBranchIds();
        return ids;
    }

    public static ArrayList<String> getAllEmployees() {
        // returns emp info like this
       employeeList=superAdmin.getEmployees();
//        list.add("1,123,Asfandyar,1111@gmail.com,Password_123,500,12345678901,Manager,Active");
//        list.add("1,123,Asfandyar,1111@gmail.com,Password_123,500,12345678901,Manager,Active");
//        list.add("1,123,Asfandyar,1111@gmail.com,Password_123,500,12345678901,Manager,Inactive");
//        list.add("1,123,Asfandyar,1111@gmail.com,Password_123,500,12345678901,Manager,Active");
//        return list;
   return employeeList;
    }

    public static ArrayList<String> getAllVendorsList() {
        // returns vendors info like this
        ArrayList<String> list = DataBaseHandler.getVendorPurchaseData();
        return list;
    }

    public static ArrayList<String> updateAdminVendorInfo(String vID, String str) {

        String []data=str.split(",");
        DataBaseHandler.updateVendorData(Integer.parseInt(vID),data);
        ArrayList<String> list = DataBaseHandler.getVendorPurchaseData();
        return list;
    }

    public static ArrayList<String> addEmployeeforAdmin(String name, String salary, String phoneNo) {
        // add employee as this method can add manager too and return updaye list like this

        int id= branchManager.addEmployee(name, "email", Integer.parseInt(salary), 0, null);
        String employeeInfo = id+","+null+","+name+","+id+"@gmail.com"+","+"123"+","+salary+","+null+","+null+","+"Active";
        employeeList.add(employeeInfo);
        return employeeList;
    }

    public static ArrayList<String> addVendorforAdmin(String name, String city, String address) {
        // adds vendor and return updated list like this
        insertVendor(name,city,address,"Inactive",1);
        ArrayList<String> list = DataBaseHandler.getVendorPurchaseData();

        return list;
    }

    public static ArrayList<String> updateAdminsEmployeeInfo(int empID, String str) {
        String[] data = str.split(",");
        if (data.length != 8) {
            throw new IllegalArgumentException("Invalid input format for employee data.");
        }
        System.out.println("Updating with: " + str);

        Employee updatedEmployee = new BranchManager(
                data[1],

                data[3],
                data[2],
                empID,
                Integer.parseInt(data[0]),
                Integer.parseInt(data[4]),
                "Active".equalsIgnoreCase(data[7]),
                data[6],
                data[5]
        );
        branchManager.updateEmployee(updatedEmployee);

        for (int i = 0; i < employeeList.size(); i++) {
            String[] existingData = employeeList.get(i).split(",");

            if (existingData.length > 1 && Integer.parseInt(existingData[0]) == empID) {
                String updatedRecord = String.format("%d,%d,%s,%s,%s,%d,%s,%s,%s",
                        updatedEmployee.getEmployeeNumber(),
                        updatedEmployee.getBranchid(),
                        updatedEmployee.getName(),
                        updatedEmployee.getEmail(),
                        updatedEmployee.getPassword(),
                        updatedEmployee.getSalary(),
                        updatedEmployee.getPhoneNumber(),
                        updatedEmployee.getRole(),
                        updatedEmployee.isActive() ? "Active" : "Inactive"
                );

                employeeList.set(i, updatedRecord);
                break;
            }
        }

        return employeeList;


//        ArrayList<String> list = new ArrayList<>();
//        list.add("1,123,Asfandyar,1111@gmail.com,Password_123,500,12345678901,Manager,Active");
//        list.add("1,123,Asfandyar,1111@gmail.com,Password_123,500,12345678901,Manager,Active");
//        list.add("1,123,Asfandyar,1111@gmail.com,Password_123,500,12345678901,Manager,Inactive");
//        list.add("1,123,Asfandyar,1111@gmail.com,Password_123,500,12345678901,Manager,Active");
//        return list;
    }

    public static ArrayList<String> getAllBranchInfo() throws SQLException {
        //all branch info in example down below should be returned

//        ArrayList<String> list = new ArrayList<>();
//        list.add("123,METRO,Lahore,190-C Muslim Town,100,Active");
//        list.add("123,METRO,Lahore,190-C Muslim Town,100,Active");
//        list.add("123,METRO,Lahore,190-C Muslim Town,100,Inactive");
//        list.add("123,METRO,Lahore,190-C Muslim Town,100,Active");
        branchList=new ArrayList<>();
String [] branches=branchManager.getAllBranches();
        for(String data:branches){
            branchList.add(data);
        }
        return branchList;
    }

    public static ArrayList<String> updateBranchesInfo(int branchID, String str) throws SQLException {
        String[] parsedData = parseBranchData(str);
        String name = parsedData[0];
        String city = parsedData[1];
        String address = parsedData[2];
        int numberOfEmployees = Integer.parseInt(parsedData[3]);
        Boolean status = Boolean.parseBoolean(parsedData[4]);

        branchManager.updateBranch(branchID, name, city, address, numberOfEmployees, status);

        updateBranchList(branchID, name, city, address, numberOfEmployees, parsedData[4]);


//    ArrayList<String> list = new ArrayList<>();
//        list.add("123,METRO,Lahore,190-C Muslim Town,100,Active");
//        list.add("123,METRO,Lahore,190-C Muslim Town,100,Active");
//        list.add("123,METRO,Lahore,190-C Muslim Town,100,Inactive");
//        list.add("123,METRO,Lahore,190-C Muslim Town,100,Active");
       return branchList;
    }
    public static String[] parseBranchData(String str) {
        String[] data = str.split(",");
        if (data.length == 5) {
            return data;
        } else {
            throw new IllegalArgumentException("Invalid input data format. Expected format: name,city,address,employees,status");
        }
    }
    private static void updateBranchList(int branchID, String name, String city, String address, int numberOfEmployees, String status) {
        for (int i = 0; i < branchList.size(); i++) {
            String branch = branchList.get(i);
            String[] branchData = branch.split(",");
            int existingBranchID = Integer.parseInt(branchData[0]);

            if (existingBranchID == branchID) {
                 String updatedBranch = String.format("%d,%s,%s,%s,%d,%s", branchID, name, city, address, numberOfEmployees, status);
                branchList.set(i, updatedBranch);
                return;
            }
        }
        String newBranch = String.format("%d,%s,%s,%s,%d,%s", branchID, name, city, address, numberOfEmployees, status);
        branchList.add(newBranch);
    }
    public static int getBranchSales(int branchID, String type) {
        //I will provide with the branch id and i should get the branch Sales and in type
        //i will specify if it is daily,weekly,monthly,yearly
        System.out.println(type);
        return DataBaseHandler.getTotalSales(branchID,type);
    }

    public static int getBranchRemaingingStock(int branchID, String type) {
        //I will provide with the branch id and i should get the branch remaining stocks
        // and in type i will specify if it is daily,weekly,monthly,yearly

        return DataBaseHandler.getTotalStockQuantity(branchID,type);
    }

    public static int getBranchProfit(int branchID, String type) {
        //I will provide with the branch id and i should get the branch profit and in type
        //i will specify if it is daily,weekly,monthly,yearly
        return DataBaseHandler.getTotalProfit(branchID,type);
    }

    public static int getBranchSalesRange(int branchID, String start, String end) {
        //I will provide with the branch id and i should get the branch Sales and
        // with start and end dates
        return 200;
    }

    public static int getBranchRemaingingStockRange(int branchID, String start, String end) {
        //I will provide with the branch id and i should get the branch remaining stocks
        // with start and end dates
        return 200;
    }

    public static int getBranchProfitRange(int branchID, String start, String end) {
        //I will provide with the branch id and i should get the branch profit with
        // start and end dates
        return 200;
    }

    //time can be daily, weekly, monthly or yearly, if time given is wrong it returns null
    //Chart types are line and bar
    public static ChartPanel DisplayChart(String Time, String chartType) {
        ArrayList<Integer> profitData = new ArrayList<>();
        ArrayList<String> profitTimeData = new ArrayList<>();
        boolean isYearly = Time.equalsIgnoreCase("yearly");
        boolean isDaily = Time.equalsIgnoreCase("daily");

        // Fetch profit data based on time period
        if (isDaily) {
            profitTimeData = DataBaseHandler.getProfitAndTimeForToday();
        } else {
            profitData = DataBaseHandler.getProfitData(Time);
        }

        XYSeries series = new XYSeries("Profit");

        if (isDaily) {
            for (int i = 0; i < profitTimeData.size(); i++) {
                String[] data = profitTimeData.get(i).split(", ");
                String profitStr = data[0].split(": ")[1];
                String timeStr = data[1].split(": ")[1];

                int profit = Integer.parseInt(profitStr);
                int hour = Integer.parseInt(timeStr);

                series.add(hour, profit);
            }
        } else {
            for (int i = 0; i < profitData.size(); i++) {
                series.add(i + 1, profitData.get(i));
            }
        }

        XYSeriesCollection lineDataset = new XYSeriesCollection(series);
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();

        if (isDaily) {

            for (int i = 0; i < profitTimeData.size(); i++) {
                String[] data = profitTimeData.get(i).split(", ");
                String profitStr = data[0].split(": ")[1];
                String timeStr = data[1].split(": ")[1];

                int profit = Integer.parseInt(profitStr);
                int hour = Integer.parseInt(timeStr);

                barDataset.addValue(profit, "Profit", "Hour " + hour);
            }
        } else {

            for (int i = 0; i < profitData.size(); i++) {
                if (isYearly) {
                    String monthName = java.time.Month.of(i + 1).name();
                    barDataset.addValue(profitData.get(i), "Profit", monthName);
                } else {
                    barDataset.addValue(profitData.get(i), "Profit", "Day " + (i + 1));
                }
            }
        }

        JFreeChart chart;

        if (chartType.equalsIgnoreCase("line")) {
            chart = ChartFactory.createXYLineChart(
                    "Profit Line Chart",
                    isDaily ? "Hour" : isYearly ? "Month" : "Day",
                    "Profit",
                    lineDataset,
                    org.jfree.chart.plot.PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            XYPlot plot = chart.getXYPlot();
            plot.setDomainGridlinesVisible(true);
            plot.setRangeGridlinesVisible(true);
            plot.setBackgroundPaint(Color.WHITE);
            plot.getRenderer().setSeriesStroke(0, new java.awt.BasicStroke(3.0f));  // Bold line

            NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
            domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        } else if (chartType.equalsIgnoreCase("bar")) {
            chart = ChartFactory.createBarChart(
                    "Profit Bar Chart",
                    isDaily ? "Hour" : isYearly ? "Month" : "Day",
                    "Profit",
                    barDataset,
                    org.jfree.chart.plot.PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            CategoryPlot plot = chart.getCategoryPlot();
            plot.setBackgroundPaint(Color.WHITE);
            plot.getRenderer().setSeriesPaint(0, Color.getHSBColor(120 / 360f, 1.0f, 0.2f));  // Color for bars
        } else {
            System.out.println("Invalid chart type.");
            return null;
        }

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        return chartPanel;

    }

    //Start date: dd/mm/yyyy , enddate: dd/mm/yyyy, chartype= line/bar
    public static ChartPanel DisplayChartRanged(String startDate, String endDate, String chartType) {
        try {
            // Use the correct date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            // Parse start and end dates
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);

            // Check date range is within 1 year (365 days)
            boolean isYearly = false;
            long daysBetween = ChronoUnit.DAYS.between(start, end);
            if (daysBetween == 365)
                isYearly = true;
            if (daysBetween > 365 || daysBetween < 0) {

                System.out.println("Invalid date range: exceeds one year or is negative.");
                return null;
            }

            // Initialize profit data variables
            long diffInDays = daysBetween;
            int diffInMonths = (int) (diffInDays / 30);
            boolean isDaily = diffInDays == 0;

            ArrayList<Integer> profitData = new ArrayList<>();
            ArrayList<String> hourlyProfitData = new ArrayList<>();

            // Fetch data based on date range
            if (isDaily) {
                hourlyProfitData = DataBaseHandler.getProfitAndTimeForSpecificDay(startDate);
            } else {
                if (diffInMonths > 1) {
                    profitData = DataBaseHandler.getMonthlyProfitData(startDate, endDate);
                } else {
                    profitData = DataBaseHandler.getProfitDataForTimeSlot(startDate, endDate);
                }
            }
            // Create the chart
            XYSeries series = new XYSeries("Profit");
            if (isDaily) {
                for (String data : hourlyProfitData) {
                    String[] parts = data.split(", ");
                    int profit = Integer.parseInt(parts[0].split(": ")[1]);
                    int hours = Integer.parseInt(parts[1].split(": ")[1]);
                    series.add(hours, profit);
                }
            } else {
                for (int i = 0; i < profitData.size(); i++) {
                    series.add(i + 1, profitData.get(i));
                }
            }

            XYSeriesCollection lineDataset = new XYSeriesCollection(series);
            DefaultCategoryDataset barDataset = new DefaultCategoryDataset();

            if (isDaily) {
                for (String data : hourlyProfitData) {
                    String[] parts = data.split(", ");
                    int profit = Integer.parseInt(parts[0].split(": ")[1]);
                    String hoursStr = parts[1].split(": ")[1];
                    barDataset.addValue(profit, "Profit", "Hour " + hoursStr);
                }
            } else {
                for (int i = 0; i < profitData.size(); i++) {
                    if (diffInMonths > 1 && i + 1 == 13) {
                        System.out.println("Error: Unexpected data for 13 months.");
                        return null;
                    }
                    String label = diffInMonths > 1 ? java.time.Month.of(i + 1).name() : "Day " + (i + 1);
                    barDataset.addValue(profitData.get(i), "Profit", label);
                }
            }

            // Generate chart based on type
            JFreeChart chart;
            if (chartType.equalsIgnoreCase("line")) {
                chart = ChartFactory.createXYLineChart(
                        "Profit Line Chart",
                        isDaily ? "Hour" : (diffInMonths > 1 ? "Month" : "Time"),
                        "Profit",
                        lineDataset,
                        org.jfree.chart.plot.PlotOrientation.VERTICAL,
                        true,
                        true,
                        false
                );
                XYPlot plot = chart.getXYPlot();
                plot.setBackgroundPaint(Color.WHITE);
                plot.getRenderer().setSeriesStroke(0, new java.awt.BasicStroke(3.0f));
            } else if (chartType.equalsIgnoreCase("bar")) {
                chart = ChartFactory.createBarChart(
                        "Profit Bar Chart",
                        isDaily ? "Hour" : (diffInMonths > 1 ? "Month" : "Time"),
                        "Profit",
                        barDataset,
                        org.jfree.chart.plot.PlotOrientation.VERTICAL,
                        true,
                        true,
                        false
                );
                CategoryPlot plot = chart.getCategoryPlot();
                plot.setBackgroundPaint(Color.WHITE);
            } else {
                System.out.println("Invalid chart type.");
                return null;
            }

            // Return chart panel
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(800, 600));
            return chartPanel;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void GenerateReport(String branchid) {
        ChartPanel chartPanelYearly = DisplayChart("yearly", "line");
        ChartPanel chartPanelMonthly = DisplayChart("monthly", "bar");
        ChartPanel chartPanelWeekly = DisplayChart("weekly", "bar");
        ChartPanel chartPanelDaily = DisplayChart("daily", "bar");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Get the current date
        LocalDate currentDate = LocalDate.now();
        String currentDateStr = currentDate.format(formatter);

        // Get the date 12 months ahead
        LocalDate twelveMonthsBehind = currentDate.plusMonths(-12);
        String twelveMonthsAheadStr = twelveMonthsBehind.format(formatter);

        ChartPanel[] chartPanels = {chartPanelYearly, chartPanelMonthly, chartPanelWeekly, chartPanelDaily};
        ArrayList<String> productStockList = DataBaseHandler.getProductStockStatus(Integer.parseInt(branchid));
        ;
        ArrayList<Integer> monthlyProfits = DataBaseHandler.getMonthlyProfitData(twelveMonthsAheadStr, currentDateStr);
        ArrayList<String> billCalculations = DataBaseHandler.Bills();
        ArrayList<Integer> total = DataBaseHandler.getProfitData("yearly");
        int annualProfit = 0;
        for (int i = 0; i < total.size(); i++) {
            annualProfit += total.get(i);
        }
        int budgetRemaining = 64000;
        generateChartPDF(chartPanels, productStockList, monthlyProfits, billCalculations, "Report.pdf", annualProfit, budgetRemaining);
    }

    public static boolean isNumbers(String line) {
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isStartDateBeforeOrEqual(String startDateStr, String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            LocalDate endDate = LocalDate.parse(endDateStr, formatter);
            return !startDate.isAfter(endDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean changePasswordCashier(String newPassword) throws SQLException {
        return cashier.changePassword(newPassword);
    }

    public static boolean changePasswordDataEntryOperator(String newPassword) throws SQLException {
        return dataEntryOperator.changePassword(newPassword);
    }

    public static boolean changePasswordBranchManager(String newPassword) throws SQLException {
        return branchManager.changePassword(newPassword);
    }

    public static boolean changePasswordSuperAdmin(String newPassword) throws SQLException {
        return superAdmin.changePassword(newPassword);
    }


    public static boolean checkInternetConnection() {
        return InternetConnection.isInternetConnected();
    }


}