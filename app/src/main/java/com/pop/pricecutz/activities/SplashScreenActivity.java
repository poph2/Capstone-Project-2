package com.pop.pricecutz.activities;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.FacebookSdk;
import com.facebook.stetho.Stetho;
import com.pop.pricecutz.R;
import com.pop.pricecutz.activities.login.LoginActivity;
import com.pop.pricecutz.activities.main.MainActivity;
import com.pop.pricecutz.data.entries.FBAccountEntry;

public class SplashScreenActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

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

        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(
                mContext,
                FBAccountEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        Intent i = null;
        if(cursor.getCount() > 0) { //User is logged in. Show MainActivity
            i = new Intent(mContext, MainActivity.class);
        }
        else {                      //User is not logged in. Show LoginActivity.
            i = new Intent(mContext, LoginActivity.class);
        }

        startActivity(i);
        finish();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
