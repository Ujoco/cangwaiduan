package com.shuangtu.prison.home.qfragment.Discipline;

import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tts.TTSHandler;
import com.example.tts.utils.OnVoicePlayListener;
import com.shuangtu.prison.common.constant.Constant;
import com.shuangtu.prison.common.net.FishLoadManager;
import com.shuangtu.prison.common.q.QActivity;
import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.discern.face.CameraFragment;
import com.shuangtu.prison.discern.model.ModelNoticeFaceDiscern;
import com.shuangtu.prison.home.R;
import com.shuangtu.prison.home.adapter.CallmanagementAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/// 点名管理
public class QFragmentCallmanagement extends QActivity {


    private RecyclerView mRecycview;

    //虚拟数据 服务器接口还没做好
    private ArrayList<String> mlist = new ArrayList<>();
    @Override
    public int getLayoutRes() {
        return R.layout.activity_qfragment_callmanagement;
    }

    @Override
    public void initView() {
        mRecycview = root.findViewById(R.id.recycview);

//        mBtnFix = root.findViewById(R.id.btnFix);
//
//        mBtnFix.setText("违规录入");
        //虚拟数据 服务器接口还没做好
        for(int i = 0; i<60;i++){
            mlist.add("https://i.picsum.photos/id/277/200/200.jpg");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initData() {


   //     mRv_prison = findViewById(R.id.rv_prison);
        GridLayoutManager mLayoutManager1 = new GridLayoutManager(root.getContext(),6,GridLayoutManager.VERTICAL,false);
        mRecycview.setLayoutManager(mLayoutManager1);
        //适配器
        CallmanagementAdapter adapter = new CallmanagementAdapter();
        //虚拟数据 服务器接口还没做好
        adapter.setdata(mlist);
        mRecycview.setAdapter(adapter);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void networkMessage() {

    }





}
