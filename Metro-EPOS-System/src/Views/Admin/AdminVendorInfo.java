package Views.Admin;
import Views.Decorate.Theme;
import Views.Frame.frame;
import Views.Admin.ExpandedVendorInfo;
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

public class AdminVendorInfo extends Theme {
    private BufferedImage image;
    private JLabel user;
    private JLabel userName;

    private JScrollPane scroll;

    JTextField typeName;
    JTextField typeAddress;
    JTextField enterCityName;
    JTextField searchText;

    private JButton logoutButton;
    private JButton addButton;
    private JButton searchButton;

    private JButton branchInfoButton;
    private JButton employeeInfoButton;

    private Image vendorLogo;
    private Image employeeLogo;
    private Image branchLogo;
    private ArrayList<String> list;
    private JLabel vendorsCount;

    private String adminProfileImgPath = "Images/adminLogo.png";
    private String vendorLogoPath = "Images/VendorInfoIcon.png";
    private String branchIconPath = "Images/branchWhite.png";
    private String employeeInfoLogoPath = "Images/EmpInfoWhite.png";
    private String searchIconPath = "Images/searchlogo.png";

    public AdminVendorInfo()
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

        super.setRectangle(48,366);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }


    public void setNames(String name,String ID)
    {
        user = new JLabel();
        user.setText(name);
        user.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        user.setForeground(Color.white);
        user.setBounds(12,189,250,18);
        user.setVerticalAlignment(JLabel.CENTER);
        user.setHorizontalAlignment(JLabel.CENTER);

        userName = new JLabel();
        userName.setText("Hi, " + ID+"!");
        userName.setFont(new Font("Inter",Font.BOLD,25));
        userName.setForeground(Color.BLACK);
        userName.setBounds(345,55,221,30);

        add(user);
        add(userName);
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

        employeeInfoButton = new JButton();
        employeeInfoButton.setBounds(79, 325, 101, 20);
        employeeInfoButton.setBorderPainted(false);
        employeeInfoButton.setContentAreaFilled(false);
        employeeInfoButton.setOpaque(false);

        branchInfoButton = new JButton();
        branchInfoButton.setBounds(79, 281, 117, 15);
        branchInfoButton.setBorderPainted(false);
        branchInfoButton.setContentAreaFilled(false);
        branchInfoButton.setOpaque(false);

        addButton = new JButton("+ ADD");
        addButton.setHorizontalAlignment(JButton.LEFT);
        addButton.setForeground(super.getInfoFieldColor());
        addButton.setFocusable(false);
        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setBounds(1244, 135, 70, 23);
        addButton.setOpaque(true);
        addButton.setBackground(super.getInfoFieldButtonColor());

        add(logoutButton);
        add(addButton);
        add(employeeInfoButton);
        add(branchInfoButton);
    }

    private void setHeading(){
        JLabel totalVendors = new JLabel("Total Vendors:");
        JLabel vendorID = new JLabel("Vendor ID");
        JLabel branchID = new JLabel("Branch ID");
        JLabel name = new JLabel("Name");
        JLabel city = new JLabel("City");
        JLabel Address = new JLabel("Address");
        JLabel Products = new JLabel("Products");
        JLabel status = new JLabel("Status");
        JLabel vendorInfo = new JLabel("Vendor Info");
        JLabel empInfo = new JLabel("Employee Info");
        JLabel branchInfo = new JLabel("Branch Info");
        vendorsCount = new JLabel("");

        totalVendors.setForeground(super.getFirstHeadingColor());
        vendorID.setForeground(super.getThirdHeadingColor());
        branchID.setForeground(super.getThirdHeadingColor());
        name.setForeground(super.getThirdHeadingColor());
        city.setForeground(super.getThirdHeadingColor());
        Address.setForeground(super.getThirdHeadingColor());
        Products.setForeground(super.getThirdHeadingColor());
        status.setForeground(super.getThirdHeadingColor());
        vendorInfo.setForeground(super.getSideMenuSelectedTextColor());
        branchInfo.setForeground(super.getSideMenuTextColor());
        empInfo.setForeground(super.getSideMenuTextColor());
        vendorsCount.setForeground(new Color(9,95,197));

        totalVendors.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        vendorID.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        branchID.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        city.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        name.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        Address.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        Products.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        status.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        vendorInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        branchInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        empInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        vendorsCount.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));

        totalVendors.setBounds(316,216,105,18);
        vendorID.setBounds(351,257,73,24);
        branchID.setBounds(451,257,73,24);
        name.setBounds(558,257,48,24);
        city.setBounds(655,257,63,24);
        vendorInfo.setBounds(110,376,86,10);
        empInfo.setBounds(110,329,70,13);
        branchInfo.setBounds(110,283,86,13);
        Address.setBounds(758,257,77,24);
        Products.setBounds(870,257,84,24);
        status.setBounds(970,257,77,24);
        vendorsCount.setBounds(424,215,83,21);

        add(totalVendors);
        add(vendorID);
        add(branchID);
        add(city);
        add(name);
        add(Address);
        add(Products);
        add(status);
        add(vendorInfo);
        add(empInfo);
        add(branchInfo);
        add(vendorsCount);
    }

    public void setValues(ArrayList<String> l, frame f, ExpandedVendorInfo expandedInfo)
    {
        list = l;
        if(l!=null){
            vendorsCount.setText(String.valueOf(list.size()));
        }
        else{
            vendorsCount.setText(String.valueOf(0));
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

                JTextField vendorID = new JTextField();
                vendorID.setText(data[0]);
                vendorID.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                vendorID.setForeground(new Color(93, 93, 93));
                vendorID.setBounds(31, 13, 78, 14);
                vendorID.setHorizontalAlignment(JLabel.CENTER);
                vendorID.setBorder(BorderFactory.createEmptyBorder());
                vendorID.setEditable(false);
                vendorID.setFocusable(false);

                String[] array3 = UIHandler.getAllBranchIDs().split(",");
                JComboBox<String> branchID = new JComboBox<>(array3);
                branchID.setEnabled(false);
                branchID.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                branchID.setBounds(138, 11, 50, 22);
                branchID.setBorder(BorderFactory.createEmptyBorder());
                branchID.setSelectedItem(data[1]);
                branchID.setFocusable(false);
                branchID.setForeground(new Color(93, 93, 93));

                JTextField name = new JTextField();
                name.setText(data[2]);
                name.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                name.setForeground(new Color(93, 93, 93));
                name.setBounds(206, 12, 119, 18);
                name.setHorizontalAlignment(JLabel.CENTER);
                name.setBorder(BorderFactory.createEmptyBorder());
                name.setEditable(false);
                name.setFocusable(false);

                JTextField city = new JTextField();
                city.setText(data[3]);
                city.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                city.setForeground(new Color(93, 93, 93));
                city.setBounds(304, 13, 112, 14);
                city.setHorizontalAlignment(JLabel.CENTER);
                city.setBorder(BorderFactory.createEmptyBorder());
                city.setEditable(false);
                city.setFocusable(false);

                JTextField address = new JTextField();
                address.setText(data[4]);
                address.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                address.setForeground(new Color(93, 93, 93));
                address.setBounds(416, 13, 132, 14);
                address.setHorizontalAlignment(JLabel.CENTER);
                address.setBorder(BorderFactory.createEmptyBorder());
                address.setEditable(false);
                address.setFocusable(false);

                JTextField products = new JTextField();
                products.setText(data[5]);
                products.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                products.setForeground(new Color(93, 93, 93));
                products.setBounds(555, 13, 70, 14);
                products.setHorizontalAlignment(JLabel.CENTER);
                products.setBorder(BorderFactory.createEmptyBorder());
                products.setEditable(false);
                products.setFocusable(false);


                String[] array = {"Active","Inactive"};

                JComboBox<String> status = new JComboBox<>(array);
                status.setEnabled(false);
                status.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                status.setBounds(651, 13, 70, 16);
                status.setBorder(BorderFactory.createEmptyBorder());
                status.setSelectedItem(data[6]);
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
                            branchID.setFocusable(true);
                            branchID.setEnabled(true);
                            city.setFocusable(true);
                            city.setEditable(true);
                            address.setFocusable(true);
                            address.setEditable(true);
                            status.setFocusable(true);
                            status.setEnabled(true);
                        }
                        else
                        {
                            String improved = branchID.getSelectedItem().toString() + "," + name.getText()+","+city.getText()+","+address.getText()+","+ products.getText()+","+status.getSelectedItem();
                            list = UIHandler.updateAdminVendorInfo(vendorID.getText(),improved);
                            name.setFocusable(false);
                            name.setEditable(false);
                            branchID.setFocusable(false);
                            branchID.setEnabled(false);
                            city.setFocusable(false);
                            city.setEditable(false);
                            address.setFocusable(false);
                            address.setEditable(false);
                            status.setFocusable(false);
                            status.setEnabled(false);
                            edit.setText("<html><u>Edit</u></html>");
                            refreshPanel(list,f,expandedInfo);
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
                        expandedInfo.setNameBranch(user.getText(), branchID.getSelectedItem().toString());
                        expandedInfo.refreshPanel(UIHandler.getVendorProducts(Integer.parseInt(vendorID.getText())),f.getFrame(), Integer.parseInt(vendorID.getText()),false);
                        f.replacePanel(getPanel(),expandedInfo.getPanel());
                        refreshPanel(list,f,expandedInfo);
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

                if (data[6].equals("Active")) {
                    label.setBackground(super.getInfoFieldColor());
                    vendorID.setBackground(super.getInfoFieldColor());
                    name.setBackground(super.getInfoFieldColor());
                    city.setBackground(super.getInfoFieldColor());
                    address.setBackground(super.getInfoFieldColor());
                    products.setBackground(super.getInfoFieldColor());
                }
                else
                {
                    label.setBackground(new Color(217, 217, 217));
                    vendorID.setBackground(new Color(217, 217, 217));
                    name.setBackground(new Color(217, 217, 217));
                    city.setBackground(new Color(217, 217, 217));
                    address.setBackground(new Color(217, 217, 217));
                    products.setBackground(new Color(217, 217, 217));
                }

                label.add(vendorID);
                label.add(branchID);
                label.add(name);
                label.add(city);
                label.add(address);
                label.add(products);
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

    public void refreshPanel(ArrayList<String> newList, frame f, ExpandedVendorInfo expandedInfo) {
        if (scroll != null) {
            remove(scroll);
            vendorsCount.setText("");
            typeName.setText("  Type Name");
            enterCityName.setText("  Enter City Name");
            typeAddress.setText("  Type Address");
            searchText.setText("Search");
            super.removeInfoField();
        }
        setValues(newList,f,expandedInfo);
        super.setInfoField();
        revalidate();
        repaint();
    }

    public void resetFields(){
        vendorsCount.setText("");
        user.setText("");
        userName.setText("");
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
    public JButton getEmployeeInfoButton(){return employeeInfoButton;}
    public JButton getBranchInfoButton(){return branchInfoButton;}

    public ArrayList<String> getList(){return list;}
    public JPanel getPanel(){return this;}
}