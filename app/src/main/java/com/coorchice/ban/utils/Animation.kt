package com.coorchice.ban.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/12
 * Notes:
 */

fun Pop(view: View) {
    val animatorSet = AnimatorSet()
    animatorSet.playSequentially(listOf(
            ScaleAnimation(view, 1.1f, 50, AccelerateDecelerateInterpolator()),
            ScaleAnimation(view, 1f, 50, AccelerateDecelerateInterpolator()),
            ScaleAnimation(view, 1.2f, 70, AccelerateDecelerateInterpolator()),
            ScaleAnimation(view, 0.85f, 50, AccelerateDecelerateInterpolator()),
            ScaleAnimation(view, 1f, 50, AccelerateDecelerateInterpolator())))
    animatorSet.start()
}

fun ScaleAnimation(view: View?, scale: Float, duration: Long, interpolator: TimeInterpolator): AnimatorSet {
    val set = AnimatorSet()
    if (view != null){
        var scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, scale)
        var scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, scale)
        set.duration = duration
        set.interpolator = interpolator
        set.playTogether(scaleX, scaleY)
        set.start()
    }
    return set
}
