package ru.kvisaz.wotolenemer.utilits;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static Date getDateFromStampInSeconds(long timestampSeconds){
        try{
            return new Date(timestampSeconds*1000);
        }
        catch (Exception e){
            return new Date();
        }
    }

    public static String getRuDate(Date date){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            return sdf.format(date);
        }
        catch (Exception e){
            return "";
        }

    }

    public static String getRuDate(long timestampSeconds){
        return getRuDate(getDateFromStampInSeconds(timestampSeconds));
    }
}
