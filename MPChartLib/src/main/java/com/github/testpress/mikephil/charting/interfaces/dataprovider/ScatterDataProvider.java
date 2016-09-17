package com.github.testpress.mikephil.charting.interfaces.dataprovider;

import com.github.testpress.mikephil.charting.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
