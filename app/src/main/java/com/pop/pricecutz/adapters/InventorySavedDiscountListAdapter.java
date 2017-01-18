package com.pop.pricecutz.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

import com.bumptech.glide.Glide;
import com.pop.pricecutz.data.entries.DiscountEntry;

/**
 * Created by Pop H2 on 9/12/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class InventorySavedDiscountListAdapter extends SimpleCursorAdapter {

    private static final String LOG_TAG = InventorySavedDiscountListAdapter.class.getSimpleName();

    private final LayoutInflater mInflater;

    private Context mContext;

    final int layoutID;
    final int imageViewID;

    public InventorySavedDiscountListAdapter(Context context, int layoutResourceID, Cursor c, String[] from, int[] to, int flags, int imageResourceID) {
        super(context, layoutResourceID, c, from, to, flags);

        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);

        layoutID = layoutResourceID;
        imageViewID = imageResourceID;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);

        View v = view;

        ViewHolder viewHolder = new ViewHolder(v, imageViewID);

        v.setTag(imageViewID, v.findViewById(imageViewID));

        int columnIndex = cursor.getColumnIndex(DiscountEntry.COLUMN_IMAGE_INDEX);

        int imageIndex = cursor.getInt(columnIndex);

        String imageName = "img_" + imageIndex + "_350_150";

        Glide.with(mContext).load(mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName())).into(viewHolder.imageView);
    }

    static class ViewHolder {

        ImageView imageView;

        public ViewHolder(View view, int imageViewID) {
            imageView   = (ImageView) view.findViewById(imageViewID);
        }
    }
}
