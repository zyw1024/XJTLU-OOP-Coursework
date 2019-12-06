/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockPriceIOT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Yiwen Zhang
 */
public class ParseStockPrice {

    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uu");
    public String dataPath;
    public StockPriceReport stockPriceReport;
    public ArrayList<StockPrice> allStockPriceList;
    public ArrayList<StockPrice> chosenStockPriceList;

    /**
     * Parse a report item and store the ticker, date, open, high, low, close,
     * volume into a StockPrice object.
     *
     * @param reportItem
     * @return stockPrice
     */
    public static StockPrice parseItem(String reportItem) {
        String items[] = reportItem.split(",");

        String ticker = items[0];
        LocalDate date = LocalDate.parse(items[1], dateFormatter);
        double open = Double.parseDouble(items[2]);
        double high = Double.parseDouble(items[3]);
        double low = Double.parseDouble(items[4]);
        double close = Double.parseDouble(items[5]);
        long volume = Long.parseLong(items[6]);

        StockPrice stockPrice = new StockPrice(ticker, date, open, high, low, close, volume);
        return stockPrice;
    }

    public static ArrayList<StockPrice> getChosenCompanyStockPriceList(String stockPriceReportPath, String companyName) {
        StockPriceReport stockPriceReport = new StockPriceReport(stockPriceReportPath);
        ArrayList<StockPrice> chosenCompanyStockPriceList = new ArrayList<>();
        Iterator iteratorOfAllStocks = stockPriceReport.stockPriceList.iterator();

        while (iteratorOfAllStocks.hasNext()) {
            String item = iteratorOfAllStocks.next().toString();
            if (item.contains(companyName)) {
//                System.out.println(item);
                chosenCompanyStockPriceList.add(ParseStockPrice.parseItem(item));
            }
        }

        return chosenCompanyStockPriceList;
    }

    /**
     * Get the change rate of a company's stock price of a date.
     */
    public static double getPriceMovementOfPeriod(LocalDate date, String companyName, String stockPriceReportPath) {
        ArrayList<StockPrice> chosenCompanyStockPriceList = getChosenCompanyStockPriceList(stockPriceReportPath, companyName);
        int listSize = chosenCompanyStockPriceList.size();
        double startPrice = 0;
        double endPrice = 0;
        double deltaPrice = 0;
        double changeRate = 0;
        boolean isFindDate = false;

        for (int i = 0; i < listSize; ++i) {
            if (chosenCompanyStockPriceList.get(i).getDate().isEqual(date)) {
                startPrice = chosenCompanyStockPriceList.get(i).getOpen();
                endPrice = chosenCompanyStockPriceList.get(i).getClose();
                isFindDate = true;
            }
        }

        if (isFindDate) {
            deltaPrice = endPrice - startPrice;
            changeRate = deltaPrice / startPrice;
            return changeRate;
        }
        else {
            // The date does not exist. return error code 9999999.
            return 9999999;
        }

    }
}
