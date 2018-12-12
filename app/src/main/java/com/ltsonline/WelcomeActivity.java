package com.ltsonline;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.ltsonline.custom.FontSource;
import com.ltsonline.netUtils.MyPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class WelcomeActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;

    ImageView login_facebook, googleplus;
    EditText email, password;
    TextView signup, first_txt, second_txt, signup_sumbit;
    TextInputLayout input_layout_email, input_layout_password;
    String _name, _email, _password, facebook_id, google_id, facebook_auth_token, imei, refreshedtoken;


    MyPreferences myPreferences;
    private CallbackManager callbackManager;

    /*g+ */
    private static final int RC_SIGN_IN = 007;
    private GoogleApiClient mGoogleApiClient;


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
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_welcome);
        myPreferences = new MyPreferences(this);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.welcome_slide1,
                R.layout.activity_login};

        // adding bottom dots
        addBottomDots(0);
        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        /* fb login */
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        Log.d("Success", "Login");
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Log.v("LoginActivity", response.toString());
                                        try {
                                            _email = object.getString("email");
                                            _name = object.getString("name");
                                            facebook_id = "" + loginResult.getAccessToken().getUserId();
                                            facebook_auth_token = "" + loginResult.getAccessToken().getToken();
                                            myPreferences.setPreferences(MyPreferences.loginType, "" + getResources().getString(R.string.loginType_facebook));
                                            //new General().execute("facebook");
                                            launchHomeScreen();

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(WelcomeActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(WelcomeActivity.this, "Login unsuccessful!", Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

            if (Build.VERSION.SDK_INT < 19) {
                View v = WelcomeActivity.this.getWindow().getDecorView();
                v.setSystemUiVisibility(View.GONE);
            } else {
                View decorView = getWindow().getDecorView();
                int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                decorView.setSystemUiVisibility(uiOptions);
            }

            if (position == 1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    WelcomeActivity.this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    WelcomeActivity.this.getWindow().setStatusBarColor(Color.BLACK);
                }
            }

            addBottomDots(position);
            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                btnSkip.setVisibility(View.VISIBLE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };

    private void launchHomeScreen() {
        myPreferences.setPreferences(MyPreferences.welcomeScreen, "1");
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);

            if (position == 0) {
                view = layoutInflater.inflate(R.layout.welcome_slide1, null);
                first_txt = (TextView) view.findViewById(R.id.first_txt);
                second_txt = (TextView) view.findViewById(R.id.second_txt);
                signup_sumbit = (TextView) view.findViewById(R.id.signup_sumbit);

                first_txt.setTypeface(FontSource.process(WelcomeActivity.this, R.raw.proxima_nova_bold));
                second_txt.setTypeface(FontSource.process(WelcomeActivity.this, R.raw.proxima_nova_rg_regular));
                signup_sumbit.setTypeface(FontSource.process(WelcomeActivity.this, R.raw.proxima_nova_rg_regular));

                signup_sumbit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(WelcomeActivity.this, SignupActivity.class);
                        startActivity(intent);
                    }
                });

            }
            if (position == 2) {
                view = layoutInflater.inflate(R.layout.activity_login, null);

                email = (EditText) view.findViewById(R.id.input_email);
                password = (EditText) view.findViewById(R.id.input_password);
                input_layout_email = (TextInputLayout) view.findViewById(R.id.input_layout_email);
                input_layout_password = (TextInputLayout) view.findViewById(R.id.input_layout_password);
                login_facebook = (ImageView) view.findViewById(R.id.login_facebook);
                googleplus = (ImageView) view.findViewById(R.id.login_google_plus);
                TextView submit = (TextView) view.findViewById(R.id.signup_sumbit);
                TextView signup = (TextView) view.findViewById(R.id.login_sign_up);

                signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myPreferences.setPreferences(MyPreferences.welcomeScreen, "1");
                        Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                        startActivity(i);

                    }
                });
                password.addTextChangedListener(new MyTextWatcher(password));
                email.addTextChangedListener(new MyTextWatcher(email));
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (email.getText().toString().length() < 9) {
                            input_layout_email.setError("Enter Mobile Number");
                            requestFocus(email);
                            return;
                        }
                        if (!validatePassword()) {
                            return;
                        }
                        _email = email.getText().toString();
                        _password = password.getText().toString();
                        if (GlobalElements.isConnectingToInternet(WelcomeActivity.this)) {
                            // new General().execute("login");
                            launchHomeScreen();
                        } else {
                            GlobalElements.showDialog(WelcomeActivity.this);
                        }
                    }
                });

                login_facebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoginManager.getInstance().logInWithReadPermissions(WelcomeActivity.this, Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
                    }
                });

                googleplus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signIn();
                    }
                });
            }
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();
            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            myPreferences.setPreferences(MyPreferences.loginType, "" + getResources().getString(R.string.loginType_google));
            launchHomeScreen();
        } else {
            //llProfileLayout.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
            String n = data.toString();
            Log.e("data", data.toString());
        }
    }

    private boolean validatePassword() {
        if (password.getText().toString().trim().isEmpty()) {
            input_layout_password.setError(getString(R.string.err_msg_password));
            requestFocus(password);
            return false;
        } else {
            input_layout_password.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        String email1 = email.getText().toString().trim();

        if (email1.isEmpty() || !isValidEmail(email1)) {
            input_layout_email.setError(getString(R.string.err_msg_email));
            requestFocus(email);
            return false;
        } else {
            input_layout_email.setErrorEnabled(false);
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_email:
                    //validateEmail();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }
        }
    }

}
