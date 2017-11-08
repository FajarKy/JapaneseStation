package com.hafiizh.japanesestation.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public abstract class BaseAdapter<Type extends RecyclerView.ViewHolder, Data> extends RecyclerView.Adapter<Type> {
    private int dataVersion = 0;

    @Nullable
    List<Data> items;

    @Nullable
    public abstract void setData(@Nullable List<Data> data);

    @SuppressLint("StaticFieldLeak")
    @MainThread
    public void replace(List<Data> update) {
        dataVersion++;
        if (items == null) {
            if (update == null)
                return;
            setData(update);
            notifyDataSetChanged();
        } else if (update == null) {
            int oldSize = items.size();
            setData(null);
            notifyItemRangeRemoved(0, oldSize);
        } else {
            int startVersion = dataVersion;
            List<Data> oldItems = items;
            new AsyncTask<Void, Void, DiffUtil.DiffResult>() {
                @Override
                protected DiffUtil.DiffResult doInBackground(Void... voids) {
                    return DiffUtil.calculateDiff(new DiffUtil.Callback() {
                        @Override
                        public int getOldListSize() {
                            return oldItems.size();
                        }

                        @Override
                        public int getNewListSize() {
                            return update.size();
                        }

                        @Override
                        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                            Data oldItem = oldItems.get(oldItemPosition);
                            Data newItem = update.get(newItemPosition);
                            return BaseAdapter.this.areItemsTheSame(oldItem, newItem);
                        }

                        @Override
                        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                            Data oldItem = oldItems.get(oldItemPosition);
                            Data newItem = update.get(newItemPosition);
                            return BaseAdapter.this.areContentsTheSame(oldItem, newItem);
                        }
                    });
                }

                @Override
                protected void onPostExecute(DiffUtil.DiffResult diffResult) {
                    if (startVersion != dataVersion)
                        return;
                    setData(update);
                    diffResult.dispatchUpdatesTo(BaseAdapter.this);
                }
            }.execute();
        }
    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    public void replace(List<Data> update, SwipeRefreshLayout refreshLayout) {
        dataVersion++;
        if (items == null) {
            if (update == null)
                return;
            setData(update);
            notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
        } else if (update == null) {
            int oldSize = items.size();
            setData(null);
            notifyItemRangeRemoved(0, oldSize);
            refreshLayout.setRefreshing(false);
        } else {
            int startVersion = dataVersion;
            List<Data> oldItems = items;
            new AsyncTask<Void, Void, DiffUtil.DiffResult>() {
                @Override
                protected DiffUtil.DiffResult doInBackground(Void... voids) {
                    return DiffUtil.calculateDiff(new DiffUtil.Callback() {
                        @Override
                        public int getOldListSize() {
                            return oldItems.size();
                        }

                        @Override
                        public int getNewListSize() {
                            return update.size();
                        }

                        @Override
                        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                            Data oldItem = oldItems.get(oldItemPosition);
                            Data newItem = update.get(newItemPosition);
                            return BaseAdapter.this.areItemsTheSame(oldItem, newItem);
                        }

                        @Override
                        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                            Data oldItem = oldItems.get(oldItemPosition);
                            Data newItem = update.get(newItemPosition);
                            return BaseAdapter.this.areContentsTheSame(oldItem, newItem);
                        }
                    });
                }

                @Override
                protected void onPostExecute(DiffUtil.DiffResult diffResult) {
                    if (startVersion != dataVersion)
                        return;
                    setData(update);
                    diffResult.dispatchUpdatesTo(BaseAdapter.this);
                }
            }.execute();
            refreshLayout.setRefreshing(false);
        }
    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    public void replace(List<Data> update, ProgressDialog dialog) {
        dataVersion++;
        if (items == null) {
            if (update == null)
                return;
            setData(update);
            notifyDataSetChanged();
            dialog.dismiss();
        } else if (update == null) {
            int oldSize = items.size();
            setData(null);
            notifyItemRangeRemoved(0, oldSize);
            dialog.dismiss();
        } else {
            int startVersion = dataVersion;
            List<Data> oldItems = items;
            new AsyncTask<Void, Void, DiffUtil.DiffResult>() {
                @Override
                protected DiffUtil.DiffResult doInBackground(Void... voids) {
                    return DiffUtil.calculateDiff(new DiffUtil.Callback() {
                        @Override
                        public int getOldListSize() {
                            return oldItems.size();
                        }

                        @Override
                        public int getNewListSize() {
                            return update.size();
                        }

                        @Override
                        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                            Data oldItem = oldItems.get(oldItemPosition);
                            Data newItem = update.get(newItemPosition);
                            return BaseAdapter.this.areItemsTheSame(oldItem, newItem);
                        }

                        @Override
                        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                            Data oldItem = oldItems.get(oldItemPosition);
                            Data newItem = update.get(newItemPosition);
                            return BaseAdapter.this.areContentsTheSame(oldItem, newItem);
                        }
                    });
                }
                @Override
                protected void onPostExecute(DiffUtil.DiffResult diffResult) {
                    if (startVersion != dataVersion)
                        return;
                    setData(update);
                    diffResult.dispatchUpdatesTo(BaseAdapter.this);
                }
            }.execute();
            dialog.dismiss();
        }
    }

    protected abstract boolean areItemsTheSame(Data oldItem, Data newItem);

    protected abstract boolean areContentsTheSame(Data oldItem, Data newItem);
}