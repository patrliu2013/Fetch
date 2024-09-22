package com.example.modules

import com.example.data.network.FetchApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val FETCH_BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

val networkModule = module {
    //retrofit
    single(named("retrofitFetch")) { provideRetrofitFetch() }

    //api services
    single { provideFetchApiService(get(named("retrofitFetch"))) }
}

fun provideRetrofitFetch() : Retrofit {
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(Interceptor { chain ->
        val request: Request =
            chain.request().newBuilder()
                .addHeader("content-type", "application/json")
                .build()
        chain.proceed(request)
    })

    return Retrofit.Builder()
        .baseUrl(FETCH_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
//        .client(httpClient.build())
//        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
}

fun provideFetchApiService(retrofit: Retrofit): FetchApiService =
    retrofit.create(FetchApiService::class.java)