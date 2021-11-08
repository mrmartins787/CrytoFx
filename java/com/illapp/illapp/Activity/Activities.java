package com.illapp.illapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.illapp.illapp.Dashzone.Dashboard;
import com.illapp.illapp.Deposit.DepositedAmount;
import com.illapp.illapp.R;

public class Activities extends AppCompatActivity {
    private ImageView cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        cancel = (ImageView) findViewById(R.id.cancel_with) ;
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activities.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }
}