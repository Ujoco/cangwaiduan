package com.shuangtu.prison.home.qfragment.fix;

import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.tts.TTSHandler;
import com.example.tts.utils.OnVoicePlayListener;
import com.shuangtu.prison.common.constant.Constant;
import com.shuangtu.prison.common.net.FishLoadManager;
import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.discern.face.CameraFragment;
import com.shuangtu.prison.discern.model.ModelNoticeFaceDiscern;
import com.shuangtu.prison.home.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/// 信息确认
public class QFragmentInfoFix2 extends QFragment {

    private FrameLayout frameLayout;
    private TextView btnCall;
    private TextView tvInfo;
    private CameraFragment cameraFragment;
    private ImageView ivCover;
    private static final int Index = 2;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_info_fix;
    }

    @Override
    public boolean registerEventBus() {
        return true;
    }

    @Override
    public void initView() {
        frameLayout = root.findViewById(R.id.frameCamera);
        btnCall = root.findViewById(R.id.btnFix);
        tvInfo = root.findViewById(R.id.tvInfo);
        ivCover = root.findViewById(R.id.ivCover);
        btnCall.setText("出监确认");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initData() {
        cameraFragment = CameraFragment.newInstance(Constant.getFaceDiscern(), Index);
        getChildFragmentManager().beginTransaction().replace(R.id.frameCamera, cameraFragment)
                .commitNowAllowingStateLoss();
    }

    @Override
    public void initListener() {
        TTSHandler.getInstance().setOnVoicePlayListener(new OnVoicePlayListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPlayEnd() {
                disposable = Flowable.timer(2, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                cameraFragment.isRequest = false;
                                tvInfo.setVisibility(View.GONE);
                                btnCall.setVisibility(View.GONE);
                            }
                        });
            }
        });
    }

    @Override
    public void networkMessage() {

    }

    Disposable disposable;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void noticeFix(ModelNoticeFaceDiscern call) {
        if (call.getSuccess() != Index) {
            return;
        }
        btnCall.setVisibility(View.VISIBLE);
        String text = "姓名：" + call.getData().getUser_name() + "\n\n编号：+ " + call.getData().getUid() + "\n\n时间：" + call.getData().getRollTime();
        tvInfo.setVisibility(View.VISIBLE);
        tvInfo.setText(text);
        TTSHandler.speckText(call.getData().getUser_name() + "出监确定成功！");
        ivCover.setVisibility(View.VISIBLE);
        FishLoadManager.loadPicture(call.getData().getAvatar(), ivCover);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        TTSHandler.getInstance().setOnVoicePlayListener(null);
    }
}
