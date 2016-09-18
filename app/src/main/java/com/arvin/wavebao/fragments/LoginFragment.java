package com.arvin.wavebao.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.arvin.wavebao.Beans.MyUser;
import com.arvin.wavebao.R;
import com.arvin.wavebao.adapter.FeedAdapter;
import com.arvin.wavebao.utils.CommonUtils;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import rx.Subscriber;

/**
 * Created by yinqilong on 2016/9/18.
 */
public class LoginFragment extends BaseFragment {

    @InjectView(R.id.et_username_login)
    EditText et_username_login;

    @InjectView(R.id.et_pwd_login)
    EditText et_pwd_login;


    @Override
    protected int getFragLayoutId() {
        return R.layout.frag_login;
    }

    @Override
    protected void setupFeed() {

    }

    @OnClick(R.id.btn_login)
    public void onLoginClicked(){
        String username = et_username_login.getText().toString().trim();
        String pwd = et_pwd_login.getText().toString().trim();
        final BmobUser bu2 = new BmobUser();
        bu2.setUsername(username);
        bu2.setPassword(pwd);
        //新增加的Observable
        bu2.loginObservable(BmobUser.class).subscribe(new Subscriber<BmobUser>() {
            @Override
            public void onCompleted() {
                CommonUtils.log("----onCompleted----");
            }

            @Override
            public void onError(Throwable e) {
                CommonUtils.log(new BmobException(e).getMessage());
            }

            @Override
            public void onNext(BmobUser bmobUser) {
                showToast(bmobUser.getUsername() + "登陆成功");
                CommonUtils.testGetCurrentUser();
            }
        });
    }

}
