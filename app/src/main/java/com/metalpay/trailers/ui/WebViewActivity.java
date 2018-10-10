package com.metalpay.trailers.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.metalpay.trailers.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {
    public static final String URL_EXTRA_KEY = "URL_EXTRA_KEY";
    public static final String TITLE_EXTRA_KEY = "TITLE_EXTRA_KEY";
    @BindView(R.id.webview) WebView mWebView;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if(bundle!= null) {
                getSupportActionBar().setTitle(bundle.getString(TITLE_EXTRA_KEY));
            }
        }

        if(bundle != null) {
            String url = bundle.getString(URL_EXTRA_KEY);
            if (mWebView.getSettings() != null) {
                mWebView.getSettings().setLoadsImagesAutomatically(true);
                mWebView.getSettings().setJavaScriptEnabled(true);
                mWebView.getSettings().setUseWideViewPort(true);
            }
            mWebView.setWebViewClient(new WebViewClient());
            mWebView.loadUrl(url);
        }else{
            finish();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
