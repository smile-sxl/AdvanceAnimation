package com.sxl.animation.path;

import android.animation.TypeEvaluator;

/**
 * author: smile .
 * date: On 2018/6/24
 */
public class ViewPathEvaluator implements TypeEvaluator<ViewData> {

    // 自定义估值器：ViewPathEvaluator
    public ViewPathEvaluator() {
    }

    @Override
    public ViewData evaluate(float t, ViewData startValue, ViewData endValue) {
        float x, y;
        float startX, startY;

        if (endValue.type == ViewPath.LINE) {
            startX = (startValue.type == ViewPath.QUAD) ? startValue.x1 : startValue.x;
            startX = (startValue.type == ViewPath.CUBIC) ? startValue.x2 : startX;
            startY = (startValue.type == ViewPath.QUAD) ? startValue.y1 : startValue.y;
            startY = (startValue.type == ViewPath.CUBIC) ? startValue.y2 : startY;

            x = startX + t * (endValue.x - startX);
            y = startY + t * (endValue.y - startY);
            // 三阶贝塞尔曲线
        } else if (endValue.type == ViewPath.CUBIC) {
            startX = (startValue.type == ViewPath.QUAD) ? startValue.x1 : startValue.x;
            startY = (startValue.type == ViewPath.QUAD) ? startValue.y1 : startValue.y;

            float oneMinusT = 1 - t;

            x = oneMinusT * oneMinusT * oneMinusT * startX +
                    3 * oneMinusT * oneMinusT * t * endValue.x +
                    3 * oneMinusT * t * t * endValue.x1 +
                    t * t * t * endValue.x2;

            y = oneMinusT * oneMinusT * oneMinusT * startY +
                    3 * oneMinusT * oneMinusT * t * endValue.y +
                    3 * oneMinusT * t * t * endValue.y1 +
                    t * t * t * endValue.y2;
        } else if (endValue.type == ViewPath.MOVE) {
            x = endValue.x;
            y = endValue.y;
            // 二阶贝塞尔曲线
        } else if (endValue.type == ViewPath.QUAD) {
            startX = (startValue.type == ViewPath.CUBIC) ? startValue.x2 : startValue.x;
            startY = (startValue.type == ViewPath.CUBIC) ? startValue.y2 : startValue.y;

            float oneMinusT = 1 - t;

            x = oneMinusT * oneMinusT * startX +
                    2 * oneMinusT * t * endValue.x +
                    t * t * endValue.x1;

            y = oneMinusT * oneMinusT * startY +
                    2 * oneMinusT * t * endValue.y +
                    t * t * endValue.y1;

        } else {
            x = endValue.x;
            y = endValue.y;
        }

        return new ViewData(x, y);
    }
}