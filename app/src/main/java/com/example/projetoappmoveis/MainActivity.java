package com.example.projetoappmoveis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webview);
        webView.loadUrl("https://tranquil-sands-16337.herokuapp.com/");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.INVISIBLE);

        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://tranquil-sands-16337.herokuapp.com/");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, "Android");

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE); // mostra a progress
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE); // esconde a progress
            }

        });


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @JavascriptInterface
    public void jsShowToast(String toast) {
        new AlertDialog.Builder(this)
                .setTitle("Erro")
                .setMessage(toast)
                .setPositiveButton(android.R.string.ok, null)
                .create()
                .show();
    }

    @JavascriptInterface
    public void jsShowToast(String toast, String header) {
        new AlertDialog.Builder(this)
                .setTitle(header)
                .setMessage(toast)
                .setPositiveButton(android.R.string.ok, null)
                .create()
                .show();
    }

}