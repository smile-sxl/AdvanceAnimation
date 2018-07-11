package com.sxl.animation;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * author: smile .
 * date: On 2018/7/1
 */
public class SceneTransitionActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnExplode;
    private Button btnSlide;
    private Button btnFade;
    private Button btnShare;
    private RelativeLayout rlView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenetransition);
        btnExplode = (Button) findViewById(R.id.btn_explode);
        btnSlide = (Button) findViewById(R.id.btn_slide);
        btnFade = (Button) findViewById(R.id.btn_fade);
        btnShare = (Button) findViewById(R.id.btn_share);
        rlView = (RelativeLayout) findViewById(R.id.rlView);

        btnExplode.setOnClickListener(this);
        btnSlide.setOnClickListener(this);
        btnFade.setOnClickListener(this);
        btnShare.setOnClickListener(this);
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //下一个页面的元素从四面八方进入，最终形成完整的页面
            case R.id.btn_explode:
                intent = new Intent(this, ThirdActivity.class);
                intent.setFlags(0);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            //下一个页面元素从底部依次向上运动，最终形成完整的页面
            case R.id.btn_slide:
                intent = new Intent(this, ThirdActivity.class);
                intent.setFlags(1);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            //下一个页面元素渐变出现，最终形成完整的页面
            case R.id.btn_fade:
                intent = new Intent(this, ThirdActivity.class);
                intent.setFlags(2);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.btn_share:
                View fab = rlView.findViewById(R.id.fab_button);
                View txName = rlView.findViewById(R.id.tx_user_name);
                intent = new Intent(this, ThirdActivity.class);
                intent.setFlags(3);
                // 跳转时，要为每一个共享的view设置对应的transitionName
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,
                        Pair.create((View) btnShare, "share"), Pair.create(fab, "fab")
                        , Pair.create(txName, "user_name")).toBundle());
                break;
        }
    }


}
