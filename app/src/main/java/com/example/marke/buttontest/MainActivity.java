package com.example.marke.buttontest;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ArrayList timeArray = new ArrayList();
    String myURL = "http://52.87.117.189:8890/?data=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void doButton0(View view){

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date()) + "-0";
        EditText editText = findViewById(R.id.editText);
        timeArray.add(timeStamp);
        editText.setText(timeStamp);
    }

    public void doButton1(View view){

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date()) + "-1";
        EditText editText = findViewById(R.id.editText);
        timeArray.add(timeStamp);
        editText.setText(timeStamp);
    }

    public void recordData(View view){

        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    String dataString = TextUtils.join(", ", timeArray);
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
