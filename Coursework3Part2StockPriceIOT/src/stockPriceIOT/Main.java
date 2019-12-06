/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockPriceIOT;

import java.awt.image.BufferedImage;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Yiwen Zhang
 */
public class Main {
    static StockPriceDisplay stockPriceDisplay;

    public static void main(String[] args) {
        System.out.println("Hello from Yiwen...");
        
        // Set the date of today
        LocalDate today = LocalDate.parse("2009-12-24");
        
        StockPriceDisplay stockPriceDisplay = new StockPriceDisplay(today);
        StockPriceGraph stockPriceGraph = new StockPriceGraph(today, "AAPL", Settings.CHART_WIDTH, Settings.CHART_HEIGHT);
        
        List<Displayable> displayables = stockPriceDisplay.getListOfDisplayables();
        displayables.add(stockPriceGraph);
        
        // Set the frame
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(Settings.FRAM_WIDTH, Settings.FRAM_HEIGHT);
        f.setVisible(true);
        f.add(stockPriceDisplay);
    }
}
