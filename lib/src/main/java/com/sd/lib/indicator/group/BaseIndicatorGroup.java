package com.sd.lib.indicator.group;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sd.lib.indicator.adapter.IndicatorAdapter;
import com.sd.lib.indicator.event.IndicatorEventPublisher;
import com.sd.lib.indicator.item.IndicatorItem;
import com.sd.lib.indicator.item.impl.ImageIndicatorItem;
import com.sd.lib.indicator.track.IndicatorTrack;

/**
 * 指示器Group
 */
public abstract class BaseIndicatorGroup extends LinearLayout implements IndicatorGroup, IndicatorEventPublisher.EventListener
{
    public BaseIndicatorGroup(Context context)
    {
        super(context);
        init();
    }

    public BaseIndicatorGroup(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public BaseIndicatorGroup(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private IndicatorEventPublisher mEventPublisher;
    private IndicatorAdapter mAdapter;
    private IndicatorTrack mIndicatorTrack;

    private SelectChangeCallback mSelectChangeCallback;

    private void init()
    {
        // 设置默认适配器
        setAdapter(new IndicatorAdapter()
        {
            @Override
            protected IndicatorItem onCreateIndicatorItem(int position, ViewGroup viewParent)
            {
                return new ImageIndicatorItem(getContext());
            }
        });
    }

    @Override
    public void setEventPublisher(IndicatorEventPublisher publisher)
    {
        final IndicatorEventPublisher oldPublisher = mEventPublisher;
        if (oldPublisher != publisher)
        {
            if (oldPublisher != null)
                oldPublisher.destroy();

            mEventPublisher = publisher;

            if (publisher != null)
                publisher.setEventListener(this);
        }
    }

    @Override
    public IndicatorEventPublisher getEventPublisher()
    {
        return mEventPublisher;
    }

    @Override
    public void setSelectChangeCallback(SelectChangeCallback callback)
    {
        mSelectChangeCallback = callback;
    }

    @Override
    public void setAdapter(IndicatorAdapter adapter)
    {
        final IndicatorAdapter oldAdapter = mAdapter;
        if (oldAdapter != adapter)
        {
            if (oldAdapter != null)
                oldAdapter.unregisterDataSetObserver(mAdapterDataSetObserver);

            mAdapter = adapter;

            if (adapter != null)
                adapter.registerDataSetObserver(mAdapterDataSetObserver);

            final IndicatorEventPublisher publisher = mEventPublisher;
            if (publisher != null)
            {
                final int count = publisher.getDataCount();
                onDataSetChanged(count);
            }
        }
    }

    @Override
    public IndicatorAdapter getAdapter()
    {
        return mAdapter;
    }

    @Override
    public void setIndicatorTrack(IndicatorTrack indicatorTrack)
    {
        mIndicatorTrack = indicatorTrack;
    }

    @Override
    public IndicatorTrack getIndicatorTrack()
    {
        return mIndicatorTrack;
    }

    @Override
    public void onDataSetChanged(int count)
    {
        final IndicatorTrack track = getIndicatorTrack();
        if (track != null)
            track.onDataSetChanged(count);
    }

    @Override
    public void onShowPercent(int position, float showPercent, boolean isEnter, boolean isMoveLeft)
    {
        final IndicatorItem item = getIndicatorItem(position);
        if (item != null)
        {
            item.onShowPercent(showPercent, isEnter, isMoveLeft);

            final IndicatorTrack track = getIndicatorTrack();
            if (track != null)
                track.onShowPercent(position, showPercent, isEnter, isMoveLeft, item.getPositionData());
        }
    }

    @Override
    public void onSelectChanged(int position, boolean selected)
    {
        final IndicatorItem item = getIndicatorItem(position);
        if (item != null)
        {
            item.onSelectChanged(selected);

            final IndicatorTrack track = getIndicatorTrack();
            if (track != null)
                track.onSelectChanged(position, selected, item.getPositionData());
        }

        if (mSelectChangeCallback != null)
            mSelectChangeCallback.onSelectChanged(position, selected);
    }

    /**
     * 适配器数据监听
     */
    private final DataSetObserver mAdapterDataSetObserver = new DataSetObserver()
    {
        @Override
        public void onChanged()
        {
            super.onChanged();
            final IndicatorEventPublisher publisher = mEventPublisher;
            if (publisher != null)
            {
                final int count = publisher.getDataCount();
                BaseIndicatorGroup.this.onDataSetChanged(count);
            }
        }
    };
}
