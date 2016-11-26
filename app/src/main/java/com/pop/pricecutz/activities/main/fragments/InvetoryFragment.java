package com.pop.pricecutz.activities.main.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pop.pricecutz.R;

/**
 * Created by Pop H2 on 10/12/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class InvetoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);
        return root;
    }

}
