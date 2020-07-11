package com.shuangtu.prison.common.net;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.shuangtu.prison.common.constant.Global;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class GlideRoundTransform extends BitmapTransformation {

    private static final String ID = "com.bumptech.glide.transformations.GlideRoundTransform";
    private static byte[] ID_BYTES;


    static {
        try {
            ID_BYTES = ID.getBytes(STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private float roundPx;
    private float roundPy;

    public GlideRoundTransform() {
        roundPx = 5;
        roundPy = 5;
    }


    public GlideRoundTransform(float roundPx, float roundPy) {
        this.roundPx = roundPx;
        this.roundPy = roundPy;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return Global.transform(toTransform, roundPx, roundPy);
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof GlideRoundTransform;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }
}
