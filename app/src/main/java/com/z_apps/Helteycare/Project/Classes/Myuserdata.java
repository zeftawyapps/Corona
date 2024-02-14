package com.z_apps.Helteycare.Project.Classes;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.location.Geocoder;
import android.location.Location;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.z_apps.Helteycare.Project.Activity.Act_Mainscreen;
import com.z_apps.Helteycare.Project.Activity.Act_signUp;
import com.z_apps.Helteycare.Project.Activity.Act_virifay;
import com.z_apps.Helteycare.R;
import com.z_apps.z_toolslib.ZTools.DB.Z_DataTable;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.AddnewFeactuerAdaptor;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.Cell;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.EventContanterintmCliked;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.EventLoadimg;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.Eventdbmissoncompliete;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.EventsLoadData;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.FireBaseUserData;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.Inputs;
import com.z_apps.z_toolslib.ZTools.FierbaseDB.Row;
import com.z_apps.z_toolslib.ZTools.Tools.Z_DateTime;
import com.z_apps.z_toolslib.ZTools.Tools.Z_methods;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class Myuserdata extends FireBaseUserData {
    public  int codestate = 1;

    String email ;
    String pass;

    Cell Q1 = new Cell("Q1");
    Cell Q2 = new Cell("Q2");
    Cell Q3 = new Cell("Q3");
    Cell Q4 = new Cell("Q4");
    Cell Q5 = new Cell("Q5");
    Cell Q6 = new Cell("Q6");
    Cell uid = new Cell("uid");
    Cell sum = new Cell("sum");
    Cell pationstat = new Cell("Pstate");
    public  Cell DeviecIMEI = new Cell("IMEI");
    public Cell pastore = new Cell("pastor");
    public Cell ConnectionDate = new Cell("ConnectionDate");


    public  String uiddata ;
    public  int Quez[]  = new int[6];
    public  void setAnsers(String id,  int [] Ques    ){
        Quez = Ques ;
        Q1.setValue(Ques[0]);
        Q2.setValue(Ques[1]);
        Q3.setValue(Ques[2]);
        Q4.setValue(Ques[3]);
        Q5.setValue(Ques[4]);
        Q6.setValue(Ques[5]);
        CelluserJob.setValue(1);

        uid.setValue(id);
        sum.setValue(sum () );


       long time = getConnectionDate(0);
        ConnectionDate.setValue(time);

if (sum() > 3 ){pationstat.setValue(STATMYBE);}else {pationstat.setValue(STATNICE);}
        setCell(new Cell[]{Q1 , Q2 , Q3 , Q4 , Q5 , Q6 , uid , sum  , CelluserJob , pationstat , ConnectionDate});
        updateFirestore(id);
    }

    int sum (){
        int ii  = 0 ;
        int i = 0  ;
        while (i<6){
            ii = ii + Quez[i];
            i++ ;
        }
        return  ii;
    }

public  long getConnectionDate(int day){

    Z_DateTime z_dateTime =new Z_DateTime() ;

    z_dateTime.getCalendar().set(Calendar.HOUR_OF_DAY , 0);
    z_dateTime.getCalendar().set(Calendar.MINUTE , 0);

    z_dateTime.getCalendar().add(Calendar.DAY_OF_MONTH , day );
 return z_dateTime.getCalendar().getTimeInMillis() ;


}
    public  final int   JOBSTATESIGHUP = 0 ;
    public  final int   JOBSTATEANSSER = 1 ;
    public  final int   JOBSTATEPATIONT = 2 ;
    public  final int   JOBSTATDOCTORREQUST = 3 ;
    public  final int   JOBSTATEDOCTOR = 4 ;
    public  final int   JOBSTATEADMIN = 5 ;
    public  final int   JOBSTATEBLOCKED = 6 ;


    public  final int   STATNICE = 10 ;
    public  final int   STATMYBE = 20 ;
    public  final int   STATSURE = 30 ;

public  String codestate(String Phone ){
    if (Phone.length() == 11 ){return "+2"; }else {return  "+213"; }


}
    public Myuserdata(Context context) {
        super(context);
        setFirestorCollectionName("UserData");

    }

    public  void login(String phone){

          email = CreatEmail(phone);

          pass = phone ;

          setUserSavesinShareprephrance_String("phone" , phone);
          SigninuseEmailandpass(email , pass );

    }




public  void signeUp(String name , String phone , String addres , Location location){

        double longe = location.getLongitude();
        double lat = location.getLatitude() ;
        CelluserLoction_lat.setValue(lat);
        CelluserLoction_long.setValue(longe);
        CelluserAddres.setValue(addres);
        Celluserphone.setValue(phone);
    email = CreatEmail(phone);
    CelluserJob.setValue(0);
    Z_methods z_methods = new Z_methods(getActivity());
DeviecIMEI.setValue(z_methods.getIAME());
    pass = phone ;
    setCanUseSaveshareprefrance(true);
    CreatAccoumtAuthWithEmailfirestore(  name ,  email , pass , new  Cell[] {CelluserLoction_long , CelluserLoction_lat ,DeviecIMEI ,  CelluserAddres, Celluserphone , CelluserJob});
}



    public  void Update(String uid ,  String name , String phone , String addres , double lon , double lat){
          CelluserLoction_lat.setValue(lat);
        CelluserLoction_long.setValue(lon);
        CelluserAddres.setValue(addres);
 Cellusername.setValue(name);
        setCell(new Cell[]{Cellusername ,

        CelluserLoction_long , CelluserLoction_lat , CelluserAddres});
        updateFirestore(uid);
        }


    public  void UpdateLoction(String uid ,   String addres , double lon , double lat){
        CelluserLoction_lat.setValue(lat);
        CelluserLoction_long.setValue(lon);
        CelluserAddres.setValue(addres);
         setCell(new Cell[]{

                CelluserLoction_long , CelluserLoction_lat , CelluserAddres});
        updateFirestore(uid);
    }

    public  void Updatejobstate(String uid ,int state  ){

       if(uid == null){Toast.makeText(getContext() , "يجب اختيار المستخدم اولا" , Toast.LENGTH_LONG).show(); return;}
        CelluserJob.setValue(state);
        setCell(new Cell[]{CelluserJob});


        updateFirestore(uid);
    }


    public  void UpdatejobstateDoctor(String uid ,int bastor  ){

        if(uid == null){Toast.makeText(getContext() , "يجب اختيار المستخدم اولا" , Toast.LENGTH_LONG).show(); return;}
        CelluserJob.setValue( JOBSTATEDOCTOR) ;
        pastore.setValue(bastor);
        setCell(new Cell[]{CelluserJob , pastore});


        updateFirestore(uid);
    }
    public  void UpdateIMEI(String uid ,String imei ){

         CelluserJob.setValue( JOBSTATEDOCTOR) ;
        DeviecIMEI.setValue(imei);
        setCell(new Cell[]{DeviecIMEI});


        updateFirestore(uid);
    }


    public  void Updateptiontstate(String uid ,int state  ){
        pationstat.setValue(state);
        Z_DateTime z_dateTime =new Z_DateTime() ;

        z_dateTime.getCalendar().set(Calendar.HOUR_OF_DAY , 0);
        z_dateTime.getCalendar().set(Calendar.MINUTE , 0);
z_dateTime.getCalendar().add(Calendar.DAY_OF_MONTH , 3);
        long time = z_dateTime.getCalendar().getTimeInMillis() ;
ConnectionDate.setValue(time);
        setCell(new Cell[]{pationstat , ConnectionDate});


        updateFirestore(uid);
    }

    String CreatEmail(String phone ){

        return  "Cororna_"+phone +"@ZeftawyApps.com";

    }
public  void Viriyfay(final String phone , final EditText text ){
Showprogress();
    ViryfayAccountusephone(codestate (phone) + phone, new com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            setProgresstitle("جاري تاكيد الرقم");


            Z_methods z_methods = new Z_methods(getContext());
            z_methods.setFinishActivity(true);

            text.setText(phoneAuthCredential.getSmsCode() + "");

            Intent i  = new Intent();
            i.putExtra("phone" , phone);
            z_methods.GOTo_Activity(Act_signUp.class , i  ,  R.anim.goin_tolift , R.anim.goout_tolift);
Dismessprogress();
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

            Log.e("PHONEError" , e.getMessage());
            Toast.makeText(getContext() , "للاسف فشل ارسال رسالة تاكد من الرقم " , Toast.LENGTH_SHORT).show();
        }
    });
}



    public  void ViriyfayForsureing(final String id,  final String phone , final EditText text ){
        Showprogress();
        ViryfayAccountusephone(codestate (phone) + phone, new com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                setProgresstitle("جاري تاكيد الرقم");

                CountDownTimer timer = new CountDownTimer(10000 , 10000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
              String sms =           text.getText( ) + "";
                        if (sms.equals("") ){

Toast.makeText(getContext() , "رجاء ادخل بالرقم في شريحة هاتفك" , Toast.LENGTH_LONG).show();
                        Z_methods z_methods = new Z_methods(getContext());
z_methods.GO_Out();
                    }}
                };
                timer.start() ;
                final Z_methods z_methods = new Z_methods(getContext());
                text.setText(phoneAuthCredential.getSmsCode() + "");


                setEventdbmissoncompliete(new Eventdbmissoncompliete() {
                    @Override
                    public void messionComplete() {
                      z_methods.GOTo_Activity(Act_Mainscreen.class,R.anim.goin_tolift , R.anim.goout_tolift);
                    }
                });
                UpdateIMEI(id , z_methods.getIAME()  );

                      Dismessprogress();

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                Log.e("PHONEError" , e.getMessage());
                Toast.makeText(getContext() , "للاسف فشل ارسال رسالة تاكد من الرقم " , Toast.LENGTH_SHORT).show();
            }
        });
    }


