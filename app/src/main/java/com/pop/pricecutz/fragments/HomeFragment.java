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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pop.pricecutz.R;
import com.pop.pricecutz.adapters.DiscountListAdapter;
import com.pop.pricecutz.entities.Discount;
import com.pop.pricecutz.entities.Randomizer;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    DiscountListAdapter mDiscountListAdapter;

    ArrayList<Discount> mDiscountArrayList;

    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getContext();

        if(savedInstanceState == null) {
            mDiscountArrayList = Randomizer.getDiscounts(20);
        }
        else {
            Gson gson = new Gson();

            String mDiscountArrayListJsonStr = savedInstanceState.getString("DiscountArrayList");

            Type arrayType = new TypeToken<ArrayList<Discount>>(){}.getType();
            mDiscountArrayList = (ArrayList<Discount>) gson.fromJson(mDiscountArrayListJsonStr, arrayType);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mDiscountListAdapter = new DiscountListAdapter(
                mContext,
                R.layout.fragment_home_list_items,
                R.id.fragment_home_list_item_imageview,
                R.id.fragment_home_list_item_textview,
                R.id.fragment_home_list_item_textview2,
                mDiscountArrayList);

        //mCompanyListAdapter = new CompanyListAdapter(getContext(), R.layout.fragment_favorites_list_item, mCompanyArrayList);

        ListView listView = (ListView) root.findViewById(R.id.fragment_home_listview);
        listView.setAdapter(mDiscountListAdapter);

        listView.setOnItemClickListener(this);

//        Toast.makeText(mContext, Integer.toString(mCompanyArrayList.size()), Toast.LENGTH_LONG).show();

        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("DiscountArrayList", new Gson().toJson(mDiscountArrayList));
    }
}