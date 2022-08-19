package com.itsolution.ipay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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

import java.util.HashMap;

public class hourly100 extends AppCompatActivity {

    private RewardedAd mRewardedAd;
    private final String TAG="--->Admob";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly100);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Button Claim=findViewById(R.id.claim);
        Claim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showrewardedad();
            }
        });
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();
        showrewardedad();
        AlertDialog.Builder alert3= new AlertDialog.Builder(hourly100.this);
        LayoutInflater inflater3 = (hourly100.this).getLayoutInflater();
        alert3.setMessage("You Got 250 iCoin from hourly check in");
        alert3.setCancelable(true);
        alert3.setView(inflater3.inflate(R.layout.tressure,null));
        alert3.setPositiveButton("Claim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showrewardedad();
            }
        });
        alert3.create().show();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                loadRewardedAd();
            }
        });



    }

    private void loadRewardedAd() {
        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(this, "ca-app-pub-4790419998077780/2903355158",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.

                        mRewardedAd = null;

                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;


                        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when ad fails to show.
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when ad is dismissed.
                                // Set the ad reference to null so you don't show the ad a second time.


                                mRewardedAd = null;
                                loadRewardedAd();
                            }
                        });
                    }
                });
    }
    private void showrewardedad(){

        if (mRewardedAd != null) {
            mRewardedAd.show(this, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {

                    Log.d(TAG, "The user earned the reward.");
                    Toast.makeText(hourly100.this,"The user earned the reward.",Toast.LENGTH_SHORT).show();
                    int rewardAmount = rewardItem.getAmount();
                    String rewardType = rewardItem.getType();

                    DatabaseReference reference= FirebaseDatabase.getInstance("https://i-pay-86691-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User");
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange( DataSnapshot snapshot) {

                            SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
                            String value = sharedPreferences.getString("value","");

                            int currentCoins=snapshot.child(value).child("balance").getValue(int.class);
                            int updateCoins=currentCoins+250;
                            HashMap<String,Object> map=new HashMap<>();
                            map.put("balance",updateCoins);
                            reference.child(value).updateChildren(map);
                            // reference.updateChildren(map);


                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            });
        } else {
            AlertDialog.Builder alert= new AlertDialog.Builder(hourly100.this);
            LayoutInflater inflater = (hourly100.this).getLayoutInflater();
            alert.setTitle("Wait for a while");
            alert.setCancelable(true);
            alert.setView(inflater.inflate(R.layout.video1,null));
            alert.create().show();
        }
        //Intent intent = new Intent(hourly100.this,UserProfile.class);
        //startActivity(intent);
    }

}