package com.github.testpress.mikephil.charting.interfaces.dataprovider;

import com.github.testpress.mikephil.charting.components.YAxis;
import com.github.testpress.mikephil.charting.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
