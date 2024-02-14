package com.z_apps.z_toolslib.ZTools.FierbaseDB;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FireBaseDataTable {

    public FireBaseDataTable(Context context) {
        this.context = context;
        FirebaseApp.initializeApp(context);

    }

    Cell cell[];
    String perant;
    Object object;
    GridView gridView;
    ListView listView;
    RecyclerView recyclerView ;
boolean progressparEdnbled = true;
boolean internetConncted ;
    public boolean isProgressparEdnbled() {
        return progressparEdnbled;
    }

    public void setProgressparEdnbled(boolean progressparEdnbled) {
        this.progressparEdnbled = progressparEdnbled;
    }
public  boolean isInternetConncted(){
    ConnectivityManager connectivityManager
            = (ConnectivityManager) getContext() .  getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

internetConncted = activeNetworkInfo != null && activeNetworkInfo.isConnected();


if (!internetConncted){

    if (event_noInternetConnceion != null ){

        event_noInternetConnceion.NoIneternet(); ;

    }else {

        Toast.makeText(getContext() , " NO INTERNET CONNECTION" , Toast.LENGTH_LONG).show();
    }
}


    return  internetConncted ;
}
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public GridView getGridView() {
        return gridView;
    }

    public void setGridView(GridView gridView) {
        this.gridView = gridView;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    int LayoutR;
    int ConentR ;
    public int getConentR() {
        return ConentR;
    }

    public void setConentR(int conentR) {
        ConentR = conentR;
    }

    public int getLayoutR() {
        return LayoutR;
    }

    public void setLayoutR(int layoutR) {
        LayoutR = layoutR;
    }


    public void Testconnction() {
        isInternetConncted();
        FirebaseApp.initializeApp(context);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Zeftawy Apps");

        myRef.setValue("Hello, World!");
    }
    public void Testconnction(String ss ) {
        isInternetConncted();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Zeftawy Apps");

        myRef.setValue(ss);
    }


    DataView dataView;
    Baseadabtor baseadabtor;

    public DataView getDataView() {
        return dataView;
    }

    public void setDataView(DataView dataView) {
        this.dataView = dataView;
    }

    public Baseadabtor getBaseadabtor() {
        return baseadabtor;
    }

    public void setBaseadabtor(Baseadabtor baseadabtor) {
        this.baseadabtor = baseadabtor;
    }

    public String getPerant() {
        return perant;
    }

    public void setPerant(String perant) {
        this.perant = perant;
        getdatabaseref(context);
    }

    public ArrayList<Row> getTable() {
        return Table;
    }

    public void setTable(ArrayList<Row> table) {
        Table = table;
    }

    public Cell[] getCell() {
        return cell;
    }

    public void setCell(Cell[] cell) {
        this.cell = cell;
    }

    public ArrayList<Row> getRows() {
        return Table;
    }

    public void setRows(ArrayList<Row> rows) {
        this.Table = rows;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public ArrayList<Row> Table = new ArrayList<Row>();
    Row row;

    public void addrow(Cell cells[]) {


        row = new Row(cells);
        row.setValuew(cells);
        Table.add(row);
    }
    public   Row   addnewrow(Cell cells[]) {


        row = new Row(cells);
        row.setValuew(cells);
         return  row ;
    }


    public void clearTable() {

        Table.clear();

    }
 public DocumentReference getValueFirestoreDecoment (String Collection , String id ){

     isInternetConncted();


     FirebaseFirestore query = FirebaseFirestore.getInstance();

        String collection = Collection;

        return     query.collection( collection).document(id) ;



    }
 public DocumentReference getValueFirestoreDecoment (String id ){

     isInternetConncted();

     FirebaseFirestore query = FirebaseFirestore.getInstance();

        String collection = getFirestorCollectionName();

        return     query.collection( collection).document(id) ;



    }

    String firestorCollection;

    public String getFirestorCollectionName() {
        return firestorCollection;
    }

    public void setFirestorCollectionName(String firestorCollection) {
        this.firestorCollection = firestorCollection;

        getdatabaserefierstore(getContext());
    }

    public void Savedata(String chield, Object value) {
        isInternetConncted();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dpr = firebaseDatabase.getReference(perant);

        if (chield == "") {


            dpr.setValue(value);

        } else {

            dpr.child(chield).setValue(value);
        }
    }

    public void SavedataFirestore(final String decument, HashMap<String, Object> Rowvalue) {
        isInternetConncted();

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(firestorCollection)
                .document(decument)
                .set(Rowvalue)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                if (eventdbmissoncomplieteFirestore != null ){

                    getEventdbmissoncomplieteFirestore().messionComplete(
                        db.     collection(firestorCollection)
                                    .document(decument)
                    );}

                if (eventdbmissoncompliete != null ){

                    getEventdbmissoncompliete().messionComplete(
                    );}
            }
        })
        ;

    }

    public Task<DocumentReference> SavedataFirestoer(HashMap<String, Object> Rowvalue) {
        isInternetConncted();

        FirebaseFirestore db = getdatabaserefierstore(context);
  return       db.collection(firestorCollection)
                .add(Rowvalue);

    }


    public void   updatedataFirestoer(final String doc , HashMap<String, Object> Rowvalue) {
        isInternetConncted();

          final FirebaseFirestore db = getdatabaserefierstore(context);
                db.collection(firestorCollection)
                .document(doc)
                .update(Rowvalue)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    if (eventdbmissoncomplieteFirestore != null ){

                        getEventdbmissoncomplieteFirestore().messionComplete(
                                db.collection(firestorCollection)
                                        .document(doc)
                        );}

                        if (eventdbmissoncompliete != null ){

                            getEventdbmissoncompliete().messionComplete(
                            );}

                    }
                })
                ;


    }
    public void   removedataFirestoer(final String doc  ) {
        isInternetConncted();

        final FirebaseFirestore db = getdatabaserefierstore(context);
        db.collection(firestorCollection)
                .document(doc)
                 .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        if (eventdbmissoncomplieteFirestore != null ){

                            getEventdbmissoncomplieteFirestore().messionComplete(
                                    db.collection(firestorCollection)
                                            .document(doc)
                            );}

                        if (eventdbmissoncompliete != null ){

                            getEventdbmissoncompliete().messionComplete(
                            );}

                    }
                })

        ;


    }

    public void updatedataFirestoerPach(String doc , HashMap<String, Object> Rowvalue) {
        isInternetConncted();

        FirebaseFirestore db = getdatabaserefierstore(context);

        db.runTransaction(new Transaction.Function<Double>() {
            @Nullable
            @Override
            public Double apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {


                return null;
            }
        });


    }

    public void Savedata(String chield, HashMap<String, Object> Rowvalue) {
        isInternetConncted();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dpr = firebaseDatabase.getReference(perant);

        if (chield == "") {


            dpr.setValue(Rowvalue);

        } else {

            dpr.child(chield).setValue(Rowvalue);
        }
    }

    public void Firebasetestconnection() {
        isInternetConncted();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message")


                ;

        myRef.setValue("Hello, Z apps ")

        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Test Error " , e.getMessage());
            }
        })
        ;
    }

    public void Savedata(String id, String chield, HashMap<String, Object> Rowvalue) {
        isInternetConncted();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dpr = firebaseDatabase.getReference(perant);

        if (chield == "") {


            dpr.child(id).setValue(Rowvalue);

        } else {

            dpr.child("/" + chield).child(id).setValue(Rowvalue);
        }
    }

    public void Savedata(DatabaseReference dpr, HashMap<String, String> Rowvalue) {
        isInternetConncted();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        dpr = firebaseDatabase.getReference(perant);


        dpr.setValue(Rowvalue);


    }

    public void Savedata(String id, DatabaseReference dpr, HashMap<String, String> Rowvalue) {
        isInternetConncted();


        dpr.child(id).setValue(Rowvalue);


    }

    public String getID() {


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dpr = firebaseDatabase.getReference(perant);

        return dpr.push().getKey();


    }

     void Desmissprogress() {
       try {

if (progressDialog == null) return; ;
           progressDialog.dismiss();
       }catch (Exception e ){}
        ;
    }

    public DatabaseReference getdatabaseref(Context context) {


        FirebaseApp.initializeApp(context);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return firebaseDatabase.getReference(perant);
    }

    public FirebaseFirestore getdatabaserefierstore(Context context) {

        FirebaseApp firebaseApp = FirebaseApp.initializeApp(context.getApplicationContext());

        FirebaseFirestore firebaseDatabase = FirebaseFirestore.getInstance(firebaseApp);

        return firebaseDatabase;
    }


    public DatabaseReference getdatabaseref(Context context, String Child) {
        FirebaseApp.initializeApp(context);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        return firebaseDatabase.getReference(perant + "/" + Child);
    }

    public String getID(String dep) {


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dpr = firebaseDatabase.getReference(perant + "/" + dep);

        return dpr.push().getKey();


    }

    String uridownload;

    public String getUridownload() {
        return uridownload;
    }

    static String uridownlod;

    public void updatedata(String id, String key, Object value) {
        isInternetConncted();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dpr = firebaseDatabase.getReference(perant);

        Map<String, Object> taskMap = new HashMap<String, Object>();
        taskMap.put(key, value);
        dpr.child(id).updateChildren(taskMap); //should I use setValue()...?

    }

    public void updatedata(String id, String child, HashMap<String, Object> Rowvalue) {
        isInternetConncted();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dpr = firebaseDatabase.getReference(perant);

        int i = 0;


        if (child == "") {


            dpr.child(id).updateChildren(Rowvalue);

        } else {

            dpr.child("/" + child).child(id).updateChildren(Rowvalue);
        }
    }

    public DatabaseReference Removedata(String id, String child) {
        isInternetConncted();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dpr = firebaseDatabase.getReference(perant);

        int i = 0;


        if (child == "") {


            dpr.child(id).removeValue();
            dpr.child("/" + child).child(id).removeValue()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            getEventdbmissoncompliete().messionComplete();
                        }
                    })
            ;
        } else {

            dpr.child("/" + child).child(id).removeValue()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            try {
                                getEventdbmissoncompliete().messionComplete();
                            } catch (Exception e) {
                            }
                        }
                    })
            ;

        }
        return dpr;
    }

    ProgressBar prg;

    public void setprogressBar(ProgressBar progressBar) {
        prg = progressBar;


    }

    public void uploadimg(Uri uri) {
        isInternetConncted();

        if (uri != null) {
            StorageReference storageRef = FirebaseStorage.getInstance().getReference("Zeftawy Apps");
            StorageReference file = storageRef.child(System.currentTimeMillis() + "." + getExtintion(uri));



                if(progressparEdnbled){
                    Showprogress();}
            file.putFile(uri)

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("Error", e.getMessage());

Dismessprogress();
                            eventsUplading.onfield();
                        }
                    })

                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                            // ...
                            //   String path =    taskSnapshot.getMetadata().getReference().getDownloadUrl().getResult().getPath();

                            String path = taskSnapshot.getMetadata().getPath();

                              Log.i("path" , path) ;