public DocumentReference loadData(String id ){

        setProgresstitle("جاري تحميل البيانات");
Showprogress();
return  getUserValue(id );
}

public  void AddDataForTest(String name , String phone ,double longe , double lat  ){

   Cellusername.setValue(name);
   Celluserphone.setValue(phone);
    Q1.setValue(0);
    Q2.setValue(0);
    Q3.setValue(0);
    Q4.setValue(0);
    Q5.setValue(0);
    Q6.setValue(0);
    CelluserJob.setValue(1);
    CelluserLoction_lat.setValue(lat);
    CelluserLoction_long.setValue(longe);
     sum.setValue(sum () + "");

    setCell(new Cell[]{Q1 , Q2 , Q3 , Q4 , Q5 , Q6 , uid , sum  , CelluserJob
    ,Cellusername , Celluserphone , CelluserLoction_lat , CelluserLoction_long
    });

insertFirestore();
}


public void LoadAdminUsers(RecyclerView recyclerView ){
    Celluserphone.setR(R.id.nametxt);
    Cellusername.setR(R.id. phonetxt);

    Celluserphone.setRinput(R.id.etxtname);
    Cellusername.setRinput(R.id. etxtphone);
    Celluserphone.setInputs(Inputs.EditText);
    Cellusername.setInputs(Inputs.EditText);
setConentR(R.layout.activity_act_userjob);
    Celluserid.setID(true);
    setAddvew(new AddnewFeactuerAdaptor() {
        @Override
        public View Addview(View view, int poss, ArrayList<Row> Table) {
            ImageView imageView  =  view.findViewById(R.id.imagstate);
int state = Integer.parseInt( Table.get(poss).getvalue(CelluserJob) + "");
switch (state){


     case 3  :imageView.setImageResource(R.drawable.loc6); break;
    case 4 :
        imageView.setImageResource(R.drawable.loc9);break;
    case  5 :
        imageView.setImageResource(R.drawable.loc2);
        break;
    case  6 :
        imageView.setImageResource(R.drawable.loc8);
        break;

}
            return view ;
        }
    });

    setCell(new Cell[]{Celluserid ,  CelluserJob, Cellusername , Celluserphone });

    FirebaseFirestore query = FirebaseFirestore.getInstance();

    setFirestorQurery(query.collection(getFirestorCollectionName()).whereGreaterThan(CelluserJob.getName() , 2).get());
loadRoteanRecyclerviewFirestre(getFirestorQurery(),R.layout.item_jobs, recyclerView , GetRecyclervewlayoutManagerLinearVertecal() , null );
setEventcontarers(new EventContanterintmCliked() {
    @Override
    public void ItemCleicked(View view, Cell[] Cells) {

      uiddata = Cells[0].getValue() + "";
        setinputvalue(Cells);

    }
});
}

    public void LoadAdminUsers_Search(RecyclerView recyclerView , String phone  ){
        Celluserphone.setR(R.id.nametxt);
        Cellusername.setR(R.id. phonetxt);

        Celluserphone.setRinput(R.id.etxtname);
        Cellusername.setRinput(R.id. etxtphone);
        Celluserphone.setInputs(Inputs.EditText);
        Cellusername.setInputs(Inputs.EditText);
        setConentR(R.layout.activity_act_userjob);
        Celluserid.setID(true);
        setAddvew(new AddnewFeactuerAdaptor() {
            @Override
            public View Addview(View view, int poss, ArrayList<Row> Table) {
                ImageView imageView  =  view.findViewById(R.id.imagstate);
                int state = Integer.parseInt( Table.get(poss).getvalue(CelluserJob) + "");
                switch (state){


                    case 3  :imageView.setImageResource(R.drawable.loc6); break;
                    case 4 :
                        imageView.setImageResource(R.drawable.loc9);break;
                    case  5 :
                        imageView.setImageResource(R.drawable.loc2);
                        break;
                    case  6 :
                        imageView.setImageResource(R.drawable.loc8);
                        break;

                }
                return view ;
            }
        });

        setCell(new Cell[]{Celluserid ,  CelluserJob, Cellusername , Celluserphone });

        FirebaseFirestore query = FirebaseFirestore.getInstance();

        setFirestorQurery(query.collection(getFirestorCollectionName()).whereGreaterThan(CelluserJob.getName() , 2)
                .whereEqualTo(Celluserphone.getName() , phone)
                .get());
        loadRoteanRecyclerviewFirestre(getFirestorQurery(),R.layout.item_jobs, recyclerView , GetRecyclervewlayoutManagerLinearVertecal() , null );
        setEventcontarers(new EventContanterintmCliked() {
            @Override
            public void ItemCleicked(View view, Cell[] Cells) {

                uiddata = Cells[0].getValue() + "";
                setinputvalue(Cells);

            }
        });
    }

