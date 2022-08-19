package com.itsolution.ipay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

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
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Rewarded_Interstial extends AppCompatActivity implements OnUserEarnedRewardListener {
    private Button interShow;
    private LottieAnimationView play;
    private RewardedInterstitialAd rewardedInterstitialAd;
    private String TAG = "--->Admob";
    private AdView mAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_rewarded_interstial);

        play=findViewById(R.id.playit);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                loadAd();
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rewardedInterstitialAd!=null) {

                    rewardedInterstitialAd.show(Rewarded_Interstial.this,/*
    OnUserEarnedRewardListener */ Rewarded_Interstial.this);
                }else{
                    Toast.makeText(Rewarded_Interstial.this,"Add not loaded",Toast.LENGTH_SHORT).show();
                    Log.e(TAG,"Ad not loaded");
                }

            }
        });
    }

    private void loadAd() {

        RewardedInterstitialAd.load(Rewarded_Interstial.this, "ca-app-pub-3940256099942544/5354046379",
                new AdRequest.Builder().build(),  new RewardedInterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(RewardedInterstitialAd ad) {
                        rewardedInterstitialAd = ad;
                        Log.e(TAG, "onAdLoaded");

                        rewardedInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            /** Called when the ad failed to show full screen content. */
                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                Log.i(TAG, "onAdFailedToShowFullScreenContent");
                            }

                            /** Called when ad showed the full screen content. */
                            @Override
                            public void onAdShowedFullScreenContent() {
                                Log.i(TAG, "onAdShowedFullScreenContent");
                            }

                            /** Called when full screen content is dismissed. */
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Log.i(TAG, "onAdDismissedFullScreenContent");
                                loadAd();
                            }
                        });


                    }
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        Log.e(TAG, "onAdFailedToLoad");
                    }
                });

    }



    @Override
    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {

        Log.i(TAG, "onUserEarnedReward");
        int rewardAmount = rewardItem.getAmount();
        String rewardType = rewardItem.getType();

        Intent intent1=getIntent();
        String username=intent1.getStringExtra("username1");
        DatabaseReference reference= FirebaseDatabase.getInstance("https://i-pay-86691-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int currentCoins=snapshot.child(username).child("balance").getValue(int.class);
                int updateCoins=currentCoins+100;
                HashMap<String,Object> map=new HashMap<>();
                map.put("balance",updateCoins);
                reference.child(username).updateChildren(map);
                // reference.updateChildren(map);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



    }
}