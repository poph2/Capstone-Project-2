package com.pop.pricecutz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.pop.pricecutz.R;
import com.pop.pricecutz.utils.SquareImageView;

import java.util.ArrayList;

/**
 * Created by adeniyi.bello on 11/26/2016.
 */

public class CategoryGridAdapter { //extends ArrayAdapter<Category> {

//    private ArrayList<Category> mCategoryArrayList;
//
//    private final LayoutInflater mInflater;
//
//    final int layoutID;
//    final int imageViewID;
//    final int imageView2ID;
//    final int textViewID;
//
//    public CategoryGridAdapter(Context context, int layoutResourceID, int imageResourceID, int imageResource2ID, int textResourceID, ArrayList<Category> categoryArrayList) {
//        super(context, layoutResourceID, textResourceID, categoryArrayList);
//
//        layoutID        = layoutResourceID;
//        imageViewID     = imageResourceID;
//        imageView2ID    = imageResource2ID;
//        textViewID      = textResourceID;
//
//        mInflater = LayoutInflater.from(context);
//        mCategoryArrayList = categoryArrayList;
//    }
//
//    @Override
//    public void clear() {
//        super.clear();
//        mCategoryArrayList.clear();
//    }
//
//    public void addAll(ArrayList<Category> itemList) {
//        super.addAll();
//        mCategoryArrayList =  itemList;
//
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        View v = view;
//
//        if (v == null) {
//            v = mInflater.inflate(layoutID, viewGroup, false);
//            v.setTag(imageViewID, v.findViewById(imageViewID));
//            v.setTag(imageView2ID, v.findViewById(imageView2ID));
//            v.setTag(textViewID, v.findViewById(textViewID));
//        }
//
//        Category category = getItem(i);
//
//        ViewHolder viewHolder = new ViewHolder(v, imageViewID, imageView2ID, textViewID);
//
//        String imageName = "img_" + category.getImageIndex() + "_350_150";
//        int imageResID = getContext().getResources().getIdentifier(imageName, "drawable", getContext().getPackageName());
//
//        viewHolder.imageView.setImageResource(imageResID);
//        //viewHolder.imageView2.setImageResource(R.drawable.ic_check_circle_black_24dp);
//
////        Glide.with(getContext()).load(imageResID).into(viewHolder.imageView2);
//        viewHolder.textView.setText(category.getName());
//
////        if(category.isSelected()) {
//////            viewHolder.imageView.setImageResource(imageResID);
////            viewHolder.imageView2.setImageResource(R.drawable.ic_check_circle_black_24dp);
//////            Glide.with(getContext())
//////                    .load(R.drawable.ic_check_circle_black_24dp)
//////                    .override(50, 50)
//////                    .into(viewHolder.imageView2);
////        }
////        else {
////            //viewHolder.imageView2.set
////            //Glide.with(getContext()).load(null).into(viewHolder.imageView);
////        }
//
//        return v;
//    }
//
//    static class ViewHolder {
//        TextView textView;
//        SquareImageView imageView;
//        SquareImageView imageView2;
//        RadioButton radioButton;
//
//        public ViewHolder(View view, int imageViewID, int imageView2ID, int textViewID) {
//            imageView   = (SquareImageView) view.findViewById(imageViewID);
//            imageView2   = (SquareImageView) view.findViewById(imageView2ID);
//            textView    = (TextView) view.findViewById(textViewID);
//        }
//    }
//
//    public ArrayList<Category> getItems() {
//        return mCategoryArrayList;
//    }
}
