package com.sd.lib.indicator.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.sd.lib.indicator.item.IndicatorItem;

public abstract class IndicatorAdapter
{
    /**
     * 创建Item
     *
     * @param position
     * @param viewParent
     * @return
     */
    public final View createIndicatorItem(int position, ViewGroup viewParent)
    {
        final IndicatorItem item = onCreateIndicatorItem(position, viewParent);
        if (item instanceof View)
        {
            return (View) item;
        } else
        {
            throw new IllegalArgumentException("onCreateIndicatorItem method must return instance of View");
        }
    }

    /**
     * 创建Item
     *
     * @param position
     * @param viewParent
     * @return
     */
    protected abstract IndicatorItem onCreateIndicatorItem(int position, ViewGroup viewParent);
}
