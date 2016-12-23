package com.pop.pricecutz.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncRequest;
import android.content.SyncResult;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.pop.pricecutz.R;
import com.pop.pricecutz.backend.companyApi.CompanyApi;
import com.pop.pricecutz.backend.companyApi.model.CollectionResponseCompany;
import com.pop.pricecutz.backend.companyApi.model.Company;
import com.pop.pricecutz.backend.discountApi.DiscountApi;
import com.pop.pricecutz.backend.discountApi.model.CollectionResponseDiscount;
import com.pop.pricecutz.backend.discountApi.model.Discount;
import com.pop.pricecutz.data.entries.CompanyEntry;
import com.pop.pricecutz.data.entries.DiscountEntry;
import com.pop.pricecutz.utils.BeanEntryConverter;

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

        syncCompany();
        syncDiscount();

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

    public void syncCompany() {
        CompanyApi.Builder builder = new CompanyApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl("https://price-cutz.appspot.com/_ah/api/");
        // end options for devappserver

        CompanyApi companyApi = builder.build();

        Log.d(LOG_TAG, "Sync Started");

        try {
            CompanyApi.Get get = companyApi.get(1l);

            Company company = get.execute();

            Log.d(LOG_TAG, "company - " + company.getCoyName());


//            CompanyApi.List company_List = companyApi.list(100);
            CompanyApi.List company_List = companyApi.list();
            CollectionResponseCompany coyCollection = company_List.execute();

            List<Company> companyList = coyCollection.getItems();

//             Log.d(LOG_TAG, "dbCollection - " + dbCollection.getCode());

            ContentValues contentValuesArr[] = new ContentValues[companyList.size()];

            for(int i = 0; i < companyList.size(); i++) {
                Log.d(LOG_TAG, i + " - " + companyList.get(i).getCoyName());

                company = companyList.get(i);

                ContentValues contentValues = BeanEntryConverter.convertToContentValues(company);

                contentValuesArr[i] = contentValues;


                Log.d(LOG_TAG, "Sync Performed");
            }

            int i = getContext().getContentResolver().bulkInsert(CompanyEntry.CONTENT_URI, contentValuesArr);

            Log.d(LOG_TAG, "Sync Performed");

        }
        catch(Exception e) {
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    public void syncDiscount() {
        DiscountApi.Builder builder = new DiscountApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl("https://price-cutz.appspot.com/_ah/api/");
        // end options for devappserver

        DiscountApi discountApi = builder.build();

        Log.d(LOG_TAG, "Sync Started");

        try {
            DiscountApi.Get discountGet = discountApi.get(1l);

            Discount discount = discountGet.execute();

            Log.d(LOG_TAG, "discount - " + discount.getDiscCode());


//            DiscountApi.List discount_List = discountApi.list(100);
            DiscountApi.List discount_List = discountApi.list();
            CollectionResponseDiscount dbCollection = discount_List.execute();

            List<Discount> discountList = dbCollection.getItems();

//             Log.d(LOG_TAG, "dbCollection - " + dbCollection.getCode());

            ContentValues contentValuesArr[] = new ContentValues[discountList.size()];

            for(int i = 0; i < discountList.size(); i++) {
                Log.d(LOG_TAG, i + " - " + discountList.get(i).getDiscCode());

                discount = discountList.get(i);

                ContentValues contentValues = BeanEntryConverter.convertToContentValues(discount);

                contentValuesArr[i] = contentValues;


                Log.d(LOG_TAG, "Sync Performed");
            }

            int i = getContext().getContentResolver().bulkInsert(DiscountEntry.CONTENT_URI, contentValuesArr);

            Log.d(LOG_TAG, "Sync Performed");

        }
        catch(Exception e) {
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
        }
    }

//    private void syncData() {
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//
//        String lastSyncKey = context.getString(R.string.pref_last_sync);
//        long lastSync = prefs.getLong(lastSyncKey, 0);
//
//        if (System.currentTimeMillis() - lastSync >= DAY_IN_MILLIS) {
//
//
//            //refreshing last sync
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putLong(lastSyncKey, System.currentTimeMillis());
//            editor.commit();
//        }
//    }
}
