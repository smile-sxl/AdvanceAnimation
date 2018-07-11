package com.sxl.animation.path;

import android.util.Log;
import android.widget.ImageView;

/**
 * author: smile .
 * date: On 2018/6/24
 */
public class CircleView {

    ImageView circle;

    public CircleView(ImageView imageView) {
        this.circle = imageView;
    }

    public void setPath(ViewData viewData) {
        Log.e("TAG", "setPath: ---------");
        circle.setTranslationX(viewData.x);
        circle.setTranslationY(viewData.y);
    }
}
