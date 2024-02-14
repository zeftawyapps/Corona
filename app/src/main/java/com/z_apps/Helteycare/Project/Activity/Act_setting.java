package com.z_apps.Helteycare.Project.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.z_apps.Helteycare.Project.Classes.Myuserdata;
import com.z_apps.Helteycare.R;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.EventSignedin;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.EventdbmissoncomplieteFirestore;
import com.z_apps.z_toolslib.ZTools.Tools.Z_Loction;
import com.z_apps.z_toolslib.ZTools.Tools.Z_methods;

import java.io.IOException;

public class Act_setting extends AppCompatActivity {
    EditText Nameetxt  , addressEtxt ;
    TextView phonetxt , Doctorrequstbtn;

    Z_methods z_methods ;
    Myuserdata userData ;

    Z_Loction z_loction ;
    Location location ;
    String uid ;
    String phone ;
    int state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_setting);


        Nameetxt = findViewById(R.id.Etxtname);
        phonetxt = findViewById(R.id.txtphone);
        addressEtxt = findViewById(R.id.Etxtaddress);
        Doctorrequstbtn = findViewById(R.id.doctorequstbtn);
        uid = getIntent().getStringExtra("uid");

         z_methods = new Z_methods(this);
        userData = new  Myuserdata(this);
        z_loction = new Z_Loction(this);
        z_loction.getLocation_andPermssion();

        userData.setEventdbmissoncomplieteFirestore(new EventdbmissoncomplieteFirestore() {
            @Override
            public DocumentReference messionComplete(DocumentReference dr) {

                return null;
            }


        });
    }

    @Override
    protected void onStart() {
        super.onStart();
     //   setLoaction();
loaddata();
    }

    void loaddata(){


    userData .loadData(uid ) .addSnapshotListener(new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

        userData.Dismessprogress();
            userData.Dismessprogress();
           phonetxt.setText (documentSnapshot.getString(userData.Celluserphone.getName()) + "");
            Nameetxt  .setText (documentSnapshot.getString(userData.Cellusername.getName()) + "");
            addressEtxt.setText (documentSnapshot.getString(userData.CelluserAddres.getName()) + "");
state = Integer.parseInt(documentSnapshot.get(userData.CelluserJob.getName()) + "");
             userData.setLatat(documentSnapshot.getDouble( userData.CelluserLoction_lat.getName()));
             userData.setLongat(documentSnapshot.getDouble( userData.CelluserLoction_long.getName()));
if (state >3){Doctorrequstbtn.setVisibility(View.GONE);}else {

    Doctorrequstbtn.setVisibility(View.VISIBLE);
}
        }
    });


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

        userData.Update( uid ,  nam  , phone , addr, userData.getLongat() , userData.getLatat());
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
                    Toast.makeText(Act_setting.this , "للاسف لم يتم تحديد الموقع رجاء التاكد من فتح  GPS" , Toast.LENGTH_SHORT).show();
                }else {

                    try {
                        String addres = z_loction.getAddressfromLocation();
                        addressEtxt.setText(addres);
                        int stxtlen = addres.length();
                        if (stxtlen > 20){
                            addressEtxt.setTextSize(15);
                            userData.setLatat( location.getLongitude() );
                            userData.setLongat(location.getLatitude() );

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

    public void Go_signout(View view) {
        userData.setUserSavesinShareprephrance_int(userData.USER_LOGIN_STATE , userData.USER_LOGIN_STATE_non);
        z_methods.GO_Out();
    }

    public void Go_doctorreq(View view) {
        Toast.makeText(this ,"تم تقديم طلب دخولك كطبيب انتظر منا اتصال " , Toast.LENGTH_LONG).show();
        userData.Updatejobstate(uid , userData.JOBSTATDOCTORREQUST);
        finish();
    }


}


