package com.z_apps.Helteycare.Project.Classes;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Loctions {

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

    public Loctions(double longat, double latatt, String name , int  res ) {
        this.longat = longat;
        this.latatt = latatt;
        this.name = name;
        this.res = res ;
    }

    public MarkerOptions getmapmark() {
        MarkerOptions mo = new MarkerOptions();
        mo.title(name);
        LatLng ny = new LatLng(latatt , longat);

        mo.position(ny);
        BitmapDescriptor bd = BitmapDescriptorFactory.fromResource(res);
        mo.icon(bd);

        return mo;
    }


}
