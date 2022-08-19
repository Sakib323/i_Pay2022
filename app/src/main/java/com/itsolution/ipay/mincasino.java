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
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class mincasino extends AppCompatActivity {


    private LottieAnimationView play;


    FirebaseDatabase rootNode;
    DatabaseReference reference;

    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;


    private static final String TAG ="--->Native Ad";
    Button BTN1,BTN2,BTN3,BTN4,BTN5,BTN6,BTN7,BTN8,BTN9,BTN10,BTN11,BTN12,BTN13,BTN14;
    TextView Guess;


    // sectors of our wheel (look at the image to see the sectors)
    private static final String[] sectors = {
            "5", "12", "3", "10", "1", "8", "9",
            "2", "7","6", "11","4"};
    @BindView(R.id.spinBtn)
    Button spinBtn;
    @BindView(R.id.resultTv)
    TextView resultTv;
    @BindView(R.id.wheel)
    ImageView wheel;
    // We create a Random instance to make our wheel spin randomly
    private static final Random RANDOM = new Random();
    private int degree = 0, degreeOld = 0;
    // We have 37 sectors on the wheel, we divide 360 by this value to have angle for each sector
    // we divide by 2 to have a half sector
    private static final float HALF_SECTOR = 360f / 12f / 2f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mincasino);
        final MediaPlayer mp1 = MediaPlayer.create(mincasino.this, R.raw.click);

        UnityAds.initialize(mincasino.this,GameID, Testmode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                unity();
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });
        unity();
        SharedPreferences refresh = getSharedPreferences("ref", MODE_PRIVATE);
        SharedPreferences.Editor edit = refresh.edit();
        edit.putBoolean("refresh",true);
        edit.apply();
        edit.commit();

        AlertDialog.Builder alert= new AlertDialog.Builder(mincasino.this);
        LayoutInflater inflater = (mincasino.this).getLayoutInflater();
        alert.setMessage("If you win you will get 5 to 10 times more of your bet amount depending on vpn bonus");
        alert.setCancelable(false);
        alert.setView(inflater.inflate(R.layout.tressure,null));
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
        ButterKnife.bind(this);
        Guess=findViewById(R.id.yourguess);


        BTN1 =(Button) findViewById(R.id.btn1);
        BTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN1.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
                unity();
            }
        });
        BTN2 =(Button) findViewById(R.id.btn2);
        BTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN2.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
                unity();
            }
        });
        BTN3 =(Button) findViewById(R.id.btn3);
        BTN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN3.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
                unity();
            }
        });
        BTN4 =(Button) findViewById(R.id.btn4);
        BTN4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN4.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
                unity();
            }
        });
        BTN5 =(Button) findViewById(R.id.btn5);
        BTN5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN5.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
                unity();
            }
        });
        BTN6 =(Button) findViewById(R.id.btn6);
        BTN6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN6.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
                unity();
            }
        });
        BTN7 =(Button) findViewById(R.id.btn7);
        BTN7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN7.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
                unity();
            }
        });
        BTN8 =(Button) findViewById(R.id.btn8);
        BTN8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN8.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
                unity();
            }
        });
        BTN9 =(Button) findViewById(R.id.btn9);
        BTN9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN9.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
                unity();
            }
        });
        BTN10 =(Button) findViewById(R.id.btn10);
        BTN10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN10.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
                unity();
            }
        });
        BTN11 =(Button) findViewById(R.id.btn11);
        BTN11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN11.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
                unity();
            }
        });
        BTN12 =(Button) findViewById(R.id.btn12);
        BTN12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN12.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
                unity();
            }
        });
        BTN13 =(Button) findViewById(R.id.btn13);
        BTN13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN13.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
                unity();
            }
        });
        BTN14 =(Button) findViewById(R.id.btn14);
        BTN14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN14.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
                unity();
            }
        });




    }



    @OnClick(R.id.spinBtn)
    public void spin(View v) {

        String nmbr2 = Guess.getText().toString();
        TextView amount = findViewById(R.id.amount);
        String coin = amount.getText().toString();
        if (nmbr2.isEmpty()||coin.isEmpty()) {

            AlertDialog.Builder alert = new AlertDialog.Builder(mincasino.this);
            LayoutInflater inflater = (mincasino.this).getLayoutInflater();
            alert.setTitle("Select a number and place an amount as bet");
            alert.setCancelable(false);
            alert.setView(inflater.inflate(R.layout.diamondbox, null));
            alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    unity();
                    finish();
                }
            });
            alert.create().show();


        } else {
            degreeOld = degree % 360;
            // we calculate random angle for rotation of our wheel
            degree = RANDOM.nextInt(360) + 720;
            // rotation effect on the center of the wheel
            RotateAnimation rotateAnim = new RotateAnimation(degreeOld, degree,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            rotateAnim.setDuration(3600);
            rotateAnim.setFillAfter(true);
            rotateAnim.setInterpolator(new DecelerateInterpolator());
            rotateAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    // we empty the result text view when the animation start
                    resultTv.setText("");
                    final MediaPlayer mp = MediaPlayer.create(mincasino.this, R.raw.spin1);
                    mp.start();


                }


                @Override
                public void onAnimationEnd(Animation animation) {
                    // we display the correct sector pointed by the triangle at the end of the rotate animation
                    String value1 = getSector(360 - (degree % 360));
                    resultTv.setText("Number on wheel:- " + value1);
                    TextView mResultText1 = findViewById(R.id.yourguess);
                    String value = mResultText1.getText().toString();
                    if (value1 == value) {



                        Boolean vpnison;
                        SharedPreferences share=getSharedPreferences("active",MODE_PRIVATE);
                        vpnison =share.getBoolean("vpnactive",false);
                        if (vpnison==true){


                            int value2 = Integer.parseInt(coin);
                            SharedPreferences icoin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                            int iCoin_balance=icoin.getInt("number",0);
                            int updateCoins=iCoin_balance+value2*10;
                            SharedPreferences.Editor EDITOR=icoin.edit();
                            EDITOR.putInt("number",updateCoins);
                            EDITOR.apply();






                            final MediaPlayer mp = MediaPlayer.create(mincasino.this, R.raw.slotwin);
                            mp.start();
                            AlertDialog.Builder alert = new AlertDialog.Builder(mincasino.this);
                            LayoutInflater inflater = (mincasino.this).getLayoutInflater();
                            alert.setTitle("Congress number has matched and you got" +(value2*10)+" iCoin with vpn bonus");
                            alert.setCancelable(false);
                            alert.setView(inflater.inflate(R.layout.treswithdia, null));
                            alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    unity();
                                    finish();
                                }
                            });
                            alert.create().show();



                        }
                        else{


                            int value2 = Integer.parseInt(coin);
                            SharedPreferences icoin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                            int iCoin_balance=icoin.getInt("number",0);
                            int updateCoins=iCoin_balance+value2*5;
                            SharedPreferences.Editor EDITOR=icoin.edit();
                            EDITOR.putInt("number",updateCoins);
                            EDITOR.apply();



                            final MediaPlayer mp = MediaPlayer.create(mincasino.this, R.raw.slotwin);
                            mp.start();
                            AlertDialog.Builder alert = new AlertDialog.Builder(mincasino.this);
                            LayoutInflater inflater = (mincasino.this).getLayoutInflater();
                            alert.setTitle("Congress number has matched and you got" +value2*5);
                            alert.setCancelable(false);
                            alert.setView(inflater.inflate(R.layout.treswithdia, null));
                            alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    unity();
                                    finish();
                                }
                            });
                            alert.create().show();

                        }

                    } else {
                        AlertDialog.Builder alert = new AlertDialog.Builder(mincasino.this);
                        LayoutInflater inflater = (mincasino.this).getLayoutInflater();
                        alert.setTitle("Sorry Number haven't matched");
                        alert.setCancelable(false);
                        alert.setView(inflater.inflate(R.layout.youlose, null));
                        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
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
                public void onAnimationRepeat(Animation animation) {

                }
            });

            // we start the animation
            wheel.startAnimation(rotateAnim);
        }
    }

    private String getSector(int degrees) {

        int i = 0;
        String text = null;

        do {
            // start and end of each sector on the wheel
            float start = HALF_SECTOR * (i * 2 + 1);
            float end = HALF_SECTOR * (i * 2 + 3);

            if (degrees >= start && degrees < end) {
                // degrees is in [start;end[
                // so text is equals to sectors[i];
                text = sectors[i];
            }
            i++;




        }
        while (text == null  &&  i < sectors.length);

        return text;





    }



    private void unity(){
        IUnityAdsShowListener iUnityAdsShowListener=new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                UnityAds.load(AppID);
                UnityAds.show(mincasino.this,AppID);
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
        UnityAds.show(mincasino.this,AppID);
    }


}