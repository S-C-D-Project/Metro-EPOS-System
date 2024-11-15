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
}
