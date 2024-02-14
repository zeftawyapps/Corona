package com.z_apps.z_toolslib.ZTools.FierbaseDB;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.Nullable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseManeger extends  FireBaseDataTable {


    String Mainparent ;
    ArrayList<String > iddep = new ArrayList<>();
    String iddepstring ;
    String depstring ;
    public  void loadTab_Firestore(String collectoin , final Cell Tabcell , final Cell idCell  , final TabLayout tabLayout , final LoadTabselected loadTableselected ){

        idCell.setID(true);
        setCell(new Cell[]{Tabcell , idCell});
        LoadDataFirestore( collectoin , new Cell[]{Tabcell , idCell});
        setEventsload(new EventsLoadData() {
            @Override
            public void onloadData(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onloadData(ArrayList<Row> Table) {
                if (Table.size() == 0 ){
                    tabLayout.setVisibility(View.GONE);
                    return;}
                tabLayout.removeAllTabs();
                for (Row r :Table
                ) {
                    String s  = r.getvalue(Tabcell.getName()) + "";
                    TabLayout.Tab tab = tabLayout.newTab();
                    iddep.add(r.getvalue(idCell.getName()) + "");
                    tab.setText(s);
                    tabLayout.addTab(tab);

                }
                Dismessprogress();
                if (tabLayout.getTabCount() > 0){
                    tabLayout.getTabAt(0).select();
                    iddepstring = iddep.get(0);
                    if (loadTableselected != null ){
                        loadTableselected.LoadselectedTab(tabLayout.getTabAt(0) ,iddep.get(0));
                    }
                }
                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {

                        loadTableselected.LoadselectedTab(tab , iddep.get(tab.getPosition()));

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
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
    }

    public  void loadTab_Firestore(Task<QuerySnapshot> dataSnapshot , final Cell Tabcell , final Cell idCell  , final TabLayout tabLayout , final LoadTabselected loadTableselected ){

        idCell.setID(true);
        setCell(new Cell[]{Tabcell , idCell});
        loadQueryFireStore( dataSnapshot);
        setEventsload(new EventsLoadData() {
            @Override
            public void onloadData(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onloadData(ArrayList<Row> Table) {
                TabLayout.Tab tab = null;
                getEventLoaded();
                if (Table.size() == 0 ){
                    tabLayout.setVisibility(View.GONE);
                    return;}
                tabLayout.removeAllTabs();
                iddep.clear();
                for (Row r :Table
                ) {
                    String s  = r.getvalue(Tabcell.getName()) + "";
                    tab=  tabLayout.newTab();
                    iddep.add(r.getvalue(idCell.getName()) + "");
                    tab.setText(s);
                    tabLayout.addTab(tab);

                }
                Dismessprogress();
                if (tabLayout.getTabCount() > 0){
                    iddepstring = iddep.get(0);

                    tabLayout.getTabAt(0).select();

                    loadTableselected.LoadselectedTab(tab , iddep.get(0));
                }
                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {

                        loadTableselected.LoadselectedTab(tab , iddep.get(tab.getPosition()));

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
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
    }

    public String getMainparent() {
        if (Mainparent == null){
            Mainparent = "Zeftawy Apps";
        }

        return Mainparent;
    }

    public void setMainparent(String mainparent) {
        Mainparent = mainparent;
    }

    public FirebaseManeger(Context context) {
        super(context);
        setContext(context);

    }


    public void insert(final String ceild , final Uri uri ) {

        uploadimg(uri);
        setEventsUplading(new EventsUplading() {
            @Override
            public void onsucess() {


                setPerant(getMainparent()  );

                String ids = getID();

                for (Cell c :
                        getCell() ) {
                    if (c.isID){
                        if (c.getValue()==null){
                            c.setValue(ids );}
                    }
                    if (c.isImg()){c.setValue(getUridownload());}
                }

                addrow( getCell());
                Savedata(ids, ceild, getRow().getRow());


            }

            @Override
            public void onfield() {

            }
        }  );



    }
    public void insert(final String ceild , final String child2 , final Uri uri ) {

        uploadimg(uri);
        setEventsUplading(new EventsUplading() {
            @Override
            public void onsucess() {


                setPerant(getMainparent()  );

                String ids = getID();

                String chi = child2 ;

                for (Cell c :
                        getCell() ) {
                    if (c.isID){
                        if (c.getValue()==null){
                            c.setValue(ids );}
                    }
                    if (c.isImg()){c.setValue(getUridownload());}
                    if(c.isPranchfield()){

                        chi =        child2    ;
                    }
                    if (c.isPranchTable()){

                        chi  =  c.getValue() + "/"+child2 ;
                    }}

                addrow( getCell());
                Savedata(ids, ceild, getRow().getRow());

                insertid(ids ,  chi  );
            }

            @Override
            public void onfield() {

            }
        }  );



    }

    public void insert( final String ceild     ) {

        boolean idnull ;
        String idstring ;

        setPerant(getMainparent()  );

        String ids = getID();

        for (Cell c :
                getCell() ) {
            if (c.isID){

                c.setValue(ids);}

        }

        addrow( getCell());
        Savedata(ids, ceild, getRow().getRow());


    }


    public  void  insertFirestore( Uri uri) {

        uploadimg(uri);
        setEventsUplading(new EventsUplading() {
            @Override
            public void onsucess() {


                setPerant(getMainparent()  );

                String ids = getID();

                for (Cell c :
                        getCell() ) {
                    if (c.isID){
                        if (c.getValue()==null){
                            c.setValue(ids );}
                    }
                    if (c.isImg()){c.setValue(getUridownload());}
                }

                addrow( getCell());
                SavedataFirestoer(getRow().row)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                                if (eventdbmissoncomplieteFirestore != null){
                                    getEventdbmissoncomplieteFirestore().messionComplete(documentReference);
                                }}
                        })
                ;



            }

            @Override
            public void onfield() {

            }
        }  );

    }

    public Task<DocumentReference> insertFirestore( ) {




        addrow( getCell());
        return SavedataFirestoer(getRow().row);

    }

    public void insertFirestore(String doc  ) {




        addrow( getCell());
        SavedataFirestore(doc ,  getRow().row)

        ;

    }

    public  void updateFirestore(String doc  ) {




        addrow( getCell());
        updatedataFirestoer(doc , getRow().row);

    }

public LinearLayoutManager GetRecyclervewlayoutManagerLinearVertecal (){
    LinearLayoutManager lm  =new LinearLayoutManager( getContext());
    lm.setOrientation(RecyclerView.VERTICAL);
        return lm;}

    public LinearLayoutManager GetRecyclervewlayoutManagerLinearHorezental (){
        LinearLayoutManager lm  =new LinearLayoutManager( getContext());
        lm.setOrientation(RecyclerView.HORIZONTAL);
        return lm;}


    public LinearLayoutManager GetRecyclervewlayoutManagerGride(int Culoms){
        return new GridLayoutManager(getContext() , Culoms);

    }

    public  void updateFirestoreNewFile(final String doc , Uri uri   ) {

        uploadimg(  uri);
        setEventsUplading(new EventsUplading() {


            @Override
            public void onsucess() {

                Dismessprogress();
                setPerant(getMainparent());

                String ids = getID();

                for (Cell c :
                        getCell()) {
                    if (c.isID) {
                        if (c.getValue() == null) {
                            c.setValue(ids);
                        }
                    }
                    if (c.isImg()) {
                        c.setValue(getUridownload());
                    }
                }

                addrow(getCell());
                updatedataFirestoer(doc, getRow().row);


            }

            @Override
            public void onfield() {

            }
        });


    }

    public  void updateFirestore(final String doc , Uri uri   ) {
        if (uri != null) {
            String Fileuri = null;
            for (Cell c :
                    getCell()) {

                if (c.isImg()) {

                    Fileuri = c.getValue() + "";
                }

            }
            if (Fileuri != null) {
                ReplaceStorage(Uri.parse(Fileuri), uri);
                setEventReplace(new EventReplace() {


                    @Override
                    public void onsucess() {
                        Dismessprogress();
                        setPerant(getMainparent());

                        String ids = doc ;

                        for (Cell c :
                                getCell()) {
                            if (c.isID) {
                                if (c.getValue() == null) {
                                    c.setValue(ids);
                                }
                            }
                            if (c.isImg()) {
                                c.setValue(getUridownload());
                            }
                        }

                        addrow(getCell());
                        updatedataFirestoer(doc, getRow().row);



                    }

                    @Override
                    public void onfield() {

                    }
                });

            }
        }else {

            //   String ids = getID();

            for (Cell c :
                    getCell()) {


                if (c.isImg()) {
                    c.setValue(getUridownload());
                }
            }

            addrow(getCell());
            updatedataFirestoer(doc, getRow().row);

        }
    }
    public  void removeFirestoreStorage(final String doc    ) {
        String Fileuri = null ;
        for (Cell c :
                getCell() ) {

            if (c.isImg()){

                Fileuri = c.getValue() + "";}

        }
        if (Fileuri!= null) {
            RemoveStorage(Uri.parse(Fileuri) );
            setEventRemoveStorage(new EventRemoveStorage() {


                @Override
                public void onsucess() {


                    setPerant(getMainparent());

                    removedataFirestoer  (doc ) ;


                }

                @Override
                public void onfield() {

                }
            });

        }
    }

    public void insertid(String id , final String ceild        ) {



        setPerant(getMainparent()  );

        String ids = id;

        for (Cell c :
                getCell() ) {
            if (c.isID){
                c.setValue(ids );
            }
        }

        addrow( getCell());
        Savedata(ids, ceild, getRow().getRow());


    }



    public void insert(final String child , String chi    ) {

        String Chiled2 = chi ;

        setPerant(getMainparent()  );

        String ids = getID();

        for (Cell c :
                getCell() ) {
            if (c.isID){
                if (c.getValue()==null){
                    c.setValue(ids );}
            }

            if(c.isPranchfield()){

                Chiled2 = chi  ;
            }
            if (c.isPranchTable()){

                Chiled2 =  c.getValue() + "/"+chi ;
            }
        }

        addrow( getCell());
        Savedata(ids, child, getRow().getRow());

        insertid(  ids  , Chiled2   );
    }


/////

    public void update(final String id , final String ceild , final Uri uri ) {
        if (uri == null){

            setPerant(getMainparent()  );

            String ids = id;



            for (Cell c :
                    getCell() ) {
                if (c.isID){
                    c.setValue(ids );

                }
                if (c.isImg()){
                    if ( getUridownload() != null){
                        c.setValue(getUridownload());}}

            }

            addrow( getCell());
            updatedata (ids, ceild, getRow().getRow());


        }else {
            String Fileuri = null ;
            for (Cell c :
                    getCell() ) {

                if (c.isImg()){

                    Fileuri = c.getValue() + "";}

            }
            if (Fileuri!= null){
                ReplaceStorage(Uri.parse(Fileuri) , uri);
                setEventReplace(new EventReplace() {
                    @Override
                    public void onsucess() {
                        Log.i("Repaced" , true + "");


                        setPerant(getMainparent()  );

                        String ids = id;


                        Cell[] cells = getCell();
                        for (Cell c :
                                cells ) {
                            if (c.isID){
                                c.setValue(ids );

                            }
                            if (c.isImg()){
                                if ( getUridownload() != null){
                                    c.setValue(getUridownload());}}

                        }

                        addrow( cells);
                        updatedata (ids, ceild, getRow().getRow());

                    }

                    @Override
                    public void onfield() {

                    }
                });
            }
        }}
    public void update(final String id , final String ceild , final String child2, final Uri uri ) {
        if (uri == null){

            setPerant(getMainparent()  );

            String ids = id;
            String chi = child2 ;


            for (Cell c :
                    getCell() ) {
                if (c.isID){
                    c.setValue(ids);

                }
                if (c.isImg()){
                    if ( getUridownload() != null){
                        c.setValue(getUridownload());}}

                if(c.isPranchfield()){

                    chi = child2  ;
                }
                if (c.isPranchTable()){

                    chi =  c.getValue() + "/"+child2 ;
                }
            }

            addrow( getCell());
            updatedata (ids, ceild, getRow().getRow());

            updateid(ids,chi);
        }else {
            String Fileuri = null ;
            for (Cell c :
                    getCell() ) {

                if (c.isImg()){

                    Fileuri = c.getValue() + "";}

            }
            if (Fileuri!= null){

                ReplaceStorage (Uri.parse(Fileuri),  uri);
                setEventReplace(new EventReplace() {
                    @Override
                    public void onsucess() {

                        setPerant(getMainparent()  );

                        String ids = id;


                        String chi = child2 ;

                        for (Cell c :
                                getCell() ) {
                            if (c.isID){
                                c.setValue(ids );

                            }
                            if (c.isImg()){
                                if ( getUridownload() != null){
                                    c.setValue(getUridownload());}}

                            if(c.isPranchfield()){

                                chi = child2  ;
                            }
                            if (c.isPranchTable()){

                                chi =  c.getValue() + "/"+child2 ;
                            }
                        }

                        addrow( getCell());
                        updatedata (ids, ceild, getRow().getRow());

                        updateid(ids,chi);
                    }

                    @Override
                    public void onfield() {

                    }
                }  );



            }}}


    public void update(String id , final String ceild     ) {



        setPerant(getMainparent()  );

        String ids = id ;

        for (Cell c :
                getCell() ) {
            if (c.isID){
                c.setValue(ids );
            }
        }

        addrow( getCell());
        updatedata(ids, ceild, getRow().getRow());


    }
    public void updateid(String id , final String ceild     ) {



        setPerant(getMainparent()  );

        String ids = id;

        for (Cell c :
                getCell() ) {
            if (c.isID){
                c.setValue(ids );
            }
        }

        addrow( getCell());
        updatedata(ids, ceild, getRow().getRow());


    }



    public void update(String id , final String child , String child2     ) {



        setPerant(getMainparent()  );

        String ids = id ;

        for (Cell c :
                getCell() ) {
            if (c.isID){
                c.setValue(ids );

            }

            if(c.isPranchfield()){

                child2 = child2  ;
            }
            if (c.isPranchTable()){

                child2 =  c.getValue() + "/"+child2 ;
            }
        }

        addrow( getCell());
        updatedata(ids, child, getRow().getRow());

        updateid (  child2 , ids  );
    }
    public  void Remove(String id , final String child , String child2   )
    {
        setPerant(getMainparent()  );

        String ids = id ;
        Removedata(id , child);
        Removedata(id , child2);
    }


    /////////////////////////
    public View RoteanView(int position , ArrayList<Row> rows){

        LayoutInflater inflater;
        inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(getLayoutR(), null);

        int i , c  ;
        i = 0 ;  c = getCell().length ;
        while (i<c) {
            if (getCell()[i].getR() != 0) {
                if (getCell()[i].isImg()) {
                    ImageView img = (ImageView) convertView.findViewById(getCell()[i].getR());
                    String imguri = rows.get(position).getvalue(getCell()[i]) + "";

                    Picasso.with(context).load(imguri).fit().into(img);

                } else {
                    TextView txt = convertView.findViewById(getCell()[i].getR());
                    String txts = rows.get(position).getvalue(getCell()[i]) + "";

                    txt.setText(txts);

                }

            }
            i++;
        }

        return convertView;
    }








    public Baseadabtor roteanadabtore() {
        setLayoutR(getLayoutR());
        DataView dv = new DataView() {


            @Override
            public void bindview(View view, Cursor cursor) {

            }

            @Override
            public View bindview(final int position, View convertView, Context context, int Rlayou, final ArrayList<Row> rows) {


                View conatnv = RoteanView(position , rows);
                if (addvew != null ){
                    convertView =   addvew.Addview(conatnv , position , rows);
                }else {
                    convertView =   conatnv ;
                }
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("pos" , position + "");
                        int i = 0 ; int c = getCell().length;
                        while (i<c){
                            getCell()[i].setValue(getTable().get(position).getvalue(getCell()[i]));
                            i ++ ;
                        }
                        if (getEventcontarers()!=null) {
                            getEventcontarers().ItemCleicked(v ,  getCell());
                        }

                        if (getConentR()!=0){
                            setinputvalue(getCell());
                        }

                    }
                });
                return  convertView;
            }

            @Override
            public View bindviewOnline(int position, View convertView, Context context, int Rlayou, ArrayList<JSONObject> rows) {
                return null;
            }
        };


        setBaseadabtor(new Baseadabtor(getContext(), getTable(), this.getLayoutR(), dv));
        return getBaseadabtor();
    }

    public void loadRoteanlistview(String child , int Rlayout , final ListView listV, ProgressBar progressBar   ) {
        setLayoutR(Rlayout);
        setListView(listV);
        setprogressBar(progressBar);
        setEventsload(new EventsLoadData() {
            @Override
            public void onloadData(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onloadData(final ArrayList<Row> Table) {

                getListView().setAdapter(roteanadabtore());
                getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("Sdfsdf", "Sdf");

                        Dismessprogress();
                    }
                });
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
        setPerant(getMainparent());
        DatabaseReference dpr = getdatabaseref(getContext()).child(child);
        LoadData(dpr, getCell());


    }


    public   void removewhereFirestore(Task<QuerySnapshot> where    ) {
        Showprogress();

        where.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot doc : task.getResult()){
                    FirebaseFirestore query = FirebaseFirestore.getInstance();
                    DocumentReference dd = query.collection(getFirestorCollectionName()).document(doc.getId());
                    dd.delete();
                }
                // Dismessprogress();
                if (getEventdbmissoncompliete() != null){
                    getEventdbmissoncompliete().messionComplete();
                }
            }

        })      ;

    }

    public   void UpdatewhereFirestore(Task<QuerySnapshot> where , Cell [] Cells  ) {
        addrow(Cells);

        where.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot doc : task.getResult()){
                    FirebaseFirestore query = FirebaseFirestore.getInstance();
                    DocumentReference dd = query.collection(getFirestorCollectionName()).document(doc.getId());
                    dd.update( getRow().row);
                }
                // Dismessprogress();
                if (getEventdbmissoncompliete() != null){
                    getEventdbmissoncompliete().messionComplete();
                }
            }

        })      ;


    }

    public void loadRoteanlistview(Query dp , int Rlayout , final GridView Gridl, ProgressBar progressBar   ) {
        setLayoutR(Rlayout);
        setGridView(Gridl);
        setprogressBar(progressBar);
        setEventsload(new EventsLoadData() {
            @Override
            public void onloadData(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onloadData(final ArrayList<Row> Table) {

                getListView().setAdapter(roteanadabtore());
                getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("Sdfsdf", "Sdf");

                        Dismessprogress();
                    }
                });
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
        setPerant(getMainparent());
        Query dpr = dp;
        LoadData(dpr, getCell());


    }


    public void loadRoteanRecycleviewview(String child , final int Rlayout , final RecyclerView recyclerView, final LinearLayoutManager len , ProgressBar progressBar   ) {
        setLayoutR(Rlayout);
        setRecyclerView(recyclerView);
        setprogressBar(progressBar);
        setEventsload(new EventsLoadData() {
            @Override
            public void onloadData(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onloadData(final ArrayList<Row> Table) {
                LinearLayoutManager llm  = len;
                getRecyclerView().setLayoutManager(llm);


                RecycleViewAdabtor recmainA = new RecycleViewAdabtor( getContext()  , Table ,Rlayout );

                recyclerView.setAdapter(recmainA);


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
        setPerant(getMainparent());
        DatabaseReference dpr = getdatabaseref(getContext()).child(child);
        LoadData(dpr, getCell());


    }
    RecycleViewAdabtor recmainA;

    public void loadRoteanRecyclerviewFirestre(Task<QuerySnapshot> task   , final int Rlayout , final RecyclerView recyclerView, final LinearLayoutManager len  , ProgressBar progressBar   ) {
        setLayoutR(Rlayout);
        setRecyclerView(recyclerView);
        setprogressBar(progressBar);
        setEventsload(new EventsLoadData() {
            @Override
            public void onloadData(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onloadData(final ArrayList<Row> Table) {
                LinearLayoutManager llm  = len;
                getRecyclerView().setLayoutManager(llm);
                getEventLoaded();

                 recmainA = new RecycleViewAdabtor( getContext()  , Table ,Rlayout );
                if (getAddvew() != null) {
                    recmainA.setaddview(getAddvew());
                }
                recyclerView.setAdapter(recmainA);
                recmainA.setContainerclick(new Recyclecontainerclecked() {
                    @Override
                    public void onclick(View v ,  Cell[] cell) {
                        if (getEventcontarers()!=null){
                            getEventcontarers().ItemCleicked(v ,  cell);
                        }
                    }
                });
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
        setPerant(getMainparent());
        loadQueryFireStore(task);

    }
    public void loadRoteanRecyclerviewFirestre(String collection , final int Rlayout , final RecyclerView recyclerView, final LinearLayoutManager len , ProgressBar progressBar   ) {
        setLayoutR(Rlayout);
        setRecyclerView(recyclerView);
        setprogressBar(progressBar);
        setEventsload(new EventsLoadData() {
            @Override
            public void onloadData(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onloadData(final ArrayList<Row> Table) {

                LinearLayoutManager llm  = len;
                getRecyclerView().setLayoutManager(llm);

                getEventLoaded();
                  recmainA = new RecycleViewAdabtor( getContext()  , Table ,Rlayout );
                if (getAddvew() != null) {
                    recmainA.setaddview(getAddvew());
                }
                recyclerView.setAdapter(recmainA);
                recmainA.setContainerclick( new Recyclecontainerclecked() {
                    @Override
                    public void onclick(View  v ,  Cell[] cell) {
                        if (getEventcontarers()!=null){
                            getEventcontarers().ItemCleicked(v ,  cell);
                        }
                    }
                });

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

        setFirestorCollectionName(collection);
        LoadDataFirestore(collection, getCell());


    }


    public void loadRoteanRecyclerviewFirestre(Task<QuerySnapshot> task   , final int Rlayout ,final  int Animlayout ,   final RecyclerView recyclerView, final LinearLayoutManager len  , ProgressBar progressBar   ) {
        setLayoutR(Rlayout);
        setRecyclerView(recyclerView);
        setprogressBar(progressBar);
        setEventsload(new EventsLoadData() {
            @Override
            public void onloadData(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onloadData(final ArrayList<Row> Table) {
                LinearLayoutManager llm  = len;
                getRecyclerView().setLayoutManager(llm);

                getRecyclerView().setLayoutAnimation(GetRecyclerviewAnimationLayout(Animlayout));

                getEventLoaded();

                recmainA = new RecycleViewAdabtor( getContext()  , Table ,Rlayout );
                if (getAddvew() != null) {
                    recmainA.setaddview(getAddvew());
                }
                recyclerView.setAdapter(recmainA);
                recmainA.setContainerclick(new Recyclecontainerclecked() {
                    @Override
                    public void onclick(View v ,  Cell[] cell) {
                        if (getEventcontarers()!=null){
                            getEventcontarers().ItemCleicked(v ,  cell);
                        }
                    }
                });
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
        setPerant(getMainparent());
        loadQueryFireStore(task);

    }
    public void loadRoteanRecyclerviewFirestre(String collection , final int Rlayout , final  int Animlayhout ,   final RecyclerView recyclerView, final LinearLayoutManager len , ProgressBar progressBar   ) {
        setLayoutR(Rlayout);
        setRecyclerView(recyclerView);
        setprogressBar(progressBar);
        setEventsload(new EventsLoadData() {
            @Override
            public void onloadData(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onloadData(final ArrayList<Row> Table) {

                LinearLayoutManager llm  = len;
                getRecyclerView().setLayoutManager(llm);
                getRecyclerView().setLayoutAnimation(GetRecyclerviewAnimationLayout(Animlayhout));

                getEventLoaded();
                recmainA = new RecycleViewAdabtor( getContext()  , Table ,Rlayout );
                if (getAddvew() != null) {
                    recmainA.setaddview(getAddvew());
                }
                recyclerView.setAdapter(recmainA);
                recmainA.setContainerclick(new Recyclecontainerclecked() {
                    @Override
                    public void onclick(View v ,  Cell[] cell) {
                        if (getEventcontarers()!=null){
                            getEventcontarers().ItemCleicked(v ,  cell);
                        }
                    }
                });

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

        setFirestorCollectionName(collection);
        LoadDataFirestore(collection, getCell());


    }

    public void loadRoteanlistviewFirestre(Task<QuerySnapshot> task   , int Rlayout , final ListView listV, ProgressBar progressBar   ) {
        setLayoutR(Rlayout);
        setListView(listV);
        setprogressBar(progressBar);
        setEventsload(new EventsLoadData() {
            @Override
            public void onloadData(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onloadData(final ArrayList<Row> Table) {
                getEventLoaded();
                getListView().setAdapter(roteanadabtore());
                getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("Sdfsdf", "Sdf");
                    }
                });
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
        loadQueryFireStore(task);

    }
    public void loadRoteanlistviewFirestre(String collection , int Rlayout , final ListView listV, ProgressBar progressBar   ) {
        setLayoutR(Rlayout);
        setListView(listV);
        setprogressBar(progressBar);
        setEventsload(new EventsLoadData() {
            @Override
            public void onloadData(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onloadData(final ArrayList<Row> Table) {

                getListView().setAdapter(roteanadabtore());
                getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("Sdfsdf", "Sdf");
                    }
                });
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
        setPerant(getMainparent());
        LoadDataFirestore(collection, getCell());


    }

    public void loadRoteangrideviewFirestre(Task<QuerySnapshot> task   , int Rlayout , final GridView listV, ProgressBar progressBar   ) {
        setLayoutR(Rlayout);
        setGridView(listV);
        setprogressBar(progressBar);
        setEventsload(new EventsLoadData() {
            @Override
            public void onloadData(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onloadData(final ArrayList<Row> Table) {
                getEventLoaded();
                getGridView().setAdapter(roteanadabtore());

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
        loadQueryFireStore(task);

    }
    public void loadRoteangridviewFirestre(String collection , int Rlayout , final GridView listV, ProgressBar progressBar   ) {
        setLayoutR(Rlayout);
        setGridView(listV);
        setprogressBar(progressBar);
        setEventsload(new EventsLoadData() {
            @Override
            public void onloadData(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onloadData(final ArrayList<Row> Table) {

                getGridView().setAdapter(roteanadabtore());
                getGridView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("Sdfsdf", "Sdf");
                    }
                });
                getEventLoaded();
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
        setPerant(getMainparent());
        LoadDataFirestore(collection, getCell());


    }


  public   void setinputvalue(   Cell [] Cell  ){


        int i , c  ;
        i = 0 ;  c = getCell().length ;
        while (i<c) {
            if (getCell()[i].getRinput() != 0) {


                if (Cell[i].getInputs() ==     Inputs.Textview) {

                    TextView txt = getActivity().findViewById(getCell()[i].getRinput());
                    String txts = Cell[i].getValue()  + "";

                    txt.setText(txts);

                }

                if (Cell[i].getInputs() ==     Inputs.EditText) {

                    EditText txt = getActivity().findViewById(getCell()[i].getRinput());
                    String txts = Cell[i].getValue()  + "";

                    txt.setText(txts);

                } else
                if (getCell()[i].getInputs() ==  Inputs.Switch) {

                    Switch txt = getActivity().findViewById(getCell()[i].getRinput());
                    Boolean txts = Boolean.parseBoolean(  Cell[i].getValue() + "" ) ;

                    txt.setChecked(txts);

                }
                if (getCell()[i].getInputs() ==  Inputs.Image) {

                    ImageView img = getActivity().findViewById(getCell()[i].getRinput());
                    String  uri =     Cell[i].getValue()  + "" ;
                    Picasso.with(context).load(uri).fit().into(img);


                }
                if (getCell()[i].getInputs() ==  Inputs.Spinner) {

                    Spinner snp = getActivity().findViewById(getCell()[i].getRinput());

                    String  data =     Cell[i].getValue()  + "" ;
                    if(Cell[i].isSpennervaluisid()){
int poss = Integer.parseInt(data);
                        snp.setSelection(poss);

                    }else {
                        Object [] array = Cell[i].getSpennerArray();
                        int pos = Arrays.binarySearch(array , data);
                        snp.setSelection(pos);

                    }


                } if (getCell()[i].getInputs() ==  Inputs.Rate) {

                    RatingBar ratingBar = getActivity().findViewById(getCell()[i].getRinput());
                    float  rat = Float.parseFloat(    Cell[i].getValue()  + "") ;
 ratingBar.setRating(rat);

                }


            }
            i++;
        }

    }
    public void loadRoteanlistview(Query dp , int Rlayout , final ListView listV, ProgressBar progressBar   ) {
        setLayoutR(Rlayout);
        setListView(listV);
        setprogressBar(progressBar);
        setEventsload(new EventsLoadData() {
            @Override
            public void onloadData(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onloadData(final ArrayList<Row> Table) {

                getListView().setAdapter(roteanadabtore());
                getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.e("Sdfsdf", "Sdf");

                        Dismessprogress();
                    }
                });
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
        Query dpr = dp;
        LoadData(dpr, getCell());


    }


    public void loadRoteangridview(String child , int Rlayout , final GridView gridView, ProgressBar progressBar   ) {
        setLayoutR(Rlayout);

        setprogressBar(progressBar);
        setEventsload(new EventsLoadData() {
            @Override
            public void onloadData(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onloadData(ArrayList<Row> Table) {

                gridView.setAdapter(roteanadabtore());

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
        setPerant(getMainparent()  );
        DatabaseReference dpr = getdatabaseref(getContext()).child(child);
        LoadData(dpr, getCell());

    }
    Object SearchData ;

    public Object getSearchData() {
        return SearchData;
    }

    public void setSearchData(Object searchData) {
        SearchData = searchData;
    }
    public  void getRotenValueFirestorefromDocument(String doc){

        getValueFirestoreDecoment(doc).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                GetDataOnloadSucsecessFireStore(documentSnapshot);

                setinputvalue(getCell());
            }
        });

    }

    public void loadSearch(String child , String id  , final ProgressBar progressBar  , EventsLoadData eventsLoadData  ) {

        setprogressBar(progressBar);
        setEventsload(  eventsLoadData );

        setPerant(getMainparent()  );
        DatabaseReference dpr = getdatabaseref(getContext()).child(child).child(id)   ;
        LoadDataitem(dpr, getCell() , id    );

    }

    public AddnewFeactuerAdaptor getAddvew() {
        return addvew;
    }

    public void setAddvew(AddnewFeactuerAdaptor addvew) {
        this.addvew = addvew;
    }

    AddnewFeactuerAdaptor addvew ;

    EventContanterintmCliked eventcontarers;
    public EventContanterintmCliked getEventcontarers() {
        return eventcontarers;
    }

    public void setEventcontarers(EventContanterintmCliked eventcontarers) {
        this.eventcontarers = eventcontarers;
    }


    int CollctoinCount ;

    public int getCollctoinCount() {
        return CollctoinCount;
    }

    public void LoadgetCollectionCountFirestore(String Collection, ProgressBar progressBar  ) {

        setprogressBar(progressBar);
       if(progressparEdnbled){
        Showprogress();}
        //  setLayoutR(R.layout.intem_catdata);
        FirebaseFirestore query = FirebaseFirestore.getInstance();
        query.collection(Collection)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        CollctoinCount = queryDocumentSnapshots.size();
                        //   Toast.makeText(getContext(), orderon + "", Toast.LENGTH_LONG).show();
                        Dismessprogress();
                    }
                });

    }




    Task<QuerySnapshot> FirestorQurery ;

    public Task<QuerySnapshot> getFirestorQurery() {
        return FirestorQurery;
    }

    public void setFirestorQurery(Task<QuerySnapshot> firestorQurery) {
        FirestorQurery = firestorQurery;
    }

    public   void loadQueryFireStore(Task<QuerySnapshot> task ) {

        if (progressparEdnbled){
            Showprogress();}
        ;

        task
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        Dismessprogress();
                        Log.i("no ", queryDocumentSnapshots.size() + "");

                        CollctoinCount =queryDocumentSnapshots.size();
                        getEventsload(queryDocumentSnapshots);

                    }
                })
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        getTable().clear();
                        GetDataOnloadSucsecessFireStore(task);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("onfeladEror ", e.getMessage());
                    }
                })
        ;

    }
    public   void LoadgetCollectionCountFirestore(Task<QuerySnapshot> task ) {
        if(progressparEdnbled){
            Showprogress();}
        ;

        task
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        Dismessprogress();
                        Log.i("no ", queryDocumentSnapshots.size() + "");

                        CollctoinCount =queryDocumentSnapshots.size();
                        getEventsload(queryDocumentSnapshots);
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        getEventsload(task);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("onfeladEror ", e.getMessage());
                    }
                })
        ;

    }

    public  double SumValue(ArrayList<Row> Table , Cell cell ){

        double value = 0  ;
        double sumvalue = 0 ;
        for (Row r:Table
        ) {
            value =  Double.parseDouble( r.getvalue(cell) + "");
            sumvalue +=value ;
        }
        return sumvalue;

    }
    ArrayList<EventLoaded> DataLoaded = new ArrayList<>();
    EventLoaded eventLoaded ;



    public void getEventLoaded() {


        for (EventLoaded l :DataLoaded
        ) {
            l.load();
        }
    }

    public void setEventLoaded(EventLoaded eventLoaded) {
        DataLoaded.add(eventLoaded);


    }

    public    interface LoadTabselected {

        void LoadselectedTab(TabLayout.Tab tab, String tabid);
    }

    public  interface EventLoaded{

        void load();
    }

   public void TableGroubbycount (ArrayList<Row> Table , int Rv , int Rn, Cell c){
        ArrayList<Row> Tablem = new ArrayList<>();

        Cell name = new Cell("n");
        Cell value = new Cell("v");
        name.setR(Rv);
        value.setR(Rn);
        setCell(new Cell[]{name , value});
        int ii = 0 ;
        while (ii<Table.size()){

            value.setValue(1);
            name.setValue(Table.get(ii).getvalue(c).toString());

            Tablem.add(addnewrow(new Cell[]{name , value}));

            ii++;
        }
        ArrayList<Row> TableG = new ArrayList<>();

        int i = 0 ;
        int ig = 0 ;
        int icount = 0 ;
        String groubtxt = "" ;
        while ( i < Tablem.size()){
            String s = Tablem.get(i).getvalue(name).toString();
            if (s.equals(groubtxt.trim()))
            {
                icount  = icount + Integer.parseInt( Tablem.get(i).getvalue(value).toString()) ;
                value.setValue(icount);
                name.setValue(Tablem.get(i).getvalue(name).toString());
                TableG.get(ig-1).setValuew(new Cell[]{name , value});
            }else {
                icount = 0 ;
                icount  = icount + Integer.parseInt( Tablem.get(i).getvalue(value).toString()) ;

                value.setValue(icount);
                name.setValue(Tablem.get(i).getvalue(name).toString());
                TableG.add(addnewrow(new Cell[]{name , value}));
                groubtxt = name.getValue() + "" ;

                ig++;

            }
            i++;
        }

        Log.i("sid" , Tablem.size() + "" );
        setTable(TableG);
    }


   public void TableGroubbysum (ArrayList<Row> Table, Cell nc , Cell vc){
        ArrayList<Row> Tablem = new ArrayList<>();

        Cell name = new Cell("n");
        Cell value = new Cell("v");
        name.setR(nc.getR());
        value.setR(vc.getR());
        setCell(new Cell[]{name , value});
        int ii = 0 ;
        while (ii<Table.size()){

            value.setValue(Table.get(ii).getvalue(vc).toString());
            name.setValue(Table.get(ii).getvalue(nc).toString());

            Tablem.add(addnewrow(new Cell[]{name , value}));

            ii++;
        }
        ArrayList<Row> TableG = new ArrayList<>();

        int i = 0 ;
        int ig = 0 ;
        double icount = 0 ;
        String groubtxt = "" ;
        while ( i < Tablem.size()){
            String s = Tablem.get(i).getvalue(name).toString();
            if (s.equals(groubtxt.trim()))
            {
                icount  = icount + Double.parseDouble( Tablem.get(i).getvalue(value).toString()) ;
                value.setValue(icount);
                name.setValue(Tablem.get(i).getvalue(name).toString());
                TableG.get(ig-1).setValuew(new Cell[]{name , value});
            }else {
                icount = 0 ;
                icount  = icount + Double.parseDouble( Tablem.get(i).getvalue(value).toString()) ;

                value.setValue(icount);
                name.setValue(Tablem.get(i).getvalue(name).toString());
                TableG.add(addnewrow(new Cell[]{name , value}));
                groubtxt = name.getValue() + "" ;

                ig++;

            }
            i++;
        }

        Log.i("sid" , Tablem.size() + "" );
        setTable(TableG);
    }


    public void RecyclerviewdeleteItemAnimation(View rowView, final int position, int Animation, int duration) {

        android.view.animation.Animation anim = AnimationUtils.loadAnimation( getContext(),
                Animation);
        anim.setDuration(duration);
        rowView.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if  (Table.size() == 0) {
                    // addEmptyView(); // adding empty view instead of the RecyclerView
                    return;
                }
                Table .remove(position); //Remove the current content from the array
                recmainA  .    notifyDataSetChanged(); //Refresh list
            }

        }, anim.getDuration());
    }

    public LayoutAnimationController GetRecyclerviewAnimationLayout(int resId){

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        return animation;
    }



}


