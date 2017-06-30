package com.coorchice.ban.utils

import android.content.Context
import android.content.SharedPreferences
import com.coorchice.ban.BanApplication
import com.coorchice.ban.model.constellation.entry.Constellation

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/29
 * Notes:
 */
object SpCacher {

    val SP_NAME = "sp_cache"
    val KEY_CONSTELLATION = "key_constellation"

    private var sp: SharedPreferences? = null

    init {
        if (sp == null) {
            sp = BanApplication.appContext?.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
    }

    fun saveConstellation(data: Constellation) {
        val editor = sp?.edit()
        editor?.putString(KEY_CONSTELLATION, data.name)
        editor?.apply()
    }

    fun getConstellation(): Constellation? {
        var constellation = sp?.getString(KEY_CONSTELLATION, null)
        if (constellation != null) {
            return Constellation.valueOf(constellation)
        } else {
            return null
        }
    }


}