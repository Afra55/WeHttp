package com.afra55.httpforus.h

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.ConcurrentHashMap

/**
 * @author Afra55
 * @date 2019-06-19
 * A smile is the best business card.
 */
object WeRHelper {

    private var weRetrofit: Retrofit? = null
    private val weRetrofitMap: ConcurrentHashMap<String, Retrofit> = ConcurrentHashMap(1)

    /**
     * 需要自行修改 APP_SERVER
     * @see WeHelpApiField.APP_SERVER
     */
    fun <T> httpServer(cls: Class<T>): T {
        if (weRetrofit == null) {
            weRetrofit =
                createRetrofit(WeHelpApiField.APP_SERVER, null, WeHelpOkHtUtils.getOkHttpClient())
        }

        return weRetrofit!!.create(cls)
    }

    fun <T> selfServer(api: String, mediaTypeString: String?, cls: Class<T>): T {
        return createRetrofit(api, mediaTypeString, WeHelpOkHtUtils.getOkHttpClient()).create(cls)
    }

    fun <T> createServer(tag: String, api: String, mediaTypeString: String?, cls: Class<T>): T {
        var re = weRetrofitMap[tag]
        if (re == null) {
            re = createRetrofit(api, mediaTypeString, WeHelpOkHtUtils.getOkHttpClient())
            weRetrofitMap[tag] = re
        }
        return re.create(cls)
    }

    private fun createRetrofit(
        baseUrl: String,
        mediaTypeString: String?,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(WeResponseConverterFactory.create(mediaTypeString))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }


}