package me.jameshunt.coolphotogrid.di.app

import dagger.Module
import dagger.Provides
import me.jameshunt.coolphotogrid.repo.HeaderInterceptor
import me.jameshunt.coolphotogrid.repo.UnsplashService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by James on 10/4/2017.
 */

@Module
class NetworkModule {

    //private val BASE_URL = "http://jsonplaceholder.typicode.com"

    private val BASE_URL = "https://api.unsplash.com/"

    @Singleton
    @Provides
    internal fun getNetworkClient(): Retrofit {

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(HeaderInterceptor())

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(logging)

        val client = httpClient.build()

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    internal fun getUnsplashService(client: Retrofit): UnsplashService {
        return client.create<UnsplashService>(UnsplashService::class.java)
    }
}