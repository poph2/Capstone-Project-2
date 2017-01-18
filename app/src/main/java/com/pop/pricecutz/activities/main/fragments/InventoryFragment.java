package com.pop.pricecutz.activities.main.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

import com.pop.pricecutz.R;
import com.pop.pricecutz.activities.other.DiscountActivity;
import com.pop.pricecutz.adapters.InventorySavedDiscountListAdapter;
import com.pop.pricecutz.data.entries.DiscountEntry;

/**
 * Created by Pop H2 on 10/12/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class InventoryFragment extends Fragment implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String LOG_TAG = InventoryFragment.class.getSimpleName();

    private InventorySavedDiscountListAdapter adapter;

    ListView listView;

    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_inventory, container, false);

        listView = (ListView) root.findViewById(R.id.fragment_inventory_listview);

        adapter = new InventorySavedDiscountListAdapter(mContext,
                R.layout.fragment_inventory_list_item,
                null,
                new String[] {DiscountEntry.COLUMN_TITLE},
                new int[] { R.id.fragment_inventory_list_item_textview },
                0,
                R.id.fragment_inventory_list_item_imageview);

        listView.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);

        listView.setOnItemClickListener(this);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(
                mContext,
                DiscountEntry.CONTENT_URI_SAVED_WITH_COMPANY,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Cursor c = adapter.getCursor();
        c.moveToPosition(i);
        long id = c.getLong(0);

        Intent intent = new Intent(getContext(), DiscountActivity.class);
        intent.putExtra(DiscountEntry._ID, id);
        startActivity(intent);
    }
}
