package com.itsolution.ipay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;


public class SignUp extends AppCompatActivity {
    TextInputLayout regName, regUsername, regEmail, regPhoneno, regPassword,refer;
    Button regBtn, regToLoginBtn;
    int regBalance;

    FirebaseDatabase rootNode;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneno = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.registerUser);
        regToLoginBtn = findViewById(R.id.reg_login_btn);

        refer=findViewById(R.id.refer);
        final ProgressBar simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();

        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this,login.class);
                startActivity(intent);
            }
        });


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences vpnon = getSharedPreferences("active", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = vpnon.edit();
                editor1.putBoolean("vpnactive",false);
                editor1.apply();


                String val = regName.getEditText().getText().toString();
                if (val.isEmpty()) {
                    regName.setError("field cannot be empty");
                    regName.setEnabled(true);
                }
                else {
                    regName.setError(null);
                    regName.setEnabled(false);
                }
                String refer_id=refer.getEditText().getText().toString();
                if(refer_id.isEmpty()){

                    SharedPreferences icoinbalance = getSharedPreferences("icoinbal", MODE_PRIVATE);
                    SharedPreferences.Editor editor4 = icoinbalance.edit();
                    editor4.putInt("number",0);
                    editor4.apply();
                }
                else{


                    SharedPreferences icoinbalance = getSharedPreferences("icoinbal", MODE_PRIVATE);
                    SharedPreferences.Editor editor4 = icoinbalance.edit();
                    editor4.putInt("number",5000);
                    editor4.apply();


                }
                String val2 = regUsername.getEditText().getText().toString();

                if (val2.isEmpty()) {
                    regUsername.setError("field cannot be empty");
                    regUsername.setEnabled(true);
                }
                else {
                    regUsername.setError(null);
                    regUsername.setEnabled(false);
                }

                String val3 = regEmail.getEditText().getText().toString();
                if (val3.isEmpty()) {
                    regEmail.setError("field cannot be empty");
                    regEmail.setEnabled(true);
                }
                else {
                    regEmail.setError(null);
                    regEmail.setEnabled(false);
                }
                String val4 = regPhoneno.getEditText().getText().toString();

                if (val4.isEmpty()) {
                    regPhoneno.setError("field cannot be empty");
                    regPhoneno.setEnabled(true);
                }
                else {
                    regPhoneno.setError(null);
                    regPhoneno.setEnabled(false);
                }

                String val5 = regPassword.getEditText().getText().toString();
                if (val5.isEmpty()) {
                    regPassword.setError("field cannot be empty");
                    regPassword.setEnabled(true);
                }
                else {
                    regPassword.setError(null);
                    regPassword.setEnabled(false);

                }
                if (val.isEmpty() ||  val2.isEmpty() || val3.isEmpty() || val4.isEmpty() || val5.isEmpty() )

                {
                    Toast.makeText(SignUp.this, "field cannot be empty", Toast.LENGTH_SHORT).show();

                }else {

                    DatabaseReference reference= FirebaseDatabase.getInstance("https://i-pay-86691-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User");
                    Query checkUser=reference.orderByChild("username").equalTo(val2);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()) {
                                regUsername.setError("This name already exist");
                                regUsername.setErrorEnabled(true);
                                regUsername.getEditText().setText("");
                                AlertDialog.Builder alert= new AlertDialog.Builder(SignUp.this);
                                LayoutInflater inflater = (SignUp.this).getLayoutInflater();
                                alert.setTitle("This Username already exist" );
                                alert.setCancelable(false);
                                alert.setView(inflater.inflate(R.layout.thisnameexist,null));
                                alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent=new Intent(SignUp.this,SignUp.class);
                                        startActivity(intent);
                                    }
                                });
                                alert.create().show();

                            } else {


                                SharedPreferences sharedPref = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
                                SharedPreferences.Editor editor1 = sharedPref.edit();
                                editor1.putInt("diamond",0);
                                editor1.putFloat("google",0);
                                editor1.putFloat("amazon",0);
                                editor1.putFloat("paypal",0);
                                editor1.putFloat("paytm",0);
                                editor1.putFloat("bitcoin",0);
                                editor1.putInt("freefire",0);
                                editor1.apply();

                                SharedPreferences game = getSharedPreferences("game2", MODE_PRIVATE);
                                SharedPreferences.Editor editor = game.edit();
                                editor.putBoolean("preview",true);
                                editor.apply();




                                SharedPreferences vpn = getSharedPreferences("vpncount", MODE_PRIVATE);
                                SharedPreferences.Editor editor3 = vpn.edit();
                                editor3.putBoolean("vpnon",false);
                                editor3.apply();


                                SharedPreferences count = getSharedPreferences("countdown", MODE_PRIVATE);
                                SharedPreferences.Editor editor2 = count.edit();
                                Calendar cal = Calendar.getInstance();
                                int current=cal.get(Calendar.DAY_OF_WEEK);

                                    if(current==7){
                                        editor2.putInt("day",1);
                                        editor2.apply();
                                    }

                                else {
                                    int currentday=cal.get(Calendar.DAY_OF_WEEK)+1;
                                    editor2.putInt("day",currentday);
                                    editor2.apply();
                                }


                                SharedPreferences day_of_sign_up=getSharedPreferences("day_of_sign_up",MODE_PRIVATE);
                                SharedPreferences.Editor sign_up_day=day_of_sign_up.edit();
                                sign_up_day.putInt("day",current);
                                sign_up_day.apply();




                                DatabaseReference reference;
                                simpleProgressBar.setVisibility(View.VISIBLE);
                                rootNode = FirebaseDatabase.getInstance("https://i-pay-86691-default-rtdb.asia-southeast1.firebasedatabase.app");
                                reference = rootNode.getReference("User");
                                String name = regName.getEditText().getText().toString();
                                String username = regUsername.getEditText().getText().toString();
                                String email = regEmail.getEditText().getText().toString();
                                String phoneNo = regPhoneno.getEditText().getText().toString();
                                String password = regPassword.getEditText().getText().toString();
                                int balance=regBalance;
                                UserHelper helperClass = new UserHelper(name,username,email,balance,phoneNo,password);
                                reference.child(username).setValue(helperClass);
                                Intent intent=new Intent(SignUp.this,login.class);
                                startActivity(intent);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });
    }


}