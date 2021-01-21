package com.example.moviedetails.data.network

import okhttp3.Interceptor
import okhttp3.Response

const val API_KEY = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MzQ2NWViNTk0YTRmNzAzZDlhZTRiMDg0MmRlMDdhNSIsInN1YiI6IjVmZjc4MWI1NmMwYjM2MDAzZWEyOGVlYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.oegbpVOXZoPWW0RsGVU0FpO3Vd7fOR9uzDFYFePS3-8"
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