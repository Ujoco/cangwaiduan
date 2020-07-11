package com.shuangtu.prison.home.fragment;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shuangtu.prison.common.q.QFragment;
import com.shuangtu.prison.home.R;

public class QFragmentWeb extends QFragment {


    public WebView viewWeb;
    private OnWebNoticeListener dataNoticeListener;

    public void setDataNoticeListener(OnWebNoticeListener dataNoticeListener) {
        this.dataNoticeListener = dataNoticeListener;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_web;
    }

    @Override
    public void initView() {
        viewWeb = root.findViewById(R.id.viewWeb);
    }

    @Override
    public void initData() {
        viewWeb.setBackgroundColor(0);
    }

    @Override
    public void initListener() {
        viewWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loadurlLocalMethod(view, url);
                return false;
            }
        });


    }

    public void loadurlLocalMethod(final WebView webView, final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(url);
            }
        });
    }


    @Override
    public void networkMessage() {
        if (dataNoticeListener != null) {
            dataNoticeListener.messageData(this);
        }
    }

    @Override
    public boolean registerEventBus() {
        return false;
    }

    @Override
    public boolean isBack() {
        return true;
    }


    public void clearCache() {
        viewWeb.loadData("", "text/html;charset=utf-8", "utf-8");
    }
}
