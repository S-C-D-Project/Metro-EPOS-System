package Views.Cashier;
import Views.Decorate.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

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
    private JButton resetButton;

    public SalesData(String name, String branchID)
    {
        super.setLineSize5(313,120);
        super.setLineSize5(313,138);
        super.setLineSizeCustom(313,306,3);
        super.setLineSizeCustom(313,351,3);
        super.setLineSizeCustom(313,399,2);
        super.setText("Cashier");
        super.setLogoutLogo();
        super.setProfileLogo("Images/CashierLogo.png");
        super.setRectangle(48,272);

        setNames(name,branchID);
        setHeading();
        setFields();
        setBottons();

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

    private void setBottons(){
        logoutButton = new JButton();
        logoutButton.setBounds(44, 684, 84, 19);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setOpaque(false);

        enterButton = new JButton("Enter");
        enterButton.setForeground(super.getInfoFieldColor());
        enterButton.setFocusable(false);
        enterButton.setBounds(786, 204, 100, 30);
        enterButton.setOpaque(true);
        enterButton.setBackground(super.getInfoFieldButtonColor());

        resetButton = new JButton("Print");
        resetButton.setForeground(super.getInfoFieldColor());
        resetButton.setFocusable(false);
        resetButton.setBounds(1195, 315, 100, 30);
        resetButton.setOpaque(true);
        resetButton.setBackground(super.getInfoFieldButtonColor());

        add(logoutButton);
        add(enterButton);
        add(resetButton);
    }

    private void setHeading(){
        JLabel enterProductID = new JLabel("Enter Product ID:");
        JLabel enterQty = new JLabel("Enter Quantity:");
        JLabel totalRS = new JLabel("Total (RS):");
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
}
