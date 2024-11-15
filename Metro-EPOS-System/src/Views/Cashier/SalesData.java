package Views.Cashier;
import Views.Decorate.Theme;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SalesData extends Theme {
    private BufferedImage image;
    private JLabel user;
    private JLabel branchID;
    private JLabel total;
    private JScrollPane scroll;
    private JTextField discountBox;
    private JTextField productID;
    private JTextField quantity;

    private JButton logoutButton;
    private JButton enterButton;
    private JButton printButton;
    private JButton fixButton;

    private double discount;
    private ArrayList<String> list;

    public SalesData(String name, String branchID)
    {
        super.setLineSize5(313,120);
        super.setLineSize5(313,138);
        super.setLineSizeCustom(313,306,3);
        super.setLineSizeCustom(313,351,3);
        super.setLineSizeCustom(313,399,2);
        super.setText("Cashier");
        super.setLogoutLogo();
        super.setProfileLogo("Metro-EPOS-System/Images/CashierLogo.png");
        super.setRectangle(48,272);

        setNames(name,branchID);
        setHeading();
        setFields();
        setButtons();

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

        branchID = new JLabel();
        branchID.setText("Branch ID: " + ID);
        branchID.setFont(new Font("Inter",Font.BOLD,25));
        branchID.setForeground(Color.BLACK);
        branchID.setBounds(345,55,221,30);

        total = new JLabel("0.0");
        total.setFont(new Font("Inter",Font.BOLD,25));
        total.setForeground(new Color(9,95,197));
        total.setBounds(460,312,148,30);
        total.setVerticalAlignment(JLabel.CENTER);

        add(total);
        add(user);
        add(branchID);
    }

    private void setFields(){
        productID = new JTextField();
        productID.setBounds(478,185,216,25);
        productID.setForeground(new Color(93,93,93));
        productID.setBackground(new Color(217,217,217));
        productID.setBorder(BorderFactory.createEmptyBorder());
        productID.setFont(new Font("Arial",Font.BOLD,18));
        productID.setHorizontalAlignment(JTextField.CENTER);

        quantity = new JTextField();
        quantity.setBounds(478,235,216,25);
        quantity.setForeground(new Color(93,93,93));
        quantity.setBackground(new Color(217,217,217));
        quantity.setBorder(BorderFactory.createEmptyBorder());
        quantity.setFont(new Font("Arial",Font.BOLD,18));
        quantity.setHorizontalAlignment(JTextField.CENTER);

        discountBox = new JTextField();
        discountBox.setBounds(808,317,149,26);
        discountBox.setForeground(new Color(93,93,93));
        discountBox.setBackground(new Color(217,217,217));
        discountBox.setBorder(BorderFactory.createEmptyBorder());
        discountBox.setFont(new Font("Arial",Font.BOLD,18));
        discountBox.setHorizontalAlignment(JTextField.CENTER);

        add(productID);
        add(quantity);
        add(discountBox);
    }

    private void setButtons(){
        logoutButton = new JButton();
        logoutButton.setBounds(44, 684, 84, 19);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setOpaque(false);

        enterButton = new JButton("Enter");
        enterButton.setForeground(super.getInfoFieldColor());
        enterButton.setFocusable(false);
        enterButton.setBorderPainted(false);
        enterButton.setContentAreaFilled(false);
        enterButton.setBounds(786, 204, 100, 30);
        enterButton.setOpaque(true);
        enterButton.setBackground(super.getInfoFieldButtonColor());

        printButton = new JButton("Print");
        printButton.setForeground(super.getInfoFieldColor());
        printButton.setFocusable(false);
        printButton.setBorderPainted(false);
        printButton.setContentAreaFilled(false);
        printButton.setBounds(1195, 315, 100, 30);
        printButton.setOpaque(true);
        printButton.setBackground(super.getInfoFieldButtonColor());

        fixButton = new JButton("Fix");
        fixButton.setForeground(super.getInfoFieldColor());
        fixButton.setFocusable(false);
        fixButton.setBorderPainted(false);
        fixButton.setContentAreaFilled(false);
        fixButton.setBounds(972, 317, 60, 26);
        fixButton.setOpaque(true);
        fixButton.setBackground(super.getInfoFieldButtonColor());

        add(logoutButton);
        add(enterButton);
        add(printButton);
        add(fixButton);
    }

    private void setHeading(){
        JLabel enterProductID = new JLabel("Enter Product ID:");
        JLabel enterQty = new JLabel("Enter Quantity:");
        JLabel totalRS = new JLabel("Total (Rs):");
        JLabel enterDiscount = new JLabel("Enter Discount (%):");
        JLabel product = new JLabel("Product");
        JLabel qty = new JLabel("Qty");
        JLabel price = new JLabel("Price");

        enterProductID.setForeground(super.getSecondHeadingColor());
        enterQty.setForeground(super.getSecondHeadingColor());
        totalRS.setForeground(super.getSecondHeadingColor());
        enterDiscount.setForeground(super.getSecondHeadingColor());
        product.setForeground(super.getThirdHeadingColor());
        qty.setForeground(super.getThirdHeadingColor());
        price.setForeground(super.getThirdHeadingColor());

        enterProductID.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        enterQty.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        enterDiscount.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        totalRS.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,25));
        product.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        qty.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        price.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));

        enterProductID.setBounds(313,187,158,22);
        enterQty.setBounds(313,237,148,22);
        totalRS.setBounds(326,312,124,30);
        enterDiscount.setBounds(626,316,179,22);

        product.setBounds(356,365,73,24);
        qty.setBounds(465,365,48,24);
        price.setBounds(556,365,94,24);

        add(enterProductID);
        add(enterQty);
        add(enterDiscount);
        add(totalRS);
        add(product);
        add(qty);
        add(price);
    }

    public void setValues(ArrayList<String> l, double dis)
    {
        list = l;
        discount = dis;
        double sum = 0;
        if(list!=null) {
            for (int i = 0; i < list.size(); i++) {
                String[] line = list.get(i).split(",");
                sum = sum + Integer.parseInt(line[2]);
            }
            sum = sum - ( sum * discount/100);
        }

        total.setText(String.valueOf(sum));
        discountBox.setText(String.valueOf(discount));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(super.getInfoFieldColor());
        panel.setBorder(null);

        int value = 0;

        if(list!=null) {
            for (int i = 0; i < list.size(); i++) {
                String[] data = list.get(i).split(",");

                JLabel label = new JLabel();
                label.setLayout(null);
                label.setOpaque(true);
                label.setPreferredSize(new Dimension(996, 40));
                label.setMaximumSize(new Dimension(996, 40));
                label.setBorder(null);


                JLabel edit = new JLabel();
                edit.setText("<html><u>Edit</u></html>");
                edit.setFont(new Font("Inter", Font.BOLD, 12));
                edit.setForeground(new Color(3, 149, 255));
                edit.setBounds(855, 13, 46, 14);
                edit.setVerticalAlignment(JLabel.CENTER);
                edit.setHorizontalAlignment(JLabel.CENTER);
                label.add(edit);

                JLabel delete = new JLabel();
                delete.setText("<html><u>Delete</u></html>");
                delete.setFont(new Font("Inter", Font.BOLD, 12));
                delete.setForeground(new Color(3, 149, 255));
                delete.setBounds(900, 13, 46, 14);
                delete.setVerticalAlignment(JLabel.CENTER);
                delete.setHorizontalAlignment(JLabel.CENTER);
                label.add(delete);

                JTextField product = new JTextField();
                product.setText(data[0]);
                product.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                product.setForeground(new Color(93, 93, 93));
                product.setBounds(5, 13, 129, 14);
                product.setHorizontalAlignment(JLabel.CENTER);
                product.setBorder(BorderFactory.createEmptyBorder());
                product.setEditable(false);
                product.setFocusable(false);

                JTextField qty = new JTextField();
                qty.setText(data[1]);
                qty.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                qty.setForeground(new Color(93, 93, 93));
                qty.setBounds(129, 13, 75, 14);
                qty.setHorizontalAlignment(JLabel.CENTER);
                qty.setBorder(BorderFactory.createEmptyBorder());
                qty.setEditable(false);
                qty.setFocusable(false);

                JTextField price = new JTextField();
                price.setText(data[2]);
                price.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                price.setForeground(new Color(93, 93, 93));
                price.setBounds(204, 13, 112, 14);
                price.setHorizontalAlignment(JLabel.CENTER);
                price.setBorder(BorderFactory.createEmptyBorder());
                price.setEditable(false);
                price.setFocusable(false);

                edit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String currentText = edit.getText();
                        if (currentText.contains("Edit")) {
                            edit.setText("<html><u>Update</u></html>");
                            qty.setFocusable(true);
                            qty.setEditable(true);
                        } else {
                            qty.setFocusable(false);
                            qty.setEditable(false);
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

                delete.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int result = JOptionPane.showConfirmDialog(null, "Delete Product: " + product.getText() + "\nDo you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
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

                if (value == 0) {
                    label.setBackground(super.getInfoFieldColor());
                    product.setBackground(super.getInfoFieldColor());
                    price.setBackground(super.getInfoFieldColor());
                    qty.setBackground(super.getInfoFieldColor());
                    value = 1;
                } else {
                    value = 0;
                    label.setBackground(new Color(217, 217, 217));
                    product.setBackground(new Color(217, 217, 217));
                    price.setBackground(new Color(217, 217, 217));
                    qty.setBackground(new Color(217, 217, 217));
                }

                label.add(product);
                label.add(price);
                label.add(qty);
                panel.add(label);
            }
        }

        scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(1000, 431));
        scroll.setBounds(313, 401, 1000, 431);
        scroll.setBorder(null);

        add(scroll);
    }

    public void refreshPanel(ArrayList<String> newList, double discount) {
        if (scroll != null) {
            remove(scroll);
            total.setText("");
            discountBox.setText("");
            super.removeInfoField();
        }
        setValues(newList,discount);
        super.setInfoField();
        revalidate();
        repaint();
    }

    public String getProductID(){return productID.getText();}
    public String getQuantity(){return quantity.getText();}
    public JButton getLogoutButton(){return logoutButton;}
    public JButton getPrintButton(){return printButton;}
    public JButton getEnterButton(){return enterButton;}
    public JButton getFixButton(){return fixButton;}
    public String getDiscount(){
        return discountBox.getText();
    }
    public double getDiscountValue(){return discount;}
    public ArrayList<String> getList(){return list;}
    public JPanel getPanel(){return this;}
}
