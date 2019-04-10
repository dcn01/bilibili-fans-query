package com.plain.bilibilitools.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.plain.bilibilitools.R;
import com.plain.bilibilitools.bean.FeatureListBean;

import java.util.ArrayList;

/**
 * Create by Plain on 2019/4/10 3:22 PM
 * Description: 功能列表适配器
 */
public class FeatureListAdapter extends RecyclerView.Adapter<FeatureListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<FeatureListBean> featureListBeans;
    private ViewHolder viewHolder;
    private OnItemClickListener onItemClickListener;

    public FeatureListAdapter(Context context, ArrayList<FeatureListBean> featureListBeans) {
        this.context = context;
        this.featureListBeans = featureListBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_rl_table_item,viewGroup,false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.ivIcon.setImageResource(featureListBeans.get(i).getIconId());
        viewHolder.tvName.setText(String.valueOf(featureListBeans.get(i).getName()));
        viewHolder.tvDescription.setText(String.valueOf(featureListBeans.get(i).getDescription()));

        viewHolder.cvRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.OnClick(viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return featureListBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivIcon;
        private final TextView tvName;
        private final TextView tvDescription;
        private final CardView cvRoot;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvRoot = itemView.findViewById(R.id.cv_root);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void OnClick(int p);
    }

}