/// getActioluri
                            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                            storageRef.child(path).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    // Got the download URL for 'users/me/profile.png'
                                    if (uri != null) {

                                        uridownload = uri.toString();
                                        Log.i("uri", uri.toString());
                                        Log.i("pathdowload", uri.getPath());


                                        if (getPrg() != null) {
                                            getPrg().setVisibility(View.GONE);
                                        } else {
                                            Desmissprogress();
                                        }
                                        eventsUplading.onsucess();
                                    } else {
                                        Log.i("DD", "not ");
                                        if (getPrg() != null) {
                                            getPrg().setVisibility(View.GONE);
                                        } else {
                                            Desmissprogress();
                                        }
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    Log.i("fiald", exception.getMessage());
                                    if (getPrg() != null) {
                                        getPrg().setVisibility(View.GONE);
                                    } else {
                                        Desmissprogress();
                                    }
                                    // Handle any errors
                                }
                            });


                        }

                    });
            ;


        } else {

        }

    }

public  void RemoveStorage(Uri uri){

    isInternetConncted();

    if (uri != null) {
        int zeftay = uri.getPath().indexOf("Zeftawy Apps");
        String file =  uri.getPath().substring(zeftay)   ;
        StorageReference mFirebaseStorage = FirebaseStorage.getInstance().getReference(file);

        if (getPrg() != null) {
            getPrg().setVisibility(View.VISIBLE);

        } else {
            showprogriss();
        }
        ;
        mFirebaseStorage.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.i("deleted" , true + "");
                eventRemoveStorage.onsucess();

                if (getPrg() != null) {
                    getPrg().setVisibility(View.GONE);
                } else {
                    Desmissprogress();
                }
            }
        });
    }

    }


    public  void ReplaceStorage(Uri olduri , final Uri newuri){

        isInternetConncted();

        if (olduri != null) {
            int zeftay = olduri.getPath().indexOf("Zeftawy Apps");
            String file =  olduri.getPath().substring(zeftay)   ;
            StorageReference mFirebaseStorage = FirebaseStorage.getInstance().getReference(file);

            if (getPrg() != null) {
                getPrg().setVisibility(View.VISIBLE);

            } else {
                showprogriss();
            }
            ;
            mFirebaseStorage.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.i("deleted" , true + "");

                    if (newuri != null) {
                        StorageReference storageRef = FirebaseStorage.getInstance().getReference("Zeftawy Apps");
                        StorageReference file = storageRef.child(System.currentTimeMillis() + "." + getExtintion(newuri));


                        file.putFile(newuri)

                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.i("Error", e.getMessage());

                                        getPrg().setVisibility(View.GONE);

                                        eventReplace.onfield();
                                    }
                                })

                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                                        // ...
                                        //   String path =    taskSnapshot.getMetadata().getReference().getDownloadUrl().getResult().getPath();

                                        String path = taskSnapshot.getMetadata().getPath();

                                        Log.i("path" , path) ;


