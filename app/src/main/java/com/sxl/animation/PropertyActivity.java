package com.sxl.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * author: smile .
 * date: On 2018/6/17
 */
public class PropertyActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_object_animator;
    private ImageView iv_value_animator;
    private ImageView iv_heart;

    private Button btn_value_animator;
    private Button btn_object_animator;
    private Button btn_heart;
    private Button btn_exercise;
    private Button btn_path;
    private Button btn_number;
    private RelativeLayout rl_heart;
    private TextView tvNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        iv_object_animator = (ImageView) findViewById(R.id.iv_object_animator);
        iv_value_animator = (ImageView) findViewById(R.id.iv_value_animator);
        btn_object_animator = (Button) findViewById(R.id.btn_object_animator);
        btn_value_animator = (Button) findViewById(R.id.btn_value_animator);
        iv_heart = (ImageView) findViewById(R.id.iv_heart);
        tvNumber = (TextView) findViewById(R.id.tv_number);
        btn_heart = (Button) findViewById(R.id.btn_heart);
        btn_path = (Button) findViewById(R.id.btn_path);
        btn_exercise = (Button) findViewById(R.id.btn_exercise);
        btn_number = (Button) findViewById(R.id.btn_number);
        rl_heart = (RelativeLayout) findViewById(R.id.rl_heart);

        btn_object_animator.setOnClickListener(this);
        btn_value_animator.setOnClickListener(this);
        btn_heart.setOnClickListener(this);
        btn_path.setOnClickListener(this);
        btn_exercise.setOnClickListener(this);
        btn_number.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_object_animator:
                //创建 ObjectAnimator 类      参数:     1.要操作的控件  2.要操作的属性  3.可变的float 类型数组
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv_object_animator, "rotationX", 0, 359);
                //设置动画运行时长
                objectAnimator.setDuration(1000);
                //开始动画
                objectAnimator.start();
                break;
            case R.id.btn_value_animator:
                // 创建 ValueAnimator 类      参数:  设置属性数值的初始值 & 结束值
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
                //设置动画运行时长
                valueAnimator.setDuration(1000);
                // 设置更新监听器：即数值每次变化更新都会调用该方法
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        //获得每次变化后的属性值
                        float f = valueAnimator.getAnimatedFraction();
                        //每次值变化时，将值手动赋值给对象的属性
                        iv_value_animator.setRotationX(f * 359);
                    }
                });
                //开始动画
                valueAnimator.start();
                break;
            case R.id.btn_heart:
                // 创建 ValueAnimator 类      参数:  设置属性数值的初始值 & 结束值
                ValueAnimator heart = ValueAnimator.ofFloat(0, 1);
                //设置动画运行时长
                heart.setDuration(2000);
                // 设置更新监听器：即数值每次变化更新都会调用该方法
                heart.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        //获得每次变化后的属性值
                        float angle = valueAnimator.getAnimatedFraction() * 20;
                        // 心形函数
                        float t = (float) (angle / Math.PI);
                        float x = (float) (20 * (16 * Math.pow(Math.sin(t), 3)));
                        float y = (float) (-20 * (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t)));

                        int offsetX = rl_heart.getWidth() / 2;
                        int offsetY = rl_heart.getHeight() / 2 - 55;
                        //根据这些数值去改变控件的状态
                        iv_heart.setX(offsetX + (int) x);
                        iv_heart.setY(offsetY + (int) y);
                    }
                });
                //开始动画
                heart.start();
                break;
            case R.id.btn_number:
                // 创建 ValueAnimator 类      参数:  设置属性数值的初始值 & 结束值
                ValueAnimator anim = ValueAnimator.ofFloat(0, 2000);
                //设置动画运行时长
                anim.setDuration(2000);
                // 设置插值器  即影响动画的播放速度
                anim.setInterpolator(new LinearInterpolator());
                // 设置更新监听器：即数值每次变化更新都会调用该方法
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        //获得每次变化后的属性值
                        String value = valueAnimator.getAnimatedValue().toString();
                        String s = value.substring(0, value.indexOf(".") + 2);
                        //根据这些数值去改变控件的状态
                        tvNumber.setText(s);
                    }
                });
                //开始动画
                anim.start();
                break;
            case R.id.btn_path:
                startActivity(new Intent(this, PathAnimActivity.class));
                break;
            case R.id.btn_exercise:
                startActivity(new Intent(this, ExerciseAnimActivity.class));
                break;

        }
    }
}
