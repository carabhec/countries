package com.hcdisat.countrylist.dataaccess.service

import com.hcdisat.countrylist.dataaccess.ServiceConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration

object RetrofitBuilder {
    val countryService: CountryService by lazy {
        buildRetrofit().create(CountryService::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(buildHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ServiceConstants.BASE_URL)
            .build()
    }

    private fun buildHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(Duration.ofSeconds(20))
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }
}