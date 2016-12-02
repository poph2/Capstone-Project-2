package com.pop.pricecutz.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pop.pricecutz.Company;
import com.pop.pricecutz.data.entries.CompanyEntry;

import java.util.ArrayList;

/**
 * Created by Pop H2 on 9/12/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class CompanyListAdapter2 extends SimpleCursorAdapter {

    //private ArrayList<Company> mCompanyArrayList;

    private final LayoutInflater mInflater;

    private Context mContext;

    final int layoutID;
    final int imageViewID;
    final int textViewID;

    public CompanyListAdapter2(Context context, int layoutID, Cursor c, String[] from, int[] to, int flags, int imageViewID, int textViewID) {

        super(context, layoutID, c, from, to, flags);

        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.layoutID = layoutID;
        this.imageViewID = imageViewID;
        this.textViewID = textViewID;
    }

//    public CompanyListAdapter2(Context context, int layoutResourceID, int imageResourceID, int textResourceID, ArrayList<Company> companyArrayList) {
//        super(context, layoutResourceID, textResourceID, companyArrayList);
//
//        layoutID = layoutResourceID;
//        imageViewID = imageResourceID;
//        textViewID = textResourceID;
//
//        mInflater = LayoutInflater.from(context);
//        //mCompanyArrayList = companyArrayList;
//    }

//    @Override
//    public void clear() {
//        super.clear();
//        mCompanyArrayList.clear();
//    }

//    public void addAll(ArrayList<Company> itemList) {
//        super.addAll();
//        mCompanyArrayList =  itemList;
//
//    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (v == null) {
            v = mInflater.inflate(layoutID, viewGroup, false);
            v.setTag(imageViewID, v.findViewById(imageViewID));
            v.setTag(textViewID, v.findViewById(textViewID));
//            v = mInflater.inflate(R.layout.fragment_favorites_list_item, viewGroup, false);
//            v.setTag(R.id.fragment_favorites_list_item_imageview, v.findViewById(R.id.fragment_favorites_list_item_imageview));
//            v.setTag(R.id.fragment_favorites_list_item_textview, v.findViewById(R.id.fragment_favorites_list_item_textview));
        }

        Cursor c = getCursor();
        //Company company = getItem(i);

        ViewHolder viewHolder = new ViewHolder(v, imageViewID, textViewID);

        //Toast.makeText(mContext, "Col count - " + c.getColumnCount(), Toast.LENGTH_LONG).show();
        //Toast.makeText(mContext, "Col name - " + c.getColumnName(4), Toast.LENGTH_LONG).show();
        //Toast.makeText(mContext, "col index - " + c.getColumnIndex(CompanyEntry.COLUMN_IMAGE_URL), Toast.LENGTH_LONG).show();
        Toast.makeText(mContext, "Val - " + c.getString(4), Toast.LENGTH_LONG).show();

        //Glide.with(mContext).load(c.getString(c.getColumnIndex(CompanyEntry.COLUMN_IMAGE_URL))).into(viewHolder.imageView);
        //viewHolder.textView.setText(company.getName());

        return v;
    }

    static class ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View view, int imageViewID, int textViewID) {
            imageView   = (ImageView) view.findViewById(imageViewID);
            textView    = (TextView) view.findViewById(textViewID);
        }
    }

}
