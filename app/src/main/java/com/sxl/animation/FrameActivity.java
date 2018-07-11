package com.sxl.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * author: smile .
 * date: On 2018/6/17
 */
public class FrameActivity extends AppCompatActivity {

    private ImageView iv_frame;
    private Button btn_frame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        iv_frame = (ImageView) findViewById(R.id.iv_frame);
        btn_frame = (Button) findViewById(R.id.btn_frame);
        btn_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_frame.setImageResource(R.drawable.animation_list);
                AnimationDrawable anim = (AnimationDrawable) iv_frame.getDrawable();
                // 是否只播放一次
                anim.setOneShot(true);
                anim.start();
            }
        });
    }
}
