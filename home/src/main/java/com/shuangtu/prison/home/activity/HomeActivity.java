package com.shuangtu.prison.home.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tts.TTSHandler;
import com.shuangtu.prison.common.constant.Arouter;
import com.shuangtu.prison.common.constant.Global;
import com.shuangtu.prison.common.constant.STUserManager;
import com.shuangtu.prison.common.model.ModelDeviceInfo;
import com.shuangtu.prison.common.model.ModelPolice;
import com.shuangtu.prison.common.model.ModelUserInfo;
import com.shuangtu.prison.common.net.FishLoadManager;
import com.shuangtu.prison.common.notice.NoticeLogin;
import com.shuangtu.prison.common.notice.NoticeLogout;
import com.shuangtu.prison.common.q.QActivity;
import com.shuangtu.prison.common.model.LoginModel;
import com.shuangtu.prison.common.net.Api;

import com.shuangtu.prison.home.Constant;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.adapter.AdapterHome;
import com.shuangtu.prison.home.adapter.PoliceinformationAdapter;
import com.shuangtu.prison.home.manager.QOvertTimeExitManager;
import com.shuangtu.prison.home.model.ModelNoticeOverExit;
import com.shuangtu.prison.home.socket.QSocketConnectManager;
import com.shuangtu.prison.home.socket.model.ModelConnect;
import com.shuangtu.prison.home.socket.model.ModelHeartbeat;
import com.trello.rxlifecycle3.android.ActivityEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Route(path = Arouter.ROUTER_HOME_MAIN)
public class HomeActivity extends HomeEventBusActivity {
    private RecyclerView rlList;
    private static final int spanCount = 4;


    private AdapterHome adapterHome;

    private LoginDailogFragment fragment;
    private LinearLayout loginFingerprint;
    private LinearLayout loginFace;
    private ConstraintLayout loginInfo;
    private Button btnLogout;
    private TextView tvLoginName;
    private ImageView ivHeader;
    private String user;
    private String pwd;
    private Disposable disposableConnectDeivce;
    private ConstraintLayout layoutPolice;
    private ImageView ivCover;
    private TextView tvName;
    private ModelPolice.RecordsBean modelPolice;
    private RecyclerView mRv_img;

    // 点击次数
    final static int COUNTS = 5;

    // 规定有效时间
    final static long DURATION = 1000;

    long[] mHits = new long[COUNTS];
    private AlertDialog dialog;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        rlList = findViewById(R.id.rlList);
        tvCurrentTime = findViewById(R.id.tvTime);
        tvRootName = findViewById(R.id.tvRootName);
        loginFingerprint = findViewById(R.id.loginFingerprint);
        loginFace = findViewById(R.id.loginFace);
        loginInfo = findViewById(R.id.loginInfo);
        btnLogout = findViewById(R.id.btnLogout);
        tvLoginName = findViewById(R.id.tvLoginName);
        ivHeader = findViewById(R.id.ivHeader);
        tvErrorConnect = findViewById(R.id.tvErrorConnect);
        layoutPolice = findViewById(R.id.layoutPolice);
        ivCover = findViewById(R.id.ivCover);
        tvName = findViewById(R.id.tvName);
        mRv_img = findViewById(R.id.rv_img);

        LayoutInflater inflater = LayoutInflater.from(getApplication());
        View view = inflater.inflate(R.layout.dialog_login, null);
        AlertDialog.Builder builder=new AlertDialog.Builder(HomeActivity.this);
        builder.setView(view);
        dialog = builder.create();
    }

    @Override
    public void initData() {
        TTSHandler.speckText("欢迎您登陆爽途仓外端App！");
        LogUtils.aTag(TAG, "initData");
        user = getIntent().getStringExtra("user");
        pwd = getIntent().getStringExtra("pwd");
        LogUtils.aTag(TAG, "user:" + user + " pwd:" + pwd);
        adapterHome = new AdapterHome(R.layout.home_adapter_item, null);
        GridLayoutManager managerHome = new GridLayoutManager(getApplicationContext(), spanCount);
        rlList.setLayoutManager(managerHome);
        rlList.setAdapter(adapterHome);
        Disposable disposable = Observable.timer(1, TimeUnit.SECONDS)
                .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        List<String> listData = Constant.modules;

                        adapterHome.replaceData(listData);
                        adapterHome.notifyDataSetChanged();
                    }
                });


        loginFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!STUserManager.getInstance().isLoginDevice()) {
                    LogUtils.aTag(TAG, "终端未登陆成功! 请稍后！");
                    ToastUtils.showLong("终端未登陆成功! 请稍后！");
                    return;
                }
                show_loginFace();
            }
        });

        loginFingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!STUserManager.getInstance().isLoginDevice()) {
                    LogUtils.aTag(TAG, "终端未登陆成功! 请稍后！");
                    ToastUtils.showLong("终端未登陆成功! 请稍后！");
                    return;
                }
                show_loginFingerprint();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!STUserManager.getInstance().isLoginDevice()) {
                    LogUtils.aTag(TAG, "终端未登陆成功! 请稍后！");
                    ToastUtils.showLong("终端未登陆成功! 请稍后！");
                    return;
                }
                show_logout();
            }
        });

        tvCurrentTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuousClick(COUNTS, DURATION);
            }

            private void continuousClick(int counts, long duration) {
                //每次点击时，数组向前移动一位
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                //为数组最后一位赋值
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
                    mHits = new long[COUNTS];//重新初始化数组
                    dialog.show();

                }
            }
        });

        STUserManager.getInstance().logoutDev();
        STUserManager.getInstance().logout();
    }


    @Override
    public void initListener() {

        QOvertTimeExitManager.getInstance().time = System.currentTimeMillis();

        adapterHome.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!STUserManager.getInstance().isLoginDevice()) {
                    LogUtils.aTag(TAG, "终端未登陆成功! 请稍后！");
                    ToastUtils.showLong("终端未登陆成功! 请稍后！");
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), HomeClassifyActivity.class);
                intent.putExtra("index", position);
                HomeActivity.this.startActivity(intent);


            }
        });


