package com.sd.lib.indicator.track;

import com.sd.lib.indicator.model.PositionData;

/**
 * 可追踪指示器Item的view
 */
public interface IndicatorTrack
{
    /**
     * 数据集变化回调
     *
     * @param count
     */
    void onDataSetChanged(int count);

    /**
     * 页面显示的百分比回调
     *
     * @param position     位置
     * @param showPercent  显示的百分比[0-1]
     * @param isEnter      true-当前页面处于进入状态，false-当前页面处于离开状态
     * @param isMoveLeft   true-内容向左移动，false-内容向右移动
     * @param positionData 当前position对应Item的位置信息
     */
    void onShowPercent(int position, float showPercent, boolean isEnter, boolean isMoveLeft, PositionData positionData);

    /**
     * 选中或者非选中回调
     *
     * @param position     位置
     * @param selected     true-选中，false-未选中
     * @param positionData 当前position对应Item的位置信息
     */
    void onSelectChanged(int position, boolean selected, PositionData positionData);
}
