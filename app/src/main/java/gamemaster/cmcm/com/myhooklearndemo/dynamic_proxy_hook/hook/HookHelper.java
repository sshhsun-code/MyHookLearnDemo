package gamemaster.cmcm.com.myhooklearndemo.dynamic_proxy_hook.hook;

import android.app.Instrumentation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 偷梁换柱
 * Created by sunqi on 2018/8/20.
 */

public class HookHelper {

    public static void attachContext() throws Exception {
        // 先获取到当前的ActivityThread对象

        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Method threadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
        threadMethod.setAccessible(true);
        //currentActivityThread是一个static函数所以可以直接invoke，不需要带实例参数

        Object activityThread = threadMethod.invoke(null);

        // 拿到原始的 mInstrumentation字段

        Field mInstrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
        mInstrumentationField.setAccessible(true);
        Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(activityThread);

        // 创建代理对象

        Instrumentation instrumentationHook = new InstrumentationHook(mInstrumentation);

        // 偷梁换柱，注意！！！偷梁换柱时，替换的对象也一定是Instrumentation类型的！！！

        mInstrumentationField.set(activityThread, instrumentationHook);
    }
}
