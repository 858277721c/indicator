package com.sd.lib.indicator.group;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.sd.lib.indicator.adapter.IndicatorAdapter;
import com.sd.lib.indicator.item.IndicatorItem;
import com.sd.lib.indicator.item.impl.ImageIndicatorItem;
import com.sd.lib.viewpager.helper.FPagerPercentChangeListener;
import com.sd.lib.viewpager.helper.FPagerSelectedChangeListener;
import com.sd.lib.viewpager.helper.FViewPagerHolder;

/**
 * ViewPager指示器Group
 */
public abstract class BasePagerIndicatorGroup extends BaseIndicatorGroup implements PagerIndicatorGroup
{
    public BasePagerIndicatorGroup(Context context)
    {
        super(context);
        init();
    }

    public BasePagerIndicatorGroup(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public BasePagerIndicatorGroup(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        //设置一个默认的Adapter
        setAdapter(new IndicatorAdapter()
        {
            @Override
            public int getCount()
            {
                return mViewPagerHolder.getAdapterCount();
            }

            @Override
            protected IndicatorItem onCreateIndicatorItem(int position, ViewGroup viewParent)
            {
                return new ImageIndicatorItem(getContext());
            }
        });
    }

    @Override
    public void setViewPager(ViewPager viewPager)
    {
        mViewPagerHolder.setViewPager(viewPager);
    }

    @Override
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
            BasePagerIndicatorGroup.this.onDataSetChanged(getAdapterCount());
            super.onDataSetChanged();
        }

        @Override
        protected void onSelectedChanged(int index, boolean selected)
        {
            BasePagerIndicatorGroup.this.onSelectChanged(index, selected);
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
            BasePagerIndicatorGroup.this.onShowPercent(position, showPercent, isEnter, isMoveLeft);
        }
    };
}