public void Loaddoctor(RecyclerView recyclerView ){
        Celluserphone.setR(R.id.nametxt);
        Cellusername.setR(R.id. phonetxt);

        Celluserphone.setRinput(R.id.etxtname);
        Cellusername.setRinput(R.id. etxtphone);
        Celluserphone.setInputs(Inputs.EditText);
        Cellusername.setInputs(Inputs.EditText);
        setConentR(R.layout.activity_act_userjob);
        Celluserid.setID(true);
        setAddvew(new AddnewFeactuerAdaptor() {
            @Override
            public View Addview(View view, int poss, ArrayList<Row> Table) {
                ImageView imageView  =  view.findViewById(R.id.imagstate);
                int state = Integer.parseInt( Table.get(poss).getvalue(sum) + "");
                switch (state){


                    case 3  :imageView.setImageResource(R.drawable.loc7); break;

                }
                return view ;
            }
        });

        setCell(new Cell[]{Celluserid ,  CelluserJob, Cellusername , Celluserphone ,sum});

        FirebaseFirestore query = FirebaseFirestore.getInstance();

        setFirestorQurery(query.collection(getFirestorCollectionName()).whereGreaterThanOrEqualTo(sum.getName() , 3).get());
        loadRoteanRecyclerviewFirestre(getFirestorQurery(),R.layout.item_jobs, recyclerView , GetRecyclervewlayoutManagerLinearVertecal() , null );
        setEventcontarers(new EventContanterintmCliked() {
            @Override
            public void ItemCleicked(View view, Cell[] Cells) {

                uiddata = Cells[0].getValue() + "";
                setinputvalue(Cells);

            }
        });
    }

    public void Loaddoctor_SearchNomberpone(RecyclerView recyclerView , String phone  ){
        Celluserphone.setR(R.id.nametxt);
        Cellusername.setR(R.id. phonetxt);

        Celluserphone.setRinput(R.id.etxtname);
        Cellusername.setRinput(R.id. etxtphone);
        Celluserphone.setInputs(Inputs.EditText);
        Cellusername.setInputs(Inputs.EditText);
        setConentR(R.layout.activity_act_userjob);
        Celluserid.setID(true);
        setAddvew(new AddnewFeactuerAdaptor() {
            @Override
            public View Addview(View view, int poss, ArrayList<Row> Table) {
                ImageView imageView  =  view.findViewById(R.id.imagstate);
                int state = Integer.parseInt( Table.get(poss).getvalue(sum) + "");
                switch (state){


                    case 3  :imageView.setImageResource(R.drawable.loc7); break;

                }
                return view ;
            }
        });

        setCell(new Cell[]{Celluserid ,  CelluserJob, Cellusername , Celluserphone ,sum});

        FirebaseFirestore query = FirebaseFirestore.getInstance();

        setFirestorQurery(query.collection(getFirestorCollectionName()).whereGreaterThanOrEqualTo(sum.getName() , 3)
                .whereEqualTo(Celluserphone.getName() , phone)
                .get());
        loadRoteanRecyclerviewFirestre(getFirestorQurery(),R.layout.item_jobs, recyclerView , GetRecyclervewlayoutManagerLinearVertecal() , null );
        setEventcontarers(new EventContanterintmCliked() {
            @Override
            public void ItemCleicked(View view, Cell[] Cells) {

                uiddata = Cells[0].getValue() + "";
                setinputvalue(Cells);

            }
        });
    }


    public  void  getdesDetails(String id , final Questions questions  , final RecyclerView recyclerView , final TextView nametxt , final TextView phontxt){
        final int anser[] = new int[6];

        setCell( new Cell[]{Q1 , Q2  ,Q3  , Q4 , Q5 , Q6 , Celluserphone , Cellusername});
      getUserValue(id).addSnapshotListener(new EventListener<DocumentSnapshot>() {
          @Override
          public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
              anser[0]  = Integer.parseInt(  documentSnapshot.get(Q1.getName()) + "");
              anser[1]  = Integer.parseInt(  documentSnapshot.get(Q2.getName()) + "");
              anser[2]  = Integer.parseInt(  documentSnapshot.get(Q3.getName()) + "");
              anser[3]  = Integer.parseInt(  documentSnapshot.get(Q4.getName()) + "");
              anser[4]  = Integer.parseInt(  documentSnapshot.get(Q5.getName()) + "");
              anser[5]  = Integer.parseInt(  documentSnapshot.get(Q6.getName()) + "");nametxt.setText(documentSnapshot.get(Cellusername.getName()) + "");
              phontxt.setText(documentSnapshot.get(Celluserphone.getName()) + "");


              questions.load(recyclerView ,anser);



          }
      });

    }
