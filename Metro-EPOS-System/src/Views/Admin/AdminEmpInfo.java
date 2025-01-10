package Views.Admin;
import Views.Decorate.Theme;
import Views.Frame.frame;
import Views.UIHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminEmpInfo extends Theme {
    private BufferedImage image;
    private JLabel user;
    private JLabel userNmae;

    private JScrollPane scroll;

    JTextField typeName;
    JTextField typePhoneNo;
    JTextField enterSalary;
    JTextField searchText;

    private JButton logoutButton;
    private JButton addButton;
    private JButton searchButton;
    private JButton branchInfoButton;
    private JButton vendorInfoButton;

    private Image vendorLogo;
    private Image employeeLogo;
    private Image branchLogo;
    private ArrayList<String> list;
    private JLabel branchNumber;
    private JLabel employeesCount;

    private String adminProfileImgPath = "Images/adminLogo.png";
    private String vendorLogoPath = "Images/vendorLogoWhite.png";
    private String searchIconPath = "Images/searchlogo.png";
    private String branchIconPath = "Images/branchWhite.png";
    private String employeeInfoLogoPath = "Images/EmpInfoLogo.png";

    public AdminEmpInfo()
    {
        super.setLineSize5(315,120);
        super.setLineSize5(315,168);
        super.setLineSizeCustom(315,248,2);
        super.setLineSizeCustom(315,288,2);
        super.setText("Admin");
        super.setLogoutLogo();
        super.setProfileLogo(adminProfileImgPath);

        setHeading();
        setLogo();
        setFields();
        setButtons();
        setSearchBar();

        super.setRectangle(48,319);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    public void setNames(String name,String name2)
    {
        user = new JLabel();
        user.setText(name);
        user.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        user.setForeground(Color.white);
        user.setBounds(12,189,250,18);
        user.setVerticalAlignment(JLabel.CENTER);
        user.setHorizontalAlignment(JLabel.CENTER);

        branchNumber = new JLabel();
        branchNumber.setText(name2);
        userNmae = new JLabel();
        userNmae.setText("Hi, " + name2 + "!");
        userNmae.setFont(new Font("Inter",Font.BOLD,25));
        userNmae.setForeground(Color.BLACK);
        userNmae.setBounds(345,55,221,30);

        add(user);
        add(userNmae);
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
            logoLabel.setBounds(76, 373, 20, 20);
            add(logoLabel);
        }

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(employeeInfoLogoPath)) {
            if (is != null) {
                employeeLogo = ImageIO.read(is);
            } else {
                System.err.println("Resource not found: " + employeeLogo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (employeeLogo != null) {
            Image scaledImage = employeeLogo.getScaledInstance(16, 20, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoLabel.setBounds(79, 325, 16, 20);
            add(logoLabel);
        }

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(branchIconPath)) {
            if (is != null) {
                branchLogo = ImageIO.read(is);
            } else {
                System.err.println("Resource not found: " + branchLogo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (branchLogo != null) {
            Image scaledImage = branchLogo.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoLabel.setBounds(78, 280, 15, 15);
            add(logoLabel);
        }
    }

    private void setFields(){
        typeName = new JTextField("  Type Name");
        typeName.setBounds(316,133,178,27);
        typeName.setForeground(new Color(173,173,173));
        typeName.setBackground(super.getInfoFieldColor());
        typeName.setBorder(new LineBorder(new Color(173,173,173),2));
        typeName.setFont(new Font("Arial",Font.BOLD,15));
        typeName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (typeName.getText().equals("  Type Name")) {
                    typeName.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (typeName.getText().isEmpty()) {
                    typeName.setText("  Type Name");
                }
            }
        });

        enterSalary = new JTextField("  Enter Salary");
        enterSalary.setBounds(507,133,178,27);
        enterSalary.setForeground(new Color(173,173,173));
        enterSalary.setBackground(super.getInfoFieldColor());
        enterSalary.setBorder(new LineBorder(new Color(173,173,173),2));
        enterSalary.setFont(new Font("Arial",Font.BOLD,15));
        enterSalary.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (enterSalary.getText().equals("  Enter Salary")) {
                    enterSalary.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (enterSalary.getText().isEmpty()) {
                    enterSalary.setText("  Enter Salary");
                }
            }
        });

        typePhoneNo = new JTextField("  Type Phone No.");
        typePhoneNo.setBounds(698,133,178,27);
        typePhoneNo.setForeground(new Color(173,173,173));
        typePhoneNo.setBackground(super.getInfoFieldColor());
        typePhoneNo.setBorder(new LineBorder(new Color(173,173,173),2));
        typePhoneNo.setFont(new Font("Arial",Font.BOLD,15));
        typePhoneNo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (typePhoneNo.getText().equals("  Type Phone No.")) {
                    typePhoneNo.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (typePhoneNo.getText().isEmpty()) {
                    typePhoneNo.setText("  Type Phone No.");
                }
            }
        });

        add(typeName);
        add(enterSalary);
        add(typePhoneNo);
    }

    private void setSearchBar(){
        try {
            BufferedImage logo = ImageIO.read(getClass().getClassLoader().getResourceAsStream(searchIconPath));
            int buttonWidth = 15;
            int buttonHeight = 15;
            Image scaledImg = logo.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            searchButton = new JButton(new ImageIcon(scaledImg));
            searchButton.setBorderPainted(false);
            searchButton.setFocusPainted(false);
            searchButton.setContentAreaFilled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        searchButton.setBounds(953,197,16,16);
        add(searchButton);

        JLabel field = new JLabel();
        field.setBackground(super.getInfoFieldColor());
        field.setBounds(596,188,387,33);
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(188, 188, 188), 2),
                BorderFactory.createEmptyBorder(0, 0, 0, 5)
        ));
        field.setHorizontalAlignment(JLabel.RIGHT);
        field.setOpaque(true);
        add(field);

        searchText = new JTextField("Search");
        searchText.setBorder(null);
        searchText.setBackground(super.getInfoFieldColor());
        searchText.setForeground(new Color(173,173,173));
        searchText.setBounds(623,190,328,29);
        searchText.setFont(new Font("Yu Gothic UI SemiBold", Font.PLAIN, 15));
        searchText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchText.getText().equals("Search")) {
                    searchText.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (searchText.getText().isEmpty()) {
                    searchText.setText("Search");
                }
            }
        });
        add(searchText);
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
        addButton.setBounds(1244, 135, 70, 23);
        addButton.setOpaque(true);
        addButton.setBackground(super.getInfoFieldButtonColor());

        branchInfoButton = new JButton();
        branchInfoButton.setBounds(79, 281, 117, 15);
        branchInfoButton.setBorderPainted(false);
        branchInfoButton.setContentAreaFilled(false);
        branchInfoButton.setOpaque(false);

        vendorInfoButton = new JButton();
        vendorInfoButton.setBounds(75, 373, 106, 20);
        vendorInfoButton.setBorderPainted(false);
        vendorInfoButton.setContentAreaFilled(false);
        vendorInfoButton.setOpaque(false);

        add(logoutButton);
        add(addButton);
        add(branchInfoButton);
        add(vendorInfoButton);
    }

    private void setHeading(){
        JLabel totalEmp = new JLabel("Total Employees:");
        JLabel branch_id = new JLabel("Branch ID");
        JLabel emp_id = new JLabel("EMP ID");
        JLabel name = new JLabel("Name");
        JLabel emial = new JLabel("Email");
        JLabel passowrd = new JLabel("Password");
        JLabel salary = new JLabel("Salary");
        JLabel pNumber = new JLabel("Phone No.");
        JLabel status = new JLabel("Status");
        JLabel role = new JLabel("Role");
        JLabel vendorInfo = new JLabel("Vendor Info");
        JLabel branchInfo = new JLabel("Branch Info");
        JLabel employeeInfo = new JLabel("Employee Info");
        employeesCount = new JLabel("");

        totalEmp.setForeground(super.getFirstHeadingColor());
        branch_id.setForeground(super.getThirdHeadingColor());
        emp_id.setForeground(super.getThirdHeadingColor());
        name.setForeground(super.getThirdHeadingColor());
        emial.setForeground(super.getThirdHeadingColor());
        passowrd.setForeground(super.getThirdHeadingColor());
        salary.setForeground(super.getThirdHeadingColor());
        pNumber.setForeground(super.getThirdHeadingColor());
        status.setForeground(super.getThirdHeadingColor());
        role.setForeground(super.getThirdHeadingColor());
        branchInfo.setForeground(super.getSideMenuTextColor());
        vendorInfo.setForeground(super.getSideMenuTextColor());
        employeeInfo.setForeground(super.getSideMenuSelectedTextColor());
        employeesCount.setForeground(new Color(9,95,197));

        totalEmp.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        branch_id.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        emp_id.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        emial.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        name.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        passowrd.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        salary.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        pNumber.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        status.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        role.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        branchInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        employeeInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        vendorInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        employeesCount.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));

        totalEmp.setBounds(316,216,127,21);
        emp_id.setBounds(319,257,54,24);
        branch_id.setBounds(391,257,73,24);
        name.setBounds(503,257,48,24);
        emial.setBounds(605,257,63,24);
        passowrd.setBounds(710,257,77,24);
        salary.setBounds(828,257,54,24);
        pNumber.setBounds(922,257,77,24);
        status.setBounds(1116,257,77,24);
        role.setBounds(1032,257,77,24);
        vendorInfo.setBounds(110,376,86,10);
        employeeInfo.setBounds(110,329,70,13);
        branchInfo.setBounds(110,283,86,13);
        employeesCount.setBounds(447,216,83,16);

        add(totalEmp);
        add(emp_id);
        add(branch_id);
        add(emial);
        add(name);
        add(passowrd);
        add(salary);
        add(pNumber);
        add(status);
        add(role);
        add(vendorInfo);
        add(employeeInfo);
        add(branchInfo);
        add(employeesCount);
    }

    public void setValues(ArrayList<String> l, frame f) throws SQLException {
        list = l;
        if(l!=null){
            employeesCount.setText(String.valueOf(list.size()));
        }
        else{
            employeesCount.setText(String.valueOf(0));
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(super.getInfoFieldColor());
        panel.setBorder(null);

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
                edit.setBounds(896, 13, 46, 14);
                edit.setVerticalAlignment(JLabel.CENTER);
                edit.setHorizontalAlignment(JLabel.CENTER);
                label.add(edit);

                JTextField empID = new JTextField();
                empID.setText(data[0]);
                empID.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                empID.setForeground(new Color(93, 93, 93));
                empID.setBounds(5, 13, 46, 14);
                empID.setHorizontalAlignment(JLabel.CENTER);
                empID.setBorder(BorderFactory.createEmptyBorder());
                empID.setEditable(false);
                empID.setFocusable(false);

                String[] array3 = UIHandler.getAllBranchIDs().split(",");
                JComboBox<String> branchID = new JComboBox<>(array3);
                branchID.setEnabled(false);
                branchID.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                branchID.setBounds(85, 11, 50, 22);
                branchID.setBorder(BorderFactory.createEmptyBorder());
                branchID.setSelectedItem(data[1]);
                branchID.setFocusable(false);
                branchID.setForeground(new Color(93, 93, 93));

                JTextField name = new JTextField();
                name.setText(data[2]);
                name.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                name.setForeground(new Color(93, 93, 93));
                name.setBounds(171, 12, 75, 18);
                name.setHorizontalAlignment(JLabel.CENTER);
                name.setBorder(BorderFactory.createEmptyBorder());
                name.setEditable(false);
                name.setFocusable(false);

                JTextField email = new JTextField();
                email.setText(data[3]);
                email.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                email.setForeground(new Color(93, 93, 93));
                email.setBounds(260, 12, 118, 18);
                email.setHorizontalAlignment(JLabel.CENTER);
                email.setBorder(BorderFactory.createEmptyBorder());
                email.setEditable(false);
                email.setFocusable(false);

                JTextField pass = new JTextField();
                pass.setText(data[4]);
                pass.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                pass.setForeground(new Color(93, 93, 93));
                pass.setBounds(378, 12, 106, 14);
                pass.setHorizontalAlignment(JLabel.CENTER);
                pass.setBorder(BorderFactory.createEmptyBorder());
                pass.setEditable(false);
                pass.setFocusable(false);

                JTextField salary = new JTextField();
                salary.setText(data[5]);
                salary.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                salary.setForeground(new Color(93, 93, 93));
                salary.setBounds(484, 13, 106, 14);
                salary.setHorizontalAlignment(JLabel.CENTER);
                salary.setBorder(BorderFactory.createEmptyBorder());
                salary.setEditable(false);
                salary.setFocusable(false);

                JTextField phone = new JTextField();
                phone.setText(data[6]);
                phone.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                phone.setForeground(new Color(93, 93, 93));
                phone.setBounds(590, 13, 106, 14);
                phone.setHorizontalAlignment(JLabel.CENTER);
                phone.setBorder(BorderFactory.createEmptyBorder());
                phone.setEditable(false);
                phone.setFocusable(false);

                String[] array1 = {"Manager","Cashier","Operator"};
                JComboBox<String> role = new JComboBox<>(array1);
                role.setEnabled(false);
                role.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                role.setBounds(711, 11, 75, 22);
                role.setBorder(BorderFactory.createEmptyBorder());
                role.setSelectedItem(data[7]);
                role.setFocusable(false);
                role.setForeground(new Color(93, 93, 93));


                String[] array = {"Active","Inactive"};
                JComboBox<String> status = new JComboBox<>(array);
                status.setEnabled(false);
                status.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                status.setBounds(802, 11, 65, 22);
                status.setBorder(BorderFactory.createEmptyBorder());
                status.setSelectedItem(data[8]);
                status.setFocusable(false);
                status.setForeground(new Color(93, 93, 93));

                edit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String currentText = edit.getText();
                        if (currentText.contains("Edit")) {
                            edit.setText("<html><u>Update</u></html>");
                            branchID.setEnabled(true);
                            name.setFocusable(true);
                            name.setEditable(true);
                            email.setFocusable(true);
                            email.setEditable(true);
                            pass.setFocusable(true);
                            pass.setEditable(true);
                            salary.setFocusable(true);
                            salary.setEditable(true);
                            phone.setFocusable(true);
                            phone.setEditable(true);
                            status.setFocusable(true);
                            status.setEnabled(true);
                            role.setFocusable(true);
                            role.setEnabled(true);
                        }
                        else
                        {
                            if(name.getText().isEmpty() || email.getText().isEmpty() || pass.getText().isEmpty() || salary.getText().isEmpty() || !UIHandler.isNumbers(salary.getText()) || phone.getText().isEmpty() || phone.getText().length()<11 || !UIHandler.isNumbers(phone.getText())){
                                JOptionPane.showMessageDialog(f.getFrame(),"Invalid Edit","Error",JOptionPane.ERROR_MESSAGE);
                            }
                            else{
                                String improved = branchID.getSelectedItem().toString() + "," + name.getText() + "," + email.getText() + "," + pass.getText() + "," + salary.getText() + "," + phone.getText() + "," + role.getSelectedItem().toString() + "," + status.getSelectedItem().toString();
                                list = UIHandler.updateAdminsEmployeeInfo(Integer.parseInt(empID.getText()),improved);
                            }
                            branchID.setEnabled(false);
                            name.setFocusable(false);
                            name.setEditable(false);
                            email.setFocusable(false);
                            email.setEditable(false);
                            pass.setFocusable(false);
                            pass.setEditable(false);
                            salary.setFocusable(false);
                            salary.setEditable(false);
                            phone.setFocusable(false);
                            phone.setEditable(false);
                            status.setFocusable(false);
                            status.setEnabled(false);
                            role.setFocusable(false);
                            role.setEnabled(false);
                            edit.setText("<html><u>Edit</u></html>");
                            try {
                                refreshPanel(list,f);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
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

                if (data[8].equals("Active")) {
                    label.setBackground(super.getInfoFieldColor());
                    empID.setBackground(super.getInfoFieldColor());
                    name.setBackground(super.getInfoFieldColor());
                    email.setBackground(super.getInfoFieldColor());
                    pass.setBackground(super.getInfoFieldColor());
                    salary.setBackground(super.getInfoFieldColor());
                    phone.setBackground(super.getInfoFieldColor());
                }
                else
                {
                    label.setBackground(new Color(217, 217, 217));
                    empID.setBackground(new Color(217, 217, 217));
                    name.setBackground(new Color(217, 217, 217));
                    email.setBackground(new Color(217, 217, 217));
                    pass.setBackground(new Color(217, 217, 217));
                    salary.setBackground(new Color(217, 217, 217));
                    phone.setBackground(new Color(217, 217, 217));
                }

                label.add(branchID);
                label.add(empID);
                label.add(name);
                label.add(email);
                label.add(pass);
                label.add(salary);
                label.add(phone);
                label.add(status);
                label.add(role);
                panel.add(label);
            }
        }

        scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(1000, 438));
        scroll.setBounds(315, 293, 1000, 438);
        scroll.setBorder(null);
        add(scroll);
    }

    public void refreshPanel(ArrayList<String> newList, frame f) throws SQLException {
        if (scroll != null) {
            remove(scroll);
            employeesCount.setText("");
            typeName.setText("  Type Name");
            enterSalary.setText("  Enter Salary");
            typePhoneNo.setText("  Type Phone No.");
            searchText.setText("Search");
            super.removeInfoField();
        }
        setValues(newList,f);
        super.setInfoField();
        revalidate();
        repaint();
    }

    public void resetFields(){
        employeesCount.setText("");
        user.setText("");
        branchNumber.setText("");
        typeName.setText("  Type Name");
        enterSalary.setText("  Enter Salary");
        typePhoneNo.setText("  Type Phone No.");
        searchText.setText("Search");
        revalidate();
        repaint();
    }

    public String getVendorName(){return typeName.getText();}
    public String getVendorCity(){return enterSalary.getText();}
    public String getVendorAddress(){return typePhoneNo.getText();}
    public String getSearched(){return searchText.getText();}

    public JButton getLogoutButton(){return logoutButton;}
    public JButton getAddButton(){return addButton;}
    public JButton getSearchButton(){return searchButton;}
    public JButton getVendorInfoButton(){return vendorInfoButton;}
    public JButton getBranchInfoButton(){return branchInfoButton;}

    public ArrayList<String> getList(){return list;}

    public JPanel getPanel(){return this;}

    public int getUserNmae(){
        return Integer.parseInt(branchNumber.getText());
    }
}