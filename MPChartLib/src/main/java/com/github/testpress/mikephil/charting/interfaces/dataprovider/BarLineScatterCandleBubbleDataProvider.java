package com.github.testpress.mikephil.charting.interfaces.dataprovider;

import com.github.testpress.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.testpress.mikephil.charting.utils.Transformer;
import com.github.testpress.mikephil.charting.components.YAxis;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(YAxis.AxisDependency axis);
    boolean isInverted(YAxis.AxisDependency axis);
    
    float getLowestVisibleX();
    float getHighestVisibleX();

    BarLineScatterCandleBubbleData getData();
}
