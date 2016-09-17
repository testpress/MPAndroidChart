package com.github.testpress.mikephil.charting.interfaces.datasets;

import com.github.testpress.mikephil.charting.data.Entry;
import com.github.testpress.mikephil.charting.renderer.scatter.ShapeRenderer;

/**
 * Created by philipp on 21/10/15.
 */
public interface IScatterDataSet extends ILineScatterCandleRadarDataSet<Entry> {

    /**
     * Returns the currently set scatter shape size
     *
     * @return
     */
    float getScatterShapeSize();

    /**
     * Returns radius of the hole in the shape
     *
     * @return
     */
    float getScatterShapeHoleRadius();

    /**
     * Returns the color for the hole in the shape
     *
     * @return
     */
    int getScatterShapeHoleColor();

    /**
     * Returns the ShapeRenderer responsible for rendering this DataSet.
     *
     * @return
     */
    ShapeRenderer getShapeRenderer();
}
