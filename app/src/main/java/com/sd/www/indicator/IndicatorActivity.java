package com.sd.www.indicator;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.sd.lib.adapter.FPagerAdapter;
import com.sd.lib.indicator.adapter.IndicatorAdapter;
import com.sd.lib.indicator.item.IndicatorItem;
import com.sd.lib.indicator.item.impl.UnderlineIndicatorItem;
import com.sd.lib.indicator.view.PagerIndicator;

public class IndicatorActivity extends AppCompatActivity
{
    private ViewPager vpg_content;
    private PagerIndicator view_indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);
        vpg_content = findViewById(R.id.vpg_content);
        view_indicator = findViewById(R.id.view_indicator);

        view_indicator.setViewPager(vpg_content);
        view_indicator.setAdapter(new IndicatorAdapter()
        {
            @Override
            protected IndicatorItem onCreateIndicatorItem(int position, ViewGroup viewParent)
            {
                final UnderlineIndicatorItem item = new UnderlineIndicatorItem(IndicatorActivity.this);
                item.getTextViewName().setText(String.valueOf(position));
                return item;
            }
        });

        vpg_content.setAdapter(mPagerAdapter);
        mPagerAdapter.getDataHolder().setData(DataModel.get(20));
    }

    private final FPagerAdapter<DataModel> mPagerAdapter = new FPagerAdapter<DataModel>()
    {
        @Override
        public View getView(ViewGroup viewGroup, final int position)
        {
            final DataModel model = getDataHolder().get(position);

            final View view = getLayoutInflater().inflate(R.layout.item_view_pager, viewGroup, false);
            final TextView textView = view.findViewById(R.id.btn);
            textView.setText(model.name);
            textView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    getDataHolder().removeData(model);
                }
            });

            return view;
        }
    };
}
