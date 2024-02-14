package com.z_apps.z_toolslib.ZTools.FierbaseDB;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public interface EventsLoadData {


    void onloadData(DataSnapshot dataSnapshot);
    void onloadData(ArrayList<Row> Table);
    void onisExcestsetvals();
    void onloadData(Task<QuerySnapshot> dataSnapshot);
void onloadsengledata(Row row);
void load(QuerySnapshot qu);

}
