package com.z_apps.Helteycare.Project.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.z_apps.Helteycare.Project.Act_Web;
import com.z_apps.Helteycare.Project.Classes.Myuserdata;
import com.z_apps.Helteycare.Project.Classes.SuberAsmin;
import com.z_apps.Helteycare.Project.Classes.Z_Recyclermenue;
import com.z_apps.Helteycare.R;
import com.z_apps.z_toolslib.ZTools.DB.Cell;
import com.z_apps.z_toolslib.ZTools.DB.EventContanterintmCliked;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.EventSignedin;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.EventdbmissoncomplieteFirestore;
import com.z_apps.z_toolslib.ZTools.Tools.Z_Loction;
import com.z_apps.z_toolslib.ZTools.Tools.Z_methods;

import java.io.IOException;
import java.util.ArrayList;

public class Act_Mainscreen extends AppCompatActivity
implements OnMapReadyCallback

{
    private GoogleMap mMap;
    MapView mapView ;
    SupportMapFragment mapFragment ;
    Myuserdata myuserData , myuserdata1;
    String phone;
    Z_methods z_methods ;
    double longe , lat;
String uid ;
int jobstate ;
Z_Loction z_loction ;
RecyclerView recyclerView ;
String uriEhsaa = "http://acina.dz/covid19/?fbclid=IwAR2VWraRX9NgvtcOg6RaguaAqXApaZhYkWlMrTSDSi3Rsasnm13oXPRggVk";
String privacy = "https://ahmednailiinnima.blogspot.com/p/blog-page.html";
  String wezara = "http://covid19.sante.gov.dz/?fbclid=IwAR1zxZAHZ6PshCTPs8bxKqBl7zWqsC9jTzwzUpff8K9UoteZBGtdLohupGc";
Z_Recyclermenue recyclermenue ;
boolean isload;
    double lt1 , lt2 , ln1 , ln2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__mainscreen2);

        mapFragment   = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

myuserData = new Myuserdata(this);
        myuserdata1 = new  Myuserdata(this);
z_methods = new Z_methods(this);

  myuserData.setEventSignedin(new EventSignedin() {
      @Override
      public void SaginedinEmail(String id) {
          myuserData.setProgresstitle("جاري تحميل البيانات");
          Toast.makeText(Act_Mainscreen.this , "مرحبا بك " , Toast.LENGTH_SHORT).show();

          uid = id ;
          loadloction(id);
      }

      @Override
      public void SaginedinEmailFialed() {
          myuserData.Dismessprogress();;
          Intent i  = new Intent();
          i.putExtra("phone" , phone);
          z_methods.GOTo_Activity(Act_virifay.class , i  ,  R.anim.goin_tolift , R.anim.goout_tolift);

      }
  });

  z_loction = new Z_Loction(this);
  recyclerView = findViewById(R.id.rec);
        recyclermenue = new Z_Recyclermenue(R.layout.item_mainmenue , this ,recyclerView );


 recyclermenue.setEventcontarers(new EventContanterintmCliked() {
     @Override
     public void ItemCleicked(View v, Cell[] Cells) {
         int id = Integer.parseInt(Cells[0].getValue()+ "");

     switch ( id) {

         case  R.id.action_settings:
             Intent i = new Intent();
             i.putExtra("uid" , uid  );
             z_methods.GOTo_Activity(Act_setting.class , i  );

             break;

         case  R.id.nav_Quistoin:
             Intent inet = new Intent();
             inet.putExtra("uid" , uid  );
             z_methods.GOTo_Activity(Act_Question.class , inet  );

             break;
         case  R.id.nav_slideshow:

             Intent ins = new Intent();

             ins.putExtra("uri" , wezara);
               z_methods.GoTo_OtherApps(wezara);
           //  z_methods.GOTo_Activity(Act_Web.class , ins );

             break;
         case  R.id.nav_stat:
             Intent idd = new Intent();

             idd.putExtra("uri" , uriEhsaa);
           //  z_methods.GOTo_Activity(Act_Web.class , idd );
            z_methods.GoTo_OtherApps(uriEhsaa);
             break;
         case  R.id.nav_tools:
             Intent idds = new Intent();

             idds.putExtra("uri" , privacy);
             z_methods.GOTo_Activity(Act_Web.class , idds );

             break;
         case  R.id.nav_jobstat:
             z_methods.GOTo_Activity(Act_userjob.class );

             break;
         case  R.id.nav_pationstat:
             Intent idsa = new Intent();

              idsa.putExtra("lt1" , lt1 ) ;
             idsa.putExtra("lt2" , lt2 ) ;
             idsa.putExtra("ln1" , ln1 ) ;
             idsa.putExtra("ln2" , ln2 ) ;

             idsa . putExtra("pastor" , pastor);

             z_methods.GOTo_Activity(Act_Doctor.class , idsa);

             break;


     }

     }
 });
      }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        SuberAsmin suberAsmin = new SuberAsmin();

        if (suberAsmin.isAdmin(phone)){

            Toast.makeText(this , "سوبر ادمن" , Toast.LENGTH_LONG  ).show();
            jobstate = 5 ;
        }else {


            jobstate = myuserData.getusersave_int("job", 0);
        }  if (jobstate == 5 ){

          getMenuInflater().inflate(R.menu.act__mainscreenadmin, menu);

      }else if (jobstate == 4) {
          getMenuInflater().inflate(R.menu.act__mainscreenadocotor, menu);


      }else {

          getMenuInflater().inflate(R.menu.act__mainscreen, menu);
      }
      
