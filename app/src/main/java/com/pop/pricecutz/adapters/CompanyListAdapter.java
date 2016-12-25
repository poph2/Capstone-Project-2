package com.pop.pricecutz.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.pop.pricecutz.R;
import com.pop.pricecutz.data.entries.CompanyEntry;
import com.squareup.picasso.Picasso;

/**
 * Created by Pop H2 on 9/12/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class CompanyListAdapter extends SimpleCursorAdapter {

    //private ArrayList<Company> mCompanyArrayList;

    private final LayoutInflater mInflater;

    private Context mContext;

    final int layoutID;
    final int imageViewID;
    final int textViewID;

    public CompanyListAdapter(Context context, int layoutID, Cursor c, String[] from, int[] to, int flags, int imageViewID, int textViewID) {

        super(context, layoutID, c, from, to, flags);

        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.layoutID = layoutID;
        this.imageViewID = imageViewID;
        this.textViewID = textViewID;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        Cursor c = cursor; //getCursor();

        final LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(layoutID, parent, false);
        v.setTag(imageViewID, v.findViewById(imageViewID));
        v.setTag(textViewID, v.findViewById(textViewID));

        ViewHolder viewHolder = new ViewHolder(v, imageViewID, textViewID);

        //viewHolder.textView.getText();
        //viewHolder.textView.setText(c.getColumnIndex(CompanyEntry.COLUMN_IMAGE_URL));
//        Glide.with(mContext).load(c.getString(c.getColumnIndex(CompanyEntry.COLUMN_IMAGE_URL))).into(viewHolder.imageView);
        Picasso.with(mContext)
                .load(c.getString(c.getColumnIndex(CompanyEntry.COLUMN_IMAGE_URL)))
                .placeholder(R.drawable.ic_my_location_black_24dp)
                .into(viewHolder.imageView);

        viewHolder.textView.setText(c.getString(c.getColumnIndex(CompanyEntry.COLUMN_IMAGE_URL)));

        return v;
    }

    static class ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View view, int imageViewID, int textViewID) {
            imageView   = (ImageView) view.findViewById(imageViewID);
            textView   = (TextView) view.findViewById(textViewID);
        }
    }

}
