package com.sd.www.indicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */

public class DataModel
{

    public String name;

    public static List<DataModel> get(int count)
    {
        List<DataModel> listModel = new ArrayList<>();
        for (int i = 0; i < count; i++)
        {
            DataModel model = new DataModel();
            model.name = String.valueOf(i);
            listModel.add(model);
        }
        return listModel;
    }
}
