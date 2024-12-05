package Views.Operator;
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VendorInfo extends Theme {
    private BufferedImage image;
    private JLabel user;
    private JLabel branchID;

    private JScrollPane scroll;

    JTextField typeName;
    JTextField typeAddress;
    JTextField enterCityName;
    JTextField searchText;

    private JButton logoutButton;
    private JButton addButton;
    private JButton searchButton;

    private Image vendorLogo;
    private ArrayList<String> list;
    private JLabel branchNumber;
    private JLabel vendorsCount;

    private String vendorProfileImgPath = "Images/DataOperatorProfile.png";
    private String vendorLogoPath = "Images/VendorInfoIcon.png";
    private String searchIconPath = "Images/searchlogo.png";

    public VendorInfo()
    {
        super.setLineSize5(315,120);
        super.setLineSize5(315,168);
        super.setLineSizeCustom(315,248,2);
        super.setLineSizeCustom(315,288,2);
        super.setText("Data Entry Operator");
        super.setLogoutLogo();
        super.setProfileLogo("Metro-EPOS-System/Images/DataOperatorProfile.png");

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
        vendorLogo = new ImageIcon("Metro-EPOS-System/Images/VendorInfoIcon.png").getImage();
        Image scaledImage = vendorLogo.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoLabel.setBounds(76, 278, 20, 20);
        add(logoLabel);
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
            BufferedImage logo = ImageIO.read(new File("Images/searchlogo.png"));
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

        add(logoutButton);
        add(addButton);
    }

    private void setHeading(){
        JLabel totalVendors = new JLabel("Total Vendors:");
        JLabel vendorID = new JLabel("Vendor ID");
        JLabel name = new JLabel("Name");
        JLabel city = new JLabel("City");
        JLabel Address = new JLabel("Address");
        JLabel Products = new JLabel("Products");
        JLabel status = new JLabel("Status");
        JLabel vendorInfo = new JLabel("Vendor Info");
        vendorsCount = new JLabel("");

        totalVendors.setForeground(super.getFirstHeadingColor());
        vendorID.setForeground(super.getThirdHeadingColor());
        name.setForeground(super.getThirdHeadingColor());
        city.setForeground(super.getThirdHeadingColor());
        Address.setForeground(super.getThirdHeadingColor());
        Products.setForeground(super.getThirdHeadingColor());
        status.setForeground(super.getThirdHeadingColor());
        vendorInfo.setForeground(super.getSideMenuSelectedTextColor());
        vendorsCount.setForeground(new Color(9,95,197));

        totalVendors.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        vendorID.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        city.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        name.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        Address.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        Products.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        status.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        vendorInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        vendorsCount.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));

        totalVendors.setBounds(316,216,105,18);
        vendorID.setBounds(351,257,73,24);
        name.setBounds(458,257,48,24);
        city.setBounds(558,257,63,24);
        vendorInfo.setBounds(110,283,86,10);
        Address.setBounds(658,257,77,24);
        Products.setBounds(773,257,84,24);
        status.setBounds(861,257,77,24);
        vendorsCount.setBounds(424,215,83,21);

        add(totalVendors);
        add(vendorID);
        add(city);
        add(name);
        add(Address);
        add(Products);
        add(status);
        add(vendorInfo);
        add(vendorsCount);
    }

    public void setValues(ArrayList<String> l, frame f, ExpandedInfo expandedInfo)
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
                vendorID.setBounds(30, 13, 78, 14);
                vendorID.setHorizontalAlignment(JLabel.CENTER);
                vendorID.setBorder(BorderFactory.createEmptyBorder());
                vendorID.setEditable(false);
                vendorID.setFocusable(false);

                JTextField name = new JTextField();
                name.setText(data[1]);
                name.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                name.setForeground(new Color(93, 93, 93));
                name.setBounds(128, 12, 75, 18);
                name.setHorizontalAlignment(JLabel.CENTER);
                name.setBorder(BorderFactory.createEmptyBorder());
                name.setEditable(false);
                name.setFocusable(false);

                JTextField city = new JTextField();
                city.setText(data[2]);
                city.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                city.setForeground(new Color(93, 93, 93));
                city.setBounds(203, 13, 112, 14);
                city.setHorizontalAlignment(JLabel.CENTER);
                city.setBorder(BorderFactory.createEmptyBorder());
                city.setEditable(false);
                city.setFocusable(false);

                JTextField address = new JTextField();
                address.setText(data[3]);
                address.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                address.setForeground(new Color(93, 93, 93));
                address.setBounds(315, 13, 132, 14);
                address.setHorizontalAlignment(JLabel.CENTER);
                address.setBorder(BorderFactory.createEmptyBorder());
                address.setEditable(false);
                address.setFocusable(false);

                JTextField products = new JTextField();
                products.setText(data[4]);
                products.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                products.setForeground(new Color(93, 93, 93));
                products.setBounds(454, 13, 70, 14);
                products.setHorizontalAlignment(JLabel.CENTER);
                products.setBorder(BorderFactory.createEmptyBorder());
                products.setEditable(false);
                products.setFocusable(false);


                String[] array = {"Active","Inactive"};

                JComboBox<String> status = new JComboBox<>(array);
                status.setEnabled(false);
                status.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                status.setBounds(547, 13, 70, 16);
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
                            String improved = name.getText()+","+city.getText()+","+address.getText()+","+ products.getText()+","+status.getSelectedItem();
                            list = UIHandler.updateVendorInfo(Integer.parseInt(vendorID.getText()),improved);
                            name.setFocusable(false);
                            name.setEditable(false);
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
                        expandedInfo.setNameBranch(user.getText(), String.valueOf(branchNumber));
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

                if (data[5].equals("Active")) {
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

    public void refreshPanel(ArrayList<String> newList, frame f,ExpandedInfo expandedInfo) {
        if (scroll != null) {
            remove(scroll);
            vendorsCount.setText("");
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
        branchNumber.setText("");
        typeName.setText("");
        typeAddress.setText("");
        enterCityName.setText("");
        searchText.setText("");
    }

    public String getVendorName(){return typeName.getText();}
    public String getVendorCity(){return enterCityName.getText();}
    public String getVendorAddress(){return typeAddress.getText();}
    public String getSearched(){return searchText.getText();}

    public JButton getLogoutButton(){return logoutButton;}
    public JButton getAddButton(){return addButton;}
    public JButton getSearchButton(){return searchButton;}

    public ArrayList<String> getList(){return list;}

    public JPanel getPanel(){return this;}

    public int getBranchID(){
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