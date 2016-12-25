package com.pop.pricecutz.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

import com.bumptech.glide.Glide;
import com.pop.pricecutz.data.entries.CompanyEntry;
import com.pop.pricecutz.data.entries.DiscountEntry;

/**
 * Created by Pop H2 on 9/12/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class HomeDiscountListAdapter extends SimpleCursorAdapter {

    private static final String LOG_TAG = HomeDiscountListAdapter.class.getSimpleName();

    private final LayoutInflater mInflater;

    private Context mContext;

    final int layoutID;
    final int imageViewID;
    final int imageView2ID;

    public HomeDiscountListAdapter(Context context, int layoutResourceID, Cursor c, String[] from, int[] to, int flags, int imageResourceID, int image2ResourceID) {
        super(context, layoutResourceID, c, from, to, flags);

        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);

        layoutID = layoutResourceID;
        imageViewID = imageResourceID;
        imageView2ID = image2ResourceID;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);

        View v = view;

        ViewHolder viewHolder = new ViewHolder(v, imageViewID, imageView2ID);

        v.setTag(imageViewID, v.findViewById(imageViewID));
        v.setTag(imageView2ID, v.findViewById(imageView2ID));

        int columnIndex = cursor.getColumnIndex(DiscountEntry.COLUMN_IMAGE_INDEX);

        int imageIndex = cursor.getInt(columnIndex);

        String imageName = "img_" + imageIndex + "_350_150";

        String coyImageURL = cursor.getString(cursor.getColumnIndex(CompanyEntry.COLUMN_IMAGE_URL));

        Glide.with(mContext).load(mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName())).into(viewHolder.imageView);
        Glide.with(mContext).load(coyImageURL).into(viewHolder.imageView2);

    }

//    @Override
//    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        final LayoutInflater inflater = LayoutInflater.from(context);
//        View v = inflater.inflate(layoutID, parent, false);
//        return v;
//    }


    static class ViewHolder {

        ImageView imageView;
        ImageView imageView2;

        public ViewHolder(View view, int imageViewID, int imageView2ID) {
            imageView   = (ImageView) view.findViewById(imageViewID);
            imageView2   = (ImageView) view.findViewById(imageView2ID);
        }
    }
}
