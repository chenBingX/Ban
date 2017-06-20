package com.chenbing.oneweather.Utils

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter
import java.lang.Thread.UncaughtExceptionHandler
import java.text.SimpleDateFormat

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Process

/**
 * Project Name:IceWeather
 * Author:IceChen
 * Date:2016/11/9
 * Notes:
 */

class CrashHandler
/**
 * 防止被重复创建
 */
private constructor() : UncaughtExceptionHandler {
    private var mDefualtCrashHandler: UncaughtExceptionHandler? = null
    private var mContext: Context? = null

    fun init(context: Context) {
        mContext = context.applicationContext // 确保获得的是系统级的Context
        mDefualtCrashHandler = Thread.getDefaultUncaughtExceptionHandler() // 获取系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this) // 把当前实例设置为系统默认异常处理器
    }


    /**
     * 这个方法是我们重写的重点，当系统出现未捕获异常时，就会调用这个方法

     * @param t 出现未捕获异常的线程
     * *
     * @param e 未捕获的异常
     */
    override fun uncaughtException(t: Thread, e: Throwable) {
        try {
            saveExceptionToFile(e)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        if (mDefualtCrashHandler != null) {
            //如果系统有默认异常处理就使用它处理
            mDefualtCrashHandler!!.uncaughtException(t, e)
        } else {
            //否则我们自行结束程序
            Process.killProcess(Process.myPid())
        }


    }

    @Throws(IOException::class)
    private fun saveExceptionToFile(e: Throwable) {
        if (FileUtil.ExistSDCard()) {
            val currentTime = System.currentTimeMillis()
            val crashTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime)
            val file = File(FileUtil.appCrashDir + "crash" + crashTime + ".txt")
            file.createNewFile()
            try {
                val pw = PrintWriter(BufferedWriter(FileWriter(file)))
                pw.println(crashTime)
                printPhoneInfo(pw)
                pw.println()
                e.printStackTrace(pw) //输出错误信息
                pw.close()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }
    }

    /**
     * 输出手机信息
     */
    @Throws(PackageManager.NameNotFoundException::class)
    private fun printPhoneInfo(pw: PrintWriter) {
        val pm = mContext!!.packageManager
        val pi = pm.getPackageInfo(mContext!!.packageName, PackageManager.GET_ACTIVITIES)
        pw.print("App version: ")
        pw.print(pi.versionName)
        pw.print("_")
        pw.println(pi.versionCode)

        //android版本号
        pw.print("OS Version: ")
        pw.print(Build.VERSION.RELEASE)
        pw.print("_")
        pw.println(Build.VERSION.SDK_INT)

        //制造商
        pw.print("Vendor: ")
        pw.println(Build.MANUFACTURER)

        //手机型号
        pw.print("Model: ")
        pw.println(Build.MODEL)

        //cpu架构
        pw.print("CPU ABI: ")
        pw.println(Build.CPU_ABI)
    }

    companion object {

        val instance = CrashHandler()
    }
}