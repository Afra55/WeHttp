package com.afra55.httpforus.h

import com.thd.thdn.LuckLog
import com.afra55.httpforus.u.WeHelpJsonUtils
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.BufferedReader
import java.lang.reflect.Type

/**
 * @author Afra55
 * @date 2019-06-19
 * A smile is the best business card.
 */
class JsonResponseBodyConverter<T>(var type: Type) : Converter<ResponseBody, T>{


    override fun convert(value: ResponseBody): T? {
        try {
            val br = BufferedReader(value.charStream())
            var line:String?
            val buffer = StringBuilder()
            while (true) {
                line = br.readLine()
                if (line != null) {
                    buffer.append(line)
                } else {
                    break
                }
            }

            return WeHelpJsonUtils.parseObject(buffer.toString(), type)
        } catch (e: Exception) {
            LuckLog.e("convert", e)
        }

        return null

    }

}