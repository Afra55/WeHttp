package com.afra55.httpforus

import android.annotation.SuppressLint
import android.content.Context

/**
 * @author Afra55
 * @date 2020-02-12
 * A smile is the best business card.
 */
@SuppressLint("StaticFieldLeak")
object WeHelp {

    var context:Context? = null
    var isDebugMode:Boolean = false

    /**
     * 在使用 WeHelp 工具之前一定要初始化
     */
    fun init(context: Context, isDebugMode:Boolean) {
        this.context = context.applicationContext
        this.isDebugMode = isDebugMode
    }

    /**
     * 在应用结束的时候释放 context
     */
    fun destroy() {
        context = null
    }
}