
package com.github.testpress.mikephil.charting.formatter;

import com.github.testpress.mikephil.charting.components.AxisBase;
import com.github.testpress.mikephil.charting.data.Entry;
import com.github.testpress.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * This ValueFormatter is just for convenience and simply puts a "%" sign after
 * each value. (Recommeded for PieChart)
 *
 * @author Philipp Jahoda
 */
public class PercentFormatter implements ValueFormatter, com.github.testpress.mikephil.charting.formatter.AxisValueFormatter {

    protected com.github.testpress.mikephil.charting.formatter.FormattedStringCache.Generic<Integer, Float> mFormattedStringCache;
    protected com.github.testpress.mikephil.charting.formatter.FormattedStringCache.PrimFloat mFormattedStringCacheAxis;

    public PercentFormatter() {
        mFormattedStringCache = new com.github.testpress.mikephil.charting.formatter.FormattedStringCache.Generic<>(new DecimalFormat("###,###,##0.0"));
        mFormattedStringCacheAxis = new com.github.testpress.mikephil.charting.formatter.FormattedStringCache.PrimFloat(new DecimalFormat("###,###,##0.0"));
    }

    /**
     * Allow a custom decimalformat
     *
     * @param format
     */
    public PercentFormatter(DecimalFormat format) {
        mFormattedStringCache = new com.github.testpress.mikephil.charting.formatter.FormattedStringCache.Generic<>(format);
        mFormattedStringCacheAxis = new com.github.testpress.mikephil.charting.formatter.FormattedStringCache.PrimFloat(format);
    }

    // ValueFormatter
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mFormattedStringCache.getFormattedValue(value, dataSetIndex) + " %";
    }

    // AxisValueFormatter
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        // TODO: Find a better way to do this.  Float isn't the best key...
        return mFormattedStringCacheAxis.getFormattedValue(value) + " %";
    }

    @Override
    public int getDecimalDigits() {
        return 1;
    }
}
