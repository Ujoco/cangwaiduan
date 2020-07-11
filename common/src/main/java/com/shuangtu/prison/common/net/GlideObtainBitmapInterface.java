package com.shuangtu.prison.common.net;

import android.graphics.drawable.Drawable;

public interface GlideObtainBitmapInterface<T> {
    void success(String uri, T resource);

    void fail(String uri,String error);
}
