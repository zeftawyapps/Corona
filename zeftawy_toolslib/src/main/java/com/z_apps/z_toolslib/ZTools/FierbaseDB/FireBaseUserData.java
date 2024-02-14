package com.z_apps.z_toolslib.ZTools.FierbaseDB;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import androidx.annotation.NonNull;

public class FireBaseUserData extends FirebaseManeger {
public    final   Cell Cellusername = new Cell("username");
 public   final Cell CellEmail = new Cell("Email");
    public final   Cell Celluserid = new Cell("id");

    public final Cell Celluserphone = new Cell("phone");
    public  final   Cell CelluserAddres = new Cell("Address");
    public final Cell CelluserCity = new Cell("userCity");
    public final   Cell CelluserLoction = new Cell("userLocation");
    public final Cell CelluserLoction_long = new Cell("userLoction_long");
    public final Cell CelluserLoction_lat = new Cell("userLoction_lat");
    public  final  Cell CelluserJob = new Cell("job");

public Cell Celluserpasswod = new Cell("pass");

    public Cell getCellissignedup() {
        return Cellissignedup;
    }

    public void setCellissignedup(Cell cellissignedup) {
        this.Cellissignedup = cellissignedup;
    }

    public    Cell Cellissignedup = new Cell("Cellissignedup ");
    public String       COLLECTION_USER_ID  = "userID" ;
    public String   USER_ID  = "userID " ;
    public String   USER_LOGIN_STATE = "user_log_ing_stat";
    public String   USER_LOGIN_EMAIL = "user_email";
    public String   USER_LOGIN_NAME = "user_name";
    public String   USER_LOGIN_PASSWaRD = "user_passward";
    public   int USER_LOGIN_STATE_Google = 1;
    public     int USER_LOGIN_STATE_non = 0;
    public     int USER_LOGIN_STATE_Email = 2;



 public Z_savingShare z_savingShare ;
    public FireBaseUserData(Context context) {
        super(context);
        setFirestorCollectionName(COLLECTION_USER_ID);
        z_savingShare = new Z_savingShare(getContext() ,  "UserProfileData");


    }


    GoogleSignInClient mGoogleSignInClient;

    public  void  GoogleBuild(String webidbulder){

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken( webidbulder )
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();


    }

public  void SigneinGooleAccount(){

    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
   getActivity().  startActivityForResult(signInIntent, 551);
}
    GoogleSignInAccount account = null;

    public void GooleonActivityResult(int requestCode, int resultCode, Intent data) {

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 551) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            // Google Sign In was successful, authenticate with Firebase
            try {



                account = task.getResult(ApiException.class);


                final GoogleSignInAccount finalAccount = account;
                QuriryUserbyid(account.getId()).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.getResult().size() > 0) {





                        }else {

                            CreatAccountAuthWithGoogleFirestore(account);

                        }
                    }
                    });



            } catch (ApiException e) {
                e.printStackTrace();
            }

        }

    }

    String phone;
    String adress;
