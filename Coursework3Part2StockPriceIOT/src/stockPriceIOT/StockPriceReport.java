/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockPriceIOT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yiwen Zhang
 */
public class StockPriceReport {

    public ArrayList<String> stockPriceList;

    public StockPriceReport(String txtPath) {
        stockPriceList = new ArrayList<>();
        File reportFile = new File(txtPath);
        if (reportFile.isFile() && reportFile.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(reportFile);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuffer sb = new StringBuffer();
                String text = null;
                while ((text = bufferedReader.readLine()) != null) {
//                    System.out.println(text);
                    this.stockPriceList.add(text);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
