package com.hafiizh.japanesestation.ui.activity.bookmark;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hafiizh.japanesestation.base.BaseAdapter;
import com.hafiizh.japanesestation.data.local.entity.BookmarkEntity;
import com.hafiizh.japanesestation.databinding.BookmarkItemBinding;
import com.hafiizh.japanesestation.listener.OnSourceItemClickListener;
import com.hafiizh.japanesestation.util.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAFIIZH on 11/3/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class BookmarkAdapter extends BaseAdapter<BookmarkAdapter.BookmarkViewHolder, BookmarkEntity> {
    private List<BookmarkEntity> bookmarkEntities;
    private OnSourceItemClickListener<BookmarkEntity> onSourceItemClickListener;
    private OnSourceItemClickListener<BookmarkEntity> entityOnSourceItemClickListener;

    public BookmarkAdapter() {
        bookmarkEntities = new ArrayList<>();
    }

    public void setOnSourceItemClickListener(OnSourceItemClickListener<BookmarkEntity> onSourceItemClickListener) {
        this.onSourceItemClickListener = onSourceItemClickListener;
    }

    public void setEntityOnSourceItemClickListener(OnSourceItemClickListener<BookmarkEntity> entityOnSourceItemClickListener) {
        this.entityOnSourceItemClickListener = entityOnSourceItemClickListener;
    }

    @Nullable
    @Override
    public void setData(@Nullable List<BookmarkEntity> bookmarkEntities) {
        if (bookmarkEntities != null) {
            this.bookmarkEntities.clear();
            this.bookmarkEntities.addAll(bookmarkEntities);
            notifyDataSetChanged();
        }
    }

    @Override
    protected boolean areItemsTheSame(BookmarkEntity oldItem, BookmarkEntity newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    @Override
    protected boolean areContentsTheSame(BookmarkEntity oldItem, BookmarkEntity newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    @Override
    public BookmarkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookmarkViewHolder(
                BookmarkItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(BookmarkViewHolder holder, int position) {
        holder.onBind(bookmarkEntities.get(position));
    }

    @Override
    public int getItemCount() {
        return bookmarkEntities == null ? 0 : bookmarkEntities.size();
    }

    class BookmarkViewHolder extends RecyclerView.ViewHolder {
        BookmarkItemBinding binding;

        public BookmarkViewHolder(BookmarkItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.postsBook.setOnClickListener(v -> entityOnSourceItemClickListener.onSourceItemClick(v, getAdapterPosition(), bookmarkEntities));
            binding.getRoot().setOnClickListener(v -> onSourceItemClickListener.onSourceItemClick(v, getAdapterPosition(), bookmarkEntities));
        }

        public void onBind(BookmarkEntity entity) {
            binding.setBookmark(entity);
            binding.executePendingBindings();
        }
    }
}