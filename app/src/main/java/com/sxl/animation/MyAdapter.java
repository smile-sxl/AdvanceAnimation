
package com.sxl.animation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<MainActivity.Item> mList;
    private Context mContext;

    /**
     * 自定义适配器
     *
     * @param context
     * @param
     */
    public MyAdapter(Context context, List<MainActivity.Item> items) {
        this.mContext = context;
        this.mList = items;
    }

    /**
     * 创建自定义的ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.item_recycler, parent, false));
        return holder;
    }

    /**
     * 绑定ViewHolder
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MyAdapter.MyViewHolder holder, final int position) {
        holder.tvItem.setText(mList.get(position).nameId);
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, mList.get(position).clazz);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 自定义ViewHolder
     */
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;
        LinearLayout llItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            llItem = (LinearLayout) itemView.findViewById(R.id.ll_item);
            tvItem = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }

}  