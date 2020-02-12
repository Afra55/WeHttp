package com.afra55.httpforus.h

import android.text.TextUtils
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import retrofit2.Converter
import java.io.OutputStreamWriter
import java.nio.charset.Charset

/**
 * @author Afra55
 * @date 2019-06-19
 * A smile is the best business card.
 */
class WeHelpJsonRequestBodyConverter<T>(var mediaTypeStr:String? = null) : Converter<T, RequestBody>{

    override fun convert(value: T): RequestBody? {
        try {
            if (value is RequestBody) {
                return value
            }
            val buffer = Buffer()
            val writer = OutputStreamWriter(buffer.outputStream(), Charset.forName("UTF-8"))
            writer.write(value.toString())
            writer.close()
            var mediaType:MediaType?
            if (TextUtils.isEmpty(mediaTypeStr)) {
                mediaType = MediaType.parse("application/json; charset=UTF-8")
            } else {
                try {
                    mediaType = MediaType.parse(mediaTypeStr)
                } catch (e: Exception) {
                    mediaType = MediaType.parse("application/json; charset=UTF-8")
                }
            }
            return RequestBody.create(mediaType, buffer.readByteString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}