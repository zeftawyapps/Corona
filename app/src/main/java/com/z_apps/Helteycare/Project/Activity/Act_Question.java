package com.z_apps.Helteycare.Project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.z_apps.Helteycare.Project.Classes.Myuserdata;
import com.z_apps.Helteycare.Project.Classes.Questions;
import com.z_apps.Helteycare.R;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.EventdbmissoncomplieteFirestore;
import com.z_apps.z_toolslib.ZTools.Tools.Z_methods;

public class Act_Question extends AppCompatActivity {
RecyclerView recyclerView ;

Questions q ;
String uid ;
Myuserdata myuserdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__question);

    recyclerView = findViewById(R.id.rec);

    q = new Questions(R.layout.item_qustion , this);
    q.load(recyclerView );

    myuserdata = new Myuserdata(this);
    uid = getIntent().getStringExtra("uid");

   myuserdata.setEventdbmissoncomplieteFirestore(new EventdbmissoncomplieteFirestore() {
       @Override
       public DocumentReference messionComplete(DocumentReference dr) {

          myuserdata.Dismessprogress();

           Z_methods z_methods = new Z_methods(Act_Question.this);
           z_methods.GOTo_Activity(Act_Mainscreen.class,R.anim.goin_tolift , R.anim.goout_tolift);
           return null;

       }
   });
    }

    public void Go_enter(View view) {
        myuserdata.setProgresstitle("جاري تسجيل البيانات");
myuserdata.Showprogress();
        myuserdata.setAnsers(uid  , q.Quez);
    }

    @Override
    public void onBackPressed() {

        Toast.makeText(this , "يجب الاجابة على الاسئله" , Toast.LENGTH_LONG).show();
    }
}
