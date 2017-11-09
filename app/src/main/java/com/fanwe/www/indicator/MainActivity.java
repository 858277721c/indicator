package com.fanwe.www.indicator;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanwe.lib.indicator.impl.PagerIndicator;
import com.fanwe.library.adapter.SDPagerAdapter;

public class MainActivity extends AppCompatActivity
{
    private ViewPager vpg_content;
    private PagerIndicator view_indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vpg_content = (ViewPager) findViewById(R.id.vpg_content);
        view_indicator = (PagerIndicator) findViewById(R.id.view_indicator);

        vpg_content.setAdapter(mPagerAdapter);
        mPagerAdapter.updateData(DataModel.get(10));
    }

    private SDPagerAdapter<DataModel> mPagerAdapter = new SDPagerAdapter<DataModel>(null, this)
    {
        @Override
        public View getView(ViewGroup viewGroup, int position)
        {
            View view = getLayoutInflater().inflate(R.layout.item_vpg, viewGroup, false);

            TextView textView = (TextView) view.findViewById(R.id.btn);
            textView.setText(getData(position).name);

            return view;
        }
    };

}
