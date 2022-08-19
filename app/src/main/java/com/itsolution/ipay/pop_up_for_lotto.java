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

public class pop_up_for_lotto extends AppCompatActivity {
    Button popup;

    private final String TAG="--->Admob";

    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pop_up_for_lotto);

        SharedPreferences refresh = getSharedPreferences("ref", MODE_PRIVATE);
        SharedPreferences.Editor edit = refresh.edit();
        edit.putBoolean("refresh",true);
        edit.apply();



        UnityAds.initialize(pop_up_for_lotto.this,GameID, Testmode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                unity();
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });






        AlertDialog.Builder alert= new AlertDialog.Builder(pop_up_for_lotto.this);
        LayoutInflater inflater = (pop_up_for_lotto.this).getLayoutInflater();
        alert.setMessage("To buy a lottery You need to watch video.If you win the lottery then you will get 45 to 65 iCoin depending on vpn bonus");
        alert.setCancelable(false);
        alert.setView(inflater.inflate(R.layout.icoins,null));
        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                unity();

            }
        });
        alert.create().show();

        popup=findViewById(R.id.btn_popup);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();



        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopMe();
                unity();
            }

            private void PopMe() {
                AlertDialog.Builder alert= new AlertDialog.Builder(pop_up_for_lotto.this);
                alert.setTitle("100 i coins lottery");
                alert.setMessage("would you like to watch video to buy a lottery?");
                LayoutInflater inflater = (pop_up_for_lotto.this).getLayoutInflater();
                alert.setCancelable(false);
                alert.setView(inflater.inflate(R.layout.icoins,null));
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        unity();
                        AlertDialog.Builder alert= new AlertDialog.Builder(pop_up_for_lotto.this);
                        LayoutInflater inflater = (pop_up_for_lotto.this).getLayoutInflater();
                        alert.setTitle("Wait for a while");
                        alert.setCancelable(true);
                        alert.setView(inflater.inflate(R.layout.video1,null));
                        alert.create().show();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        unity();
                        Toast.makeText(pop_up_for_lotto.this,"okay",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.create().show();
            }
        });

    }



    private void unity(){
        IUnityAdsShowListener iUnityAdsShowListener=new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                AlertDialog.Builder alert= new AlertDialog.Builder(pop_up_for_lotto.this);
                LayoutInflater inflater = (pop_up_for_lotto.this).getLayoutInflater();
                alert.setTitle("Wait for a while");
                alert.setCancelable(true);
                alert.setView(inflater.inflate(R.layout.video1,null));
                alert.create().show();
                UnityAds.load(AppID);
                UnityAds.show(pop_up_for_lotto.this,AppID);
            }

            @Override
            public void onUnityAdsShowStart(String s) {

            }

            @Override
            public void onUnityAdsShowClick(String s) {

            }

            @Override
            public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {

                Log.d(TAG, "The user earned the reward.");
                Toast.makeText(pop_up_for_lotto.this,"The user earned the reward",Toast.LENGTH_SHORT).show();

                int rollNummber = (int)(Math.random() * 3 + 1);
                switch (rollNummber) {
                    case 1:
                        AlertDialog.Builder alert= new AlertDialog.Builder(pop_up_for_lotto.this);
                        alert.setTitle("You Won");
                        LayoutInflater inflater = (pop_up_for_lotto.this).getLayoutInflater();
                        alert.setCancelable(false);
                        alert.setView(inflater.inflate(R.layout.treswithdia,null));
                        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                unity();
                                finish();
                            }
                        });
                        alert.create().show();


                        Boolean vpnison;
                        SharedPreferences share=getSharedPreferences("active",MODE_PRIVATE);
                        vpnison =share.getBoolean("vpnactive",false);
                        if (vpnison==true){
                            SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                            int iCoin_balance=coin.getInt("number",0);
                            int updateCoins=iCoin_balance+65;
                            SharedPreferences.Editor EDITOR=coin.edit();
                            EDITOR.putInt("number",updateCoins);
                            EDITOR.apply();


                            unity();



                        }
                        else{
                            SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                            int iCoin_balance=coin.getInt("number",0);
                            int updateCoins=iCoin_balance+45;
                            SharedPreferences.Editor EDITOR=coin.edit();
                            EDITOR.putInt("number",updateCoins);
                            EDITOR.apply();

                            unity();

                        }


                        break;

                    case 2:
                        AlertDialog.Builder alert2= new AlertDialog.Builder(pop_up_for_lotto.this);
                        LayoutInflater inflater2 = (pop_up_for_lotto.this).getLayoutInflater();
                        alert2.setTitle("Sorry You Lose");
                        alert2.setCancelable(false);
                        alert2.setView(inflater2.inflate(R.layout.youlose,null));
                        alert2.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                unity();
                                finish();
                            }
                        });
                        alert2.create().show();
                        break;

                    case 3:
                        AlertDialog.Builder alert3= new AlertDialog.Builder(pop_up_for_lotto.this);
                        LayoutInflater inflater3 = (pop_up_for_lotto.this).getLayoutInflater();
                        alert3.setTitle("Sorry You Lose try again later");
                        alert3.setCancelable(false);
                        alert3.setView(inflater3.inflate(R.layout.youlose,null));
                        alert3.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                unity();
                                finish();
                            }
                        });
                        alert3.create().show();
                        break;

                }

            }
        };
        UnityAds.load(AppID);
        UnityAds.show(pop_up_for_lotto.this,AppID);
    }



}