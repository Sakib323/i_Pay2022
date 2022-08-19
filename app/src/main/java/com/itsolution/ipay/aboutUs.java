package com.itsolution.ipay;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
//import android.provider.CalendarContract;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;



import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class aboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();


        Element adsElement = new Element();

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription("Play to earn cash with i Pay")
                .addItem(new Element().setTitle("Version 1.35"))
                .addGroup("CONNECT WITH US!")
                .addEmail("itsoluation24@gmail.com")
                .addTwitter("SakibAh66427508")
                .addFacebook("ITSolution-105399155401043")
                .addWebsite("https://web.facebook.com/ITSolution-105399155401043")
                .addYoutube("https://www.youtube.com")
                .addPlayStore("com.itsolution.ipay")
                .addInstagram("itsolution360")
                .addItem(createCopyright())
                .create();
        setContentView(aboutPage);
    }
    private Element createCopyright()
    {
        Element copyright = new Element();
        @SuppressLint("DefaultLocale") final String copyrightString = String.format("Copyright %d by itsolution", Calendar.getInstance().get(Calendar.YEAR));
        copyright.setTitle(copyrightString);
        // copyright.setIcon(R.mipmap.ic_launcher);
        copyright.setGravity(Gravity.CENTER);
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(aboutUs.this,copyrightString,Toast.LENGTH_SHORT).show();
            }
        });
        return copyright;
    }
}