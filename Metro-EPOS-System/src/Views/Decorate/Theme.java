package Views.Decorate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Theme extends JPanel {
    private Color sideMenuBackgroundColor;
    private Color sideMenuSelectedButtonColor;
    private Color sideMenuTextColor;
    private Color sideMenuSelectedTextColor;
    private Color infoFieldColor;
    private Color infoFieldButtonColor;

    private Color firstHeadingColor;
    private Color secondHeadingColor;
    private Color thirdHeadingColor;

    private Color lineColor1;
    private Color lineColor2;

    private Image logo;
    private Image profile;

    private JLabel infoField;

    private String logoutIconPath = "Images/LogoutLogo.png";

    public Theme()
    {
        setLayout(null);
        sideMenuBackgroundColor = new Color(85,136,59);
        sideMenuSelectedButtonColor = new Color(255,255,255);
        sideMenuTextColor = new Color(255,255,255);
        sideMenuSelectedTextColor = new Color(85,136,59);
        infoFieldColor = new Color(255,255,255);
        infoFieldButtonColor = new Color(155,103,56);
        lineColor1 = new Color(85,136,59);
        lineColor2 = new Color(171,171,171);
        firstHeadingColor = Color.BLACK;
        secondHeadingColor = new Color(93,93,93);
        thirdHeadingColor = new Color(128,156,19);
        setBackground(sideMenuBackgroundColor);

        infoField = new JLabel();
        infoField.setBounds(279,0,1097,730);
        RoundEdges.roundEdges(infoField, 40, infoFieldColor);
    }

    public void setLineSize5(int x, int y){
        JLabel line = new JLabel();
        line.setBackground(lineColor1);
        line.setBounds(x,y,1000,5);
        line.setOpaque(true);
        add(line);
    }
    public void setLineSizeCustom(int x, int y, int size){
        JLabel line = new JLabel();
        line.setBackground(lineColor2);
        line.setBounds(x,y,1000,size);
        line.setOpaque(true);
        add(line);
    }
    public void setText(String t){
        JLabel metro = new JLabel("METRO");
        metro.setForeground(sideMenuTextColor);
        metro.setBounds(99,27,75,23);
        metro.setFont(new Font("Inter",Font.BOLD,20));

        JLabel type = new JLabel(t);
        type.setForeground(sideMenuTextColor);
        type.setBounds(83,208,107,16);
        type.setFont(new Font("Inter",Font.PLAIN,10));
        type.setHorizontalAlignment(JLabel.CENTER);
        type.setVerticalAlignment(JLabel.TOP);

        add(metro);
        add(type);
    }
    public void setRectangle(int x, int y){
        JLabel rectangle = new JLabel();
        rectangle.setBounds(x,y,185,32);
        RoundEdges.roundEdges(rectangle, 50, sideMenuSelectedButtonColor);
        add(rectangle);
    }

    public void setProfileLogo(String path) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
            if (is != null) {
                profile = ImageIO.read(is);
            } else {
                System.err.println("Resource not found: " + path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLogoutLogo() {
        JLabel logout = new JLabel("Logout");
        logout.setForeground(sideMenuTextColor);
        logout.setFont(new Font("Inter", Font.PLAIN, 15));
        logout.setBounds(70, 684, 58, 19);
        add(logout);

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(logoutIconPath)) {
            if (is != null) {
                logo = ImageIO.read(is);
            } else {
                System.err.println("Resource not found: " + logoutIconPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (logo != null) {
            g.drawImage(logo, 44, 685, 18, 18, this);
        }
        if (profile != null) {
            g.drawImage(profile, 97, 88, 80, 80, this);
        }
    }

    public void setInfoField(){
        add(infoField);
    }
    public void removeInfoField(){
        remove(infoField);
    }

    public Color getFirstHeadingColor(){
        return firstHeadingColor;
    }
    public Color getSecondHeadingColor(){
        return secondHeadingColor;
    }
    public Color getThirdHeadingColor(){
        return thirdHeadingColor;
    }
    public Color getSideMenuSelectedButtonColor(){
        return sideMenuSelectedButtonColor;
    }
    public Color getSideMenuTextColor(){
        return sideMenuTextColor;
    }
    public Color getSideMenuSelectedTextColor(){
        return sideMenuSelectedTextColor;
    }
    public Color getInfoFieldColor(){
        return infoFieldColor;
    }
    public Color getInfoFieldButtonColor(){
        return infoFieldButtonColor;
    }
    public JPanel getPanel()
    {
        return this;
    }

    public Color getLineColor2(){return lineColor2;}
}