package com.ltsonline;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ltsonline.custom.FontSource;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.signup_finish)
    ImageView signupFinish;
    @BindView(R.id.signup_txt)
    TextView signupTxt;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.input_layout_name)
    TextInputLayout inputLayoutName;
    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_layout_email)
    TextInputLayout inputLayoutEmail;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.input_conpassword)
    EditText inputconPassword;
    @BindView(R.id.input_layout_password)
    TextInputLayout inputLayoutPassword;
    @BindView(R.id.input_layout_conpassword)
    TextInputLayout input_layout_conPassword;
    @BindView(R.id.input_phone)
    EditText inputPhone;
    @BindView(R.id.input_layout_phone)
    TextInputLayout inputLayoutPhone;
    @BindView(R.id.signup_sumbit)
    TextView signupSumbit;
    @BindView(R.id.signup_login)
    TextView signupLogin;
    @BindView(R.id.signup_linear)
    LinearLayout signupLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 19) {
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else {
            //for lower api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            SignupActivity.this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            SignupActivity.this.getWindow().setStatusBarColor(Color.BLACK);
        }

        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        signupTxt.setTypeface(FontSource.process(SignupActivity.this, R.raw.proxima_nova_bold));
        inputName.setTypeface(FontSource.process(SignupActivity.this, R.raw.proxima_nova_rg_regular));
        inputEmail.setTypeface(FontSource.process(SignupActivity.this, R.raw.proxima_nova_rg_regular));
        inputPassword.setTypeface(FontSource.process(SignupActivity.this, R.raw.proxima_nova_rg_regular));
        inputPhone.setTypeface(FontSource.process(SignupActivity.this, R.raw.proxima_nova_rg_regular));
        signupSumbit.setTypeface(FontSource.process(SignupActivity.this, R.raw.proxima_nova_rg_regular));
        signupLogin.setTypeface(FontSource.process(SignupActivity.this, R.raw.proxima_nova_rg_regular));

        signupFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signupSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signupLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent();
                    setResult(10, intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
