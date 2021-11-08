package com.illapp.illapp.Records;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.illapp.illapp.Prevalent.Prevalent;
import com.illapp.illapp.R;

public class myrecords extends AppCompatActivity {

    private TextView name,email,phone,userwallet,header;

    private  String parentDbName ="Users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecords);

        name = (TextView) findViewById(R.id.user_full_name);
        header = (TextView) findViewById(R.id.username_from_database);
        View uname= name.getRootView();
        name = uname.findViewById(R.id.user_full_name);
        name.setText(Prevalent.onlineUsers.getName());

        email = (TextView) findViewById(R.id.user_email_add);
        userwallet = (TextView) findViewById(R.id.user_wallet_id);
        phone = (TextView) findViewById(R.id.user_phone_number);

        ///
        View hname= name.getRootView();
        header = hname.findViewById(R.id.username_from_database);
        header.setText(Prevalent.onlineUsers.getName());

        View vemail = email.getRootView();
        email = vemail.findViewById(R.id.user_email_add);
        email.setText(Prevalent.onlineUsers.getMail());

        View vwallet = userwallet.getRootView();
        userwallet = vwallet.findViewById(R.id.user_wallet_id);
        userwallet.setText(Prevalent.onlineUsers.getIwallet());

        View vphone = phone.getRootView();
        phone = vphone.findViewById(R.id.user_phone_number);
        phone.setText(Prevalent.onlineUsers.getPhone());
    }
}