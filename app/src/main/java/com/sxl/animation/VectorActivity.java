package com.sxl.animation;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * author: smile .
 * date: On 2018/6/17
 */
public class VectorActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivVector;
    private ImageView ivSelector;
    private boolean isTwitterChecked = false;
    private ImageView ivFace;
    private ImageView ivSearchBox;
    private boolean isSearchBoxChecked = false;
    private ImageView ivFavorite;
    private boolean isFavoriteClick = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector);
        ivVector = (ImageView) findViewById(R.id.iv_vector);
        ivSelector = (ImageView) findViewById(R.id.iv_selector);
        ivFace = (ImageView) findViewById(R.id.iv_face);
        ivSearchBox = (ImageView) findViewById(R.id.iv_searchbox);
        ivFavorite = (ImageView) findViewById(R.id.iv_favorite);
        ivVector.setOnClickListener(this);
        ivSelector.setOnClickListener(this);
        ivFace.setOnClickListener(this);
        ivSearchBox.setOnClickListener(this);
        ivFavorite.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_vector:
                Drawable drawable = ivVector.getDrawable();
                ((Animatable) drawable).start();
                break;
            case R.id.iv_face:
                Drawable drawable1 = ivFace.getDrawable();
                ((Animatable) drawable1).start();
                break;
            case R.id.iv_searchbox:
                isSearchBoxChecked = !isSearchBoxChecked;
                final int[] stateSet = {android.R.attr.state_checked * (isSearchBoxChecked ? 1 : -1)};
                ivSearchBox.setImageState(stateSet, true);
                break;
            case R.id.iv_selector:
                isTwitterChecked = !isTwitterChecked;
                final int[] stateSet1 = {android.R.attr.state_checked * (isTwitterChecked ? 1 : -1)};
                ivSelector.setImageState(stateSet1, true);
                break;
            case R.id.iv_favorite:
                isFavoriteClick = !isFavoriteClick;
                final int[] stateSet2 = {android.R.attr.state_checked * (isFavoriteClick ? 1 : -1)};
                ivFavorite.setImageState(stateSet2, true);
                break;

        }
    }
}
