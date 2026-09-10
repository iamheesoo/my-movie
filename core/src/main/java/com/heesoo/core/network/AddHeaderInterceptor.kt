package com.heesoo.core.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    private val headerMap: Map<String, String>
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder().apply {
            headerMap.forEach { (key, value) -> addHeader(key, value) }
        }
            .build()
        return chain.proceed(newRequest)
    }
}