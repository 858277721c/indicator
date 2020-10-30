package com.sd.lib.indicator.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.sd.lib.indicator.R;
import com.sd.lib.indicator.adapter.IndicatorAdapter;
import com.sd.lib.indicator.event.IndicatorEventPublisher;
import com.sd.lib.indicator.group.IndicatorGroup;
import com.sd.lib.indicator.item.IndicatorItem;
import com.sd.lib.indicator.track.IndicatorTrack;

public class HorizontalScrollIndicator extends FrameLayout
{
    public HorizontalScrollIndicator(Context context)
    {
        super(context);
        init();
    }

    public HorizontalScrollIndicator(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public HorizontalScrollIndicator(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private TrackHorizontalScrollView mHorizontalScrollView;
    private IndicatorGroup mIndicatorGroup;
    private ViewGroup mIndicatorTrackContainer;

    private void init()
    {
        LayoutInflater.from(getContext()).inflate(R.layout.lib_indicator_horizontal_scroll, this, true);

        mHorizontalScrollView = findViewById(R.id.view_scroll);
        mIndicatorGroup = findViewById(R.id.view_indicator_group);
        mIndicatorTrackContainer = findViewById(R.id.view_indicator_track_container);

        mIndicatorGroup.setSelectChangeCallback(new IndicatorGroup.SelectChangeCallback()
        {
            @Override
            public void onSelectChanged(int position, boolean selected)
            {
                if (selected)
                {
                    final IndicatorItem item = mIndicatorGroup.getIndicatorItem(position);
                    if (item != null)
                        mHorizontalScrollView.smoothScrollTo((View) item);
                }
            }
        });
    }

    /**
     * 设置事件发布者
     *
     * @param publisher
     */
    public void setEventPublisher(IndicatorEventPublisher publisher)
    {
        mIndicatorGroup.setEventPublisher(publisher);
    }

    /**
     * 设置适配器
     *
     * @param adapter
     */
    public void setAdapter(IndicatorAdapter adapter)
    {
        mIndicatorGroup.setAdapter(adapter);
    }

    /**
     * 设置可追踪指示器Item的view
     *
     * @param track
     */
    public void setIndicatorTrack(IndicatorTrack track)
    {
        final IndicatorTrack oldTrack = mIndicatorGroup.getIndicatorTrack();
        if (oldTrack != track)
        {
            if (oldTrack != null)
                mIndicatorTrackContainer.removeAllViews();

            mIndicatorGroup.setIndicatorTrack(track);

            if (track != null)
            {
                if (track instanceof View)
                {
                    mIndicatorTrackContainer.addView((View) track);
                } else
                {
                    throw new IllegalArgumentException("track must be instance of view");
                }
            }
        }
    }

    /**
     * 清空选中状态
     */
    public void clearSelected()
    {
        mIndicatorGroup.clearSelected();
    }
}
