package com.incture.leaveme.utility;

/**
 * Created by Mohammed on 10/13/2015.
 */


        import android.text.format.DateUtils;

        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.TimeZone;

public class TimeUtils {


    public static String getDateMonthFromDate(String inputDate) {
        if (inputDate == null || inputDate.length() < 24) {
            return "";
        }
        SimpleDateFormat formatter1, formatter2;
        formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        Date date = null;
        try {
            date = formatter1.parse(inputDate.substring(0, 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter2 = new SimpleDateFormat("dd");
        String processedDate = formatter2.format(date);
        System.out.println("=====>getDateMonthFromDate  " + processedDate);
        return processedDate;
    }


    public static String getTimeFromDate(String inputDate) {
        if (inputDate == null || inputDate.length() < 24) {
            return "";
        }
        SimpleDateFormat formatter1, formatter2;
        formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter1.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));

        Date date = null;
        try {
            date = formatter1.parse(inputDate.substring(0, 24));
            ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter2 = new SimpleDateFormat("h:mm aa");
        String processedDate = formatter2.format(date);
        return processedDate;
    }

    public static String getTimeFromDate1(String inputDate) {
        if (inputDate == null || inputDate.length() < 24) {
            return "";
        }
        SimpleDateFormat formatter1, formatter2;
        formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter1.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));

        Date date = null;
        try {
            date = formatter1.parse(inputDate.substring(0, 24));
            ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter2 = new SimpleDateFormat("EEEE dd MMM yyyy,h:mm aa");
        String processedDate = formatter2.format(date);
        return processedDate;
    }

    public static String getTimeFromDatenew(String inputDate) {
        if (inputDate == null || inputDate.length() < 24) {
            return "";
        }
        SimpleDateFormat formatter1, formatter2;
        formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter1.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));

        Date date = null;
        try {
            date = formatter1.parse(inputDate.substring(0, 24));
            ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter2 = new SimpleDateFormat("EEEE, MMM dd");
        String processedDate = formatter2.format(date);
        return processedDate;
    }

    public static String getTimeFromDate2(String inputDate) {
        if (inputDate == null || inputDate.length() < 24) {
            return "";
        }
        SimpleDateFormat formatter1, formatter2;
        formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter1.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));

        Date date = null;
        try {
            date = formatter1.parse(inputDate.substring(0, 24));
            ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter2 = new SimpleDateFormat("dd MMM yyyy");
        String processedDate = formatter2.format(date);
        return processedDate;
    }

    public static String getTimeFromDate3(String inputDate) {
        if (inputDate == null || inputDate.length() < 24) {
            return "";
        }
        SimpleDateFormat formatter1, formatter2;

        formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter1.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));

        Date date = null;
        try {
            date = formatter1.parse(inputDate.substring(0, 24));
            ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter2 = new SimpleDateFormat("HH:mm:ss");
        String processedDate = formatter2.format(date);
        return processedDate;
    }

    public static String getTimeString(Date fromdate,String time) {

        long then;
        then = fromdate.getTime();
        Date date = new Date(then);

        StringBuffer dateStr = new StringBuffer();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar now = Calendar.getInstance();

        int days = daysBetween(calendar.getTime(), now.getTime());
        int minutes = hoursBetween(calendar.getTime(), now.getTime());
        int hours = minutes / 60;
        if (days == 0) {

            int second = minuteBetween(calendar.getTime(), now.getTime());
            if (minutes > 60) {

                if (hours >= 1 && hours <= 24) {
                    if(hours==1) {
                        dateStr.append(hours).append(" hr ago");
                    }else{
                        dateStr.append(hours).append(" hrs ago");
                    }
                }

            } else {

                if (second <= 10) {
                    dateStr.append("Now");
                } else if (second > 10 && second <= 30) {
                    dateStr.append("few secs ago");
                } else if (second > 30 && second <= 60) {
                    dateStr.append(second).append(" secs ago");
                } else if (second >= 60 && minutes <= 60) {
                    if(minutes==1) {
                        dateStr.append(minutes).append(" min ago");
                    }else{
                        dateStr.append(minutes).append(" mins ago");
                    }
                }
            }
        } else

        if (hours > 24 && days <= 7) {
            if(days==1) {
                dateStr.append(days).append(" day ago");
            }else{
                dateStr.append(days).append(" days ago");
            }
        } else {
            dateStr.append(time);
        }

        return dateStr.toString();
    }

    public static int minuteBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / DateUtils.SECOND_IN_MILLIS);
    }

    public static int hoursBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / DateUtils.MINUTE_IN_MILLIS);
    }

    public static int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / DateUtils.DAY_IN_MILLIS);
    }

}
