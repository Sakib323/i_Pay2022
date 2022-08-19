package com.itsolution.ipay;

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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
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


public class slotmachine extends AppCompatActivity {




    FirebaseDatabase rootNode;
    DatabaseReference reference;

    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;


    private static final String TAG ="--->Native Ad";

    private TextView msg;
    private ImageView img1, img2, img3;
    private Wheel wheel1, wheel2, wheel3;
    private Button btn;
    private boolean isStarted;

    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_slotmachine);

        SharedPreferences refresh = getSharedPreferences("ref", MODE_PRIVATE);
        SharedPreferences.Editor edit = refresh.edit();
        edit.putBoolean("refresh",true);
        edit.apply();

        UnityAds.initialize(slotmachine.this,GameID, Testmode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                unity();
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });
        unity();






        AlertDialog.Builder alert= new AlertDialog.Builder(slotmachine.this);
        LayoutInflater inflater = (slotmachine.this).getLayoutInflater();
        alert.setMessage("If all three image matches then you will win 25 iCoin & with vpn bonus you will get 60 iCoin");
        alert.setCancelable(false);
        alert.setView(inflater.inflate(R.layout.goldcoin,null));
        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                unity();

            }
        });
        alert.create().show();







        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        btn = (Button) findViewById(R.id.btn);
        msg = (TextView) findViewById(R.id.msg);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final MediaPlayer mp1 = MediaPlayer.create(slotmachine.this, R.raw.spin1);
                mp1.start();

                if (isStarted) {
                    wheel1.stopWheel();
                    wheel2.stopWheel();
                    wheel3.stopWheel();

                    if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex == wheel3.currentIndex) {
                        Intent intent =getIntent();
                        int amount=intent.getIntExtra("coin",30);


                        Intent intent1=getIntent();
                        String username=intent1.getStringExtra("username1");
                        DatabaseReference reference= FirebaseDatabase.getInstance("https://i-pay-86691-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User");
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
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


                                    AlertDialog.Builder alert= new AlertDialog.Builder(slotmachine.this);
                                    LayoutInflater inflater = (slotmachine.this).getLayoutInflater();
                                    alert.setTitle("The amount of iCoin you won 60 with 35 vpn bonus");
                                    final MediaPlayer mp = MediaPlayer.create(slotmachine.this, R.raw.slotwin);
                                    mp.start();
                                    alert.setCancelable(false);
                                    alert.setView(inflater.inflate(R.layout.icoins,null));
                                    alert.setNeutralButton("okay", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            unity();
                                            finish();
                                        }
                                    });
                                    alert.create().show();

                                }
                                else{
                                    SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                                    int iCoin_balance=coin.getInt("number",0);
                                    int updateCoins=iCoin_balance+25;
                                    SharedPreferences.Editor EDITOR=coin.edit();
                                    EDITOR.putInt("number",updateCoins);
                                    EDITOR.apply();

                                    AlertDialog.Builder alert= new AlertDialog.Builder(slotmachine.this);
                                    LayoutInflater inflater = (slotmachine.this).getLayoutInflater();
                                    alert.setTitle("The amount of iCoin you won 25 without any vpn bonus");
                                    final MediaPlayer mp = MediaPlayer.create(slotmachine.this, R.raw.slotwin);
                                    mp.start();
                                    alert.setCancelable(false);
                                    alert.setView(inflater.inflate(R.layout.icoins,null));
                                    alert.setNeutralButton("okay", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            unity();
                                            finish();
                                        }
                                    });
                                    alert.create().show();



                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        unity();


                    } else if (wheel1.currentIndex == wheel2.currentIndex || wheel2.currentIndex == wheel3.currentIndex
                            || wheel1.currentIndex == wheel3.currentIndex) {
                        AlertDialog.Builder alert= new AlertDialog.Builder(slotmachine.this);
                        LayoutInflater inflater = (slotmachine.this).getLayoutInflater();
                        alert.setTitle("Try Again Later");
                        alert.setCancelable(false);
                        alert.setView(inflater.inflate(R.layout.youlose,null));
                        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                unity();
                                finish();
                            }
                        });
                        alert.create().show();

                    } else {
                        AlertDialog.Builder alert= new AlertDialog.Builder(slotmachine.this);
                        LayoutInflater inflater = (slotmachine.this).getLayoutInflater();
                        alert.setTitle("Bad luck picture haven't matched");
                        alert.setCancelable(false);
                        alert.setView(inflater.inflate(R.layout.youlose,null));
                        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                unity();
                                finish();
                            }
                        });
                        alert.create().show();
                    }

                    btn.setText("Start");
                    isStarted = false;

                } else {

                    wheel1 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img1.setImageResource(img);
                                }
                            });
                        }
                    }, 200, randomLong(0, 200));

                    wheel1.start();

                    wheel2 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img2.setImageResource(img);
                                }
                            });
                        }
                    }, 200, randomLong(150, 400));

                    wheel2.start();

                    wheel3 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img3.setImageResource(img);
                                }
                            });
                        }
                    }, 200, randomLong(150, 400));

                    wheel3.start();

                    btn.setText("Stop");
                    msg.setText("");
                    isStarted = true;
                }
            }
        });
    }


    private void unity(){
        IUnityAdsShowListener iUnityAdsShowListener=new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                UnityAds.load(AppID);
                UnityAds.show(slotmachine.this,AppID);
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
        UnityAds.show(slotmachine.this,AppID);
    }

}
