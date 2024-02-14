package com.z_apps.Helteycare.Project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;

import com.z_apps.Helteycare.Project.Classes.Myuserdata;
import com.z_apps.Helteycare.R;
import com.z_apps.z_toolslib.ZTools.Tools.Z_methods;

public class Act_Doctor extends AppCompatActivity {
    EditText nameext , phonetxt ;
EditText SearchExt ;
    Myuserdata myuserdata ;
    RecyclerView recyclerView ;
int pastor ;

double lt1 , lt2 , ln1 , ln2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__doctor);


        nameext = findViewById(R.id.etxtname);
        phonetxt = findViewById(R.id.etxtphone);
SearchExt = findViewById(R.id.searchEtxt);
        myuserdata = new Myuserdata(this);
        recyclerView = findViewById(R.id.rec);
pastor = getIntent().getIntExtra("pastor" , 0 );
lt1 = getIntent().getDoubleExtra("lt1" , 0 );
        lt2 = getIntent().getDoubleExtra("lt1" , 0 );
        ln1 = getIntent().getDoubleExtra("ln1" , 0 );
        ln2 = getIntent().getDoubleExtra("ln2" , 0 );

        myuserdata.loadmappoints_doctor(lt1 , lt2 , ln1 , ln2 ,  recyclerView);

    }

    public void Go_detal(View view) {

String id = myuserdata.uiddata ;
        Intent i = new Intent(this , Act_pationDetails.class);
        i.putExtra("uid" , id);
i.putExtra("pastor" , pastor);
        startActivity(i);
    }

    public void Go_Search(View view) {

String phone = SearchExt.getText() + "";
        myuserdata.Loaddoctor_SearchNomberpone(recyclerView , phone);

    }
}
