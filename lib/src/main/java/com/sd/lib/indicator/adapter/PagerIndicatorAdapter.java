package com.sd.lib.indicator.adapter;

import android.view.ViewGroup;

import com.sd.lib.indicator.item.IndicatorItem;
import com.sd.lib.indicator.item.PagerIndicatorItem;

/**
 * 用{@link IndicatorAdapter}替代
 */
@Deprecated
public abstract class PagerIndicatorAdapter extends IndicatorAdapter
{
    @Override
    protected final IndicatorItem onCreateIndicatorItem(int position, ViewGroup viewParent)
    {
        return onCreatePagerIndicatorItem(position, viewParent);
    }

    @Override
    public int getCount()
    {
        return 0;
    }

    /**
     * 创建Item
     *
     * @param position
     * @param viewParent
     * @return
     */
    protected abstract PagerIndicatorItem onCreatePagerIndicatorItem(int position, ViewGroup viewParent);
}
