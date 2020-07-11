package com.shuangtu.prison.common.constant;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.LogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.shuangtu.prison.common.R;
import com.shuangtu.prison.common.q.QActivity;
import com.shuangtu.prison.common.utils.UnCeHandler;
import com.tencent.bugly.crashreport.CrashReport;

import org.litepal.LitePal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;

import static com.shuangtu.prison.common.constant.Constant.FISH_DEBUG;

public class QApplication extends Application {

    private static final String TAG = "QApplication";

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    @Override
    public void onCreate() {
        super.onCreate();

        Thread.setDefaultUncaughtExceptionHandler(new UnCeHandler());

        CrashUtils.init();
        //Log设置
        LogUtils.getConfig().setSaveDays(7);
        LogUtils.getConfig().setLogSwitch(FISH_DEBUG);
        LogUtils.getConfig().setConsoleSwitch(FISH_DEBUG);

        Global.init(this);

        if (FISH_DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);

        AutoSizeConfig.getInstance().getUnitsManager()
                .setSupportDP(false)
                .setSupportSP(false)
                .setSupportSubunits(Subunits.MM);


        LitePal.initialize(this);

        LogUtils.dTag("Application onCreate");

        CrashReport.initCrashReport(getApplicationContext(), "544b2edfb8", false);

        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                LogUtils.aTag("QApp", throwable.toString());
            }
        });



        modulesApplicationInit();
    }

    private static final String[] MODULESLIST =
            {"com.example.tts.TTSHandler",};

    private void modulesApplicationInit() {
        // 反射获取方法
        for (int i = 0; i < MODULESLIST.length; i++) {
            try {
                Class<?> threadClazz = Class.forName(MODULESLIST[i]);
                Method method = threadClazz.getMethod("initialization");
                method.invoke(null);
            } catch (Exception e) {
                LogUtils.e(TAG, "" + e.toString());
            }

        }

    }


    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.gray, android.R.color.white);//全局设置主题颜色
                layout.setHeaderHeight(50);
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                layout.setFooterHeight(50);
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    public static List<QActivity> activities = new ArrayList<>();

}
