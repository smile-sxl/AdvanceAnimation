package com.sxl.animation;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Window;

/**
 * author: smile .
 * date: On 2018/7/1
 */
public class ThirdActivity extends AppCompatActivity {
    private int flag;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flag = getIntent().getFlags();
        // 跳转的Activity在onCreate方法中开启Transition模式   配合share
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        switch (flag) {
            case 0:
                getWindow().setEnterTransition(new Explode());
                break;
            case 1:
                getWindow().setEnterTransition(new Slide());
                break;
            case 2:
                getWindow().setEnterTransition(new Fade());
                getWindow().setExitTransition(new Fade());
                break;
        }
        setContentView(R.layout.activity_third);
    }
}
