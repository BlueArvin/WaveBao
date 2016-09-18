package com.arvin.wavebao.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arvin.wavebao.R;
import com.arvin.wavebao.activities.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscription;

/**
 * Created by yinqilong on 2016/9/9.
 */
public abstract class BaseFragment extends Fragment {

    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getFragLayoutId(),container,false);
        ButterKnife.inject(this,rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupFeed();
    }

    protected void showToast(String content){
        ((BaseActivity)getActivity()).showToast(content);
    }

    /**
     * 解决Subscription内存泄露问题
     * @param s
     */
    protected void addSubscription(Subscription s) {
        ((BaseActivity)getActivity()).addSubscription(s);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((BaseActivity)getActivity()).unSubscribe();
    }

    /**
     * fragment布局
     * @return
     */
    protected abstract int getFragLayoutId();

    /**
     * fragment主逻辑
     */
    protected abstract void setupFeed();

}
