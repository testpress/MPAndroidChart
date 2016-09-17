package com.github.testpress.mikephil.charting.interfaces.dataprovider;

import com.github.testpress.mikephil.charting.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
