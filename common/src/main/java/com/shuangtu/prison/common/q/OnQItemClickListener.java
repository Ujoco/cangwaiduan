package com.shuangtu.prison.common.q;

import android.view.View;

public interface OnQItemClickListener<T>  {

    void onClick(View view, T bean, int position);
}
