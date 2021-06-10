package com.ant.main.utils;

import android.content.Context;
import android.view.ViewConfiguration;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author: zfhuang
 * @date: 2021/6/3
 */
public class FlingHelper {

    private static final double DECELERATION_RATE = (Math.log(0.78d) / Math.log(0.9d));
    private float mFlingFriction = ViewConfiguration.getScrollFriction();
    private final float mPhysicalCoeff;

    public FlingHelper(Context context) {
        mPhysicalCoeff =
                context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
    }

    private double getSplineDeceleration(int i) {
        return Math.log(0.35f * ((float) Math.abs(i)) / (mFlingFriction * mPhysicalCoeff));
    }

    private double getSplineDecelerationByDistance(double d) {
        return (DECELERATION_RATE - 1.0d) * Math.log(d / ((double) mFlingFriction * mPhysicalCoeff));
    }

    public double getSplineFlingDistance(int i) {
        return Math.exp(getSplineDeceleration(i) * DECELERATION_RATE / (DECELERATION_RATE - 1.0d))*((double)(mFlingFriction*mPhysicalCoeff));
    }

    public int getVelocityByDistance(double d) {
        return Math.abs((int)((Math.exp(getSplineDecelerationByDistance(d) * ((double) mFlingFriction)) * ((double) mPhysicalCoeff)) / 0.3499999940395355d));
    }
}