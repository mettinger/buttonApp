package org.twobuttons.app1.twobuttons;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

//import org.twobuttons.app1.twobuttons.Main2Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static String LOG_TAG = "MainActivity";
    public static final String UserKey = "UsernameKey";

    TextView TxtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginButton(View view) {

        EditText editText = findViewById(R.id.editTextUserName);
        final String username = editText.getText().toString();

        EditText editText2 = findViewById(R.id.editTextPassword);
        final String password = editText2.getText().toString();

        String result=null;

        //new MakeNetworkCall().execute("http://www.twobuttons.org/loginApp/" + username + "--" + password, "Get");
        try {
            result = new MakeNetworkCall().execute("http://www.twobuttons.org/loginApp/" + username + "-" + password, "Get").get();

        } catch (Exception ex){
            int placeholder = 1;
        }

        if (!result.equals("Username or Password Error...")) {
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra(UserKey, result);
            startActivity(intent);
        }

    }


    InputStream ByGetMethod(String ServerURL) {

        InputStream DataInputStream = null;
        try {

            URL url = new URL(ServerURL);
            HttpURLConnection cc = (HttpURLConnection)
                    url.openConnection();
            //set timeout for reading InputStream
            cc.setReadTimeout(5000);
            // set timeout for connection
            cc.setConnectTimeout(5000);
            //set HTTP method to GET
            cc.setRequestMethod("GET");
            //set it to true as we are connecting for input
            cc.setDoInput(true);

            //reading HTTP response code
            int response = cc.getResponseCode();

            //if response code is 200 / OK then read Inputstream
            if (response == HttpURLConnection.HTTP_OK) {
                DataInputStream = cc.getInputStream();
            }

        } catch (Exception e) {
            //Log.e(LOG_TAG, "Error in GetData", e);
            int placeholder = 1;
        }
        return DataInputStream;

    }


    String ConvertStreamToString(InputStream stream) {

        InputStreamReader isr = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder response = new StringBuilder();

        String line = null;
        try {

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

        } catch (IOException e) {
            //Log.e(LOG_TAG, "Error in ConvertStreamToString", e);
            int placeholder = 1;
        } catch (Exception e) {
            //Log.e(LOG_TAG, "Error in ConvertStreamToString", e);
            int placeholder = 1;
        } finally {

            try {
                stream.close();

            } catch (IOException e) {
                //Log.e(LOG_TAG, "Error in ConvertStreamToString", e);
                int placeholder = 1;

            } catch (Exception e) {
                //Log.e(LOG_TAG, "Error in ConvertStreamToString", e);
                int placeholder = 1;
            }
        }
        return response.toString();


    }

    public void DisplayMessage(String a) {

        TxtResult = (TextView) findViewById(R.id.response);
        TxtResult.setText(a);
    }

    private class MakeNetworkCall extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            DisplayMessage("Please Wait ...");
        }

        @Override
        protected String doInBackground(String... arg) {

            InputStream is = null;
            String URL = arg[0];
            //Log.d(LOG_TAG, "URL: " + URL);
            String res = "";

            is = ByGetMethod(URL);

            if (is != null) {
                res = ConvertStreamToString(is);
            } else {
                res = "Something went wrong";
            }
            return res;
        }

        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            if (result.equals("Username or Password Error...")) {
                DisplayMessage(result);
            }
            //Log.d(LOG_TAG, "Result: " + result);
        }
    }

}

