package com.bawei.queshuhui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bawei.queshuhui.base.BaseActivity;
/**
 *  * @ActivityName: SecondActivity
 *  * @Description: Activity介绍
 *  * @author: 阙树辉
 *  * @date: 2019/11/26
 */
public class SecondActivity extends BaseActivity {

    private WebView webView;
    private String key;

    @Override
    protected void initData() {
        key = getIntent().getStringExtra("key");
        webView.loadUrl(key);
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.web);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(key);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.i("TAG","页面开始加载");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i("TAG","页面加载完成");
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.i("TAG","当前进度"+newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.i("TAG","接受到标题");
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                Log.i("TAG","接受到图标");
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_second;
    }
}
