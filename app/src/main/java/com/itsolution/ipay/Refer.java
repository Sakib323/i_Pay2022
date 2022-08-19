package com.itsolution.ipay;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;




public class Refer extends AppCompatActivity {
    Button refer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent1=getIntent();
        String username=intent1.getStringExtra("username1");
        refer=findViewById(R.id.refer);
        refer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt();
            }
        });

        AlertDialog.Builder alert= new AlertDialog.Builder(Refer.this);
        LayoutInflater inflater = (Refer.this).getLayoutInflater();
        alert.setTitle("Once a user use your number as refer code you will get 5000 diamond");
        alert.setCancelable(true);
        alert.setView(inflater.inflate(R.layout.refer,null));
        alert.setPositiveButton("Refer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                shareIt();
            }
        });
        alert.create().show();

    }



    private void shareIt() {

        Intent intent =new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "i Pay : play to earn Cash");
        Intent intent1=getIntent();
        String username=intent1.getStringExtra("username1");
        intent.putExtra(Intent.EXTRA_TEXT,"You can make up to $10 per day playing iPay  https://play.google.com/store/apps/details?id=com.itsolution.ipay Use this name as your refer code > "+username);
        intent.setType("text/plain");
        startActivity(intent);

    }
}