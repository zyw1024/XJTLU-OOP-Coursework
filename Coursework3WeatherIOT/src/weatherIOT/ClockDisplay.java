package weatherIOT;

import java.awt.Color;
import java.time.Clock;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Yiwen Zhang
 */
public class ClockDisplay extends JPanel implements ActionListener, DisplayHost {

//    private final int x, y;
//    private final int width, height;
    Font font = new Font(Settings.FONT_NAME, Settings.FONT_STYLE, Settings.FONT_SIZE);
    private Clock clock;
//    private final BufferedImage iconImg;
    private List<Displayable> myDisplayables = new ArrayList<>();

    public ClockDisplay(int x, int y, int w, int h, Clock clock) {
        super();
        this.clock = clock;
        this.setLocation(x, y);
        this.setSize(w, h);
        this.setBackground(Settings.BACKGROUND_COLOR);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public List<Displayable> getListOfDisplayables() {
        return myDisplayables;
    }

    public void paint(Graphics g) {
        super.paint(g);

        // Set font
        g.setFont(font);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Settings.FONT_COLOR);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        
        // To draw every displayable in the list
        int x = 0, y = 0;
        for (Displayable disp : myDisplayables) {
            BufferedImage image = disp.getDisplayable();
            g2.drawImage(image, x, y, null);
            y = image.getHeight();
        }

        FontMetrics metrics = g2.getFontMetrics();
        int timeWidth = metrics.stringWidth("00:00:00");
        
        // Draw time String
        g2.drawString(LocalTime.now(clock).toString(), this.getWidth()/2 - timeWidth/2, this.getHeight()/2);
    }
}
