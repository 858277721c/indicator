package com.sd.lib.indicator.adapter;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

import com.sd.lib.indicator.item.IndicatorItem;

public abstract class IndicatorAdapter
{
    private final DataSetObservable mDataSetObservable = new DataSetObservable();

    public final void registerDataSetObserver(DataSetObserver observer)
    {
        mDataSetObservable.registerObserver(observer);
    }

    public final void unregisterDataSetObserver(DataSetObserver observer)
    {
        mDataSetObservable.unregisterObserver(observer);
    }

    public final void notifyDataSetChanged()
    {
        mDataSetObservable.notifyChanged();
    }

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
     * 返回个数
     *
     * @return
     */
    public abstract int getCount();

    /**
     * 创建Item
     *
     * @param position
     * @param viewParent
     * @return
     */
    protected abstract IndicatorItem onCreateIndicatorItem(int position, ViewGroup viewParent);
}
