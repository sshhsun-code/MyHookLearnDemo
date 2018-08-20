package gamemaster.cmcm.com.myhooklearndemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import gamemaster.cmcm.com.myhooklearndemo.dynamic_proxy_hook.hook.HookHelper;
import gamemaster.cmcm.com.myhooklearndemo.dynamic_proxy_hook.hook.SecondActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            // 在这里进行Hook
            HookHelper.attachContextInActivity(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("sunqi_log", this + "onCreate", new Exception());
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // 注意这里使用的ApplicationContext 启动的Activity
                // 因为Activity对象的startActivity使用的并不是ContextImpl的mInstrumentation
                // 而是自己的mInstrumentation, 如果你需要这样, 可以自己Hook
                // 比较简单, 直接替换这个Activity的此字段即可.
                startActivity(intent);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        Log.e("sunqi_log", this + "attachBaseContext", new Exception());

    }
}
