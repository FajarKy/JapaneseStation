package com.hafiizh.japanesestation.databinding;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hafiizh.japanesestation.common.Common;

/**
 * Created by HAFIIZH on 10/26/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public final class WebViewBindingAdapter {
    @SuppressLint("SetJavaScriptEnabled")
    @BindingAdapter(value = "data")
    public static void loadData(WebView web, String data) {
        String html = "<html>" + data + "</html>";
        if (data != null && !data.equals("")) {
            web.getSettings().setJavaScriptEnabled(true);
            web.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {

                }
            });
            web.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return true;
                }

                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    Common.showDialog(view.getContext(), "can't load article , please reload article");
                }
            });
            web.getSettings().setUseWideViewPort(true);
            web.getSettings().setBuiltInZoomControls(true);
            web.getSettings().setSupportZoom(true);
            web.loadData(html, "text/html", null);
        }
    }
}