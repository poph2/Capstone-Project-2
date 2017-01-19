package com.pop.pricecutz.activities.main;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.gson.Gson;
import com.pop.pricecutz.R;
import com.pop.pricecutz.activities.login.LoginActivity;
import com.pop.pricecutz.activities.main.fragments.CategoryFragment;
import com.pop.pricecutz.activities.main.fragments.HomeFragment;
import com.pop.pricecutz.activities.main.fragments.NearMeFragment;
import com.pop.pricecutz.activities.main.fragments.InventoryFragment;
import com.pop.pricecutz.activities.main.fragments.NearMeFragment2;
import com.pop.pricecutz.data.entries.FBAccountEntry;
import com.pop.pricecutz.sync.PCSyncAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<Cursor> {

    TextView usernameTextView, emailTextView;

    ImageView profilePictureImageView;


    public static String LOG_TAG = MainActivity.class.getSimpleName();

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Context mContext;

    private int[] tabIcons = {
            R.drawable.ic_home_white_24dp,
            R.drawable.ic_person_pin_circle_white_24dp,
//            R.drawable.ic_favorite_white_24dp,
            R.drawable.ic_view_module_white_24dp,
            R.drawable.ic_list_white_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getBaseContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        usernameTextView = (TextView) header.findViewById(R.id.usernameTextView);
        emailTextView = (TextView) header.findViewById(R.id.emailTextView);
        profilePictureImageView = (ImageView) header.findViewById(R.id.profile_picture_imageview);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();

        viewPager.setCurrentItem(1);

        //Request Sync
        //PCSyncAdapter.syncImmediately(mContext);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_home) {

        } else if (id == R.id.nav_near_me) {

        } else if (id == R.id.nav_categories) {

        } else if (id == R.id.nav_Inventory) {

        } else if (id == R.id.nav_log_out) {
            logOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
//        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
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

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            //Log.d(LOG_TAG, " index " + cursor.getString(cursor.getColumnIndex(FBAccountEntry.COLUMN_NAME)));

            usernameTextView.setText(cursor.getString(cursor.getColumnIndex(FBAccountEntry.COLUMN_NAME)));
            emailTextView.setText(cursor.getString(cursor.getColumnIndex(FBAccountEntry.COLUMN_EMAIL)));

            String id = cursor.getString(cursor.getColumnIndex(FBAccountEntry.COLUMN_FB_USER_ID));

            String imageURL = "https://graph.facebook.com/" + id + "/picture?type=large";

            Glide.with(mContext).load(imageURL).into(profilePictureImageView);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
//        private final List<Fragment> mFragmentList = new ArrayList<>();
//        private final List<String> mFragmentTitleList = new ArrayList<>();

        private final int mFragmentCount = 4;

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d(LOG_TAG, position + " tab selected.");
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new NearMeFragment();
                case 2:
                    return new CategoryFragment();
                case 3:
                    return new InventoryFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mFragmentCount;
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return mFragmentTitleList.get(position);
            return null;
        }
    }

    public void logOut() {
        LoginManager.getInstance().logOut();
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void getUserData() {

        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        GraphRequest request = GraphRequest.newMeRequest(accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Toast.makeText(getBaseContext(), new Gson().toJson(response), Toast.LENGTH_LONG).show();

                    }
                });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link,email,gender,birthday");
//            request.setParameters(parameters);
        request.executeAsync();
    }

}
