package com.sd.lib.indicator.event.viewpager;

import androidx.viewpager.widget.ViewPager;

import com.sd.lib.indicator.event.IndicatorEventPublisher;
import com.sd.lib.viewpager.helper.FPagerPercentChangeListener;
import com.sd.lib.viewpager.helper.FPagerSelectedChangeListener;
import com.sd.lib.viewpager.helper.FViewPagerHolder;

import java.lang.ref.WeakReference;

/**
 * ViewPager事件发布者
 */
public class ViewPagerEventPublisher implements IndicatorEventPublisher
{
    private final WeakReference<ViewPager> mViewPager;
    private EventListener mEventListener;

    public ViewPagerEventPublisher(ViewPager viewPager)
    {
        if (viewPager == null)
            throw new NullPointerException("viewPager is null");
        mViewPager = new WeakReference<>(viewPager);
    }

    @Override
    public void setEventListener(EventListener listener)
    {
        if (listener == null)
            throw new NullPointerException("listener is null");

        mEventListener = listener;
        mViewPagerHolder.setViewPager(mViewPager.get());
    }

    @Override
    public void setSelected(int position)
    {
        final ViewPager viewPager = getViewPager();
        if (viewPager != null)
            viewPager.setCurrentItem(position);
    }

    @Override
    public int getSelectedPosition()
    {
        final ViewPager viewPager = getViewPager();
        if (viewPager != null)
            return viewPager.getCurrentItem();
        return -1;
    }

    @Override
    public int getDataCount()
    {
        return mViewPagerHolder.getAdapterCount();
    }

    public ViewPager getViewPager()
    {
        return mViewPagerHolder.getViewPager();
    }

    /**
     * 保存ViewPager
     */
    private final FViewPagerHolder mViewPagerHolder = new FViewPagerHolder()
    {
        @Override
        protected void onViewPagerChanged(ViewPager oldPager, ViewPager newPager)
        {
            mPagerSelectedChangeListener.setViewPager(newPager);
            mPagerPercentChangeListener.setViewPager(newPager);
        }
    };

    /**
     * 页面数量变化和选中监听
     */
    private final FPagerSelectedChangeListener mPagerSelectedChangeListener = new FPagerSelectedChangeListener()
    {
        @Override
        protected void onDataSetChanged()
        {
            mEventListener.onDataSetChanged(getAdapterCount());
            super.onDataSetChanged();
        }

        @Override
        protected void onSelectedChanged(int index, boolean selected)
        {
            mEventListener.onSelectChanged(index, selected);
        }
    };

    /**
     * 滚动百分比监听
     */
    private final FPagerPercentChangeListener mPagerPercentChangeListener = new FPagerPercentChangeListener()
    {
        @Override
        public void onShowPercent(int position, float showPercent, boolean isEnter, boolean isMoveLeft)
        {
            mEventListener.onShowPercent(position, showPercent, isEnter, isMoveLeft);
        }
    };

    @Override
    public void destroy()
    {
        mViewPagerHolder.setViewPager(null);
        mEventListener = null;
    }
}
