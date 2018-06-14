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
package com.fanwe.lib.indicator.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import com.fanwe.lib.indicator.R;
import com.fanwe.lib.indicator.group.PagerIndicatorGroup;
import com.fanwe.lib.indicator.item.PagerIndicatorItem;
import com.fanwe.lib.indicator.track.PagerIndicatorTrack;
import com.fanwe.lib.viewpager.helper.FPagerSelectedChangeListener;

public class PagerIndicator extends FrameLayout
{
    public PagerIndicator(Context context)
    {
        super(context);
        init();
    }

    public PagerIndicator(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public PagerIndicator(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private TrackHorizontalScrollView mHorizontalScrollView;
    private PagerIndicatorGroup mPagerIndicatorGroup;
    private ViewGroup mPagerIndicatorTrackContainer;

    private void init()
    {
        LayoutInflater.from(getContext()).inflate(R.layout.lib_indicator_pager_indicator, this, true);

        mHorizontalScrollView = findViewById(R.id.view_scroll);
        mPagerIndicatorGroup = findViewById(R.id.view_indicator_group);
        mPagerIndicatorTrackContainer = findViewById(R.id.view_indicator_track_container);
    }

    private final FPagerSelectedChangeListener mPagerSelectChangeListener = new FPagerSelectedChangeListener()
    {
        @Override
        protected void onSelectedChanged(int index, boolean selected)
        {
            if (selected)
            {
                final PagerIndicatorItem item = mPagerIndicatorGroup.getPagerIndicatorItem(index);
                if (item != null)
                    mHorizontalScrollView.smoothScrollTo((View) item);
            }
        }
    };

    /**
     * 设置ViewPager
     *
     * @param viewPager
     */
    public void setViewPager(ViewPager viewPager)
    {
        mPagerSelectChangeListener.setViewPager(viewPager);
        mPagerIndicatorGroup.setViewPager(viewPager);
    }

    /**
     * 设置适配器
     *
     * @param adapter
     */
    public void setAdapter(BaseAdapter adapter)
    {
        mPagerIndicatorGroup.setAdapter(adapter);
    }

    /**
     * 返回设置的适配器
     *
     * @return
     */
    public BaseAdapter getAdapter()
    {
        return mPagerIndicatorGroup.getAdapter();
    }

    /**
     * 返回position位置对应的Item
     *
     * @param position
     * @return
     */
    public PagerIndicatorItem getPagerIndicatorItem(int position)
    {
        return mPagerIndicatorGroup.getPagerIndicatorItem(position);
    }

    /**
     * 设置可追踪指示器Item的view
     *
     * @param track
     */
    public void setPagerIndicatorTrack(PagerIndicatorTrack track)
    {
        final PagerIndicatorTrack oldTrack = mPagerIndicatorGroup.getPagerIndicatorTrack();
        if (oldTrack != track)
        {
            if (oldTrack != null)
                mPagerIndicatorTrackContainer.removeAllViews();

            mPagerIndicatorGroup.setPagerIndicatorTrack(track);

            if (track != null)
            {
                if (track instanceof View)
                {
                    mPagerIndicatorTrackContainer.addView((View) track);
                } else
                {
                    throw new IllegalArgumentException("pagerIndicatorView must be instance of view");
                }
            }
        }
    }
}
