package com.hafiizh.japanesestation.ui.fragment.popular;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hafiizh.japanesestation.BR;
import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.base.BaseAdapter;
import com.hafiizh.japanesestation.data.local.entity.PopularEntity;
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

public class PopularAdapter extends BaseAdapter<PopularAdapter.PopularViewHolder, PopularEntity> {
    private List<PopularEntity> entityList;
    private OnSourceItemClickListener<PopularEntity> onSourceItemClickListener;
    private OnSourceItemClickListener<PopularEntity> entityOnSourceItemClickListener;

    public void setEntityOnSourceItemClickListener(OnSourceItemClickListener<PopularEntity> entityOnSourceItemClickListener) {
        this.entityOnSourceItemClickListener = entityOnSourceItemClickListener;
    }

    public PopularAdapter() {
        entityList = new ArrayList<>();
    }

    @Override
    public PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PopularViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(PopularViewHolder holder, int position) {
        holder.onBind(entityList.get(position));
    }

    @Override
    public int getItemCount() {
        return entityList == null ? 0 : entityList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.layout.popular_item;
        } else {
            return R.layout.popular_item2;
        }
    }

    public void setOnSourceItemClickListener(OnSourceItemClickListener<PopularEntity> onSourceItemClickListener) {
        this.onSourceItemClickListener = onSourceItemClickListener;
    }

    @Override
    public void setData(List<PopularEntity> tabEntities) {
        if (tabEntities != null) {
            this.entityList.clear();
            this.entityList.addAll(tabEntities);
            notifyDataSetChanged();
        }
    }

    @Override
    protected boolean areItemsTheSame(PopularEntity oldItem, PopularEntity newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    @Override
    protected boolean areContentsTheSame(PopularEntity oldItem, PopularEntity newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    class PopularViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        public PopularViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().findViewById(R.id.posts_book).setOnClickListener(v -> entityOnSourceItemClickListener.onSourceItemClick(v, getAdapterPosition(), entityList));
            binding.getRoot().setOnClickListener(v -> onSourceItemClickListener.onSourceItemClick(v, getAdapterPosition(), entityList));
        }

        public void onBind(PopularEntity entity) {
            binding.setVariable(BR.popular, entity);
            binding.executePendingBindings();
        }
    }
}