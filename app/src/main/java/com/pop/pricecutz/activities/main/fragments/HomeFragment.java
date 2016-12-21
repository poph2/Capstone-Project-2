package com.pop.pricecutz.activities.main.fragments;

/**
 * Created by Pop H2 on 8/25/2016.
 * Pop Inc
 * Lagos Nigeria
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pop.pricecutz.Company;
import com.pop.pricecutz.Discount;
import com.pop.pricecutz.R;
import com.pop.pricecutz.Randomizer;
import com.pop.pricecutz.activities.other.DiscountActivity;
import com.pop.pricecutz.adapters.CompanyListAdapter;
import com.pop.pricecutz.adapters.CompanyListAdapter2;
import com.pop.pricecutz.adapters.DiscountListAdapter;
import com.pop.pricecutz.adapters.DiscountListAdapter2;
import com.pop.pricecutz.data.entries.CompanyEntry;
import com.pop.pricecutz.data.entries.DiscountEntry;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

//    DiscountListAdapter mDiscountListAdapter;

    private DiscountListAdapter2 adapter;

//    ArrayList<Discount> mDiscountArrayList;

    ListView listView;

    Context mContext;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        listView = (ListView) root.findViewById(R.id.fragment_home_listview);

//        mDiscountArrayList = Randomizer.getDiscounts(10);
//
//        mDiscountListAdapter = new DiscountListAdapter(
//                mContext,
//                R.layout.fragment_home_list_items,
//                R.id.fragment_home_list_item_imageview,
//                R.id.fragment_home_list_item_imageview2,
//                R.id.fragment_home_list_item_textview,
//                R.id.fragment_home_list_item_textview2,
//                mDiscountArrayList);
//
//        listView.setAdapter(mDiscountListAdapter);
//
//        listView.setOnItemClickListener(this);

        adapter = new DiscountListAdapter2(mContext,
                R.layout.fragment_home_list_items,
                null,
                new String[] {DiscountEntry.COLUMN_CODE, DiscountEntry.COLUMN_TITLE},
                new int[] { R.id.fragment_home_list_item_textview, R.id.fragment_home_list_item_textview2 },
                0,
                R.id.fragment_home_list_item_imageview,
                R.id.fragment_home_list_item_imageview2);

        listView.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);


//        if(savedInstanceState == null) {
//
//            mCompanyArrayList = new ArrayList<>();
//
//            mCompanyListAdapter = new CompanyListAdapter(
//                    mContext,
//                    R.layout.fragment_home_list_items,
//                    R.id.fragment_home_list_item_imageview,
//                    R.id.fragment_home_list_item_textview,
//                    mCompanyArrayList);
//        }
//        else {
//            Gson gson = new Gson();
//
//            String mCompanyArrayListJsonStr = savedInstanceState.getString("CompanyArrayList");
//
//            Type arrayType = new TypeToken<ArrayList<Company>>() {
//            }.getType();
//            mCompanyArrayList = (ArrayList<Company>) gson.fromJson(mCompanyArrayListJsonStr, arrayType);
//
//            mCompanyListAdapter = new CompanyListAdapter(
//                    mContext,
//                    R.layout.fragment_home_list_items,
//                    R.id.fragment_home_list_item_imageview,
//                    R.id.fragment_home_list_item_textview,
//                    mCompanyArrayList);
//
//            listView.setAdapter(mCompanyListAdapter);
//        }

//        getLoaderManager().initLoader(0, null, this);

//        listView.setOnItemClickListener(this);

        return root;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

//        outState.putString("CompanyArrayList", new Gson().toJson(mCompanyArrayList));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getContext(), DiscountActivity.class);
        startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(mContext, DiscountEntry.CONTENT_URI,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //mAdapter.swapCursor(null);
        adapter.swapCursor(null);
    }

//    private void refreshValuesFromContentProvider() {
//        CursorLoader cursorLoader = new CursorLoader(mContext, CompanyEntry.CONTENT_URI,
//                null, null, null, null);
//        Cursor c = cursorLoader.loadInBackground();
//        adapter.swapCursor(c);
//    }

}