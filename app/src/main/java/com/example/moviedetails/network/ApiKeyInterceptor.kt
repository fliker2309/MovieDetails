package com.example.moviedetails.network

import okhttp3.Interceptor
import okhttp3.Response

const val API_KEY = "63465eb594a4f703d9ae4b0842de07a5"
const val LANG = "en-US"

class ApiKeyInterceptor : Interceptor {

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val LANGUAGE_HEADER = "Language"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val request = originalRequest.newBuilder()
            .url(originalHttpUrl)
            .addHeader(AUTHORIZATION_HEADER, API_KEY)
            .addHeader(LANGUAGE_HEADER, LANG)
            .build()

        return chain.proceed(request)
    }
}