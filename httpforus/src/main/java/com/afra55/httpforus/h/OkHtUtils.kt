package com.afra55.httpforus.h

import com.thd.thdn.THDN
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * @author Afra55
 * @date 2019-06-19
 * A smile is the best business card.
 */
object OkHtUtils {

    private var pieOkHttpClient: OkHttpClient? = null

    fun getOkHttpClient() : OkHttpClient{

        if (pieOkHttpClient is OkHttpClient) {
            return pieOkHttpClient as OkHttpClient
        }

        val builder = OkHttpClient.Builder()

        if (THDN.isDebugMode) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }


        builder.connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(TokenInterceptor())


        val cacheFile = THDN.context?.cacheDir
        if (cacheFile != null) {
            builder.cache(Cache(cacheFile, 10 * 1024 * 1024))
        }

        pieOkHttpClient = builder.build()

        return pieOkHttpClient as OkHttpClient
    }


}