package Views.Manager;

import Views.Decorate.Theme;
import Views.Frame.frame;
import Views.Operator.ExpandedInfo;
import Views.UIHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeInfo extends Theme {
    private BufferedImage image;
    private JLabel user;
    private JLabel branchID;

    private JScrollPane scroll;

    JTextField typeName;
    JTextField enterSalary;
    JTextField typePhoneNumber;
    JComboBox<String> role;
    JTextField searchText;

    private JButton logoutButton;
    private JButton addButton;
    private JButton searchButton;
    private JButton branchInfoButton;

    private Image empLogo;
    private ArrayList<String> list;
    private JLabel branchNumber;
    private JLabel employeesCount;

    private String managerProfileImgPath = "Metro-EPOS-System/Images/ManagerLogo.png";
    private String employeeInfoPath = "Metro-EPOS-System/Images/EmpInfoLogo.png";
    private String branchInfoPath = "Metro-EPOS-System/Images/branchWhite.png";
    private String searchIconPath = "Metro-EPOS-System/Images/searchlogo.png";

    public EmployeeInfo()
    {
        super.setLineSize5(315,120);
        super.setLineSize5(315,168);
        super.setLineSizeCustom(315,248,2);
        super.setLineSizeCustom(315,288,2);
        super.setText("Branch Manager");
        super.setLogoutLogo();
        super.setProfileLogo(managerProfileImgPath);

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

    public void setNameBranch(String name, String brID){
        setNames(name,brID);
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

        branchNumber = new JLabel();
        branchNumber.setText(ID);
        branchID = new JLabel();
        branchID.setText("Branch ID: " + ID);
        branchID.setFont(new Font("Inter",Font.BOLD,25));
        branchID.setForeground(Color.BLACK);
        branchID.setBounds(345,55,221,30);

        add(user);
        add(branchID);
    }

    private void setLogo() {
        empLogo = new ImageIcon(employeeInfoPath).getImage();
        Image scaledImage = empLogo.getScaledInstance(16, 20, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoLabel.setBounds(79, 325, 16, 20);
        add(logoLabel);
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

        typePhoneNumber = new JTextField("  Type Phone No.");
        typePhoneNumber.setBounds(698,133,178,27);
        typePhoneNumber.setForeground(new Color(173,173,173));
        typePhoneNumber.setBackground(super.getInfoFieldColor());
        typePhoneNumber.setBorder(new LineBorder(new Color(173,173,173),2));
        typePhoneNumber.setFont(new Font("Arial",Font.BOLD,15));
        typePhoneNumber.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (typePhoneNumber.getText().equals("  Type Phone No.")) {
                    typePhoneNumber.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (typePhoneNumber.getText().isEmpty()) {
                    typePhoneNumber.setText("  Type Phone No.");
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

        String[] array1 = {"Cashier","Operator"};

        role = new JComboBox<>(array1);
        role.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
        role.setBounds(888, 133, 127, 27);
        role.setBorder(BorderFactory.createEmptyBorder());
        role.setForeground(new Color(93, 93, 93));

        add(typeName);
        add(typePhoneNumber);
        add(enterSalary);
        add(role);
    }

    private void setSearchBar(){
        try {
            BufferedImage logo = ImageIO.read(new File(searchIconPath));
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

        branchInfoButton = new JButton();
        branchInfoButton.setBounds(78, 280, 117, 15);
        branchInfoButton.setBorderPainted(false);
        branchInfoButton.setContentAreaFilled(false);
        branchInfoButton.setOpaque(false);

        addButton = new JButton("+ ADD");
        addButton.setHorizontalAlignment(JButton.LEFT);
        addButton.setForeground(super.getInfoFieldColor());
        addButton.setFocusable(false);
        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setBounds(1244, 135, 70, 23);
        addButton.setOpaque(true);
        addButton.setBackground(super.getInfoFieldButtonColor());

        add(logoutButton);
        add(addButton);
        add(branchInfoButton);
    }

    private void setHeading(){
        JLabel totalEmployees = new JLabel("Total Employees:");
        JLabel empID = new JLabel("EMP ID");
        JLabel name = new JLabel("Name");
        JLabel email = new JLabel("Email");
        JLabel password = new JLabel("Password");
        JLabel salary = new JLabel("Salary");
        JLabel phoneNo = new JLabel("Phone No.");
        JLabel role = new JLabel("Role");
        JLabel status = new JLabel("Status");
        JLabel employeesInfo = new JLabel("Employees Info");
        JLabel branchInfo = new JLabel("Branch Info");
        employeesCount = new JLabel("");

        totalEmployees.setForeground(super.getFirstHeadingColor());
        empID.setForeground(super.getThirdHeadingColor());
        name.setForeground(super.getThirdHeadingColor());
        email.setForeground(super.getThirdHeadingColor());
        password.setForeground(super.getThirdHeadingColor());
        salary.setForeground(super.getThirdHeadingColor());
        phoneNo.setForeground(super.getThirdHeadingColor());
        role.setForeground(super.getThirdHeadingColor());
        status.setForeground(super.getThirdHeadingColor());
        employeesInfo.setForeground(super.getSideMenuSelectedTextColor());
        branchInfo.setForeground(super.getSideMenuTextColor());
        employeesCount.setForeground(new Color(9,95,197));

        totalEmployees.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        empID.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        email.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        name.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        password.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        salary.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        phoneNo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        role.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        status.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,15));
        employeesInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        branchInfo.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,10));
        employeesCount.setFont(new Font("Yu Gothic UI SemiBold",Font.BOLD,18));

        totalEmployees.setBounds(316,216,127,18);
        empID.setBounds(319,257,54,24);
        name.setBounds(410,257,48,24);
        email.setBounds(516,257,63,24);
        employeesInfo.setBounds(110,329,86,10);
        branchInfo.setBounds(110,283,86,10);
        password.setBounds(626,257,77,24);
        salary.setBounds(744,257,54,24);
        phoneNo.setBounds(838,257,77,24);
        role.setBounds(944,257,77,24);
        status.setBounds(1032,257,77,24);
        employeesCount.setBounds(447,216,83,21);

        add(totalEmployees);
        add(empID);
        add(email);
        add(name);
        add(password);
        add(salary);
        add(phoneNo);
        add(role);
        add(status);
        add(employeesInfo);
        add(branchInfo);
        add(employeesCount);
    }

    public void setValues(ArrayList<String> l, frame f)
    {
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
                edit.setBounds(946, 13, 46, 14);
                edit.setVerticalAlignment(JLabel.CENTER);
                edit.setHorizontalAlignment(JLabel.CENTER);
                label.add(edit);

                JTextField empID = new JTextField();
                empID.setText(data[0]);
                empID.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                empID.setForeground(new Color(93, 93, 93));
                empID.setBounds(8, 13, 46, 14);
                empID.setHorizontalAlignment(JLabel.CENTER);
                empID.setBorder(BorderFactory.createEmptyBorder());
                empID.setEditable(false);
                empID.setFocusable(false);

                JTextField name = new JTextField();
                name.setText(data[1]);
                name.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                name.setForeground(new Color(93, 93, 93));
                name.setBounds(64, 13, 110, 14);
                name.setHorizontalAlignment(JLabel.CENTER);
                name.setBorder(BorderFactory.createEmptyBorder());
                name.setEditable(false);
                name.setFocusable(false);

                JTextField email = new JTextField();
                email.setText(data[2]);
                email.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                email.setForeground(new Color(93, 93, 93));
                email.setBounds(174, 13, 118, 14);
                email.setHorizontalAlignment(JLabel.CENTER);
                email.setBorder(BorderFactory.createEmptyBorder());
                email.setEditable(false);
                email.setFocusable(false);

                JTextField password = new JTextField();
                password.setText(data[3]);
                password.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                password.setForeground(new Color(93, 93, 93));
                password.setBounds(297, 13, 106, 14);
                password.setHorizontalAlignment(JLabel.CENTER);
                password.setBorder(BorderFactory.createEmptyBorder());
                password.setEditable(false);
                password.setFocusable(false);

                JTextField salary = new JTextField();
                salary.setText(data[4]);
                salary.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                salary.setForeground(new Color(93, 93, 93));
                salary.setBounds(403, 13, 106, 14);
                salary.setHorizontalAlignment(JLabel.CENTER);
                salary.setBorder(BorderFactory.createEmptyBorder());
                salary.setEditable(false);
                salary.setFocusable(false);

                JTextField phoneNo = new JTextField();
                phoneNo.setText(data[5]);
                phoneNo.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                phoneNo.setForeground(new Color(93, 93, 93));
                phoneNo.setBounds(509, 13, 106, 14);
                phoneNo.setHorizontalAlignment(JLabel.CENTER);
                phoneNo.setBorder(BorderFactory.createEmptyBorder());
                phoneNo.setEditable(false);
                phoneNo.setFocusable(false);

                String[] array1 = {"Cashier","Operator"};

                JComboBox<String> role = new JComboBox<>(array1);
                role.setEnabled(false);
                role.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                role.setBounds(615, 13, 106, 16);
                role.setBorder(BorderFactory.createEmptyBorder());
                role.setSelectedItem(data[6]);
                role.setFocusable(false);
                role.setForeground(new Color(93, 93, 93));


                String[] array2 = {"Active","Inactive"};

                JComboBox<String> status = new JComboBox<>(array2);
                status.setEnabled(false);
                status.setFont(new Font("Yu Gothic UI SemiBold", Font.BOLD, 12));
                status.setBounds(721, 13, 70, 16);
                status.setBorder(BorderFactory.createEmptyBorder());
                status.setSelectedItem(data[7]);
                status.setFocusable(false);
                status.setForeground(new Color(93, 93, 93));

                edit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String currentText = edit.getText();
                        if (currentText.contains("Edit")) {
                            edit.setText("<html><u>Update</u></html>");
                            name.setFocusable(true);
                            name.setEditable(true);
                            email.setFocusable(true);
                            email.setEditable(true);
                            password.setFocusable(true);
                            password.setEditable(true);
                            salary.setFocusable(true);
                            salary.setEditable(true);
                            phoneNo.setFocusable(true);
                            phoneNo.setEditable(true);
                            role.setFocusable(true);
                            role.setEnabled(true);
                            status.setFocusable(true);
                            status.setEnabled(true);
                        }
                        else
                        {
                            if(!UIHandler.isNumbers(salary.getText()) || phoneNo.getText().length()!=11 || !UIHandler.isNumbers(phoneNo.getText()) || !email.getText().contains("@gmail.com")){
                                JOptionPane.showMessageDialog(f.getFrame(),"Invalid Update","Error",JOptionPane.ERROR_MESSAGE);
                            }
                            else{
                                String improved = name.getText()+","+email.getText()+","+password.getText()+","+ salary.getText() + ","+ phoneNo.getText()+","+ role.getSelectedItem() +","+status.getSelectedItem();
                                list = UIHandler.updateEmployeeInfo(Integer.parseInt(empID.getText()), Integer.parseInt(branchNumber.getText()),improved);
                            }
                            name.setFocusable(false);
                            name.setEditable(false);
                            email.setFocusable(false);
                            email.setEditable(false);
                            password.setFocusable(false);
                            password.setEditable(false);
                            salary.setFocusable(false);
                            salary.setEditable(false);
                            phoneNo.setFocusable(false);
                            phoneNo.setEditable(false);
                            role.setFocusable(false);
                            role.setEnabled(false);
                            status.setFocusable(false);
                            status.setEnabled(false);
                            edit.setText("<html><u>Edit</u></html>");
                            refreshPanel(list,f);
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

                if (data[7].equals("Active")) {
                    label.setBackground(super.getInfoFieldColor());
                    empID.setBackground(super.getInfoFieldColor());
                    name.setBackground(super.getInfoFieldColor());
                    email.setBackground(super.getInfoFieldColor());
                    password.setBackground(super.getInfoFieldColor());
                    phoneNo.setBackground(super.getInfoFieldColor());
                    salary.setBackground(super.getInfoFieldColor());
                }
                else
                {
                    label.setBackground(new Color(217, 217, 217));
                    empID.setBackground(new Color(217, 217, 217));
                    name.setBackground(new Color(217, 217, 217));
                    email.setBackground(new Color(217, 217, 217));
                    password.setBackground(new Color(217, 217, 217));
                    phoneNo.setBackground(new Color(217, 217, 217));
                    salary.setBackground(new Color(217, 217, 217));
                }

                label.add(empID);
                label.add(name);
                label.add(email);
                label.add(password);
                label.add(salary);
                label.add(phoneNo);
                label.add(role);
                label.add(status);
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

    public void refreshPanel(ArrayList<String> newList, frame f) {
        if (scroll != null) {
            remove(scroll);
            employeesCount.setText("");
            typeName.setText("  Type Name");
            enterSalary.setText("  Enter Salary");
            typePhoneNumber.setText("  Type Phone No.");
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
        typePhoneNumber.setText("  Type Phone No.");
        searchText.setText("Search");
        revalidate();
        repaint();
    }

    public String getEmpName(){return typeName.getText();}
    public String getEmpNumber(){return typePhoneNumber.getText();}
    public String getEmpSalary(){return enterSalary.getText();}
    public String getEmployeeRole(){return role.getSelectedItem().toString();}
    public String getSearched(){return searchText.getText();}

    public JButton getLogoutButton(){return logoutButton;}
    public JButton getAddButton(){return addButton;}
    public JButton getSearchButton(){return searchButton;}
    public JButton getBranchInfoButton(){return branchInfoButton;}

    public ArrayList<String> getList(){return list;}

    public JPanel getPanel(){return this;}

    public int getBranchID(){
        return Integer.parseInt(branchNumber.getText());
    }
}