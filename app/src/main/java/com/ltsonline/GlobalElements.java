package com.ltsonline;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ltsonline.custom.FontSource;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by FriendFill on 30-Jul-16.
 */
public class GlobalElements extends Application {

    public static int cart_item = 0;
    public static int feed_type = 0;
    public static String action;
    public static boolean isChatActive = false;
    public static boolean redirectFlag = false;
    public static String position = "", is_wishlist = "";
    public static int notification_count = 0;
    public static ViewPager viewPager;;
    public static String fileprovider_path = "com.ltsonline.fileprovider";
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static void overrideFonts_P_Nova_Bold(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts_P_Nova_Bold(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "Proxima Nova Bold.ttf"));
            }
        } catch (Exception e) {
        }
    }

    public static void overrideFonts_P_Nova_Rg_Regular(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts_P_Nova_Rg_Regular(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "Proxima Nova Rg Regular.otf"));
            }
        } catch (Exception e) {
        }
    }

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (info != null) {
                if (info.isConnected()) {
                    return true;
                } else {
                    NetworkInfo info1 = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                    if (info1.isConnected()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public static void showDialog(Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        // Set Dialog Title
        alertDialog.setTitle("Internet Connection");
        // Set Dialog Message
        alertDialog.setMessage("Please check your internet connection ..");
        // Set OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        // Show Alert Message
        alertDialog.show();
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    public static String getDate() {
        Calendar newCalendar = Calendar.getInstance();
        SimpleDateFormat cu_date_time = new SimpleDateFormat("dd-MM-yyyy");
        String datetime = cu_date_time.format(newCalendar.getTime());
        return datetime;
    }

    public static void setStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            activity.getWindow().setStatusBarColor(Color.BLACK);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(activity.getResources().getColor(R.color.background_color));
        }
    }

    public static void setActionBar(Activity activity, String title) {
        try {
            ((AppCompatActivity) activity).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowCustomEnabled(true);
            ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) activity).getSupportActionBar().setElevation(6);
            //((AppCompatActivity)activity).getSupportActionBar().setCustomView(R.layout.actionbar);
            ((AppCompatActivity) activity).getSupportActionBar().setCustomView(activity.getLayoutInflater().inflate(R.layout.actionbar, null),
                    new ActionBar.LayoutParams(
                            ActionBar.LayoutParams.WRAP_CONTENT,
                            ActionBar.LayoutParams.WRAP_CONTENT,
                            Gravity.CENTER
                    )
            );
            View view = ((AppCompatActivity) activity).getSupportActionBar().getCustomView();
            TextView actionBarTitle = (TextView) view.findViewById(R.id.action_bar_title);
            actionBarTitle.setTypeface(FontSource.process(activity, R.raw.proxima_nova_bold));
            actionBarTitle.setText("" + title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getVersionCheck() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }
}
