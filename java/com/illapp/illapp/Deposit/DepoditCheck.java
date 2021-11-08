package com.illapp.illapp.Deposit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.illapp.illapp.Dashzone.Dashboard;
import com.illapp.illapp.R;

public class DepoditCheck extends AppCompatActivity {

    private Button submit;
    private EditText amount;
    private ImageView close;
    private int amounting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depodit_check);

        submit = (Button) findViewById(R.id.proceed_to_pay);
        amount = (EditText) findViewById(R.id.depost_amount);

        close = (ImageView) findViewById(R.id.ic_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DepoditCheck.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deposit_check();
            }
        });
    }

    private void deposit_check()
    {
        amounting = Integer.parseInt(amount.getText().toString().trim());
        String amt = amount .getText().toString();

        if (amt.isEmpty()){

            Toast.makeText(this, "Enter deposit amount", Toast.LENGTH_SHORT).show();
        }

        else {

            withdraw_pop();

        }

    }

    private void withdraw_pop() {

        Intent intent = new Intent(DepoditCheck.this, cryptodeposit.class);
        intent.putExtra(cryptodeposit.AMOUNT,amounting);
        startActivity(intent);
        finish();
    }
}