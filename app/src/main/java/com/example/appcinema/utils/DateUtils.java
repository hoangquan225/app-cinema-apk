package com.example.appcinema.utils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
public class DateUtils {
    public static String formatddMMyyyy(long milliseconds) {
        Date date = new Date(milliseconds);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        return sdf.format(date);
    }

    public static String formatddMM(long milliseconds) {
        Date date = new Date(milliseconds);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM", Locale.getDefault());
        return sdf.format(date);
    }

    public static int[] convertTimeMiliseconds(long miliSeconds) {
        int[] result = new int[3];
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(miliSeconds);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        result[0] = day;
        result[1] = month;
        result[2] = year;

        return result;
    }
}
