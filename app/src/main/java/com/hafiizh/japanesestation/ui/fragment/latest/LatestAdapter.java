package com.hafiizh.japanesestation.ui.fragment.latest;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hafiizh.japanesestation.BR;
import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.base.BaseAdapter;
import com.hafiizh.japanesestation.data.local.entity.LatestEntity;
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

public class LatestAdapter extends BaseAdapter<LatestAdapter.LatestViewHolder, LatestEntity> {
    private List<LatestEntity> latestEntities;
    private OnSourceItemClickListener<LatestEntity> onSourceItemClickListener;
    private OnSourceItemClickListener<LatestEntity> entityOnSourceItemClickListener;

    public void setOnSourceItemClickListener(OnSourceItemClickListener<LatestEntity> onSourceItemClickListener) {
        this.onSourceItemClickListener = onSourceItemClickListener;
    }

    public void setEntityOnSourceItemClickListener(OnSourceItemClickListener<LatestEntity> entityOnSourceItemClickListener) {
        this.entityOnSourceItemClickListener = entityOnSourceItemClickListener;
    }

    public LatestAdapter() {
        latestEntities = new ArrayList<>();
    }

    @Nullable
    @Override
    public void setData(@Nullable List<LatestEntity> latestEntities) {
        if (latestEntities != null) {
            this.latestEntities.clear();
            this.latestEntities.addAll(latestEntities);
            notifyDataSetChanged();
        }
    }

    @Override
    protected boolean areItemsTheSame(LatestEntity oldItem, LatestEntity newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    @Override
    protected boolean areContentsTheSame(LatestEntity oldItem, LatestEntity newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId()) &&
                Objects.equals(oldItem.getAuthor(), newItem.getAuthor()) &&
                Objects.equals(oldItem.getAuthor_link(), newItem.getAuthor_link()) &&
                Objects.equals(oldItem.getDay_publish(), newItem.getDay_publish()) &&
                Objects.equals(oldItem.getDay_update(), newItem.getDay_update()) &&
                Objects.equals(oldItem.getTime_publish(), newItem.getTime_publish()) &&
                Objects.equals(oldItem.getTime_update(), newItem.getTime_update()) &&
                Objects.equals(oldItem.getImage(), newItem.getImage()) &&
                Objects.equals(oldItem.getImage_author(), newItem.getImage_author()) &&
                Objects.equals(oldItem.getImg_reaction(), newItem.getImg_reaction()) &&
                Objects.equals(oldItem.getSub_title(), newItem.getSub_title()) &&
                Objects.equals(oldItem.getTag(), newItem.getTag()) &&
                Objects.equals(oldItem.getTag_link(), newItem.getTag_link()) &&
                Objects.equals(oldItem.getTitle(), newItem.getTitle()) &&
                Objects.equals(oldItem.getTitle_link(), newItem.getTitle_link()) &&
                Objects.equals(oldItem.getViewer(), newItem.getViewer());
    }

    @Override
    public LatestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LatestViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(LatestViewHolder holder, int position) {
        holder.onBind(latestEntities.get(position));
    }

    @Override
    public int getItemCount() {
        return latestEntities == null ? 0 : latestEntities.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return R.layout.latest_item;
        else
            return R.layout.latest_item2;
    }

    class LatestViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        public LatestViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().findViewById(R.id.posts_book).setOnClickListener(v -> entityOnSourceItemClickListener.onSourceItemClick(v, getAdapterPosition(), latestEntities));
            binding.getRoot().setOnClickListener(v -> onSourceItemClickListener.onSourceItemClick(v, getAdapterPosition(), latestEntities));
        }

        public void onBind(LatestEntity latestEntity) {
            binding.setVariable(BR.latest, latestEntity);
            binding.executePendingBindings();
        }
    }
}