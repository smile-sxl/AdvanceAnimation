package com.sxl.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sxl.animation.path.LauncherView;

/**
 * author: smile .
 * date: On 2018/6/24
 */
public class ExerciseAnimActivity extends AppCompatActivity implements View.OnClickListener {

    private LauncherView mLauncherView;
    private Button mbtnStart;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        mLauncherView = (LauncherView) findViewById(R.id.launcherview);
        mbtnStart = (Button) findViewById(R.id.btn_start);
        mbtnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mLauncherView.start();
    }
}
