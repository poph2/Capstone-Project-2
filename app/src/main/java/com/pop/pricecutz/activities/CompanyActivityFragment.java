package com.pop.pricecutz.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pop.pricecutz.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class CompanyActivityFragment extends Fragment {

    public CompanyActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_company, container, false);
    }
}
