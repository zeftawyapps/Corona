package com.z_apps.z_toolslib.ZTools.Tools;

import android.content.Context;

import com.google.android.gms.maps.model.*;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.LatLng;

import com.z_apps.z_toolslib.ZTools.Tools.Z_Loction;

public class Z_map extends Z_Loction {

    public  Z_map (Context c){

        super(c);
    }

    double longat , latatt ;
    String name ; int res ;

    public double getLongat() {
        return longat;
    }

    public void setLongat(double longat) {
        this.longat = longat;
    }

    public double getLatatt() {
        return latatt;
    }

    public void setLatatt(double latatt) {
        this.latatt = latatt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public Z_map(double longat, double latatt, String name , int  res ) {
        this.longat = longat;
        this.latatt = latatt;
        this.name = name;
        this.res = res ;
    }

    public MarkerOptions getmapmark() {
        MarkerOptions mo = new MarkerOptions ();
        mo.title(name);
        LatLng ny = new LatLng(latatt , longat);

        mo.position(ny);
        BitmapDescriptor bd = BitmapDescriptorFactory.fromResource(res);
        mo.icon(bd);

        return mo;
    }


}
