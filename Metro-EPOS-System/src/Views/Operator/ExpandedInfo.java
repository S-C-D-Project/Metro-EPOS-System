package Views.Operator;
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

public class ExpandedInfo extends Theme {
    private BufferedImage image;
    private JLabel user;
    private JLabel branchID;
    private int vendorID;

    private JScrollPane scroll;

    private JButton logoutButton;
    private JButton addButton;
    private JButton backButton;

    private Image vendorLogo;
    private ArrayList<String> list;
    private JLabel branchNumber;
    private JLabel productsCountHeading;
    private JLabel vendorIDHeading;

    private JLabel producstsCountValue;
    private JLabel vendorIDValue;

    private String vendorProfileImgPath = "Images/DataOperatorProfile.png";
    private String vendorLogoPath = "Images/VendorInfoIcon.png";

    public ExpandedInfo()
    {
        super.setLineSize5(315,120);
        super.setLineSize5(315,138);
        super.setLineSizeCustom(315,212,2);
        super.setLineSizeCustom(315,252,2);
        super.setText("Data Entry Operator");
        super.setLogoutLogo();
        super.setProfileLogo(vendorProfileImgPath);

        setHeading();
        setLogo();
        setButtons();

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
            logoLabel.setBounds(76, 278, 20, 20);
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

        add(logoutButton);
        add(addButton);
        add(backButton);
    }

    private void setHeading(){
        JLabel vendorInfo = new JLabel("Vendor Info");
        vendorInfo.setForeground(super.getSideMenuSelectedTextColor());
        vendorInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        vendorInfo.setBounds(110,283,86,10);
        add(vendorInfo);

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
            save.setBounds(855, 13, 46, 15);
            save.setVerticalAlignment(JLabel.CENTER);
            save.setHorizontalAlignment(JLabel.CENTER);
            label.add(save);

            JLabel Cancel = new JLabel();
            Cancel.setText("Cancel");
            Cancel.setFont(new Font("Inter", Font.BOLD, 12));
            Cancel.setForeground(new Color(255, 3, 3));
            Cancel.setBounds(900, 13, 55, 15);
            Cancel.setVerticalAlignment(JLabel.CENTER);
            Cancel.setHorizontalAlignment(JLabel.CENTER);
            label.add(Cancel);

            String[] categories = {
                    "_",
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
            category.setMaximumRowCount(11);


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

            save.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(Objects.requireNonNull(category.getSelectedItem()).toString().trim().equals("_") || category.getSelectedItem().toString().trim().isEmpty() || name.getText().trim().isEmpty() || name.getText().trim().equals("_") ||!UIHandler.isNumbers(originalPrice.getText()) || !UIHandler.isNumbers(salePrice.getText()) || !UIHandler.isNumbers(pricePerUnit.getText())){
                        JOptionPane.showMessageDialog(f,"Invalid Inputs", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        String adding = category.getSelectedItem().toString() + "," + name.getText() + "," + originalPrice.getText() + "," + salePrice.getText() + "," + pricePerUnit.getText();
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

            name.setFocusable(true);
            name.setEditable(true);
            originalPrice.setFocusable(true);
            originalPrice.setEditable(true);
            salePrice.setFocusable(true);
            salePrice.setEditable(true);
            pricePerUnit.setFocusable(true);
            pricePerUnit.setEditable(true);

            label.add(category);
            label.add(name);
            label.add(originalPrice);
            label.add(salePrice);
            label.add(pricePerUnit);
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
                edit.setBounds(855, 13, 46, 15);
                edit.setVerticalAlignment(JLabel.CENTER);
                edit.setHorizontalAlignment(JLabel.CENTER);
                label.add(edit);

                JLabel delete = new JLabel();
                delete.setText("<html><u>Delete</u></html>");
                delete.setFont(new Font("Inter", Font.BOLD, 12));
                delete.setForeground(new Color(3, 149, 255));
                delete.setBounds(900, 13, 55, 15);
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
                        }
                        else
                        {
                            if(!UIHandler.isNumbers(originalPrice.getText()) || !UIHandler.isNumbers(salePrice.getText()) || !UIHandler.isNumbers(pricePerUnit.getText())){
                                JOptionPane.showMessageDialog(f,"Invalid Changes","Error",JOptionPane.ERROR_MESSAGE);
                            }
                            else{
                                String improved = catagory.getText() + "," + name.getText() + "," + originalPrice.getText() + "," + salePrice.getText() + "," + pricePerUnit.getText();
                                list = UIHandler.updateVendorProductInfo(Integer.parseInt(vendorIDValue.getText()),improved,name.getText());
                            }
                            originalPrice.setFocusable(false);
                            originalPrice.setEditable(false);
                            salePrice.setFocusable(false);
                            salePrice.setEditable(false);
                            pricePerUnit.setFocusable(false);
                            pricePerUnit.setEditable(false);
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

                label.add(catagory);
                label.add(name);
                label.add(originalPrice);
                label.add(salePrice);
                label.add(pricePerUnit);
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

    public ArrayList<String> getList(){return list;}
    public JPanel getPanel(){return this;}
    public int getBranchID(){
        return Integer.parseInt(branchNumber.getText());
    }
    public int getVendorID(){return vendorID;}
}