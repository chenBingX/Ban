package com.coorchice.ban.model.constellation.entry

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/26
 * Notes:
 */
enum class ConstellationDataType(val type: String, val title: String) {
    /**
     * today,tomorrow,week,nextweek,month,year
     */
    TODAY("today", "今日运势"),
    TOMORROW("tomorrow", "明日运势"),
    WEEK("week", "本周运势"),
    NEXTWEEK("nextweek", "下周运势"),
    MONTH("month", "本月运势"),
    YEAR("year", "今年运势")
}