package com.sxl.animation;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * author: smile .
 * date: On 2018/6/17
 */
public class PathAnimActivity extends AppCompatActivity {

    private ImageView iv_circle;
    private CurvePath curvepath;
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathanim);
        iv_circle = (ImageView) findViewById(R.id.iv_circle);
        curvepath = (CurvePath) findViewById(R.id.curvepath);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(iv_circle, View.X, View.Y, curvepath.path);
                animator.setDuration(2000);
                animator.start();
            }
        });
    }

}
