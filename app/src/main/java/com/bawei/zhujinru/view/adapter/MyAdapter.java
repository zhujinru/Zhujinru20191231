package com.bawei.zhujinru.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.zhujinru.R;
import com.bawei.zhujinru.model.bean.GsonBean;
import com.bawei.zhujinru.util.NetUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作:a zhujinru
 * 时间:2019/12/31 10:17
 * 作用:适配器
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<GsonBean.RankingBean> ranking;

    public MyAdapter(List<GsonBean.RankingBean> ranking) {
        this.ranking = ranking;
    }
//寻找布局
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.child, null);
        return new MyViewHolder(inflate);
    }
//设置数据
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GsonBean.RankingBean rankingBean = ranking.get(position);
        holder.ph.setText(rankingBean.getRank());
        holder.cname.setText(rankingBean.getName());
        NetUtil.getInstance().getPho(rankingBean.getAvatar(),holder.cimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ranking.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ph)
        TextView ph;
        @BindView(R.id.cimage)
        ImageView cimage;
        @BindView(R.id.cname)
        TextView cname;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //回调
    onItemClickListener onItemClickListener;

    public void setOnItemClickListener(MyAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener{
        void onItemClick(int i);
}
}
