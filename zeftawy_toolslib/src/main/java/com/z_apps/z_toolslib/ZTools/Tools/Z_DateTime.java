package com.z_apps.z_toolslib.ZTools.Tools;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import androidx.annotation.RequiresApi;

/**
 * Created by Moaz Zeftawy on 18/09/2017.
 */

public class Z_DateTime {


    public Z_DateTime() {
        Date = new Date();
        calendar = Calendar.getInstance();
        calendar.setTime(Date);
        getFormate();

    }

    public Z_DateTime(java.util.Date d) {
        Date = d;
        calendar = Calendar.getInstance();
        calendar.setTime(Date);
        getFormate();

    }

    public Z_DateTime(java.util.Date d, String Format) {
        Date = d;
        Formate = Format;
        calendar = Calendar.getInstance();
        calendar.setTime(Date);
    }

    public Z_DateTime(String d) {
        try {
            getFormate();
            DateString = d;
            Date = StringToDate(d);
            calendar = Calendar.getInstance();
            calendar.setTime(Date);

        } catch (Exception ex) {
            System.err.println("Error in date Entry " + ex);
        }
    }

    public Z_DateTime(String d, String form) {
        try {
            setFormate(form);
            DateString = d;
            Date = StringToDate(d);
            calendar = Calendar.getInstance();
            System.out.println("date is " + getDate());
      calendar.setTime(Date);
        } catch (Exception ex) {
            System.err.println("Error in Date entry " + ex);
        }
    }

    String Formate;
    String DateString;
    java.util.Date Date;
    Calendar calendar;

    public Calendar getCalendar() {
        return calendar;
    }

    public String getFormate() {
        if (Formate == null) {
            Formate = "dd/MM/yyyy";
        }
        return Formate;
    }

    public void setFormate(String Formate) {
        this.Formate = Formate;
    }

    public String getDateString() {
        return DateString;
    }

    public void setDateString(String DateString) {
        this.DateString = DateString;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date Date) {
     /// ما تعطيش للكاليندر اي حاجة لانه كدا هنفخ لك الجهاز

        this.Date = Date;
        this.calendar.setTime(Date);
        getFormate();
    }


    public java.util.Date StringToDate(String val, String Format) throws ParseException {
        String str_date = val;
        DateFormat formatter;
        java.util.Date date;
        formatter = new SimpleDateFormat(Format);
        try {
            date = formatter.parse(str_date);
            Date = date;
            calendar.setTime(date);
            return date;

            // TODO add your handling code here:
        } catch (ParseException ex) {
            System.err.println("Convation Error" + ex);
            return null;
        }

    }

    public java.util.Date StringToDate(String val) {
        String str_date = val;
        DateFormat formatter;
        java.util.Date date;
        formatter = new SimpleDateFormat(Formate);
        try {
            date = formatter.parse(str_date);
            this.Date = date;
            calendar.setTime(date);
            return date;

            // TODO add your handling code here:
        } catch (Exception ex) {
            System.err.println("Convation Error" + ex);
            return null;
        }

    }

    public java.util.Date StringToDate_UK_SQllite(String sDate) {


        String str_date = sDate;
        DateFormat formatter;
        java.util.Date date;
        formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        try {
            date = formatter.parse(str_date);
            calendar.setTime(date);
            return date;

            // TODO add your handling code here:
        } catch (Exception ex) {
            System.err.println("Convation Error" + ex);
            return null;
        }
    }


    public java.util.Date StringToDate() throws ParseException {
        String str_date = DateString;
        DateFormat formatter;
        java.util.Date date;
        formatter = new SimpleDateFormat(Formate);
        try {
            date = formatter.parse(str_date);
            calendar.setTime(date);
            return date;

            // TODO add your handling code here:
        } catch (Exception ex) {
            System.err.println("Convation Error" + ex);
            return null;
        }

    }


    public String DateToString(java.util.Date d, String Format) throws ParseException {
        setDate(d);
        DateString = new SimpleDateFormat(Format).format(d);
        return DateString;
    }


    public String DateToString_UK(java.util.Date d, String Format) {
        try {
            setDate(d);
            DateString = new SimpleDateFormat(Format, Locale.UK).format(d);
            return DateString;
        } catch (Exception e) {
            Log.e("Date time error", e.getMessage());
            return "";
        }
    }


    public String DateToString(java.util.Date d) throws ParseException {
        setDate(d);
        DateString = new SimpleDateFormat(Formate).format(d);
        return DateString;
    }

    public String DateToString_UK(java.util.Date d) throws ParseException {
        setDate(d);
        DateString = new SimpleDateFormat(Formate, Locale.UK).format(d);
        return DateString;
    }

