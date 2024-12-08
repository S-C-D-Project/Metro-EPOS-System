package Views.Admin;
import Views.Decorate.Theme;
import Views.UIHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class ExpandedVendorInfo extends Theme {
    private BufferedImage image;
    private JLabel user;
    private JLabel branchID;
    private int vendorID;

    private JScrollPane scroll;

    private JButton logoutButton;
    private JButton addButton;
    private JButton backButton;
    private JButton branchInfoButton;
    private JButton employeeInfoButton;

    private Image vendorLogo;
    private Image employeeLogo;
    private Image branchLogo;
    private ArrayList<String> list;
    private JLabel branchNumber;
    private JLabel productsCountHeading;
    private JLabel vendorIDHeading;

    private JLabel producstsCountValue;
    private JLabel vendorIDValue;

    private String adminProfileImgPath = "Images/adminLogo.png";
    private String vendorLogoPath = "Images/VendorInfoIcon.png";
    private String branchIconPath = "Images/branchWhite.png";
    private String employeeInfoLogoPath = "Images/EmpInfoWhite.png";

    public ExpandedVendorInfo()
    {
        super.setLineSize5(315,120);
        super.setLineSize5(315,138);
        super.setLineSizeCustom(315,212,2);
        super.setLineSizeCustom(315,252,2);
        super.setText("Admin");
        super.setLogoutLogo();
        super.setProfileLogo(adminProfileImgPath);

        setHeading();
        setLogo();
        setButtons();

        super.setRectangle(48,366);

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
        addButton.setBounds(1249, 162, 70, 23);
        addButton.setOpaque(true);
        addButton.setBackground(super.getInfoFieldButtonColor());

        backButton = new JButton("BACK");
        backButton.setForeground(super.getInfoFieldColor());
        backButton.setFocusable(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBounds(1240, 62, 76, 33);
        backButton.setOpaque(true);
        backButton.setBackground(super.getInfoFieldButtonColor());

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

        add(logoutButton);
        add(addButton);
        add(backButton);
        add(employeeInfoButton);
        add(branchInfoButton);
    }

    private void setHeading(){
        JLabel vendorInfo = new JLabel("Vendor Info");
        vendorInfo.setForeground(super.getSideMenuSelectedTextColor());
        vendorInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        vendorInfo.setBounds(110,376,86,10);
        add(vendorInfo);

        JLabel empInfo = new JLabel("Employee Info");
        empInfo.setForeground(super.getSideMenuTextColor());
        empInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        empInfo.setBounds(110,329,70,13);
        add(empInfo);

        JLabel branchInfo = new JLabel("Branch Info");
        branchInfo.setForeground(super.getSideMenuTextColor());
        branchInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        branchInfo.setBounds(110,283,86,13);
        add(branchInfo);

        vendorIDHeading = new JLabel("Vendor ID:");
        vendorIDHeading.setHorizontalAlignment(JLabel.LEFT);
        vendorIDHeading.setForeground(super.getFirstHeadingColor());
        vendorIDHeading.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        vendorIDHeading.setBounds(321,154,76,18);
        add(vendorIDHeading);

        productsCountHeading = new JLabel("Total Products:");
        productsCountHeading.setHorizontalAlignment(JLabel.LEFT);
        productsCountHeading.setForeground(super.getFirstHeadingColor());
        productsCountHeading.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        productsCountHeading.setBounds(321,176,110,18);
        add(productsCountHeading);

        vendorIDValue = new JLabel("");
        vendorIDValue.setHorizontalAlignment(JLabel.LEFT);
        vendorIDValue.setForeground(super.getFirstHeadingColor());
        vendorIDValue.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        vendorIDValue.setBounds(401,154,92,18);
        add(vendorIDValue);

        producstsCountValue = new JLabel("");
        producstsCountValue.setHorizontalAlignment(JLabel.LEFT);
        producstsCountValue.setForeground(new Color(9,95,197));
        producstsCountValue.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        producstsCountValue.setBounds(435,178,83,16);
        add(producstsCountValue);

        JLabel category = new JLabel("Category");
        category.setForeground(super.getThirdHeadingColor());
        category.setHorizontalAlignment(JLabel.CENTER);
        category.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        category.setBounds(352,219,73,24);
        add(category);

        JLabel name = new JLabel("Name");
        name.setForeground(super.getThirdHeadingColor());
        name.setHorizontalAlignment(JLabel.CENTER);
        name.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        name.setBounds(482,219,48,24);
        add(name);

        JLabel original = new JLabel("Original Price");
        original.setForeground(super.getThirdHeadingColor());
        original.setHorizontalAlignment(JLabel.CENTER);
        original.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        original.setBounds(575,219,96,24);
        add(original);

        JLabel salePrice = new JLabel("Sale Price");
        salePrice.setForeground(super.getThirdHeadingColor());
        salePrice.setHorizontalAlignment(JLabel.CENTER);
        salePrice.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        salePrice.setBounds(701,219,77,24);
        add(salePrice);

        JLabel pricePerUnits = new JLabel("Price per Unit");
        pricePerUnits.setForeground(super.getThirdHeadingColor());
        pricePerUnits.setHorizontalAlignment(JLabel.CENTER);
        pricePerUnits.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        pricePerUnits.setBounds(804,219,105,24);
        add(pricePerUnits);

        JLabel stocks = new JLabel("Stocks");
        stocks.setForeground(super.getThirdHeadingColor());
        stocks.setHorizontalAlignment(JLabel.CENTER);
        stocks.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        stocks.setBounds(923,219,105,24);
        add(stocks);

        JLabel manufacture = new JLabel("Manufacture");
        manufacture.setForeground(super.getThirdHeadingColor());
        manufacture.setHorizontalAlignment(JLabel.CENTER);
        manufacture.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        manufacture.setBounds(1036,219,105,24);
        add(manufacture);

        JLabel sizeProd = new JLabel("Size");
        sizeProd.setForeground(super.getThirdHeadingColor());
        sizeProd.setHorizontalAlignment(JLabel.CENTER);
        sizeProd.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        sizeProd.setBounds(1148,219,105,24);
        add(sizeProd);
    }

    public void setValues(ArrayList<String> l, JFrame f, int vID, boolean add)
    {
        list = l;
        producstsCountValue.setText(String.valueOf(list.size()));
        vendorIDValue.setText(String.valueOf(vID));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(super.getInfoFieldColor());
        panel.setBorder(null);

        if(add){
            JLabel label = new JLabel();
            label.setLayout(null);
            label.setOpaque(true);
            label.setPreferredSize(new Dimension(996, 40));
            label.setMaximumSize(new Dimension(996, 40));
            label.setBorder(new MatteBorder(0, 0, 1, 0, new Color(171,171,171)));

            JLabel save = new JLabel();
            save.setText("Save");
            save.setFont(new Font("Inter", Font.BOLD, 12));
            save.setForeground(new Color(26, 218, 67));
            save.setBounds(950, 5, 46, 15);
            save.setVerticalAlignment(JLabel.CENTER);
            save.setHorizontalAlignment(JLabel.CENTER);
            label.add(save);

            JLabel Cancel = new JLabel();
            Cancel.setText("Cancel");
            Cancel.setFont(new Font("Inter", Font.BOLD, 12));
            Cancel.setForeground(new Color(255, 3, 3));
            Cancel.setBounds(952, 23, 41, 15);
            Cancel.setVerticalAlignment(JLabel.CENTER);
            Cancel.setHorizontalAlignment(JLabel.CENTER);
            label.add(Cancel);

            String[] categories = {
                    "Fruits & Veg",
                    "Meat & Seafood",
                    "Dairy & Eggs",
                    "Bakery",
                    "Dry Goods",
                    "Frozen Foods",
                    "Snacks","Beverages",
                    "Household Item",
                    "Personal Care"
            };
            JComboBox<String> category = new JComboBox<>(categories);
            category.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
            category.setForeground(super.getSecondHeadingColor());
            category.setBounds(15, 11, 117, 20);
            category.setBackground(Color.WHITE);
            category.setFocusable(false);
            category.setSelectedIndex(0);
            category.setMaximumRowCount(10);


            JTextField name = new JTextField();
            name.setText("_");
            name.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
            name.setForeground(super.getSecondHeadingColor());
            name.setBounds(132, 11, 117, 20);
            name.setHorizontalAlignment(JLabel.CENTER);
            name.setBorder(BorderFactory.createEmptyBorder());
            name.setEditable(false);
            name.setFocusable(false);

            JTextField originalPrice = new JTextField();
            originalPrice.setText("_");
            originalPrice.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
            originalPrice.setForeground(super.getSecondHeadingColor());
            originalPrice.setBounds(249, 11, 117, 20);
            originalPrice.setHorizontalAlignment(JLabel.CENTER);
            originalPrice.setBorder(BorderFactory.createEmptyBorder());
            originalPrice.setEditable(false);
            originalPrice.setFocusable(false);

            JTextField salePrice = new JTextField();
            salePrice.setText("_");
            salePrice.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
            salePrice.setForeground(super.getSecondHeadingColor());
            salePrice.setBounds(366, 11, 117, 20);
            salePrice.setHorizontalAlignment(JLabel.CENTER);
            salePrice.setBorder(BorderFactory.createEmptyBorder());
            salePrice.setEditable(false);
            salePrice.setFocusable(false);

            JTextField pricePerUnit = new JTextField();
            pricePerUnit.setText("_");
            pricePerUnit.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
            pricePerUnit.setForeground(super.getSecondHeadingColor());
            pricePerUnit.setBounds(483, 11, 117, 20);
            pricePerUnit.setHorizontalAlignment(JLabel.CENTER);
            pricePerUnit.setBorder(BorderFactory.createEmptyBorder());
            pricePerUnit.setEditable(false);
            pricePerUnit.setFocusable(false);

            JTextField stocksProd = new JTextField();
            stocksProd.setText("_");
            stocksProd.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
            stocksProd.setForeground(super.getSecondHeadingColor());
            stocksProd.setBounds(608, 11, 105, 19);
            stocksProd.setHorizontalAlignment(JLabel.CENTER);
            stocksProd.setBorder(BorderFactory.createEmptyBorder());
            stocksProd.setEditable(false);
            stocksProd.setFocusable(false);

            JTextField manufacture = new JTextField();
            manufacture.setText("_");
            manufacture.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
            manufacture.setForeground(super.getSecondHeadingColor());
            manufacture.setBounds(720, 11, 105, 19);
            manufacture.setHorizontalAlignment(JLabel.CENTER);
            manufacture.setBorder(BorderFactory.createEmptyBorder());
            manufacture.setEditable(false);
            manufacture.setFocusable(false);

            String[] sizes = {
                    "Small",
                    "Medium",
                    "Large"
            };
            JComboBox<String> prodSizes = new JComboBox<>(sizes);
            prodSizes.setEnabled(true);
            prodSizes.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
            prodSizes.setBounds(845, 5, 80, 30);
            prodSizes.setBorder(BorderFactory.createEmptyBorder());
            prodSizes.setSelectedItem(0);
            prodSizes.setFocusable(false);
            prodSizes.setForeground(new Color(93, 93, 93));


            save.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(Objects.requireNonNull(category.getSelectedItem()).toString().trim().equals("_") || category.getSelectedItem().toString().trim().isEmpty() || name.getText().trim().isEmpty() || name.getText().trim().equals("_") ||!UIHandler.isNumbers(originalPrice.getText()) || !UIHandler.isNumbers(salePrice.getText()) || !UIHandler.isNumbers(pricePerUnit.getText()) || !UIHandler.isNumbers(stocksProd.getText()) || manufacture.getText().trim().isEmpty() || manufacture.getText().equals("_")) {
                        JOptionPane.showMessageDialog(f,"Invalid Inputs", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else if(Integer.parseInt(salePrice.getText())<Integer.parseInt(originalPrice.getText())){
                        JOptionPane.showMessageDialog(f,"Sales Price Less than Orignal", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        String adding = category.getSelectedItem().toString() + "," + name.getText() + "," + originalPrice.getText() + "," + salePrice.getText() + "," + pricePerUnit.getText() +","+stocksProd.getText() + "," + manufacture.getText() + "," + prodSizes.getSelectedItem().toString();
                        list = UIHandler.addNewVendorProduct(vendorID,adding);
                        refreshPanel(list,f,vendorID,false);
                    }
                }
            });
            Cancel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    int result = JOptionPane.showConfirmDialog(null, "Do you Wish to Cancel?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        refreshPanel(list,f,vendorID,false);
                    }
                }
            });

            label.setBackground(new Color(217,217,217));
            category.setBackground(new Color(217,217,217));
            name.setBackground(new Color(217,217,217));
            originalPrice.setBackground(new Color(217,217,217));
            salePrice.setBackground(new Color(217,217,217));
            pricePerUnit.setBackground(new Color(217,217,217));
            stocksProd.setBackground(new Color(217,217,217));
            manufacture.setBackground(new Color(217,217,217));
            prodSizes.setBackground(new Color(217,217,217));

            name.setFocusable(true);
            name.setEditable(true);
            originalPrice.setFocusable(true);
            originalPrice.setEditable(true);
            salePrice.setFocusable(true);
            salePrice.setEditable(true);
            pricePerUnit.setFocusable(true);
            pricePerUnit.setEditable(true);
            stocksProd.setFocusable(true);
            stocksProd.setEditable(true);
            manufacture.setFocusable(true);
            manufacture.setEditable(true);

            label.add(category);
            label.add(name);
            label.add(originalPrice);
            label.add(salePrice);
            label.add(pricePerUnit);
            label.add(stocksProd);
            label.add(manufacture);
            label.add(prodSizes);
            panel.add(label);
        }

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
                edit.setBounds(950, 5, 46, 15);
                edit.setVerticalAlignment(JLabel.CENTER);
                edit.setHorizontalAlignment(JLabel.CENTER);
                label.add(edit);

                JLabel delete = new JLabel();
                delete.setText("<html><u>Delete</u></html>");
                delete.setFont(new Font("Inter", Font.BOLD, 12));
                delete.setForeground(new Color(3, 149, 255));
                delete.setBounds(952, 23, 38, 15);
                delete.setVerticalAlignment(JLabel.CENTER);
                delete.setHorizontalAlignment(JLabel.CENTER);
                label.add(delete);

                JTextField catagory = new JTextField();
                catagory.setText(data[0]);
                catagory.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
                catagory.setForeground(super.getSecondHeadingColor());
                catagory.setBounds(15, 11, 117, 20);
                catagory.setHorizontalAlignment(JLabel.CENTER);
                catagory.setBorder(BorderFactory.createEmptyBorder());
                catagory.setEditable(false);
                catagory.setFocusable(false);

                JTextField name = new JTextField();
                name.setText(data[1]);
                name.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
                name.setForeground(super.getSecondHeadingColor());
                name.setBounds(132, 11, 117, 20);
                name.setHorizontalAlignment(JLabel.CENTER);
                name.setBorder(BorderFactory.createEmptyBorder());
                name.setEditable(false);
                name.setFocusable(false);

                JTextField originalPrice = new JTextField();
                originalPrice.setText(data[2]);
                originalPrice.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
                originalPrice.setForeground(super.getSecondHeadingColor());
                originalPrice.setBounds(249, 11, 117, 20);
                originalPrice.setHorizontalAlignment(JLabel.CENTER);
                originalPrice.setBorder(BorderFactory.createEmptyBorder());
                originalPrice.setEditable(false);
                originalPrice.setFocusable(false);

                JTextField salePrice = new JTextField();
                salePrice.setText(data[3]);
                salePrice.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
                salePrice.setForeground(super.getSecondHeadingColor());
                salePrice.setBounds(366, 11, 117, 20);
                salePrice.setHorizontalAlignment(JLabel.CENTER);
                salePrice.setBorder(BorderFactory.createEmptyBorder());
                salePrice.setEditable(false);
                salePrice.setFocusable(false);

                JTextField pricePerUnit = new JTextField();
                pricePerUnit.setText(data[4]);
                pricePerUnit.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
                pricePerUnit.setForeground(super.getSecondHeadingColor());
                pricePerUnit.setBounds(483, 11, 117, 20);
                pricePerUnit.setHorizontalAlignment(JLabel.CENTER);
                pricePerUnit.setBorder(BorderFactory.createEmptyBorder());
                pricePerUnit.setEditable(false);
                pricePerUnit.setFocusable(false);

                JTextField stocksProd = new JTextField();
                stocksProd.setText(data[5]);
                stocksProd.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
                stocksProd.setForeground(super.getSecondHeadingColor());
                stocksProd.setBounds(608, 11, 105, 19);
                stocksProd.setHorizontalAlignment(JLabel.CENTER);
                stocksProd.setBorder(BorderFactory.createEmptyBorder());
                stocksProd.setEditable(false);
                stocksProd.setFocusable(false);

                JTextField manufacture = new JTextField();
                manufacture.setText(data[6]);
                manufacture.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
                manufacture.setForeground(super.getSecondHeadingColor());
                manufacture.setBounds(720, 11, 105, 19);
                manufacture.setHorizontalAlignment(JLabel.CENTER);
                manufacture.setBorder(BorderFactory.createEmptyBorder());
                manufacture.setEditable(false);
                manufacture.setFocusable(false);

                String[] sizes = {
                        "Small",
                        "Medium",
                        "Large"
                };
                JComboBox<String> prodSizes = new JComboBox<>(sizes);
                prodSizes.setEnabled(false);
                prodSizes.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 15));
                prodSizes.setBounds(845, 5, 80, 30);
                prodSizes.setBorder(BorderFactory.createEmptyBorder());
                prodSizes.setSelectedItem(data[7]);
                prodSizes.setFocusable(false);
                prodSizes.setForeground(new Color(93, 93, 93));

                edit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String currentText = edit.getText();
                        if (currentText.contains("Edit")) {
                            edit.setText("<html><u>Update</u></html>");
                            originalPrice.setFocusable(true);
                            originalPrice.setEditable(true);
                            salePrice.setFocusable(true);
                            salePrice.setEditable(true);
                            pricePerUnit.setFocusable(true);
                            pricePerUnit.setEditable(true);
                            stocksProd.setFocusable(true);
                            stocksProd.setEditable(true);
                            manufacture.setFocusable(true);
                            manufacture.setEditable(true);
                            prodSizes.setEnabled(true);
                        }
                        else
                        {
                            if(!UIHandler.isNumbers(originalPrice.getText()) || !UIHandler.isNumbers(salePrice.getText()) || !UIHandler.isNumbers(pricePerUnit.getText()) || manufacture.getText().trim().isEmpty()){
                                JOptionPane.showMessageDialog(f,"Invalid Changes","Error",JOptionPane.ERROR_MESSAGE);
                            }
                            else if(Integer.parseInt(salePrice.getText())<Integer.parseInt(originalPrice.getText())){
                                JOptionPane.showMessageDialog(f,"Sales Price Less than Orignal", "Error",JOptionPane.ERROR_MESSAGE);
                            }
                            else{
                                String improved = catagory.getText() + "," + name.getText() + "," + originalPrice.getText() + "," + salePrice.getText() + "," + pricePerUnit.getText() + "," + stocksProd.getText() + "," + manufacture.getText() + "," + prodSizes.getSelectedItem().toString();
                                list = UIHandler.updateVendorProductInfo(Integer.parseInt(vendorIDValue.getText()),improved,name.getText());
                            }
                            originalPrice.setFocusable(false);
                            originalPrice.setEditable(false);
                            salePrice.setFocusable(false);
                            salePrice.setEditable(false);
                            pricePerUnit.setFocusable(false);
                            pricePerUnit.setEditable(false);
                            stocksProd.setFocusable(false);
                            stocksProd.setEditable(false);
                            manufacture.setFocusable(false);
                            manufacture.setEditable(false);
                            prodSizes.setEnabled(false);
                            edit.setText("<html><u>Edit</u></html>");
                            refreshPanel(list,f,vID,false);
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

                delete.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int result = JOptionPane.showConfirmDialog(null, "Delete Product: " + name.getText() + "\nDo you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            list = UIHandler.deleteVendorProduct(vID,catagory.getText(),name.getText(),originalPrice.getText(),salePrice.getText(),pricePerUnit.getText());
                        }
                        refreshPanel(list,f,vID,false);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        delete.setForeground(new Color(210, 26, 26));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        delete.setForeground(new Color(3, 149, 255));
                    }
                });

                label.setBackground(super.getInfoFieldColor());
                catagory.setBackground(super.getInfoFieldColor());
                name.setBackground(super.getInfoFieldColor());
                originalPrice.setBackground(super.getInfoFieldColor());
                salePrice.setBackground(super.getInfoFieldColor());
                pricePerUnit.setBackground(super.getInfoFieldColor());
                stocksProd.setBackground(super.getInfoFieldColor());
                manufacture.setBackground(super.getInfoFieldColor());
                prodSizes.setBackground(super.getInfoFieldColor());

                label.add(catagory);
                label.add(name);
                label.add(originalPrice);
                label.add(salePrice);
                label.add(pricePerUnit);
                label.add(stocksProd);
                label.add(manufacture);
                label.add(prodSizes);
                panel.add(label);
            }
        }

        scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(1000, 474));
        scroll.setBounds(315, 255, 1000, 474);
        scroll.setBorder(null);
        add(scroll);
    }

    public void refreshPanel(ArrayList<String> newList, JFrame f, int vID, boolean add) {
        vendorID = vID;
        if (scroll != null) {
            remove(scroll);
            vendorIDValue.setText("");
            producstsCountValue.setText("");
            super.removeInfoField();
        }
        setValues(newList,f,vID,add);
        super.setInfoField();
        revalidate();
        repaint();
    }

    public void resetFields(){
        vendorIDValue.setText("");
        producstsCountValue.setText("");
        branchNumber.setText("");
    }

    public JButton getLogoutButton(){return logoutButton;}
    public JButton getAddButton(){return addButton;}
    public JButton getBackButton(){return backButton;}
    public JButton getBranchInfoButton(){return branchInfoButton;}
    public JButton getEmployeeInfoButton(){return employeeInfoButton;}

    public ArrayList<String> getList(){return list;}
    public JPanel getPanel(){return this;}
    public int getBranchID(){
        return Integer.parseInt(branchNumber.getText());
    }
    public int getVendorID(){return vendorID;}
}