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
 *     desc  : ViewPager动画转换器——page进入和退出伴随透明度和缩放比例改变
 * </pre>
 */
public class AlphaScalePageTransformer implements ViewPager.PageTransformer {

    private static final float DEFAULT_MIN_SCALE = 0.70f;
    private static final float DEFAULT_MIN_ALPHA = 0.5f;

    private float mMinScale;
    private float mMinAlpha;

    public AlphaScalePageTransformer() {
        this(DEFAULT_MIN_SCALE, DEFAULT_MIN_ALPHA);
    }

    /**
     *
     * @param minScale 最小的缩放比例
     * @param minAlpha 最小的透明度
     */
    public AlphaScalePageTransformer(float minScale, float minAlpha) {
        mMinScale = minScale;
        mMinAlpha = minAlpha;
    }

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(mMinAlpha);
            view.setScaleX(mMinScale);
            view.setScaleY(mMinScale);
        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(mMinScale, 1 - Math.abs(position));
            float verticalMargin = pageHeight * (1 - scaleFactor) / 2;
            float horizontalMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                view.setTranslationX(horizontalMargin - verticalMargin / 2);
                view.setScaleX(1 + 0.3f * position);
                view.setScaleY(1 + 0.3f * position);
            } else {
                view.setTranslationX(-horizontalMargin + verticalMargin / 2);

                view.setScaleX(1 - 0.3f * position);
                view.setScaleY(1 - 0.3f * position);
            }

            // Scale the page down (between DEFAULT_MIN_SCALE and 1)

            // Fade the page relative to its size.
            view.setAlpha(mMinAlpha
                + (scaleFactor - mMinScale) / (1 - mMinScale) * (1 - mMinAlpha));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setScaleX(mMinScale);
            view.setScaleY(mMinScale);
            view.setAlpha(mMinAlpha);
        }
    }
}