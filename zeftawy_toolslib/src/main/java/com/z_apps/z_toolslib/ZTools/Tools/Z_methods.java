package com.z_apps.z_toolslib.ZTools.Tools;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import static android.content.Context.ALARM_SERVICE;

public class Z_methods {
Context context;
Activity activity ;
boolean finishActivity = false ;
void  finsh(){
    if (finishActivity){
        getActivity().finish();
    }
}


    public void setFinishActivity(boolean finishActivity) {
        this.finishActivity = finishActivity;
    }

    public Activity getActivity() {
        if (context instanceof Activity){
            return (Activity) context;
        }else
        {  return activity;}
    }
public  void GoTo_OtherApps(String uri){
   getActivity() .  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));

}
    public Z_methods(Context context) {
      this.context = context;
   }

   public  void GOTo_Activity(final Class Toa , final int sleep , final int anim1 , final int anim2) {
      Runnable run = null;

      run = new Runnable() {
         @Override
         public void run() {
            int i = 0;
            try {
               while (i < 1) {
                  Thread.sleep(sleep);

                  Intent inte = new Intent(getActivity(), Toa );
                  getActivity().startActivity(inte);
                   getActivity().overridePendingTransition( anim1 , anim2);
                   i++;
               }
                finsh();

            } catch (Exception e) {
               Log.i("error ", e.getMessage());
            }
         }

      };
      Thread t = new Thread(run);
      t.start();

   }


    public  void GOTo_Activity(final Class Toa , final int sleep  ) {
        Runnable run = null;

        run = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                try {
                    while (i < 1) {
                        Thread.sleep(sleep);

                        Intent inte = new Intent(context, Toa );
                        getActivity().startActivity(inte);

                        i++;
                    }
                    finsh();

                } catch (Exception e) {
                    Log.i("error ", e.getMessage());
                }
            }

        };
        Thread t = new Thread(run);
        t.start();

    }

    public  void GOTo_Activity(final Class Toa , final Intent intent  , final int sleep  ) {
        Runnable run = null;

        run = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                try {
                    while (i < 1) {
                        Thread.sleep(sleep);

                        Intent inte = new Intent(context, Toa );
                       inte.putExtras(intent);
                        getActivity().startActivity(inte);

                        i++;
                    }
                    finsh();

                } catch (Exception e) {
                    Log.i("error ", e.getMessage());
                }
            }

        };
        Thread t = new Thread(run);
        t.start();

    }


    public  void GOTo_Activity(final Class Toa, int anim1 , int anim2) {
        Intent inte = new Intent(context, Toa);
        getActivity().startActivity(inte);
        getActivity().overridePendingTransition( anim1 , anim2);
        finsh();

    }
    public  void GOTo_ActivityforResult(final Class Toa, int Requst , int anim1 , int anim2) {
        Intent inte = new Intent(context, Toa);
        getActivity().startActivityForResult(inte , Requst);
        getActivity().overridePendingTransition( anim1 , anim2);
        finsh();

    }
    public  void GOTo_ActivityforResult(final Class Toa, int Requst  ) {
        Intent inte = new Intent(context, Toa);
        getActivity().startActivityForResult(inte , Requst);
         finsh();

    }

    public  void GOTo_Activity(final Class Toa, Intent i  , int anim1 , int anim2) {
        Intent inte = new Intent(context, Toa);
        inte.putExtras(i);
        getActivity().startActivity(inte);
        getActivity().overridePendingTransition( anim1 , anim2);
        finsh();

    }
public  void setImage(Context context , String uri , ImageView img){

    Picasso.with(context).load(uri).fit().into(img);

}
    public  void setImage(String uri , ImageView img){

        Picasso.with(context).load(uri).fit().into(img);

    }

    public  void setImage(Context context , Uri uri , ImageView img){

        Picasso.with(context).load(uri).into(img);

    }
    public  void setImage(Uri uri , ImageView img){

        Picasso.with(context).load(uri).fit().into(img);

    }

    public  void GOTo_ActivityforResutlt(final Class Toa, Intent i , int Requst , int anim1 , int anim2) {
        Intent inte = new Intent(context, Toa);
        inte.putExtras(i);
        getActivity().startActivityForResult(inte,Requst);
        getActivity().overridePendingTransition( anim1 , anim2);
        finsh();

    }

    public  void GOTo_ActivityforResutlt(final Class Toa, int Requst , int anim1 , int anim2) {
        Intent inte = new Intent(context, Toa);

        getActivity().startActivityForResult(inte,Requst);
        getActivity().overridePendingTransition( anim1 , anim2);
        finsh();

    }

    public  void GOTo_Activity(final Class Toa, Intent i    ) {
        Intent inte = new Intent(context, Toa);
        inte.putExtras(i);
        getActivity().startActivity(inte);
        finsh();

    }
