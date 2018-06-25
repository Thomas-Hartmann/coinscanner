/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Date;

/**
 *
 * @author tha
 */
public class Util {
    public static double getHoursFromMilliSecs(long milli){
        return milli/(1000*60*60);
    }
    public static long getMilliSecFromHours(double hours){
        return (long) hours*60*60*1000;
    }
    public static long getMilliSecFromMinutes(int minutes){
        return (int) minutes*60*1000;
    }

    public static Date getDateFromMilliSecs(long milli){
        return new Date(milli);
    }
    public static void main(String[] args) {
        System.out.println("get date from milli");
        System.out.println(new Date().getTime());
        //1518445841639
        //1499820000000
        System.out.println(getDateFromMilliSecs(1518445841639L));
    }
}
