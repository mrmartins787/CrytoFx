package com.illapp.illapp.registerzone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.illapp.illapp.Dashzone.Dashboard;
import com.illapp.illapp.MainActivity;
import com.illapp.illapp.R;
import com.illapp.illapp.loginzone.LoginPage;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.regex.Pattern;

public class RegisterPage extends AppCompatActivity {

    private EditText username,fullname,address,email,password,confirmpassword,phonenumber;
    private Button btsubmit;
    private TextView login;
    AwesomeValidation awesomeValidation;

    private ProgressDialog loadingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        //Assign varialble
        username=(EditText) findViewById(R.id.et_username);
        fullname=(EditText) findViewById(R.id.et_fullname);
        address=(EditText) findViewById(R.id.et_wallet);
        email=(EditText) findViewById(R.id.et_email);
        password=(EditText) findViewById(R.id.et_password);
       // confirmpassword=(EditText) findViewById(R.id.et_confirm_password);
        phonenumber=(EditText) findViewById(R.id.et_phonenumber);

        login = (TextView) findViewById(R.id.signin_text);

        btsubmit = (Button) findViewById(R.id.et_register_btn);

        loadingbar = new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterPage.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        btsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });

    }

    private void CreateAccount() {

        String name = username.getText().toString();
        String phone = phonenumber.getText().toString();
        String ipassword = password.getText().toString();
        String wallet = address.getText().toString();
        String mail = email.getText().toString();
        String fname = fullname.getText().toString();


        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(ipassword))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(wallet))
        {
            Toast.makeText(this, "Please write your wallet address...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(mail))
        {
            Toast.makeText(this, "Please write your email address...", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(fname))
        {
            Toast.makeText(this, "Please write your full name...", Toast.LENGTH_SHORT).show();
        }

        else {
            loadingbar.setTitle("Create Account");
            loadingbar.setMessage("Please wait, while we are checking the credentials.");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();
        }

        ValidatephoneNumber(name, phone, ipassword,wallet,fname,mail);

    }

    private void ValidatephoneNumber(final String name, final String phone, final String ipassword, final String wallet, final String fname, String mail)
    {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (!(snapshot.child("Users").child(phone).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("password", ipassword);
                    userdataMap.put("name", name);
                    userdataMap.put("fullname", fname);
                    userdataMap.put("wallet", wallet);
                    userdataMap.put("email", mail);



                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(RegisterPage.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                        loadingbar.dismiss();

                                        Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        loadingbar.dismiss();
                                        Toast.makeText(RegisterPage.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterPage.this);
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