package weatherIOT;

import java.awt.image.BufferedImage;
import java.time.Clock;
import java.time.Duration;
import java.time.ZoneId;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.util.List;

/**
 *
 * @author Yiwen Zhang
 */
public class Main {

    static ClockDisplay clockFace;

    public static void main(String[] args) {
        // Set the clock
        Clock clock = Clock.system(ZoneId.of("UTC+8"));
        Clock tickClock = Clock.tick(clock, Duration.ofSeconds(1));
//        clockFace = new ClockDisplay(0, 0, 400, 600, tickClock);
        clockFace = new ClockDisplay(1000, 1000, 1000, 100, tickClock);

        // Create a timer
        // Start ticking
        Timer timer = new Timer(1000, clockFace);
        timer.start();

        // Set the weather
        Weather suzhouWeather = new Weather("Suzhou");

        List<Displayable> clockDisplayables = clockFace.getListOfDisplayables();
        clockDisplayables.add(suzhouWeather);

        // Set the frame
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(Settings.FRAM_WIDTH, Settings.FRAM_WIDTH);
        f.setVisible(true);
        f.add(clockFace);
    }

}
