package com.itsolution.ipay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Withdrawal extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextInputLayout Full_Name,Ammount,Email,Phone,PaymentInfo;
    Button withdraw_bal;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_withdrawl);

        Intent intent1=getIntent();
        int balance=intent1.getIntExtra("balance",0);

        AlertDialog.Builder alert= new AlertDialog.Builder(Withdrawal.this);
        LayoutInflater inflater = (Withdrawal.this).getLayoutInflater();
        alert.setTitle("Account Balance is " +balance);
        alert.setMessage("Minimum Cashout limit is 100000 iCoin");
        if(balance<100000){
            Toast.makeText(Withdrawal.this,"Insufficient Balance",Toast.LENGTH_SHORT).show();
        }
        alert.setCancelable(false);
        alert.setView(inflater.inflate(R.layout.withdraw,null));
        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(balance<100000){
                    finish();
                }

            }
        });
        alert.create().show();


        Spinner spinner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(Withdrawal.this,R.array.number, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(Withdrawal.this);


        Full_Name=findViewById(R.id.full_name);
        Ammount=findViewById(R.id.ammount);
        Email=findViewById(R.id.email);
        Phone=findViewById(R.id.phoneNo);
        PaymentInfo=findViewById(R.id.paymentinfo);
        final ProgressBar simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);

        Intent intent=getIntent();

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();

        int user_balance = intent.getIntExtra("balance", 0);
        withdraw_bal=findViewById(R.id.withdraw);
        withdraw_bal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String bal1 = Ammount.getEditText().getText().toString();
                int balance_to_comp=Integer.parseInt(bal1);
                if(balance_to_comp  > user_balance ||  balance_to_comp<100000){


                    AlertDialog.Builder alert= new AlertDialog.Builder(Withdrawal.this);
                    LayoutInflater inflater = (Withdrawal.this).getLayoutInflater();
                    alert.setTitle("Your Account Balance is " +balance);
                    if(balance<100000){
                        Toast.makeText(Withdrawal.this,"Place the right amount or insufficient coins",Toast.LENGTH_SHORT).show();
                    }
                    alert.setCancelable(false);
                    alert.setView(inflater.inflate(R.layout.youlose,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                         finish();
                        }
                    });
                    alert.create().show();

                }
                else{
                    simpleProgressBar.setVisibility(View.VISIBLE);
                    rootNode = FirebaseDatabase.getInstance("https://i-pay-86691-default-rtdb.asia-southeast1.firebasedatabase.app");
                    reference = rootNode.getReference("Withdrawl_Request");
                    String name = Full_Name.getEditText().getText().toString();
                    String email = Email.getEditText().getText().toString();
                    String phoneNo = Phone.getEditText().getText().toString();
                    int balance = balance_to_comp;
                    UserHelper helperClass = new UserHelper(name, email,phoneNo,balance,"","");
                    reference.child(phoneNo).setValue(helperClass);


                    SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                    int iCoin_balance=coin.getInt("number",0);
                    int updateCoins=iCoin_balance+balance;
                    SharedPreferences.Editor EDITOR=coin.edit();
                    EDITOR.putInt("number",updateCoins);
                    EDITOR.apply();


                    Toast.makeText(Withdrawal.this,"Congress!You have successfully initiated the request for withdraw",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String Text=parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}