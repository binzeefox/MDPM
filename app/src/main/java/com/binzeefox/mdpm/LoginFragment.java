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
import android.widget.TextView;

import com.binzeefox.mdpm.db.User;
import com.binzeefox.mdpm.util.UserUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private TextInputLayout userName;
    private TextInputLayout password;
    private TextView issueLogin;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);

        FrameLayout focusHolder = (FrameLayout) v.findViewById(R.id.focus_holder);
        focusHolder.setFocusable(true);
        focusHolder.setFocusableInTouchMode(true);
        focusHolder.requestFocus();
        userName = (TextInputLayout) v.findViewById(R.id.username_field);
        password = (TextInputLayout) v.findViewById(R.id.password_field);
        issueLogin = (TextView) v.findViewById(R.id.issue_login);
        issueLogin.setVisibility(View.GONE);

        Button action = (Button) v.findViewById(R.id.button_act);
        TextView forgetPsd = (TextView) v.findViewById(R.id.forget_password);
        action.setOnClickListener(this);
        forgetPsd.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_act:
                issueLogin.setVisibility(View.GONE);
                doLogin();
                break;

            case R.id.forget_password:
                // TODO 这里跳转进忘记密码页面
                break;
            default:
                break;
        }
    }

    private void doLogin() {

        switch (UserUtil.checkLogin(userName, password)){

            case UserUtil.LOGIN_VALID:
                issueLogin.setVisibility(View.VISIBLE);
                break;

            case UserUtil.LOGIN_FAILED:
                break;

            case UserUtil.LOGIN_SUCCESS:
                int userId = UserUtil.getUserId(UserUtil.getString(userName));
                Intent intent = new Intent(getActivity(), UserActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
                getActivity().finish();
                break;
            default:
                break;
        }
    }

    /**
     * 刷新输入框
     */
    private void viewRefresh(){
        TextInputLayout[] views = new TextInputLayout[]{
                userName,password
        };
        for (TextInputLayout view:views){
            view.setError(null);
        }
    }


}
