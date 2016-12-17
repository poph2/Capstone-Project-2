package com.pop.pricecutz.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pop.pricecutz.Category;
import com.pop.pricecutz.Company;
import com.pop.pricecutz.Discount;

import java.util.ArrayList;

/**
 * Created by Pop H2 on 9/12/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class CategoryPreferenceGridAdapter extends ArrayAdapter<Category> {

    private ArrayList<Category> mCategoryArrayList;

    private final LayoutInflater mInflater;

    final int layoutID;
    final int imageViewID;
    final int textViewID;

    public CategoryPreferenceGridAdapter(Context context, int layoutResourceID, int imageResourceID, int textResourceID, ArrayList<Category> categoryArrayList) {
        super(context, layoutResourceID, textResourceID, categoryArrayList);

        layoutID = layoutResourceID;
        imageViewID = imageResourceID;
        textViewID = textResourceID;

        mInflater = LayoutInflater.from(context);
        mCategoryArrayList = categoryArrayList;
    }

    @Override
    public void clear() {
        super.clear();
        mCategoryArrayList.clear();
    }

    public void addAll(ArrayList<Category> itemList) {
        super.addAll();
        mCategoryArrayList =  itemList;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (v == null) {
            v = mInflater.inflate(layoutID, viewGroup, false);
            v.setTag(imageViewID, v.findViewById(imageViewID));
            v.setTag(textViewID, v.findViewById(textViewID));
        }

        Category category = getItem(i);

        ViewHolder viewHolder = new ViewHolder(v, imageViewID, textViewID);

        String imageName = "img_" + category.getImageIndex() + "_200_200";

        Glide.with(getContext()).load(getContext().getResources().getIdentifier(imageName, "drawable", getContext().getPackageName())).into(viewHolder.imageView);
        viewHolder.textView.setText(category.getName());

        return v;
    }

    static class ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(View view, int imageViewID, int textViewID) {
            imageView   = (ImageView) view.findViewById(imageViewID);
            textView    = (TextView) view.findViewById(textViewID);
        }
    }

    public ArrayList<Category> getItems() {
        return mCategoryArrayList;
    }
}
