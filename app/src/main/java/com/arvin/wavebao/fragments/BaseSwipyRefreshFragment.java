package com.arvin.wavebao.fragments;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.arvin.wavebao.R;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import butterknife.InjectView;

/**
 * Created by yinqilong on 2016/9/14.
 */
public class BaseSwipyRefreshFragment extends BaseFragment implements SwipyRefreshLayout.OnRefreshListener {

    @InjectView(R.id.fragRootView)
    RecyclerView rvFeed;

    @InjectView(R.id.swipyrefreshlayout)
    SwipyRefreshLayout swipyrefreshlayout;//默认支持上下刷新

    @Override
    protected int getFragLayoutId() {
        return getFragLayoutId();
    }

    @Override
    protected void setupFeed() {
        swipyrefreshlayout.setOnRefreshListener(this);
        swipyrefreshlayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        Log.d("MainActivity", "Refresh triggered at "
                + (direction == SwipyRefreshLayoutDirection.TOP ? "top" : "bottom"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        swipyrefreshlayout.setRefreshing(false);
                    }
                });
            }
        }, 2000);
    }

    protected void setSwipyrefreshlayoutDirection(SwipyRefreshLayoutDirection direction){
        swipyrefreshlayout.setDirection(direction);
    }
}
