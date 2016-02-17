package com.iitguide.iitguide;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.auth.Credentials;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    Button btnEnter;
    LinearLayout imageLayout;
    RelativeLayout userLayout;
    Animation imageAnimate;
    Animation userAnimate;
    Animation infoAnimate;
    TextView infoText;
    LinearLayout infoLayout;
    private Toolbar mainToolBar;
    public EditText userNameView;
    public EditText passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        btnEnter = (Button) findViewById(R.id.btnEnter);
        imageLayout= (LinearLayout) findViewById(R.id.imageLayout);
        userLayout = (RelativeLayout) findViewById(R.id.userLayout);
        mainToolBar = (Toolbar) findViewById(R.id.app_bar);
        userNameView = (EditText) findViewById(R.id.userNameView);
        userNameView.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        passwordView = (EditText) findViewById(R.id.passwordView);




        mainToolBar.setTitle("IITGuide");
     //   infoLayout = (LinearLayout) findViewById(R.id.infoLayout);
     //   infoText = (TextView) findViewById(R.id.infoText);

     /*   imageAnimate = AnimationUtils.loadAnimation(this, R.anim.image_fade_in);
        imageLayout.startAnimation(imageAnimate);
        imageLayout.setVisibility(View.VISIBLE);

        infoAnimate = AnimationUtils.loadAnimation(this, R.anim.user_fade_in);
        infoAnimate.setStartOffset(1000);
        infoLayout.startAnimation(infoAnimate);
        infoLayout.setVisibility(View.VISIBLE);


        userAnimate = AnimationUtils.loadAnimation(this, R.anim.user_fade_in);
        userAnimate.setStartOffset(1500);
        userLayout.startAnimation(userAnimate);
        userLayout.setVisibility(View.VISIBLE);

     */



        btnEnter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            boolean didSucceed = false;

            try {
                String myUsername = userNameView.getText().toString();
                String myPassword = passwordView.getText().toString();



                String myAuthUrl = "http://iitguide.com/restapi/rest/v1/student.json?email_id=" + myUsername + "&password=" + myPassword;
                //String myAuthUrl = "http://localhost:8080//restapi/rest/v1/student.json?email_id=" + myUsername + "&password=" + myPassword;
                System.out.println(myAuthUrl);

                try {

                    boolean connected = false;
                    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                        //we are connected to a network
                        connected = true;
                    }
                    else
                        connected = false;

                    if(!connected){

                        throw new Exception(VimeoHelper.CONNECTION_MISSING);
                    }

                    if(myUsername.equals("")|| myPassword.equals("")){

                        throw new Exception(VimeoHelper.BLANK_LOGIN_ENTRY);
                    }

                    URL myLoginUrl = new URL(myAuthUrl);
                    URLConnection yc = myLoginUrl.openConnection();

                    Log.d("OPEN_CONNECTION", "SUCCESSSUCCESS");

                    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                    String inputLine;
                    StringBuffer jsonString = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        jsonString.append(inputLine);
                    }
                    in.close();

                    Log.e("----------------", "---JSONSTRING---");
                    System.out.println(jsonString.toString());
                    Log.e("processAuth", "Success!!!!!!!");
                    VimeoHelper.processAuthJsonResponse(jsonString);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), (String) e.getMessage(), Toast.LENGTH_LONG).show();
                    throw new LoginFailException();
                }

                didSucceed = true;

                Intent intent = new Intent(MainActivity.this, CourseActivity.class);
                startActivity(intent);
                finish();


            } catch(LoginFailException failException) {
                 failException.printStackTrace();
            }
        } //OnClick
    }); //SetOnClick
} //OnCreate


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
