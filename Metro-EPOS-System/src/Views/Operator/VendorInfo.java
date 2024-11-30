package Views.Operator;

import Views.Decorate.Theme;
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
    private int branchNumber;
    private JLabel vendorsCount;

    private ExpandedInfo expandedInfo;

    private String vendorProfileImgPath = "Images/DataOperatorProfile.png";
    private String vendorLogoPath = "Images/VendorInfoIcon.png";
    private String searchIconPath = "Images/searchlogo.png";

    public VendorInfo() {
        super.setLineSize5(315, 120);
        super.setLineSize5(315, 168);
        super.setLineSizeCustom(315, 248, 2);
        super.setLineSizeCustom(315, 288, 2);
        super.setText("Data Entry Operator");
        super.setLogoutLogo();
        super.setProfileLogo(vendorProfileImgPath);

        setHeading();
        setLogo();
        setFields();
        setButtons();
        setSearchBar();

        super.setRectangle(48, 272);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    public void setNameBranch(String name, String brID) {
        setNames(name, brID);
    }

    private void setNames(String name, String ID) {
        user = new JLabel();
        user.setText(name);
        user.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
        user.setForeground(Color.white);
        user.setBounds(12, 189, 250, 18);
        user.setVerticalAlignment(JLabel.CENTER);
        user.setHorizontalAlignment(JLabel.CENTER);

        branchNumber = Integer.parseInt(ID);
        branchID = new JLabel();
        branchID.setText("Branch ID: " + ID);
        branchID.setFont(new Font("Inter", Font.BOLD, 25));
        branchID.setForeground(Color.BLACK);
        branchID.setBounds(345, 55, 221, 30);

        add(user);
        add(branchID);
    }

    private void setLogo() {
        vendorLogo = new ImageIcon(vendorLogoPath).getImage();
        Image scaledImage = vendorLogo.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoLabel.setBounds(76, 278, 20, 20);
        add(logoLabel);
    }

    private void setFields() {
        typeName = new JTextField("  Type Name");
        typeName.setBounds(316, 133, 178, 27);
        typeName.setForeground(new Color(173, 173, 173));
        typeName.setBackground(super.getInfoFieldColor());
        typeName.setBorder(new LineBorder(new Color(173, 173, 173), 2));
        typeName.setFont(new Font("Arial", Font.BOLD, 15));
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
        enterCityName.setBounds(507, 133, 178, 27);
        enterCityName.setForeground(new Color(173, 173, 173));
        enterCityName.setBackground(super.getInfoFieldColor());
        enterCityName.setBorder(new LineBorder(new Color(173, 173, 173), 2));
        enterCityName.setFont(new Font("Arial", Font.BOLD, 15));
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
        typeAddress.setBounds(698, 133, 178, 27);
        typeAddress.setForeground(new Color(173, 173, 173));
        typeAddress.setBackground(super.getInfoFieldColor());
        typeAddress.setBorder(new LineBorder(new Color(173, 173, 173), 2));
        typeAddress.setFont(new Font("Arial", Font.BOLD, 15));
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

    private void setSearchBar() {
        try {
            BufferedImage logo = ImageIO.read(new File(searchIconPath));
            int buttonWidth = 15;
            int buttonHeight = 15;
            Image scaledImg = logo.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            searchButton = new JButton(new ImageIcon(scaledImg));
            searchButton.setBorderPainted(false);
            searchButton.setFocusPainted(false);
            searchButton.setContentAreaFilled(false);
            searchButton.setBounds(953, 197, 16, 16);
            add(searchButton);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel field = new JLabel();
        field.setBackground(super.getInfoFieldColor());
        field.setBounds(596, 188, 387, 33);
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
        searchText.setForeground(new Color(173, 173, 173));
        searchText.setBounds(623, 190, 328, 29);
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

    private void setButtons() {
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

    private void setHeading() {
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
        vendorInfo.setForeground(super.getFirstHeadingColor());
        vendorsCount.setForeground(super.getFirstHeadingColor());

        totalVendors.setBounds(79, 259, 88, 24);
        vendorID.setBounds(315, 259, 78, 24);
        name.setBounds(421, 259, 78, 24);
        city.setBounds(500, 259, 60, 24);
        Address.setBounds(562, 259, 110, 24);
        Products.setBounds(673, 259, 83, 24);
        status.setBounds(764, 259, 67, 24);
        vendorInfo.setBounds(842, 259, 120, 24);
        vendorsCount.setBounds(177, 258, 120, 22);

        add(totalVendors);
        add(vendorID);
        add(name);
        add(city);
        add(Address);
        add(Products);
        add(status);
        add(vendorInfo);
        add(vendorsCount);
    }
}
