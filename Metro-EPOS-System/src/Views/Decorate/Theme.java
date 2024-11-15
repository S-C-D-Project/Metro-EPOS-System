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
}
