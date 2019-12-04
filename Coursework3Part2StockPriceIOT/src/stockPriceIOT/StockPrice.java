/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockPriceIOT;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.time.LocalDate;

/**
 *
 * @author Yiwen Zhang
 */
public class StockPrice {

    private final String ticker;
    private final LocalDate date;
    private final double open;
    private final double high;
    private final double low;
    private final double close;
    private final long volume;

    public StockPrice(String ticker, LocalDate date, double open, double high, double low, double close, long volume) {
        this.ticker = ticker;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public String getTicker() {
        return this.ticker;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public double getOpen() {
        return this.open;
    }

    public double getHigh() {
        return this.high;
    }

    public double getLow() {
        return this.low;
    }

    public double getClose() {
        return this.close;
    }

    public double getVolume() {
        return this.volume;
    }

    public static BufferedImage drawColunChart(int W, int H, int w, int h, double changeRate) {
//        BufferedImage img = new BufferedImage(96, 96, BufferedImage.TYPE_INT_RGB);
        BufferedImage img = new BufferedImage(96, 96, BufferedImage.BITMASK);
        Graphics2D g2 = img.createGraphics();

        g2.setColor(Color.black);
        g2.drawRect(0, 0, W - 1, H - 1);
        if (h < 0) {
            // Price goes down, show green column
            h = -1 * h;
            g2.setColor(Color.green);
            g2.drawRect(W / 2 - w / 2, H - h, w, h);
            g2.fillRect(W / 2 - w / 2, H - h, w, h);
        } else if (h > 0) {
            // Price goes up, show red column
            g2.setColor(Color.red);
            g2.drawRect(W / 2 - w / 2, H - h, w, h);
            g2.fillRect(W / 2 - w / 2, H - h, w, h);
        }
        
        FontMetrics fm = g2.getFontMetrics();
        String str = String.format("%.2f", changeRate / 0.01) + "%";
//                Double.toString(changeRate);
        int stringWidth = fm.stringWidth(str);
        int stringDescent = fm.getMaxDescent();
        g2.drawString(str, W / 2 - w / 2 - stringWidth / 3,
                H - h - stringDescent);

        return img;
    }
    


}
