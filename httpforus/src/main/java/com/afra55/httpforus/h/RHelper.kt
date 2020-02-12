package com.afra55.httpforus.h

import com.afra55.httpforus.h.ApiField
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * @author Afra55
 * @date 2019-06-19
 * A smile is the best business card.
 */
object RHelper {

    private var weRetrofit: Retrofit? = null
    private var httpClient: OkHttpClient? = null

    fun <T> httpServer(cls:Class<T>) : T {
        if (weRetrofit == null) {
            if (httpClient == null) {
                httpClient = OkHtUtils.getOkHttpClient()
            }
            weRetrofit = createRetrofit(ApiField.APP_SERVER, null, httpClient!!)
        }

       return  weRetrofit!!.create(cls)
    }


    private fun createRetrofit(baseUrl:String, mediaTypeString: String?, okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ResponseConverterFactory.create(mediaTypeString))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }


}