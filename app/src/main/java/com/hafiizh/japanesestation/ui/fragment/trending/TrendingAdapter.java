package com.hafiizh.japanesestation.ui.fragment.trending;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hafiizh.japanesestation.BR;
import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.base.BaseAdapter;
import com.hafiizh.japanesestation.data.local.entity.TrendingEntity;
import com.hafiizh.japanesestation.listener.OnSourceItemClickListener;
import com.hafiizh.japanesestation.util.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class TrendingAdapter extends BaseAdapter<TrendingAdapter.TrendingViewHolder, TrendingEntity> {
    private List<TrendingEntity> entityList;
    private OnSourceItemClickListener<TrendingEntity> onSourceItemClickListener;
    private OnSourceItemClickListener<TrendingEntity> entityOnSourceItemClickListener;

    public void setEntityOnSourceItemClickListener(OnSourceItemClickListener<TrendingEntity> entityOnSourceItemClickListener) {
        this.entityOnSourceItemClickListener = entityOnSourceItemClickListener;
    }

    public TrendingAdapter() {
        entityList = new ArrayList<>();
    }

    @Override
    public TrendingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrendingViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(TrendingViewHolder holder, int position) {
        holder.onBind(entityList.get(position));
    }

    @Override
    public int getItemCount() {
        return entityList == null ? 0 : entityList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.layout.trending_item;
        } else {
            return R.layout.trending_item2;
        }
    }

    public void setOnSourceItemClickListener(OnSourceItemClickListener<TrendingEntity> onSourceItemClickListener) {
        this.onSourceItemClickListener = onSourceItemClickListener;
    }

    @Override
    public void setData(List<TrendingEntity> tabEntities) {
        if (tabEntities != null) {
            this.entityList.clear();
            this.entityList.addAll(tabEntities);
            notifyDataSetChanged();
        }
    }

    @Override
    protected boolean areItemsTheSame(TrendingEntity oldItem, TrendingEntity newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    @Override
    protected boolean areContentsTheSame(TrendingEntity oldItem, TrendingEntity newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    class TrendingViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        public TrendingViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().findViewById(R.id.posts_book).setOnClickListener(v -> entityOnSourceItemClickListener.onSourceItemClick(v, getAdapterPosition(), entityList));
            binding.getRoot().setOnClickListener(v -> onSourceItemClickListener.onSourceItemClick(v, getAdapterPosition(), entityList));
        }

        public void onBind(TrendingEntity entity) {
            binding.setVariable(BR.trending, entity);
            binding.executePendingBindings();
        }
    }
}