package com.coorchice.ban.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/7/3
 * Notes:
 */
fun View.setLocation(newX: Float, newY: Float) {
    val set = AnimatorSet()
    val xAnim = ObjectAnimator.ofFloat(this, View.X, x, newX)
    val yAnim = ObjectAnimator.ofFloat(this, View.Y, y, newY)
    set.playTogether(xAnim, yAnim)
    set.duration = 0
    set.start()
}

fun View.setLocationSmooth(newX: Float, newY: Float, smoothDuration: Long) {
    val set = AnimatorSet()
    val xAnim = ObjectAnimator.ofFloat(this, View.X, x, newX)
    val yAnim = ObjectAnimator.ofFloat(this, View.Y, y, newY)
    set.playTogether(xAnim, yAnim)
    set.duration = smoothDuration
    set.start()
}

fun View.offsetXY(offsetX: Float, offsetY: Float) {
    setLocation(x + offsetX, y + offsetY)
}

fun View.offsetXYSmooth(offsetX: Float, offsetY: Float, smoothDuration: Long) {
    setLocationSmooth(x + offsetX, y + offsetY, smoothDuration)
}

val View.locationX: Float
    get() {
        val location = IntArray(2)
        getLocationInWindow(location)
        return location[0].toFloat()
    }

val View.locationY: Float
    get() {
        val location = IntArray(2)
        getLocationInWindow(location)
        return location[1].toFloat()
    }