    public String DateToString(String Format) throws ParseException {
        DateString = new SimpleDateFormat(Format).format(Date);

        return DateString;
    }

    public String DateToString_UK_SQllite() {

        DateString = new SimpleDateFormat("yyyy-MM-dd", Locale.UK).format(Date);

        return DateString;
    }

    boolean is24hore = true;

    public void setIs24hore(boolean is24hore) {
        this.is24hore = is24hore;
    }

    public String TimetToString_UK_SQllite() {
        if (is24hore) {
            DateString = new SimpleDateFormat("HH:mm", Locale.UK).format(Date);
        } else {
            DateString = new SimpleDateFormat("hh:mm a", Locale.UK).format(Date);

        }
        return DateString;
    }
    public String TimetToString  (){
        if (is24hore) {
            DateString = new SimpleDateFormat("HH:mm" ).format(Date);
        } else {
            DateString = new SimpleDateFormat("hh:mm a" ).format(Date);

        }
        return DateString;
    }

    public String DateToString_UK(String Format) throws ParseException {
        DateString = new SimpleDateFormat(Format, Locale.UK).format(Date);

        return DateString;
    }

    public String DateToString() throws ParseException {
        DateString = new SimpleDateFormat(Formate).format(Date);
        return DateString;
    }

    public String DateToString_UK() throws ParseException {
        DateString = new SimpleDateFormat(Formate, Locale.UK).format(Date);
        return DateString;
    }
    ///

    public boolean ISDate(String Date) {
        String str_date = DateString;
        DateFormat formatter;
        java.util.Date date;
        formatter = new SimpleDateFormat(Formate);
        try {
            date = formatter.parse(str_date);
            return true;

        } catch (ParseException ex) {
            return false;

        }
    }

    public boolean ISDate(String Date, String format) {
        String str_date = DateString;
        DateFormat formatter;
        java.util.Date date;
        formatter = new SimpleDateFormat(format);
        try {
            date = formatter.parse(str_date);
            return true;

        } catch (ParseException ex) {
            return false;

        }
    }

    ///
    public int getage(java.util.Date parth) {
        int pd = parth.getYear();
        int nowd = new Date().getYear();

        return nowd - pd;
    }

    public int getage() {
        int pd = Date.getYear();
        int nowd = new Date().getYear();

        return nowd - pd;
    }

    ///
    public Long DateDeff(java.util.Date d1, java.util.Date d2) {

        Long i = d1.getTime() - d2.getTime();
        Calendar c = Calendar.getInstance();


        return i;
    }

    //

    public long DateDeff(java.util.Date d1, java.util.Date d2, Time t) {

        Long i = d1.getTime() - d2.getTime();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(i);
        switch (t) {
            case DayOfyear:
                return TimeUnit.DAYS.convert(i, TimeUnit.MILLISECONDS);
            case Year:
                return d1.getYear() - d2.getYear();
            case DayOfMonth:
                Calendar c1 = Calendar.getInstance();
                c1.setTime(d1);
                ;
                Calendar c2 = Calendar.getInstance();
                c2.setTime(d2);
                Long d = c1.getTimeInMillis() - c2.getTimeInMillis();


                return d / (24 * 60 * 60 * 1000);
            case Months:
                return d1.getMonth() - d2.getMonth();

            case Munit:
                return d1.getMinutes() - d2.getMinutes();
            case Hour:
                return d1.getHours() - d2.getHours();
            case Second:
                return d1.getSeconds() - d2.getSeconds();

        }

        return TimeUnit.DAYS.convert(i, TimeUnit.MILLISECONDS);


    }


    public java.util.Date AddDate_Day(java.util.Date now, int i) {

        java.util.Date d1 = now;
        java.util.Date d2 = new Date(d1.getTime() + i * (1000 * 60 * 60 * 24));
        setDate(d2);
        return d2;
    }

    public java.util.Date AddDate_Day(int i) {


        Calendar cal = Calendar.getInstance();
        ;
        cal.setTime(getDate());

        cal.add(Calendar.DAY_OF_MONTH, i); // to get previous year add -1
        java.util.Date nextYear = cal.getTime();
        setDate(nextYear);
        return nextYear;
    }

    public java.util.Date AddDate_Month(int i) {
        java.util.Date now = Date;
        Calendar cal = Calendar.getInstance();
        ;
        cal.setTime(now);

        cal.add(Calendar.MONTH, i); // to get previous year add -1
        java.util.Date nextYear = cal.getTime();
        setDate(nextYear);
        return nextYear;
    }

