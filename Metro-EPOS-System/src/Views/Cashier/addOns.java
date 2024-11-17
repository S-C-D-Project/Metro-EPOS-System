package Views.Cashier;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class addOns extends JPanel {
    public addOns()
    {
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

    public void setHeading(){

    }
}
