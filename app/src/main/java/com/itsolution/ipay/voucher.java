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
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;

public class voucher extends AppCompatActivity {


    Button voucherbalance;
    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();

        unity();


        UnityAds.initialize(voucher.this,GameID, Testmode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                unity();
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });
        unity();



        voucherbalance=findViewById(R.id.button3);
        voucherbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(voucher.this,personalize.class);
                startActivity(intent);
                unity();
            }
        });


        TextView claim=findViewById(R.id.btn1);
        claim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
                Boolean voucher=dailycheckin.getBoolean("1",false);
                if(voucher==true){
                    SharedPreferences dailycheck = getSharedPreferences("voucher", MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = dailycheck.edit();
                    editor1.putBoolean("1",false);
                    editor1.apply();
                    unity();
                    float googlecard = (float)(Math.random() * .001 + .05);
                    SharedPreferences sharedPref = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
                    float google=sharedPref.getFloat("google",0);
                    SharedPreferences.Editor EDITOR=sharedPref.edit();
                    EDITOR.putFloat("google",googlecard+google);
                    EDITOR.apply();

                    AlertDialog.Builder alert= new AlertDialog.Builder(voucher.this);
                    LayoutInflater inflater = (voucher.this).getLayoutInflater();
                    alert.setMessage("Amount you got on Google play gift card through check-in "+googlecard);
                    alert.setCancelable(false);
                    alert.setView(inflater.inflate(R.layout.diamondbox,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();

                    SharedPreferences news=getSharedPreferences("checkin",MODE_PRIVATE);
                    SharedPreferences.Editor edit=news.edit();
                    edit.putBoolean("checkornot",false);
                    edit.apply();
                    claim.setText("claimed");
                }
                else{

                    unity();
                    AlertDialog.Builder alert= new AlertDialog.Builder(voucher.this);
                    LayoutInflater inflater = (voucher.this).getLayoutInflater();
                    alert.setMessage("You already have claimed it or it's too late to claim the reward");
                    alert.setCancelable(false);
                    alert.setView(inflater.inflate(R.layout.dialog,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();
                    claim.setText("claimed");
                }
            }
        });
        TextView claim2=findViewById(R.id.btn2);
        claim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
                Boolean voucher=dailycheckin.getBoolean("2",false);
                if(voucher==true){
                    SharedPreferences dailycheck = getSharedPreferences("voucher", MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = dailycheck.edit();
                    editor1.putBoolean("2",false);
                    editor1.apply();
                    unity();
                    claim2.setText("claimed");

                    float amazoncard = (float)(Math.random() * .001 + .05);
                    SharedPreferences sharedPref = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
                    float google=sharedPref.getFloat("amazon",0);
                    SharedPreferences.Editor EDITOR=sharedPref.edit();
                    EDITOR.putFloat("amazon",amazoncard+google);
                    EDITOR.apply();


                    AlertDialog.Builder alert= new AlertDialog.Builder(voucher.this);
                    LayoutInflater inflater = (voucher.this).getLayoutInflater();
                    alert.setMessage("Amount you got on Amazon gift card through check-in "+amazoncard);
                    alert.setCancelable(false);
                    alert.setView(inflater.inflate(R.layout.diamondbox,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();

                }
                else{
                    unity();
                    AlertDialog.Builder alert= new AlertDialog.Builder(voucher.this);
                    LayoutInflater inflater = (voucher.this).getLayoutInflater();
                    alert.setMessage("You already have claimed it or it's too late to claim the reward");
                    alert.setCancelable(false);

                    alert.setView(inflater.inflate(R.layout.treswithdia,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();
                    claim2.setText("claimed");

                }
            }
        });
        TextView claim3=findViewById(R.id.btn3);
        claim3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
                Boolean voucher=dailycheckin.getBoolean("3",false);
                if(voucher==true){
                    SharedPreferences dailycheck = getSharedPreferences("voucher", MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = dailycheck.edit();
                    editor1.putBoolean("3",false);
                    editor1.apply();
                    unity();
                    claim3.setText("claimed");


                    float paypalcard = (float)(Math.random() * .001 + .05);
                    SharedPreferences sharedPref = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
                    float google=sharedPref.getFloat("paypal",0);
                    SharedPreferences.Editor EDITOR=sharedPref.edit();
                    EDITOR.putFloat("paypal",paypalcard+google);
                    EDITOR.apply();


                    AlertDialog.Builder alert= new AlertDialog.Builder(voucher.this);
                    LayoutInflater inflater = (voucher.this).getLayoutInflater();
                    alert.setMessage("Amount you got on PayPal account through check-in "+paypalcard);
                    alert.setCancelable(false);
                    alert.setView(inflater.inflate(R.layout.diamondbox,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();

                }
                else{
                    unity();
                    AlertDialog.Builder alert= new AlertDialog.Builder(voucher.this);
                    LayoutInflater inflater = (voucher.this).getLayoutInflater();
                    alert.setMessage("You already have claimed it or it's too late to claim the reward");
                    alert.setCancelable(false);

                    alert.setView(inflater.inflate(R.layout.dialog,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();
                    claim3.setText("claimed");
                }
            }
        });
        TextView claim4=findViewById(R.id.btn4);
        claim4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
                Boolean voucher=dailycheckin.getBoolean("4",false);
                if(voucher==true){
                    SharedPreferences dailycheck = getSharedPreferences("voucher", MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = dailycheck.edit();
                    editor1.putBoolean("4",false);
                    editor1.apply();
                    claim4.setText("claimed");
                    float paytmcard = (float)(Math.random() * .001 + .05);
                    SharedPreferences sharedPref = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
                    float google=sharedPref.getFloat("paytm",0);
                    SharedPreferences.Editor EDITOR=sharedPref.edit();
                    EDITOR.putFloat("paytm",paytmcard+google);
                    EDITOR.apply();

                    unity();


                    AlertDialog.Builder alert= new AlertDialog.Builder(voucher.this);
                    LayoutInflater inflater = (voucher.this).getLayoutInflater();
                    alert.setMessage("Amount you got on your paytm account through check-in "+paytmcard);
                    alert.setCancelable(false);
                    alert.setView(inflater.inflate(R.layout.diamondbox,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();

                }
                else{
                    unity();
                    AlertDialog.Builder alert= new AlertDialog.Builder(voucher.this);
                    LayoutInflater inflater = (voucher.this).getLayoutInflater();
                    alert.setMessage("You already have claimed it or it's too late to claim the reward");
                    alert.setCancelable(false);

                    alert.setView(inflater.inflate(R.layout.treswithdia,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();
                    claim4.setText("claimed");
                }
            }
        });
        TextView claim5=findViewById(R.id.btn5);
        claim5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
                Boolean voucher=dailycheckin.getBoolean("5",false);
                if(voucher==true){
                    SharedPreferences dailycheck = getSharedPreferences("voucher", MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = dailycheck.edit();
                    editor1.putBoolean("5",false);
                    editor1.apply();
                    claim5.setText("claimed");
                    float bitcoincard = (float)(Math.random() * 0.0000013 + 0.000000325);
                    SharedPreferences sharedPref = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
                    float google=sharedPref.getFloat("bitcoin",0);
                    SharedPreferences.Editor EDITOR=sharedPref.edit();
                    EDITOR.putFloat("bitcoin",bitcoincard+google);
                    EDITOR.apply();
                    unity();


                    AlertDialog.Builder alert= new AlertDialog.Builder(voucher.this);
                    LayoutInflater inflater = (voucher.this).getLayoutInflater();
                    alert.setMessage("Amount you got on your bitcoin wallet through check-in "+bitcoincard);
                    alert.setCancelable(false);
                    alert.setView(inflater.inflate(R.layout.dialog,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();

                }
                else{
                    unity();
                    AlertDialog.Builder alert= new AlertDialog.Builder(voucher.this);
                    LayoutInflater inflater = (voucher.this).getLayoutInflater();
                    alert.setMessage("You already have claimed it or it's too late to claim the reward");
                    alert.setCancelable(false);

                    alert.setView(inflater.inflate(R.layout.treswithdia,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();
                    claim5.setText("claimed");
                }
            }
        });
        TextView claim6=findViewById(R.id.btn6);
        claim6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
                Boolean voucher=dailycheckin.getBoolean("6",false);
                if(voucher==true){
                    SharedPreferences dailycheck = getSharedPreferences("voucher", MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = dailycheck.edit();
                    editor1.putBoolean("6",false);
                    editor1.apply();
                    claim6.setText("claimed");
                    unity();


                    int freefirecard = (int)(Math.random() * 3 + 1);
                    SharedPreferences sharedPref = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
                    int google=sharedPref.getInt("freefire",0);
                    SharedPreferences.Editor EDITOR=sharedPref.edit();
                    EDITOR.putInt("freefire",freefirecard+google);
                    EDITOR.apply();

                    AlertDialog.Builder alert= new AlertDialog.Builder(voucher.this);
                    LayoutInflater inflater = (voucher.this).getLayoutInflater();
                    alert.setMessage("Free Fire diamond you got through check-in "+freefirecard);
                    alert.setCancelable(false);
                    alert.setView(inflater.inflate(R.layout.treswithdia,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();

                }
                else{
                    unity();
                    AlertDialog.Builder alert= new AlertDialog.Builder(voucher.this);
                    LayoutInflater inflater = (voucher.this).getLayoutInflater();
                    alert.setMessage("You already have claimed it or it's too late to claim the reward.");
                    alert.setCancelable(false);

                    alert.setView(inflater.inflate(R.layout.diamondbox,null));
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            unity();
                        }
                    });
                    alert.show();
                    claim6.setText("claimed");
                }
            }
        });

    }

    private void unity(){
        IUnityAdsShowListener iUnityAdsShowListener=new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                UnityAds.load(AppID);
                UnityAds.show(voucher.this,AppID);
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
        UnityAds.show(voucher.this,AppID);
    }
}