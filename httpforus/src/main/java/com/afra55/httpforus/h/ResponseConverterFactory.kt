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
class ResponseConverterFactory(var jsonObject: JSONObject, var mediaTypeStr: String?) : Converter.Factory() {

    companion object {
        @JvmStatic
        fun create(): ResponseConverterFactory {
            return create(JSONObject(), null)
        }

        @JvmStatic
        fun create(mediaType: String?): ResponseConverterFactory {
            return create(JSONObject(), mediaType)
        }

        @JvmStatic
        private fun create(jsonObject: JSONObject, mediaType: String?): ResponseConverterFactory {
            return ResponseConverterFactory(jsonObject, mediaType)
        }
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return JsonResponseBodyConverter<Converter<ResponseBody, *>?>(type)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        return JsonRequestBodyConverter<Converter<ResponseBody, *>?>(mediaTypeStr)
    }

}