package com.illapp.illapp.Deposit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.illapp.illapp.R;
import com.illapp.illapp.Withdraw.Withdraw;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class etherscan extends AppCompatActivity {

    private TextView copyT;
    private ImageView copyI;

    private ImageView closedep;
    private TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etherscan);
        copyT =(TextView) findViewById(R.id.text_to_copy);
        copyI = (ImageView) findViewById(R.id.copy_address);

        closedep = (ImageView) findViewById(R.id.ic_close);



        long duration = TimeUnit.MINUTES.toMillis(1);

        new CountDownTimer(duration,1000){
            @Override
            public void onTick(long millisUntilFinished) {

                String sduration = String.format(Locale.ENGLISH,"%02d : %02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                - TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

                timer.setText(sduration);
            }

            @Override
            public void onFinish() {

                //when time finish
                timer.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"CountDown Finished",Toast.LENGTH_LONG).show();

            }
        };

        copyI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView",copyT.getText().toString());
                assert clipboardManager != null;
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(etherscan.this, "copied", Toast.LENGTH_SHORT).show();
            }
        });

        closedep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(etherscan.this, cryptodeposit.class);
                startActivity(intent);
                finish();
            }
        });


    }
}