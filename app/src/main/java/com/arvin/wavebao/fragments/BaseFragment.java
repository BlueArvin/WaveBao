package com.arvin.wavebao.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arvin.wavebao.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yinqilong on 2016/9/9.
 */
public abstract class BaseFragment extends Fragment {

    View rootView;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        rootView = inflater.inflate(getFragLayoutId(),container,false);
//        ButterKnife.inject(this,rootView);
//        return rootView;
//    }

    protected abstract int getFragLayoutId();

}
