package Views.Admin;
import Views.Decorate.Theme;
import Views.Frame.frame;
import Views.UIHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AdminBranchInfo extends Theme {
    private BufferedImage image;
    private JLabel user;
    private JLabel userNmae;

    private JScrollPane scroll;

    JTextField typeName;
    JTextField typeAddress;
    JTextField enterCityName;
    JTextField searchText;

    private JButton logoutButton;
    private JButton addButton;
    private JButton searchButton;
    private JButton employeeInfoButton;
    private JButton vendorInfoButton;

    private Image vendorLogo;
    private Image employeeLogo;
    private Image branchLogo;
    private ArrayList<String> list;
    private JLabel branchNumber;
    private JLabel branchCount;

    private String adminProfileImgPath = "Images/adminLogo.png";
    private String vendorLogoPath = "Images/vendorLogoWhite.png";
    private String searchIconPath = "Images/searchlogo.png";
    private String branchIconPath = "Images/BranchInfoGreen.png";
    private String employeeInfoLogoPath = "Images/EmpInfoWhite.png";

    public AdminBranchInfo()
    {
        super.setLineSize5(315,120);
        super.setLineSize5(315,168);
        super.setLineSizeCustom(315,248,2);
        super.setLineSizeCustom(315,288,2);
        super.setText("Admin");
        super.setLogoutLogo();
        super.setProfileLogo(adminProfileImgPath);

        setHeading();
        setLogo();
        setFields();
        setButtons();
        setSearchBar();

        super.setRectangle(48,272);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    public void setNames(String name,String name2)
    {
        user = new JLabel();
        user.setText(name);
        user.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        user.setForeground(Color.white);
        user.setBounds(12,189,250,18);
        user.setVerticalAlignment(JLabel.CENTER);
        user.setHorizontalAlignment(JLabel.CENTER);

        branchNumber = new JLabel();
        branchNumber.setText(name2);
        userNmae = new JLabel();
        userNmae.setText("Hi, " + name2 + "!");
        userNmae.setFont(new Font("Inter",Font.BOLD,25));
        userNmae.setForeground(Color.BLACK);
        userNmae.setBounds(345,55,221,30);

        add(user);
        add(userNmae);
    }

    private void setLogo() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(vendorLogoPath)) {
            if (is != null) {
                vendorLogo = ImageIO.read(is);
            } else {
                System.err.println("Resource not found: " + vendorLogoPath);
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

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(employeeInfoLogoPath)) {
            if (is != null) {
                employeeLogo = ImageIO.read(is);
            } else {
                System.err.println("Resource not found: " + employeeLogo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (employeeLogo != null) {
            Image scaledImage = employeeLogo.getScaledInstance(16, 20, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoLabel.setBounds(79, 325, 16, 20);
            add(logoLabel);
        }

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(branchIconPath)) {
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
    }

    private void setFields(){
        typeName = new JTextField("  Type Name");
        typeName.setBounds(316,133,178,27);
        typeName.setForeground(new Color(173,173,173));
        typeName.setBackground(super.getInfoFieldColor());
        typeName.setBorder(new LineBorder(new Color(173,173,173),2));
        typeName.setFont(new Font("Arial",Font.BOLD,15));
        typeName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (typeName.getText().equals("  Type Name")) {
                    typeName.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (typeName.getText().isEmpty()) {
                    typeName.setText("  Type Name");
                }
            }
        });

        enterCityName = new JTextField("  Enter City Name");
        enterCityName.setBounds(507,133,178,27);
        enterCityName.setForeground(new Color(173,173,173));
        enterCityName.setBackground(super.getInfoFieldColor());
        enterCityName.setBorder(new LineBorder(new Color(173,173,173),2));
        enterCityName.setFont(new Font("Arial",Font.BOLD,15));
        enterCityName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (enterCityName.getText().equals("  Enter City Name")) {
                    enterCityName.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (enterCityName.getText().isEmpty()) {
                    enterCityName.setText("  Enter City Name");
                }
            }
        });

        typeAddress = new JTextField("  Type Address");
        typeAddress.setBounds(698,133,178,27);
        typeAddress.setForeground(new Color(173,173,173));
        typeAddress.setBackground(super.getInfoFieldColor());
        typeAddress.setBorder(new LineBorder(new Color(173,173,173),2));
        typeAddress.setFont(new Font("Arial",Font.BOLD,15));
        typeAddress.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (typeAddress.getText().equals("  Type Address")) {
                    typeAddress.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (typeAddress.getText().isEmpty()) {
                    typeAddress.setText("  Type Address");
                }
            }
        });

        add(typeName);
        add(enterCityName);
        add(typeAddress);
    }

    private void setSearchBar(){
        try {
            BufferedImage logo = ImageIO.read(getClass().getClassLoader().getResourceAsStream(searchIconPath));
            int buttonWidth = 15;
            int buttonHeight = 15;
            Image scaledImg = logo.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            searchButton = new JButton(new ImageIcon(scaledImg));
            searchButton.setBorderPainted(false);
            searchButton.setFocusPainted(false);
            searchButton.setContentAreaFilled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        searchButton.setBounds(953,197,16,16);
        add(searchButton);

        JLabel field = new JLabel();
        field.setBackground(super.getInfoFieldColor());
        field.setBounds(596,188,387,33);
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(188, 188, 188), 2),
                BorderFactory.createEmptyBorder(0, 0, 0, 5)
        ));
        field.setHorizontalAlignment(JLabel.RIGHT);
        field.setOpaque(true);
        add(field);

        searchText = new JTextField("Search");
        searchText.setBorder(null);
        searchText.setBackground(super.getInfoFieldColor());
        searchText.setForeground(new Color(173,173,173));
        searchText.setBounds(623,190,328,29);
        searchText.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 15));
        searchText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchText.getText().equals("Search")) {
                    searchText.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (searchText.getText().isEmpty()) {
                    searchText.setText("Search");
                }
            }
        });
        add(searchText);
    }

    private void setButtons(){
        logoutButton = new JButton();
        logoutButton.setBounds(44, 684, 84, 19);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setOpaque(false);

        addButton = new JButton("+ ADD");
        addButton.setHorizontalAlignment(JButton.LEFT);
        addButton.setForeground(super.getInfoFieldColor());
        addButton.setFocusable(false);
        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setBounds(1244, 135, 70, 23);
        addButton.setOpaque(true);
        addButton.setBackground(super.getInfoFieldButtonColor());

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

        add(logoutButton);
        add(addButton);
        add(employeeInfoButton);
        add(vendorInfoButton);
    }

    private void setHeading(){
        JLabel totalVendors = new JLabel("Total Branches:");
        JLabel branch_id = new JLabel("Branch ID");
        JLabel name = new JLabel("Name");
        JLabel city = new JLabel("City");
        JLabel Address = new JLabel("Address");
        JLabel employees = new JLabel("Employees");
        JLabel status = new JLabel("Status");
        JLabel vendorInfo = new JLabel("Vendor Info");
        JLabel branchInfo = new JLabel("Branch Info");
        JLabel employeeInfo = new JLabel("Employee Info");
        branchCount = new JLabel("");

        totalVendors.setForeground(super.getFirstHeadingColor());
        branch_id.setForeground(super.getThirdHeadingColor());
        name.setForeground(super.getThirdHeadingColor());
        city.setForeground(super.getThirdHeadingColor());
        Address.setForeground(super.getThirdHeadingColor());
        employees.setForeground(super.getThirdHeadingColor());
        status.setForeground(super.getThirdHeadingColor());
        branchInfo.setForeground(super.getSideMenuSelectedTextColor());
        vendorInfo.setForeground(super.getSideMenuTextColor());
        employeeInfo.setForeground(super.getSideMenuTextColor());
        branchCount.setForeground(new Color(9,95,197));

        totalVendors.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        branch_id.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        city.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        name.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        Address.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        employees.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        status.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        branchInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        employeeInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        vendorInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        branchCount.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));

        totalVendors.setBounds(316,216,118,15);
        branch_id.setBounds(351,257,73,24);
        name.setBounds(460,257,48,24);
        city.setBounds(580,257,63,24);
        Address.setBounds(685,257,77,24);
        employees.setBounds(790,257,84,24);
        status.setBounds(880,257,77,24);
        vendorInfo.setBounds(110,376,86,10);
        employeeInfo.setBounds(110,329,70,13);
        branchInfo.setBounds(110,283,86,13);
        branchCount.setBounds(437,216,83,16);

        add(totalVendors);
        add(branch_id);
        add(city);
        add(name);
        add(Address);
        add(employees);
        add(status);
        add(vendorInfo);
        add(employeeInfo);
        add(branchInfo);
        add(branchCount);
    }

    public void setValues(ArrayList<String> l, frame f, ExpandedReport expandedReport)
    {
        list = l;
        if(l!=null){
            branchCount.setText(String.valueOf(list.size()));
        }
        else{
            branchCount.setText(String.valueOf(0));
        }

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
                label.setPreferredSize(new Dimension(996, 40));
                label.setMaximumSize(new Dimension(996, 40));
                label.setBorder(new MatteBorder(0, 0, 1, 0, new Color(171,171,171)));

                JLabel edit = new JLabel();
                edit.setText("<html><u>Edit</u></html>");
                edit.setFont(new Font("Inter", Font.BOLD, 12));
                edit.setForeground(new Color(3, 149, 255));
                edit.setBounds(855, 13, 46, 14);
                edit.setVerticalAlignment(JLabel.CENTER);
                edit.setHorizontalAlignment(JLabel.CENTER);
                label.add(edit);

                JLabel expand = new JLabel();
                expand.setText("+ Expand");
                expand.setFont(new Font("Inter", Font.BOLD, 12));
                expand.setForeground(new Color(3, 149, 255));
                expand.setBounds(900, 13, 55, 15);
                expand.setVerticalAlignment(JLabel.CENTER);
                expand.setHorizontalAlignment(JLabel.CENTER);
                label.add(expand);

                JTextField branchID = new JTextField();
                branchID.setText(data[0]);
                branchID.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                branchID.setForeground(new Color(93, 93, 93));
                branchID.setBounds(31, 13, 78, 14);
                branchID.setHorizontalAlignment(JLabel.CENTER);
                branchID.setBorder(BorderFactory.createEmptyBorder());
                branchID.setEditable(false);
                branchID.setFocusable(false);

                JTextField name = new JTextField();
                name.setText(data[1]);
                name.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                name.setForeground(new Color(93, 93, 93));
                name.setBounds(109, 12, 119, 18);
                name.setHorizontalAlignment(JLabel.CENTER);
                name.setBorder(BorderFactory.createEmptyBorder());
                name.setEditable(false);
                name.setFocusable(false);

                JTextField city = new JTextField();
                city.setText(data[2]);
                city.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                city.setForeground(new Color(93, 93, 93));
                city.setBounds(228, 13, 112, 14);
                city.setHorizontalAlignment(JLabel.CENTER);
                city.setBorder(BorderFactory.createEmptyBorder());
                city.setEditable(false);
                city.setFocusable(false);

                JTextField address = new JTextField();
                address.setText(data[3]);
                address.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                address.setForeground(new Color(93, 93, 93));
                address.setBounds(340, 13, 132, 14);
                address.setHorizontalAlignment(JLabel.CENTER);
                address.setBorder(BorderFactory.createEmptyBorder());
                address.setEditable(false);
                address.setFocusable(false);

                JTextField employees = new JTextField();
                employees.setText(data[4]);
                employees.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                employees.setForeground(new Color(93, 93, 93));
                employees.setBounds(472, 13, 70, 14);
                employees.setHorizontalAlignment(JLabel.CENTER);
                employees.setBorder(BorderFactory.createEmptyBorder());
                employees.setEditable(false);
                employees.setFocusable(false);


                String[] array = {"Active","Inactive"};
                JComboBox<String> status = new JComboBox<>(array);
                status.setEnabled(false);
                status.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                status.setBounds(561, 13, 70, 16);
                status.setBorder(BorderFactory.createEmptyBorder());
                status.setSelectedItem(data[5]);
                status.setFocusable(false);
                status.setForeground(new Color(93, 93, 93));

                edit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String currentText = edit.getText();
                        if (currentText.contains("Edit")) {
                            edit.setText("<html><u>Update</u></html>");
                            name.setFocusable(true);
                            name.setEditable(true);
                            city.setFocusable(true);
                            city.setEditable(true);
                            address.setFocusable(true);
                            address.setEditable(true);
                            status.setFocusable(true);
                            status.setEnabled(true);
                        }
                        else
                        {
                            String improved = name.getText()+","+city.getText()+","+address.getText()+","+ employees.getText()+","+status.getSelectedItem();
                            list = UIHandler.updateBranchesInfo(Integer.parseInt(branchID.getText()),improved);
                            name.setFocusable(false);
                            name.setEditable(false);
                            city.setFocusable(false);
                            city.setEditable(false);
                            address.setFocusable(false);
                            address.setEditable(false);
                            status.setFocusable(false);
                            status.setEnabled(false);
                            edit.setText("<html><u>Edit</u></html>");
                            refreshPanel(list,f,expandedReport);
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        edit.setForeground(new Color(210, 26, 26));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        edit.setForeground(new Color(3, 149, 255));
                    }
                });

                expand.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        expandedReport.setNameBranch(user.getText(), branchID.getText());
                        expandedReport.refreshPanel(UIHandler.getStocksDataofBranch(Integer.parseInt(branchID.getText())),f,UIHandler.getBranchSales(Integer.parseInt(branchID.getText()),"daily"),UIHandler.getBranchSales(Integer.parseInt(branchID.getText()),"daily"),UIHandler.getBranchProfit(Integer.parseInt(branchID.getText()),"daily"),UIHandler.DisplayChart("daily","bar"));
                        f.replacePanel(getPanel(),expandedReport.getPanel());
                        refreshPanel(list,f,expandedReport);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        expand.setForeground(new Color(210, 26, 26));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        expand.setForeground(new Color(3, 149, 255));
                    }
                });

                if (data[5].equals("Active")) {
                    label.setBackground(super.getInfoFieldColor());
                    branchID.setBackground(super.getInfoFieldColor());
                    name.setBackground(super.getInfoFieldColor());
                    city.setBackground(super.getInfoFieldColor());
                    address.setBackground(super.getInfoFieldColor());
                    employees.setBackground(super.getInfoFieldColor());
                }
                else
                {
                    label.setBackground(new Color(217, 217, 217));
                    branchID.setBackground(new Color(217, 217, 217));
                    name.setBackground(new Color(217, 217, 217));
                    city.setBackground(new Color(217, 217, 217));
                    address.setBackground(new Color(217, 217, 217));
                    employees.setBackground(new Color(217, 217, 217));
                }

                label.add(branchID);
                label.add(name);
                label.add(city);
                label.add(address);
                label.add(employees);
                label.add(status);
                panel.add(label);
            }
        }

        scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(1000, 438));
        scroll.setBounds(315, 293, 1000, 438);
        scroll.setBorder(null);
        add(scroll);
    }

    public void refreshPanel(ArrayList<String> newList, frame f, ExpandedReport expandedReport) {
        if (scroll != null) {
            remove(scroll);
            branchCount.setText("");
            typeName.setText("  Type Name");
            enterCityName.setText("  Enter City Name");
            typeAddress.setText("  Type Address");
            searchText.setText("Search");
            super.removeInfoField();
        }
        setValues(newList,f,expandedReport);
        super.setInfoField();
        revalidate();
        repaint();
    }

    public void resetFields(){
        branchCount.setText("");
        user.setText("");
        branchNumber.setText("");
        typeName.setText("  Type Name");
        enterCityName.setText("  Enter City Name");
        typeAddress.setText("  Type Address");
        searchText.setText("Search");
        revalidate();
        repaint();
    }

    public String getVendorName(){return typeName.getText();}
    public String getVendorCity(){return enterCityName.getText();}
    public String getVendorAddress(){return typeAddress.getText();}
    public String getSearched(){return searchText.getText();}

    public JButton getLogoutButton(){return logoutButton;}
    public JButton getAddButton(){return addButton;}
    public JButton getSearchButton(){return searchButton;}
    public JButton getVendorInfoButton(){return vendorInfoButton;}
    public JButton getEmployeeInfoButton(){return employeeInfoButton;}

    public ArrayList<String> getList(){return list;}

    public JPanel getPanel(){return this;}

    public int getUserNmae(){
        return Integer.parseInt(branchNumber.getText());
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
}