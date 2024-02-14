package com.z_apps.Helteycare.Project.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.z_apps.Helteycare.Project.Classes.Myuserdata;
import com.z_apps.Helteycare.R;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.EventAccountCreated;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.EventSignedin;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.Eventdbmissoncompliete;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.EventdbmissoncomplieteFirestore;
import com.z_apps.z_toolslib.ZTools.Tools.Z_Loction;
import com.z_apps.z_toolslib.ZTools.Tools.Z_methods;

import java.io.IOException;

public class Act_signUp extends AppCompatActivity {
EditText Nameetxt  , addressEtxt ;
TextView phonetxt;

Z_methods z_methods ;
    Myuserdata userData ;

    Z_Loction z_loction ;
Location location ;
    String phone ;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_sign_up);
    Nameetxt = findViewById(R.id.Etxtname);
         phonetxt = findViewById(R.id.txtphone);
    addressEtxt = findViewById(R.id.Etxtaddress);
  phone = getIntent().getStringExtra("phone");
  phonetxt.setText(phone);
    z_methods = new Z_methods(this);
    userData = new  Myuserdata(this);
z_loction = new Z_Loction(this);
 z_loction.getLocation_andPermssion();
    userData.setEventSignedin(new EventSignedin() {

        @Override
        public void SaginedinEmail(String id) {

        }

        @Override
        public void SaginedinEmailFialed() {
            try {
                z_loction.getAddressfromLocation_Details();
                String addres = z_loction.getAddressfromLocation();
                addressEtxt.setText(addres);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    });

   userData.setEventdbmissoncomplieteFirestore(new EventdbmissoncomplieteFirestore() {
       @Override
       public DocumentReference messionComplete(DocumentReference dr) {
           Intent i = new Intent();
           i.putExtra("uid" , dr.getId());
           z_methods.setFinishActivity(true);

           z_methods.GOTo_Activity(Act_Question.class,i   ,  R.anim.goin_tolift , R.anim.goout_tolift);

           return null;
       }


   });
     }

    @Override
    protected void onStart() {
        super.onStart();
        setLoaction();

     }

    public void Go_add(View view) {
         String nam = Nameetxt.getText() + "";
         String addr = addressEtxt.getText() + "";
         if (addr.equals("")){
             addressEtxt.setError("رجاء ادخال العنوان");
         }else if (addr.length()< 10 ){

             addressEtxt.setError("رجاء ادخال العنوان كاملا او استخدم gps");
return;
         }
         if (nam.equals("")){Nameetxt.setError("رجاء ادخال اسمك ");
         return;
         }
         if (location == null ){
             addressEtxt.setError("يجب تشغيل الموقع لتحديد المكان");
return;

         }
userData.signeUp(nam  , phone , addr, location);
    }

void  setLoaction(){

    userData.setProgresstitle("جاري تحديد الموقع");
    userData.Showprogress();
    z_loction.getLocation_andPermssion();
    CountDownTimer timer = new CountDownTimer(2000 , 2000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            userData.Dismessprogress();
            location = z_loction.getMYlocation();
            if (location == null    ){
z_loction.onNullLocation();
                Toast.makeText(Act_signUp.this , "للاسف لم يتم تحديد الموقع رجاء التاكد من فتح  GPS" , Toast.LENGTH_SHORT).show();
            }else {

                try {
                    String addres = z_loction.getAddressfromLocation();
                    addressEtxt.setText(addres);
                    int stxtlen = addres.length();
                    if (stxtlen > 20){
                        addressEtxt.setTextSize(15);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    timer.start() ;
}

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        z_loction.requstpermission(requestCode, permissions, grantResults);
    }

    public void Go_loc(View view) {
        setLoaction();
    }
}
