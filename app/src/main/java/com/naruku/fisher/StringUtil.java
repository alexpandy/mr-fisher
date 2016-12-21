package com.naruku.fisher;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;



import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * The {@link StringUtil} class is used to manage convenience methods for
 * {@link String} manipulation.
 */
@SuppressLint("DefaultLocale")
public class StringUtil {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context mContext;
    public static int MENU_NORMAL = 0;
    public static int MENU_FOOTER = 1;
    // Generate key pair for 1024-bit RSA encryption and decryption
    Key publicKey = null;
    Key privateKey = null;

    /**
     * Constructor.
     */
    private StringUtil() {
    }

    public StringUtil(Context context) {
        mContext = context;
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.genKeyPair();
            publicKey = kp.getPublic();
            privateKey = kp.getPrivate();
        } catch (Exception e) {
          //  AirbrakeNotifier.notify(e);

            Log.e("RSA", "RSA key pair error");
        }

    }

    public void initPreferences() {
        pref = mContext.getSharedPreferences("APOLLO_PREF", mContext.MODE_PRIVATE);
        editor = pref.edit();
    }

    /**
     * Return true if the string is null or empty (zero length); otherwise
     * false.
     *
     * @param string the string to test
     * @return true if the string is null or empty; otherwise false
     */
    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    /**
     * Return true if the string is null, empty, "null", or contains only
     * whitespace; otherwise false.
     *
     * @param string the string to test
     * @return true if the string is null, empty, or contains only whitespace;
     * otherwise false
     */
    public static boolean isBlank(String string) {
        return string == null || string.length() == 0
                || string.trim().length() == 0 || string.equals("null");
    }

    public SpannableStringBuilder showSuperScript(String smallText, int start, int end) {
        SpannableStringBuilder mSSBuilder;
        SuperscriptSpan mSuperscriptSpan;
        mSuperscriptSpan = new SuperscriptSpan();
        mSSBuilder = new SpannableStringBuilder(smallText);
        mSSBuilder.setSpan(
                mSuperscriptSpan, // Span to add
                start, // Start of the span (inclusive)
                end, // End of the span (exclusive)
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE // Do not extend the span when text add later
        );
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(.7f);

        // Apply the RelativeSizeSpan to display small text
        mSSBuilder.setSpan(
                relativeSizeSpan,
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        return mSSBuilder;

    }

    public static String formatDayWithDate(String date) {
        String day = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date inputDate = dateFormat.parse(date);
            DateFormat formatDate = new SimpleDateFormat("EEEE");
            day = formatDate.format(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
          //  AirbrakeNotifier.notify(e);

            Logger.exc(e);
        }

        return day;
    }

    public static String formatMonthStringWithDate(String date) {
        String month = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date inputDate = dateFormat.parse(date);
            DateFormat formatDate = new SimpleDateFormat("MMM");
            month = formatDate.format(inputDate);

        } catch (ParseException e) {
            e.printStackTrace();
            Logger.exc(e);
      //      AirbrakeNotifier.notify(e);

        }

        return month;
    }

    public static String formatMonthStringWithYear(String date) {
        String month = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date inputDate = dateFormat.parse(date);
            DateFormat formatDate = new SimpleDateFormat("MMM yyyy");
            month = formatDate.format(inputDate);

        } catch (ParseException e) {
            e.printStackTrace();
            Logger.exc(e);
         //   AirbrakeNotifier.notify(e);

        }

        return month;
    }

    public static String formatDayStringWithDate(String inputDate) {

        String dayString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(inputDate);
            DateFormat formatDate = new SimpleDateFormat("d");
            dayString = formatDate.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
       //     AirbrakeNotifier.notify(e);
        }


        return dayString;
    }

    public static String getDayOfMonthSuffix(String inputDate) {
        int n = Integer.parseInt(formatDayStringWithDate(inputDate));
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    public static String formatDateStringWithDate(String inputDate) {

        String dayString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {

            Date date = dateFormat.parse(inputDate);
            DateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");
            dayString = formatDate.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        //    AirbrakeNotifier.notify(e);

        }


        return dayString;
    }

    public static String formatDateStringChangeFormat(String inputDate) {

        String dayString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {

            Date date = dateFormat.parse(inputDate);
            DateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");
            dayString = formatDate.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
         //   AirbrakeNotifier.notify(e);

        }


        return dayString;
    }

    public static String formatRecentPaymentDateStringWithDate(String inputDate) {
        String dayString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {

            Date date = dateFormat.parse(inputDate);
            DateFormat formatDate = new SimpleDateFormat("MMM dd, yyyy");
            dayString = formatDate.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
         //   AirbrakeNotifier.notify(e);

        }
        return dayString;
    }

    public static String formatinterestratetodisplay(float rate) {
        DecimalFormat threeZeroes = new DecimalFormat("#0.000");
        String result = threeZeroes.format(rate);
        return result;
    }

    // Use this method to format the amount which is to be displayed through out the application
    public static String formatAmountToDisplay(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        String currencyFormatted = decimalFormat.format(amount);
        return currencyFormatted;
    }

    public static String getAccountNumberForDisplay(String accNumber) {
        return accNumber.substring(accNumber.length() - 4, accNumber.length());
    }

    public static boolean getRoutingNumberForDisplay(String RoutingNumber) {

        if (RoutingNumber.length() == 9) {
            return true;
        }
        return false;
    }

    public static boolean getAccountNumber(String strAccNumber) {
        if (strAccNumber.length() >= 5 && strAccNumber.length() <= 22) {
            return true;
        }
        return false;
    }

    public static boolean getAccountNumberCompare(String strAccNumber, String strReenterAccNumber) {
        if (strAccNumber.equals(strReenterAccNumber)) {
            return true;
        }
        return false;
    }

    public static String formatEasternTimeZone() {

        String dayString = "";

        String strTimeZone = "EST";
        try {
            TimeZone timeZone = TimeZone.getTimeZone(strTimeZone);
            long utc = System.currentTimeMillis();
            Date date = new Date(utc);
            DateFormat format = new SimpleDateFormat("EEE MMM d, yyyy");

            format.setTimeZone(timeZone);
            dayString = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
         //   AirbrakeNotifier.notify(e);

        }
        return dayString;
    }

    public static String formatPaymentDateforBanner(String paymentDate) {
        String dayString = "";
        Date date = null;
        SimpleDateFormat actualDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = actualDateFormat.parse(paymentDate);
            DateFormat expectedDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            dayString = expectedDateFormat.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        //    AirbrakeNotifier.notify(e);

        }


        return dayString;
    }

    public static String getCurrentYear() {

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        String yearInString = String.valueOf(year);

        return yearInString;
    }

    public static int formatDiffDaysFromCurrentDate(String paymentDueDate) {

        int dayCount = 0;
        Date givenDate = null;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat actualDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            givenDate = actualDateFormat.parse(paymentDueDate);
            Date currentDate = c.getTime();
            long diffDate = currentDate.getTime() - givenDate.getTime();
            dayCount = (int) ((float) diffDate / (24 * 60 * 60 * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        //    AirbrakeNotifier.notify(e);

        }
        return dayCount;
    }

    public static String addMonth(String paymentDueDate, int count) {
        String dayString = "";
        Date date = null;
        SimpleDateFormat actualDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            date = actualDateFormat.parse(paymentDueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, count); //minus number would decrement the days
        dayString = actualDateFormat.format(cal.getTime());
        return dayString;
    }

}