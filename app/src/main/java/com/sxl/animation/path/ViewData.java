package com.sxl.animation.path;

/**
 * author: smile .
 * date: On 2018/6/24
 */
public class ViewData {

    float x, y;
    float x1, y1;
    float x2, y2;
    int type;

    public ViewData(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public ViewData(float x, float y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public ViewData(float x, float y, float x1, float y1, int type) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.type = type;
    }

    public ViewData(float x, float y, float x1, float y1, float x2, float y2, int type) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.type = type;
    }

    // 设置起点
    public static ViewData moveTo(float x, float y, int type) {
        return new ViewData(x, y, type);
    }

    // 直线移动
    public static ViewData lineTo(float x, float y, int type) {
        return new ViewData(x, y, type);
    }

    //二阶贝塞尔曲线移动
    public static ViewData quadTo(float x, float y, float x1, float y1, int type) {
        return new ViewData(x, y, x1, y1, type);
    }

    //三阶贝塞尔曲线移动
    public static ViewData cubicTo(float x, float y, float x1, float y1, float x2, float y2, int type) {
        return new ViewData(x, y, x1, y1, x2, y2, type);
    }
}
