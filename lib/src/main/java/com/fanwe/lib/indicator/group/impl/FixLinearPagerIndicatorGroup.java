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
package com.fanwe.lib.indicator.group.impl;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.fanwe.lib.indicator.group.BasePagerIndicatorGroup;
import com.fanwe.lib.indicator.item.PagerIndicatorItem;

/**
 * 线性的ViewPager指示器Group
 */
public class FixLinearPagerIndicatorGroup extends BasePagerIndicatorGroup
{
    public FixLinearPagerIndicatorGroup(Context context)
    {
        super(context);
    }

    public FixLinearPagerIndicatorGroup(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public FixLinearPagerIndicatorGroup(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public PagerIndicatorItem getPagerIndicatorItem(int position)
    {
        return (PagerIndicatorItem) getChildAt(position);
    }

    @Override
    public void onViewAdded(View child)
    {
        super.onViewAdded(child);
        if (!(child instanceof PagerIndicatorItem))
            throw new RuntimeException("child must be instance of:" + PagerIndicatorItem.class);

        if (!child.hasOnClickListeners())
        {
            child.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    final ViewPager viewPager = getViewPager();
                    if (viewPager != null)
                        viewPager.setCurrentItem(indexOfChild(v));
                }
            });
        }
    }
}
