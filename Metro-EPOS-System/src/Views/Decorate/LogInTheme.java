package Views.Decorate;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LogInTheme extends JPanel {
    private Color firstHeading;
    private Color secondHeading;
    private Color buttonColor;
    private Color backgorundColor;

    private JButton logInButton;
    private JButton saveButton;

    private JTextField id;
    private JPasswordField pass;
    private JTextField newPass;
    private JTextField confirmPass;

    private JButton selectedAdminButton;
    private JButton selectedManagerButton;
    private JButton selectedCashierButton;
    private JButton selectedDataOperatorButton;

    private JButton notSelectedAdminButton;
    private JButton notSelectedManagerButton;
    private JButton notSelectedCashierButton;
    private JButton notSelectedDataOperatorButton;

    public LogInTheme() throws IOException {
        firstHeading = new Color(70,70,70);
        secondHeading = new Color(126,126,126);
        buttonColor = new Color(155,103,56);
        backgorundColor = Color.WHITE;

        setLayout(null);
        setBackground(backgorundColor);

        setHeadings();
        setLine();
        setButton();
        setFields();
        setSideImg();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    private void setHeadings(){
        JLabel signIn = new JLabel("Sign In");
        JLabel role = new JLabel("Please select your role");
        JLabel id = new JLabel("ID");
        JLabel pass = new JLabel("PASSWORD");

        signIn.setForeground(firstHeading);
        role.setForeground(secondHeading);
        id.setForeground(secondHeading);
        pass.setForeground(secondHeading);

        signIn.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 35));
        role.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 20));
        id.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 25));
        pass.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 25));

        signIn.setBounds(761,23,121,45);
        role.setBounds(761,116,284,29);
        id.setBounds(761,314,86,29);
        pass.setBounds(761,415,150,29);

        add(signIn);
        add(role);
        add(id);
        add(pass);
    }

    private void setButton(){
        logInButton = new JButton("LogIn");
        logInButton.setBackground(buttonColor);
        logInButton.setForeground(Color.white);
        logInButton.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 25));
        logInButton.setBounds(761,554,554,58);
        logInButton.setFocusable(false);
        logInButton.setBorderPainted(false);
        add(logInButton);
    }

    private void setFields() {
        try {
            BufferedImage logo1 = ImageIO.read(new File("Metro-EPOS-System/Images/profile.png"));
            int width1 = 30;
            int height1 = 29;
            Image scaledLogo1 = logo1.getScaledInstance(width1, height1, Image.SCALE_SMOOTH);
            JLabel field1 = new JLabel(new ImageIcon(scaledLogo1));
            field1.setBackground(backgorundColor);
            field1.setBounds(761,347,554,50);
            field1.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(188, 188, 188), 3),
                    BorderFactory.createEmptyBorder(0, 10, 0, 0)
            ));
            field1.setOpaque(true);
            field1.setHorizontalAlignment(JLabel.LEFT);
            add(field1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            BufferedImage logo2 = ImageIO.read(new File("Metro-EPOS-System/Images/Lock.png"));
            int width2 = 30;
            int height2 = 29;
            Image scaledLogo2 = logo2.getScaledInstance(width2, height2, Image.SCALE_SMOOTH);
            JLabel field2 = new JLabel(new ImageIcon(scaledLogo2));
            field2.setBackground(backgorundColor);
            field2.setBounds(761,448,554,50);
            field2.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(188, 188, 188), 3),
                    BorderFactory.createEmptyBorder(0, 10, 0, 0)
            ));
            field2.setHorizontalAlignment(JLabel.LEFT);
            field2.setOpaque(true);
            add(field2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        id = new JTextField();
        id.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 25));
        id.setBorder(null);
        id.setBackground(backgorundColor);
        id.setForeground(secondHeading);
        id.setBounds(844,350,458,44);
        id.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 25));
        add(id);

        pass = new JPasswordField();
        pass.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 25));
        pass.setBorder(null);
        pass.setForeground(secondHeading);
        pass.setBackground(backgorundColor);
        pass.setBounds(844,451,458,44);
        pass.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 25));
        add(pass);
    }

    private void setLine(){
        JLabel line = new JLabel();
        line.setBackground(buttonColor);
        line.setBounds(765,78,117,8);
        line.setOpaque(true);
        add(line);
    }

    private void setSideImg() throws IOException {
        BufferedImage logo1 = ImageIO.read(new File("Metro-EPOS-System/Images/Bacground.png"));
        int width1 = 729;
        int height1 = 730;
        Image scaledLogo1 = logo1.getScaledInstance(width1, height1, Image.SCALE_SMOOTH);
        JLabel field1 = new JLabel(new ImageIcon(scaledLogo1));
        field1.setBackground(backgorundColor);
        field1.setBounds(0,0,729,730);
        add(field1);
    }

    public void setUnselectedAdminButton()
    {
        try {
            BufferedImage img = ImageIO.read(new File("Metro-EPOS-System/Images/notselectedAdmin.png"));
            int buttonWidth = 121;
            int buttonHeight = 78;
            Image scaledImg = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            notSelectedAdminButton = new JButton(new ImageIcon(scaledImg));
            notSelectedAdminButton.setBorderPainted(false);
            notSelectedAdminButton.setFocusPainted(false);
            notSelectedAdminButton.setContentAreaFilled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        notSelectedAdminButton.setBounds(761,179,121,78);
        add(notSelectedAdminButton);
    }
    public void setUnselectedManagerButton(){
        try {
            BufferedImage img = ImageIO.read(new File("Metro-EPOS-System/Images/notselectedManager.png"));
            int buttonWidth = 121;
            int buttonHeight = 78;
            Image scaledImg = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            notSelectedManagerButton = new JButton(new ImageIcon(scaledImg));
            notSelectedManagerButton.setBorderPainted(false);
            notSelectedManagerButton.setFocusPainted(false);
            notSelectedManagerButton.setContentAreaFilled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        notSelectedManagerButton.setBounds(905,179,121,78);
        add(notSelectedManagerButton);
    }
    public void setUnselectedCashierButton(){
        try {
            BufferedImage img = ImageIO.read(new File("Metro-EPOS-System/Images/notselectedCashier.png"));
            int buttonWidth = 121;
            int buttonHeight = 78;
            Image scaledImg = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            notSelectedCashierButton = new JButton(new ImageIcon(scaledImg));
            notSelectedCashierButton.setBorderPainted(false);
            notSelectedCashierButton.setFocusPainted(false);
            notSelectedCashierButton.setContentAreaFilled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        notSelectedCashierButton.setBounds(1049,179,121,78);
        add(notSelectedCashierButton);
    }
    public void setUnselectedDataOperatorButton(){
        try {
            BufferedImage img = ImageIO.read(new File("Metro-EPOS-System/Images/notselectedOperator.png"));
            int buttonWidth = 122;
            int buttonHeight = 78;
            Image scaledImg = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            notSelectedDataOperatorButton = new JButton(new ImageIcon(scaledImg));
            notSelectedDataOperatorButton.setBorderPainted(false);
            notSelectedDataOperatorButton.setFocusPainted(false);
            notSelectedDataOperatorButton.setContentAreaFilled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        notSelectedDataOperatorButton.setBounds(1193,179,122,78);
        add(notSelectedDataOperatorButton);
    }

    public void setSelectedAdminButton(){
        try {
            BufferedImage img = ImageIO.read(new File("Metro-EPOS-System/Images/selectedAdmin.png"));
            int buttonWidth = 121;
            int buttonHeight = 92;
            Image scaledImg = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            selectedAdminButton = new JButton(new ImageIcon(scaledImg));
            selectedAdminButton.setBorderPainted(false);
            selectedAdminButton.setFocusPainted(false);
            selectedAdminButton.setContentAreaFilled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        selectedAdminButton.setBounds(761,179,121,92);
        add(selectedAdminButton);
    }

    public void setSelectedManagerButton(){
        try {
            BufferedImage img = ImageIO.read(new File("Metro-EPOS-System/Images/selectedManager.png"));
            int buttonWidth = 121;
            int buttonHeight = 92;
            Image scaledImg = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            selectedManagerButton = new JButton(new ImageIcon(scaledImg));
            selectedManagerButton.setBorderPainted(false);
            selectedManagerButton.setFocusPainted(false);
            selectedManagerButton.setContentAreaFilled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        selectedManagerButton.setBounds(905,179,121,92);
        add(selectedManagerButton);
    }

    public void setSelectedCashierButton(){
        try {
            BufferedImage img = ImageIO.read(new File("Metro-EPOS-System/Images/selectedCashier.png"));
            int buttonWidth = 121;
            int buttonHeight = 92;
            Image scaledImg = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            selectedCashierButton = new JButton(new ImageIcon(scaledImg));
            selectedCashierButton.setBorderPainted(false);
            selectedCashierButton.setFocusPainted(false);
            selectedCashierButton.setContentAreaFilled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        selectedCashierButton.setBounds(1049,179,121,92);
        add(selectedCashierButton);
    }

    public void setSelectedDataOperatorButton(){
        try {
            BufferedImage img = ImageIO.read(new File("Metro-EPOS-System/Images/selectedOperator.png"));
            int buttonWidth = 122;
            int buttonHeight = 92;
            Image scaledImg = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            selectedDataOperatorButton = new JButton(new ImageIcon(scaledImg));
            selectedDataOperatorButton.setBorderPainted(false);
            selectedDataOperatorButton.setFocusPainted(false);
            selectedDataOperatorButton.setContentAreaFilled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        selectedDataOperatorButton.setBounds(1193,179,122,92);
        add(selectedDataOperatorButton);
    }

    public void displayNewUserWindow(JFrame f){
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setSize(979,512);
        frame.setLocationRelativeTo(f);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(backgorundColor);

        saveButton = new JButton("SAVE");
        saveButton.setBackground(buttonColor);
        saveButton.setForeground(Color.white);
        saveButton.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 18));
        saveButton.setBounds(770,354,124,38);
        saveButton.setFocusable(false);
        saveButton.setBorderPainted(false);
        panel.add(saveButton);

        JLabel nPass = new JLabel("NEW PASSWORD");
        JLabel cpass = new JLabel("CONFIRM PASSWORD");
        nPass.setForeground(secondHeading);
        cpass.setForeground(secondHeading);
        nPass.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 25));
        cpass.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 25));
        nPass.setBounds(93,205,212,29);
        cpass.setBounds(93,315,274,29);
        panel.add(nPass);
        panel.add(cpass);

        try {
            BufferedImage logo1 = ImageIO.read(new File("Metro-EPOS-System/Images/Lock.png"));
            int width1 = 30;
            int height1 = 29;
            Image scaledLogo1 = logo1.getScaledInstance(width1, height1, Image.SCALE_SMOOTH);
            JLabel field1 = new JLabel(new ImageIcon(scaledLogo1));
            field1.setBackground(backgorundColor);
            field1.setBounds(93,238,554,50);
            field1.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(188, 188, 188), 3),
                    BorderFactory.createEmptyBorder(0, 10, 0, 0)
            ));
            field1.setOpaque(true);
            field1.setHorizontalAlignment(JLabel.LEFT);
            panel.add(field1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            BufferedImage logo2 = ImageIO.read(new File("Metro-EPOS-System/Images/Lock.png"));
            int width2 = 30;
            int height2 = 29;
            Image scaledLogo2 = logo2.getScaledInstance(width2, height2, Image.SCALE_SMOOTH);
            JLabel field2 = new JLabel(new ImageIcon(scaledLogo2));
            field2.setBackground(backgorundColor);
            field2.setBounds(93,348,554,50);
            field2.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(188, 188, 188), 3),
                    BorderFactory.createEmptyBorder(0, 10, 0, 0)
            ));
            field2.setHorizontalAlignment(JLabel.LEFT);
            field2.setOpaque(true);
            panel.add(field2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        newPass = new JTextField();
        newPass.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 25));
        newPass.setBorder(null);
        newPass.setBackground(backgorundColor);
        newPass.setForeground(secondHeading);
        newPass.setBounds(174,241,447,44);
        newPass.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 25));
        panel.add(newPass);

        confirmPass = new JPasswordField();
        confirmPass.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 25));
        confirmPass.setBorder(null);
        confirmPass.setForeground(secondHeading);
        confirmPass.setBackground(backgorundColor);
        confirmPass.setBounds(174,351,447,44);
        confirmPass.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 25));
        panel.add(confirmPass);

        JLabel text = new JLabel("NOTE: First LogIn Detected! Password Change Required...");
        text.setForeground(new Color(173,212,19));
        text.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 24));
        text.setBounds(93,53,696,40);
        panel.add(text);


        frame.add(panel);
        frame.setVisible(true);
    }
    public String getID(){return id.getText();}
    public String getPass(){return pass.getText();}
    public String getNewPass(){return  newPass.getText();}
    public String getConfirmPass(){return  confirmPass.getText();}
    public JButton getLogInButton() {
        return logInButton;
    }
    public JButton getSaveButton(){return saveButton;}
    public JPanel getPanel(){return this;}
}
