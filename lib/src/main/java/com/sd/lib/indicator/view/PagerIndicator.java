package com.sd.lib.indicator.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.viewpager.widget.ViewPager;

import com.sd.lib.indicator.event.viewpager.ViewPagerEventPublisher;

public class PagerIndicator extends HorizontalScrollIndicator
{
    public PagerIndicator(Context context)
    {
        super(context);
    }

    public PagerIndicator(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public PagerIndicator(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置ViewPager
     *
     * @param viewPager
     */
    public void setViewPager(ViewPager viewPager)
    {
        setEventPublisher(new ViewPagerEventPublisher(viewPager));
    }
}
