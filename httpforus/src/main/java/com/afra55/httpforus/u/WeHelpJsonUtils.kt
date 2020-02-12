package com.afra55.httpforus.u

import com.afra55.httpforus.WeHelpLog
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * @author Afra55
 * @date 2019-06-19
 * A smile is the best business card.
 */
object WeHelpJsonUtils {

    @JvmStatic
    fun createJSONObject() : JSONObject{
        return JSONObject()
    }

    @JvmStatic
    fun getJSONObject(str: String): JSONObject? {
        val obj = try {
            JSON.parseObject(str)
        } catch (e: Exception) {
            WeHelpLog.e(str, e)
            null
        }

        return obj
    }

    @JvmStatic
    fun <T> parseObject(str: String, clazz: Type): T? {
        try {

            try {
                return JSON.parseObject(str, clazz)
            } catch (e: Exception) {
                WeHelpLog.e(str, e)
                return Gson().fromJson(str, clazz)
            }
        } catch (e: Exception) {

            WeHelpLog.e(str, e)
        }

        return null
    }

    @JvmStatic
    fun toJsonString(any: Any): String? {
        return try {
            JSON.toJSONString(any)
        } catch (e: Exception) {
            WeHelpLog.e("any", e)
            null
        }
    }

}