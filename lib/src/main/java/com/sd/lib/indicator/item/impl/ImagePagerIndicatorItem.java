package com.sd.lib.indicator.item.impl;

import android.content.Context;
import android.util.AttributeSet;

import com.sd.lib.indicator.item.PagerIndicatorItem;

/**
 * 用{@link ImageIndicatorItem}替代
 */
@Deprecated
public class ImagePagerIndicatorItem extends ImageIndicatorItem implements PagerIndicatorItem
{
    public ImagePagerIndicatorItem(Context context)
    {
        super(context);
    }

    public ImagePagerIndicatorItem(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ImagePagerIndicatorItem(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }
}
