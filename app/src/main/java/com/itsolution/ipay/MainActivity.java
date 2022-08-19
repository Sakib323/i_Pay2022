package com.itsolution.ipay;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT=1000;
    private boolean save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Calendar cal = Calendar.getInstance();
        int currentmin=cal.get(Calendar.MINUTE);




        SharedPreferences daynumber=getSharedPreferences("countdown",MODE_PRIVATE);
        int number_of_day=daynumber.getInt("day",0);
        int today=cal.get(Calendar.DAY_OF_WEEK);
        SharedPreferences vpn=getSharedPreferences("vpncount",MODE_PRIVATE);

        if(today==number_of_day){
            SharedPreferences.Editor editor3 = vpn.edit();
            editor3.putBoolean("vpnon",true);
            editor3.apply();
        }



        if(currentmin==1){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==2){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==3){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==4){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==5){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==6){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==7){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==8){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==9){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==10){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==11){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==12){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==13){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==14){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==15){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==16){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==17){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==18){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==19){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==20){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==21){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==22){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==23){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==24){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==25){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==26){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==27){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==28){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==29){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==30){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }

        if(currentmin==31){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==32){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==33){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==34){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==35){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==36){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==37){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==38){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==39){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }
        if(currentmin==40){
            SharedPreferences dailycheckin = getSharedPreferences("voucher", MODE_PRIVATE);
            SharedPreferences.Editor editor = dailycheckin.edit();
            editor.putBoolean("1",true);
            editor.putBoolean("2",true);
            editor.putBoolean("3",true);
            editor.putBoolean("4",true);
            editor.putBoolean("5",true);
            editor.putBoolean("6",true);
            editor.apply();
        }

        if(currentmin==1){

            SharedPreferences hourlycheckin = getSharedPreferences("dailydiamond", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = hourlycheckin.edit();
            editor1.putBoolean("checkindiamond",true);
            editor1.apply();
        }
        if(currentmin==2){

            SharedPreferences hourlycheckin = getSharedPreferences("dailydiamond", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = hourlycheckin.edit();
            editor1.putBoolean("checkindiamond",true);
            editor1.apply();

        }
        if(currentmin==3){
            SharedPreferences hourlycheckin = getSharedPreferences("dailydiamond", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = hourlycheckin.edit();
            editor1.putBoolean("checkindiamond",true);
            editor1.apply();
        }
        if(currentmin==4){

            SharedPreferences hourlycheckin = getSharedPreferences("dailydiamond", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = hourlycheckin.edit();
            editor1.putBoolean("checkindiamond",true);
            editor1.apply();
        }
        if(currentmin==5){

            SharedPreferences hourlycheckin = getSharedPreferences("dailydiamond", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = hourlycheckin.edit();
            editor1.putBoolean("checkindiamond",true);
            editor1.apply();
        }






        if(currentmin==1){
            SharedPreferences hourlycheckin = getSharedPreferences("dailyiCoin", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = hourlycheckin.edit();
            editor1.putBoolean("checkinicoin",true);
            editor1.apply();
        }
        if(currentmin==2){
            SharedPreferences hourlycheckin = getSharedPreferences("dailyiCoin", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = hourlycheckin.edit();
            editor1.putBoolean("checkinicoin",true);
            editor1.apply();

        }
        if(currentmin==3){
            SharedPreferences hourlycheckin = getSharedPreferences("dailyiCoin", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = hourlycheckin.edit();
            editor1.putBoolean("checkinicoin",true);
            editor1.apply();
        }
        if(currentmin==4){
            SharedPreferences hourlycheckin = getSharedPreferences("dailyiCoin", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = hourlycheckin.edit();
            editor1.putBoolean("checkinicoin",true);
            editor1.apply();
        }
        if(currentmin==5){
            SharedPreferences hourlycheckin = getSharedPreferences("dailyiCoin", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = hourlycheckin.edit();
            editor1.putBoolean("checkinicoin",true);
            editor1.apply();
        }



        if(currentmin==1){
            SharedPreferences hourlycheckin = getSharedPreferences("daily", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = hourlycheckin.edit();
            editor1.putBoolean("checkin",true);
            editor1.apply();
        }
        if(currentmin==2){
            SharedPreferences hourlycheckin = getSharedPreferences("daily", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = hourlycheckin.edit();
            editor1.putBoolean("checkin",true);
            editor1.apply();

        }
        if(currentmin==3){
            SharedPreferences hourlycheckin = getSharedPreferences("daily", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = hourlycheckin.edit();
            editor1.putBoolean("checkin",true);
            editor1.apply();
        }
        if(currentmin==4){
            SharedPreferences hourlycheckin = getSharedPreferences("daily", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = hourlycheckin.edit();
            editor1.putBoolean("checkin",true);
            editor1.apply();
        }
        if(currentmin==5){
            SharedPreferences hourlycheckin = getSharedPreferences("daily", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = hourlycheckin.edit();
            editor1.putBoolean("checkin",true);
            editor1.apply();
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!NetworkState.connectionAvailable(MainActivity.this)) {
                    Intent intent=new Intent(MainActivity.this,nointernet.class);
                    startActivity(intent);
                }
                else {

                    Boolean vpncount = vpn.getBoolean("vpnon", false);
                    if (vpncount == true) {
                        Context context = MainActivity.this;
                        ConnectivityManager cManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Network activeNet = cManager.getActiveNetwork();
                            NetworkCapabilities netCaps = cManager.getNetworkCapabilities(activeNet);
                            boolean vpn = netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                            if (vpn == true) {
                                SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
                                save = sharedPreferences.getBoolean("savelogin", false);
                                if (save == true) {
                                    Intent i = new Intent(MainActivity.this, UserProfile.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Intent i = new Intent(MainActivity.this, login.class);
                                    startActivity(i);
                                    finish();
                                }

                            } else {
                                Intent intent = new Intent(MainActivity.this, vpnconnection.class);
                                startActivity(intent);
                            }
                        }
                    }
                    else{

                    SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
                    save = sharedPreferences.getBoolean("savelogin", false);
                    if (save == true) {
                        Intent i = new Intent(MainActivity.this, UserProfile.class);
                        startActivity(i);
                        finish();
                    } else {
                        Intent i = new Intent(MainActivity.this, login.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}