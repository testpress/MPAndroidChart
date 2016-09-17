package com.xxmassdeveloper.mpchartexample.custom;

import com.github.testpress.mikephil.charting.components.AxisBase;
import com.github.testpress.mikephil.charting.formatter.AxisValueFormatter;
import com.github.testpress.mikephil.charting.formatter.FormattedStringCache;

import java.text.DecimalFormat;

public class MyAxisValueFormatter implements AxisValueFormatter {

    private DecimalFormat mFormat;
    private FormattedStringCache.PrimFloat mFormattedStringCache;

    public MyAxisValueFormatter() {
        mFormattedStringCache = new FormattedStringCache.PrimFloat(new DecimalFormat("###,###,###,##0.0"));
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormattedStringCache.getFormattedValue(value) + " $";
    }

    @Override
    public int getDecimalDigits() {
        return 1;
    }
}
