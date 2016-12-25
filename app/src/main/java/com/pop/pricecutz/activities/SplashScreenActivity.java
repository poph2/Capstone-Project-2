package com.pop.pricecutz.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.stetho.Stetho;
import com.pop.pricecutz.R;
import com.pop.pricecutz.activities.login.LoginActivity;
import com.pop.pricecutz.activities.main.MainActivity;
import com.pop.pricecutz.activities.other.PreferenceActivity;
import com.pop.pricecutz.sync.PCSyncAdapter;

public class SplashScreenActivity extends AppCompatActivity {

    Context mContext;

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mContext = getApplicationContext();
        FacebookSdk.sdkInitialize(getApplicationContext());

        Stetho.initializeWithDefaults(this);

        //PCSyncAdapter.syncImmediately(mContext);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                checkFacebookLogin();
            }
        }, SPLASH_TIME_OUT);
    }

    private void checkFacebookLogin() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        Intent i = null;
//        if(accessToken != null) {
//            boolean preferenceIsSet = true;
//            if(preferenceIsSet) {
//                i = new Intent(getApplicationContext(), MainActivity.class);
//            }
//            else {
//                i = new Intent(getApplicationContext(), PreferenceActivity.class);
//            }
//            startActivity(i);
//            finish();
//
//        }
//        else {
            i = new Intent(mContext, LoginActivity.class);
//        }
        LoginManager.getInstance().logOut();
        startActivity(i);
        finish();
    }
}
