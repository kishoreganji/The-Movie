package com.android.themovie.constants;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.TextView;

import com.android.themovie.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AppConstants {

    public static final String API_Key = "55957fcf3ba81b137f8fc01ac5a31fb5";
    public static final String Api_Popular_Movies = "/3/movie/popular";
    public static final String Base_Url           = "https://api.themoviedb.org";
    public static final String Img_Base_Url       = "https://image.tmdb.org/t/p/w500";


    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo()!=null;
    }

    public static void setCustomActionbar(AppCompatActivity appCompatActivity, String title) {
        try {
            appCompatActivity.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            appCompatActivity.getSupportActionBar().setDisplayShowCustomEnabled(true);
            appCompatActivity.getSupportActionBar().setCustomView(R.layout.actionbar_layout);
            ((TextView)appCompatActivity.getSupportActionBar().getCustomView().findViewById(R.id.tvTitle)).setText(title);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
