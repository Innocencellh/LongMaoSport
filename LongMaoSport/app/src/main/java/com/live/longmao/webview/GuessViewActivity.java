package com.live.longmao.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.live.longmao.R;

public class GuessViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_guess);
        final WebView webview = (WebView) findViewById(R.id.web_view);
        ImageView imageView = (ImageView) findViewById(R.id.tv_back);
        webview.loadUrl("file:///android_asset/help/guess-instruction.html");
        /*webview.loadUrl("http://www.baidu.com");*/
        //版本兼容  比较你所用的手机版本号和你所设置的版本号进行比对
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //监听Html5进行点击返回的方法
                if (webview.canGoBack()==true){
                    webview.goBack();
                }else {
                    finish();
                }
            }
        });
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //支持JavaScript
        webview.getSettings().setJavaScriptEnabled(true);
        //自适应屏幕
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setLoadWithOverviewMode(true);
        //设置可以支持缩放
        webview.getSettings().setSupportZoom(true);
        //扩大比例的缩放
        webview.getSettings().setUseWideViewPort(true);
        //设置是否出现缩放工具
        webview.getSettings().setBuiltInZoomControls(true);

    }
}
