package com.ltsonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            GlobalElements.setStatusBar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        if (intent.getStringExtra("activity").equals("about")) {
            GlobalElements.setActionBar(AboutUsActivity.this, "About US");
            webview.loadUrl("http://craftbox.in/server/lts/about_us.php");
        } else if (intent.getStringExtra("activity").equals("contact")) {
            GlobalElements.setActionBar(AboutUsActivity.this, "Contact US");
            webview.loadUrl("http://craftbox.in/server/lts/contact_us.php");
        } else {
            GlobalElements.setActionBar(AboutUsActivity.this, "Support");
            webview.loadUrl("http://craftbox.in/server/lts/contact_us.php");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
