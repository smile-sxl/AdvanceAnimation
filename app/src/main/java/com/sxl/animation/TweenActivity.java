package com.sxl.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * author: smile .
 * date: On 2018/6/17
 */
public class TweenActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_alpha;
    private ImageView iv_rotate;
    private ImageView iv_scale;
    private ImageView iv_translate;
    private Button btn_alpha;
    private Button btn_rotate;
    private Button btn_scale;
    private Button btn_translate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);
        setTitle(R.string.tween);
        iv_alpha = (ImageView) findViewById(R.id.iv_alpha);
        iv_rotate = (ImageView) findViewById(R.id.iv_rotate);
        iv_scale = (ImageView) findViewById(R.id.iv_scale);
        iv_translate = (ImageView) findViewById(R.id.iv_translate);

        btn_alpha = (Button) findViewById(R.id.btn_alpha);
        btn_rotate = (Button) findViewById(R.id.btn_rotate);
        btn_scale = (Button) findViewById(R.id.btn_scale);
        btn_translate = (Button) findViewById(R.id.btn_translate);

        btn_alpha.setOnClickListener(this);
        btn_rotate.setOnClickListener(this);
        btn_scale.setOnClickListener(this);
        btn_translate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_alpha:
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
                iv_alpha.startAnimation(animation);
                break;
            case R.id.btn_rotate:
                animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
                iv_rotate.startAnimation(animation);
                break;
            case R.id.btn_scale:
                animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
                iv_scale.startAnimation(animation);
                break;
            case R.id.btn_translate:
                animation = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
                iv_translate.startAnimation(animation);
                break;
        }
    }
}
