package Views.Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class GifPlayer {
    private String frameIconPath = "Images/FrameIcon.png";

    public GifPlayer() {
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setSize(1366, 768);
        frame.setLocationRelativeTo(null);

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

        JPanel panel = new JPanel();
        panel.setBackground(new Color(171,195,47));
        panel.setLayout(null);

        JLabel loading = new JLabel("Loading...");
        loading.setHorizontalAlignment(SwingConstants.CENTER);
        loading.setForeground(new Color(255,255,0));
        loading.setFont(new Font("Arial", Font.BOLD, 20));
        loading.setBounds(628, 527, 114, 27);
        panel.add(loading);

        JProgressBar progressBar = new JProgressBar(0, 1520);
        progressBar.setBorderPainted(false);
        progressBar.setBounds(260, 490, 830, 24);
        progressBar.setBackground(new Color(90, 90, 5));
        progressBar.setForeground(new Color(255, 255, 0));
        progressBar.setStringPainted(true);
        panel.add(progressBar);

        JLabel text = new JLabel("METRO");
        text.setForeground(new Color(236,236,163));
        text.setFont(new Font("Arial Black", Font.BOLD, 230));
        text.setBounds(200, 209, 1000, 229);
        panel.add(text);

        frame.add(panel);
        frame.setVisible(true);

        for(int i=0;i<1520;i++){
            progressBar.setValue(i);
            try {
                int random = new Random().nextInt(20);
                Thread.sleep(random);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        frame.dispose();
    }
}
