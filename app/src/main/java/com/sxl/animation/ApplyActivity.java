package com.sxl.animation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * author: smile .
 * date: On 2018/7/1
 */
public class ApplyActivity extends AppCompatActivity {

    private RecyclerView rvLol;
    private List<Hero> heros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        initData();
        rvLol = (RecyclerView) findViewById(R.id.rv_lol);
        rvLol.setItemAnimator(new DefaultItemAnimator());
        rvLol.setLayoutManager(new LinearLayoutManager(this));
        rvLol.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
        rvLol.setAdapter(new MyAdapter());
    }

    private void initData() {
        heros = new ArrayList<Hero>();
        heros.add(new Hero(R.mipmap.yasuo, "疾风剑豪", "死亡如风，常伴吾身"));
        heros.add(new Hero(R.mipmap.kapai, "卡牌大师", "幸运女神在微笑"));
        heros.add(new Hero(R.mipmap.ruiwen, "放逐之刃", "断剑重铸之日骑士归来之时！"));
        heros.add(new Hero(R.mipmap.jianji, "无双剑姬", "精准而优雅"));
        heros.add(new Hero(R.mipmap.mangseng, "盲僧", "我用双手成就你的梦想"));
        heros.add(new Hero(R.mipmap.xiaopao, "麦林炮手", "我好想射点儿什么!"));
        heros.add(new Hero(R.mipmap.yasuo, "疾风剑豪", "死亡如风，常伴吾身"));
        heros.add(new Hero(R.mipmap.kapai, "卡牌大师", "幸运女神在微笑"));
        heros.add(new Hero(R.mipmap.ruiwen, "放逐之刃", "断剑重铸之日骑士归来之时！"));
        heros.add(new Hero(R.mipmap.jianji, "无双剑姬", "精准而优雅"));
        heros.add(new Hero(R.mipmap.mangseng, "盲僧", "我用双手成就你的梦想"));
        heros.add(new Hero(R.mipmap.xiaopao, "麦林炮手", "我好想射点儿什么!"));
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ApplyActivity.this).inflate(R.layout.item_lol, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyAdapter.ViewHolder holder, int position) {
            final Hero hero = heros.get(position);
            holder.ivHeader.setImageResource(hero.getResId());
            holder.tvTitle.setText(hero.getTitle());
            holder.tvAbout.setText(hero.getAbout());
            holder.llItem.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ApplyActivity.this, DetailActivity.class);
                    intent.putExtra("hero", hero);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
                            ApplyActivity.this, Pair.create((View) holder.ivHeader, "header"),
                            Pair.create((View) holder.tvTitle, "title"), Pair.create((View) holder.tvAbout, "about")
                    ).toBundle());
                }
            });
        }

        @Override
        public int getItemCount() {
            return heros.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView ivHeader;
            TextView tvTitle, tvAbout;
            LinearLayout llItem;

            public ViewHolder(View itemView) {
                super(itemView);
                llItem = (LinearLayout) itemView.findViewById(R.id.ll_item);
                ivHeader = (ImageView) itemView.findViewById(R.id.iv_header);
                tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
                tvAbout = (TextView) itemView.findViewById(R.id.tv_about);
            }
        }
    }

}
