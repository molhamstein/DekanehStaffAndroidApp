package com.brain_socket.dekanehstaff.utils;

import android.util.Pair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateHelper {
    public static Pair<String,Long> diffBetweenDateAndCurrentTime(String date)  {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
            Date formattedDate = null;

            formattedDate = sdf.parse(date);

            Calendar c1 = Calendar.getInstance(); // Current Time
            Calendar c2 = Calendar.getInstance(); // Future Time
            c2.setTime(formattedDate);


            Long diff = Math.abs(c2.getTimeInMillis() - c1.getTimeInMillis());
            Long hours = diff / (1000 * 60 * 60);
            Long minuts = (diff / (1000 * 60)) % 60;
            Long seconds = (diff / (1000)) % 60;

            hours = Math.min(23, hours);
            minuts = Math.min(59, minuts);
            seconds = Math.min(59, seconds);

            String temp = hours.toString() + ":" + minuts.toString() + ":" + seconds.toString();
            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss", Locale.US);
            Date dd = sdf2.parse(temp);
            return new Pair<>(sdf2.format(dd), diff);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return null ;

    }


}
