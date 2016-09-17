package com.github.testpress.mikephil.charting.interfaces.dataprovider;

import com.github.testpress.mikephil.charting.data.CombinedData;

/**
 * Created by philipp on 11/06/16.
 */
public interface CombinedDataProvider extends LineDataProvider, com.github.testpress.mikephil.charting.interfaces.dataprovider.BarDataProvider, com.github.testpress.mikephil.charting.interfaces.dataprovider.BubbleDataProvider, com.github.testpress.mikephil.charting.interfaces.dataprovider.CandleDataProvider, com.github.testpress.mikephil.charting.interfaces.dataprovider.ScatterDataProvider {

    CombinedData getCombinedData();
}
