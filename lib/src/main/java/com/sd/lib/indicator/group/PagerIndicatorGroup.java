package com.sd.lib.indicator.group;

import androidx.viewpager.widget.ViewPager;

import com.sd.lib.indicator.adapter.PagerIndicatorAdapter;
import com.sd.lib.indicator.item.PagerIndicatorItem;
import com.sd.lib.indicator.track.PagerIndicatorTrack;

/**
 * ViewPager指示器Group
 */
public interface PagerIndicatorGroup
{
    /**
     * 设置ViewPager
     *
     * @param viewPager
     */
    void setViewPager(ViewPager viewPager);

    /**
     * 返回设置的ViewPager
     *
     * @return
     */
    ViewPager getViewPager();

    /**
     * 设置适配器
     *
     * @param adapter
     */
    void setAdapter(PagerIndicatorAdapter adapter);

    /**
     * 返回设置的适配器
     *
     * @return
     */
    PagerIndicatorAdapter getAdapter();

    /**
     * 设置追踪指示器Item的view
     *
     * @param pagerIndicatorTrack
     */
    void setPagerIndicatorTrack(PagerIndicatorTrack pagerIndicatorTrack);

    /**
     * 返回跟随指示器Item的view
     *
     * @return
     */
    PagerIndicatorTrack getPagerIndicatorTrack();

    /**
     * 返回position位置对应的Item
     *
     * @param position
     * @return
     */
    PagerIndicatorItem getPagerIndicatorItem(int position);

    /**
     * 数据集变化回调
     *
     * @param count
     */
    void onDataSetChanged(int count);

    /**
     * ViewPager页面显示的百分比回调
     *
     * @param position    第几页
     * @param showPercent 显示的百分比[0-1]
     * @param isEnter     true-当前页面处于进入状态，false-当前页面处于离开状态
     * @param isMoveLeft  true-ViewPager内容向左移动，false-ViewPager内容向右移动
     */
    void onShowPercent(int position, float showPercent, boolean isEnter, boolean isMoveLeft);

    /**
     * ViewPager某一页选中或者非选中回调
     *
     * @param position 第几页
     * @param selected true-选中，false-未选中
     */
    void onSelectChanged(int position, boolean selected);
}
