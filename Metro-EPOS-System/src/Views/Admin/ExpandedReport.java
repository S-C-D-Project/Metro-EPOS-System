package Views.Admin;

import Views.Decorate.Theme;
import Views.Frame.frame;
import Views.UIHandler;
import org.jfree.chart.ChartPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ExpandedReport extends Theme {
    frame f2;
    private JLabel user;
    private JLabel branchID;

    private JScrollPane scroll;

    private JButton logoutButton;
    private JButton enterButton;
    private JButton employeeInfoButton;
    private JButton generateButtonReport;
    private JButton BackButton;
    private JButton vendorInfoButton;

    private Image empLogo;
    private Image branchLogo;
    private Image vendorLogo;
    private Image salesLogo;
    private Image remainingStockLogo;
    private Image profitLogo;

    private ArrayList<String> list;
    private JLabel branchNumber;

    private JTextField start;
    private JTextField end;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    private ChartPanel chartPanel;
    private String selectedTime;

    private String adminProfileImg = "Images/adminLogo.png";
    private String employeeInfoPath = "Images/EmpInfoWhite.png";
    private String branchInfoPath = "Images/BranchInfoGreen.png";
    private String vendorInfoPath = "Images/vendorLogoWhite.png";
    private String salesLogoPath = "Images/CashLogo.png";
    private String remainingStockLogoPath = "Images/BagLogo.png";
    private String profitLogoPath = "Images/CashBagLogo.png";

    public ExpandedReport()
    {
        super.setLineSize5(315,120);
        super.setLineSize5(315,138);
        super.setLineSizeCustom(321,216,3);
        super.setLineSizeCustom(321,382,3);
        super.setText("Admin");
        super.setLogoutLogo();
        super.setProfileLogo(adminProfileImg);

        setHeading();
        setFields();
        setLogo();
        setButtons();
        setFilterLabel();

        super.setRectangle(48,272);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    public void setNameBranch(String name, String brID){
        setNames(name,brID);
    }

    private void setNames(String name,String ID)
    {
        user = new JLabel();
        user.setText(name);
        user.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        user.setForeground(Color.white);
        user.setBounds(12,189,250,18);
        user.setVerticalAlignment(JLabel.CENTER);
        user.setHorizontalAlignment(JLabel.CENTER);

        branchNumber = new JLabel();
        branchNumber.setText(ID);
        branchID = new JLabel();
        branchID.setText("Branch ID: " + ID);
        branchID.setFont(new Font("Inter",Font.BOLD,25));
        branchID.setForeground(Color.BLACK);
        branchID.setBounds(345,55,221,30);

        add(user);
        add(branchID);
    }

    private void setLogo() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(employeeInfoPath)) {
            if (is != null) {
                empLogo = ImageIO.read(is);
            } else {
                System.err.println("Resource not found: " + employeeInfoPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (empLogo != null) {
            Image scaledImage = empLogo.getScaledInstance(16, 20, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoLabel.setBounds(79, 325, 16, 20);
            add(logoLabel);
        }

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(branchInfoPath)) {
            if (is != null) {
                branchLogo = ImageIO.read(is);
            } else {
                System.err.println("Resource not found: " + branchLogo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (branchLogo != null) {
            Image scaledImage = branchLogo.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoLabel.setBounds(78, 280, 15, 15);
            add(logoLabel);
        }

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(vendorInfoPath)) {
            if (is != null) {
                vendorLogo = ImageIO.read(is);
            } else {
                System.err.println("Resource not found: " + vendorLogo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (vendorLogo != null) {
            Image scaledImage = vendorLogo.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoLabel.setBounds(76, 373, 20, 20);
            add(logoLabel);
        }
    }

    private void setButtons(){
        logoutButton = new JButton();
        logoutButton.setBounds(44, 684, 84, 19);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setOpaque(false);

        employeeInfoButton = new JButton();
        employeeInfoButton.setBounds(79, 325, 101, 20);
        employeeInfoButton.setBorderPainted(false);
        employeeInfoButton.setContentAreaFilled(false);
        employeeInfoButton.setOpaque(false);

        vendorInfoButton = new JButton();
        vendorInfoButton.setBounds(75, 373, 106, 20);
        vendorInfoButton.setBorderPainted(false);
        vendorInfoButton.setContentAreaFilled(false);
        vendorInfoButton.setOpaque(false);

        enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Arial", Font.BOLD, 10));
        enterButton.setHorizontalAlignment(JButton.LEFT);
        enterButton.setForeground(super.getInfoFieldColor());
        enterButton.setFocusable(false);
        enterButton.setBorderPainted(false);
        enterButton.setContentAreaFilled(false);
        enterButton.setBounds(1256, 144, 60, 22);
        enterButton.setOpaque(true);
        enterButton.setBackground(super.getInfoFieldButtonColor());

        generateButtonReport = new JButton("Save");
        generateButtonReport.setForeground(super.getInfoFieldColor());
        generateButtonReport.setFocusable(false);
        generateButtonReport.setBorderPainted(false);
        generateButtonReport.setContentAreaFilled(false);
        generateButtonReport.setBounds(1180, 56, 70, 26);
        generateButtonReport.setOpaque(true);
        generateButtonReport.setBackground(super.getInfoFieldButtonColor());

        BackButton = new JButton("Back");
        BackButton.setForeground(super.getInfoFieldColor());
        BackButton.setFocusable(false);
        BackButton.setBorderPainted(false);
        BackButton.setContentAreaFilled(false);
        BackButton.setBounds(1254, 56, 70, 26);
        BackButton.setOpaque(true);
        BackButton.setBackground(super.getInfoFieldButtonColor());

        add(logoutButton);
        add(enterButton);
        add(employeeInfoButton);
        add(generateButtonReport);
        add(vendorInfoButton);
        add(BackButton);
    }

    private void setHeading(){
        JLabel employeesInfo = new JLabel("Employee Info");
        JLabel branchInfo = new JLabel("Branch Info");
        JLabel vendorInfo = new JLabel("Vendor Info");
        JLabel currentStock = new JLabel("Current Products Status:");
        JLabel range = new JLabel("Range");
        JLabel startLabel = new JLabel("Start");
        JLabel endLabel = new JLabel("End");
        JLabel toLabel = new JLabel("to");

        employeesInfo.setForeground(super.getSideMenuTextColor());
        branchInfo.setForeground(super.getSideMenuSelectedTextColor());
        vendorInfo.setForeground(super.getSideMenuTextColor());
        currentStock.setForeground(super.getFirstHeadingColor());
        range.setForeground(super.getSecondHeadingColor());
        startLabel.setForeground(super.getSecondHeadingColor());
        endLabel.setForeground(super.getSecondHeadingColor());
        toLabel.setForeground(super.getSecondHeadingColor());

        branchInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        employeesInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        vendorInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        currentStock.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,20));
        range.setFont(new Font("Yu Gothic UI SemiBold",Font.PLAIN,15));
        startLabel.setFont(new Font("Yu Gothic UI SemiBold",Font.PLAIN,10));
        endLabel.setFont(new Font("Yu Gothic UI SemiBold",Font.PLAIN,10));
        toLabel.setFont(new Font("Yu Gothic UI SemiBold",Font.PLAIN,15));

        vendorInfo.setBounds(110,376,86,10);
        employeesInfo.setBounds(110,329,71,13);
        branchInfo.setBounds(110,283,86,13);
        currentStock.setBounds(321,391,248,36);
        range.setBounds(1041,145,48,19);
        startLabel.setBounds(1046,166,24,12);
        endLabel.setBounds(1206,166,19,12);
        toLabel.setBounds(1172,182,15,18);

        add(employeesInfo);
        add(branchInfo);
        add(vendorInfo);
        add(currentStock);
        add(range);
        add(startLabel);
        add(endLabel);
        add(toLabel);

        JLabel line = new JLabel();
        line.setBackground(super.getLineColor2());
        line.setBounds(711,382,3,321);
        line.setOpaque(true);
        add(line);
    }

    private void setFilterLabel()
    {
        JLabel label = new JLabel();
        selectedTime = "daily";
        label.setOpaque(true);
        label.setBackground(new Color(217,217,217));
        label.setBounds(675,160,278,38);

        JLabel today = new JLabel("Today");
        JLabel week = new JLabel("Week");
        JLabel year = new JLabel("Year");
        JLabel month = new JLabel("Month");

        today.setBounds(28,8,45,21);
        week.setBounds(93,9,41,18);
        month.setBounds(155,9,47,18);
        year.setBounds(223,9,33,18);

        today.setFont(new Font("Yu Gothic UI SemiBold",Font.PLAIN,15));
        week.setFont(new Font("Yu Gothic UI SemiBold",Font.PLAIN,15));
        month.setFont(new Font("Yu Gothic UI SemiBold",Font.PLAIN,15));
        year.setFont(new Font("Yu Gothic UI SemiBold",Font.PLAIN,15));

        today.setForeground(new Color(53,188,78));
        week.setForeground(Color.BLACK);
        month.setForeground(Color.BLACK);
        year.setForeground(Color.BLACK);

        today.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                selectedTime = "daily";
                today.setForeground(new Color(53,188,78));
                week.setForeground(Color.BLACK);
                month.setForeground(Color.BLACK);
                year.setForeground(Color.BLACK);

                refreshPanel(list,f2,UIHandler.getBranchSales(Integer.parseInt(branchNumber.getText()),"daily"),UIHandler.getBranchRemaingingStock(Integer.parseInt(branchNumber.getText()),"daily"),UIHandler.getBranchProfit(Integer.parseInt(branchNumber.getText()),"daily"),UIHandler.DisplayChart("daily","bar"));
            }
        });
        week.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                selectedTime = "weekly";
                week.setForeground(new Color(53,188,78));
                today.setForeground(Color.BLACK);
                month.setForeground(Color.BLACK);
                year.setForeground(Color.BLACK);
                refreshPanel(list,f2,UIHandler.getBranchSales(Integer.parseInt(branchNumber.getText()),"weekly"),UIHandler.getBranchRemaingingStock(Integer.parseInt(branchNumber.getText()),"weekly"),UIHandler.getBranchProfit(Integer.parseInt(branchNumber.getText()),"weekly"),UIHandler.DisplayChart("weekly","bar"));
            }
        });
        month.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                selectedTime = "monthly";
                month.setForeground(new Color(53,188,78));
                week.setForeground(Color.BLACK);
                today.setForeground(Color.BLACK);
                year.setForeground(Color.BLACK);
                refreshPanel(list,f2,UIHandler.getBranchSales(Integer.parseInt(branchNumber.getText()),"monthly"),UIHandler.getBranchRemaingingStock(Integer.parseInt(branchNumber.getText()),"monthly"),UIHandler.getBranchProfit(Integer.parseInt(branchNumber.getText()),"monthly"),UIHandler.DisplayChart("monthly","bar"));
            }
        });
        year.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                selectedTime = "yearly";
                year.setForeground(new Color(53,188,78));
                week.setForeground(Color.BLACK);
                month.setForeground(Color.BLACK);
                today.setForeground(Color.BLACK);
                refreshPanel(list,f2,UIHandler.getBranchSales(Integer.parseInt(branchNumber.getText()),"yearly"),UIHandler.getBranchRemaingingStock(Integer.parseInt(branchNumber.getText()),"yearly"),UIHandler.getBranchProfit(Integer.parseInt(branchNumber.getText()),"yearly"),UIHandler.DisplayChart("yearly","line"));
            }
        });

        label.add(today);
        label.add(week);
        label.add(month);
        label.add(year);
        add(label);
    }

    private void setFields(){
        start = new JTextField("dd/MM/yyyy");
        start.setHorizontalAlignment(JTextField.CENTER);
        start.setBounds(1038,178,117,26);
        start.setForeground(new Color(173,173,173));
        start.setBackground(new Color(217,217,217));
        start.setBorder(null);
        start.setFont(new Font("Arial",Font.BOLD,15));
        start.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (start.getText().equals("dd/MM/yyyy")) {
                    start.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (start.getText().isEmpty()) {
                    start.setText("dd/MM/yyyy");
                }
            }
        });
        add(start);

        end = new JTextField("dd/MM/yyyy");
        end.setHorizontalAlignment(JTextField.CENTER);
        end.setBounds(1199,178,117,26);
        end.setForeground(new Color(173,173,173));
        end.setBackground(new Color(217,217,217));
        end.setBorder(null);
        end.setFont(new Font("Arial",Font.BOLD,15));
        end.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (end.getText().equals("dd/MM/yyyy")) {
                    end.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (end.getText().isEmpty()) {
                    end.setText("dd/MM/yyyy");
                }
            }
        });
        add(end);
    }
    public void setSalesLabel(int value){
        label1 = new JLabel();
        label1.setBounds(321,234,302,128);
        label1.setBackground(new Color(255,166,1));
        label1.setOpaque(true);
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(salesLogoPath)) {
            if (is != null) {
                salesLogo = ImageIO.read(is);
            } else {
                System.err.println("Resource not found: " + salesLogo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (salesLogo != null) {
            Image scaledImage = salesLogo.getScaledInstance(39, 32, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoLabel.setBounds(24, 15, 39, 32);
            label1.add(logoLabel);
        }
        JLabel name1 = new JLabel("Sales");
        name1.setForeground(super.getInfoFieldColor());
        name1.setFont(new Font("Arial", Font.BOLD, 20));
        name1.setBounds(71,21,58,19);
        label1.add(name1);

        JLabel text1 = new JLabel("Rs. " + value);
        text1.setForeground(Color.BLACK);
        text1.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 30));
        text1.setBounds(24,56,278,49);
        label1.add(text1);

        add(label1);
    }

    public void setRemainLabel(int value){
        label2 = new JLabel();
        label2.setBounds(670,234,302,128);
        label2.setBackground(new Color(254,87,87));
        label2.setOpaque(true);
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(remainingStockLogoPath)) {
            if (is != null) {
                remainingStockLogo = ImageIO.read(is);
            } else {
                System.err.println("Resource not found: " + remainingStockLogo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (remainingStockLogo != null) {
            Image scaledImage = remainingStockLogo.getScaledInstance(39, 32, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoLabel.setBounds(24, 15, 39, 32);
            label2.add(logoLabel);
        }
        JLabel name2 = new JLabel("Remaining Stock");
        name2.setForeground(super.getInfoFieldColor());
        name2.setFont(new Font("Arial", Font.BOLD, 20));
        name2.setBounds(71,19,177,26);
        label2.add(name2);

        JLabel text2 = new JLabel(value+" Units");
        text2.setForeground(Color.BLACK);
        text2.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 30));
        text2.setBounds(24,56,278,49);
        label2.add(text2);

        add(label2);
    }

    public void setProfitLabel(int value){
        label3 = new JLabel();
        label3.setBounds(1014,234,302,128);
        label3.setBackground(new Color(171,195,47));
        label3.setOpaque(true);
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(profitLogoPath)) {
            if (is != null) {
                profitLogo = ImageIO.read(is);
            } else {
                System.err.println("Resource not found: " + profitLogo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (profitLogo != null) {
            Image scaledImage = profitLogo.getScaledInstance(39, 32, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoLabel.setBounds(24, 15, 39, 32);
            label3.add(logoLabel);
        }
        JLabel name3 = new JLabel("Profit");
        name3.setForeground(super.getInfoFieldColor());
        name3.setFont(new Font("Arial", Font.BOLD, 20));
        name3.setBounds(71,21,70,19);
        label3.add(name3);

        JLabel text3 = new JLabel("Rs. " + value);
        text3.setForeground(Color.BLACK);
        text3.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 30));
        text3.setBounds(24,56,278,49);
        label3.add(text3);

        add(label3);
    }

    public void setChartPanel(ChartPanel p){
        chartPanel = p;
        chartPanel.setBounds(741,407,578,290);
        add(chartPanel);
    }

    public void setValues(ArrayList<String> l, frame f)
    {
        list = l;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(super.getInfoFieldColor());
        panel.setBorder(null);

        if(list!=null) {
            for (int i = 0; i < list.size(); i++) {
                String[] data = list.get(i).split(",");

                JLabel label = new JLabel();
                label.setLayout(null);
                label.setOpaque(true);
                label.setPreferredSize(new Dimension(373, 27));
                label.setMaximumSize(new Dimension(373, 27));
                label.setBorder(new MatteBorder(0, 0, 1, 0, new Color(171,171,171)));

                JTextField pName = new JTextField();
                pName.setText(data[0]);
                pName.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 13));
                pName.setForeground(new Color(93, 93, 93));
                pName.setBounds(17, 4, 158, 20);
                pName.setHorizontalAlignment(JLabel.CENTER);
                pName.setBorder(BorderFactory.createEmptyBorder());
                pName.setEditable(false);
                pName.setFocusable(false);

                JTextField status = new JTextField();
                if(data[1].equals("Out of Stock")){
                    status.setText(data[1]);
                }
                else{
                    status.setText(data[1] + " Units");
                }
                status.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 13));
                if(data[1].equals("Out of Stock")){
                    status.setForeground(new Color(246, 28, 28));
                }
                else{
                    status.setForeground(new Color(248, 203, 20));
                }
                status.setBounds(214, 4, 150, 20);
                status.setHorizontalAlignment(JLabel.CENTER);
                status.setBorder(BorderFactory.createEmptyBorder());
                status.setEditable(false);
                status.setFocusable(false);

                if (!data[1].equals("Out of Stock")) {
                    label.setBackground(super.getInfoFieldColor());
                    pName.setBackground(super.getInfoFieldColor());
                    status.setBackground(super.getInfoFieldColor());
                }
                else
                {
                    label.setBackground(new Color(217, 217, 217));
                    pName.setBackground(new Color(217, 217, 217));
                    status.setBackground(new Color(217, 217, 217));
                }

                label.add(pName);
                label.add(status);
                label.add(status);
                panel.add(label);
            }
        }

        scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(373, 270));
        scroll.setBounds(321, 433, 373, 270);
        scroll.setBorder(null);
        add(scroll);
    }

    public void refreshPanel(ArrayList<String> newList, frame f, int sales, int remains, int profit, ChartPanel panel) {
        f2=f;
        if (scroll != null) {
            remove(scroll);
            remove(label1);
            remove(label2);
            remove(label3);
            remove(chartPanel);
            super.removeInfoField();
        }
        setValues(newList,f);
        setSalesLabel(sales);
        setRemainLabel(remains);
        setProfitLabel(profit);
        setChartPanel(panel);
        super.setInfoField();
        revalidate();
        repaint();
    }

    public void resetFields(){
        user.setText("");
        branchNumber.setText("");
        revalidate();
        repaint();
    }

    public JButton getLogoutButton(){return logoutButton;}
    public JButton getEnterButton(){return enterButton;}
    public JButton getEmployeeInfoButton(){return employeeInfoButton;}
    public JButton getGenerateButtonReport(){return generateButtonReport;}
    public JButton getBackButton(){return BackButton;}
    public JButton getVendorInfoButton(){return vendorInfoButton;}

    public String getStartRange(){return start.getText();}
    public String getEndRange(){return end.getText();}
    public String getSelectedTime(){return selectedTime;}

    public ArrayList<String> getList(){return list;}

    public JPanel getPanel(){return this;}

    public int getBranchID(){
        return Integer.parseInt(branchNumber.getText());
    }
}