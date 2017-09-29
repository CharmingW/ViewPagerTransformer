package com.charmingwong.viewpagertransformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * <pre>
 *     author: Charming Wong
 *     github: https://github.com/CharmingW
 *     blog  : http://www.jianshu.com/u/05686c7c92af & http://blog.csdn.net/CharmingWong
 *     公众号 ： Charming写字的地方
 *     time  : 17-9-29
 *     desc  : ViewPager动画转换器——page进入和退出伴随缩放比例改变
 * </pre>
 */
public class ScalePageTransformer implements ViewPager.PageTransformer {

    private static final float DEFAULT_MIN_SCALE = 0.8f;

    private static final float DEFAULT_PIVOT = 0.5f;

    private float mMinScale;

    private float mPivot;

    public ScalePageTransformer() {
        this(DEFAULT_MIN_SCALE, DEFAULT_PIVOT);
    }

    /**
     *
     * @param minScale page切换最小的缩放比例
     * @param pivot    缩放中心点位置，0.5f代表按page中心缩放
     */
    public ScalePageTransformer(float minScale, float pivot) {
        mMinScale = minScale;
        mPivot = pivot;
    }

    @Override
    public void transformPage(View page, float position) {
        page.setPivotX(page.getWidth() * mPivot);
        page.setPivotY(page.getHeight() * mPivot);
        if (position < -1) {
            // This page is way off-screen to the left.
            page.setScaleX(mMinScale);
            page.setScaleX(mMinScale);
        } else if (position <= 1) {

            if (position < 0) {
                page.setScaleX(mMinScale + (1 - mMinScale) * (1 + position));
                page.setScaleY(mMinScale + (1 - mMinScale) * (1 + position));
            } else {
                page.setScaleX(mMinScale + (1 - mMinScale) * (1 - position));
                page.setScaleY(mMinScale + (1 - mMinScale) * (1 - position));
            }

        } else {
            // This page is way off-screen to the right.
            page.setScaleX(mMinScale);
            page.setScaleY(mMinScale);
        }
    }
}
