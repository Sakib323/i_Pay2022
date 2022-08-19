package com.itsolution.ipay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class GameOver extends AppCompatActivity {

    TextView tvPoints;
    Button redeem_point;

    private final String TAG="--->Admob";
    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);

        SharedPreferences refresh = getSharedPreferences("ref", MODE_PRIVATE);
        SharedPreferences.Editor edit = refresh.edit();
        edit.putBoolean("refresh",true);
        edit.apply();



        UnityAds.initialize(GameOver.this,GameID, Testmode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {

            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });
        unity();


        redeem_point=findViewById(R.id.claim);
        redeem_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(GameOver.this);
                LayoutInflater inflater = (GameOver.this).getLayoutInflater();
                alert.setTitle("Wait for a while");
                alert.setCancelable(true);
                alert.setView(inflater.inflate(R.layout.video1,null));
                alert.create().show();
                unity();
            }
        });


        int points = getIntent().getExtras().getInt("points");
        tvPoints = findViewById(R.id.tvPoints);
        tvPoints.setText("Your Score" + points);
    }


    private void unity(){
        IUnityAdsShowListener iUnityAdsShowListener=new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                AlertDialog.Builder alert= new AlertDialog.Builder(GameOver.this);
                LayoutInflater inflater = (GameOver.this).getLayoutInflater();
                alert.setTitle("Wait for a while");
                alert.setCancelable(true);
                alert.setView(inflater.inflate(R.layout.video1,null));
                alert.create().show();
                UnityAds.load(AppID);
                UnityAds.show(GameOver.this,AppID);
            }

            @Override
            public void onUnityAdsShowStart(String s) {
                Toast.makeText(GameOver.this, "Click this ad to get iCoin as bonus", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onUnityAdsShowClick(String s) {
                Toast.makeText(GameOver.this, "Congress!You got 30 iCoin as bonus", Toast.LENGTH_SHORT).show();

                SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                int iCoin_balance=coin.getInt("number",0);
                int updateCoins=iCoin_balance+30;
                SharedPreferences.Editor EDITOR=coin.edit();
                EDITOR.putInt("number",updateCoins);
                EDITOR.apply();

            }

            @Override
            public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {
                Toast.makeText(GameOver.this,"The user earned the reward.",Toast.LENGTH_SHORT).show();


                int points = getIntent().getExtras().getInt("points");
                SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                int iCoin_balance=coin.getInt("number",0);
                int updateCoins=iCoin_balance+points;
                SharedPreferences.Editor EDITOR=coin.edit();
                EDITOR.putInt("number",updateCoins);
                EDITOR.apply();
            }
        };
        UnityAds.load(AppID);
        UnityAds.show(GameOver.this,AppID);
    }



    public void restart(View view) {
        Intent intent = new Intent(GameOver.this, MainActivity1.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view) {
        Intent intent = new Intent(GameOver.this, UserProfile.class);
        startActivity(intent);
    }
}
