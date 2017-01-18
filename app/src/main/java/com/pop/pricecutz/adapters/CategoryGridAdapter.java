package com.pop.pricecutz.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

import com.bumptech.glide.Glide;
import com.pop.pricecutz.data.entries.CategoryEntry;
import com.pop.pricecutz.data.entries.CompanyEntry;
import com.pop.pricecutz.data.entries.DiscountEntry;

/**
 * Created by Pop H2 on 9/12/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class CategoryGridAdapter extends SimpleCursorAdapter {

    private static final String LOG_TAG = CategoryGridAdapter.class.getSimpleName();

    private final LayoutInflater mInflater;

    private Context mContext;

    final int layoutID;
    final int imageViewID;

    public CategoryGridAdapter(Context context, int layoutResourceID, Cursor c, String[] from, int[] to, int flags, int imageResourceID) {
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

        int columnIndex = cursor.getColumnIndex(CategoryEntry.COLUMN_IMAGE_INDEX);

        int imageIndex = cursor.getInt(columnIndex);

        String imageName = "img_" + imageIndex + "_350_150";

//        String coyImageURL = cursor.getString(cursor.getColumnIndex(CompanyEntry.COLUMN_IMAGE_URL));

        Glide.with(mContext).load(mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName())).into(viewHolder.imageView);
//        Glide.with(mContext).load(coyImageURL).into(viewHolder.imageView2);

    }

//    @Override
//    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        final LayoutInflater inflater = LayoutInflater.from(context);
//        View v = inflater.inflate(layoutID, parent, false);
//        return v;
//    }


    static class ViewHolder {

        ImageView imageView;

        public ViewHolder(View view, int imageViewID) {
            imageView   = (ImageView) view.findViewById(imageViewID);
        }
    }
}
