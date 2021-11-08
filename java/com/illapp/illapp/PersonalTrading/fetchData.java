package com.illapp.illapp.PersonalTrading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import com.illapp.illapp.Dashzone.Dashboard;
import com.illapp.illapp.R;

public class fetchData extends AppCompatActivity {

    private WebView mypersontrade;
    ProgressBar progressBar;
    ProgressDialog progressDialog;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data);


        mypersontrade = (WebView) findViewById(R.id.webview);

        WebSettings webSettings=mypersontrade.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //mypersontrade.loadUrl("https://app.bitremi.com/");
        //code for opening app
        mypersontrade.setWebViewClient(new WebViewClient());
        mypersontrade.loadUrl("https://vigilant-nobel-a92c9b.netlify.app/");

        mypersontrade = (WebView) findViewById(R.id.webview);
        swipeRefreshLayout =(SwipeRefreshLayout) findViewById(R.id.swipe);

        progressBar=(ProgressBar) findViewById(R.id.progress);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Please wait");
        progressDialog.setTitle("Redirecting");

        //WebSettings webSettings = mypersontrade.getSettings();
        webSettings.setJavaScriptEnabled(true);




        //code for swipe
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mypersontrade.loadUrl("https://wizardly-davinci-4ff68c.netlify.app");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },4 * 1000);


            }
        });
        mypersontrade.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
                setTitle("Loading....");
                progressDialog.show();
                if (newProgress == 100) {
                    progressBar.setVisibility((View.GONE));
                    setTitle(view.getTitle());
                    progressDialog.dismiss();
                }
                super.onProgressChanged(view, newProgress);
            }

        });


        //Initializing connectionManager
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        //Get active Network info
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

        // Check network Status

        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()){
            //when network is inactive

            //using dialog

            Dialog dialog = new Dialog(this);
            //now for setting the context
            dialog.setContentView(R.layout.alert_dialog);

            //set outside touch
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);


            //set dialog width and height
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT);

            //set transparent background

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            //set animation
            dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

            //initialize the button for try again


            Button buttonRetry = dialog.findViewById(R.id.try_button);

            //perform onclick action listener

            buttonRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(fetchData.this, Dashboard.class);
                    startActivity(intent);
                    finish();

                }
            });

            dialog.show();
        }
        else {
            //when internet is active

            //load url in webview
            mypersontrade.loadUrl("https://vigilant-nobel-a92c9b.netlify.app/");
            mypersontrade.setWebViewClient(new WebViewClient());
        }
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(fetchData.this);
        builder.setMessage("Are you leaving ?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setPositiveButton("Return to app", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(fetchData.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}