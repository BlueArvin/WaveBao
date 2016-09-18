package com.arvin.wavebao.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.arvin.wavebao.Beans.MyUser;

import cn.bmob.v3.BmobUser;

/**
 * Created by yinqilong on 2016/9/12.
 */
public class CommonUtils {
    private static int screenWidth = 0;
    private static int screenHeight = 0;

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }

    public static int getScreenWidth(Context c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }

        return screenWidth;
    }

    public static boolean isAndroid5() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static void log(String msg) {
        Log.i("CommonUtils LOG","===============================================================================");
        Log.i("CommonUtils LOG", msg);
    }

    public static void testGetCurrentUser(){
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        if (myUser != null) {
            log("本地用户信息:objectId = " + myUser.getObjectId() + ",name = " + myUser.getUsername()
                    + ",SessionToken = "+ myUser.getSessionToken());
        } else {
            log("本地用户为null,请登录。");
        }
        //V3.4.5版本新增加getObjectByKey方法获取本地用户对象中某一列的值
//        String username = (String) BmobUser.getObjectByKey("username");
//        CommonUtils.log("username："+username);
    }
}
