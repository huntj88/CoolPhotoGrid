package me.jameshunt.coolphotogrid.repo

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by James on 10/5/2017.
 */
class HeaderInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()

        requestBuilder.addHeader("Authorization", "Client-ID efeb4efce6a5949bd488665359f4be15788a0a28b903d6a86bb88fb22061f08c")

        return chain.proceed(requestBuilder.build())
    }
}