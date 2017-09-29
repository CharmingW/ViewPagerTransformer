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
 *     desc  : ViewPager动画转换器——page逆时针旋转进入和退出
 * </pre>
 */
public class RotateDownPageTransformer implements ViewPager.PageTransformer {

    private static final float DEFAULT_MAX_ROTATE_DELTA = 15.0f;

    private float mMaxRotate;

    public RotateDownPageTransformer() {
        this(DEFAULT_MAX_ROTATE_DELTA);
    }

    public RotateDownPageTransformer(float maxRotate) {
        mMaxRotate = maxRotate;
    }

    @Override
    public void transformPage(View view, float position) {

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setPivotX(view.getWidth());
            view.setPivotY(view.getHeight());
            view.setRotation(-mMaxRotate);
        } else if (position <= 1) { // [-1,1]

            if (position < 0) {
                view.setPivotX(view.getWidth() * 0.5f * (1 - position));
                view.setPivotY(view.getHeight());
                view.setRotation(mMaxRotate * position);
            } else {
                view.setPivotX(view.getWidth() * 0.5f * (1 - position));
                view.setPivotY(view.getHeight());
                view.setRotation(mMaxRotate * position);
            }
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setPivotX(0);
            view.setPivotY(view.getHeight());
            view.setRotation(mMaxRotate);
        }
    }
}
