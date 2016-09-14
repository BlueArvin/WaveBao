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
public class MainFragment extends BaseSwipyRefreshFragment implements FeedAdapter.OnFeedItemClickListener, SwipyRefreshLayout.OnRefreshListener {

    private FeedAdapter feedAdapter;

    @Override
    protected int getFragLayoutId() {
        return R.layout.frag_main;
    }

    @Override
    protected void setupFeed() {
        super.setupFeed();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);

        feedAdapter = new FeedAdapter(getActivity());
        feedAdapter.setOnFeedItemClickListener(this);
        rvFeed.setAdapter(feedAdapter);
        feedAdapter.updateItems(true);
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
}
