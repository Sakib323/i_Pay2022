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

public class casino extends AppCompatActivity {

    private AdView mAdView;
    private LottieAnimationView play;


    FirebaseDatabase rootNode;
    DatabaseReference reference;

    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;


    private static final String TAG ="--->Native Ad";
    Button BTN1,BTN2,BTN3,BTN4,BTN5,BTN6,BTN7,BTN8,BTN9,BTN10,BTN11,BTN12,BTN13,BTN14,BTN15,BTN16,BTN17,BTN18,BTN19,BTN20,BTN21,BTN22,BTN23,BTN24,BTN25,BTN26,BTN27,BTN28,BTN29,BTN30,BTN31,BTN32,BTN33,BTN34,BTN35;
    TextView Guess;

    private static final String[] sectors = { "28", "9",
            "26", "30", "11", "7", "20", "32", "17",
            "5", "22","34", "15", "3", "24", "36",
            "13", "1", "00", "27", "10", "25",
            "29", "12", "8", "19", "31", "18",
            "6", "21", "33", "16", "4", "23",
            "35", "14", "2","0"
    };


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
    private static final float HALF_SECTOR = 360f / 39f / 2f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_casino);
        final MediaPlayer mp1 = MediaPlayer.create(casino.this, R.raw.click);

        SharedPreferences refresh = getSharedPreferences("ref", MODE_PRIVATE);
        SharedPreferences.Editor edit = refresh.edit();
        edit.putBoolean("refresh",true);
        edit.apply();
        edit.commit();

        UnityAds.initialize(casino.this,GameID, Testmode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                unity();
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });
        unity();

        AlertDialog.Builder alert= new AlertDialog.Builder(casino.this);
        LayoutInflater inflater = (casino.this).getLayoutInflater();
        alert.setMessage("If you win you will get 10 times more of your bet amount and to get 30 times more then make sure you have a vpn connection(UK,US,Canadian server)");
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
            }
        });
        BTN2 =(Button) findViewById(R.id.btn2);
        BTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN2.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN3 =(Button) findViewById(R.id.btn3);
        BTN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN3.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN4 =(Button) findViewById(R.id.btn4);
        BTN4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN4.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN5 =(Button) findViewById(R.id.btn5);
        BTN5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN5.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN6 =(Button) findViewById(R.id.btn6);
        BTN6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN6.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN7 =(Button) findViewById(R.id.btn7);
        BTN7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN7.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN8 =(Button) findViewById(R.id.btn8);
        BTN8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN8.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN9 =(Button) findViewById(R.id.btn9);
        BTN9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN9.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN10 =(Button) findViewById(R.id.btn10);
        BTN10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN10.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN11 =(Button) findViewById(R.id.btn11);
        BTN11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN11.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN12 =(Button) findViewById(R.id.btn12);
        BTN12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN12.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN13 =(Button) findViewById(R.id.btn13);
        BTN13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN13.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN14 =(Button) findViewById(R.id.btn14);
        BTN14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN14.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN15 =(Button) findViewById(R.id.btn15);
        BTN15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN15.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN16 =(Button) findViewById(R.id.btn16);
        BTN16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN16.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN17 =(Button) findViewById(R.id.btn17);
        BTN17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN17.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN18 =(Button) findViewById(R.id.btn18);
        BTN18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN18.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN19 =(Button) findViewById(R.id.btn19);
        BTN19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN19.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN20 =(Button) findViewById(R.id.btn20);
        BTN20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN20.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN21 =(Button) findViewById(R.id.btn21);
        BTN21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN21.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN22 =(Button) findViewById(R.id.btn22);
        BTN22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN22.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN23 =(Button) findViewById(R.id.btn23);
        BTN23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN23.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN24 =(Button) findViewById(R.id.btn24);
        BTN24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN24.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN25 =(Button) findViewById(R.id.btn25);
        BTN25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN25.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN26 =(Button) findViewById(R.id.btn26);
        BTN26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN26.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN27 =(Button) findViewById(R.id.btn27);
        BTN27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN27.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN28 =(Button) findViewById(R.id.btn28);
        BTN28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN28.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN29 =(Button) findViewById(R.id.btn29);
        BTN29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN29.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN30 =(Button) findViewById(R.id.btn30);
        BTN30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN30.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN31 =(Button) findViewById(R.id.btn31);
        BTN31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN31.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN32 =(Button) findViewById(R.id.btn32);
        BTN32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN32.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN33 =(Button) findViewById(R.id.btn33);
        BTN33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN33.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN34 =(Button) findViewById(R.id.btn34);
        BTN34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN34.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
        BTN35 =(Button) findViewById(R.id.btn35);
        BTN35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=BTN35.getText().toString();
                Guess.setText("Batted On:- "+value);
                mp1.start();
            }
        });
    }



    @OnClick(R.id.spinBtn)
    public void spin(View v) {

        String nmbr2=Guess.getText().toString();
        TextView amount=findViewById(R.id.amount);
        String coin=amount.getText().toString();

        if (nmbr2.isEmpty()||coin.isEmpty()) {

            AlertDialog.Builder alert = new AlertDialog.Builder(casino.this);
            LayoutInflater inflater = (casino.this).getLayoutInflater();
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
                    final MediaPlayer mp = MediaPlayer.create(casino.this, R.raw.spin1);
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


                            SharedPreferences Icoin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                            int iCoin_balance=Icoin.getInt("number",0);
                            int value2 = Integer.parseInt(coin);
                            int updateCoins=iCoin_balance+value2*30;
                            SharedPreferences.Editor EDITOR=Icoin.edit();
                            EDITOR.putInt("number",updateCoins);
                            EDITOR.apply();





                            final MediaPlayer mp = MediaPlayer.create(casino.this, R.raw.slotwin);
                            mp.start();
                            AlertDialog.Builder alert = new AlertDialog.Builder(casino.this);
                            LayoutInflater inflater = (casino.this).getLayoutInflater();
                            alert.setTitle("Congress number has matched and you got" +(value2*30)+" iCoin with vpn bonus");
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
                            SharedPreferences Icoin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                            int iCoin_balance=Icoin.getInt("number",0);
                            int value2 = Integer.parseInt(coin);
                            int updateCoins=iCoin_balance+value2*10;
                            SharedPreferences.Editor EDITOR=Icoin.edit();
                            EDITOR.putInt("number",updateCoins);
                            EDITOR.apply();

                            final MediaPlayer mp = MediaPlayer.create(casino.this, R.raw.slotwin);
                            mp.start();
                            AlertDialog.Builder alert = new AlertDialog.Builder(casino.this);
                            LayoutInflater inflater = (casino.this).getLayoutInflater();
                            alert.setTitle("Congress number has matched and you got" +value2*10);
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
                        AlertDialog.Builder alert = new AlertDialog.Builder(casino.this);
                        LayoutInflater inflater = (casino.this).getLayoutInflater();
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
                UnityAds.show(casino.this,AppID);
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
        UnityAds.show(casino.this,AppID);
    }


}