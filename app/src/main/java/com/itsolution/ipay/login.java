package com.itsolution.ipay;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class login extends AppCompatActivity {
    Button callSignUp,login_btn ;
    ImageView image;
    TextView logoText,sloganText;
    private CheckBox saveLoginCheckBox;
    TextInputLayout username1,password2;
    private SharedPreferences preferences;
    private RewardedAd mRewardedAd;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        preferences=this.getSharedPreferences("login",this.MODE_PRIVATE);


        String name=preferences.getString("name", "");
        String pass=preferences.getString("pass", "");

        callSignUp=findViewById(R.id.sign_up);
        username1=findViewById(R.id.username);

        password2=findViewById(R.id.password);


        username1.autofill(AutofillValue.forText(name));
        password2.autofill(AutofillValue.forText(pass));

        final ProgressBar simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);

        login_btn=findViewById(R.id.login);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                simpleProgressBar.setVisibility(View.VISIBLE);
                String userEnteredUsername=username1.getEditText().getText().toString().trim();
                String userEnteredUserPassword=password2.getEditText().getText().toString().trim();
                SharedPreferences.Editor ed=preferences.edit();
                ed.putString("name",userEnteredUsername);
                ed.putString("pass",userEnteredUserPassword);
                ed.apply();

                DatabaseReference reference= FirebaseDatabase.getInstance("https://i-pay-86691-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User");
                Query checkUser=reference.orderByChild("username").equalTo(userEnteredUsername);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            username1.setError(null);
                            username1.setErrorEnabled(false);

                            String passwordFromDB=snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                            if (passwordFromDB.equals(userEnteredUserPassword)){

                                username1.setError(null);
                                username1.setErrorEnabled(false);

                                String nameFromDB=snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                                String usernameFromDB=snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                                String phoneNoFromDB=snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                                String emailFromDB=snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                                int balanceFromDB=snapshot.child(userEnteredUsername).child("balance").getValue(int.class);
                                Intent intent=new Intent(getApplicationContext(),UserProfile.class);

                                SharedPreferences sharedPref = getSharedPreferences("info", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("name", nameFromDB);
                                editor.putString("username", usernameFromDB);
                                editor.putString("email", emailFromDB);
                                editor.putString("phoneNo", phoneNoFromDB);
                                editor.putString("password", passwordFromDB);
                                editor.putBoolean("savelogin",true);
                                editor.apply();


                                intent.putExtra("name",nameFromDB);
                                intent.putExtra("username",usernameFromDB);
                                intent.putExtra("email",emailFromDB);
                                intent.putExtra("phoneNo",phoneNoFromDB);
                                intent.putExtra("password",passwordFromDB);
                                intent.putExtra("balance",balanceFromDB);
                                startActivity(intent);

                            }
                            else {

                                password2.setError("Wrong Password");
                                password2.requestFocus();
                                simpleProgressBar.setVisibility(View.INVISIBLE);
                            }

                        }

                        else {

                            username1.setError("No Such User");
                            username1.requestFocus();
                            simpleProgressBar.setVisibility(View.INVISIBLE);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,SignUp.class);
                startActivity(intent);
            }
        });

    }

}