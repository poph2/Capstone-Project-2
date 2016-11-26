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
import com.pop.pricecutz.entities.Discount;

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
    final int textViewID;
    final int text2ViewID;

    public DiscountListAdapter(Context context, int layoutResourceID, int imageResourceID, int textResourceID, int text2ResourceID, ArrayList<Discount> discountArrayList) {
        super(context, layoutResourceID, textResourceID, discountArrayList);

        layoutID = layoutResourceID;
        imageViewID = imageResourceID;
        textViewID = textResourceID;
        text2ViewID = text2ResourceID;

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
            v.setTag(textViewID, v.findViewById(textViewID));
            v.setTag(text2ViewID, v.findViewById(text2ViewID));
//            v = mInflater.inflate(R.layout.fragment_favorites_list_item, viewGroup, false);
//            v.setTag(R.id.fragment_favorites_list_item_imageview, v.findViewById(R.id.fragment_favorites_list_item_imageview));
//            v.setTag(R.id.fragment_favorites_list_item_textview, v.findViewById(R.id.fragment_favorites_list_item_textview));
        }

        Discount discount = getItem(i);
        Company company = discount.getCompany();

        ViewHolder viewHolder = new ViewHolder(v, imageViewID, textViewID, text2ViewID);

        Glide.with(getContext()).load(company.getImageURL()).into(viewHolder.imageView);
        viewHolder.textView.setText(discount.getTitle());
        viewHolder.text2View.setText(discount.getTypeStr());

        return v;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView text2View;

        public ViewHolder(View view, int imageViewID, int textViewID, int text2ViewID) {
            imageView   = (ImageView) view.findViewById(imageViewID);
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
