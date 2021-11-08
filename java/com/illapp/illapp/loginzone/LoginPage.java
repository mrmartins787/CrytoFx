package com.illapp.illapp.loginzone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.illapp.illapp.Dashzone.Dashboard;
import com.illapp.illapp.Model.Users;
import com.illapp.illapp.Prevalent.Prevalent;
import com.illapp.illapp.R;
import com.illapp.illapp.registerzone.RegisterPage;

public class LoginPage extends AppCompatActivity {

    ImageView imageLogin;
    Animation animation;
    Button Sign_Btn;
    TextView  register_text;

    private EditText username,userpassword;

    private ProgressDialog loadingbar;
    private String parentDbName ="Users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        imageLogin = findViewById(R.id.image_login);
        register_text = findViewById(R.id.signup_text);
        Sign_Btn = findViewById(R.id.signin_btn);

        username = (EditText) findViewById(R.id.db_username);
        userpassword = (EditText) findViewById(R.id.db_password);

        loadingbar = new ProgressDialog(this);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);


        register_text.setAnimation(animation);


        Sign_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginUser();
            }
        });
        register_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(LoginPage.this, RegisterPage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void LoginUser()
    {
        String name = username.getText().toString();
        String password = userpassword.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingbar.setTitle("Login Account");
            loadingbar.setMessage("Please wait, while we are checking the credentials.");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();


            AllowAccessToAccount(name, password);
        }


    }

    private void AllowAccessToAccount(String phone, String password)
    {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if (snapshot.child(parentDbName).child(phone).exists()){

                    Users usersData = snapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if (usersData.getPhone().equals(phone)){

                        if (usersData.getPassword().equals(password))
                        {
                            Toast.makeText(LoginPage.this, "logged in Successfully...Welcome Home", Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();
                            Intent intent = new Intent(LoginPage.this, Dashboard.class);
                               Prevalent.onlineUsers = usersData;
                            startActivity(intent);
                        }
                        else
                        {
                            loadingbar.dismiss();
                            Toast.makeText(LoginPage.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                else {
                    Toast.makeText(LoginPage.this, "Account with this " + phone + " number do not exists.", Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginPage.this);
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