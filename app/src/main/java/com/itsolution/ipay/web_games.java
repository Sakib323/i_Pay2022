package com.itsolution.ipay;



import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
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
import java.util.Random;


public class web_games extends AppCompatActivity {

    CountDownTimer count;
    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;



    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_games);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);






        UnityAds.initialize(web_games.this,GameID, Testmode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                unity();

            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });
        unity();

        Intent intent=getIntent();
        String url=intent.getStringExtra("url");

        SharedPreferences refresh = getSharedPreferences("ref", MODE_PRIVATE);
        SharedPreferences.Editor edit = refresh.edit();
        edit.putBoolean("refresh",true);
        edit.apply();




        AlertDialog.Builder alert= new AlertDialog.Builder(web_games.this);
        LayoutInflater inflater = (web_games.this).getLayoutInflater();
        alert.setTitle("Youtube Video");
        alert.setMessage("For each minute here you will get 15 to 25 iCoin depending on vpn connection(vpn bonus) and paypal cash");
        alert.setCancelable(false);
        alert.setView(inflater.inflate(R.layout.games,null));
        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                unity();

            }
        });
        alert.create().show();






        count=new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

                unity();
                count.start();
                reward();
            }

        }.start();



        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();
        webView = (WebView) findViewById(R.id.url);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        webView.clearCache(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
    }


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            unity();
        }else{
            Intent intent=new Intent(web_games.this,UserProfile.class);
            startActivity(intent);
            unity();
            this.finish();

        }
    }




    private void unity(){
        IUnityAdsShowListener iUnityAdsShowListener=new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                UnityAds.load(AppID);
                UnityAds.show(web_games.this,AppID);
            }

            @Override
            public void onUnityAdsShowStart(String s) {

            }

            @Override
            public void onUnityAdsShowClick(String s) {



            }

            @Override
            public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {
                reward();

            }
        };
        UnityAds.load(AppID);
        UnityAds.show(web_games.this,AppID);
    }
    private void reward(){

        Intent intent1=getIntent();
        String username=intent1.getStringExtra("username1");
        int amount=intent1.getIntExtra("coin",30);
        Boolean vpnison;
        SharedPreferences share=getSharedPreferences("active",MODE_PRIVATE);
        vpnison =share.getBoolean("vpnactive",false);
        if (vpnison==true){
            SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
            int iCoin_balance=coin.getInt("number",0);
            int updateCoins=iCoin_balance+25;
            SharedPreferences.Editor EDITOR=coin.edit();
            EDITOR.putInt("number",updateCoins);
            EDITOR.apply();

            Toast.makeText(web_games.this, "+25 iCoin", Toast.LENGTH_SHORT).show();

        }
        else{
            SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
            int iCoin_balance=coin.getInt("number",0);
            int updateCoins=iCoin_balance+15;
            SharedPreferences.Editor EDITOR=coin.edit();
            EDITOR.putInt("number",updateCoins);
            EDITOR.apply();

            Toast.makeText(web_games.this, "+15 iCoin", Toast.LENGTH_SHORT).show();

        }
    }




}