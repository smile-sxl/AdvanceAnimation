package com.sxl.animation.path;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sxl.animation.R;

/**
 * author: smile .
 * date: On 2018/6/24
 */
public class LauncherView extends RelativeLayout {
    private ImageView purple;
    private ImageView yellow;
    private ImageView blue;
    private ImageView red;
    private int mWidth;
    private int mHeight;
    private int dp80 = dp2px(getContext(), 80);
    private ViewPath redPath;
    private ViewPath purplePath;
    private ViewPath yellowPath;
    private ViewPath bluePath;
    private AnimatorSet purpleAnim;
    private AnimatorSet yellowAnim;
    private AnimatorSet blueAnim;
    private AnimatorSet redAnim;


    public LauncherView(Context context) {
        super(context);
    }

    public LauncherView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LauncherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(CENTER_IN_PARENT);

        purple = new ImageView(getContext());
        purple.setLayoutParams(layoutParams);
        purple.setImageResource(R.drawable.shape_circle_purple);
        addView(purple);

        yellow = new ImageView(getContext());
        yellow.setLayoutParams(layoutParams);
        yellow.setImageResource(R.drawable.shape_circle_yellow);
        addView(yellow);

        blue = new ImageView(getContext());
        blue.setLayoutParams(layoutParams);
        blue.setImageResource(R.drawable.shape_circle_blue);
        addView(blue);

        red = new ImageView(getContext());
        red.setLayoutParams(layoutParams);
        red.setImageResource(R.drawable.shape_circle_red);
        addView(red);

        purpleAnim = setAnimation(purple, purplePath);
        yellowAnim = setAnimation(yellow, yellowPath);
        blueAnim = setAnimation(blue, bluePath);
        redAnim = setAnimation(red, redPath);
    }


    public void start() {
        removeAllViews();
        init();
        purpleAnim.start();
        yellowAnim.start();
        blueAnim.start();
        redAnim.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showLogo();
            }
        }, 2400);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        initPath();
    }

    private void initPath() {
        redPath = new ViewPath();
        redPath.moveTo(0, 0);
        redPath.lineTo(mWidth / 5 - mWidth / 2, 0);
        redPath.cubicTo(-700, -mHeight / 2, mWidth / 3 * 2, -mHeight / 3 * 2, 0, -240);

        purplePath = new ViewPath(); //偏移坐标
        purplePath.moveTo(0, 0);
        purplePath.lineTo(mWidth / 5 * 2 - mWidth / 2, 0);
        purplePath.cubicTo(-300, -mHeight / 2, mWidth, -mHeight / 9 * 5, 0, -dp80);

        yellowPath = new ViewPath(); //偏移坐标
        yellowPath.moveTo(0, 0);
        yellowPath.lineTo(mWidth / 5 * 3 - mWidth / 2, 0);
        yellowPath.cubicTo(300, mHeight, -mWidth, -mHeight / 9 * 5, 0, -dp80);

        bluePath = new ViewPath(); //偏移坐标
        bluePath.moveTo(0, 0);
        bluePath.lineTo(mWidth / 5 * 4 - mWidth / 2, 0);
        bluePath.cubicTo(700, mHeight / 3 * 2, -mWidth / 2, mHeight / 2, 0, -dp80);

    }

    private AnimatorSet setAnimation(final ImageView view, ViewPath path) {
        //路径
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(new CircleView(view), "path",
                new ViewPathEvaluator(), path.getDatas().toArray());
        objectAnimator.setDuration(2800);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        //组合添加缩放透明效果
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 1000);
        valueAnimator.setDuration(1800);
        valueAnimator.setStartDelay(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                float alpha = 1 - value / 2000;
                float scale = getScale(view) - 1;
                if (value <= 500) {
                    scale = 1 + (value / 500) * scale;
                } else {
                    scale = 1 + ((1000 - value) / 500) * scale;
                }
                view.setScaleX(scale);
                view.setScaleY(scale);
                view.setAlpha(alpha);
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeView(view);
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator, valueAnimator);
        return animatorSet;
    }

    private void showLogo() {
        View view = View.inflate(getContext(), R.layout.widget_load_view, this);
        View logo = view.findViewById(R.id.iv_logo);
        final View slogo = view.findViewById(R.id.iv_slogo);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(logo, View.ALPHA, 0f, 1f);
        alpha.setDuration(800);
        alpha.start();

        ObjectAnimator alpha1 = ObjectAnimator.ofFloat(slogo, View.ALPHA, 0f, 1f);
        alpha1.setDuration(200);
        alpha1.setStartDelay(400);
        alpha1.start();
    }

    private float getScale(ImageView iv) {
        if (iv == red) return 3.0f;
        if (iv == purple) return 2.0f;
        if (iv == yellow) return 4.5f;
        if (iv == blue) return 3.5f;
        return 2.0f;
    }

    private int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
