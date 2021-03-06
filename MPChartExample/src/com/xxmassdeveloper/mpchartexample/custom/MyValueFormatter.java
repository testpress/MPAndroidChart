package com.xxmassdeveloper.mpchartexample.custom;

import com.github.testpress.mikephil.charting.data.Entry;
import com.github.testpress.mikephil.charting.formatter.FormattedStringCache;
import com.github.testpress.mikephil.charting.formatter.ValueFormatter;
import com.github.testpress.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class MyValueFormatter implements ValueFormatter {

    private FormattedStringCache.Generic<Integer, Float> mFormattedStringCache;
    
    public MyValueFormatter() {
        mFormattedStringCache = new FormattedStringCache.Generic<>(new DecimalFormat("###,###,###,##0.0"));
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mFormattedStringCache.getFormattedValue(value, dataSetIndex) + " $";
    }
}
