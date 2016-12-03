package com.pop.pricecutz.activities.main.fragments;

/**
 * Created by Pop H2 on 8/25/2016.
 * Pop Inc
 * Lagos Nigeria
 */

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pop.pricecutz.Discount;
import com.pop.pricecutz.R;
import com.pop.pricecutz.Randomizer;
import com.pop.pricecutz.adapters.CompanyListAdapter2;
import com.pop.pricecutz.adapters.DiscountListAdapter;
import com.pop.pricecutz.data.entries.CompanyEntry;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    DiscountListAdapter mDiscountListAdapter;

    private CompanyListAdapter2 adapter;

//    SimpleCursorAdapter mAdapter;

    ArrayList<Discount> mDiscountArrayList;

    Context mContext;

    public HomeFragment() {
        mDiscountArrayList = Randomizer.getDiscounts(20);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getContext();

        if (savedInstanceState == null) {
            mDiscountArrayList = Randomizer.getDiscounts(20);
        } else {
            Gson gson = new Gson();

            String mDiscountArrayListJsonStr = savedInstanceState.getString("DiscountArrayList");

            Type arrayType = new TypeToken<ArrayList<Discount>>() {
            }.getType();
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

        adapter = new CompanyListAdapter2(mContext,
                R.layout.fragment_home_list_items,
                null,
                new String[] {CompanyEntry.COLUMN_NAME, CompanyEntry.COLUMN_INDUSTRY},
                new int[] { R.id.fragment_home_list_item_textview , R.id.fragment_home_list_item_textview2 },
                0,
                R.id.fragment_home_list_item_imageview);

//        adapter = new SimpleCursorAdapter(getContext(),
//                R.layout.fragment_home,
//                c,
//                new String[] { CompanyEntry.COLUMN_NAME, CompanyEntry.COLUMN_INDUSTRY},
//                new int[] { R.id.fragment_home_list_item_textview , R.id.fragment_home_list_item_textview2 }, 0);

//        mAdapter = new SimpleCursorAdapter(mContext,
//                R.layout.fragment_home_list_items,
//                null,
//                new String[]{CompanyEntry.COLUMN_NAME, CompanyEntry.COLUMN_INDUSTRY},
//                new int[]{R.id.fragment_home_list_item_textview, R.id.fragment_home_list_item_textview2}, 0);
        //setListAdapter(mAdapter);

        //mCompanyListAdapter = new CompanyListAdapter(getContext(), R.layout.fragment_favorites_list_item, mCompanyArrayList);

        ListView listView = (ListView) root.findViewById(R.id.fragment_home_listview);
//        listView.setAdapter(mDiscountListAdapter);
//        listView.setAdapter(mAdapter);
        listView.setAdapter(adapter);
//        refreshValuesFromContentProvider();

        listView.setOnItemClickListener(this);

//        Toast.makeText(mContext, Integer.toString(mCompanyArrayList.size()), Toast.LENGTH_LONG).show();

        getLoaderManager().initLoader(0, null, this);

        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //outState.putString("DiscountArrayList", new Gson().toJson(mDiscountArrayList));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(mContext, CompanyEntry.CONTENT_URI,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //mAdapter.swapCursor(data);
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