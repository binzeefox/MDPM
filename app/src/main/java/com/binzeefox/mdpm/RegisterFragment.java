package com.binzeefox.mdpm;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private TextInputLayout userName;
    private TextInputLayout password;
    private TextInputLayout psd_check;
    private TextInputLayout mEmail;
    private TextInputLayout mPhone;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_register, container, false);

        FrameLayout focusHolder = (FrameLayout) v.findViewById(R.id.focus_holder);
        focusHolder.setFocusable(true);
        focusHolder.setFocusableInTouchMode(true);
        focusHolder.requestFocus();

        userName = (TextInputLayout) v.findViewById(R.id.username_field);
        password = (TextInputLayout) v.findViewById(R.id.password_field);
        psd_check = (TextInputLayout) v.findViewById(R.id.password_confirm_field);
        mEmail = (TextInputLayout) v.findViewById(R.id.email_field);
        mPhone = (TextInputLayout) v.findViewById(R.id.phone_field);
        Button action = (Button) v.findViewById(R.id.button_act);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegister();
            }
        });
        return v;
    }

    private void doRegister() {

    }

}
