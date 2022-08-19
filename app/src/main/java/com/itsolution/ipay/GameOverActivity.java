package com.itsolution.ipay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


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

import java.util.HashMap;

public class GameOverActivity extends AppCompatActivity {
    private Button StartGameAgain;

    private TextView DisplayScore;
    private String score;

    private final String TAG="--->Admob";
    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        SharedPreferences refresh = getSharedPreferences("ref", MODE_PRIVATE);
        SharedPreferences.Editor edit = refresh.edit();
        edit.putBoolean("refresh",true);
        edit.apply();

        UnityAds.initialize(GameOverActivity.this,GameID, Testmode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {


            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });
        unity();


        score = getIntent().getExtras().get("score").toString();

        StartGameAgain = (Button) findViewById(R.id.play_again_btn);
        DisplayScore = (TextView) findViewById(R.id.displayScore);


        StartGameAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(GameOverActivity.this);
                LayoutInflater inflater = (GameOverActivity.this).getLayoutInflater();
                alert.setTitle("Wait for a while");
                alert.setCancelable(true);
                alert.setView(inflater.inflate(R.layout.video1,null));
                alert.create().show();
                unity();
            }
        });
        DisplayScore.setText("Your Score " + score);
    }




    private void unity(){
        IUnityAdsShowListener iUnityAdsShowListener=new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                AlertDialog.Builder alert= new AlertDialog.Builder(GameOverActivity.this);
                LayoutInflater inflater = (GameOverActivity.this).getLayoutInflater();
                alert.setTitle("Wait for a while");
                alert.setCancelable(true);
                alert.setView(inflater.inflate(R.layout.video1,null));
                alert.create().show();
                UnityAds.load(AppID);
                UnityAds.show(GameOverActivity.this,AppID);
            }

            @Override
            public void onUnityAdsShowStart(String s) {
                Toast.makeText(GameOverActivity.this, "Click this ad to earn iCoin as bonus", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onUnityAdsShowClick(String s) {
                Toast.makeText(GameOverActivity.this, "Congress!You got 30 iCoin as bonus", Toast.LENGTH_SHORT).show();
                SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                int iCoin_balance=coin.getInt("number",0);
                int updateCoins=iCoin_balance+30;
                SharedPreferences.Editor EDITOR=coin.edit();
                EDITOR.putInt("number",updateCoins);
                EDITOR.apply();
            }

            @Override
            public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {
                Intent intent1=getIntent();

                score = getIntent().getExtras().get("score").toString();
                int points=Integer.parseInt(score)/5;
                SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                int iCoin_balance=coin.getInt("number",0);
                int updateCoins=iCoin_balance+points;
                SharedPreferences.Editor EDITOR=coin.edit();
                EDITOR.putInt("number",updateCoins);
                EDITOR.apply();
            }
        };
        UnityAds.load(AppID);
        UnityAds.show(GameOverActivity.this,AppID);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GameOverActivity.this, UserProfile.class);
        startActivity(intent);
    }
}