package com.pop.pricecutz.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.stetho.Stetho;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.gson.Gson;
import com.pop.pricecutz.R;
import com.pop.pricecutz.activities.main.MainActivity;
import com.pop.pricecutz.backend.fBAccountApi.FBAccountApi;
import com.pop.pricecutz.backend.fBAccountApi.model.FBAccount;
import com.pop.pricecutz.data.entries.FBAccountEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SplashScreen2Activity extends AppCompatActivity {

    public static String LOG_TAG = SplashScreen2Activity.class.getSimpleName();

    Context mContext;

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mContext = getApplicationContext();
        FacebookSdk.sdkInitialize(getApplicationContext());

        Stetho.initializeWithDefaults(this);


        AccessToken accessToken = AccessToken.getCurrentAccessToken();

//        new GraphRequest(
//                AccessToken.getCurrentAccessToken(),
//                "/me",
//                null,
//                HttpMethod.GET,
//                new GraphRequest.Callback() {
//                    public void onCompleted(GraphResponse response) {
//                        Log.d(LOG_TAG, new Gson().toJson(response.toString()));
//                    }
//                }
//        ).executeAsync();

        GraphRequest request = GraphRequest.newMeRequest(accessToken,
            new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {

                    try {

                        Log.d(LOG_TAG, new Gson().toJson(object));

                        long timeInMillis = System.currentTimeMillis();

                        FBAccount fbA = new FBAccount();
                        fbA.setFbacctFbId(object.getString("id"));
                        fbA.setFbacctFirstName(object.getString("first_name"));
                        fbA.setFbacctLastName(object.getString("last_name"));
                        fbA.setFbacctName(object.getString("name"));
                        fbA.setFbacctLinkUri(object.getString("link"));
                        fbA.setFbacctEmail(object.getString("email"));
                        fbA.setFbacctGender(object.getString("gender"));
                        fbA.setFbacctCreatedTime(timeInMillis);
                        fbA.setFbacctUpdatedTime(timeInMillis);

                        new CreateFacebookAccountAsyncTask().execute(fbA);
                    }
                    catch (JSONException e) {
                        Log.d(LOG_TAG, e.getMessage());
                        e.printStackTrace();
                    }
                }
            });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,first_name,middle_name,last_name,name,link,email,gender");

        request.setParameters(parameters);
        request.executeAsync();
    }


    class CreateFacebookAccountAsyncTask extends AsyncTask<FBAccount, Void, FBAccount> {
        @Override
        protected FBAccount doInBackground(FBAccount... fbAccounts) {

            FBAccount fbA = null;

            try {

                FBAccountApi.Builder builder = new FBAccountApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("https://price-cutz.appspot.com/_ah/api/");
                FBAccountApi fbAccountApi = builder.build();

                FBAccountApi.CheckExists checkExists = fbAccountApi.checkExists(fbAccounts[0].getFbacctFbId());
                fbA = checkExists.execute();

                if(fbA == null) {
                    FBAccountApi.Insert insert = fbAccountApi.insert(fbAccounts[0]);
                    fbA = insert.execute();
                }
            }
            catch (IOException e) {
                Log.e(LOG_TAG, e.getMessage());
                e.printStackTrace();
            }

            return fbA;
        }

        @Override
        protected void onPostExecute(FBAccount fbAccount) {
            super.onPostExecute(fbAccount);

            saveFBAccountToDB(fbAccount);

            Intent i = new Intent(mContext, MainActivity.class);
            startActivity(i);
            finish();

            Log.d(LOG_TAG, new Gson().toJson(fbAccount));
        }

    }

    private void saveFBAccountToDB(FBAccount fbAccount) {

        mContext.getContentResolver().delete(FBAccountEntry.CONTENT_URI, "fbacct_fb_id = " + fbAccount.getFbacctFbId(), null);

        Uri uri = mContext.getContentResolver().insert(FBAccountEntry.CONTENT_URI, FBAccountEntry.getContentValues(fbAccount));

    }

}
