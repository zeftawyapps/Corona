package com.z_apps.Helteycare.Project.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.z_apps.Helteycare.Project.Classes.Myuserdata;
import com.z_apps.Helteycare.Project.Classes.Questions;
import com.z_apps.Helteycare.R;
import com.z_apps.z_toolslib.ZTools.Tools.Z_methods;

public class Act_pationDetails extends AppCompatActivity {
Myuserdata myuserdata ;
TextView natxt , phtxt ;
Questions q ;
String id ;
RecyclerView recyclerView ;
ImageView imageView ;
int pastor;
Button surbtn , mybebtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_pation_details);
    myuserdata = new Myuserdata(this);
    q = new Questions(R.layout.item_qustion , this);
  id = getIntent().getStringExtra("uid");
  pastor = getIntent().getIntExtra("pastor", 2 );
recyclerView = findViewById(R.id.rec);
natxt = findViewById(R.id.nametxt);
phtxt = findViewById(R.id.phonetxt);
surbtn = findViewById(R.id.surbtn);

        mybebtn = findViewById(R.id.mybbtn);

        imageView = findViewById(R.id.statimg);


        if (pastor == 2 ){

            mybebtn.setVisibility(View.VISIBLE);
            surbtn.setVisibility(View.INVISIBLE);

        }else {

            mybebtn.setVisibility(View.INVISIBLE);
            surbtn.setVisibility(View.VISIBLE);


        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_call,menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    protected void onStart() {
        super.onStart();

myuserdata.getdesDetails(id ,q ,  recyclerView , natxt , phtxt);
    }

    public void Go_nice(View view) {
        myuserdata.Updateptiontstate(id , myuserdata.STATNICE);
        Toast.makeText(this,  "حالة المريض انه سليم" , Toast.LENGTH_LONG).show();
        imageView.setImageResource(R.drawable.loc5);

    }

    public void Go_mybe(View view) {

        myuserdata.Updateptiontstate(id , myuserdata.STATMYBE);
        Toast.makeText(this,  "حالة المريض انه مشتبه" , Toast.LENGTH_LONG).show();
imageView.setImageResource(R.drawable.loc7);
    }

    public void Go_sure(View view) {

        myuserdata.Updateptiontstate(id , myuserdata.STATSURE);
        Toast.makeText(this,  "حالة المريض انه مصاب" , Toast.LENGTH_LONG).show();
        imageView.setImageResource(R.drawable.loc);

    }

    public void Go_call(MenuItem item) {
        String pho = phtxt.getText() + "";

        if (pho.equals("")){
            Toast.makeText(this , "يجب اختيار المستخدم اولا" , Toast.LENGTH_LONG).show();

            return;}
        Z_methods z_methods  = new Z_methods(this);


        z_methods.CallPhone(phtxt.getText() + "");    }

    public void Go_call_Whats(MenuItem item) {
        Z_methods z_methods  = new Z_methods(this);

 String phone = myuserdata.codestate(phtxt.getText() + "") +        phtxt.getText() + "";
        z_methods.WhatsappsendDirect(phone , "");    }



    public void Go_call_viber(MenuItem item) {
        Z_methods z_methods  = new Z_methods(this);

        String phone = myuserdata.codestate(phtxt.getText() + "") +        phtxt.getText() + "";
        z_methods.sendToViber(phone , "");    }

}

