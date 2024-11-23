package Views.Operator;
import Views.Decorate.RoundEdges;
import Views.Decorate.Theme;
import Views.UIHandler;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;

public class VendorInfo extends Theme {
    private BufferedImage image;
    private JLabel user;
    private JLabel branchID;

    private JScrollPane scroll;

    JTextField typeName;
    JTextField typeAddress;
    JTextField enterCityName;

    private JButton logoutButton;
    private JButton addButton;

    private Image vendorLogo;
    private ArrayList<String> list;
    private int branchNumber;
    private JLabel vendorsCount;

    public VendorInfo(String name, String branchID)
    {
        super.setLineSize5(315,120);
        super.setLineSize5(315,168);
        super.setLineSizeCustom(315,248,2);
        super.setLineSizeCustom(315,288,2);
        super.setText("Data Entry Operator");
        super.setLogoutLogo();
        super.setProfileLogo("Metro-EPOS-System/Images/DataOperatorProfile.png");

        setNames(name,branchID);
        setHeading();
        setLogo();
        setFields();
        setButtons();

        super.setRectangle(48,272);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
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

        branchNumber = Integer.parseInt(ID);
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
        city.setBounds(543,257,63,24);
        vendorInfo.setBounds(110,283,86,10);
        Address.setBounds(658,257,77,24);
        Products.setBounds(763,257,84,24);
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

    public void setValues(ArrayList<String> l, JFrame f)
    {
        list = l;
        vendorsCount.setText(String.valueOf(list.size()));

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
                label.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));

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
                vendorID.setBounds(5, 13, 78, 14);
                vendorID.setHorizontalAlignment(JLabel.CENTER);
                vendorID.setBorder(BorderFactory.createEmptyBorder());
                vendorID.setEditable(false);
                vendorID.setFocusable(false);

                JTextField name = new JTextField();
                name.setText(data[1]);
                name.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                name.setForeground(new Color(93, 93, 93));
                name.setBounds(129, 13, 75, 14);
                name.setHorizontalAlignment(JLabel.CENTER);
                name.setBorder(BorderFactory.createEmptyBorder());
                name.setEditable(false);
                name.setFocusable(false);

                JTextField city = new JTextField();
                city.setText(data[2]);
                city.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                city.setForeground(new Color(93, 93, 93));
                city.setBounds(204, 13, 112, 14);
                city.setHorizontalAlignment(JLabel.CENTER);
                city.setBorder(BorderFactory.createEmptyBorder());
                city.setEditable(false);
                city.setFocusable(false);

                JTextField address = new JTextField();
                address.setText(data[3]);
                address.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                address.setForeground(new Color(93, 93, 93));
                address.setBounds(204, 13, 112, 14);
                address.setHorizontalAlignment(JLabel.CENTER);
                address.setBorder(BorderFactory.createEmptyBorder());
                address.setEditable(false);
                address.setFocusable(false);

                JTextField products = new JTextField();
                products.setText(data[4]);
                products.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                products.setForeground(new Color(93, 93, 93));
                products.setBounds(204, 13, 112, 14);
                products.setHorizontalAlignment(JLabel.CENTER);
                products.setBorder(BorderFactory.createEmptyBorder());
                products.setEditable(false);
                products.setFocusable(false);


                String[] array = {"Active","Inactive"};

                JComboBox<String> status = new JComboBox<>(array);
                status.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                status.setForeground(new Color(93, 93, 93));
                status.setBounds(204, 13, 112, 14);
                status.setBorder(BorderFactory.createEmptyBorder());
                status.setEditable(false);
                status.setSelectedItem(data[5]);
                status.setFocusable(false);


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
                            status.setEditable(true);
                        }
                        else
                        {
                            //perform edit operations
                            name.setFocusable(false);
                            name.setEditable(false);
                            city.setFocusable(false);
                            city.setEditable(false);
                            address.setFocusable(false);
                            address.setEditable(false);
                            status.setFocusable(false);
                            status.setEditable(false);
                            edit.setText("<html><u>Edit</u></html>");
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
                    public void mouseClicked(MouseEvent e) {
                        // open new panel
                        refreshPanel(list,f);
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
                    status.setBackground(super.getInfoFieldColor());
                }
                else
                {
                    label.setBackground(new Color(217, 217, 217));
                    vendorID.setBackground(new Color(217, 217, 217));
                    name.setBackground(new Color(217, 217, 217));
                    city.setBackground(new Color(217, 217, 217));
                    address.setBackground(new Color(217, 217, 217));
                    products.setBackground(new Color(217, 217, 217));
                    status.setBackground(new Color(217, 217, 217));
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

    public void refreshPanel(ArrayList<String> newList, JFrame f) {
        if (scroll != null) {
            remove(scroll);
            vendorsCount.setText("");
            super.removeInfoField();
        }
        setValues(newList,f);
        super.setInfoField();
        revalidate();
        repaint();
    }

    public String getVendorName(){return typeName.getText();}
    public String getVendorCity(){return enterCityName.getText();}
    public String getVendorAddress(){return typeAddress.getText();}

    public JButton getLogoutButton(){return logoutButton;}
    public JButton getAddButton(){return addButton;}

    public ArrayList<String> getList(){return list;}

    public JPanel getPanel(){return this;}

    public int getBranchID(){
        return branchNumber;
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