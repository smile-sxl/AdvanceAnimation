package com.sxl.animation.path;

import java.util.ArrayList;
import java.util.Collection;

/**
 * author: smile .
 * date: On 2018/6/24
 */
public class ViewPath {

    // 路径类型  原点
    public static final int MOVE = 0;
    // 直线
    public static final int LINE = 1;
    // 二阶贝塞尔曲线
    public static final int QUAD = 2;
    // 三阶贝塞尔曲线
    public static final int CUBIC = 3;

    private ArrayList<ViewData> mDatas;

    public ViewPath() {
        mDatas = new ArrayList<ViewData>();
    }

    // 添加重置起点的路径
    public void moveTo(float x, float y) {
        mDatas.add(ViewData.moveTo(x, y, MOVE));
    }

    // 添加直线移动的路径
    public void lineTo(float x, float y) {
        mDatas.add(ViewData.lineTo(x, y, LINE));
    }

    // 添加二阶贝塞尔曲线移动的路径
    public void quadTo(float x, float y, float x1, float y1) {
        mDatas.add(ViewData.quadTo(x, y, x1, y1, QUAD));
    }

    // 添加三阶贝塞尔曲线移动的路径
    public void cubicTo(float x, float y, float x1, float y1, float x2, float y2) {
        mDatas.add(ViewData.cubicTo(x, y, x1, y1, x2, y2, CUBIC));
    }

    public Collection<ViewData> getDatas() {
        return mDatas;
    }

}
