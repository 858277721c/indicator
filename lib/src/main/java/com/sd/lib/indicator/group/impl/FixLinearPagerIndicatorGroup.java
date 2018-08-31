package com.sd.lib.indicator.group.impl;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.sd.lib.indicator.group.BasePagerIndicatorGroup;
import com.sd.lib.indicator.item.PagerIndicatorItem;

/**
 * 线性的ViewPager指示器Group
 */
public class FixLinearPagerIndicatorGroup extends BasePagerIndicatorGroup
{
    public FixLinearPagerIndicatorGroup(Context context)
    {
        super(context);
    }

    public FixLinearPagerIndicatorGroup(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public FixLinearPagerIndicatorGroup(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public PagerIndicatorItem getPagerIndicatorItem(int position)
    {
        return (PagerIndicatorItem) getChildAt(position);
    }

    @Override
    public void onViewAdded(View child)
    {
        super.onViewAdded(child);
        if (!(child instanceof PagerIndicatorItem))
            throw new RuntimeException("child must be instance of:" + PagerIndicatorItem.class);

        if (!child.hasOnClickListeners())
        {
            child.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    final ViewPager viewPager = getViewPager();
                    if (viewPager != null)
                        viewPager.setCurrentItem(indexOfChild(v));
                }
            });
        }
    }
}
