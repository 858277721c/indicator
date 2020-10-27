package com.sd.lib.indicator.group.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.sd.lib.indicator.event.IndicatorEventPublisher;
import com.sd.lib.indicator.group.BaseIndicatorGroup;
import com.sd.lib.indicator.item.IndicatorItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 线性的指示器Group
 */
public class FixLinearIndicatorGroup extends BaseIndicatorGroup
{
    public FixLinearIndicatorGroup(Context context)
    {
        super(context);
    }

    public FixLinearIndicatorGroup(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public FixLinearIndicatorGroup(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    private final List<IndicatorItem> mListItem = new ArrayList<>();

    @Override
    public IndicatorItem getIndicatorItem(int position)
    {
        return (IndicatorItem) getChildAt(position);
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
                    final int index = mListItem.indexOf(view);
                    publisher.setSelected(index);
                }
            }
        });
    }

    @Override
    public void onViewAdded(View child)
    {
        super.onViewAdded(child);
        if (child instanceof IndicatorItem)
        {
            final int index = indexOfChild(child);
            final IndicatorItem item = (IndicatorItem) child;
            mListItem.add(index, item);
            registerClickListenerIfNeed(child);
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

    @Override
    public void onViewRemoved(View child)
    {
        super.onViewRemoved(child);
        if (child instanceof IndicatorItem)
        {
            final IndicatorItem item = (IndicatorItem) child;
            mListItem.remove(item);
        }
    }
}
