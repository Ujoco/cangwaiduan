package com.shuangtu.prison.common.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.shuangtu.prison.common.R;
import com.shuangtu.prison.common.constant.Global;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FishLoadManager {

    public static void loadImage(ImageView imageView, String url) {
        Glide.with(Global.mContext).asBitmap().load(encodeUrl(url)).into(imageView);
    }

    public static void loadPicture(Context context, Object uri, ImageView iv) {
        load(context, uri, iv, null);
    }

    public static void loadPicture(Object uri, ImageView iv) {
        load(Global.mContext, uri, iv, null);
    }

    public static void loadCirclCorner(Object uri, ImageView iv) {
        load(Global.mContext, uri, iv, new GlideRoundTransform());
    }

    public static void loadCirclCorner(Object uri, ImageView iv, float roundPx) {
        loadCirclCorner(uri, iv, roundPx, roundPx);
    }

    public static void loadCirclCorner(Object uri, ImageView iv, float roundPx, float roundPy) {
        load(Global.mContext, uri, iv, new GlideRoundTransform(roundPx, roundPy));
    }

    private static void load(Context context, Object uri, ImageView iv, GlideRoundTransform transform) {
//        if(uri instanceof String){
//            uri = checkUrlAddress((String) uri);
//        }

        GlideRequest glideRequest = GlideApp.with(context)
                .asBitmap()
                .load(uri)
                .placeholder(R.mipmap.icon_placeholder);

        if (transform != null) {
            glideRequest.transform(transform);
        }
        glideRequest.into(iv);
    }

    public static String encodeUrl(String url) {
        return Uri.encode(url, "-![.:/,%?&=]");
    }

    public static void obtainDrawable(final Object uri, @NonNull final GlideObtainBitmapInterface obtainBitmapInterface) {
        GlideApp.with(Global.mContext)
                .asDrawable()
                .load(uri)
                .into(getObtainDrawable(uri, obtainBitmapInterface));
    }

    public static void obtainBitmap(final Object uri, @NonNull final GlideObtainBitmapInterface obtainBitmapInterface) {
        GlideApp.with(Global.mContext)
                .asBitmap()
                .load(uri)
                .into(getObtainBitmap(uri, obtainBitmapInterface));
    }

    private static Target getObtainBitmap(final Object uri, final GlideObtainBitmapInterface obtainBitmapInterface) {
        return new SimpleTarget<Bitmap>() {

            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                if (obtainBitmapInterface != null) {
                    obtainBitmapInterface.success(String.valueOf(uri), resource);
                }
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                if (obtainBitmapInterface != null) {
                    obtainBitmapInterface.fail(String.valueOf(uri), "fail");
                }
            }
        };
    }

    private static Target getObtainDrawable(final Object uri, final GlideObtainBitmapInterface obtainBitmapInterface) {
        return new SimpleTarget<Drawable>() {

            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                if (obtainBitmapInterface != null) {
                    obtainBitmapInterface.success(String.valueOf(uri), resource);
                }
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                if (obtainBitmapInterface != null) {
                    obtainBitmapInterface.fail(String.valueOf(uri), "fail");
                }
            }
        };
    }
}
