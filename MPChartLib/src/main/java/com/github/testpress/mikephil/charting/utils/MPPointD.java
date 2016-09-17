
package com.github.testpress.mikephil.charting.utils;

import java.util.List;

/**
 * Point encapsulating two double values.
 *
 * @author Philipp Jahoda
 */
public class MPPointD extends com.github.testpress.mikephil.charting.utils.ObjectPool.Poolable {

    private static com.github.testpress.mikephil.charting.utils.ObjectPool<MPPointD> pool;

    static {
        pool = com.github.testpress.mikephil.charting.utils.ObjectPool.create(64, new MPPointD(0,0));
        pool.setReplenishPercentage(0.5f);
    }

    public static MPPointD getInstance(double x, double y){
        MPPointD result = pool.get();
        result.x = x;
        result.y = y;
        return result;
    }

    public static void recycleInstance(MPPointD instance){
        pool.recycle(instance);
    }

    public static void recycleInstances(List<MPPointD> instances){
        pool.recycle(instances);
    }

    public double x;
    public double y;

    protected com.github.testpress.mikephil.charting.utils.ObjectPool.Poolable instantiate(){
        return new MPPointD(0,0);
    }

    private MPPointD(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * returns a string representation of the object
     */
    public String toString() {
        return "MPPointD, x: " + x + ", y: " + y;
    }
}