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
import android.widget.TextView;
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

public class ClaimVoucher extends AppCompatActivity {

    Boolean checkinornot,iCoin,diamond;


    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_voucher);
        TextView claim1,claim2,claim3;
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        unity();
        UnityAds.initialize(ClaimVoucher.this,GameID, Testmode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                unity();
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });
        unity();



        int surprise = (int)(Math.random() * 70 + 10);
        int surprise1 = (int)(Math.random() * 70 + 10);
        int diamondcheckin = (int)(Math.random() * 2 + 1);


        SharedPreferences share=getSharedPreferences("daily",MODE_PRIVATE);
        checkinornot=share.getBoolean("checkin",false);

        SharedPreferences shareicoin=getSharedPreferences("dailyiCoin",MODE_PRIVATE);
        iCoin=shareicoin.getBoolean("checkinicoin",false);

        SharedPreferences sharediamond=getSharedPreferences("dailydiamond",MODE_PRIVATE);
        diamond=sharediamond.getBoolean("checkindiamond",false);

        claim1=findViewById(R.id.btn1);
        claim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(diamond==true){
                    unity();
                    AlertDialog.Builder alert= new AlertDialog.Builder(ClaimVoucher.this);
                    LayoutInflater inflater = (ClaimVoucher.this).getLayoutInflater();
                    alert.setMessage("Diamond You Got through check-in "+diamondcheckin);
                    alert.setCancelable(false);
                    alert.setView(inflater.inflate(R.layout.treasurebox,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();

                    SharedPreferences hourlycheckin = getSharedPreferences("dailydiamond", MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = hourlycheckin.edit();
                    editor1.putBoolean("checkindiamond",false);
                    editor1.apply();

                    diamond=false;

                    claim1.setText("claimed");
                    SharedPreferences share=getSharedPreferences("amountofdiamond",MODE_PRIVATE);
                    int now=share.getInt("diamond",0);
                    SharedPreferences.Editor editor=share.edit();
                    editor.putInt("diamond",now+diamondcheckin);
                    editor.apply();

                }
                else{
                    unity();
                    claim1.setText("claimed");
                    AlertDialog.Builder alert= new AlertDialog.Builder(ClaimVoucher.this);
                    LayoutInflater inflater = (ClaimVoucher.this).getLayoutInflater();
                    alert.setMessage("You already have claimed it or it's too late to claim the reward.Please try to check-in within first five minutes of an hour");
                    alert.setCancelable(false);

                    alert.setView(inflater.inflate(R.layout.treasurebox,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();

                }

            }
        });
        claim2=findViewById(R.id.btn2);
        claim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(iCoin==true){
                    unity();
                    claim2.setText("claimed");

                    SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                    int iCoin_balance=coin.getInt("number",0);
                    int updateCoins=iCoin_balance+surprise1;
                    SharedPreferences.Editor EDITOR=coin.edit();
                    EDITOR.putInt("number",updateCoins);
                    EDITOR.apply();

                    AlertDialog.Builder alert= new AlertDialog.Builder(ClaimVoucher.this);
                    LayoutInflater inflater = (ClaimVoucher.this).getLayoutInflater();
                    alert.setMessage("iCoin You Got through check-in "+surprise1);
                    alert.setCancelable(false);
                    alert.setView(inflater.inflate(R.layout.treasurebox,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();

                    SharedPreferences hourlycheckin = getSharedPreferences("dailyiCoin", MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = hourlycheckin.edit();
                    editor1.putBoolean("checkinicoin",false);
                    editor1.apply();

                    iCoin=false;

                }else{
                    claim2.setText("claimed");
                    unity();
                    AlertDialog.Builder alert= new AlertDialog.Builder(ClaimVoucher.this);
                    LayoutInflater inflater = (ClaimVoucher.this).getLayoutInflater();
                    alert.setMessage("You already have claimed it or it's too late to claim the reward.Please try to check-in within the first five minutes of an hour");
                    alert.setCancelable(false);

                    alert.setView(inflater.inflate(R.layout.treasurebox,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();

                }

            }
        });
        claim3=findViewById(R.id.btn3);
        claim3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkinornot==true){
                    claim3.setText("claimed");
                    unity();

                    SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                    int iCoin_balance=coin.getInt("number",0);
                    int updateCoins=iCoin_balance+surprise;
                    SharedPreferences.Editor EDITOR=coin.edit();
                    EDITOR.putInt("number",updateCoins);
                    EDITOR.apply();

                    AlertDialog.Builder alert= new AlertDialog.Builder(ClaimVoucher.this);
                    LayoutInflater inflater = (ClaimVoucher.this).getLayoutInflater();
                    alert.setMessage("iCoin You Got through check-in "+surprise);
                    alert.setCancelable(false);
                    alert.setView(inflater.inflate(R.layout.treasurebox,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();

                    SharedPreferences hourlycheckin = getSharedPreferences("daily", MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = hourlycheckin.edit();
                    editor1.putBoolean("checkin",false);
                    editor1.apply();

                    checkinornot=false;
                }
                else{
                    claim3.setText("claimed");
                    unity();
                    AlertDialog.Builder alert= new AlertDialog.Builder(ClaimVoucher.this);
                    LayoutInflater inflater = (ClaimVoucher.this).getLayoutInflater();
                    alert.setMessage("You already have claimed it or it's too late to claim the reward.Please try to check-in within first five minutes of an hour");
                    alert.setCancelable(false);

                    alert.setView(inflater.inflate(R.layout.treasurebox,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();
                }

            }
        });


    }

    private void unity(){
        IUnityAdsShowListener iUnityAdsShowListener=new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                UnityAds.load(AppID);
                UnityAds.show(ClaimVoucher.this,AppID);
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
        UnityAds.show(ClaimVoucher.this,AppID);
    }


}