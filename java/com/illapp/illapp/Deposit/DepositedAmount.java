package com.illapp.illapp.Deposit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.illapp.illapp.Dashzone.Dashboard;
import com.illapp.illapp.R;
import com.illapp.illapp.Withdraw.Withdraw;

public class DepositedAmount extends AppCompatActivity {

    private Button deposit, request;
    private ImageView cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposited_amount);

        deposit = (Button) findViewById(R.id.depo_btn);
        request = (Button) findViewById(R.id.request);

        cancel = (ImageView) findViewById(R.id.cancel_with) ;
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DepositedAmount.this, "-No Data available-", Toast.LENGTH_SHORT).show();
            }
        });

        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DepositedAmount.this, DepoditCheck.class);
                startActivity(intent);
                finish();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DepositedAmount.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }


}