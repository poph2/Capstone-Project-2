package com.pop.pricecutz.fragments;

/**
 * Created by Pop H2 on 8/25/2016.
 * Pop Inc
 * Lagos Nigeria
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.pop.pricecutz.R;
import com.pop.pricecutz.activities.CompanyActivity;
import com.pop.pricecutz.adapters.CompanyListAdapter;
import com.pop.pricecutz.entities.Company;
import com.pop.pricecutz.entities.Randomizer;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment implements AdapterView.OnItemClickListener {

    CompanyListAdapter mCompanyListAdapter;

    ArrayList<Company> mCompanyArrayList;

    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        mCompanyArrayList = Randomizer.getCompanies(10);

        mCompanyListAdapter = new CompanyListAdapter(
                getActivity(),
                R.layout.fragment_favorites_list_item,
                R.id.fragment_favorites_list_item_imageview,
                R.id.fragment_favorites_list_item_textview,
                mCompanyArrayList);

        //mCompanyListAdapter = new CompanyListAdapter(getContext(), R.layout.fragment_favorites_list_item, mCompanyArrayList);

        ListView listView = (ListView) root.findViewById(R.id.fragment_favorites_listview);
        listView.setAdapter(mCompanyListAdapter);

        listView.setOnItemClickListener(this);

        mContext = getContext();

//        Toast.makeText(mContext, Integer.toString(mCompanyArrayList.size()), Toast.LENGTH_LONG).show();

        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Company c = mCompanyListAdapter.getItem(i);

        Intent intent = new Intent(mContext, CompanyActivity.class);
        intent.putExtra("company", new Gson().toJson(c));
        startActivity(intent);

//        Toast.makeText(mContext, Integer.toString(i), Toast.LENGTH_LONG).show();
    }
}