public  void RestartActivity(Intent i ){



    i .setClass(getActivity(), this.getClass());

  getActivity() .   startActivity(i );
}


    public  void RestartActivity( ){

Intent i = new Intent() ;

        i .setClass(getActivity(), this.getClass());

        getActivity() .   startActivity(i );
    }

    public  void GO_Out(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity(). startActivity(intent);
        getActivity() .    finish();
    }
    public  void GOTo_Activity(  final Class Toa   ) {
        Intent inte = new Intent(context, Toa);

        getActivity().startActivity(inte);

    }

    public  void GOTo_ActivityforResult(final Class Toa, Intent i , int Req    ) {
        Intent inte = new Intent(context, Toa);
        inte.putExtras(i);
        getActivity().startActivityForResult(inte , Req);

    }


    public  void GOTo_ActivityforResutlt(final Class Toa  , int Req  ) {
        Intent inte = new Intent(context, Toa);

        getActivity().startActivityForResult(inte ,Req);

    }
public  void FinshActivityWithanimation(int anim , int anim1){


  getActivity() .   overridePendingTransition(anim, anim1);

}

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
  public  void ChangeColor(View view , int  d1 , int  d2 ){
     Context c = context ;
     Drawable[] AnimationColors = {
            c. getResources().getDrawable(d1) ,
             c. getResources().getDrawable(d2)

     };

     Drawable[] DefaultColors = {
             c. getResources().getDrawable(d2) ,
             c. getResources().getDrawable(d1)


     };

     TransitionDrawable transitiondrawable1, transitiondrawable2;

     transitiondrawable1 = new TransitionDrawable(AnimationColors);
      transitiondrawable1.startTransition(2500);
     view.setBackground(transitiondrawable1);



     transitiondrawable2 = new TransitionDrawable(DefaultColors);
      transitiondrawable2.startTransition(1000);
     view.setBackground(transitiondrawable2);




  }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public  void ChangeColor(View view , int  d1 , int  d2 , int power ){
        Context c = context ;
        Drawable[] AnimationColors = {
                c. getResources().getDrawable(d1) ,
                c. getResources().getDrawable(d2)

        };

        Drawable[] DefaultColors = {
                c. getResources().getDrawable(d2) ,
                c. getResources().getDrawable(d1)


        };

        TransitionDrawable transitiondrawable1, transitiondrawable2;

        transitiondrawable1 = new TransitionDrawable(AnimationColors);

        view.setBackground(transitiondrawable1);

        transitiondrawable1.startTransition(10);

        transitiondrawable2 = new TransitionDrawable(DefaultColors);

        view.setBackground(transitiondrawable2);

        transitiondrawable2.startTransition(power);


    }


 public   void setAlarm (Date tiem , Class alarm ){
      Z_DateTime dateTime = new Z_DateTime(tiem);
      AlarmManager alarmManager;

      alarmManager = (AlarmManager) context. getSystemService(ALARM_SERVICE);
      Log.d("MyActivity", "Alarm On");
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(dateTime.getDate());
      int h =    calendar.get(Calendar.HOUR_OF_DAY);
      int  m =   calendar.get(Calendar.MINUTE );
      Log.i("hour", h + "");
      Log.i("mounth", m + "");

      Intent myIntent = new Intent( context, alarm.getClass()) ;
      PendingIntent pendingIntent = PendingIntent.getBroadcast( context, 0, myIntent, 0);
      alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

   }


   public   void addNotifay(int id ,String  CHANNEL_ID ,  int icon , int smallicon , String msg , String title, Uri audio, Class c ) {
       NotificationCompat.Builder nbuld;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           String NOTIFICATION_CHANNEL_ID = "ws.wolfsoft.e_books";
           String channelName = "payservice";
           NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
            chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
           NotificationManager manager = (NotificationManager)context.  getSystemService(context.NOTIFICATION_SERVICE);
           assert manager != null;
           manager.createNotificationChannel(chan);
           Intent intent = new Intent();
           PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
  Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle();
           bigTextStyle.setBigContentTitle( title) ;
            builder.setSmallIcon(smallicon);
            builder.setPriority(Notification.PRIORITY_MAX);
            builder.setFullScreenIntent(pendingIntent, true);
           builder.setAutoCancel(true);
           builder.setOngoing(true);

           NotificationManager mang = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
           mang.notify(id, builder.build());
           id++;


           } else {

               nbuld = new NotificationCompat.Builder(context);
               nbuld.setContentTitle(title);
               nbuld.setContentText(msg);
               nbuld.setSmallIcon(icon);
               nbuld.setSmallIcon(smallicon, id);
               if (audio == null) {

                   Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                   nbuld.setSound(sound);
                   nbuld.setAutoCancel(true);
               }
               ///intent.gotoActivity();
               Intent i = new Intent(context, c);
               TaskStackBuilder tsb = TaskStackBuilder.create(context);
               tsb.addParentStack(c);
               tsb.addNextIntent(i);
               PendingIntent resultPendingIntent =
                       tsb.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);

               nbuld.setContentIntent(resultPendingIntent);

               nbuld.addAction(icon, title, resultPendingIntent);

               NotificationManager mang = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
               mang.notify(id, nbuld.build());
               id++;

           }
       }




    public  interface  Iintent{
    void gotoActivity();
}
    public void sendToViber(String messageBody, String viberContact) {
        Uri uri = Uri.parse("smsto:" + viberContact);
        Intent viberIntent = new Intent(Intent.ACTION_SENDTO, uri);
        viberIntent.setPackage("com.viber.voip");
       getActivity() .  startActivity(Intent.createChooser(viberIntent, "Share"));
    }
    public void WhatsappsendDirect(String phone , String txt){


        try {

            Uri uri = Uri.parse("whatsapp://send?phone=" + "+"+ phone + "&text="+ txt);
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            // i.setType("text/plain");
            String text = txt ;
            i.putExtra(Intent.EXTRA_TEXT, text);
            getActivity().   startActivityForResult(i, 5);
        }
        catch (ActivityNotFoundException e){
            e.printStackTrace();
            Toast.makeText(getActivity(), "WhatsApp not installed.", Toast.LENGTH_SHORT).show();
        }
    }

   public void Whatsapp_Sent(String phone , String mag ){

        PackageManager pm= context.getPackageManager();
        try {
            Uri uri = Uri.parse("smsto:" + phone);
            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = mag;

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            waIntent.putExtra("jid", 1 );
            //  getContext().  startActivity(Intent.createChooser(waIntent, "Share with"));
             context.  startActivity( waIntent );

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(context, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }



    public void Sharetext(String msg){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);


        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,msg);
       context. startActivity(Intent.createChooser(intent, "Share"));
    }

    public  void SMS(String phont, String msg  ){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phont, null, msg, null, null);
            Toast.makeText(context, "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(context,
                    "Sending SMS failed.",
                    Toast.LENGTH_LONG).show();
            Log.i("error", e.getMessage());
            e.printStackTrace();
        }
    }

    public Intent showImgFileChooser() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("image/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        return intent;
    }

    public Intent showFileChooser() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        return intent;
    }
    public Intent showFileaudioChooser() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("audio/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        return Intent.createChooser(intent, "select audio") ;
    }
