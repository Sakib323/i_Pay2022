package com.itsolution.ipay;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class UserProfile extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private AdView mAdView;
    private static final String TAG = "--->Native Ad";
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private boolean save, once;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private FusedLocationProviderClient fusedLocationClient;
    private GestureDetector gestureDetector;
    RelativeLayout update;
    Button cancel1;
    MeowBottomNavigation bottomNav;
    LinearLayout lay;

    TextView fullName, email, balance_on_profile, See_All, notice1,show;
    RelativeLayout spin, slot, rewarded, DICE, PopMeforLotto, candy_crush, quizquestion, guessdigit, ipay_browser, VOUCHER,youtube,game,Music,Games;
    ImageView profile;
    Boolean vpnactive;
    CardView cash, withdrawal, about;

    Button ref;

    @SuppressLint({"WrongViewCast", "ResourceType"})
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);
        final MediaPlayer mp = MediaPlayer.create(UserProfile.this, R.raw.click);


        balance_on_profile=findViewById(R.id.id_bal);

        gestureDetector = new GestureDetector( this, new SwipeDetector());

        cancel1=findViewById(R.id.cancel);
        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update=findViewById(R.id.notice);
                update.setVisibility(View.GONE);
            }
        });


        update=findViewById(R.id.notice);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notice1=findViewById(R.id.noticetext);
                DatabaseReference noticeboard= FirebaseDatabase.getInstance("https://i-pay-86691-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("notice");
                noticeboard.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String notice=snapshot.getValue(String.class);
                        String notices=String.format(String.valueOf(notice));
                        notice1.setText(notices);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        notice1=findViewById(R.id.noticetext);
        DatabaseReference noticeboard= FirebaseDatabase.getInstance("https://i-pay-86691-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("notice");
        noticeboard.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String notice=snapshot.getValue(String.class);
                String notices=String.format(String.valueOf(notice));
                notice1.setText(notices);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        SharedPreferences refresh = getSharedPreferences("ref", MODE_PRIVATE);
        boolean refreshdata=refresh.getBoolean("refresh",false);

        if(refreshdata==true){


            SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
            int iCoin_balance=coin.getInt("number",0);

            String user_balance=String.format(String.valueOf(iCoin_balance));
            balance_on_profile.setText(user_balance);
            SharedPreferences.Editor edit = refresh.edit();
            edit.putBoolean("refresh",false);
            edit.apply();

        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                int iCoin_balance=coin.getInt("number",0);
                        String user_balance=String.format(String.valueOf(iCoin_balance));
                        balance_on_profile.setText(user_balance);

            }
        }, 0);


        SharedPreferences sharedPreferences1 = getSharedPreferences("info", MODE_PRIVATE);
        String name = sharedPreferences1.getString("name","");
        String username = sharedPreferences1.getString("username","");
        String email1 = sharedPreferences1.getString("email","");
        String phoneNo = sharedPreferences1.getString("phoneNo","");

        Intent intent= getIntent();
        String value=intent.getStringExtra("username");
        SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("value", value);
        editor.apply();



        VOUCHER=(RelativeLayout) findViewById(R.id.voucher);
        VOUCHER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_username=username;
                Intent intent1 = new Intent(UserProfile.this,voucher.class);
                intent1.putExtra("username1",user_username);
                startActivity(intent1);
                mp.start();

            }
        });
        profile=findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(UserProfile.this,personalize.class);
                String balance=balance_on_profile.getText().toString();
                intent1.putExtra("balance",balance);
                startActivity(intent1);
                mp.start();
            }
        });







        quizquestion=(RelativeLayout) findViewById(R.id.quiz);
        quizquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=UserProfile.this;
                ConnectivityManager cManager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    Network activeNet=cManager.getActiveNetwork();
                    NetworkCapabilities netCaps=cManager.getNetworkCapabilities(activeNet);
                    boolean vpn=netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                    if(vpn==true){
                        String user_username=username;
                        Intent intent2 = new Intent(UserProfile.this,Quiz.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",7);
                        startActivity(intent2);
                        mp.start();
                    }
                    else{
                        String user_username=username;
                        Intent intent2 = new Intent(UserProfile.this,Quiz.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",5);
                        startActivity(intent2);
                        mp.start();
                    }
                }
            }
        });


        youtube=findViewById(R.id.youtube);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_username=username;
                Intent intent = new Intent(UserProfile.this,Youtube.class);
                intent.putExtra("username1",user_username);
                startActivity(intent);
                mp.start();
            }
        });

        PopMeforLotto=(RelativeLayout) findViewById(R.id.popUp);
        PopMeforLotto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=UserProfile.this;
                ConnectivityManager cManager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    Network activeNet=cManager.getActiveNetwork();
                    NetworkCapabilities netCaps=cManager.getNetworkCapabilities(activeNet);
                    boolean vpn=netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                    if(vpn==true){

                        String user_username=username;
                        Intent intent2 = new Intent(UserProfile.this,slotmachine.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",55);
                        startActivity(intent2);
                        mp.start();
                    }else{
                        String user_username=username;
                        Intent intent2 = new Intent(UserProfile.this,slotmachine.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",30);
                        startActivity(intent2);
                        mp.start();
                    }
                }
            }
        });


        DICE=(RelativeLayout) findViewById(R.id.DICE);
        DICE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Context context=UserProfile.this;
                ConnectivityManager cManager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    Network activeNet=cManager.getActiveNetwork();
                    NetworkCapabilities netCaps=cManager.getNetworkCapabilities(activeNet);
                    boolean vpn=netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                    if(vpn==true){

                        String user_username=username;
                        Intent intent2 = new Intent(UserProfile.this,Dice.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",75);
                        intent2.putExtra("balance", String.valueOf(balance_on_profile));
                        startActivity(intent2);
                        mp.start();
                    }else{
                        String user_username=username;
                        Intent intent2 = new Intent(UserProfile.this,Dice.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",40);
                        intent2.putExtra("balance", String.valueOf(balance_on_profile));
                        startActivity(intent2);
                        mp.start();
                    }
                }

            }
        });


        cash=findViewById(R.id.cashout);
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_username=username;
                Intent intent=new Intent(UserProfile.this,Withdrawal.class);
                int value1=Integer.parseInt(balance_on_profile.getText().toString());
                intent.putExtra("balance",value1);
                intent.putExtra("username1",user_username);
                startActivity(intent);
                mp.start();
            }
        });
        withdrawal=findViewById(R.id.history1);
        withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(UserProfile.this);
                LayoutInflater inflater = (UserProfile.this).getLayoutInflater();
                alert.setTitle("History");
                alert.setMessage("You don't have any cashout history so far");
                alert.setCancelable(true);
                alert.setView(inflater.inflate(R.layout.historylay,null));
                alert.create().show();
                mp.start();
            }
        });

        about=findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(UserProfile.this,aboutUs.class);
                startActivity(intent1);
                mp.start();
            }
        });

        See_All=findViewById(R.id.see_all);
        See_All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_username=username;
                Intent intent = new Intent(UserProfile.this,See_All.class);
                intent.putExtra("username1",user_username);
                startActivity(intent);
                mp.start();
            }
        });

        spin=(RelativeLayout) findViewById(R.id.fortunewheel);
        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_username=username;
                Intent intent1 = new Intent(UserProfile.this,spinvoucher.class);
                intent1.putExtra("username1",user_username);
                startActivity(intent1);
                mp.start();

            }
        });


        candy_crush=(RelativeLayout) findViewById(R.id.r3);
        candy_crush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=UserProfile.this;
                ConnectivityManager cManager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    Network activeNet=cManager.getActiveNetwork();
                    NetworkCapabilities netCaps=cManager.getNetworkCapabilities(activeNet);
                    boolean vpn=netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                    if(vpn==true){

                        String user_username=username;
                        Intent intent2 = new Intent(UserProfile.this,candy_crush.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",9);
                        startActivity(intent2);
                        mp.start();
                    }else{
                        String user_username=username;
                        Intent intent2 = new Intent(UserProfile.this,candy_crush.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",5);
                        startActivity(intent2);
                        mp.start();
                    }
                }
            }
        });
        rewarded=(RelativeLayout) findViewById(R.id.r4);
        rewarded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=UserProfile.this;
                ConnectivityManager cManager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    Network activeNet=cManager.getActiveNetwork();
                    NetworkCapabilities netCaps=cManager.getNetworkCapabilities(activeNet);
                    boolean vpn=netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                    if(vpn==true){

                        String user_username=username;
                        Intent intent2 = new Intent(UserProfile.this,Market.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",35);
                        startActivity(intent2);
                        mp.start();
                    }else{
                        String user_username=username;
                        Intent intent2 = new Intent(UserProfile.this,Market.class);
                        intent2.putExtra("username1",user_username);
                        intent2.putExtra("coin",20);
                        startActivity(intent2);
                        mp.start();
                    }
                }
            }
        });
        slot=(RelativeLayout) findViewById(R.id.r2);
        slot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_dialog();
            }
        });

        game=(RelativeLayout) findViewById(R.id.r1);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_music_dialog();
            }
        });


        ref=(Button) findViewById(R.id.refresh);
        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();

                SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                int iCoin_balance=coin.getInt("number",0);
                        String user_balance=String.format(String.valueOf(iCoin_balance));
                        balance_on_profile.setText(user_balance);


            }
        });
        fullName = findViewById(R.id.full_name);
        email = findViewById(R.id.profile_email);
        balance_on_profile=findViewById(R.id.id_bal);
        showAlluserData();
        SharedPreferences preview = getSharedPreferences("game2", MODE_PRIVATE);
        once=preview.getBoolean("preview",false);
        if(once==true){
            AlertDialog.Builder alert2= new AlertDialog.Builder(UserProfile.this);
            LayoutInflater inflater2 = (UserProfile.this).getLayoutInflater();
            alert2.setMessage("For each thousand (1000 iCoin) you will get $0.012.You can get the payment though bitcoin,PayTM,PayPal,Bkash,Rocket,Gpay,Free Fire Top");
            alert2.setCancelable(false);
            alert2.setView(inflater2.inflate(R.layout.goldcoin,null));
            alert2.setPositiveButton("Skip", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    AlertDialog.Builder alert= new AlertDialog.Builder(UserProfile.this);
                    LayoutInflater inflater2 = (UserProfile.this).getLayoutInflater();
                    alert.setMessage("To earn iCoin you can play games");
                    alert.setCancelable(false);
                    alert.setView(inflater2.inflate(R.layout.games,null));
                    alert.setPositiveButton("Skip", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            AlertDialog.Builder aler= new AlertDialog.Builder(UserProfile.this);
                            LayoutInflater inflater2 = (UserProfile.this).getLayoutInflater();
                            aler.setMessage("Watch video on youtube");
                            aler.setCancelable(false);
                            aler.setView(inflater2.inflate(R.layout.youtube,null));
                            aler.setPositiveButton("Skip", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    AlertDialog.Builder alert1= new AlertDialog.Builder(UserProfile.this);
                                    LayoutInflater inflater2 = (UserProfile.this).getLayoutInflater();
                                    alert1.setMessage("Browse Internet on iPay Browser");
                                    alert1.setCancelable(false);
                                    alert1.setView(inflater2.inflate(R.layout.browser,null));
                                    alert1.setPositiveButton("Skip", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            AlertDialog.Builder alert2= new AlertDialog.Builder(UserProfile.this);
                                            LayoutInflater inflater2 = (UserProfile.this).getLayoutInflater();
                                            alert2.setMessage("Or participate on quiz context");
                                            alert2.setCancelable(false);
                                            alert2.setView(inflater2.inflate(R.layout.quiz,null));
                                            alert2.setPositiveButton("Skip", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    AlertDialog.Builder alert3= new AlertDialog.Builder(UserProfile.this);
                                                    LayoutInflater inflater2 = (UserProfile.this).getLayoutInflater();
                                                    alert3.setMessage("Tap the see all button to have a look on all the option or swipe right");
                                                    alert3.setCancelable(false);
                                                    alert3.setView(inflater2.inflate(R.layout.treswithdia,null));
                                                    alert3.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            AlertDialog.Builder alert3= new AlertDialog.Builder(UserProfile.this);
                                                            LayoutInflater inflater2 = (UserProfile.this).getLayoutInflater();
                                                            alert3.setMessage("Tap on Total Balance to update your iCoin");
                                                            alert3.setCancelable(false);
                                                            alert3.setView(inflater2.inflate(R.layout.tap,null));
                                                            alert3.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                                    AlertDialog.Builder alert3= new AlertDialog.Builder(UserProfile.this);
                                                                    LayoutInflater inflater2 = (UserProfile.this).getLayoutInflater();
                                                                    alert3.setMessage("You even can buy and sell diamond in market.This market works like share market where price for diamond keep changing continuously.");
                                                                    alert3.setCancelable(false);
                                                                    alert3.setView(inflater2.inflate(R.layout.diamondbox,null));
                                                                    alert3.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialogInterface, int i) {



                                                                            AlertDialog.Builder alert4= new AlertDialog.Builder(UserProfile.this);
                                                                            LayoutInflater inflater2 = (UserProfile.this).getLayoutInflater();
                                                                            alert4.setMessage("Use vpn from Australia, Canada, New Zealand, United Kingdom, United States server to speed up your earning.You have 1 days of trial period.");
                                                                            alert4.setCancelable(false);
                                                                            alert4.setView(inflater2.inflate(R.layout.vpn,null));
                                                                            alert4.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                                                                @Override
                                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                                    SharedPreferences preview = getSharedPreferences("game2", MODE_PRIVATE);
                                                                                    SharedPreferences.Editor edit=preview.edit();
                                                                                    edit.putBoolean("preview",false);
                                                                                    edit.apply();

                                                                                    AlertDialog.Builder alert5=new AlertDialog.Builder(UserProfile.this);
                                                                                    LayoutInflater inflater=(UserProfile.this).getLayoutInflater();
                                                                                    alert5.setMessage("Choice Your favourite one from music and games & start earning.");
                                                                                    alert5.setCancelable(false);
                                                                                    alert5.setView(inflater.inflate(R.layout.games,null));
                                                                                    alert5.setPositiveButton("Games", new DialogInterface.OnClickListener() {
                                                                                        @Override
                                                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                                                            Intent intent=new Intent(UserProfile.this,web_games.class);
                                                                                            intent.putExtra("url","https://poki.com/");
                                                                                            intent.putExtra("username1",username);
                                                                                            startActivity(intent);
                                                                                        }
                                                                                    });
                                                                                    alert5.setNegativeButton("Music", new DialogInterface.OnClickListener() {
                                                                                        @Override
                                                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                                                            Intent intent=new Intent(UserProfile.this,web_games.class);
                                                                                            intent.putExtra("url","https://wynk.in/music");
                                                                                            intent.putExtra("username1",username);
                                                                                            startActivity(intent);
                                                                                        }
                                                                                    });
                                                                                    alert5.show();

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
            once=false;
        }



        Context context=UserProfile.this;
        ConnectivityManager cManager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            Network activeNet=cManager.getActiveNetwork();
            NetworkCapabilities netCaps=cManager.getNetworkCapabilities(activeNet);
            boolean vpn=netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
            if(vpn==true){
                SharedPreferences vpnon = getSharedPreferences("active", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = vpnon.edit();
                editor1.putBoolean("vpnactive",true);
                editor1.apply();

                AlertDialog.Builder alert4= new AlertDialog.Builder(UserProfile.this);
                LayoutInflater inflater2 = (UserProfile.this).getLayoutInflater();
                alert4.setMessage("You are using vpn connection & your earning will speed up now");
                alert4.setCancelable(false);
                alert4.setView(inflater2.inflate(R.layout.vpn,null));
                alert4.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });
                alert4.create().show();
            }
            else{

                SharedPreferences vpnon = getSharedPreferences("active", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = vpnon.edit();
                editor1.putBoolean("vpnactive",false);
                editor1.apply();

            }
        }





    }

    private void voucher_status() {
        Dialog dialog=new Dialog(UserProfile.this);
        dialog.setContentView(R.layout.voucher_status);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bgforvoucher);
        dialog.setCancelable(true);
        dialog.show();
        TextView paypalbal=dialog.findViewById(R.id.cash1);
        TextView amazonbal=dialog.findViewById(R.id.cash2);
        TextView paytmbal=dialog.findViewById(R.id.cash3);
        TextView bitcoinbal=dialog.findViewById(R.id.cash4);
        TextView freefirebal=dialog.findViewById(R.id.cash5);
        TextView googlebal=dialog.findViewById(R.id.cash5);


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


        paypalbal.setText(paypalamount+" USD");
        paytmbal.setText(paytmamount+" USD");
        googlebal.setText(googleamount+" USD");
        bitcoinbal.setText(bitcoinamount+" USD");
        freefirebal.setText(freefireamount+" diamond");
        amazonbal.setText(amazonamount+" USD");

        TextView cash0,cash1,cash2,cash3,cash4,cash5;

        cash0=dialog.findViewById(R.id.btn1);
        cash1=dialog.findViewById(R.id.btn2);
        cash2=dialog.findViewById(R.id.btn3);
        cash3=dialog.findViewById(R.id.btn4);
        cash4=dialog.findViewById(R.id.btn5);
        cash5=dialog.findViewById(R.id.btn6);
        cash0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(UserProfile.this);
                LayoutInflater inflater = (UserProfile.this).getLayoutInflater();
                alert.setMessage("Minimum cash out limit is $100.So you can't withdraw.Your paypal account balance is "+paypal);
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
        cash1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(UserProfile.this);
                LayoutInflater inflater = (UserProfile.this).getLayoutInflater();
                alert.setMessage("Minimum cash out limit is $100.So you can't withdraw.Your amazon account balance is "+amazon);
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
        cash2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(UserProfile.this);
                LayoutInflater inflater = (UserProfile.this).getLayoutInflater();
                alert.setMessage("Minimum cash out limit is $100.So you can't withdraw.Your paytm account balance is "+paytm);

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
        cash3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(UserProfile.this);
                LayoutInflater inflater = (UserProfile.this).getLayoutInflater();
                alert.setMessage("Minimum cash out limit is 0.00114btc.So you can't withdraw.Your bitcoin wallet balance is "+bitcoin);

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
        cash4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(UserProfile.this);
                LayoutInflater inflater = (UserProfile.this).getLayoutInflater();
                alert.setMessage("Minimum diamond top up limit is 1000.So you can't withdraw.Diamond you have on your wallet is "+freefire);

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
        cash5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert= new AlertDialog.Builder(UserProfile.this);
                LayoutInflater inflater = (UserProfile.this).getLayoutInflater();
                alert.setMessage("Minimum cash out limit is $100.So you can't withdraw.Your Gift Card has "+google);

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





    }

    private void How_to_earn_dialog() {
        Dialog dialog=new Dialog(UserProfile.this);
        dialog.setContentView(R.layout.how_to_earn_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.newbg6);
        dialog.setCancelable(true);
        dialog.show();
        TextView music,game;


        game=dialog.findViewById(R.id.btn2);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_dialog();
            }
        });


        music=dialog.findViewById(R.id.btn3);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_music_dialog();
            }
        });

    }


    protected void onSwipeRight() {

        SharedPreferences sharedPreferences1 = getSharedPreferences("info", MODE_PRIVATE);
        String username = sharedPreferences1.getString("username","");
        String user_username=username;
        Intent intent = new Intent(UserProfile.this,See_All.class);
        intent.putExtra("username1",user_username);
        startActivity(intent);
    }

    protected void onSwipeLeft() {
        SharedPreferences sharedPreferences1 = getSharedPreferences("info", MODE_PRIVATE);
        String username = sharedPreferences1.getString("username","");
        String user_username=username;
        Intent intent = new Intent(UserProfile.this,See_All.class);
        intent.putExtra("username1",user_username);
        startActivity(intent);
    }

    public class SwipeDetector extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {

            // Check movement along the Y-axis. If it exceeds SWIPE_MAX_OFF_PATH,
            // then dismiss the swipe.
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
            {
                return false;
            }

            //toast( "start = "+String.valueOf( e1.getX() )+" | end = "+String.valueOf( e2.getX() )  );
            //from left to right
            if( e2.getX() > e1.getX() )
            {
                if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
                {
                    onSwipeRight();
                    return true;
                }
            }

            if( e1.getX() > e2.getX() )
            {
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
                {
                    onSwipeLeft();
                    return true;
                }
            }

            return false;
        }
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        // TouchEvent dispatcher.
        if (gestureDetector != null)
        {
            if (gestureDetector.onTouchEvent(ev))
                // If the gestureDetector handles the event, a swipe has been
                // executed and no more needs to be done.
                return true;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return gestureDetector.onTouchEvent(event);
    }

    private void showAlluserData() {
        SharedPreferences sharedPreferences1 = getSharedPreferences("info", MODE_PRIVATE);
        String name = sharedPreferences1.getString("name","");
        String username = sharedPreferences1.getString("username","");
        String email1 = sharedPreferences1.getString("email","");
        Intent intent= getIntent();
        String user_username=username;
        String user_name=name;
        String user_email=email1;
        int user_balance = intent.getIntExtra("balance", 0);
        fullName.setText(user_name);
        email.setText(user_email);
        balance_on_profile.setText(String.format("%d",user_balance));
    }
    private void show_dialog(){
        Dialog dialog=new Dialog(UserProfile.this);
        dialog.setContentView(R.layout.dialog_view);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bgfordialog);
        dialog.setCancelable(true);
        dialog.show();


        SharedPreferences sharedPreferences1 = getSharedPreferences("info", MODE_PRIVATE);
        String username = sharedPreferences1.getString("username","");

        TextView game1=dialog.findViewById(R.id.btn1);
        game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://www.agame.com/games/mobile__tablet_games");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });


        TextView game2=dialog.findViewById(R.id.btn2);
        game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://www.atmegame.com/online-mobile-games");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });

        TextView game3=dialog.findViewById(R.id.btn3);
        game3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://www.gamesgames.com/games/smartphone-games");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });


        TextView game4=dialog.findViewById(R.id.btn4);
        game4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://poki.com/");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });

        TextView game5=dialog.findViewById(R.id.btn5);
        game5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://now.gg/games.html");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });


        TextView game6=dialog.findViewById(R.id.btn6);
        game6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://www.king.com/games");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });


        TextView game7=dialog.findViewById(R.id.btn7);
        game7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://www.gamepix.com/t/mobile-games");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });

        TextView game8=dialog.findViewById(R.id.btn8);
        game8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://www.crazygames.com/");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });


        TextView game9=dialog.findViewById(R.id.btn9);
        game9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://www.yiv.com/");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });


        TextView game10=dialog.findViewById(R.id.btn10);
        game10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://www.bgames.com/");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });



        TextView game11=dialog.findViewById(R.id.btn11);
        game11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://kizi.com/");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });


        TextView game12=dialog.findViewById(R.id.btn12);
        game12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://yaksgames.com/");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });



    }
    private void show_music_dialog(){
        Dialog dialog=new Dialog(UserProfile.this);
        dialog.setContentView(R.layout.music_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bgfordialog2);
        dialog.setCancelable(true);
        dialog.show();


        SharedPreferences sharedPreferences1 = getSharedPreferences("info", MODE_PRIVATE);
        String username = sharedPreferences1.getString("username","");

        TextView game1=dialog.findViewById(R.id.btnm1);
        game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://freeplaymusic.com/");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });


        TextView game2=dialog.findViewById(R.id.btnm2);
        game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://www.jiosaavn.com/");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });

        TextView game3=dialog.findViewById(R.id.btnm3);
        game3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://soundcloud.com/");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });


        TextView game4=dialog.findViewById(R.id.btnm4);
        game4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://wynk.in/music");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });

        TextView game5=dialog.findViewById(R.id.btnm5);
        game5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://www.jango.com/");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });


        TextView game6=dialog.findViewById(R.id.btnm6);
        game6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://gaana.com/");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });


        TextView game7=dialog.findViewById(R.id.btnm7);
        game7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://www.di.fm");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });

        TextView game8=dialog.findViewById(R.id.btnm8);
        game8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://www.boomplay.com/");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });


        TextView game9=dialog.findViewById(R.id.btnm9);
        game9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfile.this,web_games.class);
                intent.putExtra("url","https://streamsquid.com/");
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });




    }
}