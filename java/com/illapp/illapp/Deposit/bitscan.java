package com.illapp.illapp.Deposit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.illapp.illapp.R;

public class bitscan extends AppCompatActivity {

   private  TextView copyT;
    private  ImageView copyI;

    private ImageView closedep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitscan);

        copyT =(TextView) findViewById(R.id.text_to_copy);
        copyI = (ImageView) findViewById(R.id.copy_address);

        closedep = (ImageView) findViewById(R.id.ic_close);
        copyI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView",copyT.getText().toString());
                assert clipboardManager != null;
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(bitscan.this, "copied", Toast.LENGTH_SHORT).show();
            }
        });

        closedep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bitscan.this, cryptodeposit.class);
                startActivity(intent);
                finish();
            }
        });

    }
}