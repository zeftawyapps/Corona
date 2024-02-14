package com.z_apps.z_toolslib.ZTools.FierbaseDB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;


public class Baseadabtor extends BaseAdapter {
   private ArrayList<Row> Rows;
   LayoutInflater inflater;
int LayoutR ;
   DataView dataView; Context context;


    public Baseadabtor(Context cont, ArrayList<Row> object , int LR, DataView dv ) {
        inflater = LayoutInflater.from(cont);
        this.Rows = object; LayoutR = LR;
        dataView = dv ; context =cont;
    }

    @Override
   public int getCount() {
       return Rows.size();
   }

   @Override
   public Object getItem(int position) {
       return Rows.get(position);
   }
    public Row getItemvalue(int position) {
        return Rows.get(position);
    }


   @Override
   public long getItemId(int position) {
       return position;
   }
    @Override
   public View getView(int position, View convertView, ViewGroup parent) {




       return dataView.bindview(position,convertView,context,LayoutR,Rows);
   }
}