/// getActioluri
                                        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                                        storageRef.child(path).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                // Got the download URL for 'users/me/profile.png'
                                                if (uri != null) {

                                                    uridownload = uri.toString();
                                                    Log.i("uri", uri.toString());
                                                    Log.i("pathdowload", uri.getPath());


                                                    if (getPrg() != null) {
                                                        getPrg().setVisibility(View.GONE);
                                                    } else {
                                                        Desmissprogress();
                                                    }
                                                    eventReplace.onsucess();
                                                } else {
                                                    Log.i("DD", "not ");
                                                    if (getPrg() != null) {
                                                        getPrg().setVisibility(View.GONE);
                                                    } else {
                                                        Desmissprogress();
                                                    }
                                                }
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception exception) {
                                                Log.i("fiald", exception.getMessage());
                                                if (getPrg() != null) {
                                                    getPrg().setVisibility(View.GONE);
                                                } else {
                                                    Desmissprogress();
                                                }
                                                // Handle any errors
                                            }
                                        });


                                    }

                                });
                        ;


                    } else {

                    }

                }
            });




        } }
    String getExtintion(Uri uri) {
        ContentResolver cr = getContext().getContentResolver();
        MimeTypeMap mim = MimeTypeMap.getSingleton();

        return mim.getExtensionFromMimeType(cr.getType(uri));
    }


    Context context;
    FirebaseUser user;

    public void setUserid(String userid) {
        Userid = userid;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    ProgressDialog progressDialog;

    public String getProgresstitle() {
        return progresstitle;
    }

    public void setProgresstitle(String progresstitle) {

        if (progresstitle == null) {
            progresstitle = "Loading ...";
        }
        this.progresstitle = progresstitle;
    }

    String progresstitle = "Loading";

    void showprogriss() {
        progressDialog = new ProgressDialog(context);
try {


    progressDialog.setMessage(progresstitle);
    progressDialog.show();
}catch (Exception e ) {}
    }

    public ProgressBar getPrg() {
        return prg;
    }


    EventsUplading eventsUplading;
   // EventsLoadData eventsload;
    EventRemoveStorage eventRemoveStorage ;

    public EventRemoveStorage getEventRemoveStorage() {
        return eventRemoveStorage;
    }

    public void setEventRemoveStorage(EventRemoveStorage eventRemoveStorage) {
        this.eventRemoveStorage = eventRemoveStorage;
    }

    public EventsUplading getEventsUplading() {
        return eventsUplading;
    }
    public void setEventsUplading(EventsUplading eventsUplading) {
        this.eventsUplading = eventsUplading;
    }
ArrayList<EventsLoadData> eventsLoadData = new ArrayList<>();

    public void getEventsload(DataSnapshot d ) {
        for (EventsLoadData v:eventsLoadData
             ) {
            v.onloadData(d);

        }
     }
    public void getEventsload(ArrayList<Row> r ) {
        for (EventsLoadData v:eventsLoadData
        ) {
            v.onloadData(r);

        }
     }
    public void getEventsload(Task<QuerySnapshot> r ) {
        for (EventsLoadData v:eventsLoadData
        ) {
            v.onloadData(r);

        }
     }
    public void getEventsload(QuerySnapshot r ) {
        for (EventsLoadData v:eventsLoadData
        ) {
            v.load(r);

        }
     }

    public void setEventsload(EventsLoadData eventsload) {
      //  this.eventsload = eventsload;
        eventsLoadData.add(eventsload);
    }

    public Query LoadFirebaseData(String select) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        prg.setVisibility(View.VISIBLE);
        clearTable();
        Query query = reference.child(select);
        UserInfo userInfo;

        return query;


    }
