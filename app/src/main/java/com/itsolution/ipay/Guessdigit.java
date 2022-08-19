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
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Guessdigit extends AppCompatActivity {


    private LottieAnimationView play;
    TextInputLayout numberbyuser, betam;
    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;

    FirebaseDatabase rootNode;
    DatabaseReference reference;



    private static final String TAG = "--->Native Ad";


    // sectors of our wheel (look at the image to see the sectors)
    private static final String[] sectors = {"32", "15",
            "19", "4", "21", "2", "25", "17", "34",
            "6", "27", "13", "36", "11", "30", "8",
            "23", "10", "5", "24", "16", "33",
            "1", "20", "14", "31", "9", "22",
            "18", "29", "7", "28", "12", "35",
            "3", "26", "0"
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
    private static final float HALF_SECTOR = 360f / 37f / 2f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guessdigit);

        numberbyuser = findViewById(R.id.enterednumber);

        betam = findViewById(R.id.bet);


        UnityAds.initialize(Guessdigit.this,GameID, Testmode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                unity();

            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });
        unity();


        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();

        AlertDialog.Builder alert = new AlertDialog.Builder(Guessdigit.this);
        LayoutInflater inflater = (Guessdigit.this).getLayoutInflater();
        alert.setTitle("Guess a digit");
        alert.setMessage("Guess a number and place an amount and make a spin.if you win you will get 10 times more of your batted amount");
        alert.setCancelable(false);
        alert.setView(inflater.inflate(R.layout.treswithdia, null));
        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              unity();
            }
        });
        alert.create().show();





        ButterKnife.bind(this);
    }



    @OnClick(R.id.spinBtn)
    public void spin(View v) {

        String userEnteredvalue = numberbyuser.getEditText().getText().toString().trim();
        String userammmount = betam.getEditText().getText().toString().trim();
        if (userEnteredvalue.isEmpty()|| userammmount.isEmpty()){

            AlertDialog.Builder alert = new AlertDialog.Builder(Guessdigit.this);
            LayoutInflater inflater = (Guessdigit.this).getLayoutInflater();
            alert.setTitle("Guess the digit & place amount.Make sure you have vpn connection(USA,UK,Canadian server) to get vpn bonus.");
            alert.setCancelable(false);
            alert.setView(inflater.inflate(R.layout.icoins, null));
            alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                unity();
                }
            });
            alert.create().show();

        }
        else{


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
                final MediaPlayer mp = MediaPlayer.create(Guessdigit.this, R.raw.spin1);
                mp.start();


            }


            @Override
            public void onAnimationEnd(Animation animation) {
                // we display the correct sector pointed by the triangle at the end of the rotate animation
                String value1 = getSector(360 - (degree % 360));
                resultTv.setText(value1);
                String userEnteredvalue = numberbyuser.getEditText().getText().toString().trim();
                String userammmount = betam.getEditText().getText().toString().trim();
                int amm = Integer.parseInt(userammmount);
                if (userEnteredvalue == value1) {




                    Boolean vpnison;
                    SharedPreferences share=getSharedPreferences("active",MODE_PRIVATE);
                    vpnison =share.getBoolean("vpnactive",false);
                    if (vpnison==true){
                        SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                        int iCoin_balance=coin.getInt("number",0);
                        int updateCoins=iCoin_balance+amm*10;
                        SharedPreferences.Editor EDITOR=coin.edit();
                        EDITOR.putInt("number",updateCoins);
                        EDITOR.apply();



                        Toast.makeText(Guessdigit.this, "Congress", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder alert = new AlertDialog.Builder(Guessdigit.this);
                        LayoutInflater inflater = (Guessdigit.this).getLayoutInflater();
                        alert.setTitle("You Won,Now you will get 10x (10 times) of " +amm+ " with +5 times vpn bonus");
                        alert.setCancelable(false);
                        alert.setView(inflater.inflate(R.layout.cong, null));
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
                        SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                        int iCoin_balance=coin.getInt("number",0);
                        int updateCoins=iCoin_balance+amm*5;
                        SharedPreferences.Editor EDITOR=coin.edit();
                        EDITOR.putInt("number",updateCoins);
                        EDITOR.apply();


                        Toast.makeText(Guessdigit.this, "Congress", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder alert = new AlertDialog.Builder(Guessdigit.this);
                        LayoutInflater inflater = (Guessdigit.this).getLayoutInflater();
                        alert.setTitle("You Won,Now you will get 10x (10 times) of " + amm+" without any bonus");
                        alert.setCancelable(false);
                        alert.setView(inflater.inflate(R.layout.cong, null));
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
                    AlertDialog.Builder alert = new AlertDialog.Builder(Guessdigit.this);
                    LayoutInflater inflater = (Guessdigit.this).getLayoutInflater();
                    alert.setTitle("You Lose,Now you will lose" + amm);
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

                    SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                    int iCoin_balance=coin.getInt("number",0);
                    int updateCoins=iCoin_balance-amm;
                    SharedPreferences.Editor EDITOR=coin.edit();
                    EDITOR.putInt("number",updateCoins);
                    EDITOR.apply();


                    Toast.makeText(Guessdigit.this, "You Lose", Toast.LENGTH_SHORT).show();
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
                UnityAds.show(Guessdigit.this,AppID);
            }

            @Override
            public void onUnityAdsShowStart(String s) {
                Toast.makeText(Guessdigit.this, "Click this ad to earn iCoin as bonus", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onUnityAdsShowClick(String s) {
                Toast.makeText(Guessdigit.this, "Congress!You got 30 iCoin as bonus", Toast.LENGTH_SHORT).show();
                Intent intent1=getIntent();
                String username=intent1.getStringExtra("username1");
                SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                int iCoin_balance=coin.getInt("number",0);
                int updateCoins=iCoin_balance+30;
                SharedPreferences.Editor EDITOR=coin.edit();
                EDITOR.putInt("number",updateCoins);
                EDITOR.apply();
            }

            @Override
            public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {

            }
        };
        UnityAds.load(AppID);
        UnityAds.show(Guessdigit.this,AppID);
    }


}