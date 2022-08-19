package com.itsolution.ipay;

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

import com.airbnb.lottie.LottieAnimationView;

import lecho.lib.hellocharts.view.LineChartView;

public class personalize extends AppCompatActivity {

    TextView name1,username1,phoneNo1,email1,value,diamond;

    LineChartView lineChartView;
    LottieAnimationView howtoearn,earnmore,notfication;
    Button see;

    int[] yAxisData = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalize2);

        SharedPreferences sharedPref1 = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
        float paypal1=sharedPref1.getFloat("paypal",0);
        float amazon1=sharedPref1.getFloat("google",0);
        int freefire1=sharedPref1.getInt("freefire",0);
        float bitcoin1=sharedPref1.getFloat("bitcoin",0);
        float google1=sharedPref1.getFloat("google",0);
        float paytm1=sharedPref1.getFloat("paytm",0);

        TextView paypalwithdra=findViewById(R.id.withdrawpaypal);
        paypalwithdra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(personalize.this);
                LayoutInflater inflater = (personalize.this).getLayoutInflater();
                alert.setMessage("Minimum cash out limit is $100.So you can't withdraw.Your paypal account balance is "+paypal1);
                alert.setCancelable(false);

                alert.setView(inflater.inflate(R.layout.dialog,null));
                alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();


            }
        });
        TextView amazonwithdra=findViewById(R.id.withdrawamazon);
        amazonwithdra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(personalize.this);
                LayoutInflater inflater = (personalize.this).getLayoutInflater();
                alert.setMessage("Minimum cash out limit is $100.So you can't withdraw.Your amazon account balance is "+amazon1);
                alert.setCancelable(false);

                alert.setView(inflater.inflate(R.layout.dialog,null));
                alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();
            }
        });

        TextView paytmwithdra=findViewById(R.id.withdrawpaytm);
        paytmwithdra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(personalize.this);
                LayoutInflater inflater = (personalize.this).getLayoutInflater();
                alert.setMessage("Minimum cash out limit is $100.So you can't withdraw.Your paytm account balance is "+paytm1 );

                alert.setCancelable(false);

                alert.setView(inflater.inflate(R.layout.dialog,null));
                alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();
            }
        });

        TextView bitcoinwithdra=findViewById(R.id.withdrawbitcoin);
        bitcoinwithdra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(personalize.this);
                LayoutInflater inflater = (personalize.this).getLayoutInflater();
                alert.setMessage("Minimum cash out limit is 0.00114btc.So you can't withdraw.Your bitcoin wallet balance is "+bitcoin1);

                alert.setCancelable(false);

                alert.setView(inflater.inflate(R.layout.dialog,null));
                alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();
            }
        });

        TextView freefirewithdra=findViewById(R.id.withdrawfreefire);
        freefirewithdra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(personalize.this);
                LayoutInflater inflater = (personalize.this).getLayoutInflater();
                alert.setMessage("Minimum diamond top up limit is 1000.So you can't withdraw.Diamond you have on your wallet is "+freefire1);

                alert.setCancelable(false);

                alert.setView(inflater.inflate(R.layout.dialog,null));
                alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();
            }
        });

        TextView googlewithdra=findViewById(R.id.withdrawgoogle);
        googlewithdra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(personalize.this);
                LayoutInflater inflater = (personalize.this).getLayoutInflater();
                alert.setMessage("Minimum cash out limit is $100.So you can't withdraw.Your Gift Card has "+google1);

                alert.setCancelable(false);

                alert.setView(inflater.inflate(R.layout.dialog,null));
                alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();
            }
        });


        SharedPreferences sharedPref = getSharedPreferences("amountofdiamond", MODE_PRIVATE);
        float paypal=sharedPref.getFloat("paypal",0);
        float amazon=sharedPref.getFloat("google",0);
        int freefire=sharedPref.getInt("freefire",0);
        float bitcoin=sharedPref.getFloat("bitcoin",0);
        float google=sharedPref.getFloat("google",0);
        float paytm=sharedPref.getFloat("paytm",0);

        String amazonamount=String.valueOf(amazon);
        String freefireamount=String.valueOf(freefire);
        String bitcoinamount=String.valueOf(bitcoin);
        String googleamount=String.valueOf(google);
        String paytmamount=String.valueOf(paytm);
        String paypalamount=String.valueOf(paypal);


        TextView paypalbal=findViewById(R.id.amountpaypal);
        paypalbal.setText(paypalamount+" USD");
        TextView paytmbal=findViewById(R.id.amountpaytm);
        paytmbal.setText(paytmamount+" USD");
        TextView googlebal=findViewById(R.id.amountgoogle);
        googlebal.setText(googleamount+" USD");
        TextView bitcoinbal=findViewById(R.id.amountbitcoin);
        bitcoinbal.setText(bitcoinamount+" USD");
        TextView freefirebal=findViewById(R.id.amountfreefire);
        freefirebal.setText(freefireamount+" diamond");
        TextView amazonbal=findViewById(R.id.amountamazon);
        amazonbal.setText(amazonamount+" USD");


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();
        Intent intent=getIntent();
        String bal=intent.getStringExtra("balance");

        see=findViewById(R.id.seeall);
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(personalize.this,See_All.class);
                startActivity(intent);
            }
        });

        SharedPreferences share=getSharedPreferences("amountofdiamond",MODE_PRIVATE);
        int youhave=share.getInt("diamond",0);
        String valueofdiamond = String.valueOf(youhave);
        diamond=findViewById(R.id.diamond);
        diamond.setText("Diamond:- "+valueofdiamond);



        howtoearn=findViewById(R.id.howto);
        howtoearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert2= new AlertDialog.Builder(personalize.this);
                LayoutInflater inflater2 = (personalize.this).getLayoutInflater();
                alert2.setMessage("For each thousand (1000 iCoin) you will get $0.012.You can get the payment though bitcoin,PayTM,PayPal,Bkash,Rocket,Gpay,Free Fire Top");
                alert2.setCancelable(false);
                alert2.setView(inflater2.inflate(R.layout.goldcoin,null));
                alert2.setPositiveButton("Skip", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        AlertDialog.Builder alert= new AlertDialog.Builder(personalize.this);
                        LayoutInflater inflater2 = (personalize.this).getLayoutInflater();
                        alert.setMessage("To earn iCoin you can play games");
                        alert.setCancelable(false);
                        alert.setView(inflater2.inflate(R.layout.games,null));
                        alert.setPositiveButton("Skip", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                AlertDialog.Builder aler= new AlertDialog.Builder(personalize.this);
                                LayoutInflater inflater2 = (personalize.this).getLayoutInflater();
                                aler.setMessage("Watch video on youtube");
                                aler.setCancelable(false);
                                aler.setView(inflater2.inflate(R.layout.youtube,null));
                                aler.setPositiveButton("Skip", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        AlertDialog.Builder alert1= new AlertDialog.Builder(personalize.this);
                                        LayoutInflater inflater2 = (personalize.this).getLayoutInflater();
                                        alert1.setMessage("Browse Internet on iPay Browser");
                                        alert1.setCancelable(false);
                                        alert1.setView(inflater2.inflate(R.layout.browser,null));
                                        alert1.setPositiveButton("Skip", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                AlertDialog.Builder alert2= new AlertDialog.Builder(personalize.this);
                                                LayoutInflater inflater2 = (personalize.this).getLayoutInflater();
                                                alert2.setMessage("Or participate on quiz context");
                                                alert2.setCancelable(false);
                                                alert2.setView(inflater2.inflate(R.layout.quiz,null));
                                                alert2.setPositiveButton("Skip", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        AlertDialog.Builder alert3= new AlertDialog.Builder(personalize.this);
                                                        LayoutInflater inflater2 = (personalize.this).getLayoutInflater();
                                                        alert3.setMessage("Tap the SEE ALL button to have a look on all the option");
                                                        alert3.setCancelable(false);
                                                        alert3.setView(inflater2.inflate(R.layout.treswithdia,null));
                                                        alert3.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                                AlertDialog.Builder alert3= new AlertDialog.Builder(personalize.this);
                                                                LayoutInflater inflater2 = (personalize.this).getLayoutInflater();
                                                                alert3.setMessage("Tap on Total Balance to update your iCoin");
                                                                alert3.setCancelable(false);
                                                                alert3.setView(inflater2.inflate(R.layout.tap,null));
                                                                alert3.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {

                                                                        AlertDialog.Builder alert3= new AlertDialog.Builder(personalize.this);
                                                                        LayoutInflater inflater2 = (personalize.this).getLayoutInflater();
                                                                        alert3.setMessage("You even can buy and sell diamond in market.This market works like share market where price for diamond keep changing continuously.");
                                                                        alert3.setCancelable(false);
                                                                        alert3.setView(inflater2.inflate(R.layout.diamondbox,null));
                                                                        alert3.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialogInterface, int i) {


                                                                                AlertDialog.Builder alert4= new AlertDialog.Builder(personalize.this);
                                                                                LayoutInflater inflater2 = (personalize.this).getLayoutInflater();
                                                                                alert4.setMessage("Use UK,US,Canadian vpn server to speed up your earning");
                                                                                alert4.setCancelable(false);
                                                                                alert4.setView(inflater2.inflate(R.layout.vpn,null));
                                                                                alert4.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                                                                    @Override
                                                                                    public void onClick(DialogInterface dialogInterface, int i) {


                                                                                    }
                                                                                });
                                                                                alert4.create().show();

                                                                            }
                                                                        });
                                                                        alert3.create().show();

                                                                    }
                                                                });
                                                                alert3.create().show();
                                                                
                                                            }
                                                        });
                                                        alert3.create().show();
                                                    }
                                                });
                                                alert2.create().show();
                                            }
                                        });
                                        alert1.create().show();
                                    }
                                });
                                aler.create().show();

                            }
                        });
                        alert.create().show();
                    }
                });
                alert2.create().show();


            }
        });

        earnmore=findViewById(R.id.earnmore);
        earnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert4= new AlertDialog.Builder(personalize.this);
                LayoutInflater inflater2 = (personalize.this).getLayoutInflater();
                alert4.setMessage("Spend more time on iPay and attend all the notification where you will get surprise treasure chest to claim and trade on diamond market");
                alert4.setCancelable(false);
                alert4.setView(inflater2.inflate(R.layout.diamondbox,null));
                alert4.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert4.create().show();


            }
        });

        notfication=findViewById(R.id.notifiction);
        notfication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert4= new AlertDialog.Builder(personalize.this);
                LayoutInflater inflater2 = (personalize.this).getLayoutInflater();
                alert4.setMessage("You don't have any notification so far");
                alert4.setCancelable(false);
                alert4.setView(inflater2.inflate(R.layout.notification1,null));
                alert4.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert4.create().show();


            }
        });



        SharedPreferences sharedPreferences1 = getSharedPreferences("info", MODE_PRIVATE);
        String name = sharedPreferences1.getString("name","");
        String username = sharedPreferences1.getString("username","");
        String email = sharedPreferences1.getString("email","");
        String phoneNo = sharedPreferences1.getString("phoneNo","");

        name1=findViewById(R.id.name);
        name1.setText("Name:- "+name);
        username1=findViewById(R.id.username);
        username1.setText("Username:- "+username);
        phoneNo1=findViewById(R.id.phoneNo);
        phoneNo1.setText("Phone:- "+phoneNo);
        email1=findViewById(R.id.email);
        email1.setText("Email:- "+email);
        value=findViewById(R.id.iCoin);
        value.setText("Balance:- "+bal);



    }
}