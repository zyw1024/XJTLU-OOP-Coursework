/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherIOT;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;

/**
 *
 * @author Yiwen Zhang
 */
public class Weather implements Displayable {

    private static final String urlString = "https://api.openweathermap.org/data/2.5/weather?q=";
    private final String city;    
    private static final String id = "&appid=9a4b6e9ba21ca8926607319fabbc6bc1";
    private static final double K = 273.15; //kelvin to celsius
    public String report;
    public String iconID;
//    public BufferedImage iconImg;

    public Weather(String city) {
        this.city = city;
        this.report = getReport();
        this.iconID = ParseWheather.extractIconID(report);
//        this.iconImg = Icon.downloadIcon(iconID);
    }

    public String getReport() {
        String queryString = urlString + city + id;
        URLConnection conn = null;
        String report = null;

        try {
            URL url = new URL(queryString);
            conn = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream is = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader rd = new BufferedReader(isr);) {
            String line;
            while ((line = rd.readLine()) != null) {
                System.out.println("Reading: " + line);
                report = line;     //its JSON, so only one line
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return report;
    }
    
    public BufferedImage getDisplayable(){
        BufferedImage iconImg = Icon.downloadIcon(iconID);
        return iconImg;
    }
    
}
