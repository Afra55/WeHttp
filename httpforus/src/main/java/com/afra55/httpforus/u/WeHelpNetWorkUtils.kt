package com.afra55.httpforus.u

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.text.TextUtils
import com.afra55.httpforus.WeHelp
import java.net.NetworkInterface
import java.util.*

/**
 * @author Afra55
 * @date 2019-11-29
 * A smile is the best business card.
 */
object WeHelpNetWorkUtils {

    fun isOnline(): Boolean {
        val connMgr = WeHelp.context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr?.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

    fun isVpn(): Boolean {
        try {
            val v01 = NetworkInterface.getNetworkInterfaces() ?: return false
            val v02 = Collections.list(v01).iterator()
            do {
                if (!v02.hasNext()) {
                    return false
                }
                val next = v02.next()
                if (!next.isUp) {
                    continue
                }
                if (next.interfaceAddresses.size == 0) {
                    continue
                }
                if (!TextUtils.equals("tun0", next.name) && !TextUtils.equals("ppp0", next.name)) {
                    continue
                }
                return true
            } while (true)
        } catch (e: Exception) {
        }
        return false
    }

    /**
     * 判断是否是wifi连接
     */
    fun isWifi() :Boolean{
        val cm = WeHelp.context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            ?: return false
        return cm.activeNetworkInfo?.type == ConnectivityManager.TYPE_WIFI
    }
}