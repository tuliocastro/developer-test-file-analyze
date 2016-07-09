package br.com.segware.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by tulio on 07/07/16.
 */
public class DateUtil {


    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static DateTime format(String date, String format) {

        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);

        return formatter.parseDateTime(date);
    }

    public static DateTime format(String date) {

        return format(date, DEFAULT_DATE_FORMAT);
    }
}
