package com.coorchice.ban.model.constellation.entry

import android.graphics.Color
import com.coorchice.ban.R

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/26
 * Notes:
 */

enum class Constellation(val type: String, val date: String, val color: Int, val icon: Int) {
    /**
     *
     * 白羊座 (3/21 - 4/20) Aries
    金牛座 (4/21 - 5/20) Taurus
    双子座 (5/21 - 6/21) Gemini
    巨蟹座 (6/22 - 7/22) Cancer
    狮子座 (7/23 - 8/22) Leo
    处女座 (8/23 - 9/22) Virgo
    天秤座 (9/23 - 10/22) Libra
    天蝎座 (10/23 - 11/21) Scorpio
    射手座 (11/22 - 12/21) Sagittarius
    魔羯座 (12/22 - 1/19) Capricorn
    水瓶座 (1/20 - 2/18) Aquarius
    双鱼座 (2/19 - 3/20) Pisces
     */

    ARIES("白羊座", "3.21 - 4.20", Color.parseColor("#75AD15"), R.drawable.aries),
    TAURUS("金牛座", "4.21 - 5.20", Color.parseColor("#87622F"), R.drawable.taurus),
    GEMINI("双子座", "5.21 - 6.21", Color.parseColor("#DD2210"), R.drawable.gemini),
    CANCER("巨蟹座", "6.22 - 7.22", Color.parseColor("#F4C119"), R.drawable.cancer),
    LEO("狮子座", "7.23 - 8.22", Color.parseColor("#622B19"), R.drawable.lion),
    VIRGO("处女座", "8.23 - 9.22", Color.parseColor("#945398"), R.drawable.virgo),
    LIBRA("天秤座", "9.23 - 10.22", Color.parseColor("#2DAFA0"), R.drawable.libra),
    SCORPIO("天蝎座", "10.23 - 11.21", Color.parseColor("#525390"), R.drawable.scorpio),
    SAGITTARIUS("射手座", "11.22 - 12.21", Color.parseColor("#909090"), R.drawable.sagittarius),
    CAPRICORN("魔羯座", "12.22 - 1.19", Color.parseColor("#31190D"), R.drawable.capricorn),
    AQUARIUS("水瓶座", "1.20 - 2.18", Color.parseColor("#2898DE"), R.drawable.aquarius),
    PISCES("双鱼座", "2.19 - 3.20", Color.parseColor("#D87B9A"), R.drawable.pisces);

}