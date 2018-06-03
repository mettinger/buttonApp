package com.example.marke.buttontest;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.Intent;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    String myURL = "http://www.twobuttons.org:8890/?data="; //personal aws machine
    String user_id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Intent intent = getIntent();
        user_id = intent.getStringExtra(MainActivity.UserKey);
        //EditText editText = findViewById(R.id.editTextInfo);
        //editText.setText(user_id);
    }

    public void sendData(String buttonCode) {

        final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date()) + buttonCode;
        TextView timeStampBox = findViewById(R.id.textView_timestamp);
        timeStampBox.setText(timeStamp);

        EditText infoBox = findViewById(R.id.editTextInfo);
        final String info = String.valueOf(infoBox.getText());

        new Thread(new Runnable() {
            @Override
            public void run() {
                String dataString;
                try {
                    if (info.length() > 0) {
                        dataString = timeStamp + "-" + info + "-" + user_id;
                    } else {
                        dataString = timeStamp + "-" + "None" + "-" + user_id;
                    }
                    URL url = new URL(myURL + dataString);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int status = con.getResponseCode();
                    con.disconnect();
                } catch (Exception ex) {
                    int placeholder = 1;
                }
            }
        }).start();
    }

    public void doButton0(View view) {
        sendData("-0");
    }

    public void doButton1(View view) {
        sendData("-1");
    }
}

