package Views.Cashier;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class addOns extends JPanel {
    JFrame f;
    JTextField additions;
    JTextField receivedAmount;
    double total;
    JButton ok_Button = new JButton("OK");
    JButton cancel_Button = new JButton("Cancel");
    JLabel total_value;

    public void show(double t, JFrame f1)
    {
        f = new JFrame();
        f.setUndecorated(true);
        JPanel contentPane = (JPanel) f.getContentPane();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        contentPane.setBorder(border);
        f.setSize(615,149);
        f.setResizable(false);
        f.setLocationRelativeTo(f1);


        setLayout(null);
        setBackground(Color.white);
        total = t;
        setHeading();
        setFields();
        setButtons();

        f.add(this.getPanel());
        f.setVisible(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    private void setHeading(){
        JLabel additionals = new JLabel("Additional Charges:");
        JLabel received = new JLabel("Received Amount:");
        JLabel total_label = new JLabel("Total(Rs): ");
        total_value = new JLabel(String.valueOf(total));

        additionals.setForeground(new Color(93,93,93));
        received.setForeground(new Color(93,93,93));
        total_label.setForeground(new Color(93,93,93));
        total_value.setForeground(new Color(9,95,197));

        additionals.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        received.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));
        total_label.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,20));
        total_value.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,20));

        additionals.setBounds(17,35,175,22);
        received.setBounds(17,83,175,22);
        total_label.setBounds(464,9,99,24);
        total_value.setBounds(464,33,148,24);

        add(additionals);
        add(received);
        add(total_label);
        add(total_value);
    }

    private void setFields(){
        additions = new JTextField();
        additions.setBounds(194,33,157,26);
        additions.setForeground(new Color(93,93,93));
        additions.setBackground(new Color(217,217,217));
        additions.setBorder(BorderFactory.createEmptyBorder());
        additions.setFont(new Font("Arial",Font.BOLD,18));
        additions.setHorizontalAlignment(JTextField.CENTER);

        receivedAmount = new JTextField();
        receivedAmount.setBounds(194,81,157,26);
        receivedAmount.setForeground(new Color(93,93,93));
        receivedAmount.setBackground(new Color(217,217,217));
        receivedAmount.setBorder(BorderFactory.createEmptyBorder());
        receivedAmount.setFont(new Font("Arial",Font.BOLD,18));
        receivedAmount.setHorizontalAlignment(JTextField.CENTER);

        add(additions);
        add(receivedAmount);
    }

    private void setButtons(){
        ok_Button.setForeground(Color.WHITE);
        ok_Button.setFocusable(false);
        ok_Button.setBorderPainted(false);
        ok_Button.setContentAreaFilled(false);
        ok_Button.setBounds(423, 79, 80, 25);
        ok_Button.setOpaque(true);
        ok_Button.setBackground(new Color(155,103,56));

        cancel_Button.setForeground(Color.WHITE);
        cancel_Button.setFocusable(false);
        cancel_Button.setBorderPainted(false);
        cancel_Button.setContentAreaFilled(false);
        cancel_Button.setBounds(516, 79, 80, 25);
        cancel_Button.setOpaque(true);
        cancel_Button.setBackground(new Color(155,103,56));

        add(ok_Button);
        add(cancel_Button);
    }

    public void remove()
    {
        total_value.setText("");
        additions.setText("");
        receivedAmount.setText("");
        f.dispose();
    }

    public double getTotal(){return total;}
    public String getReceivedAmonunt(){return receivedAmount.getText();}
    public String getAddionalCharges(){return additions.getText();}
    public JButton getCancel_Button(){return cancel_Button;}
    public JButton getOk_Button(){return ok_Button;}
    public JPanel getPanel(){return this;}
}
