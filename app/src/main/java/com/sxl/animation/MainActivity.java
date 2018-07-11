package com.sxl.animation;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<MainActivity.Item> mList;
    private MyAdapter myAdapter;

    public enum Item {

        TWEEN(R.string.tween, TweenActivity.class),
        FRAME(R.string.frame, FrameActivity.class),
        PROPERTY(R.string.property, PropertyActivity.class),
        CIRCUL(R.string.circularreveal, CirculActivity.class),
        ACTIVITY(R.string.activity, TransActivity.class),
        ANIMATE(R.string.animated, VectorActivity.class),
        PATH(R.string.path, PathActivity.class),
        CUSTOMSVG(R.string.customsvg, CustomSvgActivity.class),
        CUSTOMSVG2(R.string.customsvg2, CustomSvg2Activity.class);

        public int nameId;
        public Class<?> clazz;

        Item(@StringRes int nameId, Class<?> clazz) {
            this.nameId = nameId;
            this.clazz = clazz;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = Arrays.asList(Item.values());
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
        myAdapter = new MyAdapter(this, mList);
        mRecyclerView.setAdapter(myAdapter);
    }
}
