package com.pop.pricecutz.adapters;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pop.pricecutz.Discount;
import com.pop.pricecutz.data.entries.CompanyEntry;
import com.pop.pricecutz.data.entries.DiscountEntry;

import java.util.ArrayList;

/**
 * Created by Pop H2 on 9/12/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class DiscountListAdapter2 extends SimpleCursorAdapter {

    private static final String LOG_TAG = DiscountListAdapter2.class.getSimpleName();

//    private ArrayList<Discount> mDiscountArrayList;

    private final LayoutInflater mInflater;

    private Context mContext;

    final int layoutID;
    final int imageViewID;
    final int imageView2ID;
//    final int textViewID;
//    final int textView2ID;

    public DiscountListAdapter2(Context context, int layoutResourceID, Cursor c, String[] from, int[] to, int flags, int imageResourceID, int image2ResourceID) {
        super(context, layoutResourceID, c, from, to, flags);

        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);

        layoutID = layoutResourceID;
        imageViewID = imageResourceID;
        imageView2ID = image2ResourceID;
//        textViewID = textResourceID;
//        textView2ID = text2ResourceID;

        Log.d(LOG_TAG, "Got here");
    }

//    @Override
//    public void bindView(View view, Context context, Cursor cursor) {
//        ViewHolder viewHolder = new ViewHolder(view, imageViewID, imageView2ID);
//
//        //Log.d("DiscountListAdapter", company.getImageURL());
//
//        int columnIndex = cursor.getColumnIndex(DiscountEntry.COLUMN_IMAGE_INDEX);
//
//        int imageIndex = cursor.getInt(columnIndex);
//
//        String imageName = "img_" + imageIndex + "_350_150";
//
////        String image = mContext.getResources().getIdentifier("img_96_350_150.jpg", "drawable", mContext.getPackageName());
//
//        //Log.d("DiscountListAdapter2", cursor.getColumnIndex(DiscountEntry.COLUMN_CODE));
//        Log.d(LOG_TAG, "bindView - " + imageName);
////
////        //Glide.with(getContext()).load(company.getImageURL()).into(viewHolder.imageView);
//        Glide.with(mContext).load(mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName())).into(viewHolder.imageView);
////        Glide.with(mContext).load(company.getImageURL()).into(viewHolder.imageView2);
////        viewHolder.textView.setText(company.getName());
////        viewHolder.text2View.setText(discount.getTitle());
//    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(layoutID, parent, false);

        ViewHolder viewHolder = new ViewHolder(v, imageViewID, imageView2ID);

        v.setTag(imageViewID, v.findViewById(imageViewID));
        v.setTag(imageView2ID, v.findViewById(imageView2ID));

        int columnIndex = cursor.getColumnIndex(DiscountEntry.COLUMN_IMAGE_INDEX);

        int imageIndex = cursor.getInt(columnIndex);

        String imageName = "img_" + imageIndex + "_350_150";

        String coyImageURL = cursor.getString(cursor.getColumnIndex(CompanyEntry.COLUMN_IMAGE_URL));

//        String image = mContext.getResources().getIdentifier("img_96_350_150.jpg", "drawable", mContext.getPackageName());

        //Log.d("DiscountListAdapter2", cursor.getColumnIndex(DiscountEntry.COLUMN_CODE));
        Log.d("DiscountListAdapter2", imageName);
//
//        //Glide.with(getContext()).load(company.getImageURL()).into(viewHolder.imageView);
        Glide.with(mContext).load(mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName())).into(viewHolder.imageView);
        Glide.with(mContext).load(coyImageURL).into(viewHolder.imageView2);

        return v;
    }

    static class ViewHolder {
        ImageView imageView;
        ImageView imageView2;
//        TextView textView;
//        TextView text2View;

        public ViewHolder(View view, int imageViewID, int imageView2ID) {
            imageView   = (ImageView) view.findViewById(imageViewID);
            imageView2   = (ImageView) view.findViewById(imageView2ID);
//            textView    = (TextView) view.findViewById(textViewID);
//            text2View    = (TextView) view.findViewById(text2ViewID);
        }
    }
}