    public java.util.Date AddDate_Month_LastDay() {
        java.util.Date nextYear = getDate();
        Calendar c = Calendar.getInstance();
        c.setTime(nextYear); //------>
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        nextYear = c.getTime();
        setDate(nextYear);
        return nextYear;
    }

    public java.util.Date AddDate_Month_LastDay(java.util.Date D) {
        java.util.Date nextYear = D;
        Calendar c = Calendar.getInstance();
        c.setTime(nextYear); //------>
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        nextYear = c.getTime();
        setDate(nextYear);
        return nextYear;
    }

    public java.util.Date AddDate_Month(java.util.Date now, int i) {

        Calendar cal = Calendar.getInstance();
        ;
        cal.setTime(now);

        cal.add(Calendar.MONTH, i); // to get previous year add -1
        java.util.Date nextYear = cal.getTime();
        return nextYear;
    }

    public java.util.Date AddDate_Year(int i) {
        java.util.Date now = new Date();
        Calendar cal = Calendar.getInstance();
        ;
        cal.setTime(now);

        cal.add(Calendar.YEAR, i); // to get previous year add -1
        java.util.Date nextYear = cal.getTime();
        return nextYear;
    }

    public java.util.Date AddDate_Year(java.util.Date now, int i) {

        Calendar cal = Calendar.getInstance();
        ;
        cal.setTime(now);

        cal.add(Calendar.YEAR, i); // to get previous year add -1
        java.util.Date nextYear = cal.getTime();
        return nextYear;
    }

    public java.util.Date AddDate(int i, int c) {
        java.util.Date now = getDate();
        Calendar cal = Calendar.getInstance();
        ;
        cal.setTime(now);

        cal.add(c, i); // to get previous year add -1
        java.util.Date dates = cal.getTime();
        return dates;
    }


    public java.util.Date ChangeDate_Age(int i) {
        java.util.Date now = new Date();
        Calendar cal = Calendar.getInstance();
        ;
        cal.setTime(now);

        cal.add(Calendar.YEAR, -i); // to get previous year add -1
        java.util.Date nextYear = cal.getTime();
        return nextYear;
    }


    public boolean isLastDate(java.util.Date d) {
        long i1, i2;
        i1 = d.getTime();
        i2 = new Date().getTime();
        System.out.println(" i1 " + i1 + " i2  " + i2);
        if (i1 < i2) {
            System.out.println("true");
            return true;
        } else {
            System.out.println("False");
            return false;
        }

    }

    public Time Time;

    public Z_DateTime.Time getTime() {
        return Time;
    }

    public enum Time {
        DayOfyear, DayOfMonth, Year, Months, Hour, Munit, Second

    }

    public void setTimeonly() {
        Calendar cal = Calendar.getInstance();
        cal.set(1900, 0, 1);
        setDate(cal.getTime());
        calendar = cal;
        Log.i("time is ", getDate() + "");
    }

    public void setTimeonly(int h, int m) {
        Calendar cal = Calendar.getInstance();
        cal.set(1900, 0, 1, h, m);
        setDate(cal.getTime());
        calendar = cal;
        Log.i("time is ", getDate() + "");
    }

    public void setTimeonlyNow(int h, int m) {
        Calendar cal = Calendar.getInstance();
        int y, Mon, day;
        y = cal.get(Calendar.YEAR);
        Mon = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(y, Mon, day, h, m, 0);
        setDate(cal.getTime());
        calendar = cal;
        Log.i("time is ", getDate() + "");
    }