public   void loadmappoints (final double latp1 , final double latp3 ,
                             final double longp2 , final double longp4
                             ){


setEventsload(new EventsLoadData() {
    @Override
    public void onloadData(DataSnapshot dataSnapshot) {






    }

    @Override
    public void onloadData(ArrayList<Row> Table) {
        ArrayList <MarkerOptions> mapsmark = new ArrayList<>();
        mapsmark.clear();
int i = 0 ;
while (i<Table.size()){
    String name = Table.get(i).getvalue(Cellusername) + "";

    double lat = Double.parseDouble( Table.get(i).getvalue(CelluserLoction_lat) + "");
    double lon = Double.parseDouble( Table.get(i).getvalue(CelluserLoction_long) + "");


int stat = Integer.parseInt( Table.get(i).getvalue(pationstat) + "");
int r = R.drawable.loc6 ;
switch (stat){

    case    STATNICE :r = R.drawable.loc5; break;

    case    STATMYBE :r = R.drawable.loc7; break;
    case    STATSURE :r = R.drawable.loc; break;

}


    mapsmark.add(getmapmark(name , lat , lon ,r));
    i++ ;
}

Eventloctionload.loadevent(mapsmark);
    }

    @Override
    public void onisExcestsetvals() {

    }

    @Override
    public void onloadData(Task<QuerySnapshot> dataSnapshot) {

    }

    @Override
    public void onloadsengledata(Row row) {

    }

    @Override
    public void load(QuerySnapshot qu) {

    }
});

setCell(new Cell[]{CelluserLoction_long , CelluserLoction_lat  , Cellusername , pationstat});
    final FirebaseFirestore query = FirebaseFirestore.getInstance();

    setFirestorQurery(query.collection(getFirestorCollectionName())
            .orderBy(CelluserLoction_lat.getName())
            .   whereLessThan(CelluserLoction_lat.getName() , latp1)
            .whereGreaterThan(CelluserLoction_lat.getName() , latp3)
            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    FirebaseFirestore query = FirebaseFirestore.getInstance();

                    query.collection(getFirestorCollectionName())
                            .orderBy(CelluserLoction_long.getName())
                            .   whereLessThan(CelluserLoction_long.getName() , longp2)
                            .whereGreaterThan(CelluserLoction_long.getName() , longp4)
                            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                            clearTable();
                            GetDataOnloadSucsecessFireStore(queryDocumentSnapshots);
                        }
                    });


                }
            }));
}



    public   void loadmappoints_doctor (final double latp1 , final double latp3 ,
                                        final double longp2 , final double longp4, final RecyclerView rec   ){



        setCell(new Cell[]{CelluserLoction_long , CelluserLoction_lat  , Cellusername , pationstat});
        final FirebaseFirestore query = FirebaseFirestore.getInstance();

        setFirestorQurery(query.collection(getFirestorCollectionName())
                .orderBy(CelluserLoction_lat.getName())
                .   whereLessThan(CelluserLoction_lat.getName() , latp1)
                .whereGreaterThan(CelluserLoction_lat.getName() , latp3)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        FirebaseFirestore query = FirebaseFirestore.getInstance();

                        query.collection(getFirestorCollectionName())
                                .orderBy(CelluserLoction_long.getName())
                                .   whereLessThan(CelluserLoction_long.getName() , longp2)
                                .whereGreaterThan(CelluserLoction_long.getName() , longp4)
                                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                              Loaddoctor(rec );


                                  }
                        });


                    }
                }));
    }


    public MarkerOptions getmapmark(String name , double Lat , double longe , int icon ) {
        MarkerOptions mo = new MarkerOptions();
        mo.title(name);
        LatLng ny = new LatLng( Lat  , longe);

        mo.position(ny);
        BitmapDescriptor bd = BitmapDescriptorFactory.fromResource(icon);
        mo.icon(bd);

        return mo;
    }


    public  loadloactions Eventloctionload ;

    public  interface  loadloactions{

        void loadevent(ArrayList<MarkerOptions> loc);
    }



}
