package br.com.rafaeldias.apipokedex.data.remote

import br.com.rafaeldias.apipokedex.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceClient {

    private const val TIMEOUT: Long = 20

    fun <T> getServiceClient(apiClass: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiClass)
    }

    private fun logInterceptor(): HttpLoggingInterceptor = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY }
    else
        HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.NONE }

    private fun createOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logInterceptor())
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)

        return okHttpClient.build()
    }
}