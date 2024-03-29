package di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import data_source.UserApi
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import repository.ApiRepository
import repository.ApiRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://randomuser.me/api/"
private const val CONNECT_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 10L
private const val READ_TIMEOUT = 10L

private fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient().newBuilder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .build()

private fun provideGson(): Gson =
    GsonBuilder()
        .create()

private fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
    Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

fun provideUserApi(retrofit: Retrofit): UserApi =
    retrofit.create()

private fun provideApiRepositoryImpl(
    userApi: UserApi
): ApiRepository = ApiRepositoryImpl(userApi)


fun provideRetrofitModule(): Module =
    module {
        single { provideOkHttpClient() }
        single { provideGson() }
        single { provideRetrofit(okHttpClient = get(), gson = get()) }
        single { provideUserApi(retrofit = get()) }
        single { provideApiRepositoryImpl(userApi = get()) }
    }
