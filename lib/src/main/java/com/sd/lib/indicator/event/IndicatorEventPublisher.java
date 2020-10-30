package com.sd.lib.indicator.event;

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
     * 设置选中位置
     *
     * @param position
     */
    void setSelected(int position);

    /**
     * 返回选中的位置
     *
     * @return
     */
    int getSelectedPosition();

    /**
     * 返回一共有多少数据
     *
     * @return
     */
    int getDataCount();

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
