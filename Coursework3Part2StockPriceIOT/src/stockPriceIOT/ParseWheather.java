/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockPriceIOT;

/**
 *
 * @author Yiwen Zhang
 */
public class ParseWheather {

    public static String searchInfo(String origStr, String key, String endStr) {
        int startIndx = origStr.indexOf(key) - 1;
        int endIndx = origStr.indexOf(endStr, startIndx) + 1;
        String info = origStr.substring(startIndx, endIndx);
        return info;
    }

    public static String searchInfoItem(String origStr, String key) {
        String infoItem = searchInfo(origStr, key, ",");
        infoItem = simpStr(infoItem);
        return infoItem;
    }

    public static String simpStr(String userStr) {
        String simpStr = null;
        simpStr = userStr.replace("\"", "");
        simpStr = simpStr.replace("[", "");
        simpStr = simpStr.replace("]", "");
        simpStr = simpStr.replace("}", "");
        simpStr = simpStr.replace("{", "");
        simpStr = simpStr.replace(",", "");

        return simpStr;
    }

    public static String extractIconID(String report) {
        String temp = searchInfoItem(report, "icon");
        String[] strArr = temp.split(":");
        return strArr[1];
    }

}

