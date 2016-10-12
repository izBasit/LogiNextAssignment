package me.iz.mobility.loginextassignment.utils;

import android.content.Context;
import android.location.LocationManager;

import org.ocpsoft.prettytime.PrettyTime;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by ibasit on 10/11/2016.
 */

public class Utility {

    /**
     * Eta will be generated randomly between 10 hours and 10 minutes
     * @return
     */
    public static long getRandomETA() {

        long offset = Timestamp.valueOf("2016-10-12 00:00:00").getTime();
        long end = Timestamp.valueOf("2016-10-20 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
        return rand.getTime();
    }

    /**
     * Method to get humanized eta
     * @param eta
     * @return
     */
    public static String getHumanizedETA(long eta) {

        PrettyTime prettyTime = new PrettyTime();
        String humanizedDate =  prettyTime.format(new Date(eta));
        return humanizedDate;
    }

    public static int randInt() {

        int min = 10000000;
        int max = 99999999;
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static String getDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM, yyyy", Locale.getDefault());
        return formatter.format(c.getTime());
    }

    public static boolean isGPSOn(Context mContext) {
        LocationManager manager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
