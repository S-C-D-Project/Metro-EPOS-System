package Views.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import java.io.IOException;
import java.io.InputStream;

public class frame {
    private JFrame frame;
    private String frameIconPath = "Images/FrameIcon.png";

    public frame() {
        frame = new JFrame("Metro POS System");
        frame.setSize(1366, 768);

        BufferedImage icon = null;
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(frameIconPath)) {
            if (is != null) {
                icon = ImageIO.read(is);
            } else {
                System.err.println("Resource not found: " + frameIconPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (icon != null) {
            frame.setIconImage(icon);
        }

        JPanel contentPane = (JPanel) frame.getContentPane();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        contentPane.setBorder(border);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.BLACK);
    }

    public void addPanel(JPanel p) {
        frame.add(p);
        frame.revalidate();
        frame.repaint();
    }

    public void replacePanel(JPanel old, JPanel latest) {
        frame.remove(old);
        frame.add(latest);
        frame.revalidate();
        frame.repaint();
    }

    public void show() {
        frame.setVisible(true);
    }

    public void destroy() {
        frame.dispose();
    }

    public JFrame getFrame() {
        return frame;
    }
}
