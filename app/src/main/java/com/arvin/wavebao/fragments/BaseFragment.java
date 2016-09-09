package com.arvin.wavebao.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arvin.wavebao.R;

import butterknife.InjectView;

/**
 * Created by yinqilong on 2016/9/9.
 */
public abstract class BaseFragment extends Fragment {

    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = View.inflate(getActivity(),getFragLayoutId(),null);
        return rootView;
    }

    protected abstract int getFragLayoutId();
}
