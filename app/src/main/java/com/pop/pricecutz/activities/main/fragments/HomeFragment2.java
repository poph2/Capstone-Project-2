package com.pop.pricecutz.activities.main.fragments;

/**
 * Created by adeniyi.bello on 12/13/2016.
 */

import android.content.Context;
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
import com.pop.pricecutz.R;
import com.pop.pricecutz.adapters.CompanyListAdapter;
import com.pop.pricecutz.data.entries.CompanyEntry;

import java.util.ArrayList;


/**
 * Created by Pop H2 on 8/25/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class HomeFragment2 extends Fragment implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

//    CompanyListAdapter mCompanyListAdapter;
//
////    private CompanyListAdapter2 adapter;
//
////    SimpleCursorAdapter mAdapter;
//
//    ArrayList<Company> mCompanyArrayList;
//
//    ListView listView;
//
//    Context mContext;
//
//    public HomeFragment2() {
//
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        mContext = getContext();
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View root = inflater.inflate(R.layout.fragment_home, container, false);
//
//        listView = (ListView) root.findViewById(R.id.fragment_home_listview);
//
////        if(savedInstanceState == null) {
////
////            mCompanyArrayList = new ArrayList<>();
////
////            mCompanyListAdapter = new CompanyListAdapter(
////                    mContext,
////                    R.layout.fragment_home_list_items,
////                    R.id.fragment_home_list_item_imageview,
////                    R.id.fragment_home_list_item_textview,
////                    mCompanyArrayList);
////        }
////        else {
////            Gson gson = new Gson();
////
////            String mCompanyArrayListJsonStr = savedInstanceState.getString("CompanyArrayList");
////
////            Type arrayType = new TypeToken<ArrayList<Company>>() {
////            }.getType();
////            mCompanyArrayList = (ArrayList<Company>) gson.fromJson(mCompanyArrayListJsonStr, arrayType);
////
////            mCompanyListAdapter = new CompanyListAdapter(
////                    mContext,
////                    R.layout.fragment_home_list_items,
////                    R.id.fragment_home_list_item_imageview,
////                    R.id.fragment_home_list_item_textview,
////                    mCompanyArrayList);
////
////            listView.setAdapter(mCompanyListAdapter);
////        }
//
////        getLoaderManager().initLoader(0, null, this);
//
////        listView.setOnItemClickListener(this);
//
//        return root;
//    }
//
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        outState.putString("CompanyArrayList", new Gson().toJson(mCompanyArrayList));
//    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

//        return new CursorLoader(mContext, CompanyEntry.CONTENT_URI,
//                null, null, null, null);

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        //mAdapter.swapCursor(data);
//        mCompanyArrayList = new ArrayList();
//        while (data.moveToNext()) {
//
////            int id          = data.getInt(data.getColumnIndex(CompanyEntry.COLUMN_COY_ID));
//            int id          = data.getInt(data.getColumnIndex(CompanyEntry._ID));
//            String name     = data.getString(data.getColumnIndex(CompanyEntry.COLUMN_NAME));
//            String industry = data.getString(data.getColumnIndex(CompanyEntry.COLUMN_INDUSTRY));
//            String imageURL = data.getString(data.getColumnIndex(CompanyEntry.COLUMN_IMAGE_URL));
//
//            Company company = new Company(id, name, industry, imageURL);
//            mCompanyArrayList.add(company);
//
//            Log.d("HOMEFRAGMENT", new Gson().toJson(company));
//        }
//
//        Toast.makeText(mContext, "Cursor row count - " + data.getCount(), Toast.LENGTH_LONG).show();
//
////        mCompanyListAdapter.clear();
////        mCompanyListAdapter.addAll(mCompanyArrayList);
//
//        mCompanyListAdapter = new CompanyListAdapter(
//                mContext,
//                R.layout.fragment_home_list_items,
//                R.id.fragment_home_list_item_imageview,
//                R.id.fragment_home_list_item_textview,
//                mCompanyArrayList);
//
//        listView.setAdapter(mCompanyListAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //mAdapter.swapCursor(null);
        //adapter.swapCursor(null);
    }

//    private void refreshValuesFromContentProvider() {
//        CursorLoader cursorLoader = new CursorLoader(mContext, CompanyEntry.CONTENT_URI,
//                null, null, null, null);
//        Cursor c = cursorLoader.loadInBackground();
//        adapter.swapCursor(c);
//    }

}