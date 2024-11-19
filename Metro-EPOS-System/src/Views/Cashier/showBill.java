package Views.Cashier;

import javax.swing.*;
import java.awt.*;

public class showBill {
    private JFrame frame;
    private JLabel label;

    public showBill(String path, JFrame f) {
        initFrame(f);
        displayBill(path);
    }

    private void initFrame(JFrame f) {
        frame = new JFrame("Bill Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(f);
        frame.setResizable(true);

        JScrollPane scrollPane = new JScrollPane();
        frame.add(scrollPane, BorderLayout.CENTER);

        label = new JLabel("", SwingConstants.CENTER);
        scrollPane.setViewportView(label);
    }

    private void displayBill(String path)
    {
        try
        {
            ImageIcon imageIcon = new ImageIcon(path);
            Image image = imageIcon.getImage().getScaledInstance(-1, frame.getHeight(), Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(image));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(frame, "Failed to load the bill image: " + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        frame.setVisible(true);
    }
}
