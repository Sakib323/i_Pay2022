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
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class Quiz extends AppCompatActivity {


        TextView tv;
        Button submitbutton, quitbutton;
        RadioGroup radio_g;
        RadioButton rb1,rb2,rb3,rb4;

    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;

        String questions[] = {
                "Which country proposed a five-year extension of New START Treaty with Russia?",
                "Marcelo Rebelo de Sousa has been re-elected as the President of which nation?",
                "Which country has recently amended its citizenship laws to attract individuals with specialized skills?",
                "Which country is set to create a public platform to track polluters?",
                "Which country is the largest funder of the World Health Organisation (WHO)?",
                "Which is the first country to receive the batch of free Covid vaccines through COVAX scheme?",
                "India has donated a ‘Digital cobalt therapy machine’ developed by BARC, to which country?",
                "Which is the headquarters of the UN’s Economic Commission for Europe (UNECE)?",
                "Which country successfully launched 38 foreign satellites from 18 countries using its Soyuz-2.1a carrier rocket?",
                "Which country has vaccinated 93% of its adult population in 16 days?",
                "HEAL-COVID, which was making news recently, is a nation-wide trial to be implemented in which country?",
                "MS-Word is an example of _____?",
                "Ctrl, Shift and Alt are called .......... keys?",
                "A computer cannot \"boot\" if it does not have the _____?",
                "________ is the process of dividing the disk into tracks and sectors?",
                "Junk e-mail is also called ______?",
                "Microsoft Office is an example of a",
                "By default, your documents print in ________ mode?",
                "25+25=?",
                "300/3=?"
        };
        String answers[] = {"United States","Portugal","UAE","China","United States","Ghana","Madagascar","Geneva","Russia","Bhutan","UK","Application software",
        "modifier","Operating system","Formatting","Spam","Horizontal market software","Portrait","50","100"};
        String opt[] = {
                "United States","United Kingdom","Japan","China",
                "Portugal","Italy","Germany","France",
                "India","UAE","US","Germany",
                "United Kingdom","China","Brazil","Australia",
                "China","United States","Russia","United Kingdom",
                "DRC","Ghana","Sudan","Ethiopia",
                "Mauritius","Madagascar","Myanmar","Maldives",
                "Geneva","Brussels","Rome","Paris",
                "China","Japan","Russia","UAE",
                "Bhutan","India","Sri lanka","Nepal",
                "USA","UK","Russia","Taiwan",
                "An operating system","A processing device","Application software","An input device",
                "modifier","function","alphanumeric","adjustment",
                "Compiler","Loader","Operating system","Assembler",
                "Tracking","Formatting","Crashing","Allotting",
                "Spam","Spoof","Sniffer script","Spool",
                "Closed source software","Open source software","Horizontal market software","vertical market software",
                "Landscape","Portrait","Page Setup","Print View",
                "65","50","35","37",
                 "250","150","100","120"};

        int flag = (int)(Math.random() * 10 + 0);
        public static int marks=0,correct=0,wrong=0;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_quiz);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



            UnityAds.initialize(Quiz.this,GameID, Testmode, new IUnityAdsInitializationListener() {
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

            ActionBar actionBar;
            actionBar = getSupportActionBar();
            actionBar.hide();



            AlertDialog.Builder alert= new AlertDialog.Builder(Quiz.this);
            LayoutInflater inflater = (Quiz.this).getLayoutInflater();
            alert.setMessage("Answer the multiple question and get iCoin as reward.You will receive 7 to 3 iCoin for each right answer depending on vpn bonus");
            alert.setCancelable(false);
            alert.setView(inflater.inflate(R.layout.quiz,null));
            alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    unity();
                }
            });
            alert.create().show();




            final TextView score = (TextView)findViewById(R.id.textView4);

            submitbutton=(Button)findViewById(R.id.button3);
            quitbutton=(Button)findViewById(R.id.buttonquit);
            tv=(TextView) findViewById(R.id.tvque);

            radio_g=(RadioGroup)findViewById(R.id.answersgrp);
            rb1=(RadioButton)findViewById(R.id.radioButton);
            rb2=(RadioButton)findViewById(R.id.radioButton2);
            rb3=(RadioButton)findViewById(R.id.radioButton3);
            rb4=(RadioButton)findViewById(R.id.radioButton4);
            tv.setText(questions[flag]);
            rb1.setText(opt[0]);
            rb2.setText(opt[1]);
            rb3.setText(opt[2]);
            rb4.setText(opt[3]);
            submitbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    unity();

                    if(radio_g.getCheckedRadioButtonId()==-1)
                    {
                        Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                    String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
                    if(ansText.equals(answers[flag])) {
                        correct++;
                        unity();
                        Intent intent1=getIntent();
                        String username=intent1.getStringExtra("username1");
                        int amount=intent1.getIntExtra("coin",5);

                        DatabaseReference reference= FirebaseDatabase.getInstance("https://i-pay-86691-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User");
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange( DataSnapshot snapshot) {
                                Boolean vpnison;
                                SharedPreferences share=getSharedPreferences("active",MODE_PRIVATE);
                                vpnison =share.getBoolean("vpnactive",false);
                                if (vpnison==true){
                                    SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                                    int iCoin_balance=coin.getInt("number",0);
                                    int updateCoins=iCoin_balance+7;
                                    SharedPreferences.Editor EDITOR=coin.edit();
                                    EDITOR.putInt("number",updateCoins);
                                    EDITOR.apply();
                                    Toast.makeText(getApplicationContext(), "Correct,You Have Earned 7 Coin with vpn bonus", Toast.LENGTH_SHORT).show();
                                    unity();
                                }
                                else{
                                    SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                                    int iCoin_balance=coin.getInt("number",0);
                                    int updateCoins=iCoin_balance+3;
                                    SharedPreferences.Editor EDITOR=coin.edit();
                                    EDITOR.putInt("number",updateCoins);
                                    EDITOR.apply();
                                    unity();
                                    Toast.makeText(getApplicationContext(), "Correct,You Have Earned 3 Coin without vpn bonus", Toast.LENGTH_SHORT).show();


                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                    }
                    else {
                        wrong++;
                        Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                        unity();
                    }

                    flag++;

                    if (score != null)
                        score.setText(""+correct);

                    if(flag<questions.length)
                    {
                        tv.setText(questions[flag]);
                        rb1.setText(opt[flag*4]);
                        rb2.setText(opt[flag*4 +1]);
                        rb3.setText(opt[flag*4 +2]);
                        rb4.setText(opt[flag*4 +3]);
                    }
                    else
                    {

                        Intent intent =getIntent();
                        int amount=intent.getIntExtra("coin",5);
                        marks=correct;
                        AlertDialog.Builder alert= new AlertDialog.Builder(Quiz.this);
                        LayoutInflater inflater = (Quiz.this).getLayoutInflater();
                        alert.setTitle("iPay Quiz");
                        alert.setMessage("iCoin you got "+correct*5);

                        alert.setCancelable(false);
                        alert.setView(inflater.inflate(R.layout.quiz,null));
                        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               finish();
                               unity();
                            }
                        });
                        alert.create().show();
                    }
                    radio_g.clearCheck();
                }
            });

            quitbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   finish();
                   unity();
                }
            });
        }



    private void unity(){
        IUnityAdsShowListener iUnityAdsShowListener=new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                UnityAds.load(AppID);
                UnityAds.show(Quiz.this,AppID);
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
        UnityAds.show(Quiz.this,AppID);
    }

    }