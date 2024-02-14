package com.z_apps.Helteycare.Project.Classes;

import java.util.ArrayList;

public class SuberAsmin {

ArrayList<String > admins = new ArrayList<>();

    public SuberAsmin() {

//        admins.add("01552530994");
        admins.add("01120031403");

        admins.add("0667967661");
        admins.add("0556921782");

        admins.add("0777619447");
        admins.add("0774747678");
        admins.add("0667967661");
        admins.add("0556921782");

    }

    public  boolean isAdmin(String phone){

    return admins.contains(phone);


    }
}
