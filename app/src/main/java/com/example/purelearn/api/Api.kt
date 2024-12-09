package com.example.purelearn.api

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

//    @POST("signup")
//    suspend fun signup(@Body signUpRequest, email: String, password: String, name: String): Response<SignUpRespose>

    @FormUrlEncoded
    @POST("signup")
    suspend fun signUp(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("name") name: String
    ): Response<SignUpRespose>


//    @FormUrlEncoded
//    @POST("login")
//   suspend fun login(
//       @Field("email") email:String,
//       @Field("password") password: String,
//   ):Response<LoginResponce>


//    @FormUrlEncoded
//    @POST("login")
//    suspend fun login(
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): Response<LoginResponce>

}