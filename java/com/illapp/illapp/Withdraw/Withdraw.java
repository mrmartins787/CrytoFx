package com.illapp.illapp.Withdraw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.illapp.illapp.Dashzone.Dashboard;
import com.illapp.illapp.Deposit.DepositedAmount;
import com.illapp.illapp.MainActivity;
import com.illapp.illapp.R;
import com.illapp.illapp.loginzone.LoginPage;

public class Withdraw extends AppCompatActivity {

    private EditText amount, password, btc;
    private Button submit;
    private ImageView cancel;
    private ProgressDialog loadingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        amount = (EditText) findViewById(R.id.with_amount);
        password = (EditText) findViewById(R.id.with_pass);
        btc = (EditText) findViewById(R.id.with_btc);

        cancel = (ImageView) findViewById(R.id.cancel_with) ;

        loadingbar = new ProgressDialog(this);
        ///
        submit = (Button) findViewById(R.id.with_btn);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Withdraw.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                withdraw();
            }
        });
    }

    private void withdraw()
    {

        String amt = amount .getText().toString();
        String pwrd = password .getText().toString();
        String address = btc .getText().toString();

        if (amt.isEmpty())
        {

           Toast.makeText(this, "Enter card amount", Toast.LENGTH_SHORT).show();

        }
       else if (pwrd.isEmpty())
        {

            Toast.makeText(this, "Enter card password", Toast.LENGTH_SHORT).show();

        }
      else   if (address.isEmpty())
        {
           // Toast toast = new Toast(getApplicationContext());
           // toast.setDuration(Toast.LENGTH_SHORT);
            //View custom_view = getLayoutInflater().inflate(R.layout.toastamount, null);
            //toast.setView(custom_view);
            //toast.show();
            Toast.makeText(this, "Enter card address", Toast.LENGTH_SHORT).show();

        }
        else {

            //loadingbar.setTitle("Uploading.....");
           // loadingbar.setMessage("Please wait, while your order is being processed by engine...");
           // loadingbar.setCanceledOnTouchOutside(false);
           // loadingbar.show();
            withdraw_pop();



        }

    }

    private void withdraw_pop()
    {


        final Dialog popup_dialog;
        Button withdraw,depositandearn;
        ImageView iv_close;
        popup_dialog = new Dialog(Withdraw.this);
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
                Toast.makeText(Withdraw.this, "Insufficient Funds", Toast.LENGTH_SHORT).show();
            }
        });

        depositandearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Withdraw.this, DepositedAmount.class);
                startActivity(intent);
                finish();
            }
        });
        popup_dialog.setCanceledOnTouchOutside(false);
        popup_dialog.setCancelable(false);


    }
}