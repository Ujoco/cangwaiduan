package com.shuangtu.prison.common.utils;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.shuangtu.prison.common.constant.Global;
import com.shuangtu.prison.common.constant.QApplication;


/*处理崩溃重叠*/
public class UnCeHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    public static final String TAG = "CatchExcep";


    public UnCeHandler() {
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }

            Intent intent = new Intent(Global.mContext, QApplication.activities.get(0).getClass());
            @SuppressLint("WrongConstant") PendingIntent restartIntent = PendingIntent.getActivity(
                    Global.mContext, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
            //退出程序
            AlarmManager mgr = (AlarmManager) Global.mContext.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
                    restartIntent); // 1秒钟后重启应用
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则没有处理返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                ToastUtils.showLong("很抱歉,程序出现异常,即将重启!");
                Looper.loop();
            }
        }.start();

        StringBuilder sb = new StringBuilder();
        sb.append("---------------错误捕获---------------");
        sb.append("\n");
        for (int i = ex.getStackTrace().length - 1; i >= 0; i--) {
            sb.append(ex.getStackTrace().length - i + "：");
            StackTraceElement element = ex.getStackTrace()[i];
            sb.append("\t文件名：");
            sb.append(element.getFileName());
            sb.append("\n");
            sb.append("\t类名：");
            sb.append(element.getClassName());
            sb.append("\n");
            sb.append("\t行数：");
            sb.append(element.getLineNumber());
            sb.append("\n");
            sb.append("\t方法名：");
            sb.append(element.getMethodName());
            sb.append("\n");
        }
        sb.append("\n\t");
        sb.append(ex.toString());
        LogUtils.e(TAG, sb.toString());
        sb.append("---------------捕获成功----------------");
        return true;
    }


}