recyclermenue.setMenu(menu);

      return recyclermenue.loadmenu();
    }




    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        SuberAsmin suberAsmin = new SuberAsmin();

        if (suberAsmin.isAdmin(phone)){

            Toast.makeText(this , "سوبر ادمن" , Toast.LENGTH_LONG  ).show();
            jobstate = 5 ;
        }else {


            jobstate = myuserData.getusersave_int("job", 0);
        }  if (jobstate == 5 ){

            getMenuInflater().inflate(R.menu.act__mainscreenadmin, menu);

        }else if (jobstate == 4) {
            getMenuInflater().inflate(R.menu.act__mainscreenadocotor, menu);


        }else {

            getMenuInflater().inflate(R.menu.act__mainscreen, menu);
        }

        recyclermenue.setMenu(menu);

        return recyclermenue.loadmenu();

    }

int pastor = 0 ;
      @Override
    protected void onStart() {


        super.onStart();
        loading();

    }
    String imei ;
    void loadloction ( String id ){
        mMap.clear();
        z_methods.getIAME();

try {



        myuserData.loadData(id ) .addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                myuserData.Dismessprogress();
                myuserData.setLatat(documentSnapshot.getDouble(myuserData.CelluserLoction_lat.getName()));
                myuserData.setLongat(documentSnapshot.getDouble(myuserData.CelluserLoction_long.getName()));
                jobstate    = Integer.parseInt( documentSnapshot.get(myuserData.CelluserJob.getName() ) + "");
   imei = documentSnapshot.getString(myuserData.DeviecIMEI.getName()) + "";


z_methods = new Z_methods(Act_Mainscreen.this);
String eeim = z_methods.getIAME();
             if (imei.equals(eeim)){

               if (jobstate == 4 ){

                   pastor    = Integer.parseInt( documentSnapshot.get(myuserData.pastore.getName() ) + "");


               }
                myuserData.setUserSavesinShareprephrance_int("job" , jobstate);
// forTestData();
                mapFragment.getMapAsync(Act_Mainscreen.this);
                z_loction.getpoints(myuserData.getLatat() , myuserData.getLongat() , 10000);

                myuserdata1.loadmappoints(z_loction.getLatp1() , z_loction.getLatp3() ,
                        z_loction.getLongp2() , z_loction .getLongp4());


        lt1 = z_loction.getLatp1();
        lt2 =  z_loction.getLatp3();
        ln1  = z_loction.getLongp2() ;
        ln2 = z_loction .getLongp4() ;
             }else {
                 Intent i = new Intent();
                 i.putExtra("phone" , phone);
                 i.putExtra("isinsert" , false);
 i.putExtra("id" , uid  );
                 z_methods.GOTo_Activity(Act_virifay.class , i  ,  R.anim.goin_tolift , R.anim.goout_tolift);



             } }
        });
