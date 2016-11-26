package com.pop.pricecutz.activities.main.fragments;

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
import com.google.gson.reflect.TypeToken;
import com.pop.pricecutz.Company;
import com.pop.pricecutz.R;
import com.pop.pricecutz.Randomizer;
import com.pop.pricecutz.activities.company.CompanyActivity;
import com.pop.pricecutz.adapters.CompanyListAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FavoriteFragment extends Fragment implements AdapterView.OnItemClickListener {

    CompanyListAdapter mCompanyListAdapter;

    ArrayList<Company> mCompanyArrayList;

    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getContext();

        if(savedInstanceState == null) {
            mCompanyArrayList = Randomizer.getCompanies(10);
        }
        else {
            Gson gson = new Gson();

            String mCompanyArrayListJsonStr = savedInstanceState.getString("CompanyArrayList");

            Type arrayType = new TypeToken<ArrayList<Company>>(){}.getType();
            mCompanyArrayList = (ArrayList<Company>) gson.fromJson(mCompanyArrayListJsonStr, arrayType);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("DiscountArrayList", new Gson().toJson(mCompanyArrayList));
    }
}