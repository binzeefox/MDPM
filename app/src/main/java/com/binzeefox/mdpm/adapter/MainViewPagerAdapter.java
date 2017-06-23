package com.binzeefox.mdpm.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.binzeefox.mdpm.LoginFragment;
import com.binzeefox.mdpm.RegisterFragment;

/**
 * Created by tong.xiwen on 2017/6/23.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGER_COUNT = 2;
    private String titles[] = new String[]{"登陆", "注册"};
    private Context context;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1){
            return new RegisterFragment();
        }
        return new LoginFragment();
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
