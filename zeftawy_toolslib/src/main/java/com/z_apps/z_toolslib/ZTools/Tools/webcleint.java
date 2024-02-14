package com.z_apps.z_toolslib.ZTools.Tools;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

public class webcleint extends WebViewClient {
boolean isback ;
String Mainurl ;

    public String getMainurl() {
        return Mainurl;
    }

    public void setMainurl(String mainurl) {
        Mainurl = mainurl;
    }

    public boolean isIsback() {
        return isback;
    }

    public void setIsback(boolean isback) {
        this.isback = isback;
    }

    public ArrayList<String> getUrls() {
        return Urls;
    }
urichangeed urichangeed ;

    public webcleint.urichangeed getUrichangeed() {
        return urichangeed;
    }

    public void setUrichangeed(webcleint.urichangeed urichangeed) {
        this.urichangeed = urichangeed;
    }

    ArrayList<String> Urls = new ArrayList<String>();
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
urichangeed.uricahged();
if (isback){isback= false; }else {
Urls.add(url);}
       int i  = 0 ;
       while (i <= Urls.size()-1){

           Log.i("uri"+ i , Urls.get(i));
 i++ ;
       }

    }

public  interface  urichangeed  {
        void  uricahged();
}

}