public  void GetDataOnloadSucsecess (DataSnapshot dataSnapshot){


    clearTable();
    if (dataSnapshot.exists()) {
        Log.i("loaded ", true + "");


        for (DataSnapshot issu : dataSnapshot.getChildren()) {
            boolean istestnull = false;
            int i = 0;
            while (i < cell.length) {
                String childname = cell[i].getName();
                String s = issu.child(childname).getValue() + "";
                if (!s.equals("null")) {
                    cell[i].setValue(s);
                    if (cell[i].isnullTest) {
                        String nul = issu.child(cell[i].getName()).getValue() + "";
                        if (nul.equals("null")) {
                            istestnull = true;
                        }
                    }

                }
                i++;
            }
            if (istestnull) {
                istestnull = false;
                continue;
            }
            addrow(cell);
        }
        if (eventsLoadData.size() !=0){
         getEventsload(Table);

        }
        Dismessprogress();
    } else {
        clearTable();
        getEventsload(Table);
Dismessprogress();
    }

    Dismessprogress();


}

public  boolean isValidationError(){
        boolean iserror = false  ;
    for (Cell c:
         getCell()) {
        if (c.isErrorfound()){
            iserror = true ;
            break;

        }
    }
 return  iserror ;
}

    public void LoadData(Query Qur, final Cell[] cell) {
        isInternetConncted();


        //    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
setCell(cell);
if (progressparEdnbled){
       Showprogress();}
        clearTable();
        Query query = Qur;
        query.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                          GetDataOnloadSucsecess(dataSnapshot);
                                     Dismessprogress();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                          Dismessprogress();

                                        }

                                        public EventsLoadData getEventload() {
                                            return Eventload;
                                        }

                                        public void setEventload(EventsLoadData eventload) {
                                            Eventload = eventload;
                                        }

                                        EventsLoadData Eventload;


                                    }
        );
    }





    public void LoadDataFirestore(String Qur, final Cell[] cell) {
        isInternetConncted();
setCell(cell);

        //    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
if (progressparEdnbled){
      Showprogress();}
        clearTable();
        FirebaseFirestore query = FirebaseFirestore.getInstance();
        query.collection(Qur)
                .get()

                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

GetDataOnloadSucsecessFireStore(task);
                        }
                    }
                });
    }



