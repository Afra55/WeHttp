package com.afra55.httpforus.h

import com.afra55.httpforus.WeHelp
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * @author Afra55
 * @date 2019-06-19
 * A smile is the best business card.
 */
object WeHelpOkHtUtils {

    private var WeHelpOkHttpClient: OkHttpClient? = null

    private var interceptorList = mutableListOf<Interceptor>()

    fun addInterceptor(interceptor: Interceptor) {
        interceptorList.add(interceptor)
    }

    fun getOkHttpClient() : OkHttpClient{

        if (WeHelpOkHttpClient is OkHttpClient) {
            return WeHelpOkHttpClient as OkHttpClient
        }

        val builder = OkHttpClient.Builder()

        if (WeHelp.isDebugMode) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }


        builder.connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

        for (i in interceptorList) {
            builder.addInterceptor(i)
        }

        val cacheFile = WeHelp.context?.cacheDir
        if (cacheFile != null) {
            builder.cache(Cache(cacheFile, 10 * 1024 * 1024))
        }

        WeHelpOkHttpClient = builder.build()

        return WeHelpOkHttpClient as OkHttpClient
    }


}