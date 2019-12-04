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

    static ClockDisplay clockFace;
    static StockPriceDisplay stockPriceDisplay;

    public static void main(String[] args) {
//        // Set the clock
//        Clock clock = Clock.system(ZoneId.of("UTC+8"));
//        Clock tickClock = Clock.tick(clock, Duration.ofSeconds(1));
//        clockFace = new ClockDisplay(0, 0, 400, 600, tickClock);
//        clockFace = new ClockDisplay(1000, 1000, 1000, 100, tickClock);
//
//        // Create a timer
//        // Start ticking
//        Timer timer = new Timer(1000, clockFace);
//        timer.start();
//
//        // Set the weather
//        Weather suzhouWeather = new Weather("Suzhou");
//
//        List<Displayable> clockDisplayables = clockFace.getListOfDisplayables();
//        clockDisplayables.add(suzhouWeather);
//

//        stockPriceDisplay = new StockPriceDisplay("AAPL","YESTERDAY", 0, 0, 96, 96);
        stockPriceDisplay = new StockPriceDisplay("AAPL","LAST_WEEK", 0, 0, 96, 96);
        
        // Set the frame
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(Settings.FRAM_WIDTH, Settings.FRAM_HEIGHT);
        f.setVisible(true);
        f.add(stockPriceDisplay);
//        f.add(clockFace);
        

        

    }
}
