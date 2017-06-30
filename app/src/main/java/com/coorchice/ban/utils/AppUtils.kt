package com.coorchice.ban.utils

import android.annotation.SuppressLint
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.telephony.TelephonyManager
import android.util.Base64
import android.view.View
import android.view.ViewConfiguration
import android.view.inputmethod.InputMethodManager
import com.coorchice.ban.BanApplication
import com.coorchice.ban.BuildConfig
import java.io.*
import java.net.Inet4Address
import java.net.NetworkInterface

/**
 * Project Name:AnimDveDemo
 * Author:IceChen
 * Date:16/8/24
 * Notes:
 */
object AppUtils {

    private var versionName: String = ""
    private val versionCode = 0
    private val deviceId: String? = null
    private val deviceMAC: String? = null

    @Throws(IOException::class)
    fun ObjectToBase64String(`object`: Any): String {
        val baos = ByteArrayOutputStream()
        val oos = ObjectOutputStream(baos)
        oos.writeObject(`object`)
        val base64String = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
        oos?.close()
        baos?.close()
        return base64String
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    fun <T> Base64StringToObject(base64String: String, clazz: Class<T>): T {
        val bytes = Base64.decode(base64String, Base64.DEFAULT)
        val bais = ByteArrayInputStream(bytes)
        val ois = ObjectInputStream(bais)
        val t = ois.readObject() as T
        ois?.close()
        bais?.close()
        return t
    }

    fun PackageIsExist(packageName: String): Boolean {
        val packageManager = BanApplication.appContext!!.packageManager
        try {
            return packageManager!!.getPackageInfo(packageName, 0) != null
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return false
    }

    fun showInputMethod(view: View) {
        try {
            val imm = view.context
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun hideInputMethod(view: View?) {
        if (view != null && view.context != null && view.windowToken != null) {
            try {
                (view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                        .hideSoftInputFromWindow(view.windowToken, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    /**
     * 判断当前屏幕是否锁屏.

     * @param context
     * *
     * @return boolean
     */
    fun inKeyguardRestrictedInputMode(context: Context): Boolean {
        val mKeyguardManager = context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        return mKeyguardManager.inKeyguardRestrictedInputMode()
    }

    /**
     * 屏幕是否是亮着的.

     * @param context
     * *
     * @return boolean
     */
    fun isScreenOn(context: Context): Boolean {
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return pm.isScreenOn
    }

    /**
     * 获取虚拟按键栏的高度

     * @param context
     * *
     * @return
     */
    var navigationBarHeight = 0

    fun getNavigationBarHeight(context: Context): Int {
        if (navigationBarHeight == 0) {
            if (hasNavBar(context)) {
                val res = context.resources
                val resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android")
                if (resourceId > 0) {
                    navigationBarHeight = res.getDimensionPixelSize(resourceId)
                }
            } else if (isMeizu) {
                navigationBarHeight = getSmartBarHeight(context)
            }
        }
        return navigationBarHeight
    }

    /**
     * 检查是否存在虚拟按键栏

     * @param context
     * *
     * @return
     */
    @SuppressLint("NewApi")
    private fun hasNavBar(context: Context): Boolean {
        val res = context.resources
        val resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android")
        if (resourceId != 0) {
            var hasNav = res.getBoolean(resourceId)
            // check override flag
            val sNavBarOverride = navBarOverride
            if ("1" == sNavBarOverride) {
                hasNav = false
            } else if ("0" == sNavBarOverride) {
                hasNav = true
            }
            return hasNav
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey()
        }
    }

    /**
     * 判断是否meizu手机

     * @return
     */
    val isMeizu: Boolean
        get() = Build.BRAND == "Meizu"

    /**
     * 获取魅族手机底部虚拟键盘高度

     * @param context
     * *
     * @return
     */
    fun getSmartBarHeight(context: Context): Int {
        try {
            val c = Class.forName("com.android.internal.R\$dimen")
            val obj = c.newInstance()
            val field = c.getField("mz_action_button_min_height")
            val height = Integer.parseInt(field.get(obj).toString())
            return context.resources.getDimensionPixelSize(height)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return 0
    }

    /**
     * 判断虚拟按键栏是否重写

     * @return
     */
    private val navBarOverride: String
        get() {
            var sNavBarOverride: String = ""
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                try {
                    val c = Class.forName("android.os.SystemProperties")
                    val m = c.getDeclaredMethod("get", String::class.java)
                    m.isAccessible = true
                    sNavBarOverride = m.invoke(null, "qemu.hw.mainkeys") as String
                } catch (e: Throwable) {
                }

            }
            return sNavBarOverride
        }

    /**获得手机Ip */
    val phoneIp: String
        get() {
            try {
                val en = NetworkInterface.getNetworkInterfaces()
                while (en
                        .hasMoreElements()) {
                    val intf = en.nextElement()
                    val enumIpAddr = intf.inetAddresses
                    while (enumIpAddr
                            .hasMoreElements()) {
                        val inetAddress = enumIpAddr.nextElement()
                        if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                            return inetAddress.getHostAddress().toString()
                        }
                    }
                }
            } catch (e: Exception) {
            }

            return ""
        }

    /**
     * 获得手机Imei
     */
    fun getImei(context: Context): String {
        try {
            val telephonyManager = context
                    .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return telephonyManager.deviceId
        } catch (e: Exception) {
            // In some devices, we are not able to get device id, and may cause some exception,
            // so catch it.
            return ""
        }

    }

    /**
     * 获得手机Imsi
     */
    fun getImsi(context: Context): String {
        try {
            val telephonyManager = context
                    .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return telephonyManager.subscriberId
        } catch (e: Exception) {
            // In some devices, we are not able to get device id, and may cause some exception,
            // so catch it.
            return ""
        }

    }

    fun getVersionName(context: Context): String {
        val packageInfo = getPackageInfo(context, context.packageName, 0)
        if (packageInfo != null) {
            versionName = packageInfo.versionName
        } else {
            versionName = ""
        }

        return versionName
    }

    fun getPackageInfo(context: Context, packageName: String, flag: Int): PackageInfo? {
        val packageManager = context.packageManager
        var packageInfo: PackageInfo? = null
        try {
            packageInfo = packageManager.getPackageInfo(packageName, flag)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: RuntimeException) {
            // In some ROM, there will be a PackageManager has died exception. So we catch it here.
            e.printStackTrace()
        }

        return packageInfo
    }

    val sdkVersion: Int
        get() = Build.VERSION.SDK_INT

    val channelName: String
        get() = BuildConfig.FLAVOR

    val modelVersion: String
        get() = Build.MODEL

    fun getResUri(resId: Int?): Uri? {
        return Uri.parse("res://" + BanApplication.appContext?.packageName + "/" + resId)
    }

    fun getResUriString(resId: Int): String {
        return "res://" + BanApplication.appContext?.packageName + "/" + resId
    }
}
