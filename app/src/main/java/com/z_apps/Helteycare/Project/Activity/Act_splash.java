package com.z_apps.Helteycare.Project.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.z_apps.Helteycare.Project.Classes.Myuserdata;
import com.z_apps.Helteycare.R;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.EventSignedin;
import com.z_apps.z_toolslib.ZTools.Tools.Z_methods;

public class Act_splash extends AppCompatActivity {
    Myuserdata myuserData ;
Z_methods z_methods ;
String phone , uid ,imei ;
   ImageView logoimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_splash);
        logoimg = findViewById(R.id.logoimg);
        myuserData = new Myuserdata(this);
        myuserData.setEventSignedin(new EventSignedin() {
            @Override
            public void SaginedinEmail(String id) {
                myuserData.setProgresstitle("جاري تحميل البيانات");

               uid = id ;
                loadloction(id);
            }

            @Override
            public void SaginedinEmailFialed() {
              //  myuserData.Dismessprogress();;
                Intent i  = new Intent();
                i.putExtra("phone" , phone);
                z_methods.GOTo_Activity(Act_virifay.class , i  ,  R.anim.goin_tolift , R.anim.goout_tolift);

            }
        });
z_methods = new Z_methods(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    z_methods.Animation(R.anim.godilogin , logoimg);
    loading();
    }

    void loadloction (String id ){
         z_methods.getIAME();

        try {



            myuserData.loadData(id ) .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                         imei = documentSnapshot.getString(myuserData.DeviecIMEI.getName()) + "";

            int         jobstate    = Integer.parseInt( documentSnapshot.get(myuserData.CelluserJob.getName() ) + "");
                    myuserData.setUserSavesinShareprephrance_int("job" , jobstate);

                    z_methods = new Z_methods(Act_splash.this );
                    String eeim = z_methods.getIAME();
                    if (! imei.equals(eeim)){


                        Intent i = new Intent();
                        i.putExtra("phone" , phone);
                        i.putExtra("isinsert" , false);
                        i.putExtra("id" , uid  );
                        z_methods.GOTo_Activity(Act_virifay.class , i  ,  R.anim.goin_tolift , R.anim.goout_tolift);



                    }  else  {
z_methods.setFinishActivity(true);
                        z_methods.GOTo_Activity(Act_Mainscreen.class,R.anim.goin_tolift , R.anim.goout_tolift);

                    }}
            });


        }catch (Exception e){z_methods.GOTo_Activity(Act_login.class);}
    }


    void loading (){
        Z_methods methods = new Z_methods(this);

        int stat =     myuserData.CheckSigneUpstatus();
        if (stat == myuserData.USER_LOGIN_STATE_non){
            methods.GOTo_Activity(Act_login.class , R.anim.godilogin , 0 );

        } else {
            phone  =myuserData.getusersave_String("phone" , "");

            myuserData.login(phone);

        }


    }

}
