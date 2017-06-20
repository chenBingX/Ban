package com.coorchice.ban

import android.app.Application
import android.content.Context
import com.chenbing.oneweather.Utils.CrashHandler
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Project Name:Ban
 * Author:CoorChice
 * Date:2017/6/10
 * Notes:
 */
class BanApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
        CrashHandler.instance.init(appContext as BanApplication)
        Fresco.initialize(this)


    }

    companion object {
        var appContext: Context? = null
    }
}