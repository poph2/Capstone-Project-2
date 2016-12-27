package com.pop.pricecutz.activities.other;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.google.gson.Gson;
import com.pop.pricecutz.R;
import com.pop.pricecutz.backend.savedDiscountApi.model.SavedDiscount;
import com.pop.pricecutz.data.entries.CompanyEntry;
import com.pop.pricecutz.data.entries.DiscountEntry;
import com.pop.pricecutz.data.entries.SavedDiscountEntry;


public class DiscountActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static String LOG_TAG = DiscountActivity.class.getSimpleName();

    Context mContext;

    long mID;

    String fb_id;

    ImageView imageView, imageView2;
    TextView companyTextView, descrTextView, titleTextView;

    boolean savedByUser;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        setToolbar();

        FacebookSdk.sdkInitialize(this.getApplicationContext());

        fb_id = AccessToken.getCurrentAccessToken().getUserId();

        mContext = getBaseContext();

        Intent i = getIntent();

        mID = i.getLongExtra(DiscountEntry._ID, 0);

        if(mID == 0) {
            onBackPressed();
        }

        imageView = (ImageView) findViewById(R.id.discountBannerImageView);
        imageView2 = (ImageView) findViewById(R.id.companyLogoImageView);

        companyTextView = (TextView) findViewById(R.id.discountCompanyTextView);
        descrTextView = (TextView) findViewById(R.id.discountDescriptionTextView);
        titleTextView = (TextView) findViewById(R.id.discountTitleTextView);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                archiveFABCLicked();
            }
        });

        getLoaderManager().initLoader(0, null, this);
        getLoaderManager().initLoader(1, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        switch (id) {
            case 0:
                return new CursorLoader(
                        mContext,
                        DiscountEntry.CONTENT_URI_WITH_COMPANY,
                        null,
                        "discount._id = " + mID,
                        null,
                        null
                );
            case 1:
                return new CursorLoader(
                        mContext,
                        SavedDiscountEntry.CONTENT_URI,
                        null,
                        "discount_id = " + mID,
                        null,
                        null
                );
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        Log.d(LOG_TAG, loader.getId() + " - " + new Gson().toJson(data.getColumnNames()));

        switch (loader.getId()) {

            case 0:
                data.moveToFirst();

                String companyName = data.getString(data.getColumnIndex(CompanyEntry.COLUMN_NAME));
                String discountDescription = data.getString(data.getColumnIndex(DiscountEntry.COLUMN_DESCRIPTION));
                String discountTitle = data.getString(data.getColumnIndex(DiscountEntry.COLUMN_TITLE));
                int discountImageIndex = data.getInt(data.getColumnIndex(DiscountEntry.COLUMN_IMAGE_INDEX));
                String companyImageURL = data.getString(data.getColumnIndex(CompanyEntry.COLUMN_IMAGE_URL));

                String imageName = "img_" + discountImageIndex + "_350_150";

                Glide.with(mContext).load(mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName())).into(imageView);
                Glide.with(mContext).load(companyImageURL).into(imageView2);

                companyTextView.setText(companyName);
                descrTextView.setText(discountDescription);
                titleTextView.setText(discountTitle);
                break;

            case 1:

                if(data.getCount() == 0) {
                    savedByUser = false;
                    fab.setImageResource(R.drawable.ic_archive_white_24dp);
                }
                else {
                    savedByUser = true;
                    fab.setImageResource(R.drawable.ic_delete_sweep_white_24dp);
                }
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void setToolbar() {

        //TODO The back toolbar isn't working
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Back clicked", Toast.LENGTH_LONG).show();
                onBackPressed();
                finish();
            }
        });
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void archiveFABCLicked() {
        if(savedByUser) {
            savedByUser = false;
            fab.setImageResource(R.drawable.ic_archive_white_24dp);
            getContentResolver().delete(SavedDiscountEntry.CONTENT_URI, SavedDiscountEntry.COLUMN_DISCOUNT_ID + " = " + mID, null);
        }
        else {
            savedByUser = true;
            fab.setImageResource(R.drawable.ic_delete_sweep_white_24dp);

            long timeInMillis = System.currentTimeMillis();

            SavedDiscount savedDiscount = new SavedDiscount();
            savedDiscount.setFbacctFbId(fb_id);
            savedDiscount.setDiscountId(mID);
            savedDiscount.setSdiscCreatedTime(timeInMillis);
            savedDiscount.setSdiscUpdatedTime(timeInMillis);

            getContentResolver().insert(SavedDiscountEntry.CONTENT_URI, SavedDiscountEntry.getContentValues(savedDiscount));
        }
    }


}
