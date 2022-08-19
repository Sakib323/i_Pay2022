package com.itsolution.ipay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;

import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;

public class Dice extends AppCompatActivity {

    Button rollButton;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;




    private static final String TAG ="--->Native Ad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final MediaPlayer mp1 = MediaPlayer.create(Dice.this, R.raw.click);

        unity();
        SharedPreferences refresh = getSharedPreferences("ref", MODE_PRIVATE);
        SharedPreferences.Editor edit = refresh.edit();
        edit.putBoolean("refresh",true);
        edit.apply();

        UnityAds.initialize(Dice.this,GameID, Testmode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                unity();
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });

        setContentView(R.layout.activity_dice);

        AlertDialog.Builder alert= new AlertDialog.Builder(Dice.this);
        LayoutInflater inflater = (Dice.this).getLayoutInflater();
        alert.setMessage("Select a digit from 1 to 6,If your prediction match with dice you will get 30 iCoin(without vpn bonus) & 50 iCoin(with vpn bonus)");
        alert.setCancelable(false);
        alert.setView(inflater.inflate(R.layout.dice,null));
        final MediaPlayer mp = MediaPlayer.create(Dice.this, R.raw.diceroll);
        mp.start();
        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                unity();
            }
        });
        alert.create().show();

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();


        TextView mResultText = findViewById(R.id.result_text);
        TextView mResultText1 = findViewById(R.id.result_view);
        button1=(Button) findViewById(R.id.number1);
        button2=(Button) findViewById(R.id.number2);
        button3=(Button) findViewById(R.id.number3);
        button4=(Button) findViewById(R.id.number4);
        button5=(Button) findViewById(R.id.number5);
        button6=(Button) findViewById(R.id.number6);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=button1.getText().toString();
                int Value =Integer.parseInt(value);
                mResultText1.setText(value);
                mp1.start();
                unity();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=button2.getText().toString();
                int Value =Integer.parseInt(value);
                mResultText1.setText(value);
                mp1.start();
                unity();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=button3.getText().toString();
                int Value =Integer.parseInt(value);
                mResultText1.setText(value);
                mp1.start();
                unity();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=button4.getText().toString();
                int Value =Integer.parseInt(value);
                mResultText1.setText(value);
                mp1.start();
                unity();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=button5.getText().toString();
                int Value =Integer.parseInt(value);
                mResultText1.setText(value);
                mp1.start();
                unity();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=button6.getText().toString();
                int Value =Integer.parseInt(value);
                mResultText1.setText(value);
                mp1.start();
                unity();
            }
        });


        rollButton = findViewById(R.id.button);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mResultText1.getText().toString().isEmpty()){
                    AlertDialog.Builder alert= new AlertDialog.Builder(Dice.this);
                    LayoutInflater inflater = (Dice.this).getLayoutInflater();
                    alert.setMessage("Please select a number first");
                    alert.setCancelable(false);
                    alert.setView(inflater.inflate(R.layout.dice,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.create().show();
                }
                else{
                    roll();
                    unity();
                }
            }
        });
    }




    private void roll() {
        TextView mResultText1 = findViewById(R.id.result_view);
        String value1=mResultText1.getText().toString();
        int rollNummber = (int)(Math.random() * 6 + 1);
        ImageView diceImage = findViewById(R.id.imageView);
        TextView mResultText = findViewById(R.id.result_text);

        switch (rollNummber){
            case 1:
                diceImage.setImageResource(R.drawable.dice_1);
                mResultText.setText("Number on dice is:- 1");
                Toast.makeText(this, "one", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                diceImage.setImageResource(R.drawable.dice_2);
                mResultText.setText("Number on dice is:- 2");
                Toast.makeText(this, "two", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                diceImage.setImageResource(R.drawable.dice_3);
                mResultText.setText("Number on dice is:- 3");
                Toast.makeText(this, "three", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                diceImage.setImageResource(R.drawable.dice_4);
                mResultText.setText("Number on dice is:- 4");
                Toast.makeText(this, "four", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                diceImage.setImageResource(R.drawable.dice_5);
                mResultText.setText("Number on dice is:- 5");
                Toast.makeText(this, "five", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                diceImage.setImageResource(R.drawable.dice_6);
                mResultText.setText("Number on dice is:- 6");
                Toast.makeText(this, "six", Toast.LENGTH_SHORT).show();
                break;
            default:
                diceImage.setImageResource(R.drawable.empty_dice);
        }
        if (Integer.parseInt(value1) == rollNummber)
        {
            Toast.makeText(Dice.this, "You Won", Toast.LENGTH_SHORT).show();



            unity();


            Boolean vpnison;
            SharedPreferences share=getSharedPreferences("active",MODE_PRIVATE);
            vpnison =share.getBoolean("vpnactive",false);
            if (vpnison==true){




                SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                int iCoin_balance=coin.getInt("number",0);
                int updateCoins=iCoin_balance+50;
                SharedPreferences.Editor EDITOR=coin.edit();
                EDITOR.putInt("number",updateCoins);
                EDITOR.apply();

                unity();
                AlertDialog.Builder alert= new AlertDialog.Builder(Dice.this);
                LayoutInflater inflater = (Dice.this).getLayoutInflater();
                alert.setTitle("The amount of iCoin you won 50 with 20 vpn bonus");
                final MediaPlayer mp = MediaPlayer.create(Dice.this, R.raw.slotwin);
                mp.start();
                alert.setCancelable(false);
                alert.setView(inflater.inflate(R.layout.icoins,null));
                alert.setNeutralButton("okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        unity();
                        finish();
                    }
                });
                alert.create().show();




            }
            else{
                SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                int iCoin_balance=coin.getInt("number",0);
                int updateCoins=iCoin_balance+30;
                SharedPreferences.Editor EDITOR=coin.edit();
                EDITOR.putInt("number",updateCoins);
                EDITOR.apply();

                unity();
                AlertDialog.Builder alert= new AlertDialog.Builder(Dice.this);
                LayoutInflater inflater = (Dice.this).getLayoutInflater();
                alert.setTitle("The amount of iCoin you won 30 with out any vpn bonus");
                final MediaPlayer mp = MediaPlayer.create(Dice.this, R.raw.slotwin);
                mp.start();
                alert.setCancelable(false);
                alert.setView(inflater.inflate(R.layout.icoins,null));
                alert.setNeutralButton("okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        unity();
                        finish();
                    }
                });
                alert.create().show();



            }



            unity();



        }

        else{
            Toast.makeText(Dice.this, "You Lost", Toast.LENGTH_SHORT).show();
            unity();
            AlertDialog.Builder alert= new AlertDialog.Builder(Dice.this);
            LayoutInflater inflater = (Dice.this).getLayoutInflater();
            alert.setTitle("Better Luck Next Time");
            alert.setCancelable(false);
            alert.setView(inflater.inflate(R.layout.youlose,null));
            alert.setNeutralButton("okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    unity();
                    finish();
                }
            });
            alert.create().show();


        }
    }



    private void unity(){
        IUnityAdsShowListener iUnityAdsShowListener=new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                UnityAds.load(AppID);
                UnityAds.show(Dice.this,AppID);
            }

            @Override
            public void onUnityAdsShowStart(String s) {

            }

            @Override
            public void onUnityAdsShowClick(String s) {

            }

            @Override
            public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {

            }
        };
        UnityAds.load(AppID);
        UnityAds.show(Dice.this,AppID);
    }

}