    public void setTimeonly(String time) {

        try {
            Calendar cal = Calendar.getInstance();

            cal.set(1900, 0, 1);
            setDate(calendar.getTime());
            //  Log.i("time is " , getDate() + "");
            StringToDate(time, "HH:mm");
            Log.i("time is ", getDate() + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void setTimeonlyNOw(String time) {

        try {
            Calendar cal = Calendar.getInstance();
            int y, Mon, day;

            y = cal.get(Calendar.YEAR);
            Mon = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DAY_OF_MONTH);
            cal.set(y, Mon, day);
            setDate(calendar.getTime());
            //  Log.i("time is " , getDate() + "");
            StringToDate(time, "HH:mm");
            Log.i("time is ", getDate() + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void setTimeonlytomorrow(String time) {

        try {
            Calendar cal = Calendar.getInstance();
            int y, Mon, day;

            y = cal.get(Calendar.YEAR);
            Mon = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DAY_OF_MONTH);
            cal.set(y, Mon, day + 1);
            setDate(calendar.getTime());
            //  Log.i("time is " , getDate() + "");
            StringToDate(time, "HH:mm");
            Log.i("time is ", getDate() + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
/////


    public Z_DateTime(Context context , Date date) {
        this.context = context;
        Date = date;
        calendar = Calendar.getInstance();
        calendar.setTime(Date);
        getFormate();

    }
    public Z_DateTime(Context context ) {
        this.context = context;
        Date = new Date();
        calendar = Calendar.getInstance();
        calendar.setTime(Date);
        getFormate();

    }

    Context context;
    Activity activity ;

    public Activity getActivity() {
        if (context instanceof Activity){
            return (Activity) context;
        }else
        {  return activity;}
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Dialog onCreateDialogDate(int id) {


            DatePickerDialog d = new DatePickerDialog(context, myDateListener,
                    getDate().getYear(),
                    getDate().getMonth(),
                    getDate().getDate());

            Calendar c = Calendar.getInstance();
            c.setTime(new Date());

            Date dd = new Date();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int da = c.get(Calendar.DAY_OF_MONTH);
            d.getDatePicker().init(y, m, da, new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                    int id;
                }
            });
            d.getDatePicker().setMaxDate(dd.getTime());
            d.setTitle("");
            return d;


    }



    @RequiresApi(api = Build.VERSION_CODES.N)
     public Dialog onCreateDialogtime(int id)
    {

            TimePickerDialog d = new TimePickerDialog(context, mytimeeListener,
                    getCalendar().get(Calendar.HOUR),
                    getCalendar().get(Calendar.MINUTE),
                    false);
            d.setTitle("");
            return d;

        }


    DatePickerDialog.OnDateSetListener myDateListener;

    public TimePickerDialog.OnTimeSetListener mytimeeListener ;

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
        this.Date = calendar.getTime();
    getFormate();


    }

    public void setCalendar(Date date) {
        this.calendar.setTime(date);
        this.Date = date;
        getFormate();


    }


    public void setTime(Z_DateTime.Time time) {
        Time = time;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setMyDateListener(DatePickerDialog.OnDateSetListener myDateListener) {
        this.myDateListener = myDateListener;
    }

    public void setMytimeeListener(TimePickerDialog.OnTimeSetListener mytimeeListener) {
        this.mytimeeListener = mytimeeListener;
    }



    public  String formatMillis(long timeInMillis) {
        String sign = "";
        if (timeInMillis < 0) {
            sign = "-";
            timeInMillis = Math.abs(timeInMillis);
        }

        long minutes = timeInMillis / TimeUnit.MINUTES.toMillis(1);
        long seconds = timeInMillis % TimeUnit.MINUTES.toMillis(1) / TimeUnit.SECONDS.toMillis(1);
        long millis = timeInMillis % TimeUnit.SECONDS.toMillis(1);

        final StringBuilder formatted = new StringBuilder(20);
        formatted.append(sign);
        formatted.append(String.format("%02d", minutes));
        formatted.append(String.format(":%02d", seconds));
        // formatted.append(String.format(".%03d", millis));

        return formatted.toString();
    }


    public void setMytimeeAlarmListener(final Class Alarm , final int Requstcode) {

        this.mytimeeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar cal = Calendar.getInstance();
                 cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),hourOfDay,minute, 0 );
    setCalendar(cal);
    setDate(cal.getTime());
    setAlarm(cal  , Alarm ,Requstcode);
            }
        };
    }

    private void setAlarm(Calendar targetCal , Class alarm , int rq) {



        Intent intent = new Intent(context, alarm );
        intent.putExtra("Date",targetCal.getTimeInMillis());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, rq , intent, 0);
        AlarmManager alarmManager = (AlarmManager) context .  getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP ,  targetCal.getTimeInMillis(),
                pendingIntent);

    }

    public   void setAlarm (Date time , Class alarm , int Requstcode ){
        AlarmManager alarmManager;

        alarmManager = (AlarmManager) context . getSystemService(Context.  ALARM_SERVICE);
        Log.d("MyActivity", "Alarm On");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int h =    calendar.get(Calendar.HOUR_OF_DAY);
        int  m =   calendar.get(Calendar.MINUTE );
        Log.i("hour", h + "");
        Log.i("mounth", m + "");

        Intent myIntent = new Intent( context , alarm ) ;

        myIntent.putExtra("Date",calendar.getTimeInMillis());

        PendingIntent pendingIntent = PendingIntent.getBroadcast( context , Requstcode, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time.getTime(), pendingIntent);

    }
public  long getDatemellescondFromBroadcastIneent(Intent intent){

        return  intent.getLongExtra("Date" ,Date.getTime() );
}


    public  Calendar getCalenderFromBroadcastIneent(Intent intent){
long time;
          time =  intent.getLongExtra("Date" ,Date.getTime() );
    Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
    setCalendar(calendar);
    return  calendar;
    }


}