public  void Showprogress (){

    if (getPrg() != null) {
        getPrg().setVisibility(View.VISIBLE);

    } else {
        showprogriss();
    }
}

    public  void Dismessprogress (){
if (!progressparEdnbled) return;
        if (getPrg() != null) {
            getPrg().setVisibility(View.GONE);
        } else {

            Desmissprogress();
        }
    }

    public void GetDataOnloadSucsecessFireStore(Task<QuerySnapshot> task) {
         for (QueryDocumentSnapshot document : task.getResult()) {
             getEventsload(task);

            boolean istestnull = false;
            int i = 0;
            while (i < cell.length) {
                String childname = cell[i].getName();
                String s = document.get(cell[i].getName()) + "";
                String id = document.getId();
                if (cell[i].isID){
                    cell[i].setValue(id);

                }else
                if (!s.equals("null")) {
                    {
                    cell[i].setValue(s);}
                    if (cell[i].isnullTest) {
                        String nul = document.get(cell[i].getName()) + "";
                        if (nul.equals("null")) {
                            istestnull = true;
                        }
                    }

                }
                i++;
            }
            if (istestnull) {
                istestnull = false;
                continue;
            }
            addrow(cell);
        }
        if (eventsLoadData.size() >0){
            getEventsload(Table);}
        Dismessprogress();
    }

    public void GetDataOnloadSucsecessFireStore(  QuerySnapshot task) {

        for (DocumentSnapshot document : task.getDocuments()) {

            boolean istestnull = false;
            int i = 0;
            while (i < cell.length) {
                String childname = cell[i].getName();
                String s = document.get(cell[i].getName()) + "";
                if (!s.equals("null")) {
                    cell[i].setValue(s);
                    if (cell[i].isnullTest) {
                        String nul = document.get(cell[i].getName()) + "";
                        if (nul.equals("null")) {
                            istestnull = true;
                        }
                    }

                }
                i++;
            }
            if (istestnull) {
                istestnull = false;
                continue;
            }
            addrow(cell);
        }
String ss = "SDf" ;
        if (eventsLoadData.size()!=0){
         getEventsload(Table);
        }
        Dismessprogress();
    }

    public Row GetDataOnloadSucsecessFireStore(  DocumentSnapshot document ) {
             int i = 0;
            while (i < cell.length) {
                String childname = cell[i].getName();
                String s = document.get(cell[i].getName()) + "";
                    cell[i].setValue(s);
                i++;
            }
        Dismessprogress();
            Row r = new Row(cell);
            return  r ;
    }


    public void LoadDataitem(DatabaseReference Qur , final Cell [] cell , final String id ) {
        isInternetConncted();


        //    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
if (progressparEdnbled){
        if (getPrg()!=null){
            getPrg().setVisibility(View.VISIBLE);

        }else {
            showprogriss();
        }}
        clearTable();
        DatabaseReference query  = Qur;
        query .   addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()){
                                                    Log.i("loaded "  , true + "" );

                                                    clearTable();
                                                    if  (dataSnapshot.getKey() == id ) {
                                                        boolean istestnull = false ;
                                                        int i = 0 ;
                                                        while (i<cell.length) {
                                                            String childname = cell[i].getName();
                                                            String s = dataSnapshot.child(childname).getValue() + "";
                                                            if (!s.equals("null")) {
                                                                cell[i].setValue(s);
                                                                if (cell[i].isnullTest) {
                                                                    String nul = dataSnapshot.child(cell[i].getName()).getValue() + "";
                                                                    if (nul.equals("null")) {
                                                                        istestnull = true;
                                                                    }
                                                                }

                                                            }
                                                            i++;
                                                        }

                                                        addrow(cell);
                                                    }
                                                     getEventsload(Table);

                                                    if (getPrg() !=null ) {
                                                        getPrg().setVisibility(View.GONE);
                                                    }else {
                                                        Desmissprogress();
                                                    }
                                                }

                                                if (getPrg() !=null ) {
                                                    getPrg().setVisibility(View.GONE);
                                                }else {
                                                    Desmissprogress();
                                                }


                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                Log.e("load_category error "  , databaseError.getMessage());

                                                if (getPrg() !=null ) {
                                                    getPrg().setVisibility(View.GONE);
                                                }else {
                                                    Desmissprogress();
                                                }

                                            }

                                            public EventsLoadData getEventload() {
                                                return Eventload;
                                            }

                                            public void setEventload(EventsLoadData eventload) {
                                                Eventload = eventload;
                                            }

                                            EventsLoadData Eventload ;



                                        }
        );
    }


    public boolean  isChildExsiste(DatabaseReference dbr , final String child) {
        isInternetConncted();

        final boolean[] isrestrant = new boolean[1];
        isrestrant[0]=false;
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


        clearTable();
        Query query = dbr;
        query.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.child(child).exists()) {
                                                isrestrant[0] = true;
                                          //      getEventsload().onisExcestsetvals();

                                            } else {
                                                isrestrant[0] = false;

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            Log.i("cnaceld" , "tre" + "");
                                            isrestrant[0] = false ;
                                        }
                                    }

        );
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
            isrestrant[0] = false;
        }
