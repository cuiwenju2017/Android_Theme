package com.shanjing.android_theme.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.shanjing.android_theme.R;
import com.shanjing.android_theme.utils.ButtonUtils;

/**
 * 入口
 */
public class MainActivity extends BaseActivity {

    private Switch sw;
    private TextView tv, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sw = findViewById(R.id.sw);
        tv = findViewById(R.id.tv);
        tv2 = findViewById(R.id.tv2);
        //取出保存的值（取数据）
        boolean isChecked = sprfMain.getBoolean("isChecked", false);
        sw.setChecked(isChecked);//获取状态并设置当前状态

        //开关
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    //保存数据
                    editorMain.putBoolean("isChecked", true);
                    editorMain.commit();

                } else {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    //保存数据
                    editorMain.putBoolean("isChecked", false);
                    editorMain.commit();
                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ButtonUtils.isFastDoubleClick(R.id.tv)) {
                    startActivity(new Intent(MainActivity.this, TwoActivity.class));
                }
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ButtonUtils.isFastDoubleClick(R.id.tv)) {
                    startActivity(new Intent(MainActivity.this, ThreeActivity.class));
                }
            }
        });

    }
}
