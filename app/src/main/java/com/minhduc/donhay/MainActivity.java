package com.minhduc.donhay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.view.Gravity;

import androidx.activity.OnBackPressedCallback;
import androidx.core.view.WindowCompat;

public class MainActivity extends Activity {
    private WebView webView;
    private FrameLayout root;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setStatusBarColor(Color.rgb(7,17,31));
        window.setNavigationBarColor(Color.rgb(7,17,31));
        WindowCompat.setDecorFitsSystemWindows(window, true);

        root = new FrameLayout(this);
        root.setBackgroundColor(Color.rgb(7,17,31));

        webView = new WebView(this);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);

        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(true);
        s.setMediaPlaybackRequiresUserGesture(false);
        s.setBuiltInZoomControls(false);
        s.setDisplayZoomControls(false);
        s.setSupportZoom(false);
        s.setLoadWithOverviewMode(false);
        s.setUseWideViewPort(false);
        s.setCacheMode(WebSettings.LOAD_DEFAULT);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        root.addView(webView, new FrameLayout.LayoutParams(-1, -1));
        setContentView(root);

        showSplash();
        webView.loadUrl("file:///android_asset/index.html");

        new Handler().postDelayed(() -> hideSplash(), 1200);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override public void handleOnBackPressed() {
                if (webView.canGoBack()) webView.goBack();
                else finish();
            }
        });
    }

    private void showSplash() {
        final LinearLayout splash = new LinearLayout(this);
        splash.setOrientation(LinearLayout.VERTICAL);
        splash.setGravity(Gravity.CENTER);
        splash.setBackgroundColor(Color.rgb(7,17,31));

        ImageView icon = new ImageView(this);
        icon.setImageResource(com.minhduc.donhay.R.drawable.app_icon);
        int size = (int)(112 * getResources().getDisplayMetrics().density);
        LinearLayout.LayoutParams ip = new LinearLayout.LayoutParams(size, size);
        ip.bottomMargin = (int)(20 * getResources().getDisplayMetrics().density);
        splash.addView(icon, ip);

        TextView title = new TextView(this);
        title.setText("Độ Nhạy Minh Đức");
        title.setTextColor(Color.WHITE);
        title.setTextSize(25);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(null, 1);
        splash.addView(title, new LinearLayout.LayoutParams(-2, -2));

        TextView sub = new TextView(this);
        sub.setText("Cài đặt độ nhạy theo đúng máy của bạn");
        sub.setTextColor(Color.rgb(155,180,210));
        sub.setTextSize(14);
        sub.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(-2, -2);
        sp.topMargin = (int)(8 * getResources().getDisplayMetrics().density);
        splash.addView(sub, sp);

        root.addView(splash, new FrameLayout.LayoutParams(-1, -1));
        splash.setTag("splash");
    }

    private void hideSplash() {
        View splash = root.findViewWithTag("splash");
        if (splash != null) {
            splash.animate().alpha(0f).setDuration(300).withEndAction(() -> root.removeView(splash)).start();
        }
    }

    @Override
    protected void onDestroy() {
        if (webView != null) webView.destroy();
        super.onDestroy();
    }
}