public void  Animation(int anim , View view){


    Animation animscal = AnimationUtils.loadAnimation( getActivity(), anim);
    view.setAnimation(animscal);
    view.getAnimation().start();
}
public  void coppytext(String text){

    ClipboardManager clipboard = (ClipboardManager) context.  getSystemService(Context.CLIPBOARD_SERVICE);
    ClipData clip = ClipData.newPlainText("Zekr" , text);
    clipboard.setPrimaryClip(clip);


}



         int previousFingerPosition = 0;
        int baseLayoutPosition = 0;
         int defaultViewHeight = 0 ;
int DefrantY= 0 ;
          boolean isScrollingUp = false;
         boolean isScrollingDown = false;

public  void setscrollview(View view){



    view.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            // Get finger position on screen
            final int Y = (int) event.getRawY();

            // Switch on motion event type
            switch (event.getAction() & MotionEvent.ACTION_MASK) {

                case MotionEvent.ACTION_DOWN:
                    // save default base layout height
                    defaultViewHeight = v.getHeight();

                    // Init finger and view position
                    previousFingerPosition = Y;
                    baseLayoutPosition = (int) v.getY();
                    break;

                case MotionEvent.ACTION_UP:
                    // If user was doing a scroll up
                    if(isScrollingUp){
                        // Reset baselayout position
                        v.setY(0);
                        // We are not in scrolling up mode anymore
                        isScrollingUp = false;
                    }

                    // If user was doing a scroll down
                    if(isScrollingDown){
                        // Reset baselayout position
                        v.setY(0);
                        // Reset base layout size
                        v.getLayoutParams().height = defaultViewHeight;
                        v.requestLayout();
                        // We are not in scrolling down mode anymore
                        isScrollingDown = false;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:

                       DefrantY = Y-previousFingerPosition;
                 if (DefrantY<= 0 ){

                     dragingEvents.ScrollUp(v,  - DefrantY);
                 }else {

                     dragingEvents.ScrollDown(v, DefrantY);

                 }
                    break;
            }
            return true;


         }
    });


}
DragingEvents dragingEvents  ;

    public DragingEvents getDragingEvents() {
        return dragingEvents;
    }

    public void setDragingEvents(DragingEvents dragingEvents) {
        this.dragingEvents = dragingEvents;
    }

    public void CallPhone(String phone) {
        Z_Permission permission ;

        permission = new Z_Permission(context);
        permission.AddPrimssion(Manifest.permission.CALL_PHONE);
        permission.CheckPirmission();


        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phone));

        getActivity().startActivity(callIntent);
    }

    public  long getRamsize(){

        ActivityManager actManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        actManager.getMemoryInfo(memInfo);
        long totalMemory = memInfo.totalMem;
        return  totalMemory;
    }
public  String getIAME() {
    String imei = "";
    TelephonyManager telephonyManager = (TelephonyManager) getActivity().  getSystemService(Context.TELEPHONY_SERVICE);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        if (getActivity().  checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            if (telephonyManager != null) {
                try {
                    imei = telephonyManager.getImei();
                } catch (Exception e) {
                    e.printStackTrace();
                    imei = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
                }
            }
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, 1010);
        }
    } else {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            if (telephonyManager != null) {
                imei = telephonyManager.getDeviceId();
            }
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, 1010);
        }
    }
return  imei;
}
}

