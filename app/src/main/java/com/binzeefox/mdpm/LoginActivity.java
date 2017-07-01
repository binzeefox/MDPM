package com.binzeefox.mdpm;

import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.binzeefox.mdpm.adapter.MainViewPagerAdapter;
import com.binzeefox.mdpm.util.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewRefresh(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initViews();
    }

    /**
     * 初始化ViewPager
     */
    private void initViews() {
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    public void viewRefresh(int position) {
        View view = viewPager.getChildAt(position);

        FrameLayout focusHolder = (FrameLayout) view.findViewById(R.id.focus_holder);
        focusHolder.setFocusable(true);
        focusHolder.setFocusableInTouchMode(true);
        focusHolder.requestFocus();

        List<Integer> ids = new ArrayList<>();
        ids.add(R.id.username_field);
        ids.add(R.id.password_field);
        if (position == 1){
            ids.add(R.id.password_confirm_field);
            ids.add(R.id.email_field);
            ids.add(R.id.phone_field);
        }else {
            TextView issue = (TextView) view.findViewById(R.id.issue_login);
            issue.setVisibility(View.GONE);
        }

        for (int id:ids){
            TextInputLayout inputLayout = (TextInputLayout) view.findViewById(id);
            inputLayout.setError(null);
            if (id != R.id.username_field){
                inputLayout.getEditText().setText(null);
            }
        }
    }
}
