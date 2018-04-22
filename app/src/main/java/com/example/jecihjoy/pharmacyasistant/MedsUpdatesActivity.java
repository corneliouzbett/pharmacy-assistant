package com.example.jecihjoy.pharmacyasistant;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MedsUpdatesActivity extends AppCompatActivity{

        private WebView web;
        ProgressDialog progressDialog;
        String url = "https://www.pharmaceutical-journal.com/news-and-analysis/research-briefing/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meds_update);
        setTitle("Updates On Medicines");

        progressDialog = new ProgressDialog(this);


        web =(WebView) findViewById(R.id.web);

        progressDialog.show();
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        web.loadUrl(url);



        web.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });



        web.getSettings().setJavaScriptEnabled(true);
    }
}
