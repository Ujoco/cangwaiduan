package com.shuangtu.prison.common.constant;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;


public class Global {

    public static Context mContext;

    public static float mDensity;

    public static float mScreenWidth;

    public static float mScreenHeight;
    private static View mStatusBarView;
    private static DisplayMetrics displayMetrics;

    public static void init(Context context) {
        mContext = context;
        initScreenSize();
    }

    public static final String APP_PACKAGE_NAME = "com.gdsnm.asvod";//包名

    /**
     * 检测某个应用是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static void launchapp() {
        if (isAppInstalled(mContext, APP_PACKAGE_NAME)) {
            mContext.startActivity(mContext.getPackageManager().getLaunchIntentForPackage(APP_PACKAGE_NAME));
        } else {

        }
    }

    /**
     * 16进制颜色转换int
     */
    public static int toColorFromString(String colorStr) {
        return Color.parseColor(colorStr);
    }

    public static float getScreenWidth() {
        return displayMetrics.widthPixels;
    }

    public static float getScreenHeight() {
        return displayMetrics.heightPixels;
    }

    private static void initScreenSize() {
        displayMetrics = mContext.getResources().getDisplayMetrics();
        mDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;
    }


    public static View inflate(int layoutResID, ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(layoutResID, parent, false);
    }

    public static View inflate(int layoutResID) {
        return inflate(layoutResID, null);
    }


    /**
     * 跳转Activity
     *
     * @param tClass
     * @param <T>
     */
    public static <T> void startActivity(Class<T> tClass) {
        Intent intent = new Intent(mContext, tClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    public static void startActivity(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (mContext.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            mContext.startActivity(intent);
        }
    }

    /**
     * 设置全屏
     *
     * @param window
     */
    public static void setFullScreen(Window window) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 退出全屏
     *
     * @param window
     * @param activity
     */
    public static void quitFullScreen(Window window, Activity activity) {
        final WindowManager.LayoutParams attrs = window.getAttributes();
        attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.setAttributes(attrs);
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        Global.setStatusBarColor(activity, Color.BLACK);
    }

    public static void setNoTitle(AppCompatActivity activity) {
        activity.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * 设置渗透全屏模式
     */
    public static void setNoStatusBarFullMode(Activity activity) {
        // sdk 4.4
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            if (mStatusBarView != null) {
                ViewGroup root = (ViewGroup) activity.findViewById(android.R.id.content);
                root.removeView(mStatusBarView);
            }
            return;
        }

        // sdk 5.x
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        }
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object obj = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");
            int id = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 设置Activity状态栏颜色
     *
     * @param activity
     * @param statusColor
     */
    public static void setStatusBarColor(Activity activity, int statusColor) {
        // sdk 5.x
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(statusColor);
            return;
        }
    }


    public static String getHtmlImageUrl(String content) {
        if (TextUtils.isEmpty(content)) return null;
        List<String> srcList = new ArrayList<String>(); //用来存储获取到的图片地址
        Pattern p = Pattern.compile("<(img|IMG)(.*?)(>|></img>|/>)");//匹配字符串中的img标签
        Matcher matcher = p.matcher(content);
        boolean hasPic = matcher.find();
        if (hasPic == true)//判断是否含有图片
        {
            while (hasPic) //如果含有图片，那么持续进行查找，直到匹配不到
            {
                String group = matcher.group(2);//获取第二个分组的内容，也就是 (.*?)匹配到的
                Pattern srcText = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");//匹配图片的地址
                Matcher matcher2 = srcText.matcher(group);
                if (matcher2.find()) {
                    srcList.add(matcher2.group(3));//把获取到的图片地址添加到列表中
                }
                hasPic = matcher.find();//判断是否还有img标签
            }

        }
        if (srcList != null && srcList.size() > 0) {
            return srcList.get(0);
        }
        return null;
    }


    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        calendar.set(Calendar.HOUR, -12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String result = format.format(today);
        return result;
    }

    /**
     * 获取未来 第 past 天的日期
     *
     * @param past
     * @return
     */
    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        calendar.set(Calendar.HOUR, -12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = format.format(today);
        return result;
    }

    /**
     * 获取1970
     */
    public static String getStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970, 0, 01);
        calendar.set(Calendar.HOUR, -12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = format.format(today);
        return result;
    }

    /**
     * Bitmap圆角处理
     *
     * @param bitmap
     * @return
     */
    public static Bitmap transform(Bitmap bitmap, float roundPx, float roundPy) {
        try {
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getHeight(), Bitmap.Config.ARGB_4444);
            Canvas canvas = new Canvas(output);
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight());
            final RectF rectF = new RectF(new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight()));
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(Color.BLACK);
            canvas.drawRoundRect(rectF, roundPx, roundPy, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            final Rect src = new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight());
            canvas.drawBitmap(bitmap, src, rect, paint);
            return output;
        } catch (Exception e) {
            return bitmap;
        }
    }

    public static String simpleTime(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm:ss");
        String sDate = sdf.format(date);
        return sDate;
    }

    public static String simpleTime2(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:MM:SS");
        String sDate = sdf.format(date);
        return sDate;
    }

    public static String simpleTime3(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        String sDate = sdf.format(date);
        return sDate;
    }

    public static String simpleTime4(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = sdf.format(date);
        return sDate;
    }

    public static String simpleTime5(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd");
        String sDate = sdf.format(date);
        return sDate;
    }
}
