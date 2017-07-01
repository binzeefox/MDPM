package com.binzeefox.mdpm.client;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binzeefox.mdpm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckUserFragment extends Fragment {


    public CheckUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reset_psd, container, false);
    }

}
