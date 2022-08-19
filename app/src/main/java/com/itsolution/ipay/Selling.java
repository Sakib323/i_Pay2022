package com.itsolution.ipay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;

import java.util.HashMap;

public class Selling extends AppCompatActivity {


    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling);
        TextInputLayout amountofdaimond;
        amountofdaimond=findViewById(R.id.amount);



        UnityAds.initialize(Selling.this,GameID, Testmode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                unity();
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });
        unity();

        String val = amountofdaimond.getEditText().getText().toString();

        Button confirm;


        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent=getIntent();
        int rate=intent.getIntExtra("rate",1000);


        TextView amount;
        amount=findViewById(R.id.youhave);
        SharedPreferences share=getSharedPreferences("amountofdiamond",MODE_PRIVATE);
        int youhave=share.getInt("diamond",0);
        String value = String.valueOf(youhave);
        amount.setText(value);

        TextView change;
        change=findViewById(R.id.changingvalue);

        confirm=findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unity();
                String val = amountofdaimond.getEditText().getText().toString();
                if(val.isEmpty()){
                    AlertDialog.Builder alert= new AlertDialog.Builder(Selling.this);
                    LayoutInflater inflater = (Selling.this).getLayoutInflater();
                    alert.setMessage("Please Place an amount you want to sell.");
                    alert.setCancelable(false);
                    alert.setView(inflater.inflate(R.layout.diamondbox,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();
                }else{
                    unity();
                    int balance=Integer.parseInt(change.getText().toString());
                    int item= Integer.parseInt(val);
                    int cost=item*rate;
                    if(item>youhave){
                        AlertDialog.Builder alert= new AlertDialog.Builder(Selling.this);
                        LayoutInflater inflater = (Selling.this).getLayoutInflater();
                        alert.setMessage("You don't have this much diamond in your wallet");
                        alert.setCancelable(false);
                        alert.setView(inflater.inflate(R.layout.diamondbox,null));
                        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                unity();

                            }
                        });
                        alert.show();
                    }else{
                        unity();

                        SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                        int iCoin_balance=coin.getInt("number",0);
                        int updateCoins=iCoin_balance+cost;
                        SharedPreferences.Editor EDITOR=coin.edit();
                        EDITOR.putInt("number",updateCoins);
                        EDITOR.apply();

                        AlertDialog.Builder alert= new AlertDialog.Builder(Selling.this);
                        LayoutInflater inflater = (Selling.this).getLayoutInflater();
                        alert.setMessage("You have sold "+item);
                        alert.setCancelable(false);
                        alert.setView(inflater.inflate(R.layout.success,null));
                        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                unity();
                                finish();
                            }
                        });
                        alert.show();


                    }
                }
            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                int iCoin_balance=coin.getInt("number",0);

                String user_balance=String.format(String.valueOf(iCoin_balance));
                change.setText(user_balance);

            }
        }, 0);




    }

    private void unity(){
        IUnityAdsShowListener iUnityAdsShowListener=new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                UnityAds.load(AppID);
                UnityAds.show(Selling.this,AppID);
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
        UnityAds.show(Selling.this,AppID);
    }

}