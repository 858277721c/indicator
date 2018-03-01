package com.fanwe.www.indicator;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanwe.lib.indicator.common.utils.FViewPagerInfoListener;
import com.fanwe.library.adapter.SDPagerAdapter;
import com.fanwe.library.utils.LogUtil;

public class ViewPagerInfoActivity extends AppCompatActivity
{
    private ViewPager vpg_content;
    private FViewPagerInfoListener mViewPagerInfoListener = new FViewPagerInfoListener();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_info);
        vpg_content = findViewById(R.id.vpg_content);

        initListener();

        vpg_content.setAdapter(mPagerAdapter);
        mPagerAdapter.updateData(DataModel.get(10));
    }

    private SDPagerAdapter<DataModel> mPagerAdapter = new SDPagerAdapter<DataModel>(null, this)
    {
        @Override
        public View getView(ViewGroup viewGroup, final int position)
        {
            View view = getLayoutInflater().inflate(R.layout.item_vpg, viewGroup, false);

            TextView textView = view.findViewById(R.id.btn);
            textView.setText(getData(position).name);
            textView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    removeData(position);
                }
            });

            return view;
        }
    };

    private void initListener()
    {
        mViewPagerInfoListener.setDataSetObserver(mDataSetObserver);
        mViewPagerInfoListener.setOnPageCountChangeCallback(mOnPageCountChangeCallback);
        mViewPagerInfoListener.setOnPageSelectedChangeCallback(mOnPageSelectedChangeCallback);
        mViewPagerInfoListener.setOnPageScrolledPercentCallback(mOnPageScrolledPercentCallback);
        mViewPagerInfoListener.setViewPager(vpg_content); //设置要监听的ViewPager
    }

    private FViewPagerInfoListener.OnPageScrolledPercentCallback mOnPageScrolledPercentCallback = new FViewPagerInfoListener.OnPageScrolledPercentCallback()
    {
        @Override
        public void onShowPercent(int position, float showPercent, boolean isEnter, boolean isMoveLeft)
        {
            if (isEnter)
            {
                LogUtil.e("onShowPercent enter:" + position + " " + showPercent + " " + isMoveLeft);
            } else
            {
                LogUtil.e("onShowPercent leave---------->:" + position + " " + showPercent + " " + isMoveLeft);
            }
        }
    };

    private DataSetObserver mDataSetObserver = new DataSetObserver()
    {
        @Override
        public void onChanged()
        {
            super.onChanged();
            LogUtil.i("DataSetObserver onChanged");
        }
    };

    private FViewPagerInfoListener.OnPageCountChangeCallback mOnPageCountChangeCallback = new FViewPagerInfoListener.OnPageCountChangeCallback()
    {
        @Override
        public void onPageCountChanged(int count)
        {
            LogUtil.i("onPageCountChanged:" + count);
        }
    };

    private FViewPagerInfoListener.OnPageSelectedChangeCallback mOnPageSelectedChangeCallback = new FViewPagerInfoListener.OnPageSelectedChangeCallback()
    {
        @Override
        public void onSelectedChanged(int position, boolean selected)
        {
            LogUtil.i("onSelectedChanged:" + position + " " + selected);
        }
    };


}