return isrestrant[0] ;

    }

    public Intent showImgFileChooser() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("image/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
      return intent;
    }

    EventLoadimg eventLoadimg ;
    String Imgs[];
   ArrayList<String> Imgsuri = new ArrayList<String>();

   public  void getimguri(String imgint ){

       isInternetConncted();

       final Uri[] uriimg = new Uri[1];
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    storageRef.child(imgint).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
        @Override
        public void onSuccess(Uri uri) {
            // Got the download URL for 'users/me/profile.png'
            if (uri != null) {
              uriimg[0] =  uri;

              eventLoadimg.imguri(uri.toString());
                Log.i("uri", uri.toString());
              } else {
                Log.i("DD", "not ");
            }
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception exception) {
            Log.i("fiald", exception.getMessage());

            // Handle any errors
        }
    });


}

Activity activity ;

    public Activity getActivity() {
        if ( getContext() instanceof Activity) {
           activity = (Activity) context;
        }
        return activity;
    }

    public void setActivity(Activity activity) {

setContext(activity);
        this.activity = activity;
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks ;



public void CreatAccountusephone(final String phoneNumber ){
    isInternetConncted();

    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            Log.d(TAG, "onVerificationCompleted:" + credential);
            PhoneCode = credential.getSmsCode();
            if (PhoneCode != null) {
                Verfaysms(PhoneCode);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.



            // Show a message and update the UI
            // ...
        }

        @Override
        public void onCodeSent(String s,
                               PhoneAuthProvider.ForceResendingToken token) {

            super.onCodeSent(s ,token);
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d(TAG, "onCodeSent:" + s);

            // Save verification ID and resending token so we can use them later
verfayid = s ;
            // ...
        }
    };




    PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,        // Phone number to verify
            60,                 // Timeout duration
            TimeUnit.SECONDS,   // Unit of timeout
            getActivity(),               // Activity (for callback binding)
            mCallbacks);        // OnVerificationStateChangedCallbacks


   }


    public void ViryfayAccountusephone(final String phoneNumber ){
        isInternetConncted();

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:" + credential);
                PhoneCode = credential.getSmsCode();
                if (PhoneCode != null) {
                    ViryfayAccountusephone(PhoneCode);
                }
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.



                // Show a message and update the UI
                // ...
            }

            @Override
            public void onCodeSent(String s,
                                   PhoneAuthProvider.ForceResendingToken token) {

                super.onCodeSent(s ,token);
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.i(TAG, "onCodeSent:" + s);

                // Save verification ID and resending token so we can use them later
                verfayid = s ;
                // ...
            }
        };




        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                getActivity(),               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks


    }
    public void ViryfayAccountusephone(final String phoneNumber  , PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks ){
        isInternetConncted();





        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                getActivity(),               // Activity (for callback binding)
                callbacks);        // OnVerificationStateChangedCallbacks


    }


    PhoneAuthCredential credential ;
   public void Verfaysms(String code ){
  credential = PhoneAuthProvider.getCredential(verfayid , code);
PhoneCode = code ;
singinwithphone();
   }

    public void VerfaysmsWithiotcreatAccount(String code ){
        credential = PhoneAuthProvider.getCredential(verfayid , code);
        PhoneCode = code ;
     }

    FirebaseAuth mAuth;

    public FirebaseAuth getAuth() {
        return mAuth;
    }

    public  void singinwithphone(){
        isInternetConncted();

    mAuth = FirebaseAuth.getInstance();
    mAuth.signInWithCredential(credential)
            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");

                        FirebaseUser user = task.getResult().getUser();
/*
                        Z_savingShare save = new Z_savingShare(context , "login");

                        save.getEditor().putString("phone" , user.getPhoneNumber());
                        save.getEditor().putString("vid" , verfayid);
                        save.getEditor().putString("code" , PhoneCode);
                        save.getEditor().putBoolean("user" , true);
                        save.getEditor().putBoolean("Email" , false);


save.getEditor().apply();*/
                        // ...
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                        }
                    }
                }
            });
}

