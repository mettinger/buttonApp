package com.example.marke.buttontest;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ArrayList timeArray = new ArrayList();
    String myURL = "http://www.twobuttons.org:8890/?data="; //personal aws machine

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    // HANDLE BUTTON 0 PRESS
    public void doButton0(View view){

        final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date()) + "-0";
        EditText timeStampBox = findViewById(R.id.editText);
        timeStampBox.setText(timeStamp);

        EditText infoBox = findViewById(R.id.editText2);
        final String info = String.valueOf(infoBox.getText());

        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    String dataString = timeStamp + "-" + info;
                    URL url = new URL(myURL + dataString);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int status = con.getResponseCode();
                    con.disconnect();
                    timeArray.clear();
                }
                catch (Exception ex) {
                    int placeholder = 1;
                }
            }
        }).start();
    }

    // HANDLE BUTTON 1 PRESS
    public void doButton1(View view){

        final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date()) + "-1";
        EditText timeStampBox = findViewById(R.id.editText);
        timeStampBox.setText(timeStamp);

        EditText infoBox = findViewById(R.id.editText2);
        final String info = String.valueOf(infoBox.getText());

        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    String dataString = timeStamp + "-" + info;
                    URL url = new URL(myURL + dataString);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int status = con.getResponseCode();
                    con.disconnect();
                    timeArray.clear();
                }
                catch (Exception ex) {
                    int placeholder = 1;
                }
            }
        }).start();
    }

}
