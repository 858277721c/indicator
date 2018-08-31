/*
 * Copyright (C) 2017 zhengjun, fanwe (http://www.fanwe.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sd.lib.indicator.group;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sd.lib.indicator.adapter.PagerIndicatorAdapter;
import com.sd.lib.indicator.item.PagerIndicatorItem;
import com.sd.lib.indicator.item.impl.ImagePagerIndicatorItem;
import com.sd.lib.indicator.track.PagerIndicatorTrack;
import com.sd.lib.viewpager.helper.FPagerPercentChangeListener;
import com.sd.lib.viewpager.helper.FPagerSelectedChangeListener;
import com.sd.lib.viewpager.helper.FViewPagerHolder;

/**
 * ViewPager指示器Group
 */
public abstract class BasePagerIndicatorGroup extends LinearLayout implements PagerIndicatorGroup
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

    private PagerIndicatorAdapter mAdapter;
    private PagerIndicatorTrack mPagerIndicatorTrack;

    private void init()
    {
        //设置一个默认的Adapter
        setAdapter(new PagerIndicatorAdapter()
        {
            @Override
            public PagerIndicatorItem onCreatePagerIndicatorItem(int position, ViewGroup viewParent)
            {
                return new ImagePagerIndicatorItem(getContext());
            }
        });
    }

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

    @Override
    public void setAdapter(PagerIndicatorAdapter adapter)
    {
        if (mAdapter != null)
            mAdapter.unregisterDataSetObserver(mIndicatorAdapterDataSetObserver);

        mAdapter = adapter;

        if (adapter != null)
            adapter.registerDataSetObserver(mIndicatorAdapterDataSetObserver);
    }

    @Override
    public PagerIndicatorAdapter getAdapter()
    {
        return mAdapter;
    }

    @Override
    public void setPagerIndicatorTrack(PagerIndicatorTrack pagerIndicatorTrack)
    {
        mPagerIndicatorTrack = pagerIndicatorTrack;
    }

    @Override
    public PagerIndicatorTrack getPagerIndicatorTrack()
    {
        return mPagerIndicatorTrack;
    }

    @Override
    public void onDataSetChanged(int count)
    {
        final PagerIndicatorTrack track = getPagerIndicatorTrack();
        if (track != null)
            track.onDataSetChanged(count);
    }

    @Override
    public void onShowPercent(int position, float showPercent, boolean isEnter, boolean isMoveLeft)
    {
        final PagerIndicatorItem item = getPagerIndicatorItem(position);
        if (item != null)
        {
            item.onShowPercent(showPercent, isEnter, isMoveLeft);

            final PagerIndicatorTrack track = getPagerIndicatorTrack();
            if (track != null)
                track.onShowPercent(position, showPercent, isEnter, isMoveLeft, item.getPositionData());
        }
    }

    @Override
    public void onSelectChanged(int position, boolean selected)
    {
        final PagerIndicatorItem item = getPagerIndicatorItem(position);
        if (item != null)
        {
            item.onSelectChanged(selected);

            final PagerIndicatorTrack track = getPagerIndicatorTrack();
            if (track != null)
                track.onSelectChanged(position, selected, item.getPositionData());
        }
    }

    private final DataSetObserver mIndicatorAdapterDataSetObserver = new DataSetObserver()
    {
        @Override
        public void onChanged()
        {
            super.onChanged();
            onDataSetChanged(mViewPagerHolder.getAdapterCount());
        }

        @Override
        public void onInvalidated()
        {
            super.onInvalidated();
        }
    };
}
