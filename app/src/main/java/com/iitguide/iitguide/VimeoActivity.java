package com.iitguide.iitguide;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;

/**
 * Created by Ashank on 8/24/2015.
 */
public class VimeoActivity extends AppCompatActivity {

    HTML5WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mWebView = new HTML5WebView(this);

        Bundle extras = getIntent().getExtras();

        String value = extras.getString("PRIV_URL");


        if (savedInstanceState != null) {
            mWebView.restoreState(savedInstanceState);
        } else {
            mWebView.loadUrl(VimeoHelper.getExternalUrl(value));
            //mWebView.loadUrl("file:///data/bbench/index.html");
        }

        setContentView(mWebView.getLayout());

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }

   /* @Override
    public void onStop() {
        super.onStop();
        mWebView.stopLoading();
        mWebView.destroy();
    } */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.inCustomView()) {
                mWebView.hideCustomView();
                return true;
            }
         }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
       super.onBackPressed();
        mWebView.destroy();
       this.finish();
    }

    @Override
        protected void onPause() {
        super.onPause();
        mWebView.stopLoading();
    }


}
