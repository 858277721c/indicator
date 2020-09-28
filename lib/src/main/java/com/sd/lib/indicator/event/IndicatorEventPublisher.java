package com.sd.lib.indicator.event;

import android.view.View;

/**
 * 指示器事件发布者
 */
public interface IndicatorEventPublisher
{
    /**
     * 设置事件监听
     *
     * @param listener
     */
    void setEventListener(EventListener listener);

    /**
     * 初始化Item
     *
     * @param view
     * @param position
     */
    void initItemView(View view, int position);

    /**
     * 销毁
     */
    void destroy();

    /**
     * 事件监听
     */
    interface EventListener
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
         * @param position    位置
         * @param showPercent 显示的百分比[0-1]
         * @param isEnter     true-当前页面处于进入状态，false-当前页面处于离开状态
         * @param isMoveLeft  true-内容向左移动，false-内容向右移动
         */
        void onShowPercent(int position, float showPercent, boolean isEnter, boolean isMoveLeft);

        /**
         * 选中或者非选中回调
         *
         * @param position 位置
         * @param selected true-选中，false-未选中
         */
        void onSelectChanged(int position, boolean selected);
    }
}
