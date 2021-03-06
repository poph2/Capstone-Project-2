package com.pop.pricecutz.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SyncRequest;
import android.content.SyncResult;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.gson.Gson;
import com.pop.pricecutz.R;
import com.pop.pricecutz.backend.categoryApi.CategoryApi;
import com.pop.pricecutz.backend.categoryApi.model.Category;
import com.pop.pricecutz.backend.categoryApi.model.CollectionResponseCategory;
import com.pop.pricecutz.backend.companyApi.CompanyApi;
import com.pop.pricecutz.backend.companyApi.model.CollectionResponseCompany;
import com.pop.pricecutz.backend.companyApi.model.Company;
import com.pop.pricecutz.backend.discountApi.DiscountApi;
import com.pop.pricecutz.backend.discountApi.model.CollectionResponseDiscount;
import com.pop.pricecutz.backend.discountApi.model.Discount;
import com.pop.pricecutz.backend.outletApi.OutletApi;
import com.pop.pricecutz.backend.outletApi.model.CollectionResponseOutlet;
import com.pop.pricecutz.backend.outletApi.model.Outlet;
import com.pop.pricecutz.data.entries.CategoryEntry;
import com.pop.pricecutz.data.entries.CompanyEntry;
import com.pop.pricecutz.data.entries.DiscountEntry;
import com.pop.pricecutz.data.entries.OutletEntry;

import java.util.List;

