package com.coorchice.ban.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/27
 * Notes:
 */
fun getWeekOfDate(date: Date): String {
    val weekDays = arrayOf("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
    val cal = Calendar.getInstance()
    cal.time = date
    var w = cal.get(Calendar.DAY_OF_WEEK) - 1
    if (w < 0) w = 0
    return weekDays[w]
}

fun getWeekOfCurrent(): String {
    return getWeekOfDate(Date())
}

fun formatDate(date: Date, format: String): String {
    return SimpleDateFormat(format).format(date)
}

fun formatCurrentDate(format: String): String {
    return SimpleDateFormat(format).format(Date())
}


