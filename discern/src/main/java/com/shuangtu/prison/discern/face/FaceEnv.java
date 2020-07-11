package com.shuangtu.prison.discern.face;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.sun.jna.ptr.IntByReference;

public class FaceEnv {
    private static int index = 0;
    private static FaceCompareEngine engine = null;
    private static final String TAG = "FaceEnv";
    private static IntByReference x = new IntByReference(0),
            y = new IntByReference(0),
            width = new IntByReference(0),
            height = new IntByReference(0);
    /**
     * 初始化人脸识别dll环境，加载模型文件
     * @param fragment
     * @return
     */
    public static  boolean init(Fragment fragment)
    {
        boolean ret = false;
        if (engine == null) {
            try {
                //content fragment.
                FragmentActivity content = fragment.getActivity();
                String fdFile = "fd_2_00.dat";
                ret = copyAssetAndWrite(content, fdFile);
                String pdFile = "pd_2_00_pts5.dat";
                ret = copyAssetAndWrite(content, pdFile);
                String frFile = "fr_2_10.dat";
                ret = copyAssetAndWrite(content, frFile);
                File dataFile = new File(content.getCacheDir(), fdFile);
                String qiu002 = "qiu_002.jpg";
                ret = copyAssetAndWrite(content, qiu002);
                String qiu004 = "qiu_004.jpg";
                ret = copyAssetAndWrite(content, qiu004);

                Log.d(TAG, "getCacheDir:" + content.getCacheDir());

                if (dataFile.exists()) {
                    Log.d(TAG,  "file:" + dataFile.getAbsolutePath() + " exists.");
                } else {
                    Log.e(TAG,  "file:" + dataFile.getAbsolutePath() + " doesn't  exist.");
                }


                try {

                    engine = new FaceCompareEngine(content.getCacheDir() + "/" + fdFile, content.getCacheDir() + "/" + pdFile, content.getCacheDir() + "/" + frFile);
                } catch (Exception e) {

                    Log.e(TAG,  "init so fail:" + e.getMessage());
                }

                if (engine != null) {
                    Log.d(TAG,"init so ok!");
                    ret = true;
                } else {
                    Log.d(TAG, "init so fail!");
                    ret = false;
                }
                //float sim = engine.Compare(content.getCacheDir() + "/" + qiu002, content.getCacheDir() + "/" + qiu004);
                //Log.d(TAG, content.getCacheDir() + "/" + qiu002 + " and " + content.getCacheDir() + "/" + qiu004 + " similarity is:" + sim + "%");
            } catch (Exception e) {
                Log.d(TAG, "init env error:" + e.getMessage());
                ret = false;
            }
        }

        return ret;
    }

    public static Rect FeatureEx(String srcImageFile, float[] feature)
    {
        Rect  rect = new Rect();

        if ( null != engine)
        {
            if (engine.FeatureEx(srcImageFile,x,y,width,height,feature))
            {
                rect.left = x.getValue();
                rect.top = y.getValue();
                rect.right = rect.left + width.getValue();
                rect.bottom = rect.top + height.getValue();
            }
        }
        else
        {
            Log.d("","face engine not init yet!");
        }

        return rect;
    }

    /**
     * 将asset文件写入缓存
     */
    private static boolean copyAssetAndWrite(FragmentActivity content, String fileName){
        try {
            File cacheDir=content.getCacheDir();
            if (!cacheDir.exists()){
                cacheDir.mkdirs();
            }
            File outFile =new File(cacheDir,fileName);
            if (!outFile.exists()){
                boolean res=outFile.createNewFile();
                if (!res){
                    return false;
                }
            }else {
                if (outFile.length()>10){//表示已经写入一次
                    return true;
                }
            }
            InputStream is=content.getAssets().open(fileName);
            FileOutputStream fos = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int byteCount;
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
            is.close();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * 将asset文件写入缓存
     */
    public static String saveBitmap(FragmentActivity content, Bitmap mBitmap){
        String filePath = "";
        String fileName = String.format("%06d",index++) + ".png";

        try {
            File cacheDir = content.getCacheDir();
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }

            filePath = cacheDir + "/" +  fileName;
            File outFile = new File(filePath);

            if (!outFile.exists()) {
                boolean res = outFile.createNewFile();
                if (!res) {
                    return filePath;
                }
            } else {
                if (outFile.length() > 10) {//表示已经写入一次
                    return filePath;
                }
            }

            FileOutputStream fOut = null;
            fOut = new FileOutputStream(outFile);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
        }
        catch(Exception e)
        {
            Log.e(TAG,  "create file " + e.getMessage());
         }

        return filePath;
    }
}
