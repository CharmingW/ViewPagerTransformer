package com.charmingwong.viewpagertransformer;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

/**
 * <pre>
 *     author: Charming Wong
 *     github: https://github.com/CharmingW
 *     blog  : http://www.jianshu.com/u/05686c7c92af & http://blog.csdn.net/CharmingWong
 *     公众号 ： Charming写字的地方
 *     time  : 17-9-29
 *     desc  : ViewPager动画转换器——page顺时针旋转进入和退出
 * </pre>
 */
public class RotateUpPageTransformer implements PageTransformer {

    private static final float DEFAULT_MAX_ROTATE_DELTA = 15f;

    private float mMaxRotate;

    public RotateUpPageTransformer() {
        this(DEFAULT_MAX_ROTATE_DELTA);
    }

    public RotateUpPageTransformer(float maxRotateDelta) {
        mMaxRotate = -maxRotateDelta;
    }

    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {
            // This page is way off-screen to the left.
            page.setPivotX(page.getWidth());
            page.setPivotY(0);
            page.setRotation(-mMaxRotate);
        } else if (position <= 1) {

            if (position < 0) {
                page.setPivotX(page.getWidth() * 0.5f * (1 - position));
                page.setPivotY(0);
                page.setRotation(mMaxRotate * position);
            } else {
                page.setPivotX(page.getWidth() * 0.5f * (1 - position));
                page.setPivotY(0);
                page.setRotation(mMaxRotate * position);
            }

        } else {
            // This page is way off-screen to the right.
            page.setPivotX(0);
            page.setPivotY(0);
            page.setRotation(mMaxRotate);
        }
    }
}
