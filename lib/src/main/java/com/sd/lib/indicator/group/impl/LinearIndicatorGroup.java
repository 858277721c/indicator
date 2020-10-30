package com.sd.lib.indicator.group.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.sd.lib.indicator.adapter.IndicatorAdapter;
import com.sd.lib.indicator.event.IndicatorEventPublisher;
import com.sd.lib.indicator.group.BaseIndicatorGroup;
import com.sd.lib.indicator.item.IndicatorItem;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * 线性的指示器Group
 */
public class LinearIndicatorGroup extends BaseIndicatorGroup
{
    public LinearIndicatorGroup(Context context)
    {
        super(context);
        init();
    }

    public LinearIndicatorGroup(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public LinearIndicatorGroup(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private final Map<View, Integer> mMapView = new WeakHashMap<>();

    private void init()
    {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    @Override
    public IndicatorItem getIndicatorItem(int position)
    {
        return (IndicatorItem) getPositionView(position);
    }

    @Override
    public void onDataSetChanged(int count)
    {
        super.onDataSetChanged(count);
        removeAllViews();
        addIndicatorItem(count);
    }

    /**
     * 添加Item
     *
     * @param count 要添加的数量
     */
    private void addIndicatorItem(int count)
    {
        if (count <= 0)
            return;

        final IndicatorAdapter adapter = getAdapter();
        if (adapter == null)
            return;

        mMapView.clear();
        for (int i = 0; i < count; i++)
        {
            final View view = adapter.createIndicatorItem(i, this);
            if (view.getParent() == null)
            {
                ViewGroup.LayoutParams params = view.getLayoutParams();
                if (params == null)
                {
                    if (getOrientation() == HORIZONTAL)
                    {
                        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.MATCH_PARENT);
                    } else
                    {
                        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                    }
                    view.setLayoutParams(params);
                }

                addView(view, params);
            }

            mMapView.put(view, i);
            registerClickListenerIfNeed(view);
        }
    }

    private void registerClickListenerIfNeed(final View view)
    {
        if (view.hasOnClickListeners())
            return;

        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final IndicatorEventPublisher publisher = getEventPublisher();
                if (publisher != null)
                {
                    final int position = getViewPosition(view);
                    publisher.setSelected(position);
                }
            }
        });
    }

    private View getPositionView(int position)
    {
        if (mMapView.isEmpty())
            return null;

        for (Map.Entry<View, Integer> entry : mMapView.entrySet())
        {
            if (position == entry.getValue())
                return entry.getKey();
        }
        return null;
    }

    private int getViewPosition(View view)
    {
        return mMapView.get(view);
    }

    @Override
    public void onViewAdded(View child)
    {
        super.onViewAdded(child);
        if (child instanceof IndicatorItem)
        {
        } else
        {
            post(new Runnable()
            {
                @Override
                public void run()
                {
                    throw new RuntimeException("child must be instance of " + IndicatorItem.class.getName());
                }
            });
        }
    }
}
