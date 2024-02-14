package com.z_apps.Helteycare.Project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.z_apps.Helteycare.R;
import com.z_apps.z_toolslib.ZTools.Tools.webcleint;

public class Act_Web extends AppCompatActivity {
    WebView webView;
    ProgressBar press;
    webcleint webcleint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__web);
    String uri = getIntent().getStringExtra("uri");
        webView = findViewById(R.id.web);
        press = findViewById(R.id.pB1);

startwebshow(uri);
     }
    webcleint.urichangeed wbc = new webcleint.urichangeed() {
        @Override
        public void uricahged() {

        }
    };
    void startwebshow(String uri){
        webView.getSettings().setJavaScriptEnabled(true); // enable javascript
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        webView.getSettings().setSupportMultipleWindows(false);
        webView.getSettings().setSupportZoom(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true); // enable javascript
        webcleint = new webcleint();
        webcleint.setUrichangeed(wbc);
        webView.setWebViewClient(webcleint);

        webView.setWebChromeClient(new WebChromeClient()

        {


            public void onProgressChanged(WebView view, int newProgress){
                // Update the progress bar with page loading progress
                press.setVisibility(View.VISIBLE);
                press.setProgress(newProgress);
                if(newProgress == 100){
                    // Hide the progressbar
                    press.setVisibility(View.GONE);
                }
            }
        });
        webView.loadUrl(uri);
    }

}
