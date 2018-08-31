package com.sd.lib.indicator.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.sd.lib.indicator.R;
import com.sd.lib.indicator.adapter.PagerIndicatorAdapter;
import com.sd.lib.indicator.group.PagerIndicatorGroup;
import com.sd.lib.indicator.item.PagerIndicatorItem;
import com.sd.lib.indicator.track.PagerIndicatorTrack;
import com.sd.lib.viewpager.helper.FPagerSelectedChangeListener;

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
    public void setAdapter(PagerIndicatorAdapter adapter)
    {
        mPagerIndicatorGroup.setAdapter(adapter);
    }

    /**
     * 返回设置的适配器
     *
     * @return
     */
    public PagerIndicatorAdapter getAdapter()
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