String PhoneCode ;
String verfayid ;
String Userid ;

    public String getUserid() {
        return Userid;
    }

    public String getPhoneCode() {
        return PhoneCode;
    }

    public String getVerfayid() {
        return verfayid;
    }





public  boolean isCurrentuser(){

      mAuth = FirebaseAuth.getInstance();
       FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){return false;}else {return  true; }

}

public  void CreatAccountuseEmail(String Emaint , String pass ){

    isInternetConncted();

    mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(Emaint , pass)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                          Userid = user.getUid();


if ( eventAccountCreated!=null ) {
    eventAccountCreated.AccountCreated();
}
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e(TAG, "signInWithEmail:failure", task.getException());

                        }

                        // ...
                    }
                })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Creat account error " , e.getMessage());
            }
        });

}


    public  void RemoveAccountuseEmail(){

        isInternetConncted();

        final String id ;
        mAuth = FirebaseAuth.getInstance();
      FirebaseUser user = mAuth.getCurrentUser();
  id = user.getUid() ;
      user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
          @Override
          public void onSuccess(Void aVoid) {
              if (eventAcountRemoved != null){

                  eventAcountRemoved.Removed(id );

              }
          }
      });

    }

public  void AdduserToken(String id , Cell cell) throws FileNotFoundException {

}
public  void SigninuseEmailandpass(String Email , String pass){
    isInternetConncted();

        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(Email , pass)

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Sign in error " , e.getMessage());

                  String notercord = "There is no user record corresponding  ";
Dismessprogress();
if (eventSignedin != null) {
    eventSignedin.SaginedinEmailFialed();
}
                    }
                })

                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Dismessprogress();

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            if ( eventdbmissoncompliete!=null ) {
                                eventdbmissoncompliete.messionComplete();
                            }

                            if ( eventSignedin!=null ) {
                                eventSignedin.SaginedinEmail(mAuth.getCurrentUser().getUid());
                            }
                        } else {
                            // If sign in fails, display a message to the user.

                            Dismessprogress();

                        }

                        // ...
                    }
                }

                );


}
EventSignedin eventSignedin ;
EventReplace eventReplace ;

    public EventReplace getEventReplace() {
        return eventReplace;
    }

    public void setEventReplace(EventReplace eventReplace) {
        this.eventReplace = eventReplace;
    }

    public EventLoadimg getEventLoadimg() {
        return eventLoadimg;
    }

    public void setEventLoadimg(EventLoadimg eventLoadimg) {
        this.eventLoadimg = eventLoadimg;
    }

    public EventSignedin getEventSignedin() {
        return eventSignedin;
    }

    public void setEventSignedin(EventSignedin eventSignedin) {
        this.eventSignedin = eventSignedin;
    }

    public Eventdbmissoncompliete getEventdbmissoncompliete() {
        return eventdbmissoncompliete;
    }
