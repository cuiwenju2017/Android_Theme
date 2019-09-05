package com.shanjing.android_theme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shanjing.android_theme.activity.IssueActivity;
import com.shanjing.android_theme.fragment.HRCartFragment;
import com.shanjing.android_theme.fragment.HRHomeFragment;
import com.shanjing.android_theme.fragment.HRMyFragment;
import com.shanjing.android_theme.fragment.HRVideoFragment;

public class ThreeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg;
    private RadioButton rb_home;
    private HRHomeFragment hrHomeFragment;
    private HRCartFragment hrCartFragment;
    private HRVideoFragment hrVideoFragment;
    private HRMyFragment hrMyFragment;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        initView();
    }

    private void initView() {
        rg = findViewById(R.id.rg);
        rb_home = findViewById(R.id.rb_home);
        iv = findViewById(R.id.iv);
        rg.setOnCheckedChangeListener(this);
        rb_home.setChecked(true);//设置首页选中
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThreeActivity.this, IssueActivity.class));
            }
        });
    }

    private int mPosition;

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        mPosition = checkedId;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (checkedId == R.id.rb_home) {
            if (hrHomeFragment == null) {
                hrHomeFragment = new HRHomeFragment();
                transaction.add(R.id.fragment_container, hrHomeFragment);
            } else {
                transaction.show(hrHomeFragment);
            }
        } else if (checkedId == R.id.rb_mall) {
            if (hrCartFragment == null) {
                hrCartFragment = new HRCartFragment();
                transaction.add(R.id.fragment_container, hrCartFragment);
            } else {
                transaction.show(hrCartFragment);
            }
        } else if (checkedId == R.id.rb_video) {
            if (hrVideoFragment == null) {
                hrVideoFragment = new HRVideoFragment();
                transaction.add(R.id.fragment_container, hrVideoFragment);
            } else {
                transaction.show(hrVideoFragment);
            }
        } else if (checkedId == R.id.rb_my) {
            if (hrMyFragment == null) {
                hrMyFragment = new HRMyFragment();
                transaction.add(R.id.fragment_container, hrMyFragment);
            } else {
                transaction.show(hrMyFragment);
            }
        }
        transaction.commit();
    }

    public void hideAllFragment(FragmentTransaction transaction) {
        if (hrHomeFragment != null) {
            transaction.hide(hrHomeFragment);
        }
        if (hrCartFragment != null) {
            transaction.hide(hrCartFragment);
        }
        if (hrVideoFragment != null) {
            transaction.hide(hrVideoFragment);
        }
        if (hrMyFragment != null) {
            transaction.hide(hrMyFragment);
        }
    }

    /**
     * 解决9.0Fragment的重影问题
     *
     * @param outState
     */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        /* 记录当前的position */
        outState.putInt("position", mPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPosition = savedInstanceState.getInt("position");
        onCheckedChanged(rg, mPosition);
    }

}
