package com.coorchice.ban.utils

import android.util.Log
import com.coorchice.ban.BuildConfig

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/10
 * Notes:
 */
val allow = BuildConfig.DEBUG

private fun getLogPrefix(): String {
    val stackTraceElement = Thread.currentThread().stackTrace[4]
    var className = stackTraceElement.className
    className = className.substring(className.lastIndexOf(".") + 1)
    return "$className.${stackTraceElement.methodName}(L:${stackTraceElement.lineNumber})"
}

fun logv(content: String) {
    if (allow) Log.v(getLogPrefix(), content)
}

fun logi(content: String) {
    if (allow) Log.i(getLogPrefix(), content)
}

fun logw(content: String) {
    if (allow) Log.w(getLogPrefix(), content)
}

fun loge(content: String) {
    if (allow) Log.e(getLogPrefix(), content)
}