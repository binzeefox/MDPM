package com.binzeefox.mdpm;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import com.binzeefox.mdpm.util.CommonUtil;
import com.binzeefox.mdpm.util.UserUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private TextInputLayout userName;
    private TextInputLayout password;
    private TextInputLayout psd_check;
    private TextInputLayout mEmail;
    private TextInputLayout mPhone;
    private FrameLayout focusHolder;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_register, container, false);

        focusHolder = (FrameLayout) v.findViewById(R.id.focus_holder);
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

    /**
     * 验证注册
     */
    private void doRegister() {
        if (UserUtil.checkRegister(userName, mEmail, mPhone, password, psd_check)){
            CommonUtil.showToast(getContext(), "注册成功，即将登陆");
            Thread jump = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1500);
                        int userId = UserUtil.getUserId(UserUtil.getString(userName));
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                        getActivity().finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            jump.start();
        }
    }

    /**
     * 重置输入框
     */
    private void viewRefresh(){
        focusHolder.requestFocus();
        TextInputLayout[] views = new TextInputLayout[]{
                userName,password,psd_check,mEmail,mPhone
        };
        for (TextInputLayout view:views){
            view.setError(null);
            if (view.getId() != R.id.username_field) {
                view.getEditText().setText(null);
            }
        }
    }

}
