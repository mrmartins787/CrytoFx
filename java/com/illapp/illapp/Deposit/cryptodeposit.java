package com.illapp.illapp.Deposit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.illapp.illapp.Dashzone.Dashboard;
import com.illapp.illapp.R;
import com.illapp.illapp.Records.myrecords;
import com.illapp.illapp.Withdraw.Withdraw;

import java.io.IOException;
import java.util.UUID;

public class cryptodeposit extends AppCompatActivity {

    public  static  final  String AMOUNT = "AMOUNT";

    private TextView amountshow;
    private  int amt;
    private ImageView close;
    private static final int PICK_IMAGE_REQUEST = 1;

    private CardView ether,lite,btc;
    private Button upload,submit;
    private Uri filepath;
    FirebaseStorage storage;
    StorageReference storageReference;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cryptodeposit);
        amountshow = findViewById(R.id.amountfromwith);

        ether = (CardView) findViewById(R.id.ether_code);
        btc = (CardView) findViewById(R.id.bitcoin_code);
        lite = (CardView) findViewById(R.id.litecoin_code);

        upload = (Button) findViewById(R.id.proceed_to_screen);
        submit = (Button) findViewById(R.id.proceed_to_db);

        close = (ImageView) findViewById(R.id.cancel_with);

        imageView = (ImageView) findViewById(R.id.imageView);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(cryptodeposit.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        btc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(cryptodeposit.this, "Bitcoin", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(cryptodeposit.this, bitscan.class);
                startActivity(intent);
                finish();
            }
        });
        ether.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cryptodeposit.this, "Ethereum", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(cryptodeposit.this, etherscan.class);
                startActivity(intent);
               finish();
            }
        });
        lite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cryptodeposit.this, "Litecoin", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(cryptodeposit.this, litcoin.class);
                startActivity(intent);
                finish();
            }
        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });


        Intent intent = getIntent();
        amt = intent.getIntExtra(AMOUNT, 0);




        amountshow.setText("You are to make payment of $" + amt + " using your preferred mode of payment below. Screenshot the proof of payment");


    }

    private void uploadImage() {

        final ProgressDialog progressDialog = new ProgressDialog(cryptodeposit.this);
        progressDialog.setTitle("Uploading...");
        progressDialog.setMessage("Please wait while file is being uploaded");
        progressDialog.show();

        StorageReference reference = storageReference.child("image/*"+ UUID.randomUUID().toString());
        reference.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        progressDialog.dismiss();
                        Toast.makeText(cryptodeposit.this,"-Uploaded Successfully-", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(cryptodeposit.this, Dashboard.class);
                        startActivity(intent);
                        finish();
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        progressDialog.dismiss();
                        Toast.makeText(cryptodeposit.this,"Failed."+ e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                        double progress = (100.0* snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                        progressDialog.setMessage("Successful " + (int) progress + "%");
                    }
                });


    }

    private void openFileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent,"select image"), PICK_IMAGE_REQUEST);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            {
                filepath = data.getData();
                try
                {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(cryptodeposit.this.getContentResolver(),filepath);
                    imageView.setImageBitmap(bitmap);
                }
                catch (IOException e)
                {

                    e.printStackTrace();
                }
            }

        }
    }
}