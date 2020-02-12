package com.afra55.httpforus.h

import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * @author Afra55
 * @date 2019-06-19
 * A smile is the best business card.
 */
class WeResponseConverterFactory(var jsonObject: JSONObject, var mediaTypeStr: String?) : Converter.Factory() {

    companion object {
        @JvmStatic
        fun create(): WeResponseConverterFactory {
            return create(JSONObject(), null)
        }

        @JvmStatic
        fun create(mediaType: String?): WeResponseConverterFactory {
            return create(JSONObject(), mediaType)
        }

        @JvmStatic
        private fun create(jsonObject: JSONObject, mediaType: String?): WeResponseConverterFactory {
            return WeResponseConverterFactory(jsonObject, mediaType)
        }
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return WeHelpJsonResponseBodyConverter<Converter<ResponseBody, *>?>(type)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        return WeHelpJsonRequestBodyConverter<Converter<ResponseBody, *>?>(mediaTypeStr)
    }

}