package com.itsolution.ipay;

import androidx.annotation.NonNull;
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

import com.airbnb.lottie.LottieAnimationView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
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

import java.util.HashMap;

public class Rewarded extends AppCompatActivity {

    private LottieAnimationView play;



    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        unity();
        AlertDialog.Builder alert= new AlertDialog.Builder(Rewarded.this);
        LayoutInflater inflater = (Rewarded.this).getLayoutInflater();
        alert.setMessage("Watch video to earn iCoin.You will get 25 iCoin for each video you watch.On vpn bonus you will get 50 iCoin");
        alert.setCancelable(false);
        alert.setView(inflater.inflate(R.layout.icoins,null));
        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              unity();
            }
        });
        alert.create().show();

        SharedPreferences refresh = getSharedPreferences("ref", MODE_PRIVATE);
        SharedPreferences.Editor edit = refresh.edit();
        edit.putBoolean("refresh",true);
        edit.apply();




        play=findViewById(R.id.playit);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(Rewarded.this);
                LayoutInflater inflater = (Rewarded.this).getLayoutInflater();
                alert.setTitle("Wait for a while");
                alert.setCancelable(true);
                alert.setView(inflater.inflate(R.layout.video,null));
                alert.create().show();

                unity();
            }
        });

        UnityAds.initialize(Rewarded.this,GameID, Testmode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                unity();
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });

    }






    private void unity(){
        IUnityAdsShowListener iUnityAdsShowListener=new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {

                AlertDialog.Builder alert= new AlertDialog.Builder(Rewarded.this);
                LayoutInflater inflater = (Rewarded.this).getLayoutInflater();
                alert.setTitle("Wait for a while");
                alert.setCancelable(true);
                alert.setView(inflater.inflate(R.layout.video,null));
                alert.create().show();

                UnityAds.load(AppID);
                UnityAds.show(Rewarded.this,AppID);
            }

            @Override
            public void onUnityAdsShowStart(String s) {

            }

            @Override
            public void onUnityAdsShowClick(String s) {

            }

            @Override
            public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {

                Intent intent1=getIntent();
                String username=intent1.getStringExtra("username1");
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


                }
                else{
                    SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                    int iCoin_balance=coin.getInt("number",0);
                    int updateCoins=iCoin_balance+25;
                    SharedPreferences.Editor EDITOR=coin.edit();
                    EDITOR.putInt("number",updateCoins);
                    EDITOR.apply();

                }


            }
        };
        UnityAds.load(AppID);
        UnityAds.show(Rewarded.this,AppID);
    }


}