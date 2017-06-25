package com.binzeefox.mdpm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_login, container, false);

        FrameLayout focusHolder = (FrameLayout) v.findViewById(R.id.focus_holder);
        focusHolder.setFocusable(true);
        focusHolder.setFocusableInTouchMode(true);
        focusHolder.requestFocus();
        return v;
    }

}
