package com.example.proven.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proven.R;
import com.example.proven.model.Users;
import com.example.proven.view.dataAnalytics.CustomBtnActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    private Button createAccount;

    private EditText registerName, registerPhoneNum, registerPassNum;
    private ProgressDialog LoadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        createAccount = (Button) findViewById(R.id.createAccount);
        registerName = (EditText) findViewById(R.id.registerName);
        registerPhoneNum = (EditText) findViewById(R.id.registerPhoneNum);
        registerPassNum = (EditText) findViewById(R.id.registerPassNum);
        LoadingBar = new ProgressDialog(this);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });
    }


    private void createAccount() {

        String name = registerName.getText().toString();
        String pass = registerPassNum.getText().toString();
        String phone = registerPhoneNum.getText().toString();

        // if the user forget to fill all fieds

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please, Write Your Name...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Please, Write Your Password...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please, Write Your Phone Number...", Toast.LENGTH_SHORT).show();
        } else {
            // dialogue to tell user about loading

            LoadingBar.setTitle("Create Account");
            LoadingBar.setMessage("Please Wait, While we are Checking the Credentials");
            LoadingBar.setCanceledOnTouchOutside(false);
            LoadingBar.show();


            //consider phone number as uniqe key for the user.

            ValidatePhoneNumber(name, pass, phone);

        }

    }


    //if phone validate

    private void ValidatePhoneNumber(final String name, final String passWord, final String Phone) {

        //storing data

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        //read data
        RootRef.child("users").child(Phone).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            //retrieve data
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //if number is invalid

                if (!(snapshot.exists())) {


                    RootRef.child("users").child(Phone).setValue(new Users(name, Phone, passWord))
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override

                                //After discover that number is invalid, this step is to create another account

                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this, "Congratulation, Your Account has been Created.", Toast.LENGTH_SHORT).show();
                                        LoadingBar.dismiss();

                                        //take user to login page

                                        Intent intent = new Intent(Register.this, CustomBtnActivity.class);
                                        startActivity(intent);
                                        //creating account is failed
                                    } else {
                                        LoadingBar.dismiss();
                                        Toast.makeText(Register.this, "Network Erroe, Please Try Again afte another Time...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                } else {

                    //Number is valid

                    Toast.makeText(Register.this, "The" + Phone + "Already Exists.", Toast.LENGTH_SHORT).show();
                    LoadingBar.dismiss();

                    //undefined problem

                    Toast.makeText(Register.this, "NetWork Error, Please Try Again Using Another Phone Number.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Register.this, CustomBtnActivity.class);
                    startActivity(intent);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}


