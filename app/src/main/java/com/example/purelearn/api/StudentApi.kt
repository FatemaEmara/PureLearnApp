package com.example.purelearn.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.Response

interface StudentApi {
    @FormUrlEncoded
    @POST("student/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<Unit>
}
