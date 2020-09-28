package com.sd.lib.indicator.group;

import com.sd.lib.indicator.adapter.IndicatorAdapter;
import com.sd.lib.indicator.item.IndicatorItem;
import com.sd.lib.indicator.track.IndicatorTrack;

/**
 * 指示器Group
 */
public interface IndicatorGroup
{
    /**
     * 设置适配器
     *
     * @param adapter
     */
    void setAdapter(IndicatorAdapter adapter);

    /**
     * 返回设置的适配器
     *
     * @return
     */
    IndicatorAdapter getAdapter();

    /**
     * 设置追踪指示器Item的view
     *
     * @param indicatorTrack
     */
    void setIndicatorTrack(IndicatorTrack indicatorTrack);

    /**
     * 返回跟随指示器Item的view
     *
     * @return
     */
    IndicatorTrack getIndicatorTrack();

    /**
     * 返回position位置对应的Item
     *
     * @param position
     * @return
     */
    IndicatorItem getIndicatorItem(int position);

    /**
     * 数据集变化回调
     *
     * @param count
     */
    void onDataSetChanged(int count);

    /**
     * 页面显示的百分比回调
     *
     * @param position    第几页
     * @param showPercent 显示的百分比[0-1]
     * @param isEnter     true-当前页面处于进入状态，false-当前页面处于离开状态
     * @param isMoveLeft  true-内容向左移动，false-内容向右移动
     */
    void onShowPercent(int position, float showPercent, boolean isEnter, boolean isMoveLeft);

    /**
     * 某一页选中或者非选中回调
     *
     * @param position 第几页
     * @param selected true-选中，false-未选中
     */
    void onSelectChanged(int position, boolean selected);
}
