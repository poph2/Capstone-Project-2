package com.pop.pricecutz.activities.other;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pop.pricecutz.Discount;
import com.pop.pricecutz.R;
import com.pop.pricecutz.Randomizer;

public class DiscountActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        setToolbar();

        mContext = getBaseContext();



        Discount d = Randomizer.getDiscounts(1).get(0);

        ImageView imageView = (ImageView) findViewById(R.id.discountBannerImageView);
        ImageView imageView2 = (ImageView) findViewById(R.id.companyLogoImageView);

        TextView companyTextView = (TextView) findViewById(R.id.discountCompanyTextView);
        TextView descrTextView = (TextView) findViewById(R.id.discountDescriptionTextView);
        TextView titleTextView = (TextView) findViewById(R.id.discountTitleTextView);

        String imageName = "img_" + d.getImageIndex() + "_350_150";

        Glide.with(mContext).load(mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName())).into(imageView);
        Glide.with(mContext).load(d.getCompany().getImageURL()).into(imageView2);

        companyTextView.setText(d.getCompany().getName());
        descrTextView.setText(d.getDescription());
        titleTextView.setText(d.getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(d.isSavedByUser()) {
            fab.setImageResource(R.drawable.ic_delete_sweep_white_24dp);
        }
        else {
            fab.setImageResource(R.drawable.ic_archive_white_24dp);
        }
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

}
