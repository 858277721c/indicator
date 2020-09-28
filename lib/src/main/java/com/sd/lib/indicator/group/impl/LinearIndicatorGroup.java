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

    private void init()
    {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    @Override
    public IndicatorItem getIndicatorItem(int position)
    {
        return (IndicatorItem) getChildAt(position);
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

        final IndicatorEventPublisher publisher = getEventPublisher();

        for (int i = 0; i < count; i++)
        {
            final View view = adapter.createIndicatorItem(i, this);

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

            if (publisher != null)
                publisher.initItemView(view, i);
        }
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
