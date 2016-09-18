package com.arvin.wavebao.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arvin.wavebao.Beans.MyUser;
import com.arvin.wavebao.R;
import com.arvin.wavebao.adapter.FeedAdapter;
import com.arvin.wavebao.utils.CommonUtils;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import rx.Subscriber;

/**
 * Created by yinqilong on 2016/9/18.
 */
public class RegFragment extends BaseFragment {

//    @InjectView(R.id.et_phone)
//    EditText et_phone;

    @InjectView(R.id.et_username)
    EditText et_username;

    @InjectView(R.id.et_pwd)
    EditText et_pwd;

    @InjectView(R.id.et_pwd2)
    EditText et_pwd2;

    @InjectView(R.id.tv_reg_info)
    TextView tv_reg_info;

    @Override
    protected int getFragLayoutId() {
        return R.layout.frag_reg;
    }

    @Override
    protected void setupFeed() {

    }

    @OnClick(R.id.btn_reg)
    public void onRegBtnClicked(){
        //1.检查两次密码是否一致

        String username = et_username.getText().toString().trim();
        if(!TextUtils.isEmpty(username)){
            final String pwd = et_pwd.getText().toString();
            String pwd2 = et_pwd2.getText().toString();
            if(!TextUtils.isEmpty(pwd)){
                if(!TextUtils.isEmpty(pwd2)){
                    if(pwd2.equals(pwd)){
                        MyUser myUser = new MyUser();
                        myUser.setUsername(username);
                        myUser.setPassword(pwd);
                        addSubscription(myUser.signUp(new SaveListener<MyUser>() {
                            @Override
                            public void done(MyUser s, BmobException e) {
                                if(e==null){
//                                    CommonUtils.log(s.toString());
                                    showToast("注册成功:" +s.toString());
                                    CommonUtils.log("----开始登陆----");
                                    s.setPassword(pwd);
                                    s.loginObservable(BmobUser.class).subscribe(new Subscriber<BmobUser>() {
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
                                            CommonUtils.log(bmobUser.getUsername() + "登陆成功");
//                                            testGetCurrentUser();
                                        }
                                    });
                                }else{
                                    CommonUtils.log(e.getMessage());
                                    showToast("注册失败");
                                }
                            }
                        }));
                    }else{
                        showToast("两次密码输入不一致");
                    }
                }else{
                    showToast("确认密码不能为空");
                }
            }else{
                showToast("密码不能为空");
            }
        }else{
            showToast("用户名不能为空");
        }

        //2.检查验证码是否有效
//        final String number = et_phone.getText().toString();
//        String code = et_vetify_code.getText().toString();
//        if(!TextUtils.isEmpty(number)&&!TextUtils.isEmpty(code)){
//            BmobSMS.verifySmsCode(number,code, new UpdateListener() {
//
//                @Override
//                public void done(BmobException ex) {
//                    if(ex==null){//短信验证码已验证成功
//                        //3.注册
//                        final MyUser myUser = new MyUser();
//                        myUser.setUsername(number);
//                        myUser.setPassword(pwd);
//                        addSubscription(myUser.signUp(new SaveListener<MyUser>() {
//                            @Override
//                            public void done(MyUser s, BmobException e) {
//                                if(e==null){
//                                    CommonUtils.log(s.toString());
//                                    showToast("注册成功:" +s.toString());
//                                }else{
//                                    CommonUtils.log(e.getMessage());
//                                    showToast("注册失败");
//                                }
//                            }
//                        }));
//                    }else{
//                        showToast("短信验证码已失效");
//                    }
//                }
//            });
//        }else{
//            showToast("请输入手机号和验证码");
//        }

    };

//    @OnClick(R.id.btn_vetify_code)
//    public void onVetifyCodeBtnClicked(){
//        String number = et_phone.getText().toString();
//        if(!TextUtils.isEmpty(number)){
//            BmobSMS.requestSMSCode(number, "注册模板",new QueryListener<Integer>() {
//
//                @Override
//                public void done(Integer smsId, BmobException ex) {
//                    if(ex==null){//验证码发送成功
//                        showToast("验证码发送成功");//用于查询本次短信发送详情
//                    }else{
//                        showToast("验证码发送失败，请稍后重试");
//                    }
//                }
//            });
//        }else{
//            showToast("请输入手机号码");
//        }
//    };

    @OnClick(R.id.btn_get_user_info)
    public void getLocalUserInfo(){
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        if (myUser != null) {
            CommonUtils.log("本地用户信息:objectId = " + myUser.getObjectId() + ",name = " + myUser.getUsername()
                    + ",SessionToken = "+ myUser.getSessionToken());
        } else {
            showToast("本地用户为null,请登录。");
        }
        //V3.4.5版本新增加getObjectByKey方法获取本地用户对象中某一列的值
		String username = (String) BmobUser.getObjectByKey("username");
        CommonUtils.log("username："+username);
    }

}
