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
import android.widget.GridView;

import com.pop.pricecutz.R;
import com.pop.pricecutz.activities.other.DiscountActivity;
import com.pop.pricecutz.activities.other.DiscountCategoryActivity;
import com.pop.pricecutz.adapters.CategoryGridAdapter;
import com.pop.pricecutz.data.entries.CategoryEntry;
import com.pop.pricecutz.data.entries.DiscountEntry;

public class CategoryFragment extends Fragment implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String LOG_TAG = HomeFragment.class.getSimpleName();

    private CategoryGridAdapter adapter;

    GridView gridView;

    Context mContext;

    public CategoryFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_categories, container, false);

        gridView = (GridView) root.findViewById(R.id.fragment_categories_gidview);

        adapter = new CategoryGridAdapter(mContext,
                R.layout.fragment_categories_grid_items,
                null,
                new String[] {CategoryEntry.COLUMN_NAME},
                new int[] { R.id.fragment_categories_grid_item_textview },
                0,
                R.id.fragment_categories_grid_item_imageview);

        gridView.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);

        gridView.setOnItemClickListener(this);

        return root;
    }




    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Cursor c = adapter.getCursor();
        c.moveToPosition(i);
        long id = c.getLong(0);

        String name = c.getString(c.getColumnIndex(CategoryEntry.COLUMN_NAME));

        Intent intent = new Intent(getContext(), DiscountCategoryActivity.class);

        intent.putExtra(CategoryEntry._ID, id);
        intent.putExtra(CategoryEntry.COLUMN_NAME, name);

        startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                mContext,
                CategoryEntry.CONTENT_URI,
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
}
