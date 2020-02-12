package com.afra55.httpforus.u.sp

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.afra55.httpforus.WeHelp
import com.afra55.httpforus.u.WeHelpJsonUtils
import java.lang.reflect.Type

/**
 * @author Afra55
 * @date 2019-06-25
 * A smile is the best business card.
 */
open class WeHelpBaseSp(val name: String = "WeHelp") {


    private var sp: SharedPreferences? = WeHelp.context?.getSharedPreferences(name, Context.MODE_PRIVATE)

    fun setValue(key: String, any: Any) {
        sp?.let {
            val edit = it.edit()
            when (any) {
                is String -> edit.putString(key, any)
                is Boolean -> edit.putBoolean(key, any)
                is Int -> edit.putInt(key, any)
                is Long -> edit.putLong(key, any)
                is Float -> edit.putFloat(key, any)
                else -> {
                    val json = WeHelpJsonUtils.toJsonString(any)
                    edit.putString(key, json)
                }
            }
            edit.apply()
        }
    }

    fun getBooleanValue(key: String, default: Boolean): Boolean {
        return sp?.getBoolean(key, default) ?: default
    }

    fun getStringValue(key: String, default: String? = ""): String? {
        return sp?.getString(key, default) ?: default
    }

    fun getIntValue(key: String, default: Int): Int {
        return sp?.getInt(key, default) ?: default
    }

    fun getLongValue(key: String, default: Long): Long {
        return sp?.getLong(key, default) ?: default
    }

    fun getFloatValue(key: String, default: Float): Float {
        return sp?.getFloat(key, default) ?: default
    }

    fun <T> getAny(key: String, clazz: Type): T?  {
        val json = getStringValue(key)
        if (TextUtils.isEmpty(json)) {
            return null
        }
        return WeHelpJsonUtils.parseObject<T>(json!!, clazz)
    }

    fun clearAll() {
        sp?.edit()?.clear()?.apply()
    }
}