isload = true;

}catch (Exception e){z_methods.GOTo_Activity(Act_login.class);}
    }

    void getloadloction (  ){
        mMap.clear();

setLoaction();
      myuserData.setEventdbmissoncomplieteFirestore(new EventdbmissoncomplieteFirestore() {
          @Override
          public DocumentReference messionComplete(DocumentReference dr) {
              mMap.clear();

              myuserdata1.loadmappoints(z_loction.getLatp1() , z_loction.getLatp3() ,
                      z_loction.getLongp2() , z_loction .getLongp4());

              return null;
          }
      });
    }


    void  setLoaction(){
        z_loction.getLocation_andPermssion();

        myuserData.setProgresstitle("جاري تحديد الموقع");
        myuserData.Showprogress();
        z_loction.getLocation_andPermssion();
        CountDownTimer timer = new CountDownTimer(2000 , 2000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                myuserData.Dismessprogress();
             z_loction.getMYlocation();
                if (z_loction.getMYlocation() == null    ){
                    z_loction.onNullLocation();
                    Toast.makeText(Act_Mainscreen.this , "للاسف لم يتم تحديد الموقع رجاء التاكد من فتح  GPS" , Toast.LENGTH_SHORT).show();
                }else {

                    try {
                        String addres = z_loction.getAddressfromLocation();

                        z_loction.getpoints(z_loction.getMYlocation().getLatitude() , z_loction.getMYlocation().getLongitude() , 10000);



                        double longe , late  ;
                        longe = z_loction.getMYlocation().getLongitude();
                        late = z_loction.getMYlocation().getLatitude();
                        myuserData.UpdateLoction(uid , addres , longe , late );



                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.start() ;
    }


    void loading (){
        Z_methods methods = new Z_methods(this);

         int stat =     myuserData.CheckSigneUpstatus();
        if (stat == myuserData.USER_LOGIN_STATE_non){
            methods.GOTo_Activity(Act_login.class , R.anim.godilogin , 0 );

        } else {
            phone  =myuserData.getusersave_String("phone" , "");
            myuserData.setProgresstitle("جار تسجيل الدخول");
            myuserData.Showprogress();
            myuserData.login(phone);

        }


    }


    public void Go_Q(MenuItem item) {
        Intent i = new Intent();
        i.putExtra("uid" , uid  );
        z_methods.GOTo_Activity(Act_Question.class , i  );

    }

    public void Go_data(MenuItem item)
    {
        Intent i = new Intent();

        i.putExtra("uri" , wezara);
      //  z_methods.GoTo_OtherApps(wezara);
        z_methods.GOTo_Activity(Act_Web.class , i );
    }

    public void Go_priv(MenuItem item) {
        Intent i = new Intent();

        i.putExtra("uri" , privacy);
      //  z_methods.GoTo_OtherApps(privacy);
        z_methods.GOTo_Activity(Act_Web.class , i );

    }

    public void Go_send(MenuItem item) {
    }

    public void Go_setting(MenuItem item) {

        Intent i = new Intent();
        i.putExtra("uid" , uid  );
        z_methods.GOTo_Activity(Act_setting.class , i  );

    }

    public void Go_jobstat(MenuItem item) {
        z_methods.GOTo_Activity(Act_userjob.class );
    }

    public void Go_pationstat(MenuItem item) {


        z_methods.GOTo_Activity(Act_Doctor.class   );

    }


    public  void forTestData(){

        myuserData.AddDataForTest(   "محمد" , "01121131403" , myuserData.getLongat()+0.000005 , myuserData .getLatat() + 0.00004);


        myuserData.AddDataForTest(   "خالد" , "01120071403" , myuserData.getLongat() -0.000005  , myuserData .getLatat() - +0.000005 );


        myuserData.AddDataForTest(   "محمود" , "01120031503" , myuserData.getLongat() +0.000003 , myuserData .getLatat() +0.000005 );


        myuserData.AddDataForTest(   "رياض" , "01120131403" , myuserData.getLongat() -0.000005 , myuserData .getLatat() - +0.000007 );

    }

    public void Go_sdata(MenuItem item) {
        Intent i = new Intent();

        i.putExtra("uri" , uriEhsaa);
        z_methods.GoTo_OtherApps(uriEhsaa);
    }
boolean isopend = false;
    public void Go_menu(View view) {
       menurul();
    }

    void menurul(){
        if (!isload){return;}
        if (isopend){
            recyclerView.setVisibility(View.GONE);
            isopend = false ; }else {
            recyclerView.setVisibility(View.VISIBLE);
            recyclermenue.loadmenu();
            isopend = true ; }

    }

    public void Go_home(View view) {
        getloadloction();
    }

    public void Go_home(MenuItem item) {
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.clear();
        mMap.setMinZoomPreference(13);

        // Add a marker in Sydney and move the camera
        final LatLng ny = new LatLng(myuserData.getLatat(), myuserData.getLongat());


        myuserdata1.Eventloctionload = new Myuserdata.loadloactions() {
            @Override
            public void loadevent(ArrayList<MarkerOptions> loc) {
                int i = 0;
                while (i < loc.size()) {

                    mMap.addMarker(loc.get(i));

                    i++;

                }
                mMap.moveCamera(CameraUpdateFactory.newLatLng(ny));


            }
        };
    }


 }
