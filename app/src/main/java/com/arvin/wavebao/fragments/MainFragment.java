package com.arvin.wavebao.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arvin.wavebao.R;
import com.arvin.wavebao.adapter.FeedAdapter;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yinqilong on 2016/9/9.
 */
public class MainFragment extends BaseFragment implements FeedAdapter.OnFeedItemClickListener, SwipyRefreshLayout.OnRefreshListener {

//    @InjectView(R.id.fragRootView)
    RecyclerView rvFeed;

//    @InjectView(R.id.swipyrefreshlayout)
    SwipyRefreshLayout swipyrefreshlayout;

    private FeedAdapter feedAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_main,container,false);
        rvFeed = (RecyclerView) rootView.findViewById(R.id.fragRootView);
        swipyrefreshlayout = (SwipyRefreshLayout) rootView.findViewById(R.id.swipyrefreshlayout);
//        ButterKnife.inject(this,rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupFeed();
    }

    private void setupFeed() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);

        swipyrefreshlayout.setOnRefreshListener(this);
        swipyrefreshlayout.setDirection(SwipyRefreshLayoutDirection.BOTH);

        feedAdapter = new FeedAdapter(getActivity());
        feedAdapter.setOnFeedItemClickListener(this);
        rvFeed.setAdapter(feedAdapter);
        feedAdapter.updateItems(true);
    }

    @Override
    protected int getFragLayoutId() {
        return R.layout.frag_main;
    }

    @Override
    public void onCommentsClick(View v, int position) {
        Toast.makeText(getActivity(),"onCommentsClick",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoreClick(View v, int position) {
        Toast.makeText(getActivity(),"onMoreClick",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProfileClick(View v) {
        Toast.makeText(getActivity(),"onProfileClick",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        Log.d("MainActivity", "Refresh triggered at "
                + (direction == SwipyRefreshLayoutDirection.TOP ? "top" : "bottom"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Hide the refresh after 2sec
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        swipyrefreshlayout.setRefreshing(false);
                    }
                });
            }
        }, 2000);
    }
}
