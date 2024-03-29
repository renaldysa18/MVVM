package com.redveloper.mvvm.data.network

import com.redveloper.mvvm.data.network.responses.AuthResponse
import com.redveloper.mvvm.data.network.responses.QuoteResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface BaseApi {

    //login
    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Response<AuthResponse>

    //signup
    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignUp(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ) : Response<AuthResponse>

    //quote
    @GET("quotes")
    suspend fun getQoutes() : Response<QuoteResponse>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : BaseApi{

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BaseApi::class.java)
        }
    }
}