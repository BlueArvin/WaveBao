package com.arvin.wavebao.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.OvershootInterpolator;
import com.arvin.wavebao.R;
import com.arvin.wavebao.fragments.EditInfoFragment;
import com.arvin.wavebao.fragments.LoginFragment;
import com.arvin.wavebao.fragments.MainFragment;
import com.arvin.wavebao.fragments.RegFragment;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Optional;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG_FRAGMENT_MAIN = "官方精品";
    private static final String TAG_FRAGMENT_REG = "注册";
    private static final String TAG_FRAGMENT_LOGIN = "登录";
    private static final String TAG_FRAGMENT_EDIT = "修改个人信息";

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
    private RegFragment regFragment;
    private LoginFragment loginFragment;
    private EditInfoFragment editFragment;

    private boolean pendingIntroAnimation;

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
        else{
            pendingIntroAnimation = true;
            cur_frag_tag = TAG_FRAGMENT_MAIN;
        }
        setTabSelection();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString(KEY_CUR_FRAG, cur_frag_tag);
    }

    @Optional
    @OnClick(R.id.fab)
    public void OnFabClicked() {
        showSnackBar(fab,"发布自己的活动");
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        if (pendingIntroAnimation) {
            pendingIntroAnimation = false;
            fab.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));
            startContentAnimation();
        }
        return true;
    }

    private void startContentAnimation() {
        fab.animate()
                .translationY(0)
                .setInterpolator(new OvershootInterpolator(1.f))
                .setStartDelay(300)
                .setDuration(400)
                .start();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        String itemTitle = item.getTitle().toString();

        switch (id) {
            case R.id.nav_camera:
                cur_frag_tag = TAG_FRAGMENT_MAIN;
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.reg:
                cur_frag_tag = TAG_FRAGMENT_REG;
                break;
            case R.id.login:
                cur_frag_tag = TAG_FRAGMENT_LOGIN;
                break;
            case R.id.edit:
                cur_frag_tag = TAG_FRAGMENT_EDIT;
                break;
            case R.id.nav_send:
                break;
        }

        setToolbarTitle(itemTitle);
        setTabSelection();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mainFragment != null) {
            transaction.hide(mainFragment);
        }
        if (regFragment != null) {
            transaction.hide(regFragment);
        }
        if (loginFragment != null) {
            transaction.hide(loginFragment);
        }
        if (editFragment != null) {
            transaction.hide(editFragment);
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
        }else if(TAG_FRAGMENT_REG.equals(cur_frag_tag)){
            if (regFragment == null) {
                regFragment = new RegFragment();
                transaction.add(R.id.fl_content, regFragment, TAG_FRAGMENT_MAIN);
            } else {
                transaction.show(regFragment);
            }
        }else if(TAG_FRAGMENT_LOGIN.equals(cur_frag_tag)){
            if (loginFragment == null) {
                loginFragment = new LoginFragment();
                transaction.add(R.id.fl_content, loginFragment, TAG_FRAGMENT_MAIN);
            } else {
                transaction.show(loginFragment);
            }
        }else if(TAG_FRAGMENT_EDIT.equals(cur_frag_tag)){
            if (editFragment == null) {
                editFragment = new EditInfoFragment();
                transaction.add(R.id.fl_content, editFragment, TAG_FRAGMENT_MAIN);
            } else {
                transaction.show(editFragment);
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
