package com.z_apps.z_toolslib.ZTools.FierbaseDB;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

import org.json.JSONObject;

import java.util.ArrayList;


public interface DataView {
    void  bindview(View view, Cursor cursor);
      View bindview(int position, View convertView, Context context, int Rlayou, ArrayList<Row> rows) ;
    View bindviewOnline(int position, View convertView, Context context, int Rlayou, ArrayList<JSONObject> rows) ;

}
