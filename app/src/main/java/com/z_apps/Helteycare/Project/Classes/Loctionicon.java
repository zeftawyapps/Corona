package com.z_apps.Helteycare.Project.Classes;

import android.content.Context;
import android.database.Cursor;
import android.location.Geocoder;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.GeoPoint;
import com.z_apps.Helteycare.R;
import com.z_apps.z_toolslib.ZTools.DB.Baseadabtor;
import com.z_apps.z_toolslib.ZTools.DB.Cell;
import com.z_apps.z_toolslib.ZTools.DB.DataView;
import com.z_apps.z_toolslib.ZTools.DB.Row;
import com.z_apps.z_toolslib.ZTools.DB.Z_DataTable;
import com.z_apps.z_toolslib.ZTools.DB.Z_Database_Connection;

import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.RequiresApi;

public class Loctionicon extends Z_DataTable {
    GeoPoint geoPoint ;

 Cell id = new Cell("id");
  Cell name = new Cell("name");
 public Cell iconno = new Cell("iconno");

 ArrayList<Row> rows  = new ArrayList<>();

    public ArrayList<Row> getRows() {
        return rows;
    }

    public Loctionicon(Z_Database_Connection connection) {
        super(connection);


 setCell(new   Cell[]{id ,name , iconno });
     }

    public Loctionicon(int Rlayout, Context cont) {
        super(Rlayout, cont);
        setCell(new   Cell[]{id ,iconno ,  name   });

    }

public  void  addloctoin()
{

    Row r = new Row(getCell());
    r.setValues(new String[]{0 + "" , R.drawable.loc + "" , "0"});

    Row r1 = new Row(getCell());
    r1.setValues(new String[]{1 + "" , R.drawable.loc1 + "" , "1"});

    Row r2 = new Row(getCell());
    r2.setValues(new String[]{2 + "" , R.drawable.loc2 + "" , "2"});

    Row r3 = new Row(getCell());
    r3.setValues(new String[]{3 + "" , R.drawable.loc3 + "" , "3"});

    Row r4 = new Row(getCell());
    r4.setValues(new String[]{4 + "" , R.drawable.loc4 + "" , "4"});

    Row r5 = new Row(getCell());
    r5.setValues(new String[]{5 + "" , R.drawable.loc5 + "" , "5"});

    Row r6 = new Row(getCell());
    r6.setValues(new String[]{6 + "" , R.drawable.loc6 + "" , "6"});

    Row r7 = new Row(getCell());
    r7.setValues(new String[]{7 + "" , R.drawable.loc7 + "" , "7"});

    Row r8 = new Row(getCell());
    r8.setValues(new String[]{8 + "" , R.drawable.loc8 + "" , "8"});

    Row r9 = new Row(getCell());
    r9.setValues(new String[]{9 + "" , R.drawable.loc9 + "" , "9"});

rows.add(r); rows.add(r1); rows.add(r2); rows.add(r3); rows.add(r4); rows.add(r5);
rows.add(r6); rows.add(r7); rows.add(r8);  rows.add(r9);
}

public Baseadabtor showlist(){

    DataView dataView = new DataView() {
        @Override
        public void bindview(View view, Cursor cursor) {

        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public View bindview(int position, View convertView, Context context, int Rlayou, ArrayList<Row> rows) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(Rlayou, null);


             View v1 = convertView.findViewById(R.id.txtname);
            TextView t = (TextView) v1;
            t.setText( rows.get(position).getvalue(name) + "");
            View v2 = convertView.findViewById(R.id.img);

            int Rt = Integer.parseInt( rows.get(position).getvalue(iconno) + "");
            ImageView img  = (ImageView) v2;
            img.setBackground( getContext().getDrawable(Rt));


            return convertView;
        }

        @Override
        public View bindviewOnline(int position, View convertView, Context context, int Rlayou, ArrayList<JSONObject> rows) {
            return null;
        }
    };
 return new  Baseadabtor(getContext() ,rows,R.layout.item_list , dataView) ;


}

}
