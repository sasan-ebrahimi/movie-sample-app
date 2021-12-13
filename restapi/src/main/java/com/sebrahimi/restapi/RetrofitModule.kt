package com.sebrahimi.restapi

import com.sebrahimi.common.DIKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    // TODO Hide API KEY
    private const val API_KEY =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3N2ZjN2Y4MDQwZjQ3NDBmYTEzODI0OWVhZjBmMTViZiIsInN1YiI6IjYxYTgwNzg1MzgzZGYyMDAyYzc2ZTgwOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rqRYIgKPh7rkvzkOuKZg3WIBZMPBRI1OcA351dF3qJE"

    @Provides
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val authenticatedRequest = original.newBuilder()
                .header(
                    "Authorization",
                    "Bearer $API_KEY"
                ).build();
            chain.proceed(authenticatedRequest)
        }
    }

    @Provides
    fun provideHttpClient(
        authInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(
        @Named(DIKey.BASE_URL) baseUrl: String,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}