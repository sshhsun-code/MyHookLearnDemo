package gamemaster.cmcm.com.myhooklearndemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import gamemaster.cmcm.com.myhooklearndemo.dynamic_proxy_hook.hook.HookHelper;

/**
 * Created by sunqi on 2018/8/20.
 */

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.e("sunqi_log", this + "attachBaseContext", new Exception());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("sunqi_log", this + "onCreate", new Exception());
        try {
            // 在这里进行Hook
//            HookHelper.attachContextInActivityThread();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