String Location ;
double longat , latat;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setLongat(double longat) {
        this.longat = longat;
    }

    public void setLatat(double latat) {
        this.latat = latat;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLongat(int longat) {
        this.longat = longat;
    }

    public void setLatat(int latat) {
        this.latat = latat;
    }

    public String getLocation() {
        return Location;
    }

    public double getLongat() {
        return longat;
    }

    public double getLatat() {
        return latat;
    }

    public String getUserphone() {
        return phone;
    }

    public String getAdress() {
        return adress;
    }

    public String getUserCity() {
        return city;
    }

    String city ;

    public void  CreatAccountAuthWithGoogleFirestore(final GoogleSignInAccount acct) {
        // [START_EXCLUDE silent]
        // [END_EXCLUDE]
      final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (isCurrentuser()){

                         Cellusername.setValue(acct.getDisplayName());
                          CellEmail.setValue(acct.getEmail());
                          Celluserid.setValue(acct.getId());
                           Cellissignedup.setValue(true);
                          setCell(new Cell[]{Cellusername , CellEmail ,Celluserid });
                            insertFirestore(acct.getId());


                             z_savingShare.getEditor().putInt( USER_LOGIN_STATE ,USER_LOGIN_STATE_Google);
                            z_savingShare.getEditor().putString( USER_ID, acct.getId());
                            z_savingShare.getEditor().putString( USER_LOGIN_NAME , acct.getDisplayName());
                            z_savingShare.getEditor().putString(USER_LOGIN_EMAIL , acct.getEmail());



                            z_savingShare.getEditor().apply();
                           /* Z_methods methods = new Z_methods(getContext());
                            methods.GOTo_Activity(HomePageActivity.class , R.anim.trans , R.anim.tinvers);
                              getActivity().finish();
                        */

                        }


                    }
                });
    }

    public Task<QuerySnapshot> QuriryUserbyid(String id ){
        FirebaseFirestore query = FirebaseFirestore.getInstance();


        return  query.collection(COLLECTION_USER_ID)
                .whereEqualTo(this. Celluserid.getName() , id).get();


    }

    public DocumentReference getUserValue (String id ){

    return     getValueFirestoreDecoment(id );

    }

 boolean useSaveshareprefrance;

    public void setCanUseSaveshareprefrance(boolean useSaveshareprefrance) {
        this.useSaveshareprefrance = useSaveshareprefrance;
    }

    public  void  CreatAccoumtAuthWithEmailfirestore(final String name , final String Emails , String pass){
        setEventAccountCreated(new EventAccountCreated() {
            @Override
            public void AccountCreated() {
                Log.i("user" , getAuth().getCurrentUser().getEmail());

                Cellusername.setValue(name);
               CellEmail  .setValue(getAuth().getCurrentUser().getEmail());
                Celluserid.setValue(getAuth().getCurrentUser().getUid());

                setCell(new Cell[]{Cellusername , CellEmail , Celluserid});

if (useSaveshareprefrance) {

    z_savingShare.getEditor().putInt(USER_LOGIN_STATE, USER_LOGIN_STATE_Email);
    z_savingShare.getEditor().putString(USER_ID, getAuth().getCurrentUser().getUid());
    z_savingShare.getEditor().putString(USER_LOGIN_EMAIL, Emails);
    z_savingShare.getEditor().putString(USER_LOGIN_NAME, name);


    z_savingShare.getEditor().apply();
}

                insertFirestore(getAuth().getCurrentUser().getUid());

                Dismessprogress();

            }
        });
        this.setProgresstitle("Signe up ");
      if (progressparEdnbled){
        this.Showprogress();}
        CreatAccountuseEmail(Emails , pass);
}


    public  void  CreatAccoumtAuthWithEmailfirestore(final String name , final String Emails , String pass,final Cell[] Cells ){
        setEventAccountCreated(new EventAccountCreated() {
            @Override
            public void AccountCreated() {
                Log.i("user" , getAuth().getCurrentUser().getEmail());
                ArrayList<Cell> cellsArray = new ArrayList<Cell>();
                Cellusername.setValue(name);
                CellEmail  .setValue(getAuth().getCurrentUser().getEmail());
                Celluserid.setValue(getAuth().getCurrentUser().getUid());

                Collections.addAll(cellsArray , Cells);
cellsArray.add(Cellusername);

cellsArray.add(CellEmail);
cellsArray.add(Celluserid);

int c = cellsArray.size() ;
        Cell[] cc = new Cell[c];

      int i  = 0 ;
      while (i<c){


            cc[i] = cellsArray.get(i);
i++ ;
        }

setCell(cc);
                if (useSaveshareprefrance) {

                    z_savingShare.getEditor().putInt(USER_LOGIN_STATE, USER_LOGIN_STATE_Email);
                    z_savingShare.getEditor().putString(USER_ID, getAuth().getCurrentUser().getUid());
                    z_savingShare.getEditor().putString(USER_LOGIN_EMAIL, Emails);
                    z_savingShare.getEditor().putString(USER_LOGIN_NAME, name);


                    z_savingShare.getEditor().apply();
                }

                insertFirestore(getAuth().getCurrentUser().getUid());

                Dismessprogress();

            }
        });
        this.setProgresstitle("Signe up ");
        if (progressparEdnbled){
            this.Showprogress();}
        CreatAccountuseEmail(Emails , pass);
    }


    public  void Log_In_Account_AuthEmailPasFirestore(String Emails , String pass){

        this.setProgresstitle("Log in  ");
        this.Showprogress();
        SigninuseEmailandpass(Emails , pass);
    }
public  void SaveinLoginInShareprefrance(){

        z_savingShare.getEditor().putInt(USER_LOGIN_STATE, USER_LOGIN_STATE_Email);
        z_savingShare.getEditor().putString(USER_ID, getAuth().getCurrentUser().getUid());
        z_savingShare.getEditor().putString(USER_LOGIN_EMAIL, getAuth().getCurrentUser().getEmail());


        z_savingShare.getEditor().apply();



}
    public  void RemovedAccountAuthWithEmailFirestore(String Email , String pss)
    {

       setEventSignedin(new EventSignedin() {
           @Override
           public void SaginedinEmail(String id) {
              eventAcountRemoved = new EventAcountRemoved() {
                  @Override
                  public void Removed(String id) {
                      removedataFirestoer(id);
                  }
              };

               RemoveAccountuseEmail();
           }

           @Override
           public void SaginedinEmailFialed() {

           }
       });
         Log_In_Account_AuthEmailPasFirestore(Email , pss);


    }
    public  void setUserSavesinShareprephrance_int  (String key , int  valu){
        z_savingShare.getEditor().putInt(key , valu);

        z_savingShare.getEditor().apply();

    }
    public  void setUserSavesinShareprephrance_bool (String key , boolean  valu){
        z_savingShare.getEditor().putBoolean(key , valu);

        z_savingShare.getEditor().apply();

    }

    public  void setUserSavesinShareprephrance_String (String key , String  valu){
        z_savingShare.getEditor().putString(key , valu);

        z_savingShare.getEditor().apply();

    }



    public  int  CheckSigneUpstatus(){

       return            z_savingShare.getSharedPreferences().getInt(USER_LOGIN_STATE , USER_LOGIN_STATE_non );


}


    public  int   getusersave_int(String key, int def){
        return            z_savingShare.getSharedPreferences().getInt(key ,def );
    }
    public  String   getusersave_String(String key, String  def){
        return            z_savingShare.getSharedPreferences().getString(key , def );
    }
    public  boolean   getusersave_bool(String key, boolean  def){
        return            z_savingShare.getSharedPreferences().getBoolean(key , def  );
    }


    public  String   getuseridfrominternalsave(){
        return            z_savingShare.getSharedPreferences().getString(USER_ID , "" );
    }
    public  String   getusernamefrominternalsave(){
        return            z_savingShare.getSharedPreferences().getString(USER_LOGIN_NAME , "" );
    }

    public  String   getuserEmailfrominternalsave(){
        return            z_savingShare.getSharedPreferences().getString(USER_LOGIN_EMAIL, "" );
    }







}
