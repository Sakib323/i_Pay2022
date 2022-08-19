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
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class spinvoucher extends AppCompatActivity {


    private LottieAnimationView play;
    boolean haveornot,haveornot1,haveornot2,haveornot3,haveornot4,haveornot5,haveornot6,haveornot7=false;


    FirebaseDatabase rootNode;
    DatabaseReference reference;

    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;



    private static final String TAG ="--->Native Ad";


    // sectors of our wheel (look at the image to see the sectors)
    private static final String[] sectors = { "1 USD Google Gift Card", "2 Free Fire Diamond",
            "2 USD Amazon Gift Card", "1 USD Bitcoin", "2 USD Paytm", "1 USD Paypal", "0 Reward", "1 USD Amazon Gift Card", "0 Reward","1 USD Paytm"};
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
    private static final float HALF_SECTOR = 360f / 9f / 2f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_spinvoucher);


        Calendar cal = Calendar.getInstance();
        int currentday=cal.get(Calendar.DAY_OF_WEEK);


        UnityAds.initialize(spinvoucher.this,GameID, Testmode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                unity();
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });
        unity();


        SharedPreferences sharedPreferences = getSharedPreferences("spin", MODE_PRIVATE);
        haveornot =sharedPreferences.getBoolean("no",true);
        SharedPreferences sharedPreferences1 = getSharedPreferences("spin1", MODE_PRIVATE);
        haveornot1 =sharedPreferences1.getBoolean("no1",true);
        SharedPreferences sharedPreferences2 = getSharedPreferences("spin2", MODE_PRIVATE);
        haveornot2 =sharedPreferences2.getBoolean("no2",true);
        SharedPreferences sharedPreferences3 = getSharedPreferences("spin3", MODE_PRIVATE);
        haveornot3 =sharedPreferences3.getBoolean("no3",true);
        SharedPreferences sharedPreferences4 = getSharedPreferences("spin4", MODE_PRIVATE);
        haveornot4 =sharedPreferences4.getBoolean("no4",true);
        SharedPreferences sharedPreferences5 = getSharedPreferences("spin5", MODE_PRIVATE);
        haveornot5 =sharedPreferences5.getBoolean("no5",true);
        SharedPreferences sharedPreferences6 = getSharedPreferences("spin6", MODE_PRIVATE);
        haveornot6 =sharedPreferences6.getBoolean("no6",true);
        SharedPreferences sharedPreferences7 = getSharedPreferences("spin7", MODE_PRIVATE);
        haveornot7 =sharedPreferences7.getBoolean("no7",true);


        if (currentday == 1 && haveornot==true) {
            SharedPreferences number = getSharedPreferences("numberofspin", MODE_PRIVATE);
            SharedPreferences.Editor nspin = number.edit();
            nspin.putInt("spinnumber",5);
            nspin.apply();
            nspin.commit();

            unity();
            SharedPreferences SPIN = getSharedPreferences("spin", MODE_PRIVATE);
            SharedPreferences.Editor edit = SPIN.edit();
            edit.putBoolean("no",false);
            edit.apply();

            SharedPreferences SPIN1 = getSharedPreferences("spin1", MODE_PRIVATE);
            SharedPreferences.Editor edit1 = SPIN1.edit();
            edit1.putBoolean("no1",true);
            edit1.apply();

        }
        if (currentday == 2 && haveornot1==true) {
            SharedPreferences number = getSharedPreferences("numberofspin", MODE_PRIVATE);
            SharedPreferences.Editor nspin = number.edit();
            nspin.putInt("spinnumber",5);
            nspin.apply();
            nspin.commit();

            unity();
            SharedPreferences SPIN = getSharedPreferences("spin1", MODE_PRIVATE);
            SharedPreferences.Editor edit = SPIN.edit();
            edit.putBoolean("no1",false);
            edit.apply();


            SharedPreferences SPIN1 = getSharedPreferences("spin2", MODE_PRIVATE);
            SharedPreferences.Editor edit1 = SPIN1.edit();
            edit1.putBoolean("no2",true);
            edit1.apply();

        }
        if (currentday == 3 && haveornot2==true) {
            SharedPreferences number = getSharedPreferences("numberofspin", MODE_PRIVATE);
            SharedPreferences.Editor nspin = number.edit();
            nspin.putInt("spinnumber",5);
            nspin.apply();
            nspin.commit();

            unity();
            SharedPreferences SPIN = getSharedPreferences("spin2", MODE_PRIVATE);
            SharedPreferences.Editor edit = SPIN.edit();
            edit.putBoolean("no2",false);
            edit.apply();


            SharedPreferences SPIN1 = getSharedPreferences("spin3", MODE_PRIVATE);
            SharedPreferences.Editor edit1 = SPIN1.edit();
            edit1.putBoolean("no3",true);
            edit1.apply();

        }

        if (currentday == 4 && haveornot3==true) {
            SharedPreferences number = getSharedPreferences("numberofspin", MODE_PRIVATE);
            SharedPreferences.Editor nspin = number.edit();
            nspin.putInt("spinnumber",5);
            nspin.apply();
            nspin.commit();

            unity();
            SharedPreferences SPIN = getSharedPreferences("spin3", MODE_PRIVATE);
            SharedPreferences.Editor edit = SPIN.edit();
            edit.putBoolean("no3",false);
            edit.apply();


            SharedPreferences SPIN1 = getSharedPreferences("spin4", MODE_PRIVATE);
            SharedPreferences.Editor edit1 = SPIN1.edit();
            edit1.putBoolean("no4",true);
            edit1.apply();

        }
        if (currentday == 4 && haveornot4==true) {
            SharedPreferences number = getSharedPreferences("numberofspin", MODE_PRIVATE);
            SharedPreferences.Editor nspin = number.edit();
            nspin.putInt("spinnumber",5);
            nspin.apply();
            nspin.commit();

            unity();
            SharedPreferences SPIN = getSharedPreferences("spin4", MODE_PRIVATE);
            SharedPreferences.Editor edit = SPIN.edit();
            edit.putBoolean("no4",false);
            edit.apply();


            SharedPreferences SPIN1 = getSharedPreferences("spin5", MODE_PRIVATE);
            SharedPreferences.Editor edit1 = SPIN1.edit();
            edit1.putBoolean("no5",true);
            edit1.apply();

        }
        if (currentday == 5 && haveornot5==true) {
            SharedPreferences number = getSharedPreferences("numberofspin", MODE_PRIVATE);
            SharedPreferences.Editor nspin = number.edit();
            nspin.putInt("spinnumber",5);
            nspin.apply();
            nspin.commit();

            unity();
            SharedPreferences SPIN = getSharedPreferences("spin5", MODE_PRIVATE);
            SharedPreferences.Editor edit = SPIN.edit();
            edit.putBoolean("no5",false);
            edit.apply();


            SharedPreferences SPIN1 = getSharedPreferences("spin6", MODE_PRIVATE);
            SharedPreferences.Editor edit1 = SPIN1.edit();
            edit1.putBoolean("no6",true);
            edit1.apply();
        }
        if (currentday == 6 && haveornot6==true) {
            SharedPreferences number = getSharedPreferences("numberofspin", MODE_PRIVATE);
            SharedPreferences.Editor nspin = number.edit();
            nspin.putInt("spinnumber",5);
            nspin.apply();
            nspin.commit();

            unity();
            SharedPreferences SPIN = getSharedPreferences("spin6", MODE_PRIVATE);
            SharedPreferences.Editor edit = SPIN.edit();
            edit.putBoolean("no6",false);
            edit.apply();


            SharedPreferences SPIN1 = getSharedPreferences("spin7", MODE_PRIVATE);
            SharedPreferences.Editor edit1 = SPIN1.edit();
            edit1.putBoolean("no7",true);
            edit1.apply();

        }
        if (currentday == 7 && haveornot7==true) {
            SharedPreferences number = getSharedPreferences("numberofspin", MODE_PRIVATE);
            SharedPreferences.Editor nspin = number.edit();
            nspin.putInt("spinnumber",5);
            nspin.apply();
            nspin.commit();

            unity();
            SharedPreferences SPIN = getSharedPreferences("spin7", MODE_PRIVATE);
            SharedPreferences.Editor edit = SPIN.edit();
            edit.putBoolean("no7",false);
            edit.apply();


            SharedPreferences SPIN1 = getSharedPreferences("spin", MODE_PRIVATE);
            SharedPreferences.Editor edit1 = SPIN1.edit();
            edit1.putBoolean("no",true);
            edit1.apply();

        }



        SharedPreferences refresh = getSharedPreferences("ref", MODE_PRIVATE);
        SharedPreferences.Editor edit = refresh.edit();
        edit.putBoolean("refresh",true);
        edit.apply();
        edit.commit();


        AlertDialog.Builder alert= new AlertDialog.Builder(spinvoucher.this);
        LayoutInflater inflater = (spinvoucher.this).getLayoutInflater();
        alert.setMessage("Tap on the spin button to play");
        alert.setCancelable(false);
        alert.setView(inflater.inflate(R.layout.icoins,null));
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


    }



    @OnClick(R.id.spinBtn)
    public void spin(View v) {
        unity();
        SharedPreferences nspin = getSharedPreferences("numberofspin", MODE_PRIVATE);
        int spinnumber =nspin.getInt("spinnumber",0);


        TextView life=findViewById(R.id.spin);

        if(spinnumber!=0) {
            int live=spinnumber-1;
            String text=Integer.toString(spinnumber);
            life.setText(text);
            SharedPreferences SPIN = getSharedPreferences("numberofspin", MODE_PRIVATE);
            SharedPreferences.Editor edit = SPIN.edit();
            edit.putInt("spinnumber",live);
            edit.apply();
            unity();
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
                    final MediaPlayer mp = MediaPlayer.create(spinvoucher.this, R.raw.spin1);
                    mp.start();
                    unity();


                }


                @Override
                public void onAnimationEnd(Animation animation) {
                    // we display the correct sector pointed by the triangle at the end of the rotate animation
                    String value1 = getSector(360 - (degree % 360));
                    resultTv.setText(value1);

                    final MediaPlayer mp = MediaPlayer.create(spinvoucher.this, R.raw.slotwin);
                    mp.start();
                    AlertDialog.Builder alert = new AlertDialog.Builder(spinvoucher.this);
                    LayoutInflater inflater = (spinvoucher.this).getLayoutInflater();
                    alert.setTitle("Congress You Won " + value1);
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

                    if(value1.equals("1 USD Google Gift Card")){
                        unity();
                        SharedPreferences sharedPref = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
                        float google=sharedPref.getFloat("google",0);
                        SharedPreferences.Editor EDITOR=sharedPref.edit();
                        EDITOR.putFloat("google",1+google);
                        EDITOR.apply();

                    }
                    if(value1.equals("2 Free Fire Diamond")){
                        unity();
                        SharedPreferences sharedPref = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
                        int google=sharedPref.getInt("freefire",0);
                        SharedPreferences.Editor EDITOR=sharedPref.edit();
                        EDITOR.putInt("freefire",2+google);
                        EDITOR.apply();

                    }
                    if(value1.equals("2 USD Amazon Gift Card")){

                        SharedPreferences sharedPref = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
                        float google=sharedPref.getFloat("amazon",0);
                        SharedPreferences.Editor EDITOR=sharedPref.edit();
                        EDITOR.putFloat("amazon",2+google);
                        EDITOR.apply();
                        unity();


                    }
                    if(value1.equals("1 USD Bitcoin")){
                        SharedPreferences sharedPref = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
                        float google=sharedPref.getFloat("bitcoin",0);
                        SharedPreferences.Editor EDITOR=sharedPref.edit();
                        EDITOR.putFloat("bitcoin",0+google);
                        EDITOR.apply();
                        unity();
                    }
                    if(value1.equals("2 USD Paytm")){
                        SharedPreferences sharedPref = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
                        float google=sharedPref.getFloat("paytm",0);
                        SharedPreferences.Editor EDITOR=sharedPref.edit();
                        EDITOR.putFloat("paytm",2+google);
                        EDITOR.apply();
                        unity();

                    }
                    if(value1.equals("1 USD Paypal")){
                        SharedPreferences sharedPref = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
                        float google=sharedPref.getFloat("paypal",0);
                        SharedPreferences.Editor EDITOR=sharedPref.edit();
                        EDITOR.putFloat("paypal",1+google);
                        EDITOR.apply();
                        unity();
                    }
                    if(value1.equals("0 Reward")){
                        AlertDialog.Builder alert1 = new AlertDialog.Builder(spinvoucher.this);
                        LayoutInflater inflater1 = (spinvoucher.this).getLayoutInflater();
                        alert1.setTitle("Sorry You got nothing");
                        alert1.setCancelable(false);
                        alert1.setView(inflater1.inflate(R.layout.treswithdia, null));
                        alert1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                unity();
                                finish();
                            }
                        });
                        alert1.create().show();
                    }
                    if(value1.equals("1 USD Amazon Gift Card")){
                        SharedPreferences sharedPref = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
                        float google=sharedPref.getFloat("amazon",0);
                        SharedPreferences.Editor EDITOR=sharedPref.edit();
                        EDITOR.putFloat("amazon",1+google);
                        EDITOR.apply();
                        unity();

                    }
                    if(value1.equals("0 Reward")){
                        AlertDialog.Builder alert1 = new AlertDialog.Builder(spinvoucher.this);
                        LayoutInflater inflater1 = (spinvoucher.this).getLayoutInflater();
                        alert1.setTitle("Sorry You got nothing");
                        alert1.setCancelable(false);
                        alert1.setView(inflater1.inflate(R.layout.treswithdia, null));
                        alert1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                unity();
                                finish();
                            }
                        });
                        alert1.create().show();

                    }
                    if(value1.equals("1 USD Paytm")){
                        SharedPreferences sharedPref = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
                        float google=sharedPref.getFloat("paytm",0);
                        SharedPreferences.Editor EDITOR=sharedPref.edit();
                        EDITOR.putFloat("paytm",1+google);
                        EDITOR.apply();
                        unity();

                    }


                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            // we start the animation
            wheel.startAnimation(rotateAnim);
        }else {

            AlertDialog.Builder alert= new AlertDialog.Builder(spinvoucher.this);
            LayoutInflater inflater = (spinvoucher.this).getLayoutInflater();
            alert.setMessage("You don't have any spin left");
            alert.setCancelable(false);
            alert.setView(inflater.inflate(R.layout.icoins,null));
            alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    unity();
                }
            });
            alert.create().show();

        }
    }//

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
                UnityAds.show(spinvoucher.this,AppID);
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
        UnityAds.show(spinvoucher.this,AppID);
    }



}