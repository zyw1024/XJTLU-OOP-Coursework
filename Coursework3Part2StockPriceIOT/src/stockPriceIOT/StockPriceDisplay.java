/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockPriceIOT;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JPanel;

/**
 *
 * @author Yiwen Zhang
 */
public class StockPriceDisplay extends JPanel implements DisplayHost {

    private LocalDate date;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd E", Locale.ENGLISH);

    private List<Displayable> myDisplayables = new ArrayList<>();
    Font font = new Font(Settings.FONT_NAME, Settings.FONT_STYLE, Settings.FONT_SIZE);

    public StockPriceDisplay(LocalDate date) {
        super();
        this.date = date;
//        this.setBackground(Color.white);
        this.setBackground(Settings.BACKGROUND_COLOR);
    }

    public List<Displayable> getListOfDisplayables() {
        return myDisplayables;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(font);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Settings.FONT_COLOR);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        FontMetrics metrics = g2.getFontMetrics();
        int dateWidth = metrics.stringWidth(this.date.format(this.dateFormatter));
        g2.drawString(this.date.format(this.dateFormatter), this.getWidth() / 2 - dateWidth / 2, this.getHeight() / 2);

        // To draw every displayable in the list
        int x = 0, y = 0;
        for (Displayable disp : myDisplayables) {
            BufferedImage image = disp.getDisplayable();
            g2.drawImage(image, x, y, null);
            y = image.getHeight();
        }
    }
}
