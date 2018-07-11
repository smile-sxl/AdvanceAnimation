package com.sxl.animation;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

/**
 * author: smile .
 * date: On 2018/6/17
 */
public class CirculActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivOval;
    private ImageView ivRect;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circul);
        ivOval = (ImageView) findViewById(R.id.iv_oval);
        ivRect = (ImageView) findViewById(R.id.iv_rect);
        ivOval.setOnClickListener(this);
        ivRect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_oval:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //参数  View，中心点坐标，开始半径，结束半径
                    Animator animator = ViewAnimationUtils.createCircularReveal(ivOval, ivOval.getWidth() / 2,
                            ivOval.getHeight() / 2, 0, ivOval.getWidth());
                    animator.setDuration(2000);
                    animator.start();
                }
                break;
            case R.id.iv_rect:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = ViewAnimationUtils.createCircularReveal(ivRect, 0,
                            0, 0, (float) Math.hypot(ivRect.getWidth(), ivRect.getHeight()));
                    animator.setDuration(2000);
                    animator.start();
                }
                break;
        }
    }
}
