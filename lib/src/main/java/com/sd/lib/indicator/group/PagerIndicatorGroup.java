package com.sd.lib.indicator.group;

import androidx.viewpager.widget.ViewPager;

/**
 * ViewPager指示器Group
 */
public interface PagerIndicatorGroup extends IndicatorGroup
{
    /**
     * 设置ViewPager
     *
     * @param viewPager
     */
    void setViewPager(ViewPager viewPager);

    /**
     * 返回设置的ViewPager
     *
     * @return
     */
    ViewPager getViewPager();
}
