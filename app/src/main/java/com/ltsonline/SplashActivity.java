package com.ltsonline;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ltsonline.netUtils.MyPreferences;
import com.ltsonline.netUtils.RuntimePermissionsActivity;

public class SplashActivity extends RuntimePermissionsActivity {

    MyPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);
        myPreferences = new MyPreferences(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity.super.requestAppPermissions(new
                                String[]{Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.GET_ACCOUNTS
                        }, R.string.runtime_permissions_txt
                        , 20);

            }
        }, 1000);
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        if (requestCode == 20) {

            if (myPreferences.getPreferences(MyPreferences.welcomeScreen).equals("")) {
                Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
