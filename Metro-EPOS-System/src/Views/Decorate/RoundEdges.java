package Views.Decorate;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RoundEdges {
    public static void roundEdges(JComponent component, int radius, Color bg) {
        component.setBorder(new RoundedBorder(radius, bg));
        component.setOpaque(false);
    }

    private static class RoundedBorder implements Border {
        private final int radius;
        private final Color backgroundColor;

        public RoundedBorder(int radius, Color backgroundColor) {
            this.radius = radius;
            this.backgroundColor = backgroundColor;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(backgroundColor);
            g2d.fillRoundRect(x, y, width - 1, height - 1, radius, radius);

            g2d.setColor(Color.GRAY);
            g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius, radius, radius, radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }
}
