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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Yiwen Zhang
 */
public class StockPriceDisplay extends JPanel implements DisplayHost {

    private String period;
    private String companyName;
    private double changeRate;

    private List<Displayable> myDisplayables = new ArrayList<>();
    Font font = new Font(Settings.FONT_NAME, Settings.FONT_STYLE, Settings.FONT_SIZE);
    private int smallWindowX;
    private int smallWindowY;
    private int smallWindowW;
    private int smallWindowH;
    private int columnX;
    private int columnY;
    private int columnW;
    private int columnH;
    private BufferedImage columnChartImg;

    public StockPriceDisplay(String companyName, String period, int x, int y, int w, int h) {
        super();

        this.companyName = companyName;
        this.period = period;
        this.changeRate = ParseStockPrice.getPriceMovementOfPeriod(period, companyName, Settings.STOCK_PRICE_REPORT_PATH);
        
        this.smallWindowX = x;
        this.smallWindowY = y;
        this.smallWindowW = w;
        this.smallWindowH = h;
        
        this.columnH = (int) (((this.changeRate) / 0.1 ) * this.smallWindowH);
        this.columnW = this.smallWindowW / 4;
        this.columnX = this.smallWindowX + this.smallWindowW/2 - this.columnW/2;
        this.columnY = this.smallWindowY + (this.smallWindowH - this.columnH);
        
        this.setBackground(Color.white);
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
        int dateWidth = metrics.stringWidth(LocalDate.now().toString());
        g2.drawString(LocalDate.now().toString(), this.getWidth() / 2 - dateWidth / 2, this.getHeight() / 2);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        this.columnChartImg = StockPrice.drawColunChart(this.smallWindowW, this.smallWindowH, this.columnW, this.columnH, this.changeRate);
        System.out.println(this.columnH);
        g.drawImage(this.columnChartImg, this.smallWindowX, this.smallWindowY, this);
    }
}
