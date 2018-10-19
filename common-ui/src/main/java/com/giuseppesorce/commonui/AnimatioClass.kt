package com.giuseppesorce.commonui

import android.animation.Animator
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.View
import android.widget.ImageView

/**
 * @author Giuseppe Sorce
 */

class AnimatioClass {

    internal var mButon: View? = null

    init {


        mButon!!.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .translationZ(10f)
                .setInterpolator(FastOutSlowInInterpolator())
                .setStartDelay(200)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {}

                    override fun onAnimationEnd(animation: Animator) {}

                    override fun onAnimationCancel(animation: Animator) {}

                    override fun onAnimationRepeat(animation: Animator) {}
                })
                .start()
    }
}
