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
public class StockPriceGraph implements Displayable {

    private int chartWidth;
    private int chartHeight;
    private int columnWidth;
    private int columnHeight;
    private int columnX;
    private int columnY;
    private double changeRate;

    public StockPriceGraph(LocalDate date, String companyName, int chartWidth, int chartHeight) {
        this.changeRate = ParseStockPrice.getPriceMovementOfPeriod(date, companyName, Settings.STOCK_PRICE_REPORT_PATH);
        this.chartWidth = chartWidth;
        this.chartHeight = chartHeight;
        this.columnWidth = this.chartWidth / 4;
        this.columnHeight = (int)(((this.changeRate) / 0.1) * this.chartHeight);
        this.columnX = this.chartWidth / 2 - this.columnWidth / 2;
        this.columnY = this.chartHeight - this.columnHeight;
    }

    public static BufferedImage drawColumnChart(int W, int H, int w, int h, double changeRate) {
//        BufferedImage img = new BufferedImage(96, 96, BufferedImage.TYPE_INT_RGB);
        BufferedImage img = new BufferedImage(W, H, BufferedImage.BITMASK);
        Graphics2D g2 = img.createGraphics();

        FontMetrics fm = g2.getFontMetrics();
        if (h > 9999) {
            System.out.println("Error! The date you are finding does not exit in the data set!");
            String text1 = "No data on ";
            String text2 = " that date!";
            h = 0;
            int stringWidth1 = fm.stringWidth(text1);
            int stringDescent = fm.getMaxDescent();

            int stringWidth2 = fm.stringWidth(text2);

            g2.setColor(Color.black);
            g2.drawRect(0, 0, W - 1, H - 1);
            g2.drawString(text1, W / 2 - stringWidth1 / 2,
                    H / 2 - stringDescent);
            g2.drawString(text2, W / 2 - stringWidth2 / 2,
                    H / 2 + stringDescent * 3);
            return img;
        } else {
            String str = String.format("%.2f", changeRate / 0.01) + "%";
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

            int stringWidth = fm.stringWidth(str);
            int stringDescent = fm.getMaxDescent();
            g2.drawString(str, W / 2 - w / 2 - stringWidth / 3,
                    H - h - stringDescent);
            return img;
        }

    }

    public BufferedImage getDisplayable() {
        BufferedImage columnChartImg = StockPriceGraph.drawColumnChart(this.chartWidth, this.chartHeight, this.columnWidth, this.columnHeight, this.changeRate);
        return columnChartImg;
    }
}
