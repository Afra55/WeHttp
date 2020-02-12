package com.afra55.httpforus.u

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.text.TextUtils
import com.afra55.httpforus.WeHelp
import java.net.NetworkInterface
import java.util.*


/**
 * @author Afra55
 * @date 2020-02-10
 * A smile is the best business card.
 */
object WeHelpPackageUtils {


    /**
     * 获取版本名称
     *
     * @param context 上下文
     *
     * @return 版本名称
     */
    @JvmStatic
    fun getVersionName(context: Context): String? {

        //获取包管理器
        val pm = context.packageManager
        //获取包信息
        try {
            val packageInfo = pm.getPackageInfo(context.packageName, 0)
            //返回版本号
            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return null

    }

    /**
     * 获取版本号
     *
     * @param context 上下文
     *
     * @return 版本号
     */
    @JvmStatic
    fun getVersionCode(context: Context): Int {

        //获取包管理器
        val pm = context.packageManager
        //获取包信息
        try {
            val packageInfo = pm.getPackageInfo(context.packageName, 0)
            //返回版本号
            return packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return 0

    }

    /**
     * 获取App的名称
     *
     * @param context 上下文
     *
     * @return 名称
     */
    @JvmStatic
    fun getAppName(context: Context): String? {
        val pm = context.packageManager
        //获取包信息
        try {
            val packageInfo = pm.getPackageInfo(context.getPackageName(), 0)
            //获取应用 信息
            val applicationInfo = packageInfo.applicationInfo
            //获取albelRes
            val labelRes = applicationInfo.labelRes
            //返回App的名称
            return context.getResources().getString(labelRes)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return null
    }
}