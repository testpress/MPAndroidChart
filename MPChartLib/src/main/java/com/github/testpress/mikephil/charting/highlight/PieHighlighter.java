package com.github.testpress.mikephil.charting.highlight;

import com.github.testpress.mikephil.charting.charts.PieChart;
import com.github.testpress.mikephil.charting.data.Entry;
import com.github.testpress.mikephil.charting.interfaces.datasets.IPieDataSet;

/**
 * Created by philipp on 12/06/16.
 */
public class PieHighlighter extends PieRadarHighlighter<PieChart> {

    public PieHighlighter(PieChart chart) {
        super(chart);
    }

    @Override
    protected com.github.testpress.mikephil.charting.highlight.Highlight getClosestHighlight(int index, float x, float y) {

        IPieDataSet set = mChart.getData().getDataSet();

        final Entry entry = set.getEntryForIndex(index);

        return new com.github.testpress.mikephil.charting.highlight.Highlight(index, entry.getY(), x, y, 0, set.getAxisDependency());
    }
}
