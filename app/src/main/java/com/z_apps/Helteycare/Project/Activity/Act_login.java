package com.z_apps.Helteycare.Project.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.z_apps.Helteycare.Project.Classes.Myuserdata;
import com.z_apps.Helteycare.R;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.EventSignedin;
import com.z_apps.z_toolslib.ZTools.Tools.Z_methods;

public class Act_login extends AppCompatActivity {
Myuserdata myuserdata ;
EditText phoneEtxt ;
String phone ;
Z_methods z_methods ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_login);

   myuserdata = new Myuserdata(this);
   phoneEtxt = findViewById(R.id.etxtphone) ;
   z_methods = new Z_methods(this);
        z_methods.getIAME();

   myuserdata.setEventSignedin(new EventSignedin() {
       @Override
       public void SaginedinEmail(String id) {
myuserdata.Dismessprogress();
          myuserdata.setUserSavesinShareprephrance_int(myuserdata.USER_LOGIN_STATE , myuserdata.USER_LOGIN_STATE_Email);
           myuserdata.setUserSavesinShareprephrance_String(myuserdata.USER_ID , id );


           Intent i = new Intent();
i.putExtra("uid" , id );
z_methods.setFinishActivity(true);
         //  z_methods.GOTo_Activity(Act_Question.class  ,i ,  R.anim.goin_tolift , R.anim.goout_tolift);
          // z_methods.GOTo_Activity(Act_Mainscreen.class,R.anim.goin_tolift , R.anim.goout_tolift);
 loadloction(id);
       }
       void loadloction (final String id ){
           z_methods.getIAME();

           try {



               myuserdata   .loadData(id ) .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                   @Override
                   public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
               String         imei = documentSnapshot.getString(myuserdata.DeviecIMEI.getName()) + "";

                       int         jobstate    = Integer.parseInt( documentSnapshot.get(myuserdata.CelluserJob.getName() ) + "");
                       myuserdata.setUserSavesinShareprephrance_int("job" , jobstate);

                       z_methods = new Z_methods(Act_login.this );
                       String eeim = z_methods.getIAME();
                       if (! imei.equals(eeim)){


                           Intent i = new Intent();
                           i.putExtra("phone" , phone);
                           i.putExtra("isinsert" , false);
                           i.putExtra("id" , id  );
                           z_methods.GOTo_Activity(Act_virifay.class , i  ,  R.anim.goin_tolift , R.anim.goout_tolift);



                       }  else  {
                           z_methods.setFinishActivity(true);
                           z_methods.GOTo_Activity(Act_Mainscreen.class,R.anim.goin_tolift , R.anim.goout_tolift);

                       }}
               });


           }catch (Exception e){z_methods.GOTo_Activity(Act_login.class);}
       }

       @Override
       public void SaginedinEmailFialed() {
           myuserdata.Dismessprogress();
           Intent i  = new Intent();
           i.putExtra("phone" , phone);
           i.putExtra("isinsert" , true);
           z_methods.setFinishActivity(true);

           z_methods.GOTo_Activity(Act_virifay.class , i  ,  R.anim.goin_tolift , R.anim.goout_tolift);
       }
   });



    }


    public void Go_login(View view) {

        phone = phoneEtxt.getText() + "";
        if (phone.equals("")) {
            phoneEtxt.setError("يجب ادخال رقم الهاتف");
            return;
        }

        if (myuserdata.codestate == 0) {
            if (phone.length() == 11) {
                myuserdata.setProgresstitle("جاري تسجيل الدخول");
                myuserdata.Showprogress();
                myuserdata.login(phone);
            }

        } else if (phone.length() == 10|| phone.length() == 11) {
            myuserdata.setProgresstitle("جاري تسجيل الدخول");
            myuserdata.Showprogress();
            myuserdata.login(phone);

        }
else {
            phoneEtxt.setError("يجب ادخال رقم الهاتف كاملا مع وضع 0 اول الرقم");
        }
        }
    @Override
    public void onBackPressed() {
        Z_methods z_methods = new Z_methods(this);
        myuserdata.setUserSavesinShareprephrance_int(myuserdata . USER_LOGIN_STATE , myuserdata.USER_LOGIN_STATE_non);

        z_methods.GO_Out();
    }



}

