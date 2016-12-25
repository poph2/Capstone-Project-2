package com.pop.pricecutz.activities.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.gson.Gson;
import com.pop.pricecutz.activities.SplashScreen2Activity;
import com.pop.pricecutz.activities.main.MainActivity;
import com.pop.pricecutz.activities.other.PreferenceActivity;
import com.pop.pricecutz.backend.companyApi.CompanyApi;
import com.pop.pricecutz.backend.discountApi.DiscountApi;
import com.pop.pricecutz.backend.fBAccountApi.FBAccountApi;
import com.pop.pricecutz.backend.fBAccountApi.model.FBAccount;
import com.pop.pricecutz.backend.userApi.UserApi;
import com.pop.pricecutz.backend.userApi.model.User;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.facebook.FacebookSdk;
import com.pop.pricecutz.R;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks , GoogleApiClient.OnConnectionFailedListener {

    public static String LOG_TAG = LoginActivity.class.getSimpleName();


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    CallbackManager callbackManager;

    LoginButton loginButtonFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        loginButtonFacebook = (LoginButton) findViewById(R.id.login_button_facebook);

        loginButtonFacebook.setReadPermissions(Arrays.asList("public_profile","user_friends","email"));


        loginButtonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getBaseContext(), "Facebook Login Success", Toast.LENGTH_LONG).show();

                Intent i = new Intent(getBaseContext(), SplashScreen2Activity.class);
                startActivity(i);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getBaseContext(), "Facebook Login Cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getBaseContext(), "Facebook Login Error", Toast.LENGTH_LONG).show();
            }
        });

//        checkFacebookLogin();

    }

    private void checkFacebookLogin() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        Intent i = null;

        if(accessToken != null) {
            boolean preferenceIsSet = true;
            if(preferenceIsSet) {
                i = new Intent(getApplicationContext(), MainActivity.class);
            }
            else {
                i = new Intent(getApplicationContext(), PreferenceActivity.class);
            }
            startActivity(i);
            finish();
        }
    }

    private boolean createUser() {
        boolean returnVal = false;
        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        if(accessToken != null) {

            String userID = accessToken.getUserId();

            returnVal = true;
        }

        return returnVal;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}

