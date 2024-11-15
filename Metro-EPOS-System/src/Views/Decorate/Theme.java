package Views.Decorate;

import javax.swing.*;
import java.awt.*;

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
    
    public void setProfileLogo(String path)
    {
        profile = new ImageIcon(path).getImage();
    }

    public void setLogoutLogo()
    {
        JLabel logout = new JLabel("Logout");
        logout.setForeground(sideMenuTextColor);
        logout.setFont(new Font("Inter",Font.PLAIN,15));
        logout.setBounds(70,684,58,19);
        add(logout);
        logo = new ImageIcon("Images/LogoutLogo.png").getImage();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(logo, 44, 685,18,18, this);
        g.drawImage(profile, 97, 88,80,80, this);
    }
}
