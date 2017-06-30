package com.coorchice.ban.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.util.Property
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import kotlinx.android.synthetic.main.fragment_constellation_detail_today.*

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
    }
    return set
}

fun ScaleAnimation(view: View?, scale: Float, duration: Long, interpolator: TimeInterpolator, repeat: Boolean): AnimatorSet {
    val set = AnimatorSet()
    if (view != null) {
        var scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, scale)
        var scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, scale)
        if (repeat) {
            scaleX.repeatCount = -1
            scaleX.repeatMode = ObjectAnimator.REVERSE
            scaleY.repeatCount = -1
            scaleY.repeatMode = ObjectAnimator.REVERSE
        }
        set.duration = duration
        set.interpolator = interpolator
        set.playTogether(scaleX, scaleY)
    }
    return set
}

fun playScaleAnimation(view: View?, scale: Float, duration: Long, interpolator: TimeInterpolator): AnimatorSet {
    val set = AnimatorSet()
    if (view != null) {
        var scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, scale)
        var scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, scale)
        set.duration = duration
        set.interpolator = interpolator
        set.playTogether(scaleX, scaleY)
        set.start()
    }
    return set
}

fun ScaleAnimation(view: View?, fromScale: Float, toScale: Float, duration: Long, interpolator: TimeInterpolator): AnimatorSet {
    val set = AnimatorSet()
    if (view != null) {
        var scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, fromScale, toScale)
        var scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, fromScale, toScale)
        set.duration = duration
        set.interpolator = interpolator
        set.playTogether(scaleX, scaleY)
    }
    return set
}


fun floatJumpAnim(view: View?): AnimatorSet {
    val set = AnimatorSet()
    if (view != null) {
        var scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 0.9f, 1.1f)
        scaleX.repeatMode = ObjectAnimator.REVERSE
        scaleX.repeatCount = -1
        var scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.9f, 1.1f)
        scaleY.repeatMode = ObjectAnimator.REVERSE
        scaleY.repeatCount = -1
        var alphaAnim = ObjectAnimator.ofFloat(view, View.ALPHA, 0.8f, 1f)
        alphaAnim.repeatMode = ObjectAnimator.REVERSE
        alphaAnim.repeatCount = -1
        set.duration = 2 * 1000
        set.interpolator = AccelerateDecelerateInterpolator()
        set.playTogether(scaleX, scaleY, alphaAnim)
        set.start()
    }
    return set
}

fun rotateAnim(view: View?, from: Float, to: Float, duration: Long, interpolator: TimeInterpolator, repeat: Boolean): AnimatorSet {
    val set = AnimatorSet()
    if (view != null) {
        var anim = ObjectAnimator.ofFloat(view, View.ROTATION, from, to)
        if (repeat) {
            anim.repeatMode = ObjectAnimator.RESTART
            anim.repeatCount = -1
        }
        set.duration = duration
        set.interpolator = interpolator
        set.playTogether(anim)
        set.start()
    }
    return set
}

fun alphaAnim(view: View?, from: Float, to: Float, duration: Long, interpolator: TimeInterpolator): AnimatorSet {
    val set = AnimatorSet()
    if (view != null) {
        var alphaAnim = ObjectAnimator.ofFloat(view, View.ALPHA, from, to)
        set.duration = duration
        set.interpolator = interpolator
        set.play(alphaAnim)
    }
    return set
}

fun translationYAnim(view: View?, from: Float, to: Float, duration: Long, interpolator: TimeInterpolator, repeat: Boolean): AnimatorSet {
    val set = AnimatorSet()
    if (view != null) {
        var translationAnim = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, from, to)
        if (repeat) {
            translationAnim.repeatMode = ObjectAnimator.REVERSE
            translationAnim.repeatCount = -1
        }
        set.duration = duration
        set.interpolator = interpolator
        set.play(translationAnim)
    }
    return set
}

fun playTranslationYAnim(view: View?, from: Float, to: Float, duration: Long, interpolator: TimeInterpolator, repeat: Boolean): AnimatorSet {
    var translationYAnim = translationYAnim(view, from, to, duration, interpolator, repeat)
    translationYAnim.start()
    return translationYAnim
}