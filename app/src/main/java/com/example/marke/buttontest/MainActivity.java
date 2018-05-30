package com.example.marke.buttontest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String myURL = "http://www.twobuttons.org/?data=";
    //String user_id = "1";
    public static final String UsernameKey = "UsernameKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginButton(View view) {

        /*
        Intent intent = new Intent(this, Main2Activity.class);
        EditText editText = findViewById(R.id.editTextUserName);
        String username = editText.getText().toString();
        intent.putExtra( UsernameKey, username);
        startActivity(intent); */

        URL url = new URL("http://www.android.com/");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            readStream(in);
        } finally {
            urlConnection.disconnect();
        }

        Intent intent = new Intent(this, Main2Activity.class);

        intent.putExtra( UsernameKey, username);
        startActivity(intent);
    }
}

 /*
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String myURL = "http://www.twobuttons.org:8890/?data="; //personal aws machine
    String user_id = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void sendData(String buttonCode) {

        final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date()) + buttonCode;
        EditText timeStampBox = findViewById(R.id.editText);
        timeStampBox.setText(timeStamp);

        EditText infoBox = findViewById(R.id.editText2);
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
*/