ArrayList<Eventdbmissoncompliete> eventdbmissoncomplietelistner ;

    public ArrayList<Eventdbmissoncompliete> getEventdbmissoncomplietelistner() {
        return eventdbmissoncomplietelistner;
    }

    public void AddEventdbmissoncomplietelistner(Eventdbmissoncompliete eventdbmissoncomplietelistner) {
        this.eventdbmissoncomplietelistner .add( eventdbmissoncomplietelistner);
    }

    public void setEventdbmissoncompliete(Eventdbmissoncompliete eventdbmissoncompliete) {
        this.eventdbmissoncompliete = eventdbmissoncompliete;
    }
public  EventdbmissoncomplieteFirestore eventdbmissoncomplieteFirestore ;

    public EventdbmissoncomplieteFirestore getEventdbmissoncomplieteFirestore() {
        return eventdbmissoncomplieteFirestore;
    }

    public void setEventdbmissoncomplieteFirestore(EventdbmissoncomplieteFirestore eventdbmissoncomplieteFirestore) {
        this.eventdbmissoncomplieteFirestore = eventdbmissoncomplieteFirestore;
    }

    public Eventdbmissoncompliete eventdbmissoncompliete ;

public  EventAccountCreated eventAccountCreated;
public  EventAcountRemoved eventAcountRemoved;

    public EventAcountRemoved getEventAcountRemoved() {
        return eventAcountRemoved;
    }

    public void setEventAcountRemoved(EventAcountRemoved eventAcountRemoved) {
        this.eventAcountRemoved = eventAcountRemoved;
    }

    public EventAccountCreated getEventAccountCreated() {
        return eventAccountCreated;
    }

    public void setEventAccountCreated(EventAccountCreated eventAccountCreated) {
        this.eventAccountCreated = eventAccountCreated;
    }


Event_NoInternetConnceion event_noInternetConnceion ;

    public Event_NoInternetConnceion getEvent_noInternetConnceion() {
        return event_noInternetConnceion;
    }

    public void setEvent_noInternetConnceion(Event_NoInternetConnceion event_noInternetConnceion) {
        this.event_noInternetConnceion = event_noInternetConnceion;
    }
}



