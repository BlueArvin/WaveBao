package com.arvin.wavebao.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.arvin.wavebao.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yinqilong on 2016/9/9.
 */
public abstract class BaseActivity extends AppCompatActivity{

    protected final String TAG_LOG = this.getClass().getSimpleName();

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    protected Bundle savedInstanceState;

    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView();

        ButterKnife.inject(this);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        bindViewListener();
        proAccessLogic();

    }

    protected Bundle getSavedInstanceState(){
        return this.savedInstanceState;
    }

    protected void setToolbarTitle(String title){
        if(this.toolbar != null){
            toolbar.setTitle(title);
        }
    }

    public void showToast(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }

    public void showSnackBar(View view, String content){
        Snackbar.make(view, content, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    /**
     * 解决Subscription内存泄露问题
     * @param s
     */
    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unSubscribe();
    }

    public void unSubscribe(){
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    protected Toolbar getToolbar(){
        return this.toolbar;
    }


    protected abstract void setContentView();
    protected abstract void bindViewListener();
    protected abstract void proAccessLogic();
}
