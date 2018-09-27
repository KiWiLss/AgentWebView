package com.kiwilss.agentwebview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.kiwilss.agentweb.AgentWeb;

public class MainActivity extends AppCompatActivity {

    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout llOuter = findViewById(R.id.ll_main_outer);



        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) llOuter, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()


                .setWebChromeClient(mWebChromeClient)

                .setWebViewClient(mWebViewClient)

                .createAgentWeb()
                .ready()
                .go("http://www.jd.com");

    }

    private WebViewClient mWebViewClient=new WebViewClient(){
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
            Log.e("MMM", "onPageStarted: "+url );
        }
    };
    private WebChromeClient mWebChromeClient=new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //do you work
            Log.e("MMM", "onProgressChanged: "+newProgress);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {

            WebView webView = mAgentWeb.getWebCreator().getWebView();
            String url = webView.getUrl();
            String originalUrl = webView.getOriginalUrl();
            Log.e("MMM", "onKeyDown: "+url +"|||"+originalUrl);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
