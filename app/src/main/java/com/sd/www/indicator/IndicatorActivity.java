package com.sd.www.indicator;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.sd.lib.adapter.FPagerAdapter;
import com.sd.lib.indicator.adapter.IndicatorAdapter;
import com.sd.lib.indicator.item.IndicatorItem;
import com.sd.lib.indicator.item.impl.ImageIndicatorItem;
import com.sd.lib.indicator.item.impl.UnderlineIndicatorItem;
import com.sd.www.indicator.databinding.ActivityIndicatorBinding;
import com.sd.www.indicator.databinding.ItemViewPagerBinding;

public class IndicatorActivity extends AppCompatActivity
{
    private ActivityIndicatorBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mBinding = ActivityIndicatorBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        // 为指示器绑定ViewPager
        mBinding.viewIndicatorDefault.setViewPager(mBinding.vpgContent);
        mBinding.viewIndicatorUnderline.setViewPager(mBinding.vpgContent);

        // 为指示器设置适配器
        mBinding.viewIndicatorDefault.setAdapter(mDefaultIndicatorAdapter);
        mBinding.viewIndicatorUnderline.setAdapter(mUnderlineIndicatorAdapter);

        mBinding.vpgContent.setAdapter(mPagerAdapter);
        mPagerAdapter.getDataHolder().setData(DataModel.get(20));
    }

    private final FPagerAdapter<DataModel> mPagerAdapter = new FPagerAdapter<DataModel>()
    {
        @Override
        public View getView(ViewGroup viewGroup, final int position)
        {
            final DataModel model = getDataHolder().get(position);
            final ItemViewPagerBinding binding = ItemViewPagerBinding.inflate(getLayoutInflater(), viewGroup, false);

            binding.btn.setText(model.name);
            binding.btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    getDataHolder().removeData(model);
                }
            });

            return binding.getRoot();
        }
    };

    /**
     * 指示器适配器
     */
    private final IndicatorAdapter mDefaultIndicatorAdapter = new IndicatorAdapter()
    {
        @Override
        protected IndicatorItem onCreateIndicatorItem(int position, ViewGroup viewParent)
        {
            return new ImageIndicatorItem(IndicatorActivity.this);
        }
    };

    /**
     * 指示器适配器
     */
    private final IndicatorAdapter mUnderlineIndicatorAdapter = new IndicatorAdapter()
    {
        @Override
        protected IndicatorItem onCreateIndicatorItem(int position, ViewGroup viewParent)
        {
            final UnderlineIndicatorItem item = new UnderlineIndicatorItem(IndicatorActivity.this);
            item.getTextViewName().setText(String.valueOf(position));
            item.setMinimumWidth(100);
            return item;
        }
    };
}
