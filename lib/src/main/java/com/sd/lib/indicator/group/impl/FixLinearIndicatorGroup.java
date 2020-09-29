package com.sd.lib.indicator.group.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.sd.lib.indicator.group.BaseIndicatorGroup;
import com.sd.lib.indicator.item.IndicatorItem;

/**
 * 线性的指示器Group
 */
public class FixLinearIndicatorGroup extends BaseIndicatorGroup
{
    public FixLinearIndicatorGroup(Context context)
    {
        super(context);
    }

    public FixLinearIndicatorGroup(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public FixLinearIndicatorGroup(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public IndicatorItem getIndicatorItem(int position)
    {
        return (IndicatorItem) getChildAt(position);
    }

    @Override
    public void onViewAdded(View child)
    {
        super.onViewAdded(child);
        if (child instanceof IndicatorItem)
        {
            final int index = indexOfChild(child);
            initIndicatorItemView(child, index);
        } else
        {
            post(new Runnable()
            {
                @Override
                public void run()
                {
                    throw new RuntimeException("child must be instance of " + IndicatorItem.class.getName());
                }
            });
        }
    }
}
