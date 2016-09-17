package com.github.testpress.mikephil.charting.highlight;

import com.github.testpress.mikephil.charting.data.BarData;
import com.github.testpress.mikephil.charting.data.DataSet;
import com.github.testpress.mikephil.charting.data.Entry;
import com.github.testpress.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.testpress.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.testpress.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.testpress.mikephil.charting.utils.MPPointD;

/**
 * Created by Philipp Jahoda on 22/07/15.
 */
public class HorizontalBarHighlighter extends BarHighlighter {

	public HorizontalBarHighlighter(BarDataProvider chart) {
		super(chart);
	}

	@Override
	public com.github.testpress.mikephil.charting.highlight.Highlight getHighlight(float x, float y) {

		BarData barData = mChart.getBarData();

		MPPointD pos = getValsForTouch(y, x);

		com.github.testpress.mikephil.charting.highlight.Highlight high = getHighlightForX((float) pos.y, y, x);
		if (high == null)
			return null;

		IBarDataSet set = barData.getDataSetByIndex(high.getDataSetIndex());
		if (set.isStacked()) {

			return getStackedHighlight(high,
					set,
					(float) pos.y,
					(float) pos.x);
		}

		MPPointD.recycleInstance(pos);

		return high;
	}

	@Override
	protected com.github.testpress.mikephil.charting.highlight.Highlight buildHighlight(IDataSet set, int dataSetIndex, float xVal, DataSet.Rounding rounding) {

		final Entry e = set.getEntryForXPos(xVal, rounding);

		MPPointD pixels = mChart.getTransformer(set.getAxisDependency()).getPixelsForValues(e.getY(), e.getX());

		return new com.github.testpress.mikephil.charting.highlight.Highlight(e.getX(), e.getY(), (float) pixels.x, (float) pixels.y, dataSetIndex, set.getAxisDependency());
	}

	@Override
	protected float getDistance(float x1, float y1, float x2, float y2) {
		return Math.abs(y1 - y2);
	}
}
