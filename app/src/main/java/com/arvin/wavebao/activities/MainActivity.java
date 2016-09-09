package com.arvin.wavebao.activities;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.arvin.wavebao.R;
import com.arvin.wavebao.fragments.MainFragment;

import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG_FRAGMENT_MAIN = "Import";

    private static final String KEY_CUR_FRAG = "key_cur_frag";

    private String cur_frag_tag;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    @InjectView(R.id.drawer_layout)
    DrawerLayout drawer;

    @InjectView(R.id.nav_view)
    NavigationView navigationView;


    private FragmentManager fragmentManager;
    private MainFragment mainFragment;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void bindViewListener() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void proAccessLogic() {
        initFragment();
    }

    private void initFragment() {
        if (getSavedInstanceState() != null)
            cur_frag_tag = getSavedInstanceState().getString(KEY_CUR_FRAG);
        else
            cur_frag_tag = TAG_FRAGMENT_MAIN;
        setTabSelection();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString(KEY_CUR_FRAG, cur_frag_tag);
    }

    @OnClick(R.id.fab)
    public void OnFabClicked() {
        Snackbar.make(fab, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        String itemTitle = item.getTitle().toString();

        switch (id) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }

        setToolbarTitle(itemTitle);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mainFragment != null) {
            transaction.hide(mainFragment);
        }
    }

    private void setTabSelection() {
        if (TextUtils.isEmpty(cur_frag_tag))
            return;

        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);

        if (TAG_FRAGMENT_MAIN.equals(cur_frag_tag)) {
            if (mainFragment == null) {
                mainFragment = new MainFragment();
                transaction.add(R.id.fl_content, mainFragment, TAG_FRAGMENT_MAIN);
            } else {
                transaction.show(mainFragment);
            }
        }

        transaction.commit();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setToolbarTitle(cur_frag_tag);
    }
}
