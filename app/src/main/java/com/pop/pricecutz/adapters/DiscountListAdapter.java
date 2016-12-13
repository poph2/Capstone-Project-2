package com.pop.pricecutz.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pop.pricecutz.Company;
import com.pop.pricecutz.Discount;
import com.pop.pricecutz.R;

import java.util.ArrayList;

/**
 * Created by Pop H2 on 9/12/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class DiscountListAdapter extends ArrayAdapter<Discount> {

    private ArrayList<Discount> mDiscountArrayList;

    private final LayoutInflater mInflater;

    final int layoutID;
    final int imageViewID;
    final int imageView2ID;
    final int textViewID;
    final int textView2ID;

    public DiscountListAdapter(Context context, int layoutResourceID, int imageResourceID, int image2ResourceID, int textResourceID, int text2ResourceID, ArrayList<Discount> discountArrayList) {
        super(context, layoutResourceID, textResourceID, discountArrayList);

        layoutID = layoutResourceID;
        imageViewID = imageResourceID;
        imageView2ID = image2ResourceID;
        textViewID = textResourceID;
        textView2ID = text2ResourceID;

        mInflater = LayoutInflater.from(context);
        mDiscountArrayList = discountArrayList;
    }

    @Override
    public void clear() {
        super.clear();
        mDiscountArrayList.clear();
    }

    public void addAll(ArrayList<Discount> itemList) {
        super.addAll();
        mDiscountArrayList =  itemList;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (v == null) {
            v = mInflater.inflate(layoutID, viewGroup, false);
            v.setTag(imageViewID, v.findViewById(imageViewID));
            v.setTag(imageView2ID, v.findViewById(imageView2ID));
            v.setTag(textViewID, v.findViewById(textViewID));
            v.setTag(textView2ID, v.findViewById(textView2ID));
//            v = mInflater.inflate(R.layout.fragment_favorites_list_item, viewGroup, false);
//            v.setTag(R.id.fragment_favorites_list_item_imageview, v.findViewById(R.id.fragment_favorites_list_item_imageview));
//            v.setTag(R.id.fragment_favorites_list_item_textview, v.findViewById(R.id.fragment_favorites_list_item_textview));
        }

        Discount discount = getItem(i);
        Company company = discount.getCompany();

        ViewHolder viewHolder = new ViewHolder(v, imageViewID, imageView2ID, textViewID, textView2ID);

        Log.d("DiscountListAdapter", company.getImageURL());

        String imageName = "img_" + discount.getImageIndex() + "_350_150";

        //String image = getContext().getResources().getIdentifier("img_96_350_150.jpg", "drawable", getContext().getPackageName());

        //Glide.with(getContext()).load(company.getImageURL()).into(viewHolder.imageView);
        Glide.with(getContext()).load(getContext().getResources().getIdentifier(imageName, "drawable", getContext().getPackageName())).into(viewHolder.imageView);
        Glide.with(getContext()).load(company.getImageURL()).into(viewHolder.imageView2);
        viewHolder.textView.setText(company.getName());
        viewHolder.text2View.setText(discount.getTitle());

        return v;
    }

    static class ViewHolder {
        ImageView imageView;
        ImageView imageView2;
        TextView textView;
        TextView text2View;

        public ViewHolder(View view, int imageViewID, int imageView2ID, int textViewID, int text2ViewID) {
            imageView   = (ImageView) view.findViewById(imageViewID);
            imageView2   = (ImageView) view.findViewById(imageView2ID);
            textView    = (TextView) view.findViewById(textViewID);
            text2View    = (TextView) view.findViewById(text2ViewID);
//            imageView   = (ImageView) view.findViewById(R.id.fragment_favorites_list_item_imageview);
//            textView    = (TextView) view.findViewById(R.id.fragment_favorites_list_item_textview);
        }
    }

    public ArrayList<Discount> getItems() {
        return mDiscountArrayList;
    }
}
