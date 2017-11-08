package com.hafiizh.japanesestation.ui.activity.kategori;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hafiizh.japanesestation.base.BaseAdapter;
import com.hafiizh.japanesestation.data.local.entity.KategoriTagEntity;
import com.hafiizh.japanesestation.databinding.KategoriTagItemBinding;
import com.hafiizh.japanesestation.listener.OnSourceItemClickListener;
import com.hafiizh.japanesestation.util.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAFIIZH on 11/7/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class KategoriTagAdapter extends RecyclerView.Adapter<KategoriTagAdapter.KategoriTagViewHolder> {
    private List<KategoriTagEntity> kategoriTagEntities;
    private OnSourceItemClickListener<KategoriTagEntity> onSourceItemClickListener;
    private OnSourceItemClickListener<KategoriTagEntity> entityOnSourceItemClickListener;

    public KategoriTagAdapter() {
        kategoriTagEntities = new ArrayList<>();
    }

    @Override
    public KategoriTagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KategoriTagViewHolder(
                KategoriTagItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(KategoriTagViewHolder holder, int position) {
        holder.onBind(kategoriTagEntities.get(position));
    }

    @Override
    public int getItemCount() {
        return kategoriTagEntities == null ? 0 : kategoriTagEntities.size();
    }

    public void setOnSourceItemClickListener(OnSourceItemClickListener<KategoriTagEntity> onSourceItemClickListener) {
        this.onSourceItemClickListener = onSourceItemClickListener;
    }

    public void setEntityOnSourceItemClickListener(OnSourceItemClickListener<KategoriTagEntity> entityOnSourceItemClickListener) {
        this.entityOnSourceItemClickListener = entityOnSourceItemClickListener;
    }

    public void setData(List<KategoriTagEntity> entities) {
        if (kategoriTagEntities != null)
            this.kategoriTagEntities.addAll(entities);
    }

    public void clear() {
        kategoriTagEntities.clear();
    }

    class KategoriTagViewHolder extends RecyclerView.ViewHolder {
        KategoriTagItemBinding binding;

        public KategoriTagViewHolder(KategoriTagItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.postsImage.setOnClickListener(v -> entityOnSourceItemClickListener.onSourceItemClick(v, getAdapterPosition(), kategoriTagEntities));
            binding.getRoot().setOnClickListener(v -> onSourceItemClickListener.onSourceItemClick(v, getAdapterPosition(), kategoriTagEntities));
        }

        public void onBind(KategoriTagEntity entity) {
            binding.setKategori_tag(entity);
            binding.executePendingBindings();
        }
    }
}