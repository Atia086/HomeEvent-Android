package com.yolo.michael.yolo.fragment.base;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by student5305 on 21/04/16.
 */
public class BaseFragment extends Fragment {


    private String actionBarTitle;

    public void setActionBarTitle(int resourceId) {
        setActionBarTitle(getString(resourceId));
    }

    public void setActionBarTitle(String actionBarTitle) {
        this.actionBarTitle = actionBarTitle;
    }

    @Override
    public void onResume() {
        super.onResume();

        AppCompatActivity activity  = (AppCompatActivity)getActivity();
        ActionBar supportActionBar = activity.getSupportActionBar();
        supportActionBar.setTitle(actionBarTitle);
    }

}
