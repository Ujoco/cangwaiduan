package com.shuangtu.prison.discern.dialog;

import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.ToastUtils;
import com.shuangtu.prison.common.constant.Constant;
import com.shuangtu.prison.common.constant.Global;
import com.shuangtu.prison.common.net.GlideApp;
import com.shuangtu.prison.common.notice.NoticeLogin;
import com.shuangtu.prison.discern.QDialogFragment;
import com.shuangtu.prison.discern.R;
import com.shuangtu.prison.discern.face.CameraFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class STLoginFaceDialog extends QDialogFragment {

    private TextView tvTitle;
    private ImageView ivFace;
    private ImageView ivStatus;
    private TextView tvStatus;
    private Button btnClose;

    @Override
    public int getLayoutRes() {
        return R.layout.login_face;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView() {
        tvTitle = viewRoot.findViewById(R.id.tvTitle);
        tvTitle.setText("请对准摄像头");
        ivFace = viewRoot.findViewById(R.id.ivFace);
        GlideApp.with(getActivity()).asGif().load(R.mipmap.discern_face).into(ivFace);
        ivStatus = viewRoot.findViewById(R.id.ivStatus);
        tvStatus = viewRoot.findViewById(R.id.tvStatus);
        btnClose = viewRoot.findViewById(R.id.btnClose);

        getChildFragmentManager().beginTransaction().replace(R.id.frameFace, CameraFragment.newInstance(Constant.getMatch(), -1))
                .commitNowAllowingStateLoss();
    }


    @Override
    public void initData() {
        statusChange(0);
    }

    @Override
    public void initListener() {
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void networkMessage() {

    }

    private boolean isSuccess = false;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void noticeLogin(NoticeLogin notice) {
        if (notice.getType() == 1 && notice.isLogin() && !isSuccess) {
            isSuccess = true;
            statusChange(1);
            ToastUtils.showLong("人脸登陆成功！");
            Flowable.timer(500, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            if (isVisible()) {
                                dismiss();
                            }
                        }
                    });
        }
    }

    @Override
    public boolean registerEventBus() {
        return true;
    }

    private void statusChange(int status) {
        if (status == 0) {
            tvStatus.setText("认证中......");
            ivStatus.setVisibility(View.GONE);
        } else if (status == 1) {
            tvStatus.setText("人脸认证通过");
            ivStatus.setVisibility(View.VISIBLE);
        } else {
            tvStatus.setText("人脸认证失败");
            ivStatus.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
