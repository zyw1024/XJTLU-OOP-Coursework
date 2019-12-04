/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockPriceIOT;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Yiwen Zhang
 */
public class Icon {

    public static BufferedImage downloadIcon(String id) {
        String iconUrl
                = "http://openweathermap.org/img/wn/" + id + "@2x.png";
        BufferedImage iconImg = null;
        try {
            iconImg = ImageIO.read(new URL(iconUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iconImg;
    }
}
