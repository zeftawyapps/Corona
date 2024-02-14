package com.z_apps.Helteycare.Project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.z_apps.Helteycare.Project.Classes.Myuserdata;
import com.z_apps.Helteycare.R;
import com.z_apps.z_toolslib.ZTools.Tools.Z_methods;

import java.lang.ref.PhantomReference;

public class Act_virifay extends AppCompatActivity {
String phone ;
Myuserdata myuserdata ;
EditText smsEtxt ;
boolean isinsert ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_virifay);
smsEtxt = findViewById(R.id.smsEtxt);
   phone = getIntent().getStringExtra("phone");
   isinsert = getIntent().getBooleanExtra("isinsert" , true);
   myuserdata = new Myuserdata(this);

  if (isinsert) {
      myuserdata.Viriyfay(phone, smsEtxt);
  }else {

      myuserdata.ViriyfayForsureing( getIntent().getStringExtra("id"),  phone, smsEtxt);

  }}

    public void Go_viriy(View view) {
    }

    @Override
    public void onBackPressed() {
         Z_methods z_methods = new Z_methods(this);
        myuserdata.setUserSavesinShareprephrance_int(myuserdata . USER_LOGIN_STATE , myuserdata.USER_LOGIN_STATE_non);

        z_methods.GO_Out();
    }
}
