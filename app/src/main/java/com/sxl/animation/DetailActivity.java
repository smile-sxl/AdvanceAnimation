package com.sxl.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * author: smile .
 * date: On 2018/7/1
 */
public class DetailActivity extends AppCompatActivity {
    private Hero hero;
    private ImageView ivHeader;
    private TextView tvTitle, tvAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        hero = getIntent().getParcelableExtra("hero");
        setContentView(R.layout.activity_detail);
        ivHeader = (ImageView) findViewById(R.id.iv_header);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvAbout = (TextView) findViewById(R.id.tv_about);
        ivHeader.setImageResource(hero.getResId());
        tvTitle.setText(hero.getTitle());
        tvAbout.setText(hero.getAbout());
    }
}
