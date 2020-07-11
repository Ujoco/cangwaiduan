package com.shuangtu.prison.discern.face;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;

import java.util.Arrays;
import java.util.List;

public class FaceCompareEngine {

    public interface FaceCompare extends Library
    {
        FaceCompare INSTANCE = (FaceCompare)Native.loadLibrary("FaceCompare",FaceCompare.class);
        Pointer FaceCompare_ctor(String fdFile, String pdFile, String frFile);
        //获取输入人脸区域及特征值
        boolean FeatureEx2(Pointer self, String srcImageFile, IntByReference x, IntByReference y, IntByReference width, IntByReference height, float[] feature);
    }

    private Pointer self;

    public FaceCompareEngine(String fdFile, String pdFile, String frFile)
    {
        //Native.loadLibrary("seeta_fa_lib",FaceAlignment.class);
        //Native.loadLibrary("seeta_facedet_lib",FaceDetection.class);
        //System.out.println(this.getClass().getClassLoader().getResources());
        self = FaceCompare.INSTANCE.FaceCompare_ctor(fdFile, pdFile, frFile);
        //faceDetection = FaceDetection.INSTANCE.
    }

    //获取输入人脸的特征值
    public boolean FeatureEx(String srcImageFile, IntByReference x, IntByReference y, IntByReference width, IntByReference height, float[] feature)
    {
        boolean ret = false;

        if (self != null)
            ret = FaceCompare.INSTANCE.FeatureEx2(self, srcImageFile, x, y, width, height, feature);

        return ret;
    }
}
