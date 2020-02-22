package com.elemental.graphqltesting.network

import com.elemental.graphqltesting.config.ApiKeyToken
import com.elemental.graphqltesting.config.Const
import com.elemental.graphqltesting.data.model.GraphqlQuery
import com.elemental.graphqltesting.data.response.Response
import com.elemental.graphqltesting.data.response.Users
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("graphql")
    fun insertTodo(@Body query: GraphqlQuery): Call<Response>

    @POST("graphql")
    fun getUserName(@Body query: GraphqlQuery):Call<Users>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ) : ApiService {

            val requestInterceptor = Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization","Bearer ${ApiKeyToken.apiToken}")
                    .addHeader("Content-Type","application/json")
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(Const.ONLINE_API_END)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()
                .create(ApiService::class.java)
        }
    }
}