/**
 * Created by Pop H2 on 12/19/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class PCSyncAdapter extends AbstractThreadedSyncAdapter {

    public static final String LOG_TAG = PCSyncAdapter.class.getSimpleName();

    Context context;

    public static final int SYNC_INTERVAL = 60 * 180;
    public static final int SYNC_FLEXTIME = SYNC_INTERVAL/3;
    private static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;

    public PCSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);

        this.context = context;
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {

        long timeInMillis = System.currentTimeMillis();
        long lastSyncTime = getLastSyncTime();

        syncCategory(lastSyncTime);
        syncCompany(lastSyncTime);
        syncDiscount(lastSyncTime);
        syncOutlet(lastSyncTime);

//        setLastSyncTime(timeInMillis);

    }

    public static void configurePeriodicSync(Context context, int syncInterval, int flexTime) {
        Account account = getSyncAccount(context);
        String authority = context.getString(R.string.content_authority);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // we can enable inexact timers in our periodic sync
            SyncRequest request = new SyncRequest.Builder().
                    syncPeriodic(syncInterval, flexTime).
                    setSyncAdapter(account, authority).
                    setExtras(new Bundle()).build();
            ContentResolver.requestSync(request);
        } else {
            ContentResolver.addPeriodicSync(account,
                    authority, new Bundle(), syncInterval);
        }
    }

    public static Account getSyncAccount(Context context) {
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        // Create the account type and default account
        Account newAccount = new Account(
                context.getString(R.string.app_name), context.getString(R.string.sync_account_type));

        // If the password doesn't exist, the account doesn't exist
        if ( null == accountManager.getPassword(newAccount) ) {

        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
            if (!accountManager.addAccountExplicitly(newAccount, "", null)) {
                return null;
            }
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call ContentResolver.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */

            onAccountCreated(newAccount, context);
        }
        return newAccount;
    }

    private static void onAccountCreated(Account newAccount, Context context) {
        /*
         * Since we've created an account
         */
        PCSyncAdapter.configurePeriodicSync(context, SYNC_INTERVAL, SYNC_FLEXTIME);

        /*
         * Without calling setSyncAutomatically, our periodic sync will not be enabled.
         */
        ContentResolver.setSyncAutomatically(newAccount, context.getString(R.string.content_authority), true);

        /*
         * Finally, let's do a sync to get things started
         */
        syncImmediately(context);
    }

    public static void syncImmediately(Context context) {

        final Context c = context;

        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(getSyncAccount(context),
                context.getString(R.string.content_authority), bundle);
    }

    public long getLastSyncTime() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        String lastSyncKey = context.getString(R.string.pref_last_sync);
        long lastSyncTime = prefs.getLong(lastSyncKey, 0);

        return lastSyncTime;
    }

    public void setLastSyncTime(long lastSyncTime) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();

        String lastSyncKey = context.getString(R.string.pref_last_sync);
        editor.putLong(lastSyncKey, lastSyncTime);
        editor.apply();
    }

    public void syncCategory(long lastSyncTime) {
        CategoryApi.Builder builder = new CategoryApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl("https://price-cutz.appspot.com/_ah/api/");

        CategoryApi categoryApi = builder.build();

        try {

            CategoryApi.ListByTime categoryListByTime = categoryApi.listByTime(lastSyncTime);
            CollectionResponseCategory catCollection = categoryListByTime.execute();

            List<Category> categories = catCollection.getItems();

            ContentValues contentValuesArr[] = new ContentValues[categories.size()];

            for(int i = 0; i < categories.size(); i++) {

                Category category = categories.get(i);

                ContentValues contentValues = CategoryEntry.getContentValues(category);

                contentValuesArr[i] = contentValues;
            }

            int i = getContext().getContentResolver().bulkInsert(CategoryEntry.CONTENT_URI, contentValuesArr);

        }
        catch(Exception e) {
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    public void syncCompany(long lastSyncTime) {
        CompanyApi.Builder builder = new CompanyApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl("https://price-cutz.appspot.com/_ah/api/");

        CompanyApi companyApi = builder.build();

        try {

            CompanyApi.ListByTime companyListByTime = companyApi.listByTime(lastSyncTime);
            CollectionResponseCompany coyCollection = companyListByTime.execute();

            List<Company> companies = coyCollection.getItems();

            Log.d(LOG_TAG, "companies - " + new Gson().toJson(companies));

            ContentValues contentValuesArr[] = new ContentValues[companies.size()];

            for(int i = 0; i < companies.size(); i++) {

                Company company = companies.get(i);

                ContentValues contentValues = CompanyEntry.getContentValues(company);

                contentValuesArr[i] = contentValues;
            }

            int i = getContext().getContentResolver().bulkInsert(CompanyEntry.CONTENT_URI, contentValuesArr);

        }
        catch(Exception e) {
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    public void syncDiscount(long lastSyncTime) {
        DiscountApi.Builder builder = new DiscountApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl("https://price-cutz.appspot.com/_ah/api/");
        // end options for devappserver

        DiscountApi discountApi = builder.build();

        Log.d(LOG_TAG, "Sync Started");

        try {

            DiscountApi.ListByTime discountListByTime = discountApi.listByTime(lastSyncTime);
            CollectionResponseDiscount dbCollection = discountListByTime.execute();

            List<Discount> discountList = dbCollection.getItems();

            ContentValues contentValuesArr[] = new ContentValues[discountList.size()];

            for(int i = 0; i < discountList.size(); i++) {
                Log.d(LOG_TAG, i + " - " + discountList.get(i).getDiscCode());

                Discount discount = discountList.get(i);

                ContentValues contentValues = DiscountEntry.getContentValues(discount);

                contentValuesArr[i] = contentValues;

            }

            int i = getContext().getContentResolver().bulkInsert(DiscountEntry.CONTENT_URI, contentValuesArr);

        }
        catch(Exception e) {
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    public void syncOutlet(long lastSyncTime) {
        OutletApi.Builder builder = new OutletApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl("https://price-cutz.appspot.com/_ah/api/");
        // end options for devappserver

        OutletApi outletApi = builder.build();

        Log.d(LOG_TAG, "Sync Started");

        try {

            OutletApi.ListByTime outletListByTime = outletApi.listByTime(lastSyncTime);
            CollectionResponseOutlet dbCollection = outletListByTime.execute();

            List<Outlet> outletList = dbCollection.getItems();

            ContentValues contentValuesArr[] = new ContentValues[outletList.size()];

            Log.d(LOG_TAG, "outletList.size() - " + outletList.size());

            for(int i = 0; i < outletList.size(); i++) {

                Outlet outlet = outletList.get(i);

                ContentValues contentValues = OutletEntry.getContentValues(outlet);

                contentValuesArr[i] = contentValues;

            }

            int i = getContext().getContentResolver().bulkInsert(OutletEntry.CONTENT_URI, contentValuesArr);

        }
        catch(Exception e) {
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
        }
    }


}
