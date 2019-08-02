package com.plain.bilibilitools.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.plain.bilibilitools.R;
import com.plain.bilibilitools.bean.PicListBean;
import com.plain.rvbannerlibrary.adapter.RvBannerBaseAdapter;

import java.util.List;

/**
 * Author : Plain
 * ClassName : com.plain.bilibilitools.adapter
 * Description :
 * CreateDate : 2019-08-02 15:48
 * Version : V1.0
 */
public class PicBannerAdapter extends RvBannerBaseAdapter<PicListBean, PicBannerAdapter.PicViewHolder> {


    /**
     * 需要上下文和数据
     *
     * @param context 上下文
     * @param list    数据
     */
    public PicBannerAdapter(Context context, List<PicListBean> list) {
        super(context, list);
    }

    @Override
    protected void bindData(PicViewHolder picViewHolder, int i, List<PicListBean> list, Context context) {
        Glide.with(context)
                .load(list.get(i).getPicUrl())
                .into(picViewHolder.mIvPic);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_rv_banner_pic;
    }

    @Override
    protected PicViewHolder createVH(View view) {
        return new PicViewHolder(view);
    }

    class PicViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIvPic;

        PicViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvPic = itemView.findViewById(R.id.ivPic);
        }
    }

}
