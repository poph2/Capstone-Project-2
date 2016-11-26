package com.pop.pricecutz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pop.pricecutz.entities.Company;

import java.util.ArrayList;

/**
 * Created by Pop H2 on 9/12/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class CompanyListAdapter extends ArrayAdapter<Company> {

    private ArrayList<Company> mCompanyArrayList;

    private final LayoutInflater mInflater;

    final int layoutID;
    final int imageViewID;
    final int textViewID;

    public CompanyListAdapter(Context context, int layoutResourceID, int imageResourceID, int textResourceID, ArrayList<Company> companyArrayList) {
        super(context, layoutResourceID, textResourceID, companyArrayList);

        layoutID = layoutResourceID;
        imageViewID = imageResourceID;
        textViewID = textResourceID;

        mInflater = LayoutInflater.from(context);
        mCompanyArrayList = companyArrayList;
    }

    @Override
    public void clear() {
        super.clear();
        mCompanyArrayList.clear();
    }

    public void addAll(ArrayList<Company> itemList) {
        super.addAll();
        mCompanyArrayList =  itemList;

    }

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

        Company company = getItem(i);

        ViewHolder viewHolder = new ViewHolder(v, imageViewID, textViewID);

        Glide.with(getContext()).load(company.getImageURL()).into(viewHolder.imageView);
        viewHolder.textView.setText(company.getName());

        return v;
    }

    static class ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View view, int imageViewID, int textViewID) {
            imageView   = (ImageView) view.findViewById(imageViewID);
            textView    = (TextView) view.findViewById(textViewID);
//            imageView   = (ImageView) view.findViewById(R.id.fragment_favorites_list_item_imageview);
//            textView    = (TextView) view.findViewById(R.id.fragment_favorites_list_item_textview);
        }
    }

    public ArrayList<Company> getItems() {
        return mCompanyArrayList;
    }
}
