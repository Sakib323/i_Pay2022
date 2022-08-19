package com.itsolution.ipay;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;




public class vpnconnection extends AppCompatActivity {
    Button refer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpnconnection);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();

        refer=findViewById(R.id.refer);
        refer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt();
            }
        });



    }



    private void shareIt() {

        Intent goToMarket = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://play.google.com/store/search?q=vpn&c=apps"));
        startActivity(goToMarket);

    }
}