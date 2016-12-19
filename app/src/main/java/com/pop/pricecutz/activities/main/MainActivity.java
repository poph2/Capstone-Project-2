package com.pop.pricecutz.activities.main;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SyncAdapterType;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.gson.Gson;
import com.pop.pricecutz.Category;
import com.pop.pricecutz.Company;
import com.pop.pricecutz.Data;
import com.pop.pricecutz.Discount;
import com.pop.pricecutz.R;
import com.pop.pricecutz.Randomizer;
import com.pop.pricecutz.activities.login.LoginActivity;
import com.pop.pricecutz.activities.main.fragments.CategoryFragment;
import com.pop.pricecutz.activities.main.fragments.FavoriteFragment;
import com.pop.pricecutz.activities.main.fragments.HomeFragment;
import com.pop.pricecutz.activities.main.fragments.NearMeFragment;
import com.pop.pricecutz.activities.main.fragments.InvetoryFragment;
import com.pop.pricecutz.backend.categoryBeanApi.CategoryBeanApi;
import com.pop.pricecutz.backend.categoryBeanApi.model.CategoryBean;
import com.pop.pricecutz.backend.companyBeanApi.CompanyBeanApi;
import com.pop.pricecutz.backend.companyBeanApi.model.CompanyBean;
import com.pop.pricecutz.backend.discountBeanApi.DiscountBeanApi;
import com.pop.pricecutz.backend.discountBeanApi.model.DiscountBean;
import com.pop.pricecutz.backend.outletBeanApi.OutletBeanApi;
import com.pop.pricecutz.data.entries.CompanyEntry;
import com.pop.pricecutz.data.entries.Data1;
import com.pop.pricecutz.sync.PCSyncAdapter;
import com.pop.pricecutz.utils.BeanEntityConverter;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String LOG_TAG = MainActivity.class.getSimpleName();

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Context mContext;

    private int[] tabIcons = {
            R.drawable.ic_home_white_24dp,
            R.drawable.ic_person_pin_circle_white_24dp,
            R.drawable.ic_favorite_white_24dp,
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

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();

        viewPager.setCurrentItem(0);

        //new InitialDataAsyncTask().execute("");

        //addToDatabase();
        //readFromDatabase();

        Log.d("AsyncTask", "About to start");

        //new EndpointsAsyncTask().execute(1);

        //Request Sync
        PCSyncAdapter.syncImmediately(mContext);

        getUserData();
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
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_log_out) {
            logOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new HomeFragment(), "");
        adapter.addFragment(new NearMeFragment(), "");
        adapter.addFragment(new FavoriteFragment(), "");
        adapter.addFragment(new CategoryFragment(), "");
        adapter.addFragment(new InvetoryFragment(), "");

