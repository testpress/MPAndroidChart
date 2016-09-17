package com.github.testpress.mikephil.charting.interfaces.dataprovider;

import com.github.testpress.mikephil.charting.data.BubbleData;

public interface BubbleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BubbleData getBubbleData();
}
