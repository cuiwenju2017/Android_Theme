package com.shanjing.android_theme.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.shanjing.android_theme.R;

public class BaseActivity extends AppCompatActivity {

    SharedPreferences sprfMain;
    SharedPreferences.Editor editorMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sprfMain = getSharedPreferences("counter", Context.MODE_PRIVATE);
        editorMain = sprfMain.edit();
        //取出保存的值（取数据）
        boolean isChecked = sprfMain.getBoolean("isChecked", false);
        //根据保存的值设置主题状态
        if (isChecked) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            //设置夜间模式下虚拟按键栏的背景色
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setNavigationBarColor(Color.parseColor("#303030"));
            }
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            //设置日间模式下虚拟按键栏的背景色
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setNavigationBarColor(Color.parseColor("#ffffff"));
            }
        }
    }
}
