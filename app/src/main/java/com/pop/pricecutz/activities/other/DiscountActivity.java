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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.pop.pricecutz.R;
import com.pop.pricecutz.backend.savedDiscountApi.model.SavedDiscount;
import com.pop.pricecutz.data.entries.CompanyEntry;
import com.pop.pricecutz.data.entries.DiscountEntry;
import com.pop.pricecutz.data.entries.SavedDiscountEntry;


public class DiscountActivity extends AppCompatActivity {

    Context mContext;

    long mID;

    ImageView imageView, imageView2;
    TextView companyTextView, descrTextView, titleTextView;

    boolean savedByUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        setToolbar();

        FacebookSdk.sdkInitialize(this.getApplicationContext());

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

        getLoaderManager().initLoader(0, null, new DiscountLoaderCallBack());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new );
////        if(d.isSavedByUser()) {
//            fab.setImageResource(R.drawable.ic_delete_sweep_white_24dp);
////        }
////        else {
////            fab.setImageResource(R.drawable.ic_archive_white_24dp);
////        }
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

    class DiscountLoaderCallBack implements LoaderManager.LoaderCallbacks<Cursor> {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new CursorLoader(
                    mContext,
                    DiscountEntry.CONTENT_URI_WITH_COMPANY,
                    null,
                    "discount._id = " + mID,
                    null,
                    null
            );
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

            data.moveToFirst();

            String companyName          = data.getString(data.getColumnIndex(CompanyEntry.COLUMN_NAME));
            String discountDescription  = data.getString(data.getColumnIndex(DiscountEntry.COLUMN_DESCRIPTION));
            String discountTitle        = data.getString(data.getColumnIndex(DiscountEntry.COLUMN_TITLE));
            int discountImageIndex      = data.getInt(data.getColumnIndex(DiscountEntry.COLUMN_IMAGE_INDEX));
            String companyImageURL      = data.getString(data.getColumnIndex(CompanyEntry.COLUMN_IMAGE_URL));

            String imageName = "img_" + discountImageIndex + "_350_150";

            Glide.with(mContext).load(mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName())).into(imageView);
            Glide.with(mContext).load(companyImageURL).into(imageView2);

            companyTextView.setText(companyName);
            descrTextView.setText(discountDescription);
            titleTextView.setText(discountTitle);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    }

    class SavedDiscountLoaderCallBack implements LoaderManager.LoaderCallbacks<Cursor> {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {

            AccessToken accessToken = AccessToken.getCurrentAccessToken();

            return new CursorLoader(
                    mContext,
                    SavedDiscountEntry.CONTENT_URI,
                    null,
                    "discount._id = " + mID,
                    null,
                    null
            );
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

            data.moveToFirst();

            String companyName          = data.getString(data.getColumnIndex(CompanyEntry.COLUMN_NAME));
            String discountDescription  = data.getString(data.getColumnIndex(DiscountEntry.COLUMN_DESCRIPTION));
            String discountTitle        = data.getString(data.getColumnIndex(DiscountEntry.COLUMN_TITLE));
            int discountImageIndex      = data.getInt(data.getColumnIndex(DiscountEntry.COLUMN_IMAGE_INDEX));
            String companyImageURL      = data.getString(data.getColumnIndex(CompanyEntry.COLUMN_IMAGE_URL));

            String imageName = "img_" + discountImageIndex + "_350_150";

            Glide.with(mContext).load(mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName())).into(imageView);
            Glide.with(mContext).load(companyImageURL).into(imageView2);

            companyTextView.setText(companyName);
            descrTextView.setText(discountDescription);
            titleTextView.setText(discountTitle);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    }



}