//        adapter.addFragment(new Fragment(), "ONE");
//        adapter.addFragment(new Fragment(), "TWO");
//        adapter.addFragment(new Fragment(), "THREE");

        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return mFragmentTitleList.get(position);
            return null;
        }
    }

    public void addToDatabase() {
        ContentValues[] contentValuesArr = new ContentValues[Data1.name.length];

        //int i = 1;

        for(int i = 0; i < Data1.name.length; i++) {
        //for(int i = 0; i <= 1; i++) {

            contentValuesArr[i] = new ContentValues();

            contentValuesArr[i].put(CompanyEntry.COLUMN_COY_ID, Integer.toString(i));
            contentValuesArr[i].put(CompanyEntry.COLUMN_NAME, Data1.name[i]);
            contentValuesArr[i].put(CompanyEntry.COLUMN_INDUSTRY, Data1.industry[i]);
            contentValuesArr[i].put(CompanyEntry.COLUMN_IMAGE_URL, Data1.image_url[i]);



            //Cursor c = getContentResolver().query(CompanyEntry.CONTENT_URI, null, CompanyEntry.COLUMN_COY_ID + " = " + Integer.toString(i), null, null);
            //if(c.getCount() == 0) {
            //Uri uri = getContentResolver().insert(CompanyEntry.CONTENT_URI, contentValues);

            Log.d(LOG_TAG, i + ": \t" + Data1.name[i] + "\t" + Data1.image_url[i]);
            //}

//        Uri uri = getContentResolver().insert(CompanyEntry.CONTENT_URI, contentValues);
            //Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
//        refreshValuesFromContentProvider();
        }

        int response = getContentResolver().bulkInsert(CompanyEntry.CONTENT_URI, contentValuesArr);
        Toast.makeText(getBaseContext(), "Bulk insert", Toast.LENGTH_LONG).show();
    }

    public void logOut() {
        LoginManager.getInstance().logOut();
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();
    }

    private class InitialDataAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                for (int i = 0; i < Data1.name.length; i++) {
                    //showToast(getBaseContext(), "i = " + i);
                    addToDatabase(getBaseContext(), i);
                    //Toast.makeText(getBaseContext(), "i = " + i, Toast.LENGTH_LONG).show();
                    Thread.sleep(1000);
                }
            }
            catch (Exception e) {
                Log.e(LOG_TAG, e.getMessage());
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
            //etResponse.setText(result);
        }

        public void addToDatabase(Context context, int i) {
            ContentValues contentValues = new ContentValues();

            contentValues = new ContentValues();

            contentValues.put(CompanyEntry.COLUMN_COY_ID, Integer.toString(i));
            contentValues.put(CompanyEntry.COLUMN_NAME, Data1.name[i]);
            contentValues.put(CompanyEntry.COLUMN_INDUSTRY, Data1.industry[i]);
            contentValues.put(CompanyEntry.COLUMN_IMAGE_URL, Data1.image_url[i]);

            //Cursor c = getContentResolver().query(CompanyEntry.CONTENT_URI, null, CompanyEntry.COLUMN_COY_ID + " = " + Integer.toString(i), null, null);
            //if(c.getCount() == 0) {
                Uri uri = getContentResolver().insert(CompanyEntry.CONTENT_URI, contentValues);

                Log.d(LOG_TAG, i + ": \t" + Data1.name[i] + "\t" + Data1.image_url[i]);

                showToast(context, Data1.name[i] + "\t" + Data1.image_url[i]);
            //}
        }

        private void showToast(Context context, String str) {
            final String str2 = str;
            Handler handler =  new Handler(context.getMainLooper());
            handler.post( new Runnable(){
                public void run(){
                    Toast.makeText(getBaseContext(), str2, Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    public class EndpointsAsyncTask extends AsyncTask<Integer, Void, String> {

        private CategoryBeanApi categoryBeanApi = null;
        private CompanyBeanApi companyBeanApi = null;
        private DiscountBeanApi discountBeanApi = null;
        private OutletBeanApi outletBeanApi = null;

        private Integer index;

        @Override
        protected String doInBackground(Integer... params) {
            index = params[0];

//            loadInitialCategoryData();
//            loadInitialCompanyData();
//            loadInitialDiscountData();

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            //jokeGetterListener.jokeGotten(result);
        }

        private void loadInitialCategoryData() {
            if (categoryBeanApi == null) {  // Only do this once
                CategoryBeanApi.Builder builder = new CategoryBeanApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("https://price-cutz.appspot.com/_ah/api/");
                // end options for devappserver

                categoryBeanApi = builder.build();
            }

            for(int i = 1; i <= Data.categories.length; i++) {
                try {
                    Category c = new Category(i, "", Data.categories[i-1]);
                    CategoryBean bean = BeanEntityConverter.convertToBean(c);
                    Log.d("AsyncTask", "About to insert - " + i);
                    categoryBeanApi.insert(bean).execute();
                    Log.d("AsyncTask", "Insert performed - " + i);
                }
                catch(IOException e) {
                    Log.e("AsyncTask", e.getMessage());
                    e.printStackTrace();
                }
            }

            Log.d("AsyncTask", "Insert performed");
        }

        private void loadInitialCompanyData() {
            if (companyBeanApi == null) {  // Only do this once
                CompanyBeanApi.Builder builder = new CompanyBeanApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("https://price-cutz.appspot.com/_ah/api/");
                // end options for devappserver

                companyBeanApi = builder.build();
            }

            for(int i = 1; i <= Data.name.length; i++) {
                try {
                    Company c = new Company(i);
                    CompanyBean cBean = BeanEntityConverter.convertToBean(c);
                    Log.d("AsyncTask", "About to insert - " + i);
                    companyBeanApi.insert(cBean).execute();
                    Log.d("AsyncTask", "Insert performed - " + i);
                }
                catch(IOException e) {
                    Log.e("AsyncTask", e.getMessage());
                    e.printStackTrace();
                }
            }

            Log.d("AsyncTask", "Insert performed");
        }

        private void loadInitialDiscountData() {
            if (discountBeanApi == null) {  // Only do this once
                DiscountBeanApi.Builder builder = new DiscountBeanApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("https://price-cutz.appspot.com/_ah/api/");
                // end options for devappserver

                discountBeanApi = builder.build();
            }

            for(long i = 1; i <= 100; i++) {
                try {
                    Discount d = Randomizer.getDiscount(i);
                    DiscountBean bean = BeanEntityConverter.convertToBean(d);
                    Log.d("AsyncTask", "About to insert - " + i);
                    discountBeanApi.insert(bean).execute();
                    Log.d("AsyncTask", "Insert performed - " + i);
                }
                catch(IOException e) {
                    Log.e("AsyncTask", e.getMessage());
                    e.printStackTrace();
                }
            }

            Log.d("AsyncTask", "Insert performed");
        }


    }

    private void getUserData() {

        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Toast.makeText(getBaseContext(), new Gson().toJson(object), Toast.LENGTH_LONG).show();
                    }
                });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link,email,gender,birthday");
//            request.setParameters(parameters);
        request.executeAsync();
    }

}
