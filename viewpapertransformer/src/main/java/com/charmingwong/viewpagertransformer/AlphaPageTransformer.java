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
 *     desc  : ViewPager动画转换器——page进入和退出伴随透明度改变
 * </pre>
 */
public class AlphaPageTransformer implements ViewPager.PageTransformer {

    private static final float DEFAULT_MIN_ALPHA = 0.5f;

    private float mMinAlpha;

    public AlphaPageTransformer() {
        this(DEFAULT_MIN_ALPHA);
    }

    /**
     *
     * @param minAlpha 最小的透明度
     */
    public AlphaPageTransformer(float minAlpha) {
        mMinAlpha = minAlpha;
    }

    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {
            // This page is way off-screen to the left.
            page.setAlpha(mMinAlpha);
        } else if (position <= 1) {

            if (position < 0) {
                page.setAlpha(mMinAlpha + (1 - mMinAlpha) * (1 + position));
            } else {
                page.setAlpha(mMinAlpha + (1 - mMinAlpha) * (1 - position));
            }

        } else {
            // This page is way off-screen to the right.
            page.setAlpha(mMinAlpha);
        }
    }

}
