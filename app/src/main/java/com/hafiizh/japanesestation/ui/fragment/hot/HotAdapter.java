package com.hafiizh.japanesestation.ui.fragment.hot;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hafiizh.japanesestation.BR;
import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.base.BaseAdapter;
import com.hafiizh.japanesestation.data.local.entity.HotEntity;
import com.hafiizh.japanesestation.listener.OnSourceItemClickListener;
import com.hafiizh.japanesestation.util.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAFIIZH on 10/24/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class HotAdapter extends BaseAdapter<HotAdapter.HotViewHolder, HotEntity> {
    private List<HotEntity> hotEntities;
    private OnSourceItemClickListener<HotEntity> onSourceItemClickListener;
    private OnSourceItemClickListener<HotEntity> entityOnSourceItemClickListener;

    public HotAdapter() {
        hotEntities = new ArrayList<>();
    }

    public void setOnSourceItemClickListener(OnSourceItemClickListener<HotEntity> onSourceItemClickListener) {
        this.onSourceItemClickListener = onSourceItemClickListener;
    }

    public void setEntityOnSourceItemClickListener(OnSourceItemClickListener<HotEntity> entityOnSourceItemClickListener) {
        this.entityOnSourceItemClickListener = entityOnSourceItemClickListener;
    }

    @Override
    public void setData(List<HotEntity> hotEntities) {
        if (hotEntities != null) {
            this.hotEntities.clear();
            this.hotEntities.addAll(hotEntities);
            notifyDataSetChanged();
        }
    }

    @Override
    protected boolean areItemsTheSame(HotEntity oldItem, HotEntity newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    @Override
    protected boolean areContentsTheSame(HotEntity oldItem, HotEntity newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    @Override
    public HotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(HotViewHolder holder, int position) {
        holder.onBind(hotEntities.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return R.layout.hot_item;
        else
            return R.layout.hot_item2;
    }

    @Override
    public int getItemCount() {
        return hotEntities == null ? 0 : hotEntities.size();
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        public HotViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().findViewById(R.id.posts_book).setOnClickListener(v -> entityOnSourceItemClickListener.onSourceItemClick(v, getAdapterPosition(), hotEntities));
            binding.getRoot().setOnClickListener(v -> onSourceItemClickListener.onSourceItemClick(v, getAdapterPosition(), hotEntities));
        }

        public void onBind(HotEntity hotEntity) {
            binding.setVariable(BR.hot, hotEntity);
            binding.executePendingBindings();
        }
    }
}