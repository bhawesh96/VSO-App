package com.example.bhawesh96.vso_androidapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapterLogin extends FragmentPagerAdapter {
    private Context _context;

    public ViewPagerAdapterLogin(Context context, FragmentManager fm) {
        super(fm);
        this._context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new FragmentLogin();
        else
            return new FragmentSignUp();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0)
            return "LOGIN";
        else
            return "SIGN-UP";
    }
}
