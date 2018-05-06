package com.iot.quickhpu.utils;


import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.iot.quickhpu.callback.RSACryptographyCallback;

/**
 * @作者 m1563
 * @日期 2018/2/28
 * @描述 RSA 加密
 */
public class RSACryptographyUtils {

    // js加密
    public static String encrypt(WebView webView, final RSACryptographyCallback rsaCryptographyCallback, final String pw) {
        // 加载JS代码
        // 格式规定为:file:///android_asset/文件名.html
        webView.loadUrl("file:///android_asset/rsa.html");
        WebSettings webSettings = webView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                invokeJs(view,rsaCryptographyCallback,pw);
            }
        });
        return null;
    }

    private static void invokeJs(WebView webView, final RSACryptographyCallback rsaCryptographyCallback, String pw){
        // 调用html页面中的js函数update
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript("doEncrypt('" + pw + "')", new ValueCallback<String>() {
                @Override
                // value就是调用的函数的返回值
                public void onReceiveValue(String value) {
                    value = value.replaceAll("\"", "");
                    rsaCryptographyCallback.onSuccess(value);
                }
            });
        }
    }

}  