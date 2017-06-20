package com.chenbing.oneweather.Utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager

/**
 * Network utility functions.
 */
object NetworkUtils {
    private val NETWORK_TYPE_UNAVAILABLE = -1
    private val NETWORK_TYPE_MOBILE = -100
    private val NETWORK_TYPE_WIFI = -101

    private val NETWORK_CLASS_WIFI = -101
    private val NETWORK_CLASS_UNAVAILABLE = -1
    /** Unknown network class.  */
    private val NETWORK_CLASS_UNKNOWN = 0
    /** Class of broadly defined "2G" networks.  */
    private val NETWORK_CLASS_2_G = 1
    /** Class of broadly defined "3G" networks.  */
    private val NETWORK_CLASS_3_G = 2
    /** Class of broadly defined "4G" networks.  */
    private val NETWORK_CLASS_4_G = 3

    // 适配低版本手机
    /** Network type is unknown  */
    val NETWORK_TYPE_UNKNOWN = 0
    /** Current network is GPRS  */
    val NETWORK_TYPE_GPRS = 1
    /** Current network is EDGE  */
    val NETWORK_TYPE_EDGE = 2
    /** Current network is UMTS  */
    val NETWORK_TYPE_UMTS = 3
    /** Current network is CDMA: Either IS95A or IS95B  */
    val NETWORK_TYPE_CDMA = 4
    /** Current network is EVDO revision 0  */
    val NETWORK_TYPE_EVDO_0 = 5
    /** Current network is EVDO revision A  */
    val NETWORK_TYPE_EVDO_A = 6
    /** Current network is 1xRTT  */
    val NETWORK_TYPE_1xRTT = 7
    /** Current network is HSDPA  */
    val NETWORK_TYPE_HSDPA = 8
    /** Current network is HSUPA  */
    val NETWORK_TYPE_HSUPA = 9
    /** Current network is HSPA  */
    val NETWORK_TYPE_HSPA = 10
    /** Current network is iDen  */
    val NETWORK_TYPE_IDEN = 11
    /** Current network is EVDO revision B  */
    val NETWORK_TYPE_EVDO_B = 12
    /** Current network is LTE  */
    val NETWORK_TYPE_LTE = 13
    /** Current network is eHRPD  */
    val NETWORK_TYPE_EHRPD = 14
    /** Current network is HSPA+  */
    val NETWORK_TYPE_HSPAP = 15
    /** Current network is GSM {@hide}  */
    val NETWORK_TYPE_GSM = 16
    /** Current network is TD_SCDMA {@hide}  */
    val NETWORK_TYPE_TD_SCDMA = 17

    fun isNetworkConnected(context: Context): Boolean {
        val connManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        try {
            activeNetworkInfo = connManager.activeNetworkInfo
        } catch (e: Exception) {
            // in some roms, here maybe throw a exception(like nullpoint).
            e.printStackTrace()
        }

        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun getActiveNetworkInfo(context: Context): NetworkInfo {
        val connManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        try {
            activeNetworkInfo = connManager.activeNetworkInfo
        } catch (e: Exception) {
            // in some roms, here maybe throw a exception(like nullpoint).
            e.printStackTrace()
        }

        return activeNetworkInfo!!
    }

    fun isMobileNetworkConnected(context: Context): Boolean {
        val connManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo: NetworkInfo? = null
        try {
            // maybe throw exception in android framework
            networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return networkInfo != null && networkInfo.isConnected
    }

    fun isWifiConnected(context: Context): Boolean {
        val connManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo: NetworkInfo? = null
        try {
            // maybe throw exception in android framework
            networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return networkInfo != null && networkInfo.isConnected
    }

    @SuppressLint("MissingPermission")
    fun isWifiClosed(context: Context): Boolean {
        @SuppressLint("WifiManagerPotentialLeak")
        val wm = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return wm.wifiState == WifiManager.WIFI_STATE_DISABLED
    }

    /**
     * 获取网络类型

     * @return
     */
    fun getCurrentNetworkType(context: Context): String {
        val networkClass = getNetworkClass(context)
        var type = "Unknown"
        when (networkClass) {
            NETWORK_CLASS_UNAVAILABLE -> type = "unavailable"
            NETWORK_CLASS_WIFI -> type = "Wi-Fi"
            NETWORK_CLASS_2_G -> type = "2G"
            NETWORK_CLASS_3_G -> type = "3G"
            NETWORK_CLASS_4_G -> type = "4G"
            NETWORK_CLASS_UNKNOWN -> type = "unknown"
        }
        return type
    }

    private fun getNetworkClassByType(networkType: Int): Int {
        when (networkType) {
            NETWORK_TYPE_UNAVAILABLE -> return NETWORK_CLASS_UNAVAILABLE
            NETWORK_TYPE_WIFI -> return NETWORK_CLASS_WIFI
            NETWORK_TYPE_GPRS, NETWORK_TYPE_EDGE, NETWORK_TYPE_CDMA, NETWORK_TYPE_1xRTT, NETWORK_TYPE_IDEN -> return NETWORK_CLASS_2_G
            NETWORK_TYPE_UMTS, NETWORK_TYPE_EVDO_0, NETWORK_TYPE_EVDO_A, NETWORK_TYPE_HSDPA, NETWORK_TYPE_HSUPA, NETWORK_TYPE_HSPA, NETWORK_TYPE_EVDO_B, NETWORK_TYPE_EHRPD, NETWORK_TYPE_HSPAP, NETWORK_TYPE_GSM, NETWORK_TYPE_TD_SCDMA -> return NETWORK_CLASS_3_G
            NETWORK_TYPE_LTE -> return NETWORK_CLASS_4_G
            else -> return NETWORK_CLASS_UNKNOWN
        }
    }

    private fun getNetworkClass(context: Context): Int {
        var networkType = NETWORK_TYPE_UNKNOWN
        try {
            val network = (context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                    .activeNetworkInfo
            if (network != null && network.isAvailable
                    && network.isConnected) {
                val type = network.type
                if (type == ConnectivityManager.TYPE_WIFI) {
                    networkType = NETWORK_TYPE_WIFI
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    val telephonyManager = context.getSystemService(
                            Context.TELEPHONY_SERVICE) as TelephonyManager
                    networkType = telephonyManager.networkType
                }
            } else {
                networkType = NETWORK_TYPE_UNAVAILABLE
            }

        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return getNetworkClassByType(networkType)

    }
}
