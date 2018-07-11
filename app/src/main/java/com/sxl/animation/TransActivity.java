package com.sxl.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * author: smile .
 * date: On 2018/6/17
 */
public class TransActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnActivity1;
    private Button btnActivity2;
    private Button btnActivity3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        btnActivity1 = (Button) findViewById(R.id.btn_activity1);
        btnActivity2 = (Button) findViewById(R.id.btn_activity2);
        btnActivity3 = (Button) findViewById(R.id.btn_activity3);
        btnActivity1.setOnClickListener(this);
        btnActivity2.setOnClickListener(this);
        btnActivity3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_activity1:
                startActivity(new Intent(this, TwoActivity.class));
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case R.id.btn_activity2:
                startActivity(new Intent(this, SceneTransitionActivity.class));
                break;
            case R.id.btn_activity3:
                startActivity(new Intent(this, ApplyActivity.class));
                break;
        }
    }
}
