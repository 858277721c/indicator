package com.sd.lib.indicator.item.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sd.lib.indicator.R;
import com.sd.lib.indicator.item.IndicatorItem;
import com.sd.lib.indicator.model.PositionData;

public class UnderlineIndicatorItem extends FrameLayout implements IndicatorItem
{
    public UnderlineIndicatorItem(Context context)
    {
        super(context);
        init();
    }

    public UnderlineIndicatorItem(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public UnderlineIndicatorItem(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private TextView tv_name;
    private View view_underline;

    private PositionData mPositionData;

    private void init()
    {
        LayoutInflater.from(getContext()).inflate(R.layout.lib_indicator_underline_item, this, true);
        tv_name = findViewById(R.id.tv_name);
        view_underline = findViewById(R.id.view_underline);
    }

    public TextView getTextViewName()
    {
        return tv_name;
    }

    public View getViewUnderline()
    {
        return view_underline;
    }

    @Override
    public void onSelectChanged(boolean selected)
    {
        setSelected(selected);
    }

    @Override
    public void onShowPercent(float showPercent, boolean isEnter, boolean isMoveLeft)
    {

    }

    @Override
    public PositionData getPositionData()
    {
        if (mPositionData == null)
            mPositionData = new PositionData();
        return mPositionData;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);

        getPositionData().left = getLeft();
        getPositionData().top = getTop();
        getPositionData().right = getRight();
        getPositionData().bottom = getBottom();
    }
}
