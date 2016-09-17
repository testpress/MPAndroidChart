package com.github.testpress.mikephil.charting.highlight;

import com.github.testpress.mikephil.charting.charts.PieChart;
import com.github.testpress.mikephil.charting.charts.PieRadarChartBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by philipp on 12/06/16.
 */
public abstract class PieRadarHighlighter<T extends PieRadarChartBase> implements Highlighter {

    protected T mChart;

    /**
     * buffer for storing previously highlighted values
     */
    protected List<com.github.testpress.mikephil.charting.highlight.Highlight> mHighlightBuffer = new ArrayList<com.github.testpress.mikephil.charting.highlight.Highlight>();

    public PieRadarHighlighter(T chart) {
        this.mChart = chart;
    }

    @Override
    public com.github.testpress.mikephil.charting.highlight.Highlight getHighlight(float x, float y) {

        float touchDistanceToCenter = mChart.distanceToCenter(x, y);

        // check if a slice was touched
        if (touchDistanceToCenter > mChart.getRadius()) {

            // if no slice was touched, highlight nothing
            return null;

        } else {

            float angle = mChart.getAngleForPoint(x, y);

            if (mChart instanceof PieChart) {
                angle /= mChart.getAnimator().getPhaseY();
            }

            int index = mChart.getIndexForAngle(angle);

            // check if the index could be found
            if (index < 0 || index >= mChart.getData().getMaxEntryCountSet().getEntryCount()) {
                return null;

            } else {
                return getClosestHighlight(index, x, y);
            }
        }
    }

    /**
     * Returns the closest Highlight object of the given objects based on the touch position inside the chart.
     *
     * @param index
     * @param x
     * @param y
     * @return
     */
    protected abstract com.github.testpress.mikephil.charting.highlight.Highlight getClosestHighlight(int index, float x, float y);
}
