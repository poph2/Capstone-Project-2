package com.pop.pricecutz.fragments;

/**
 * Created by Pop H2 on 8/25/2016.
 * Pop Inc
 * Lagos Nigeria
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pop.pricecutz.Discount;
import com.pop.pricecutz.R;
import com.pop.pricecutz.Randomizer;
import com.pop.pricecutz.adapters.DiscountListAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    DiscountListAdapter mDiscountListAdapter;

    ArrayList<Discount> mDiscountArrayList;

    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mDiscountArrayList = Randomizer.getDiscounts(7);

        mDiscountListAdapter = new DiscountListAdapter(
                getActivity(),
                R.layout.fragment_home_list_items,
                R.id.fragment_home_list_item_imageview,
                R.id.fragment_home_list_item_textview,
                R.id.fragment_home_list_item_textview2,
                mDiscountArrayList);

        //mCompanyListAdapter = new CompanyListAdapter(getContext(), R.layout.fragment_favorites_list_item, mCompanyArrayList);

        ListView listView = (ListView) root.findViewById(R.id.fragment_home_listview);
        listView.setAdapter(mDiscountListAdapter);

        listView.setOnItemClickListener(this);

        mContext = getContext();

//        Toast.makeText(mContext, Integer.toString(mCompanyArrayList.size()), Toast.LENGTH_LONG).show();

        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}