package Views.Cashier;
import Views.Decorate.Theme;

import javax.swing.*;
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
}
