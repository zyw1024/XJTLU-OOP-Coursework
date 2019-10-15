/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework1weather;

/**
 *
 * @author yiwen.zhang1902
 */
public class Coursework1Weather {
    static String weatherReport = "{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"weather\":[{\"id\":520,\"main\":\"Rain\",\"description\":\"light intensity shower rain\",\"icon\":\"09d\"}],\"base\":\"stations\",\"main\":{\"temp\":293.04,\"pressure\":1003,\"humidity\":77,\"temp_min\":289.26,\"temp_max\":296.48},\"visibility\":10000,\"wind\":{\"speed\":3.1},\"clouds\":{\"all\":74},\"dt\":1569148567,\"sys\":{\"type\":1,\"id\":1414,\"message\":0.0198,\"country\":\"GB\",\"sunrise\":1569131156,\"sunset\":1569175255},\"timezone\":3600,\"id\":2643743,\"name\":\"London\",\"cod\":200}";
    static int MAXSIZE = 10;

    public static void main(String[] args) {
        // Store the weather report to a local string 
        String wrStr = weatherReport.replace("[", "");
        
        //store the keywords user a wants to search
        String blockKeyArr[] = {"coord", "weather", "main", "wind", "sys"};
        String itemKeyArr[] = {"temp", "pressure", "name"};
        String weatherBlockArr[] = null;
        String weatherItemArr[] = null;

        // Searching...
        weatherBlockArr = searchWeatherInfoBlock(wrStr, blockKeyArr);
        weatherItemArr = searchWeatherInfoItem(wrStr, itemKeyArr);

        System.out.println("=================================Good morning, here is today's weather infomation:=================================");
        printStrArr(weatherBlockArr);

        System.out.println("=======================================You may just want to see a brief one:======================================");
        printStrArr(weatherItemArr);

    }

    // The basic method for searching information according to key
    public static String searchInfo(String origStr, String key, String endStr) {
        int startIndx = origStr.indexOf(key) - 1;
        int endIndx = origStr.indexOf(endStr, startIndx) + 1;
        String info = origStr.substring(startIndx, endIndx);
        return info;
    }

    // Search the block information 
    public static String searchInfoBlock(String origStr, String key) {
        String infoBlock = searchInfo(origStr, key + "\":{", "}");
        return infoBlock;
    }

    // Search the small item 
    public static String searchInfoItem(String origStr, String key) {
        String infoItem = searchInfo(origStr, key, ",");
        return infoItem;
    }

    // Search the weather block information a user wants to know
    public static String[] searchWeatherInfoBlock(String origStr, String keyArr[]) {
        String[] weatherInfoBlocks = new String[MAXSIZE];

        for (int i = 0; i < keyArr.length; ++i) {
            weatherInfoBlocks[i] = searchInfoBlock(origStr, keyArr[i]);
        }

        return weatherInfoBlocks;
    }

    // Search the small item of weather information a user wants to know
    public static String[] searchWeatherInfoItem(String origStr, String keyArr[]) {
        String[] weatherInfoItems = new String[MAXSIZE];

        for (int i = 0; i < keyArr.length; ++i) {
            weatherInfoItems[i] = searchInfoItem(origStr, keyArr[i]);
        }

        return weatherInfoItems;
    }

    // Print each  not-null member of a string array
    public static void printStrArr(String[] strArr) {
        for (String s : strArr) {
            if (s != null) {
//                System.out.println(s);
                formatOutput(s);
                System.out.println("");
            } else {
                break;
            }

        }
    }

    // Get rid of the punctuation
    public static String simpStr(String userStr) {
        String simpStr = null;
        simpStr = userStr.replace("\"", "");
        simpStr = simpStr.replace("[", "");
        simpStr = simpStr.replace("]", "");

        return simpStr;
    }

    // Generate indent
    public static void braceCheckIndent(String userStr) {
        int BrcCnt = 0;

        for (char chr : userStr.toCharArray()) {
            if (chr == '{') {
                chr = ' ';
                BrcCnt++;
                System.out.print("\n");
                for (int i = 0; i < BrcCnt; i++) {
                    System.out.print("   ");
                }
            } else if (chr == '}') {
                chr = ' ';
                BrcCnt--;
                System.out.print("\n");

                for (int i = 0; i < BrcCnt; i++) {
                    System.out.print("   ");
                }
            }

            if (chr == ',') {
                chr = ' ';
                System.out.print("\n");
                for (int i = 0; i < BrcCnt; i++) {
                    System.out.print("   ");
                }
            }
            System.out.print(chr);

        }

    }

    // Print the information with a format
    public static void formatOutput(String userStr) {
        String str = simpStr(userStr);
        braceCheckIndent(str);
    }
}