//        adapterHome.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//                switch (position){
//                    case  0:
//
//                        break;
//
//                    case  1:
//                        Intent intent = new Intent(getApplicationContext(), DisciplineActivity.class);
//                        intent.putExtra("index", position);
//                        HomeActivity.this.startActivity(intent);
//                        break;
//                }
//            }
//        });
    }


    @Override
    public boolean registerEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void logoutNotice(NoticeLogout model) {
        LogUtils.aTag(TAG, "logoutNotice:" + model.toString());
        if (model.isSuccess()) {
            if (model.getType() == 1) {
                STUserManager.getInstance().logout();
            } else {
                STUserManager.getInstance().logoutDev();
            }

            if (!STUserManager.getInstance().isLoginDevice()) {

                ToastUtils.showLong("设备正在重新登陆");
                messageLogin(user, pwd, 2);
            }

            loginChnage(new NoticeLogin(model.getType(), false));

        } else {
            messageLogout();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginChnage(NoticeLogin model) {
        LogUtils.aTag(TAG, model.toString());

//        if (STUserManager.getInstance().isLogin()) {
//            loginFingerprint.setVisibility(View.GONE);
//            loginFace.setVisibility(View.GONE);
//          //  loginInfo.setVisibility(View.VISIBLE);
//            FishLoadManager.loadPicture(model.getCover(), ivHeader);
//            tvLoginName.setText(model.getName());
//            messageUserInfo();
//            EventBus.getDefault().post(new ModelNoticeOverExit());
//        } else {
//            loginFingerprint.setVisibility(View.VISIBLE);
//            loginFace.setVisibility(View.VISIBLE);
//            loginInfo.setVisibility(View.GONE);
//
//        }


        if (STUserManager.getInstance().isLoginDevice()) {
            LogUtils.aTag(TAG, "connectService");
            QSocketConnectManager.getManager().connectService(1);
        } else {
            LogUtils.aTag(TAG, "colse");
            QSocketConnectManager.colse();
            layoutPolice.setVisibility(View.GONE);
        }

    }

    @Override
    public void networkMessage() {
        if (STUserManager.getInstance().isLoginDevice()) {
            LogUtils.aTag(TAG, "默认是登陆的");
            loginChnage(new NoticeLogin(2, true));

        } else {
            LogUtils.aTag(TAG, "无token");
            messageLogin(user, pwd, 2);
            loginChnage(new NoticeLogin(2, false));
        }

        if (STUserManager.getInstance().isLogin()) {
            messageUserInfo();
        }

    }

    private void messageLogin(final String user, final String pwd, final int type) {

        LogUtils.aTag("用户登陆信息", user, pwd);

        LoginModel loginModel = null;

        if (type == 1) {
            loginModel = STUserManager.getInstance().getModelUser();
        } else {
            loginModel = STUserManager.getInstance().getModelDevice();
        }

        if (loginModel == null || loginModel.getToken().isEmpty()) {

            final SweetAlertDialog dialog = new SweetAlertDialog(this)
                    .setTitleText("提示")
                    .setContentText(type == 1 ? "正在登陆请耐心等候！" : "正在登陆服务器");
            dialog.show();

            Api.getInstance().login(user, pwd, type)
                    .compose(this.<LoginModel>bindUntilEvent(ActivityEvent.DESTROY))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<LoginModel>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(LoginModel loginModel) {

                            if (dialog.isShowing()) {
                                dialog.dismissWithAnimation();
                            }

                            dimssDialog();

                            if (type == 1) {
                                STUserManager.getInstance().setToken_user(loginModel.getToken());
                                STUserManager.getInstance().setModelUser(loginModel);
                            } else {
                                STUserManager.getInstance().setToekn_device(loginModel.getToken());
                                STUserManager.getInstance().setModelDevice(loginModel);

                            }

                            LogUtils.dTag(TAG, "登录成功：" + loginModel.toString());
                            hanlderLogin(type);
                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtils.showLong("登陆失败!");

                            if (dialog.isShowing()) {
                                dialog.dismissWithAnimation();
                            }
                            dimssDialog();

                            if (type == 2) {
                                ToastUtils.showLong("设备登陆失败！ 10s后尝试重新连接！");
                                if (disposableConnectDeivce == null || disposableConnectDeivce.isDisposed()) {
                                    connectLoginDevice();
                                }
                            }

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            hanlderLogin(type);
        }
    }

    private void hanlderLogin(int type) {
        if (type == 1) {
            EventBus.getDefault().post(new NoticeLogin(type, STUserManager.getInstance().isLogin()));
        } else {
            EventBus.getDefault().post(new NoticeLogin(type, STUserManager.getInstance().isLoginDevice()));
        }


        /// 设备登陆 获取监室信息
        if (type == 2) {
            stopDisposable(disposableConnectDeivce);
            messageDeviceInfo();
        }
    }

    /// 退出登陆
    private void messageLogout() {

        Api.getInstance().getLogout()
                .compose(this.<String>bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String model) {
                        STUserManager.getInstance().reset();
                        EventBus.getDefault().post(new NoticeLogout(true, 1));
                        ToastUtils.showLong("退出登陆成功");

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showLong("退出失败");
                        LogUtils.aTag(TAG, e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /// 获取用户信息
    private void messageUserInfo() {

        if (!STUserManager.getInstance().isLogin()) {
            return;
        }

        Disposable disposable = Api.getInstance().getUserArea()
                .compose(this.<ModelUserInfo>bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ModelUserInfo>() {
                    @Override
                    public void accept(ModelUserInfo model) throws Exception {
                        LogUtils.aTag(TAG, model.toString());
                        FishLoadManager.loadCirclCorner(model.getAvatar(), ivHeader);
                        tvLoginName.setText(model.getUserName());
                    }
                });
    }

    private void messageDeviceInfo() {
        Api.getInstance().getRoomInfo()
                .compose(this.<ModelDeviceInfo>bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ModelDeviceInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModelDeviceInfo model) {
                        STUserManager.getInstance().setDeviceInfo(model);
                        LogUtils.aTag(TAG, model.toString());
                        onMessagePolice();
                        //STUserManager.getInstance().setToken_user(model.get);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.eTag(TAG, "获取监狱信息失败:" + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopDisposable(disposableConnectDeivce);
        QSocketConnectManager.colse();
    }

    /// 设备重连登陆定时器
    private void connectLoginDevice() {

        Observable observable = Observable.interval(10, 10, TimeUnit.SECONDS) //0延迟  每隔1秒触发
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())//操作UI主要在UI线程
                .take(Integer.MAX_VALUE);

        observable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposableConnectDeivce = d;
            }

            @Override
            public void onNext(Long o) {
                if (isFinishing()) {
                    return;
                }
                ToastUtils.showLong("开始重新尝试登陆服务器！");
                LogUtils.aTag(TAG, "开始重连");
                messageLogin(user, pwd, 2);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    private void onMessagePolice() {
        Api.getInstance().getPolice(STUserManager.getInstance().getDeviceInfo().getId())
                .compose(this.<ModelPolice>bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ModelPolice>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModelPolice model) {
                        LogUtils.aTag(TAG, "" + model.toString());
                        if (model.getRecords() != null && model.getRecords().size() > 0) {
                            modelPolice = model.getRecords().get(0);
                            // layoutPolice.setVisibility(View.VISIBLE);
                            FishLoadManager.loadPicture(Global.mContext, modelPolice.getAvatar(), ivCover);
                            LogUtils.aTag(TAG, model.toString());
//                            tvName.setText(modelPolice.getUserName());
                            LogUtils.aTag(TAG, "获取到多少条数据A." + model.getRecords().size() + model.getRecords().get(0).getAvatar());
                            LogUtils.aTag(TAG, "获取到多少条数据B." + STUserManager.getInstance().getDeviceInfo().getId());
                            LinearLayoutManager SetLayoutManagersupervision = (new LinearLayoutManager(getApplicationContext()));
                            SetLayoutManagersupervision.setOrientation(LinearLayoutManager.HORIZONTAL);
                            mRv_img.setLayoutManager(SetLayoutManagersupervision);

                            PoliceinformationAdapter policeinformationAdapter = new PoliceinformationAdapter();
                            //    mChoiceSupervisionAdapter = new ChoiceSupervisionAdapter();
                            policeinformationAdapter.setData(model.getRecords());
                            //    mChoiceSupervisionAdapter.setData(supervisionlist);
                            mRv_img.setAdapter(policeinformationAdapter);
                        } else {
                            modelPolice = null;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.eTag(TAG, "获取监狱信息失败:" + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
