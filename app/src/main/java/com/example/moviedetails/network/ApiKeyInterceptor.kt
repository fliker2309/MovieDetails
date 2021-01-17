package com.example.moviedetails.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor  : Interceptor{

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val request = originalRequest.newBuilder()
            .url(originalHttpUrl)
            .addHeader(AUTHORIZATION_HEADER, API_KEY)
            .build()
        return chain.proceed(request)
    }
}