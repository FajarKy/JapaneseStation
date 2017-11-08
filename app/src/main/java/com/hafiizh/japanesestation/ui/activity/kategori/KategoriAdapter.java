package com.hafiizh.japanesestation.ui.activity.kategori;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hafiizh.japanesestation.BR;
import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.base.BaseAdapter;
import com.hafiizh.japanesestation.data.local.entity.KategoriEntity;
import com.hafiizh.japanesestation.listener.OnSourceItemClickListener;
import com.hafiizh.japanesestation.util.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAFIIZH on 10/25/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class KategoriAdapter extends BaseAdapter<KategoriAdapter.KategoriViewHolder, KategoriEntity> {
    private List<KategoriEntity> kategoriEntities;
    private OnSourceItemClickListener<KategoriEntity> onSourceItemClickListener;
    private OnSourceItemClickListener<KategoriEntity> entityOnSourceItemClickListener;

    public void setEntityOnSourceItemClickListener(OnSourceItemClickListener<KategoriEntity> entityOnSourceItemClickListener) {
        this.entityOnSourceItemClickListener = entityOnSourceItemClickListener;
    }

    public KategoriAdapter() {
        kategoriEntities = new ArrayList<>();
    }

    public void setOnSourceItemClickListener(OnSourceItemClickListener<KategoriEntity> onSourceItemClickListener) {
        this.onSourceItemClickListener = onSourceItemClickListener;
    }

    @Override
    public void setData(List<KategoriEntity> kategoriEntities) {
        if (kategoriEntities != null) {
            this.kategoriEntities.clear();
            this.kategoriEntities.addAll(kategoriEntities);
            notifyDataSetChanged();
        }
    }

    @Override
    protected boolean areItemsTheSame(KategoriEntity oldItem, KategoriEntity newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    @Override
    protected boolean areContentsTheSame(KategoriEntity oldItem, KategoriEntity newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    @Override
    public KategoriViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KategoriViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(KategoriViewHolder holder, int position) {
        holder.onBind(kategoriEntities.get(position));
    }

    @Override
    public int getItemCount() {
        return kategoriEntities == null ? 0 : kategoriEntities.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return R.layout.kategori_item;
        else
            return R.layout.kategori_item2;
    }

    class KategoriViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        public KategoriViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().findViewById(R.id.posts_book).setOnClickListener(v -> entityOnSourceItemClickListener.onSourceItemClick(v, getAdapterPosition(), kategoriEntities));
            binding.getRoot().setOnClickListener(v -> onSourceItemClickListener.onSourceItemClick(v, getAdapterPosition(), kategoriEntities));
        }

        public void onBind(KategoriEntity entity) {
            binding.setVariable(BR.kategori, entity);
            binding.executePendingBindings();
        }
    }
}