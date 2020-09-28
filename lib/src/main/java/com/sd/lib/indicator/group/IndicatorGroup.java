package com.sd.lib.indicator.group;

import com.sd.lib.indicator.adapter.IndicatorAdapter;
import com.sd.lib.indicator.event.IndicatorEventPublisher;
import com.sd.lib.indicator.item.IndicatorItem;
import com.sd.lib.indicator.track.IndicatorTrack;

/**
 * 指示器Group
 */
public interface IndicatorGroup
{
    /**
     * 设置事件发布者
     *
     * @param publisher
     */
    void setEventPublisher(IndicatorEventPublisher publisher);

    /**
     * 返回设置的事件发布者
     *
     * @return
     */
    IndicatorEventPublisher getEventPublisher();

    /**
     * 设置选中状态回调
     *
     * @param callback
     */
    void setSelectChangeCallback(SelectChangeCallback callback);

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

    interface SelectChangeCallback
    {
        /**
         * 选中或者非选中回调
         *
         * @param position 位置
         * @param selected true-选中，false-未选中
         */
        void onSelectChanged(int position, boolean selected);
    }
}
