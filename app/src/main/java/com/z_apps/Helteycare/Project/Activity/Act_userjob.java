package com.z_apps.Helteycare.Project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.z_apps.Helteycare.Project.Classes.Myuserdata;
import com.z_apps.Helteycare.R;
import com.z_apps.z_toolslib.ZTools.Tools.Z_methods;

public class Act_userjob extends AppCompatActivity {
EditText nameext , phonetxt ;

Myuserdata myuserdata ;
RecyclerView recyclerView ;

    EditText SearchExt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_userjob);

   nameext = findViewById(R.id.etxtname);
   phonetxt = findViewById(R.id.etxtphone);
SearchExt = findViewById(R.id.searchEtxt);
    myuserdata = new Myuserdata(this);
    recyclerView = findViewById(R.id.rec);
    myuserdata.LoadAdminUsers(recyclerView);
    }

    public void Go_blcck(View view) {
        myuserdata.Updatejobstate(myuserdata.uiddata , myuserdata.JOBSTATEBLOCKED);
        myuserdata.LoadAdminUsers(recyclerView);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_call,menu);

        return super.onCreateOptionsMenu(menu);

    }

    public void Go_doctorreq(View view) {



    }

    public void Go_admin(View view) {
        myuserdata.Updatejobstate(myuserdata.uiddata , myuserdata.JOBSTATEADMIN);
        myuserdata.LoadAdminUsers(recyclerView);

    }

    public void Go_call(MenuItem item) {

      String pho = phonetxt.getText() + "";

      if (pho.equals("")){
          Toast.makeText(this , "يجب اختيار المستخدم اولا" , Toast.LENGTH_LONG).show();

          return;}
        Z_methods z_methods  = new Z_methods(this);


        z_methods.CallPhone(phonetxt.getText() + "");
    }
    public void Go_call_Whats(MenuItem item) {
        Z_methods z_methods  = new Z_methods(this);

        String phone = myuserdata.codestate(phonetxt.getText() + "") +        phonetxt.getText() + "";
        z_methods.WhatsappsendDirect(phone , "");    }



    public void Go_call_viber(MenuItem item) {
        Z_methods z_methods  = new Z_methods(this);

        String phone = myuserdata.codestate( phonetxt.getText() + "") +        phonetxt.getText() + "";
        z_methods.sendToViber(phone , "");    }

    public void Go_Search(View view) {
        myuserdata.LoadAdminUsers_Search(recyclerView , SearchExt.getText() + "");

    }

    public void Go_doctpation(View view) {
        myuserdata.UpdatejobstateDoctor(myuserdata.uiddata , 2);
        myuserdata.LoadAdminUsers(recyclerView);

    }

    public void Go_doctorreqbast(View view) {

        myuserdata.UpdatejobstateDoctor(myuserdata.uiddata ,1);
        myuserdata.LoadAdminUsers(recyclerView);

    }

    public void Go_pation(View view) {
        myuserdata.Updatejobstate(myuserdata.uiddata , myuserdata.JOBSTATEPATIONT);
        myuserdata.LoadAdminUsers(recyclerView);

    }
}
