package com.itsolution.ipay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class candy_crush extends AppCompatActivity {

    String GameID="4619317";
    String AppID="Rewarded_Android";
    private Boolean Testmode=false;

    int[] candies={
            R.drawable.bluecandy,
            R.drawable.greencandy,
            R.drawable.orangecandy,
            R.drawable.purplecandy,
            R.drawable.redcandy,
            R.drawable.yellowcandy
    };
    private RewardedAd mRewardedAd;
    int widthOfBlock,noOfBlocks=8,widthOfScreen;
    ArrayList<ImageView> candy=new ArrayList<>();
    int candyToBeDragged,candyToBeReplaced;
    int notCandy=R.drawable.tranparent;
    Handler mHandler;
    int interval=100;
    TextView scoreResult;
    int score=0;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candy_crush);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final MediaPlayer mp = MediaPlayer.create(candy_crush.this, R.raw.press);




        UnityAds.initialize(candy_crush.this,GameID, Testmode, new IUnityAdsInitializationListener() {
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
        new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {


            }

            public void onFinish() {
                unity();
            }

        }.start();



        scoreResult=findViewById(R.id.score);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthOfScreen= displayMetrics.widthPixels;
        int heightOfScreen=displayMetrics.heightPixels;
        widthOfBlock=widthOfScreen/noOfBlocks;
        createBoard();
        for (ImageView imageView: candy){
            imageView.setOnTouchListener(new OnSwipeListener(this){
                @Override
                void onSwipeLeft() {
                    super.onSwipeLeft();
                    candyToBeDragged=imageView.getId();
                    candyToBeReplaced=candyToBeDragged-1;
                    candyInterchange();
                    mp.start();
                    unity();
                }

                @Override
                void onSwipeRight() {
                    super.onSwipeRight();
                    candyToBeDragged=imageView.getId();
                    candyToBeReplaced=candyToBeDragged+1;
                    candyInterchange();
                    mp.start();
                    unity();
                }

                @Override
                void onSwipeTop() {
                    super.onSwipeTop();
                    candyToBeDragged=imageView.getId();
                    candyToBeReplaced=candyToBeDragged-noOfBlocks;
                    candyInterchange();
                    mp.start();
                    unity();
                }

                @Override
                void onSwipeBottom() {
                    super.onSwipeBottom();
                    candyToBeDragged=imageView.getId();
                    candyToBeReplaced=candyToBeDragged+noOfBlocks;
                    candyInterchange();
                    mp.start();
                    unity();
                }
            });
        }
        mHandler=new Handler();
        startRepeat();

    }



    private void checkRowForThree(){
        for(int i=0;i<62;i++){
            int chosedCandy=(int)candy.get(i).getTag();
            boolean isBlank=(int) candy.get(i).getTag()==notCandy;
            Integer[] notValid={6,7,14,15,22,23,30,31,38,39,46,47,54,55};
            List<Integer>list= Arrays.asList(notValid);
            if(!list.contains(i)){
                int x=i;
                if((int)candy.get(x++).getTag()==chosedCandy&& !isBlank && (int)candy.get(x++).getTag()==chosedCandy&& (int)candy.get(x).getTag()==chosedCandy){
                    candy.get(x).setImageResource(notCandy);
                    candy.get(x).setTag(notCandy);
                    score=score+3;
                    scoreResult.setText(String.valueOf(score));
                    x--;
                    candy.get(x).setImageResource(notCandy);
                    candy.get(x).setTag(notCandy);
                    x--;
                    candy.get(x).setImageResource(notCandy);
                    candy.get(x).setTag(notCandy);

                    final MediaPlayer mp1 = MediaPlayer.create(candy_crush.this, R.raw.matched);
                    mp1.start();

                    Intent intent =getIntent();
                    int amount=intent.getIntExtra("coin",5);



                    Boolean vpnison;
                    SharedPreferences share=getSharedPreferences("active",MODE_PRIVATE);
                    vpnison =share.getBoolean("vpnactive",false);
                    if (vpnison==true){


                        SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                        int iCoin_balance=coin.getInt("number",0);
                        int updateCoins=iCoin_balance+5;
                        SharedPreferences.Editor EDITOR=coin.edit();
                        EDITOR.putInt("number",updateCoins);
                        EDITOR.apply();

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

                    }

                    unity();






                }
            }
        }
        moveDownCandies();
    }




    private void checkColumnForThree(){
        for(int i=0;i<47;i++){
            int chosedCandy=(int)candy.get(i).getTag();
            boolean isBlank=(int) candy.get(i).getTag()==notCandy;

            int x=i;
            if((int)candy.get(x).getTag()==chosedCandy&& !isBlank &&
                    (int)candy.get(x+noOfBlocks).getTag()==chosedCandy&&
                    (int)candy.get(x+2*noOfBlocks).getTag()==chosedCandy){
                score=score+3;
                scoreResult.setText(String.valueOf(score));
                candy.get(x).setImageResource(notCandy);
                candy.get(x).setTag(notCandy);
                x=x+noOfBlocks;
                candy.get(x).setImageResource(notCandy);
                candy.get(x).setTag(notCandy);
                x=x+noOfBlocks;
                candy.get(x).setImageResource(notCandy);
                candy.get(x).setTag(notCandy);
                final MediaPlayer mp1 = MediaPlayer.create(candy_crush.this, R.raw.matched);
                mp1.start();

                Boolean vpnison;
                SharedPreferences share=getSharedPreferences("active",MODE_PRIVATE);
                vpnison =share.getBoolean("vpnactive",false);
                if (vpnison==true){


                    SharedPreferences coin = getSharedPreferences("icoinbal", MODE_PRIVATE);
                    int iCoin_balance=coin.getInt("number",0);
                    int updateCoins=iCoin_balance+5;
                    SharedPreferences.Editor EDITOR=coin.edit();
                    EDITOR.putInt("number",updateCoins);
                    EDITOR.apply();

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

                }

            }
        }
        moveDownCandies();

    }
    private void moveDownCandies(){
        Integer[] firstRow ={0,1,2,3,4,5,6,7};
        List<Integer> list = Arrays.asList(firstRow);
        for(int i =55;i>=0;i--){
            if((int) candy.get(i+noOfBlocks).getTag()==notCandy){
                candy.get(i+noOfBlocks).setImageResource((int)candy.get(i).getTag());
                candy.get(i+noOfBlocks).setTag(candy.get(i).getTag());
                candy.get(i).setImageResource(notCandy);
                candy.get(i).setTag(notCandy);
                if(list.contains(i)&& (int) candy.get(i).getTag()==notCandy){
                    int randomColour=(int)Math.floor(Math.random()*candies.length);
                    candy.get(i).setImageResource(candies[randomColour]);
                    candy.get(i).setTag(candies[randomColour]);
                }

            }
        }
        for(int i=0;i<0;i++){
            if((int)candy.get(i).getTag()==notCandy){
                int randomColour=(int)Math.floor(Math.random()*candies.length);
                candy.get(i).setImageResource(candies[randomColour]);
                candy.get(i).setTag(candies[randomColour]);

            }
        }
    }

    Runnable repeatChecker=new Runnable() {
        @Override
        public void run() {
            try {
                checkRowForThree();
                checkColumnForThree();
                moveDownCandies();
            }finally {
                mHandler.postDelayed(repeatChecker,interval);

            }
        }
    };
    void startRepeat(){
        repeatChecker.run();
    }
    private void candyInterchange(){
        int background=(int)candy.get(candyToBeReplaced).getTag();
        int background1=(int)candy.get(candyToBeDragged).getTag();
        candy.get(candyToBeDragged).setImageResource(background);
        candy.get(candyToBeReplaced).setImageResource(background1);
        candy.get(candyToBeDragged).setTag(background);
        candy.get(candyToBeReplaced).setTag(background1);
    }

    private void createBoard() {
        GridLayout gridLayout=findViewById(R.id.board);
        gridLayout.setRowCount(noOfBlocks);
        gridLayout.setColumnCount(noOfBlocks);
        gridLayout.getLayoutParams().width=widthOfScreen;
        gridLayout.getLayoutParams().height=widthOfScreen;

        for(int i=0;i< noOfBlocks*noOfBlocks;i++){
            ImageView imageView=new ImageView(this);
            imageView.setId(i);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(widthOfBlock,widthOfBlock));
            imageView.setMaxHeight(widthOfBlock);
            imageView.setMaxWidth(widthOfBlock);
            int randomCandy=(int) Math.floor(Math.random()*candies.length);
            imageView.setImageResource(randomCandy);
            imageView.setImageResource(candies[randomCandy]);
            imageView.setTag(candies[randomCandy]);
            candy.add(imageView);
            gridLayout.addView(imageView);
        }
    }

    private void unity(){
        IUnityAdsShowListener iUnityAdsShowListener=new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                UnityAds.load(AppID);
                UnityAds.show(candy_crush.this,AppID);
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
        UnityAds.show(candy_crush.this,AppID);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(candy_crush.this,UserProfile.class);
        startActivity(intent);
    }
}
