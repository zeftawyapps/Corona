package com.z_apps.z_toolslib.ZTools.DB;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

public class Curseradaptor extends CursorAdapter {
    DataView dataView;
    int LayoutR;
    public Curseradaptor(Context context, Cursor cursor, int R, DataView dataViews)
    {
        super(context, cursor, 0);
        LayoutR = R ;
        dataView =dataViews;
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(LayoutR, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        dataView.bindview(view,cursor);
        // Find fields to populate in inflated template
    /*    TextView idt = (TextView) view.findViewById(R.id.idtext);
        TextView nametex = (TextView) view.findViewById(R.id.Namttv);

        TextView phonted =(TextView) view.findViewById(R.id.Phonetv);
        // Extract properties from cursor
        String ID = cursor.getString(cursor.getColumnIndexOrThrow("_id")) ;

        String Name =   cursor.getString(cursor.getColumnIndexOrThrow("Name"));
        String Phone =   cursor.getString(cursor.getColumnIndexOrThrow("Phone"));


          idt.setText(ID);
        nametex.setText(Name);

        // Populate fields with extracted properties

        phonted.setText(Phone);*/

    }
}
