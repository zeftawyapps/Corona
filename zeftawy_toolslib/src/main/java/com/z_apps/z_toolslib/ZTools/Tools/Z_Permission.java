package com.z_apps.z_toolslib.ZTools.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;

public class Z_Permission {
    ArrayList<String> pisrmssion ;
Activity c ;
    public Z_Permission(Activity act , ArrayList<String> pisrmssion) {

        this.pisrmssion = pisrmssion;
        c = act;
    }

    public Z_Permission(Context act ) {
     pisrmssion = new ArrayList<String>();
        c = (Activity) act;
    }
public  void AddPrimssion(String s ){
        pisrmssion.add(s);
}
   boolean ispissed = false ;

    public boolean isIspissed() {

        for ( String pr :pisrmssion) {

            if (ContextCompat.checkSelfPermission(c, pr) == PackageManager.PERMISSION_GRANTED) {


            }
        }
        if (pisrmssion.size()==0){return true;}else
        {  return false;}
    }

    public void setIspissed(boolean ispissed) {
        this.ispissed = ispissed;
    }

    public void CheckPirmission(){
   ArrayList<String> ss = new ArrayList<String>();
    if   (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        for ( String pr :pisrmssion) {
            if (ContextCompat.checkSelfPermission(c, pr) == PackageManager.PERMISSION_GRANTED) {
                ispissed = false ;
            }
                ss.add(pr);
        }
        String pars[] =  ss.toArray(new String[ss.size()]);
        c.requestPermissions(pars , 1);

    }


}


}
