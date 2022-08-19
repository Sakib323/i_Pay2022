package com.itsolution.ipay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;

public class See_All extends AppCompatActivity {
    private ImageView spin,slot,rewarded,DICE,PopMeforLotto,candy_crush,flyingfish,space,quizquestion,refer2earn,guessdigit,youtube,Browser,hourlyreward,diamondmarket,cell13casino,cell38casino,VOUCHER,fortune,Browser1,Youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_see_all);
        final MediaPlayer mp = MediaPlayer.create(See_All.this, R.raw.click);



        Browser1=findViewById(R.id.Browser1);
        Browser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=getIntent();
                String user_username=intent1.getStringExtra("username1");
                Intent intent = new Intent(See_All.this,Browser.class);
                intent.putExtra("username1",user_username);
                startActivity(intent);
                mp.start();
            }
        });
        Youtube=findViewById(R.id.youtube);
        Youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=getIntent();
                String user_username=intent1.getStringExtra("username1");
                Intent intent = new Intent(See_All.this,Youtube.class);
                intent.putExtra("username1",user_username);
                startActivity(intent);
                mp.start();
            }
        });

        VOUCHER=findViewById(R.id.voucher);
        VOUCHER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=getIntent();
                String user_username=intent1.getStringExtra("username1");
                Intent intent = new Intent(See_All.this,voucher.class);
                intent.putExtra("username1",user_username);
                startActivity(intent);
                mp.start();
            }
        });

        cell38casino=findViewById(R.id.cell38casino);
        cell38casino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=getIntent();
                String user_username=intent1.getStringExtra("username1");
                Intent intent = new Intent(See_All.this,casino.class);
                intent.putExtra("username1",user_username);
                startActivity(intent);
                mp.start();
            }
        });
        cell13casino=findViewById(R.id.cell13casino);
        cell13casino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=getIntent();
                String user_username=intent1.getStringExtra("username1");
                Intent intent = new Intent(See_All.this,mincasino.class);
                intent.putExtra("username1",user_username);
                startActivity(intent);
                mp.start();
            }
        });

        diamondmarket=findViewById(R.id.diamondmarket);
        diamondmarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=getIntent();
                String user_username=intent1.getStringExtra("username1");
                Intent intent = new Intent(See_All.this,Market.class);
                intent.putExtra("username1",user_username);
                startActivity(intent);
                mp.start();
            }
        });

        hourlyreward=findViewById(R.id.hourlyreward);
        hourlyreward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=getIntent();
                String user_username=intent1.getStringExtra("username1");
                Intent intent = new Intent(See_All.this,ClaimVoucher.class);
                intent.putExtra("username1",user_username);
                startActivity(intent);
                mp.start();
            }
        });


        flyingfish=findViewById(R.id.fishrun);
        flyingfish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=getIntent();
                String user_username=intent1.getStringExtra("username1");
                Intent intent = new Intent(See_All.this,MainActivity2.class);
                intent.putExtra("username1",user_username);
                startActivity(intent);
                mp.start();
            }
        });


        DICE=findViewById(R.id.dice);
        DICE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context=See_All.this;
                ConnectivityManager cManager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    Network activeNet=cManager.getActiveNetwork();
                    NetworkCapabilities netCaps=cManager.getNetworkCapabilities(activeNet);
                    boolean vpn=netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                    if(vpn==true){

                        Intent intent1=getIntent();
                        String user_username=intent1.getStringExtra("username1");
                        Intent intent2 = new Intent(See_All.this,Dice.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",75);
                        startActivity(intent2);
                        mp.start();
                    }else{
                        Intent intent1=getIntent();
                        String user_username=intent1.getStringExtra("username1");
                        Intent intent2 = new Intent(See_All.this,Dice.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",40);
                        startActivity(intent2);
                        mp.start();
                    }
                }
            }
        });


        Browser=findViewById(R.id.Browser);
        Browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=See_All.this;
                ConnectivityManager cManager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    Network activeNet=cManager.getActiveNetwork();
                    NetworkCapabilities netCaps=cManager.getNetworkCapabilities(activeNet);
                    boolean vpn=netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                    if(vpn==true){
                        Intent intent1=getIntent();
                        String user_username=intent1.getStringExtra("username1");
                        Intent intent2 = new Intent(See_All.this,mincasino.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",55);
                        startActivity(intent2);
                        mp.start();
                    }else{
                        Intent intent1=getIntent();
                        String user_username=intent1.getStringExtra("username1");
                        Intent intent2 = new Intent(See_All.this,mincasino.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",30);
                        startActivity(intent2);
                        mp.start();
                    }
                }
            }
        });

        space=findViewById(R.id.spacewar);
        space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=getIntent();
                String user_username=intent1.getStringExtra("username1");
                Intent intent = new Intent(See_All.this,MainActivity1.class);
                intent.putExtra("username1",user_username);
                startActivity(intent);
                mp.start();
            }
        });


        quizquestion=findViewById(R.id.quiz);
        quizquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=See_All.this;
                ConnectivityManager cManager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    Network activeNet=cManager.getActiveNetwork();
                    NetworkCapabilities netCaps=cManager.getNetworkCapabilities(activeNet);
                    boolean vpn=netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                    if(vpn==true){

                        Intent intent1=getIntent();
                        String user_username=intent1.getStringExtra("username1");
                        Intent intent2 = new Intent(See_All.this,Quiz.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",7);
                        startActivity(intent2);
                        mp.start();
                    }else{
                        Intent intent1=getIntent();
                        String user_username=intent1.getStringExtra("username1");
                        Intent intent2 = new Intent(See_All.this,Quiz.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",5);
                        startActivity(intent2);
                        mp.start();
                    }
                }
            }
        });




        guessdigit=findViewById(R.id.DICE_i1);
        guessdigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=See_All.this;
                ConnectivityManager cManager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    Network activeNet=cManager.getActiveNetwork();
                    NetworkCapabilities netCaps=cManager.getNetworkCapabilities(activeNet);
                    boolean vpn=netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                    if(vpn==true){

                        Intent intent1=getIntent();
                        String user_username=intent1.getStringExtra("username1");
                        Intent intent2 = new Intent(See_All.this,Guessdigit.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",12);
                        startActivity(intent2);
                        mp.start();
                    }else{
                        Intent intent1=getIntent();
                        String user_username=intent1.getStringExtra("username1");
                        Intent intent2 = new Intent(See_All.this,Guessdigit.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",10);
                        startActivity(intent2);
                        mp.start();
                    }
                }
            }
        });

        refer2earn=findViewById(R.id.refer);
        refer2earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=getIntent();
                String user_username=intent1.getStringExtra("username1");
                Intent intent = new Intent(See_All.this,Refer.class);
                intent.putExtra("username1",user_username);
                startActivity(intent);
                mp.start();
            }
        });


        PopMeforLotto=findViewById(R.id.popMe);
        PopMeforLotto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=See_All.this;
                ConnectivityManager cManager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    Network activeNet=cManager.getActiveNetwork();
                    NetworkCapabilities netCaps=cManager.getNetworkCapabilities(activeNet);
                    boolean vpn=netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                    if(vpn==true){

                        Intent intent1=getIntent();
                        String user_username=intent1.getStringExtra("username1");
                        Intent intent2 = new Intent(See_All.this,pop_up_for_lotto.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",55);
                        startActivity(intent2);
                        mp.start();
                    }else{
                        Intent intent1=getIntent();
                        String user_username=intent1.getStringExtra("username1");
                        Intent intent2 = new Intent(See_All.this,pop_up_for_lotto.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",30);
                        startActivity(intent2);
                        mp.start();
                    }
                }
            }
        });

        spin= findViewById(R.id.i1);
        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=getIntent();
                String user_username=intent1.getStringExtra("username1");
                Intent intent = new Intent(See_All.this,Lucky_Spin.class);
                intent.putExtra("username1",user_username);
                startActivity(intent);
                mp.start();

            }
        });
        slot=(ImageView)findViewById(R.id.i2);
        slot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=See_All.this;
                ConnectivityManager cManager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    Network activeNet=cManager.getActiveNetwork();
                    NetworkCapabilities netCaps=cManager.getNetworkCapabilities(activeNet);
                    boolean vpn=netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                    if(vpn==true){

                        Intent intent1=getIntent();
                        String user_username=intent1.getStringExtra("username1");
                        Intent intent2 = new Intent(See_All.this,slotmachine.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",60);
                        startActivity(intent2);
                        mp.start();
                    }else{
                        Intent intent1=getIntent();
                        String user_username=intent1.getStringExtra("username1");
                        Intent intent2 = new Intent(See_All.this,slotmachine.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",35);
                        startActivity(intent2);
                        mp.start();
                    }
                }

            }
        });
        fortune= findViewById(R.id.fortune);
        fortune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=getIntent();
                String user_username=intent1.getStringExtra("username1");
                Intent intent = new Intent(See_All.this,spinvoucher.class);
                intent.putExtra("username1",user_username);
                startActivity(intent);
                mp.start();

            }
        });
        candy_crush=findViewById(R.id.i3);
        candy_crush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=See_All.this;
                ConnectivityManager cManager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    Network activeNet=cManager.getActiveNetwork();
                    NetworkCapabilities netCaps=cManager.getNetworkCapabilities(activeNet);
                    boolean vpn=netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                    if(vpn==true){
                        Intent intent1=getIntent();
                        String user_username=intent1.getStringExtra("username1");
                        Intent intent2 = new Intent(See_All.this,candy_crush.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",9);
                        startActivity(intent2);
                        mp.start();
                    }else{
                        Intent intent1=getIntent();
                        String user_username=intent1.getStringExtra("username1");
                        Intent intent2 = new Intent(See_All.this,candy_crush.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",5);
                        startActivity(intent2);
                        mp.start();
                    }
                }
            }
        });
        rewarded=(ImageView) findViewById(R.id.i4);
        rewarded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=See_All.this;
                ConnectivityManager cManager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    Network activeNet=cManager.getActiveNetwork();
                    NetworkCapabilities netCaps=cManager.getNetworkCapabilities(activeNet);
                    boolean vpn=netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                    if(vpn==true){
                        Intent intent1=getIntent();
                        String user_username=intent1.getStringExtra("username1");
                        Intent intent2 = new Intent(See_All.this,Rewarded.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",35);
                        startActivity(intent2);
                        mp.start();
                    }else{
                        Intent intent1=getIntent();
                        String user_username=intent1.getStringExtra("username1");
                        Intent intent2 = new Intent(See_All.this,Rewarded.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",20);
                        startActivity(intent2);
                        mp.start();
                    }
                }

            }
        });

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();
    }
}