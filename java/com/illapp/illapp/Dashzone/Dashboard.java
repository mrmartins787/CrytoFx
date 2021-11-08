package com.illapp.illapp.Dashzone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.illapp.illapp.Activity.Activities;
import com.illapp.illapp.Deposit.DepositedAmount;
import com.illapp.illapp.PersonalTrading.fetchData;
import com.illapp.illapp.PersonalTrading.personal;
import com.illapp.illapp.Prevalent.Prevalent;
import com.illapp.illapp.R;
import com.illapp.illapp.Records.myrecords;
import com.illapp.illapp.Withdraw.Withdraw;
import com.illapp.illapp.loginzone.LoginPage;

public class Dashboard extends AppCompatActivity {

    private CardView record,deposit,activity,withdraw,logout,personal;
    private TextView name;
    private  ImageView charts;

    private  String parentDbName ="Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        record = (CardView) findViewById(R.id.myrecords);
        deposit = (CardView) findViewById(R.id.deposit);
        activity = (CardView) findViewById(R.id.activity);
        withdraw = (CardView) findViewById(R.id.withdraw);
        logout = (CardView) findViewById(R.id.logout);
        personal = (CardView) findViewById(R.id.personal);

        charts = (ImageView) findViewById(R.id.chart);
        name = (TextView) findViewById(R.id.username_from_database);

        View uname= name.getRootView();
        name = uname.findViewById(R.id.username_from_database);

        name.setText(Prevalent.onlineUsers.getName());

        charts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, fetchData.class);
                startActivity(intent);
                finish();

            }
        });

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, Withdraw.class);
                startActivity(intent);
               // finish();

            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,myrecords.class);
                startActivity(intent);
               /// finish();

            }
        });

        ///

        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, DepositedAmount.class);
                startActivity(intent);
             //   finish();

            }
        });

        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, com.illapp.illapp.PersonalTrading.personal.class);
                startActivity(intent);
              //  finish();

            }
        });

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, Activities.class);
                startActivity(intent);
               // finish();

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, LoginPage.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private void withdraw()
    {
        final Dialog popup_dialog;
        Button withdraw,depositandearn;
        ImageView iv_close;
        popup_dialog = new Dialog(Dashboard.this);
        popup_dialog.show();
        popup_dialog.setContentView(R.layout.withdraw_layout);
        iv_close = (ImageView) popup_dialog.findViewById(R.id.cancel_with);
        withdraw = (Button) popup_dialog.findViewById(R.id.proceed_to_withdraw);
        depositandearn = (Button) popup_dialog.findViewById(R.id.proceed_to_deposit);


        //set dialog width and height
        popup_dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

        //set transparent background

        popup_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //set animation
        popup_dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup_dialog.dismiss();
            }
        });
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Dashboard.this, "Insufficient Funds", Toast.LENGTH_SHORT).show();
            }
        });

        depositandearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, DepositedAmount.class);
                startActivity(intent);
                finish();
            }
        });
        popup_dialog.setCanceledOnTouchOutside(false);
        popup_dialog.setCancelable(false);

    }

    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
        builder.setMessage("Are you leaving ?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setPositiveButton("Exit App", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finishAffinity();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}