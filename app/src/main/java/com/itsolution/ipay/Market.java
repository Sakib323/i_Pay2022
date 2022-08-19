package com.itsolution.ipay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

public class Market extends AppCompatActivity {


    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        unity();

        Button buy,sell;
        TextView amount;
        amount=findViewById(R.id.youhave);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        UnityAds.initialize(Market.this,GameID, Testmode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                unity();
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });
        unity();

        TextView change;
        change=findViewById(R.id.changingvalue);

        CardView refresh;
        refresh=findViewById(R.id.forrefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unity();
                DatabaseReference reference= FirebaseDatabase.getInstance("https://i-pay-86691-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("diamond");

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int currentCoins=snapshot.getValue(int.class);
                        String rate=String.format(String.valueOf(currentCoins));
                        change.setText(rate);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DatabaseReference reference= FirebaseDatabase.getInstance("https://i-pay-86691-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("diamond");

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int currentCoins=snapshot.getValue(int.class);
                        String rate=String.format(String.valueOf(currentCoins));
                        change.setText(rate);

                        SharedPreferences share=getSharedPreferences("amountofdiamond",MODE_PRIVATE);
                        int youhave=share.getInt("diamond",0);
                        String value = String.valueOf(youhave);
                        amount.setText(value);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }, 0);


        sell=findViewById(R.id.Sell);
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unity();

                AlertDialog.Builder alert= new AlertDialog.Builder(Market.this);
                LayoutInflater inflater = (Market.this).getLayoutInflater();
                alert.setMessage("Are you sure you want to sell diamond in return of iCoin?");
                alert.setCancelable(false);
                alert.setView(inflater.inflate(R.layout.diamondbox,null));
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent =new Intent(Market.this,Selling.class);
                        String amount=change.getText().toString();
                        int rate=Integer.parseInt(amount);
                        intent.putExtra("rate",rate);
                        startActivity(intent);
                        unity();

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        unity();
                    }
                });
                alert.create().show();

            }
        });
        buy=findViewById(R.id.Buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unity();

                AlertDialog.Builder alert= new AlertDialog.Builder(Market.this);
                LayoutInflater inflater = (Market.this).getLayoutInflater();
                alert.setMessage("Are you sure you want to buy diamond with iCoin?");
                alert.setCancelable(false);
                alert.setView(inflater.inflate(R.layout.diamondbox,null));
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent =new Intent(Market.this,buying.class);
                        String amount=change.getText().toString();
                        int rate=Integer.parseInt(amount);
                        intent.putExtra("rate",rate);
                        startActivity(intent);
                        unity();

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        unity();

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
                UnityAds.load(AppID);
                UnityAds.show(Market.this,AppID);
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
        UnityAds.show(Market.this,AppID);
    }

}