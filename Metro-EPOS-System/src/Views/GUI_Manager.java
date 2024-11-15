package Views;
import Views.Cashier.SalesData;
import Views.Frame.frame;

import javax.swing.*;
import java.util.ArrayList;

public class GUI_Manager
{
    JPanel oldPanel;
    private SalesData sales;

    public GUI_Manager()
    {
        frame f = new frame();

        ArrayList<String> list = new ArrayList<>();
        for(int i=0; i<3; i++){
            list.add("100,100,100");
        }

        sales = new SalesData("Asfandyar","1234");
        sales.refreshPanel(list);

        //-------------------CASHIER PANEL LOGIC----------------------------
        sales.getEnterButton().addActionListener(e->{
            JOptionPane.showMessageDialog(f.getFrame(),"Enter Pressed","Message",JOptionPane.INFORMATION_MESSAGE);
        });
        sales.getLogoutButton().addActionListener(e->{
            JOptionPane.showMessageDialog(f.getFrame(),"Logout Pressed","Message",JOptionPane.INFORMATION_MESSAGE);
        });
        sales.getResetButton().addActionListener(e->{
            JOptionPane.showMessageDialog(f.getFrame(),"Reset Pressed","Message",JOptionPane.INFORMATION_MESSAGE);
        });

        f.addPanel(sales.getPanel());
        oldPanel = sales.getPanel();
        f.show();
    }

    public static void main(String[] args) {
        GUI_Manager g = new GUI_Manager();
    }
}
