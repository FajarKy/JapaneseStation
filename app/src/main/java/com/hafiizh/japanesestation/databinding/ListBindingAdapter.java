package com.hafiizh.japanesestation.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.hafiizh.japanesestation.base.BaseAdapter;
import com.hafiizh.japanesestation.data.Resource;

import java.util.List;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public final class ListBindingAdapter {
    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView rv, Resource resource) {
        RecyclerView.Adapter adapter = rv.getAdapter();
        if (adapter == null)
            return;
        if (resource == null || resource.data == null)
            return;
        if (adapter instanceof BaseAdapter) {
            ((BaseAdapter) adapter).setData((List) resource.data);
        }
    }
}
