package com.afra55.httpforus.h

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Afra55
 * @date 2019-06-19
 * A smile is the best business card.
 */
class TokenInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val builder = request.newBuilder()
        val httpUrl = request.url()
//        val headers = request.headers()

        val urlStr = httpUrl.toString()


        return chain.proceed(builder.build())
    }


}