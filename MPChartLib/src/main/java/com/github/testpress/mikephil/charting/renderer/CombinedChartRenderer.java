package com.github.testpress.mikephil.charting.renderer;

import android.graphics.Canvas;

import com.github.testpress.mikephil.charting.animation.ChartAnimator;
import com.github.testpress.mikephil.charting.charts.Chart;
import com.github.testpress.mikephil.charting.charts.CombinedChart;
import com.github.testpress.mikephil.charting.charts.CombinedChart.DrawOrder;
import com.github.testpress.mikephil.charting.data.ChartData;
import com.github.testpress.mikephil.charting.data.CombinedData;
import com.github.testpress.mikephil.charting.highlight.Highlight;
import com.github.testpress.mikephil.charting.utils.ViewPortHandler;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Renderer class that is responsible for rendering multiple different data-types.
 */
public class CombinedChartRenderer extends com.github.testpress.mikephil.charting.renderer.DataRenderer {

    /**
     * all rederers for the different kinds of data this combined-renderer can draw
     */
    protected List<com.github.testpress.mikephil.charting.renderer.DataRenderer> mRenderers = new ArrayList<com.github.testpress.mikephil.charting.renderer.DataRenderer>(5);

    protected WeakReference<Chart> mChart;

    public CombinedChartRenderer(CombinedChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        mChart = new WeakReference<Chart>(chart);
        createRenderers(chart, animator, viewPortHandler);
    }

    /**
     * Creates the renderers needed for this combined-renderer in the required order. Also takes the DrawOrder into
     * consideration.
     *
     * @param chart
     * @param animator
     * @param viewPortHandler
     */
    protected void createRenderers(CombinedChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {

        mRenderers.clear();

        DrawOrder[] orders = chart.getDrawOrder();

        for (DrawOrder order : orders) {

            switch (order) {
                case BAR:
                    if (chart.getBarData() != null)
                        mRenderers.add(new BarChartRenderer(chart, animator, viewPortHandler));
                    break;
                case BUBBLE:
                    if (chart.getBubbleData() != null)
                        mRenderers.add(new com.github.testpress.mikephil.charting.renderer.BubbleChartRenderer(chart, animator, viewPortHandler));
                    break;
                case LINE:
                    if (chart.getLineData() != null)
                        mRenderers.add(new com.github.testpress.mikephil.charting.renderer.LineChartRenderer(chart, animator, viewPortHandler));
                    break;
                case CANDLE:
                    if (chart.getCandleData() != null)
                        mRenderers.add(new com.github.testpress.mikephil.charting.renderer.CandleStickChartRenderer(chart, animator, viewPortHandler));
                    break;
                case SCATTER:
                    if (chart.getScatterData() != null)
                        mRenderers.add(new com.github.testpress.mikephil.charting.renderer.ScatterChartRenderer(chart, animator, viewPortHandler));
                    break;
            }
        }
    }

    @Override
    public void initBuffers() {

        for (com.github.testpress.mikephil.charting.renderer.DataRenderer renderer : mRenderers)
            renderer.initBuffers();
    }

    @Override
    public void drawData(Canvas c) {

        for (com.github.testpress.mikephil.charting.renderer.DataRenderer renderer : mRenderers)
            renderer.drawData(c);
    }

    @Override
    public void drawValues(Canvas c) {

        for (com.github.testpress.mikephil.charting.renderer.DataRenderer renderer : mRenderers)
            renderer.drawValues(c);
    }

    @Override
    public void drawExtras(Canvas c) {

        for (com.github.testpress.mikephil.charting.renderer.DataRenderer renderer : mRenderers)
            renderer.drawExtras(c);
    }

    protected List<Highlight> mHighlightBuffer = new ArrayList<Highlight>();

    @Override
    public void drawHighlighted(Canvas c, Highlight[] indices) {

        Chart chart = mChart.get();
        if (chart == null) return;

        for (com.github.testpress.mikephil.charting.renderer.DataRenderer renderer : mRenderers) {
            ChartData data = null;

            if (renderer instanceof BarChartRenderer)
                data = ((BarChartRenderer)renderer).mChart.getBarData();
            else if (renderer instanceof com.github.testpress.mikephil.charting.renderer.LineChartRenderer)
                data = ((com.github.testpress.mikephil.charting.renderer.LineChartRenderer)renderer).mChart.getLineData();
            else if (renderer instanceof com.github.testpress.mikephil.charting.renderer.CandleStickChartRenderer)
                data = ((com.github.testpress.mikephil.charting.renderer.CandleStickChartRenderer)renderer).mChart.getCandleData();
            else if (renderer instanceof com.github.testpress.mikephil.charting.renderer.ScatterChartRenderer)
                data = ((com.github.testpress.mikephil.charting.renderer.ScatterChartRenderer)renderer).mChart.getScatterData();
            else if (renderer instanceof com.github.testpress.mikephil.charting.renderer.BubbleChartRenderer)
                data = ((com.github.testpress.mikephil.charting.renderer.BubbleChartRenderer)renderer).mChart.getBubbleData();

            int dataIndex = data == null ? -1
                    : ((CombinedData)chart.getData()).getAllData().indexOf(data);

            mHighlightBuffer.clear();

            for (Highlight h : indices) {
                if (h.getDataIndex() == dataIndex || h.getDataIndex() == -1)
                    mHighlightBuffer.add(h);
            }

            renderer.drawHighlighted(c, mHighlightBuffer.toArray(new Highlight[mHighlightBuffer.size()]));
        }
    }

    /**
     * Returns the sub-renderer object at the specified index.
     *
     * @param index
     * @return
     */
    public com.github.testpress.mikephil.charting.renderer.DataRenderer getSubRenderer(int index) {
        if (index >= mRenderers.size() || index < 0)
            return null;
        else
            return mRenderers.get(index);
    }

    /**
     * Returns all sub-renderers.
     *
     * @return
     */
    public List<com.github.testpress.mikephil.charting.renderer.DataRenderer> getSubRenderers() {
        return mRenderers;
    }

    public void setSubRenderers(List<com.github.testpress.mikephil.charting.renderer.DataRenderer> renderers) {
        this.mRenderers = renderers;
    }
}
