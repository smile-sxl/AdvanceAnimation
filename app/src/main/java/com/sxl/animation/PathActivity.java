package com.sxl.animation;

import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.mime.commonanim.pathview.PathView;
import com.mime.commonanim.trimpathview.SearchView;

/**
 * author: smile .
 * date: On 2018/7/8
 */
public class PathActivity extends AppCompatActivity {
    private PathView pathView;
    // 自定义搜索View
    private SearchView searchView;
    private ImageView ivDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        pathView = (PathView) findViewById(R.id.pathView);
        searchView = (SearchView) findViewById(R.id.searchview);
        ivDemo = (ImageView) findViewById(R.id.iv_demo);
    }

    public void startPathView(View view) {
        // SVG的path必须是连接起来的  如果不是连接起来的 要另起一个path
        pathView.getPathAnimator().
                delay(100).
                duration(1500).
                interpolator(new AccelerateDecelerateInterpolator()).
                start();

        try {
            SVG svg = SVG.getFromResource(this, R.raw.android);
            Picture picture = svg.renderToPicture();
            Drawable drawable = new PictureDrawable(picture);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                ivDemo.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
            ivDemo.setImageDrawable(drawable);
        } catch (SVGParseException e) {
            e.printStackTrace();
        }
    }

    public void startSearchView(View view) {
        searchView.startAnim();
    }
}
