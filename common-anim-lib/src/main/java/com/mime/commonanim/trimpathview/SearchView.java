package com.mime.commonanim.trimpathview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.mime.commonanim.R;


/**
 * <p>write the description for the file
 *
 * @author renjialiang
 * @createTime 2016/8/1 9:46
 * @project Demo
 */
public class SearchView extends View {

    private static final int DEAFULT_WIDTH = 180;

    private static final int DEAFULT_HEIGHT = 180;

    private static final int ANIM_TIME = 1600;

    public enum State {
        NONE,
        STARTING,
        SEARCHING,
        ENDING
    }

    private Paint mPaint;

    private int mViewWidth = DEAFULT_WIDTH;

    private int mViewHeight = DEAFULT_HEIGHT;

    private State mCurrentState = State.NONE;

    private Path path_search;

    private Path path_circle;

    private PathMeasure mMeasure;

    private ValueAnimator mStartingAnimator;

    private ValueAnimator mSearchingAnimator;

    private ValueAnimator mEndingAnimator;

    private float mAnimatorValue = 0;

    private ValueAnimator.AnimatorUpdateListener mUpdateListener;

    private Animator.AnimatorListener mAnimatorListener;

    private Handler mAnimatorHandler;

    private boolean isOver = false;

    private int count = 0;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 初始化画笔
        initPaint();
        // 初始化路径
        initPath();
        // 初始化监听
        initListener();
        // 初始化消息处理器
        initHandler();
        // 初始化动画
        initAnimator();
        setBackgroundColor(getResources().getColor(R.color.bg_search_view));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (measureWidth <= 0) {
            mViewWidth = DEAFULT_WIDTH;
        } else {
            mViewWidth = measureWidth;
        }
        if (measureHeight <= 0) {
            mViewHeight = DEAFULT_HEIGHT;
        } else {
            mViewHeight = measureHeight;
        }
        // 设置测量到的尺寸
        setMeasuredDimension(mViewWidth, mViewHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    /**
     * 开始动画 并设置状态为开始
     */
    public void startAnim() {
        mCurrentState = State.STARTING;
        mStartingAnimator.start();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(60);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void initPath() {
        // 搜索路径
        path_search = new Path();
        // 圆路径  点转的圆的大小
        path_circle = new Path();
        // 创建一个空的PathMeasure
        mMeasure = new PathMeasure();
        // 矩形范围
        RectF oval1 = new RectF(-200, -200, 200, 200);
        // 添加圆弧
        path_search.addArc(oval1, 45, 359.99f);//Don not be 360.
        // 矩形范围
        RectF oval2 = new RectF(-400, -400, 400, 400);
        // 添加圆弧
        path_circle.addArc(oval2, 45, -359.99f);//Don not be 360.

        float[] pos = new float[2];
        // 关联一个Path    第一个参数自然就是被关联的 Path 了，第二个参数是用来确保 Path 闭合
        mMeasure.setPath(path_circle, false);
        // 获取指定长度的位置坐标及该点切线值    1.距离 Path 起点的长度   2.该点的坐标值  3.该点的正切值
        mMeasure.getPosTan(0, pos, null);
        //搜索路径 连接 圆的位置坐标  这个点就是直线的右下角点
        path_search.lineTo(pos[0], pos[1]);
    }

    private void initListener() {
        // 数值发生器监听
        mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        };
        // 动画的监听 开始  取消  停止 重复
        mAnimatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mAnimatorHandler.sendEmptyMessage(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        };
    }

    private void initHandler() {
        mAnimatorHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (mCurrentState) {
                    case STARTING:
                        isOver = false;
                        mCurrentState = State.SEARCHING;
                        mSearchingAnimator.start();
                        break;
                    case SEARCHING:
                        count++;
                        if (count == 2) {
                            isOver = true;
                        }
                        if (!isOver) {
                            mSearchingAnimator.start();
                        } else {
                            mCurrentState = State.ENDING;
                            mEndingAnimator.start();
                        }
                        break;
                    case ENDING:
                        count = 0;
                        mCurrentState = State.NONE;
                        break;
                }
            }
        };
    }

    private void initAnimator() {
        //开始动画  出现中点  搜索开始变消失
        mStartingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(ANIM_TIME);
        //搜索动画  小点随着转圆圈
        mSearchingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(ANIM_TIME);
        //停止动画  搜索开始出现
        mEndingAnimator = ValueAnimator.ofFloat(1, 0).setDuration(ANIM_TIME);

        mStartingAnimator.addUpdateListener(mUpdateListener);
        mSearchingAnimator.addUpdateListener(mUpdateListener);
        mEndingAnimator.addUpdateListener(mUpdateListener);

        mStartingAnimator.addListener(mAnimatorListener);
        mSearchingAnimator.addListener(mAnimatorListener);
        mEndingAnimator.addListener(mAnimatorListener);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSearch(canvas);
    }

    private void drawSearch(Canvas canvas) {
        //将画布的原点移动到中间位置
        canvas.translate(mViewWidth / 2, mViewHeight / 2);

        float start;
        float end;
        switch (mCurrentState) {
            // 初始时画出搜索框
            case NONE:
                canvas.drawPath(path_search, mPaint);
                break;
            // 开始动画时
            case STARTING:
                // 关联搜索的路径
                mMeasure.setPath(path_search, false);
                // 设置新的路径 原点的位置
                Path dst = new Path();
                dst.rLineTo(0, 0);
                // 获取开始的长度
                start = mMeasure.getLength() * mAnimatorValue;
                // 获取结束时路径的长度
                end = mMeasure.getLength();
                // 截取片段
                mMeasure.getSegment(start == end ? start - 0.01f : start, end, dst, true);
                // 画出截取的path
                canvas.drawPath(dst, mPaint);
                break;
            case SEARCHING:
                //关联圆的路径
                mMeasure.setPath(path_circle, false);
                // 设置新的路径  原点的位置
                Path dst2 = new Path();
                dst2.rLineTo(0, 0);
                // 获取结束时路径的长度 保持短圆弧的位置
                end = mMeasure.getLength() * mAnimatorValue;
                // 动态的改变圆弧的长度
                start = (float) (end - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * mMeasure.getLength() / 4));
                if (start == end) {
                    end = end + 0.01f;
                }
                // 截取片段
                mMeasure.getSegment(start, end, dst2, true);
                // 画出截取的path
                canvas.drawPath(dst2, mPaint);
                break;
            case ENDING:
                // 关联搜索的路径
                mMeasure.setPath(path_search, false);
                // 设置新的路径  原点的位置
                Path dst3 = new Path();
                dst3.rLineTo(0, 0);
                // 获取开始的长度
                start = mMeasure.getLength() * mAnimatorValue;
                // 获取结束时路径的长度
                end = mMeasure.getLength();
                // 截取片段
                mMeasure.getSegment(start == end ? start - 0.01f : start, end, dst3, true);
                // 画出截取的path
                canvas.drawPath(dst3, mPaint);
                break;
        }
    }
}